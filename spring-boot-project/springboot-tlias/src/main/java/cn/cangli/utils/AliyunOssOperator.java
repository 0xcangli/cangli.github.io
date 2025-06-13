package cn.cangli.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@Component
public class AliyunOssOperator {

    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;

    public String upload(byte[] content, String fileName) throws Exception {
        String endpoint = aliyunOSSConfig.getEndpoint();
        String bucketName = aliyunOSSConfig.getBucketName();
        String region = aliyunOSSConfig.getRegion();
        /*
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "cangli";
         */

        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
//        String objectName = "001.pdf";

        // 原始文件路径
//        String originalPath = "example/image.png"; // 示例路径

//        String region = "cn-beijing";


//        File originalFile = new File(fileName);

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 获取文件扩展名
        String extension = "";
//        String name = originalFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            extension = fileName.substring(dotIndex); // 包含点号，如 ".png"
        }

        // 生成 UUID 文件名
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + extension;

        System.out.println(new Date() + "<UNK>" + newFileName);
        // 获取父目录 + 新文件名组成完整路径
//        File renamedFile = new File(originalFile.getParent(), newFileName);

        // 重命名操作
//        boolean success = originalFile.renameTo(renamedFile);
//
//        if (success) {
//            System.out.println("重命名成功，新文件名: " + renamedFile.getAbsolutePath());
//        } else {
//            System.out.println("重命名失败");
//        }

        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。


        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        String url=null;
        try {
//            file = new File("/home/ime/Downloads/1.7 Linux程序编译调试方法.pdf");
//            byte[] content = Files.readAllBytes(file.toPath());

            //上传文件
            ossClient.putObject(bucketName, newFileName, new ByteArrayInputStream(content));
            url = "https://" + bucketName + ".oss-cn-beijing.aliyuncs.com/" + newFileName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return url;

    }
}
