package com.example.testlocalfile;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @Author: liuyl
 * @Date: 2020/6/13 10:42
 * @Version: 1.0
 * @Description: 读取本项目resources下文件-测试输出到控制台
 */
public class TestLocalFile {
    public static void main(String[] args) throws IOException {
        File file = ResourceUtils.getFile("classpath:file/produce.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String str = reader.readLine();
            while (str != null) {
                System.out.println(str);
                str = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            //当抛出多个异常时，子异常当在父异常前抛出。
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
