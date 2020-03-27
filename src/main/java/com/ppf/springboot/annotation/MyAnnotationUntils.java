package com.ppf.springboot.annotation;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.net.URL;
import java.util.List;

public final class MyAnnotationUntils implements NumberPoint{


    public static List<Class<?>> getAllClass() throws ClassNotFoundException {
        Class<MyAnnotationUntils> myAnnotationUntilsClass = MyAnnotationUntils.class;
        String path = myAnnotationUntilsClass.getResource("").getPath();


        //文件所在上一级目录的物理地址：F:/java/myproject/springboot/target/classes/com/ppf/springboot/annotation
        String parent_absolutePath;

        //文件所在目录的物理地址：F:/java/myproject/springboot/target/classes/com/ppf/springboot/annotation/MyAnnotationUntils.class
        String absolutePath;

        //文件上一级目录的相对路径：com.ppf.springboot.annotation
        String parent_relativePath;

        //文件相对路径（不含后缀名）：com.ppf.springboot.annotation.MyAnnotationUntils
        String relativePath;

        //文件相对路径（包含.class）:com.ppf.springboot.annotationLoginRequired.class
        String relativePath_;


        //文件名(包含.class):LoginRequired.class
        String fileName_;

        //文件名(不包含.class)LoginRequired
        String fileName;


        parent_absolutePath = path.substring(1, path.length() - 1);
        System.out.println("文件所在上一级目录的物理地址：" + parent_absolutePath);

        parent_relativePath = myAnnotationUntilsClass.getPackage().getName();
        System.out.println("文件上一级目录的相对路径：" + parent_relativePath);


        File file = new File(parent_absolutePath);
        fileName_ = file.list()[0];
        System.out.println("文件名(包含后缀名)" + fileName_);

        fileName =fileName_.substring(0,fileName_.length() -6) ;
        System.out.println("文件名(不包含后缀名)" + fileName);

        relativePath_ = parent_relativePath + "." + fileName_;
        System.out.println("文件相对路径(包含后缀名)" + relativePath_);

        relativePath = relativePath_.substring(0, relativePath_.length() - 6);
        System.out.println("文件相对路径(不包含后缀名)" + relativePath);


        String str = MyAnnotationUntils.class.getName();


        MyAnnotationUntils.class.getName();
        System.out.println(str);

        return null;
    }


    @Override
    public int value11() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
