package com.ppf.springboot.untis.pathUntis;

import java.io.File;
import java.util.regex.Matcher;

public class PathUntil {


    public static String path(String path) {

//        String s  = path.replaceAll("/", Matcher.quoteReplacement(File.separator))
//                .replaceAll("\\\\", Matcher.quoteReplacement(File.separator));

//        String s  = path.replaceAll("/", File.separator)
//                .replaceAll("\\\\", File.separator);

 // replaceAll(regex,target)方法中,regex会支持正则表达式，所以用Matcher.quoteReplacement(regex)压制不允许转义


        String target =  Matcher.quoteReplacement(File.separator);

        String s = path.replaceAll("/",target).replaceAll("\\\\",target);
           return s;
    }
}
