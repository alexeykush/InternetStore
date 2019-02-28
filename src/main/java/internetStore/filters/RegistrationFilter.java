package internetStore.filters;

import internetStore.dto.User;
import internetStore.exceptions.NoParametersException;
import internetStore.exceptions.UserExistsException;
import internetStore.service.ServiceUsers;
import internetStore.utils.Freemarker;
import internetStore.utils.ParameterFromRequest;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegistrationFilter implements Filter {
    private ServiceUsers serviceUsers;
    private final Freemarker f = new Freemarker();

    public RegistrationFilter(ServiceUsers serviceUsers) {
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
                String name = pfr.getStr("name");
                String surname = pfr.getStr("surname");
                String login = pfr.getStr("login");
                String password = pfr.getStr("password");
                User user = new User(name, surname, login, password);

                if (serviceUsers.userExists(user)) {
                    throw new UserExistsException("Such user exists");
                }

                chain.doFilter(request, response);
            } catch (UserExistsException e) {

                    data.put("message", e.getMessage());
                    data.put("rout", "/reg");
                    f.render("fail.ftl", data, (HttpServletResponse) response);
            } catch (NoParametersException e) {
                    data.put("message", e.getMessage());
                    data.put("rout", "/reg");
                    f.render("fail.ftl", data, (HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
