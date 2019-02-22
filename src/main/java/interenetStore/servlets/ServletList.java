package interenetStore.servlets;

import interenetStore.dto.Commodity;
import interenetStore.service.ServiceCommodities;
import interenetStore.service.ServiceCookie;
import interenetStore.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class ServletList extends HttpServlet {
    private ServiceCommodities serviceCommodities;
    private final Freemarker f = new Freemarker();
    private ServiceCookie serviceCookie;
    public ServletList(ServiceCommodities serviceCommodities) {
        this.serviceCommodities = serviceCommodities;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serviceCookie = new ServiceCookie(req,resp);
        Collection<Commodity> commodities = serviceCommodities.getAll();

        boolean authorized = serviceCookie.getCookie() != null;

        HashMap<String, Object> data = new HashMap<>();
        data.put("commodities",commodities);
        data.put("authorized", authorized);

        f.render("commodities.ftl",data,resp);
    }
}
