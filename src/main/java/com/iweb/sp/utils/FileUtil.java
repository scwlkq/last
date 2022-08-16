package com.iweb.sp.utils;

import com.aliyun.oss.common.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Lenovo
 * @date 2022/8/16 16:28
 */
public class FileUtil {
    public static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        if (StringUtils.isNullOrEmpty(fileName)) {
            return null;
        }
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            System.out.println(("MultipartFile转换为File转换异常：" + e.getMessage()));
        }
        return null;
    }

    public static boolean useOss(String name, MultipartFile file) {
        boolean flag  ;
        try {
            File loadFile = FileUtil.multipartFileToFile(file);
            File newNameFile = FileUtil.renameFile(name, loadFile);
            flag = OssUtil.upLoading(newNameFile.getName(), newNameFile.getAbsolutePath());
            newNameFile.delete();
            return flag;
        } catch (Exception e) {
            return false;
        }

    }

    public static File renameFile(String newName, File file) throws IOException {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        File tempFile = new File(file.getParent() + "/" + newName + fileType);
        return tempFile;
    }
}
