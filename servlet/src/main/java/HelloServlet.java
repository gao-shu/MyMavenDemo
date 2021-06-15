import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletInputStream inputStream = req.getInputStream();
//        ServletOutputStream outputStream = resp.getOutputStream();
        PrintWriter writer = resp.getWriter();
        writer.println("hello , serlvet");


        //servletContext  ____会被替换
        //1.共享数据  ————session或者requecst
        ServletContext servletContext = this.getServletContext();
        //2. 获取初始化参数
        servletContext.setAttribute("userName", 123);
        //3.请求转发
        servletContext.getRequestDispatcher("/s").forward(req, resp);
        //4.读取配置文件
        InputStream stream = servletContext.getResourceAsStream("/WEB-INF/classes/db.propreties");
        Properties properties = new Properties();
        properties.load(stream);
        properties.get("username");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
