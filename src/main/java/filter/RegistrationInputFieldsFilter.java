package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import service.validation.LoginValidationExecutor;
import service.validation.Validator;
import servlet.auth.helper.CredentialsExtractor;
import servlet.auth.helper.dto.Credential;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebFilter("/registration")
public class RegistrationInputFieldsFilter implements Filter {

    private LoginValidationExecutor loginValidationExecutor;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginValidationExecutor =
                (LoginValidationExecutor) filterConfig.getServletContext().getAttribute("loginValidationExecutor");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getMethod().equals("POST")) {
            Credential credential = CredentialsExtractor.extract(req);
            Map<String, List<String>> stringListMap = loginValidationExecutor.executeValidation(credential);

            if (stringListMap.values().isEmpty()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                for(var validationErrorMessages:stringListMap.entrySet()) {
                   if (!validationErrorMessages.getValue().isEmpty()) {
                       req.setAttribute(validationErrorMessages.getKey()+"ValidationErrorMessages",
                               validationErrorMessages.getValue()
                       );
                   }
                }

                req.getRequestDispatcher("/registration.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
