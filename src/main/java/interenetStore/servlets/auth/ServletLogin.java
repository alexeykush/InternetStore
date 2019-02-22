package interenetStore.servlets.auth;

import interenetStore.dto.User;
import interenetStore.service.ServiceCookie;
import interenetStore.service.ServiceUsers;
import interenetStore.utils.Freemarker;
import interenetStore.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServletLogin extends HttpServlet {
    private ServiceCookie serviceCookie;

    private final Freemarker f = new Freemarker();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();

        List<String> fields = new ArrayList<>();
        fields.add("login");
        fields.add("password");

        data.put("fields", fields);
        data.put("rout", "/login");

        f.render("form.ftl", data, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);

        String login = pfr.getStr("login");
        String password = pfr.getStr("password");
        User user = new User(login, password);

        serviceCookie = new ServiceCookie(req, resp);
        serviceCookie.addCookie(user.getId());

        resp.sendRedirect("/");
    }
}
