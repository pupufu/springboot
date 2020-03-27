package myTest;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class Test123 {
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

    }

    public static ArrayList<Integer> readfile(File[] files, ArrayList<Integer> list) {
        if (files == null) {// 如果目录为空，直接退出
            return list;
        }
//        for (File f : files) {
////如果是文件，直接输出名字
//            if (f.isFile()) {
//                list.add(1);
//            }
        for (int i = 0; i <files.length ; i++) {
            File f = files[i];

            if (f.isFile()){
                list.add(1);
            }
//如果是文件夹，递归调用
            else{
                readfile(f.listFiles(), list);
            }
        }
        return list;
    }



}
