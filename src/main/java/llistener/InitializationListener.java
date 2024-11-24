package llistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.UserService;

import java.io.File;

@WebListener
public class InitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ObjectMapper objectMapper =new ObjectMapper();
        File file = new File("D:\\rab_stol\\IdeaProjects\\project_module3\\src\\main\\resources\\users.json");

        UserDAO userDAO = new UserDAO(objectMapper, file);
        UserService userService = new UserService(userDAO);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
    }
}
