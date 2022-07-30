package org.example.handler;

import io.swen90007sm2.framework.annotation.mvc.Handler;
import io.swen90007sm2.framework.annotation.mvc.HandlesRequest;
import io.swen90007sm2.framework.annotation.mvc.PathVariable;
import io.swen90007sm2.framework.annotation.mvc.QueryParam;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author 996Worker
 * @description
 * @create 2022-07-30 22:21
 */
@Handler(path = "/file")
public class FileController {

    @HandlesRequest(path = "/err/{id}", method = RequestMethod.GET)
    public R echo (@PathVariable("id") long id,  HttpServletRequest req, HttpServletResponse resp) {
        return R.ok().setData(req.toString());
    }

    @HandlesRequest(path = "/err/{id}", method = RequestMethod.POST)
    public void echoPost (@PathVariable("id") long id,  HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("very good");
    }

    @HandlesRequest(path = "/download", method = RequestMethod.GET)
    public void downLoad (@QueryParam("fileName") String fileName, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("下载文件的路径："+fileName);
        //2. 下载的文件名叫什么
        String filename = fileName.substring(fileName.lastIndexOf("\\") + 1);
        try {
            //3. 设置想办法让浏览器能够支持（Content-Disposition）下载我们需要的东西，中文文件名URLEncoder.encode编码，否则有可能乱码（重庆.png）
            resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
            //4. 获取下载文件的输入流
            FileInputStream in = null;
            in = new FileInputStream(fileName);
            //5. 创建缓冲区
            int len = 0;
            byte[] buffer = new byte[1024];
            //6. 获取OutputStream对象
            ServletOutputStream out = resp.getOutputStream();
            //7. 将FileOutputStream流写到buffer缓冲区,使用OutputStream将缓冲区中的数据输出到客户端！
            while ((len=in.read(buffer))>0){
                out.write(buffer,0,len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}