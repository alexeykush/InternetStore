package interenetStore.servlets.auth;

import interenetStore.service.ServiceCookie;
import interenetStore.utils.Freemarker;
import interenetStore.utils.ParameterFromRequest;
import interenetStore.dto.User;
import interenetStore.service.ServiceUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServletRegistration extends HttpServlet {

    private ServiceUsers serviceUsers;
    private ServiceCookie serviceCookie;
    private final Freemarker f = new Freemarker();

    public ServletRegistration(ServiceUsers serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        List<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("surname");
        fields.add("login");
        fields.add("password");

        data.put("fields",fields);
        data.put("rout","/reg");

        f.render("form.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);

        String name = pfr.getStr("name");
        String surname = pfr.getStr("surname");
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");
        User user = new User(name, surname, login, password);
        serviceUsers.add(user);

        serviceCookie = new ServiceCookie(req,resp);
        serviceCookie.addCookie(user.getId());

        resp.sendRedirect("/");

    }
}
