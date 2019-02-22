package interenetStore.servlets;

import interenetStore.dto.Cart;
import interenetStore.service.ServiceCarts;
import interenetStore.service.ServiceCookie;
import interenetStore.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class ServletCart extends HttpServlet {
    private ServiceCookie serviceCookie;
    private final Freemarker f = new Freemarker();
    private ServiceCarts serviceCarts;

    public ServletCart(ServiceCarts serviceCarts) {
        this.serviceCarts = serviceCarts;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        serviceCookie = new ServiceCookie(req,resp);
        int activeUserId = Integer.parseInt(serviceCookie.getCookie().getValue());

        Collection<Cart> goods = serviceCarts.getAll(activeUserId);

        int total = goods
                .stream()
                .mapToInt(cart -> cart.getQuantity() * cart.getPrice())
                .sum();

        HashMap<String, Object> data = new HashMap<>();
        data.put("goods",goods);
        data.put("total",total);

        f.render("cart.ftl",data,resp);

    }
}
