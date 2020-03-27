package myTest;

import com.ppf.springboot.annotation.MyAnnotationUntils;
import org.apache.poi.ss.formula.functions.T;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class Test {

    static int num = 0;
    public static void main(String[] args) {
//取得目标目录
        //String path = "F:/Program Files/java/jdk1.8.0_144";
        String path = "F:/ppf";
        File file = new File(path);
//获取目录下子文件及子文件夹
        File[] files = file.listFiles();
        ArrayList<Integer> list = new ArrayList<>();
        list = readfile(files, list);
        System.out.println(list.size());
        System.out.println("调用次数："+num);

    }

    public static ArrayList<Integer> readfile(File[] files, ArrayList<Integer> list) {
//        if (files == null) {// 如果目录为空，直接退出
//            return list;
//        }
        System.out.println("abcd");
        for (File f : files) {
//如果是文件，直接输出名字
            if (f.isFile()) {
                list.add(1);
                //   System.out.println(f.getName());
            }
//如果是文件夹，递归调用
            else {
                System.out.println("1234");
                readfile(f.listFiles(), list);
                num++;
            }
        }
        return list;
    }



}
