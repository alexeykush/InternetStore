package interenetStore.servlets.handlers;

import interenetStore.dto.Cart;
import interenetStore.service.ServiceCarts;
import interenetStore.service.ServiceCookie;
import interenetStore.utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletCartHandler extends HttpServlet {

    private ServiceCarts serviceCarts;
    private ServiceCookie serviceCookie;

    public ServletCartHandler(ServiceCarts serviceCarts) {
        this.serviceCarts = serviceCarts;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        serviceCookie = new ServiceCookie(req,resp);

        int userId = Integer.parseInt(serviceCookie.getCookie().getValue());
        int commodityId = pfr.getInt("commodityId");
        Cart cart = new Cart(userId, commodityId);

        if (req.getRequestURI().equals("/handle/add")) {
            serviceCarts.add(cart);
        } else if (req.getRequestURI().equals("/handle/remove")) {
            serviceCarts.remove(cart);
        } else if (req.getRequestURI().equals("/handle/delete")){
            serviceCarts.delete(cart);
        }
        
        String redirectTo = req.getHeader("Referer");
        resp.sendRedirect(redirectTo);

    }
}
