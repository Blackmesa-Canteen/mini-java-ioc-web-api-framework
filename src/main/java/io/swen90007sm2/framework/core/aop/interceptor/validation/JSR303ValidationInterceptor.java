package io.swen90007sm2.framework.core.aop.interceptor.validation;

import io.swen90007sm2.framework.annotation.validation.Validated;
import io.swen90007sm2.framework.bean.MethodCalling;
import io.swen90007sm2.framework.core.aop.interceptor.AbstractInterceptor;
import io.swen90007sm2.framework.exception.RequestException;
import io.swen90007sm2.framework.exception.ValidationException;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.*;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * interceptor to perform JSR303 validation
 *
 * @author xiaotian
 * @author shuang.kou:https://github.com/Snailclimb/jsoncat
 */
public class JSR303ValidationInterceptor extends AbstractInterceptor {

    private final Validator VALIDATOR;

    public JSR303ValidationInterceptor() {

        // close the factory if finished generate a Validator
        // attach hibernateValidator to JSR303 API
        try (ValidatorFactory factory = Validation.byProvider(
                HibernateValidator.class
                )
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()) {

            this.VALIDATOR = factory.getValidator();
        }
    }

    /**
     * check whther perform the validator on this bean
     */
    @Override
    public boolean supports(Object bean) {
        return (bean != null && bean.getClass().isAnnotationPresent(Validated.class));
    }

    @Override
    public Object intercept(MethodCalling methodCalling) {

        // get annotations from param
        Annotation[][] paramAnnos = methodCalling.getTargetMethod().getParameterAnnotations();
        Object[] methodArgs = methodCalling.getArgs();

        // get validated param, validate it with Hibernate validator
        // travel all params
        for (int i = 0; i < methodArgs.length; i++) {
            // one param may have multiple param
            Annotation[] annotationArray = paramAnnos[i];
            boolean needValid = Arrays.stream(annotationArray).anyMatch(
                    // Valid.class Marks a property, method parameter or method return type for validation cascading.
                    annotation -> annotation.annotationType() == Valid.class
            );

            // if this param has @Valid anno, validate with JSR303
            if (needValid) {
                // use validator created by factory to validate the arg, then create result.
                if (ArrayUtils.isEmpty(methodArgs) || methodArgs[i] == null) {
                    throw new RequestException("missing input param");
                }
                Set<ConstraintViolation<Object>> results = VALIDATOR.validate(methodArgs[i]);
                if (!results.isEmpty()) {
                    throw new ValidationException(results);
                }
            }
        }

        // passed validation, continue calling the method
        return methodCalling.proceed();
    }
}
