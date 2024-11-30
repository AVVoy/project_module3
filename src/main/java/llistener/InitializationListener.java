package llistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductDAO;
import dao.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.ProductService;
import service.UserService;

import java.io.File;

@WebListener
public class InitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ObjectMapper objectMapper =new ObjectMapper();
        File userFile = new File("D:\\rab_stol\\IdeaProjects\\project_module3\\src\\main\\resources\\users.json");
        File productFile = new File("D:\\rab_stol\\IdeaProjects\\project_module3\\src\\main\\resources\\products.json");

        UserDAO userDAO = new UserDAO(objectMapper, userFile);
        UserService userService = new UserService(userDAO);

        ProductDAO productDao = new ProductDAO(objectMapper, productFile);
        ProductService productService = new ProductService(productDao);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);

        servletContext.setAttribute("productService", productService);

    }
}
