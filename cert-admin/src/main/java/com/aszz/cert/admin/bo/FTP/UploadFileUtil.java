package com.aszz.cert.admin.bo.FTP;

/**
 * Created by User on 2020/3/17.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
    /**
     * 使用相同的名称
     *
     * @param file
     * @param filePath
     * @return
     */
//    public static String handleFileUpload(MultipartFile file,String filePath){
    public static boolean handleFileUpload(MultipartFile file, String filePath) {
        try {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                InputStream inpustream = file.getInputStream();
                FileOutputStream fos = new FileOutputStream(filePath + File.separator + name);
                Streams.copy(inpustream, fos, true);
            } else {
//                return "can not upload this file,because it is empty!";
                return false;
            }
        } catch (IOException e) {
//            return "failed to upload this file,because Stream interrupt unexpectedly";
            return false;
        }
//        return "upload file sucess";
        return true;
    }

    /**
     * 重命名文件，包含后缀
     *
     * @param file
     * @param filePath
     * @param fileName
     * @return
     */
    public static String handleFileUpload(MultipartFile file, String filePath, String fileName) {
        try {
            if (!file.isEmpty()) {
                InputStream inpustream = file.getInputStream();
                FileOutputStream fos = new FileOutputStream(filePath + File.separator + fileName);
                Streams.copy(inpustream, fos, true);
            } else {
                return "can not upload this file,because it is empty!";
            }
        } catch (IOException e) {
            return "failed to upload this file,because Stream interrupt unexpectedly";
        }
        return "upload file sucess";
    }

    /**
     * Copy bytes from a large (over 2GB) file with same filename
     *
     * @param file
     * @param filePath
     * @param fileName
     * @return
     */

    public static String handleLargeFileUpload(MultipartFile file, String filePath) {
        try {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                InputStream inpustream = file.getInputStream();
                FileOutputStream fos = new FileOutputStream(filePath + File.separator + name);
                IOUtils.copyLarge(inpustream, fos);
                if (inpustream != null) {
                    inpustream.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } else {
                return "can not upload this file,because it is empty!";
            }
        } catch (IOException e) {
            return "failed to upload this file,because Stream interrupt unexpectedly";
        }
        return "upload file sucess";
    }

    /**
     * Copy bytes from a large (over 2GB) file with rename file
     *
     * @param file
     * @param filePath
     * @param fileName
     * @return
     */

    public static String handleLargeFileUpload(MultipartFile file, String filePath, String fileName) {
        try {
            if (!file.isEmpty()) {
                InputStream inpustream = file.getInputStream();
                FileOutputStream fos = new FileOutputStream(filePath + File.separator + fileName);
                IOUtils.copyLarge(inpustream, fos);
                if (inpustream != null) {
                    inpustream.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } else {
                return "can not upload this file,because it is empty!";
            }
        } catch (IOException e) {
            return "failed to upload this file,because Stream interrupt unexpectedly";
        }
        return "upload file sucess";
    }

}