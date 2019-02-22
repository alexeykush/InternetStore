package interenetStore.service;

import interenetStore.dao.DAO;
import interenetStore.dto.Cart;

import java.util.Collection;
import java.util.stream.Collectors;

public class ServiceCarts {
    private DAO<Cart> cartDAO;

    public ServiceCarts(DAO<Cart> cartDAO) {
        this.cartDAO = cartDAO;
    }

    public void add(Cart item){
        cartDAO.add(item);
    }

    public void remove(Cart item) {
        cartDAO.remove(item);
    }

    public void delete(Cart item){
        item.setQuantity(-1);
        cartDAO.remove(item);
    }

    public Collection<Cart> getAll(int id){
        return cartDAO.getAll()
                .stream()
                .filter(cart -> cart.getUserId() == id)
                .collect(Collectors.toList());
    }


}
