package interenetStore.filters;

import interenetStore.dto.User;
import interenetStore.exceptions.LoginFailureException;
import interenetStore.exceptions.NoParametersException;
import interenetStore.service.ServiceUsers;
import interenetStore.utils.Freemarker;
import interenetStore.utils.ParameterFromRequest;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginFilter implements Filter {
    private ServiceUsers serviceUsers;
    private final Freemarker f = new Freemarker();

    public LoginFilter(ServiceUsers serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }
        HashMap<String, Object> data = new HashMap<>();


        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            try {
                ParameterFromRequest pfr = new ParameterFromRequest(req);

                String login = pfr.getStr("login");
                String password = pfr.getStr("password");
                User user = new User(login, password);

                if (!serviceUsers.userExists(user) || !serviceUsers.checkPassword(user)) {
                    throw new LoginFailureException("Incorrect login or password");
                }

                chain.doFilter(request, response);
            } catch (NoParametersException e) {
                data.put("message", e.getMessage());
                data.put("rout","/login");

                f.render("fail.ftl", data,(HttpServletResponse) response);
            } catch (LoginFailureException e){
                data.put("message", e.getMessage());
                data.put("rout","/login");
                f.render("fail.ftl", data,(HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
