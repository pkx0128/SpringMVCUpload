package com.pankx.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 图片上传的控制器类
 */
@Controller
@RequestMapping("/upload")
public class UserController {

    /**
     * 传统文件上传方法实现
     * @return
     */
    @RequestMapping("/myupload")
    public String myupload(HttpServletRequest request) throws Exception {
        System.out.println("myupload方法执行。。。");
        //指定文件上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploadFiles/");
        //判断文件目录是否存在，不存在则创建目录
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //创建FileItem对象工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //使用DiskFileItemFactory对象创建文件上传对象
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析reuqest，并获取文件上传项
        List<FileItem> list = upload.parseRequest(request);
        for(FileItem fileItem:list){
            if(fileItem.isFormField()){
                //简单表数据
            }else{
                //获取文件名称
                String filename = fileItem.getName();
                //设置文件的名为唯一值
                //生成UUID随机值，并去掉值的"-"
                String uuid = UUID.randomUUID().toString().replace("-","");
                //拼接文件名称
                filename = uuid + "_" +filename;
                //上传文件
                fileItem.write(new File(path,filename));
                //删除临时文件
                fileItem.delete();
            }
        }
        return "success";
    }

    /**
     * SpringMVC文件上传实例
     * @return
     */
    @RequestMapping("/mvcupload")
    public String mvcupload(HttpServletRequest request, MultipartFile mvcuploadfile) throws IOException {

        //指定上传文件位置
        String path = request.getSession().getServletContext().getRealPath("/mvcuploads/");
        //判断路径是否存在,不存在就创建
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取文件实际名称
        String filename = mvcuploadfile.getOriginalFilename();
        //设置文件的名为唯一值
        //生成UUID随机值，并去掉值的"-"
        String uuid = UUID.randomUUID().toString().replace("-","");
        //拼接文件名称
        filename = uuid + "_" +filename;
        //完成文件上传
        mvcuploadfile.transferTo(new File(path,filename));
        return "success";
    }

    /**
     * 跨服务器上传文件简单实例
     * @param uptofileserver
     * @return
     * @throws IOException
     */
    @RequestMapping("/uptofileserver")
    public String uptofileserver(MultipartFile uptofileserver) throws IOException {
        //文件服务的目录
        String path = "http://localhost:8090/uploads/";
        //获取文件的名称
        String filename = uptofileserver.getOriginalFilename();
        //创建客户端对象
        Client client = Client.create();
        //连接文件服务器
        WebResource resource = client.resource(path+filename);
        //上传文件到文件服务器
        resource.put(uptofileserver.getBytes());
        return "success";
    }
}
