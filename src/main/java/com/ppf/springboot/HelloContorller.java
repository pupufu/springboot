package com.ppf.springboot;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("ppf")
public class HelloContorller {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;

    @RequestMapping("/hello")
    //@ResponseBody
    // 你好啊.................1234567890000000....
    public String getIndex(Model m, HttpServletRequest request) {
       m.addAttribute("now", DateFormat.getTimeInstance().format(new Date()));
        m.addAttribute("name", name);
        return "hello";
    }
}
