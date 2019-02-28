package internetStore;

import internetStore.dao.CartsDaoSql;
import internetStore.dao.CommoditiesDaoSql;
import internetStore.dao.DAO;
import internetStore.dao.UsersDaoSql;
import internetStore.db.DbConnection;
import internetStore.dto.Cart;
import internetStore.dto.Commodity;
import internetStore.dto.User;
import internetStore.filters.LoginFilter;
import internetStore.filters.RegistrationFilter;
import internetStore.service.ServiceCarts;
import internetStore.service.ServiceCommodities;
import internetStore.service.ServiceUsers;
import internetStore.servlets.*;
import internetStore.servlets.auth.ServletLogin;
import internetStore.servlets.auth.ServletRegistration;
import internetStore.servlets.handlers.ServletCartHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {

    public static void main(String[] args) throws Exception {

        String webPort = System.getenv("PORT");

        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }


        DAO<User> userDAO = new UsersDaoSql(new DbConnection().connection());
        ServiceUsers userService = new ServiceUsers(userDAO);

        DAO<Commodity> commodityDAO = new CommoditiesDaoSql(new DbConnection().connection());
        ServiceCommodities commodityService = new ServiceCommodities(commodityDAO);

        DAO<Cart> cartDAO = new CartsDaoSql(new DbConnection().connection());
        ServiceCarts serviceCarts = new ServiceCarts(cartDAO);

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new ServletMain()),"/*");
        handler.addServlet(new ServletHolder(new ServletRegistration(userService)),"/reg/*");
        handler.addServlet(new ServletHolder(new ServletLogin()),"/login/*");
        handler.addServlet(new ServletHolder(new ServletList(commodityService)),"/list/*");
        handler.addServlet(new ServletHolder(new ServletCart(serviceCarts)),"/cart/*");
        handler.addServlet(new ServletHolder(new ServletCartHandler(serviceCarts)),"/handle/*");

        handler.addFilter(new FilterHolder(new RegistrationFilter(userService)),"/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new LoginFilter(userService)),"/login/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        Server server = new Server(Integer.parseInt(webPort));

        server.setHandler(handler);

        server.start();
        server.join();

    }

}