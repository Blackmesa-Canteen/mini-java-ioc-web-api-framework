package io.swen90007sm2.db;

import io.swen90007sm2.core.helper.ConfigFileReader;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A helper for database connection with JDBC
 *
 * @author xiaotian
 */
public class DbHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbHelper.class);

    private static final ThreadLocal<Connection> DB_CONNECTION_HOLDER;

    private static final QueryRunner QUERY_RUNNER;

    private static final BasicDataSource DATA_SOURCE;

    static {
        DB_CONNECTION_HOLDER = new ThreadLocal<>();
        QUERY_RUNNER = new QueryRunner();

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(ConfigFileReader.getJdbcDriver());
        DATA_SOURCE.setUrl(ConfigFileReader.getJdbcUrl());
        DATA_SOURCE.setUsername(ConfigFileReader.getJdbcUsername());
        DATA_SOURCE.setPassword(ConfigFileReader.getJdbcPassword());
    }

    public static Connection getDbConnection() {
        Connection res = DB_CONNECTION_HOLDER.get();
        if (res == null) {
            try {
                res = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("db connection exception: ", e);
                throw new RuntimeException(e);
            } finally {
                DB_CONNECTION_HOLDER.set(res);
            }
        }
        return res;
    }

    /**
     * query the database, and map the result to the java entity
     */
    public static  <T> T queryToEntity(Class<T> entityClass, String sqlText, Object... params) {
        T res;
        try {
            Connection dbConnection = getDbConnection();
            res = QUERY_RUNNER.query(dbConnection, sqlText, new BeanHandler<>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query db exception: ", e);
            throw new RuntimeException(e);
        }

        return res;
    }

    /**
     * Insert
     */

    /**
     * Update
     */

    /**
     * delete
     */

}
