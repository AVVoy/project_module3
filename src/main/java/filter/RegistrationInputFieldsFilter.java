package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import service.validation.PasswordValidator;
import service.validation.LoginValidator;

import java.io.IOException;
import java.util.List;

@WebFilter("/registration")
public class RegistrationInputFieldsFilter implements Filter {

    private LoginValidator loginValidator;
    private PasswordValidator passwordValidator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginValidator = (LoginValidator) filterConfig.getServletContext().getAttribute("loginValidator");
        passwordValidator = (PasswordValidator) filterConfig.getServletContext().getAttribute("passwordValidator");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getMethod().equals("POST")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            List<String> loginValidationErrorMessages = loginValidator.validate(login);
            List<String> passwordValidationErrorMessages = passwordValidator.validate(password);

            if (loginValidationErrorMessages.isEmpty() && passwordValidationErrorMessages.isEmpty()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                if (!loginValidationErrorMessages.isEmpty()) {
                    req.setAttribute("loginValidationErrorMessages", loginValidationErrorMessages);
                }

                if (!passwordValidationErrorMessages.isEmpty()) {
                    req.setAttribute("passwordValidationErrorMessages", passwordValidationErrorMessages);
                }

                req.getRequestDispatcher("/registration.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
