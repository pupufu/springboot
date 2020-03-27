package com.ppf.springboot.untis.poiUntils;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ppf.springboot.untis.dateUntils.DateUntils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class POIUntils2222 {

//    /**
//     * @param inputStream
//     * @param clazz       （需要转换成的对象类型）
//     * @param firstRow    （从第几行开始导入，从0开始）
//     * @param lastRow     （最后一行导入的编号）
//     * @return
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     * @throws InstantiationException
//     */
//    public static <T> List<T> parseExcel(InputStream inputStream, Class<T> clazz, String[] typeName, int firstRow, int lastRow) throws Exception {
//
//
////把每一行数据封装成Class对应的对象
////最终转化为List
//        List<T> list = new LinkedList<>();
//
//        // Workbook wb = new XSSFWorkbook(inputStream);
//        Workbook wb = WorkbookFactory.create(inputStream);
//
//        System.out.println(wb.getClass().getName());
//        Sheet sheet = wb.getSheetAt(0);
//        //  System.out.println("一共有行数:" + sheet.getLastRowNum());
//        for (int i = firstRow; i <= lastRow; i++) {//遍历每行
////每遍历一行最终会得到一个具体的Class对象
//            T t = clazz.newInstance();
//
//            Row row = sheet.getRow(i);
////先得到一个String[]
//            String[] columnValues = new String[typeName.length];
//            for (int j = 0; j < typeName.length; j++) {//遍历每列
////遍历每列可以得到String
//                String strValue = null;
//                Cell cell = row.getCell(j);
//
//                if (cell != null) {//设置为文本，否则为数字时会cell.getStringCellValue()报空指针异常
//                    cell.setCellType(Cell.CELL_TYPE_STRING);
//                    strValue = cell.getStringCellValue().trim();
//                    columnValues[j] = strValue;
//                } else {//cell=null
//                    columnValues[j] = null;
//                }
//            }//每列遍历完成
//            // Object[]:对应的值 例如{"张三",18}
//            String[] columnValues1 = columnValues;
//
//            Object[] objects = stringTransfromType(typeName, columnValues, clazz);
//            t = rowTransobject(typeName, objects, clazz);
//            list.add(t);
//        }//每行遍历完成
//
//        inputStream.close();
//        return list;
//    }
//
//
//    /**
//     * @param inputStream
//     * @param clazz       （需要转换成的对象类型）
//     * @param firstRow    （从第几行开始导入，从0开始）
//     * @return
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     * @throws InstantiationException
//     */
//    public static <T> List<T> parseExcel(InputStream inputStream, Class<T> clazz, String[] typeName, int firstRow) throws Exception {
//
//
//        Workbook wb = WorkbookFactory.create(inputStream);
//        Sheet sheet = wb.getSheetAt(0);
//        int lastRow = sheet.getLastRowNum();
//
//
//        return parseExcel(inputStream, clazz, typeName, firstRow, lastRow);
//
//    }
//
//
//    /**
//     * 将字段的String值转化为实际的类型
//     * 支持类型：String,Integer,Long,Byte,Short,Float,Character,Boolean,Bigdecimal,Date
//     *
//     * @param typeName     字段名称
//     * @param columnValues 字段值（String）
//     * @param clazz        字段所属的类
//     * @return
//     * @throws Exception
//     */
//
//    public static Object[] stringTransfromType(String[] typeName, String[] columnValues, Class clazz) throws Exception {        //返回object[] objects;
//        Object[] objects = new Object[columnValues.length];
//
//        for (int i = 0; i < typeName.length; i++) {
//
//            Field field = clazz.getDeclaredField(typeName[i]);
//
//            if (columnValues[i] != null && !"".equals(columnValues[i])) {
//                //这里巨坑  如果写columnValues[i]!="",是不行的，因为2个""空字符串可能地址不一样，因为前面用的new String[];
//
//                String value = columnValues[i];
//
//                //    Class<?> keyClass = field.getType();//###########这句话不对，永远的得到的同一个class
//
//                String type = field.getGenericType().toString();
//                //    System.out.println("@@@@TYPE^^^^" + type);
//                if (type.endsWith("String")) {
//                    objects[i] = value;
//                } else if (type.endsWith("Integer")) {
//                    objects[i] = Integer.parseInt(value);
//                } else if (type.endsWith("Long")) {
//                    objects[i] = Long.parseLong(value);
//                } else if (type.endsWith("Float")) {
//                    objects[i] = Float.parseFloat(value);
//                } else if (type.endsWith("Byte")) {
//                    objects[i] = Byte.valueOf(value);
//                } else if (type.endsWith("Short")) {
//                    objects[i] = Short.valueOf(value);
//                } else if (type.endsWith("Character")) {
//                    objects[i] = (value.toCharArray())[0];
//                } else if (type.endsWith("Boolean")) {
//                    objects[i] = Boolean.valueOf(value);
//                } else if (type.endsWith("BigDecimal")) {
//                    objects[i] = new BigDecimal(value);
//                } else if (type.endsWith("Date")) {
//                    //获取日期格式模板
//                    String pattern = DateUntils.getPattern(value);
//                    //  System.out.println("得到的日期格式"+pattern);
//                    //根据模板格式 把String解析成Date
//                    Date date = new SimpleDateFormat(pattern).parse(value);
//                    objects[i] = date;
//                } else {
//                    throw new Exception("第" + i + "列出现未知的数据类型");
//                }
//
//            } else {
//                objects[i] = null;
//            }
//        }
//
//        return objects;
//    }
//
//    /**
//     * @param typeName 所在类的字段名称
//     * @param objects  对应字段的值
//     * @param clazz    将转换成的class
//     * @param <T>
//     * @return
//     * @throws Exception
//     */
//    public static <T> T rowTransobject(String[] typeName, Object[] objects, Class<T> clazz) throws Exception {
//        T t = clazz.newInstance();
//        for (int j = 0; j < typeName.length; j++) {
//            Field field = clazz.getDeclaredField(typeName[j]);//获取对应字段
//            // 取消语言访问检查
//            field.setAccessible(true);
//            field.set(t, objects[j]);
//        }
//        return t;
//    }

}