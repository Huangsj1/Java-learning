package day7;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipStreamDemo {
    public static void main(String[] args) throws IOException {
        // 一、解压缩
        System.out.println("----------解压缩----------");
        File src = new File("E:\\javaTest\\myZip.zip");
        File des = new File("E:\\javaTest\\");
        unzip(src, des);

        // 二、压缩(压缩文件夹)
        System.out.println("----------压缩----------");
        File src2 = new File("E:\\javaTest\\myZip2");
        File des2 = new File(src2.getPath() + ".zip");
        // 创建压缩流关联到指定压缩包
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(des2));
        tozip(src2, zos, src2.getName());
        zos.close();
    }

    public static void unzip(File src, File des) throws IOException {
        // 解压的本质：将压缩包的所有文件/文件夹拿出来，拷贝到本地目的地中
        ZipInputStream zis = new ZipInputStream(new FileInputStream(src));
        // 当前压缩包中获取到的文件/文件夹，都是ZipEntry对象
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println(entry);
            if (entry.isDirectory()) {
                // 如果是目录就需要在目的地创建对应的目录
                File file = new File(des, entry.toString());
                file.mkdirs();
            } else {
                // 如果是文件就读取后写
                FileOutputStream fos = new FileOutputStream(new File(des, entry.toString()));
                int b;
                while ((b = zis.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                zis.closeEntry();
            }
        }
        zis.close();
    }

    public static void tozip(File src, ZipOutputStream zos, String path) throws IOException {
        // 遍历src中所有文件/文件夹，在压缩包里创建并写入
        File[] files = src.listFiles();
        if (files.length == 0) {
            // 空文件夹也要创建
            ZipEntry entry = new ZipEntry(path + "\\");
            zos.putNextEntry(entry);
        }
        for (File file : files) {
            if (file.isFile()) {
                // 获取ZipEntry对象，为压缩包中的对象(相对压缩包的路径)
                ZipEntry entry = new ZipEntry(path + "\\" + file.getName());
                zos.putNextEntry(entry);
                // 读取文件中的数据，写到压缩包
                FileInputStream fis = new FileInputStream(file);
                int b;
                while ((b = fis.read()) != -1) {
                    zos.write(b);
                }
                fis.close();
                zos.closeEntry();
            } else {
                tozip(file, zos, path + "\\" + file.getName());
            }
        }
    }
}
