package StudyTest;

import com.mysql.jdbc.Driver;

import javax.servlet.Servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Test {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        //String url ="jdbc:mysql://192.168.0.101:3306/springboot?useSSL=true&serverTimezone=UTC";
       String url = "jdbc:mysql://localhost";

        Connection connection = DriverManager.getConnection(url, "root", "root");
        if (connection==null){
            System.out.println("连接失败");
        }else {
            System.out.println("连接成功");
        }


    }
}
