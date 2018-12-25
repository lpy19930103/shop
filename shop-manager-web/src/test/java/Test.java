import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, MyException {
        // 1. 加载FastDFS的tracker的配置信息，其实就是 tracker_server=192.168.37.161:22122
        ClientGlobal.init(System.getProperty("user.dir") + "\\shop-manager-web\\src\\main\\resources\\fastdfs-client.properties");
        // 2. 创建TrackerClient，直接new
        TrackerClient trackerClient = new TrackerClient();
        // 3. 使用TrackerClient，获取TrackerServer
        TrackerServer connection = trackerClient.getConnection();
        // 4. 声明StorageServer，为null就可以了
        StorageServer storageServer = null;
        // 5. 创建StorageClient，需要两个参数TrackerServer，StorageServer
        StorageClient storageClient = new StorageClient(connection, storageServer);
        // 6. 使用StorageClient上传图片
        String[] result = storageClient.upload_file("C:\\Users\\lpy\\Desktop\\tupian.jpg", "jpg", null);
        // 7. 打印返回的结果，String数组
        for (String s : result) {
            System.out.println(s);
        }

    }
}
