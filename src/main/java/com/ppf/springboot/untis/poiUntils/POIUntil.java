package com.ppf.springboot.untis.poiUntils;

import com.ppf.springboot.untis.dateUntils.DatePattern;
import com.ppf.springboot.untis.dateUntils.DateUntils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.swing.plaf.synth.DefaultSynthStyle;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class POIUntil {

    /**
     * @param inputStream 文件输出流
     * @param clazz       （需要转换成的对象类型）
     * @param firstRow    （从第几行开始导入，从0开始）
     * @param lastRow     （最后一行导入的编号）
     * @return List<T>
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> parseExcel(InputStream inputStream, Class<T> clazz, String[] typeName, int firstRow, int lastRow) throws Exception {


//把每一行数据封装成Class对应的对象
//最终转化为List


        // Workbook wb = new XSSFWorkbook(inputStream);
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        List<T> list = parseSheet(sheet, clazz, typeName, firstRow, lastRow);
        inputStream.close();
        return list;
    }


    /**
     * @param inputStream s
     * @param clazz       （需要转换成的对象类型）
     * @param firstRow    （从第几行开始导入，从0开始）
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> parseExcel(InputStream inputStream, Class<T> clazz, String[] typeName, int firstRow) throws Exception {

        List<T> list = new LinkedList<>();
        Workbook wb = WorkbookFactory.create(inputStream);
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            int lastRow = sheet.getLastRowNum();
            List<T> sheetList = parseSheet(sheet, clazz, typeName, firstRow, lastRow);
            list.addAll(sheetList);
        }
        inputStream.close();
        return list;


    }

    public static <T> List<T> parseSheet(Sheet sheet, Class<T> clazz, String[] typeName, int firstRow, int lastRow) throws Exception {
        List<T> list = new LinkedList<>();
        for (int i = firstRow; i <= lastRow; i++) {//遍历每行
            //每遍历一行最终会得到一个具体的Class对象
            T t = clazz.newInstance();

            Row row = sheet.getRow(i);
            if (row == null) {//某一行为空，就直接跳过
                continue;
            }

            //先得到一个String[]
            String[] columnValues = new String[typeName.length];
            for (int j = 0; j < typeName.length; j++) {//遍历每列
                //遍历每列可以得到String
                String strValue = null;
                Cell cell = row.getCell(j);

                if (cell != null) {//设置为文本，否则为数字时会cell.getStringCellValue()报空指针异常
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    strValue = cell.getStringCellValue().trim();
                    columnValues[j] = strValue;
                } else {//cell=null
                    columnValues[j] = null;
                }
            }//每列遍历完成
            // Object[]:对应的值 例如{"张三",18}
            String[] columnValues1 = columnValues;

            Object[] objects = stringTransfromType(typeName, columnValues, clazz);
            t = rowTransobject(typeName, objects, clazz);
            list.add(t);
        }//每行遍历完成
        return list;
    }


    /**
     * 将字段的String值转化为实际的类型
     * 支持类型：String,Integer,Long,Byte,Short,Float,Character,Boolean,Bigdecimal,Date
     *
     * @param typeName     字段名称
     * @param columnValues 字段值（String）
     * @param clazz        字段所属的类
     * @return
     * @throws Exception
     */

    public static Object[] stringTransfromType(String[] typeName, String[] columnValues, Class clazz) throws Exception {        //返回object[] objects;
        Object[] objects = new Object[columnValues.length];

        for (int i = 0; i < typeName.length; i++) {

            Field field = clazz.getDeclaredField(typeName[i]);

            if (columnValues[i] != null && !"".equals(columnValues[i])) {
                //这里巨坑  如果写columnValues[i]!="",是不行的，因为2个""空字符串可能地址不一样，因为前面用的new String[];

                String value = columnValues[i];
                //    Class<?> keyClass = field.getType();//###########这句话不对，永远的得到的同一个class

                String type = field.getGenericType().toString();
                //    System.out.println("@@@@TYPE^^^^" + type);
                if (type.endsWith("String")) {
                    objects[i] = value;
                } else if (type.endsWith("Integer")) {
                    objects[i] = Integer.parseInt(value);
                } else if (type.endsWith("Long")) {
                    objects[i] = Long.parseLong(value);
                } else if (type.endsWith("Float")) {
                    objects[i] = Float.parseFloat(value);
                } else if (type.endsWith("Byte")) {
                    objects[i] = Byte.valueOf(value);
                } else if (type.endsWith("Short")) {
                    objects[i] = Short.valueOf(value);
                } else if (type.endsWith("Character")) {
                    objects[i] = (value.toCharArray())[0];
                } else if (type.endsWith("Boolean")) {
                    objects[i] = Boolean.valueOf(value);
                } else if (type.endsWith("BigDecimal")) {
                    objects[i] = new BigDecimal(value);
                } else if (type.endsWith("Date")) {
                    //获取日期格式模板
                    String pattern = DateUntils.getPattern(value);
                    if (pattern == null) {
                        throw new Exception("日期格式错误");
                    }
                    //  System.out.println("得到的日期格式"+pattern);
                    //根据模板格式 把String解析成Date
                    Date date = new SimpleDateFormat(pattern).parse(value);
                    objects[i] = date;
                } else {
                    throw new Exception("第" + i + "列出现未知的数据类型");
                }

            } else {
                objects[i] = null;
            }
        }

        return objects;
    }

    /**
     * @param typeName 所在类的字段名称
     * @param objects  对应字段的值
     * @param clazz    将转换成的class
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T rowTransobject(String[] typeName, Object[] objects, Class<T> clazz) throws Exception {
        T t = clazz.newInstance();
        for (int j = 0; j < typeName.length; j++) {
            Field field = clazz.getDeclaredField(typeName[j]);//获取对应字段
            // 取消语言访问检查
            field.setAccessible(true);
            field.set(t, objects[j]);
        }
        return t;
    }


    /**
     * 默认日期格式：YYYY-MM-dd
     *
     * @param list       导出数据
     * @param titleName  表头标题
     * @param columnName 每列名称
     * @param fieldsName 每列对应类属性名
     * @param <T>        导出类泛型
     * @return
     * @throws Exception
     */
    public static <T> Workbook exportExcel(List<T> list, String titleName, String[] columnName, String[] fieldsName) throws Exception {

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        //设置标题
        setExcelTitle(sheet, titleName, (short) 800, columnName);
        //设置列名
        setRowName(sheet, columnName);
        //设置内容
        setContent(sheet, list, columnName, fieldsName);
        //写入数据

        return wb;
    }

    /**
     * 导出并指定导出日期格式
     *
     * @param list        导出数据
     * @param titleName   表头标题
     * @param columnName  每列名称
     * @param fieldsName  每列对应类属性名
     * @param datePattern 日期格式
     * @param <T>         导出类泛型
     * @return
     * @throws Exception
     */
    public static <T> Workbook exportExcel(List<T> list, String titleName, String[] columnName, String[] fieldsName, String datePattern) throws Exception {

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        //设置标题
        setExcelTitle(sheet, titleName, (short) 800, columnName);
        //设置列名
        setRowName(sheet, columnName);
        //设置内容
        setContent(sheet, list, columnName, fieldsName, datePattern);
        //写入数据

        return wb;
    }

    /**
     * 根据模板格式导出
     * 模板第1行第1列为标题，模板第2行为字段中文名称，第3行以后为内容
     *
     * @param list        导出数据
     * @param filePath    模板的绝对地址
     * @param fieldsName  每列对应的java字段
     * @param datePattern 导入的日期格式
     * @param <T>         导出类泛型
     * @return
     * @throws Exception
     */
    public static <T> Workbook exportExcel(List<T> list, String filePath, String[] fieldsName, String datePattern) throws Exception {

        Workbook wb = WorkbookFactory.create(new File(filePath));
        Sheet sheet = wb.getSheetAt(0);

        //得到列名
        Row row = sheet.getRow(1);
        int num = fieldsName.length;
        String[] columnName = new String[num];
        for (int i = 0; i < num; i++) {
            columnName[i] = row.getCell(i).getStringCellValue();
        }

        //设置内容
        setContent(sheet, list, columnName, fieldsName, datePattern);
        return wb;
    }


    public static <T> void setContent(Sheet sheet, List<T> list, String[] columnName, String[] fieldsName) throws Exception {

        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            Row row = sheet.createRow(i + 2);
            String[] values = transObjectToString(t, fieldsName);

            for (int j = 0; j < columnName.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(values[j]);
            }
        }
    }

    public static <T> void setContent(Sheet sheet, List<T> list, String[] columnName, String[] fieldsName, String datePattern) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            Row row = sheet.createRow(i + 2);
            String[] values = transObjectToString(t, fieldsName, datePattern);

            for (int j = 0; j < columnName.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(values[j]);
            }
        }


    }


    /***
     *
     * @param t 一个对象
     * @param fieldsName 字段名称
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String[] transObjectToString(T t, String[] fieldsName) throws Exception {
        String[] values = new String[fieldsName.length];
        Class<?> clazz = t.getClass();
        for (int i = 0; i < fieldsName.length; i++) {

            Field field = clazz.getDeclaredField(fieldsName[i]);
            field.setAccessible(true);
            Object obj = field.get(t);
            if (obj == null) {
                obj = "";
            }
            if (obj instanceof Date) {
                obj = new SimpleDateFormat(DatePattern.date24).format(obj);
            }

            values[i] = obj.toString();


        }
        return values;
    }

    public static <T> String[] transObjectToString(T t, String[] fieldsName, String datePattern) throws Exception {
        String[] values = new String[fieldsName.length];
        Class<?> clazz = t.getClass();
        for (int i = 0; i < fieldsName.length; i++) {

            Field field = clazz.getDeclaredField(fieldsName[i]);
            field.setAccessible(true);
            Object obj = field.get(t);
            if (obj == null) {
                obj = "";
            }
            if (obj instanceof Date) {
                obj = new SimpleDateFormat(datePattern).format(obj);
            }

            values[i] = obj.toString();


        }
        return values;
    }


    public static void setExcelTitle(Sheet sheet, String titleName, short titleHight, String[] rowName) {

        Row row = sheet.createRow(0);
        row.setHeight(titleHight);
        // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)

        CellRangeAddress region = new CellRangeAddress(0, 0, 0, rowName.length - 1);
        sheet.addMergedRegion(region);

        Cell cell = row.createCell(0);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(titleName);
    }

    public static void setRowName(Sheet Sheet, String[] rowName) {
        Row row = Sheet.createRow(1);
        for (int i = 0; i < rowName.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(rowName[i]);
        }

    }
}