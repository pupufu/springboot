package com.ppf.springboot.controller;

import com.ppf.springboot.entity.User;
import com.ppf.springboot.service.UserService;
import com.ppf.springboot.untis.ioUntils.IOUntil;
import com.ppf.springboot.untis.poiUntils.POIUntil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class POIController {

    @Autowired
    private UserService userService;


    @RequestMapping("")
    public String excel() {
        return "export";
    }

    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        String id = request.getSession().getId();
//        System.out.println(id);

        Cookie[] cookies = request.getCookies();
        System.out.println("cookie数目："+cookies.length);
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName()+"-----"+cookie.getValue());
        }


//        List<User> userList = userService.getUserList();
//
//        ServletOutputStream os = response.getOutputStream();
//
//        String titleName = "测试导出";
//        String [] rowName = {"姓名","年龄","成绩","生日"};
//        String [] fieldName = {"name","age","grade","birthday"};
//
//        String fileName="我是哈哈.xlsx";
//        response.setHeader( "Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) );
//        Workbook workbook = POIUntils.exportExcel(userList,titleName,rowName,fieldName);
//        workbook.write(os);
//        os.flush();
//        os.close();


        List<User> userList = userService.getUserList();

        ServletOutputStream os = response.getOutputStream();


        String[] fieldName = {"name", "age", "grade", "birthday"};

        String fileName = "我是哈哈.xlsx";
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));


        //ooooo
      //  String separator = File.separator;
   //     String filePath = "F:\\java\\myproject\\springboot\\src\\main\\webapp\\file\\导出模板.xlsx";
    //    String filePath1 = "F:separatorjava\\myproject\\springboot\\src\\main\\webapp\\file\\导出模板.xlsx";
        //     /src/main/webapp/file/导出模板.xlsx
        //String webPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        String webPath = request.getSession().getServletContext().getRealPath("");
        String filePath3 = webPath + "file/导出模板.xlsx";
        filePath3 = IOUntil.getPath(filePath3);
        System.out.println("!!!!!!!!!!   " + webPath);
        String datePattern = "YYYY-MM-dd HH:mm:ss";
        Workbook workbook = POIUntil.exportExcel(userList, filePath3, fieldName, datePattern);
        workbook.write(os);
        os.flush();
        os.close();


    }
//ppp123456

}
