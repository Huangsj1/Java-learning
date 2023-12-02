package day6;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        // 一、创建文件
        // 1.根据文件路径创建
        String str = "E:\\计算机自学指南\\java\\test\\a.txt";
        File f1 = new File(str);
        System.out.println(f1);
        // 2.根据父级路径和子路径创建(就是将两者拼接)
        String parent = "E:\\计算机自学指南\\java\\test";
        String child = "a.txt";
        File f2 = new File(parent, child);
        System.out.println(f2);
        // 3.将File表示的路径和String表示的路径进行拼接
        File parent2 = new File("E:\\计算机自学指南\\java\\test");
        String child2 = "a.txt";
        File f3 = new File(parent2, child2);
        System.out.println(f3);

        // 二、判断和获取
        // 1.判断是否存在/文件/文件夹
        System.out.println("----------judge----------");
        System.out.println(f1.exists());
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        // 2.获取文件信息
        System.out.println("----------get info----------");
        System.out.println(f1.length());            // 返回的是字节，不能获取文件夹大小
        System.out.println(f1.getAbsolutePath());
        System.out.println(f1.getPath());
        System.out.println(f1.getName());
        System.out.println(f1.lastModified());

        // 三、创建和删除
        // 1.createNewFile 创建新的空的文件
        //   1)已存在的无法创建
        //   2)父路径不存在无法创建
        //   3)创建的一定是文件，可以无后缀名
        System.out.println("----------createNewFile----------");
        File f4 = new File("E:\\计算机自学指南\\java\\test\\abc.txt");
        boolean res1 = f4.createNewFile();
        System.out.println(res1);
        // 2.mkdir 创建文件夹
        //   1)已存在的无法创建
        //   2)父路径不存在无法创建
        //   3)路径不可存在重名的文件
        System.out.println("----------mkdir----------");
        File f5 = new File("E:\\计算机自学指南\\java\\test\\ddd");
        boolean res2 = f5.mkdir();
        System.out.println(res2);
        // 3.mkdirs 创建多级文件夹(会调用mkdir)
        System.out.println("----------mkdirs----------");
        File f6 = new File("E:\\计算机自学指南\\java\\test\\ddd\\eee\\fff");
        boolean res3 = f6.mkdirs();
        System.out.println(res3);
        // 4.delete 删除文件、空文件夹
        System.out.println("----------delete----------");
        boolean res4 = f6.delete();
        System.out.println(res4);

        // 四、文件夹的获取并遍历
        // 1.listFiles 得到当前文件夹下所有文件/文件夹的File（若文件夹不存在或无法打开则返回null，若里面没东西则数组长度为0）
        System.out.println("----------listFiles----------");
        File f7 = new File("E:\\计算机自学指南\\java\\test");
        File[] files = f7.listFiles();
        for (File file : files) {
            System.out.println(file);
        }

        // 练习一：查找某个文件夹下所有.avi文件
        System.out.println("==========practice1==========");
        File f8 = new File("E:\\计算机自学指南");
        findAVI(f8);
        //findAllAVI();

        // 练习二、查找文件夹下所有类型的文件，并计数
        System.out.println("==========practice2==========");
        File f9 = new File("E:\\计算机自学指南\\java\\test");
        System.out.println(getCount(f9));
    }

    public static void findAllAVI() {
        // 获取所有盘
        File[] allFile = File.listRoots();
        System.out.println(Arrays.toString(allFile));
        // 遍历所有盘
        for (File file : allFile) {
            System.out.println("正在搜索 " + file.getPath());
            findAVI(file);
        }
    }

    public static void findAVI(File src) {
        // 首先获得当前文件夹下所有文件/文件夹
        File[] files = src.listFiles();
        // 然后遍历查找，若为文件则判断是否后缀为.avi，若为文件夹则递归查找
        if(files != null) {
            for (File file : files) {
                if(file.isFile()) {
                    if(file.getName().endsWith(".avi")) {
                        System.out.println(file);
                    }
                } else {
                    findAVI(file);
                }
            }
        }
    }

    public static HashMap<String, Integer> getCount(File src) {
        // 1.定义Map计数
        HashMap<String, Integer> hm = new HashMap<>();
        // 2.进入src文件夹
        File[] files = src.listFiles();
        if (files == null) return null;
        for (File file : files) {
            // 3.如果是文件，统计
            if (file.isFile()) {
                String name = file.getName();
                // 分割得到后缀,\\.表示得到单纯的"."，而不是通用字符
                String[] arr = name.split("\\.");
                if (arr.length >= 2) {
                    // a.txt, a.t.txt
                    String endName = arr[arr.length - 1];
                    if (hm.containsKey(endName)) {
                        // 存在key
                        int count = hm.get(endName);
                        count++;
                        hm.put(endName, count);
                    } else {
                        // 不存在key
                        hm.put(endName, 1);
                    }
                }
            } else {
                // 递归文件夹
                HashMap<String, Integer> sonMap = getCount(file);
                // 遍历SonMap的所有键值对添加到当前HashMap中
                Set<Map.Entry<String, Integer>> entries = sonMap.entrySet();
                for (Map.Entry<String, Integer> entry : entries) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    if (hm.containsKey(key)) {
                        // 存在key
                        int count = hm.get(key);
                        count += value;
                        hm.put(key, count);
                    } else {
                        // 不存在key
                        hm.put(key, value);
                    }
                }
            }
        }
        return hm;
    }
}
