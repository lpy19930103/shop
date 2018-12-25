package com.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.common.pojo.PicUploadResult;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("pic/upload")
public class PicUploadController {


    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    private static String[] TYPE = {".jpg", ".jpeg", ".png", ".bmp", ".gif"};

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PicUploadResult upload(MultipartFile multipartFile) {
        boolean flag = false;
        PicUploadResult picUploadResult = new PicUploadResult();
        System.out.println(multipartFile.getOriginalFilename());
        for (String type : TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(),type)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            picUploadResult.setMsg("图片类型错误，请重新选择");
            return picUploadResult;
        }
        flag = false;
        try {
            BufferedImage read = ImageIO.read(multipartFile.getInputStream());
            if (read != null) {
                picUploadResult.setHeight(String.valueOf(read.getHeight()));
                picUploadResult.setWidth(String.valueOf(read.getWidth()));
                flag = true;
            }

        if (flag) {
            // 1. 加载FastDFS的tracker的配置信息，其实就是 tracker_server=192.168.37.161:22122
            ClientGlobal.init(System.getProperty("user.dir") + "\\src\\main\\resources\\fastdfs-client.properties");
            // 2. 创建TrackerClient，直接new
            TrackerClient trackerClient = new TrackerClient();
            // 3. 使用TrackerClient，获取TrackerServer
            TrackerServer connection = trackerClient.getConnection();
            // 4. 声明StorageServer，为null就可以了
            StorageServer storageServer = null;
            // 5. 创建StorageClient，需要两个参数TrackerServer，StorageServer
            StorageClient storageClient = new StorageClient(connection, storageServer);
            // 6. 使用StorageClient上传图片
            String ext = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
            String[] result = storageClient.upload_file(multipartFile.getBytes(), ext, null);
            // 7. 进行返回的结果的拼接，上传图片的url
            String picUrl = this.IMAGE_SERVER_URL + result[0] + "/" + result[1];

            // 设置图片url
            picUploadResult.setUrl(picUrl);

            // 上传成功设置为1
            picUploadResult.setError(1);

        }
        } catch (Exception e) {
            e.printStackTrace();
            picUploadResult.setMsg("图片上传失败，请重试");
        }
        return picUploadResult;

    }


}
