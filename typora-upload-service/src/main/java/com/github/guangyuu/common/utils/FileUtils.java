package com.github.guangyuu.common.utils;


import java.io.*;

/**
 * @Author Guangyu
 * @Date 2022/8/9
 * @Description 文件处理工具类
 **/
public class FileUtils {
    /**
     * 数据写入文件
     *
     * @param path     目录
     * @param filename 文件名
     * @param bytes    数据
     * @return boolean
     */
    public static boolean writeFile(String path, String filename, byte[] bytes) {
        File file = new File(path, filename);
        return writeFile(file, bytes);
    }

    /**
     * 数据写入文件
     *
     * @param file  文件
     * @param bytes 数据
     * @return boolean
     */
    public static boolean writeFile(File file, byte[] bytes) {
        if (file == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            ioClose(null, fileOutputStream);
        }
        return true;
    }


    /**
     * 释放流
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     */
    private static void ioClose(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
