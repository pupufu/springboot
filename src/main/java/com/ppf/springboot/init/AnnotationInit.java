//package com.ppf.springboot.init;
//
//
//import com.ppf.springboot.untis.pathUntis.PathUntil;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.util.List;
//
//@Component
//public class AnnotationInit {
//    private static String ApplicationPath = "F:\\java\\myproject\\springboot\\src\\main\\java";
//    private static String relativePath = "com/ppf/springboot/annotation";
//    private static String obsulutePath = ApplicationPath + File.separator + relativePath;
//    //转化为系统分隔符
//    private static String relPath = PathUntil.path(relativePath);
//    private static  String obsPath = PathUntil.path(obsulutePath);
//
//
//    private static File file = new File(obsPath);
//    private static File[] files = file.listFiles();
//
//    static {
//        init();
//    }
//
//    public static void init()  {
//        for(File f :files){
//
//            if (f.isFile()) {
//                String name = f.getName();
//                System.out.println(name);
//                String simpleClassName = name.substring(0, name.length() - 5);
//                System.out.println(simpleClassName);
//                String className = relativePath + simpleClassName;
//                try {
//                    Class<?> aClass = Class.forName(className);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//
//
//}
//
//
//
//
