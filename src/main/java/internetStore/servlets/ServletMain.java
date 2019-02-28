package internetStore.servlets;

import internetStore.service.ServiceCookie;
import internetStore.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ServletMain extends HttpServlet {
    private final Freemarker f = new Freemarker();
    private ServiceCookie serviceCookie;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serviceCookie = new ServiceCookie(req,resp);

        if(req.getParameter("logout") != null && req.getParameter("logout").equals("true")){
            serviceCookie.removeCookie();
            resp.sendRedirect("/");
        }

        boolean authorized = serviceCookie.getCookie() != null;

        HashMap<String, Object> data = new HashMap<>();
        data.put("authorized", authorized);

        f.render("main.ftl", data, resp);

    }
}
