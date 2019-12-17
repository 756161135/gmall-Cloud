package com.atguigu.gmall.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import org.springframework.web.multipart.MultipartFile;


public class MyFileUpload {
    public static String uploadImage(MultipartFile multipartFile){
        String url=ConstantClass.IP;
        //1.获取配置路径
        String path = MyFileUpload.class.getClassLoader().getResource("tracker.properties").getPath();

        try {
            //2.全局配置
            ClientGlobal.init(path);
            TrackerClient client = new TrackerClient();
            TrackerServer connection = client.getConnection();
            StorageClient storageClient = new StorageClient(connection,null);

            //4.获取一个地址的后缀
            String original = multipartFile.getOriginalFilename();
            int i = original.lastIndexOf(".");
            String postfix = original.substring(i + 1);
            //5.上传文件
            String[] paths = storageClient.upload_file( multipartFile.getBytes(), postfix, null);

            //6.拼接路径
            for (String p : paths) {
                url= url+"/"+p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        return url;
    }
}
