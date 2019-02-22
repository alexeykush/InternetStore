package interenetStore.dao;

import interenetStore.dto.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CartsDaoSql implements DAO<Cart> {
    private final Connection conn;

    public CartsDaoSql(Connection conn) {
        this.conn = conn;
    }

    private int checkQuantity(int userId, int commodityId){
        int quantity = 0;
        String sql = String.format("SELECT * FROM alexeyku_carts WHERE user_id = %s AND commodity_id = %s",userId,commodityId);
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();
            if(rSet.next()){
                quantity = rSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public void delete(Cart item){
        String sql = String.format("DELETE FROM alexeyku_carts WHERE user_id = %s AND commodity_id = %s",item.getUserId(),item.getCommodityId());
        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void add(Cart item) {
        try {
            String sql;
            PreparedStatement stm = null;

            if(checkQuantity(item.getUserId(),item.getCommodityId()) == 0){
                sql = "INSERT INTO alexeyku_carts VALUES(?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1,item.getUserId());
                stm.setInt(2,item.getCommodityId());
                stm.setInt(3,1);
            }
            else{
                sql = String.format("UPDATE alexeyku_carts SET quantity = quantity + 1 WHERE user_id = %s AND commodity_id = %s",item.getUserId(),item.getCommodityId());
                stm = conn.prepareStatement(sql);
            }

            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Cart item) {
        try {
            String sql;
            if(item.getQuantity() == -1 || checkQuantity(item.getUserId(),item.getCommodityId()) == 1){
                sql = String.format("DELETE FROM alexeyku_carts WHERE user_id = %s AND commodity_id = %s",item.getUserId(),item.getCommodityId());
            }
            else{
                sql = String.format("UPDATE alexeyku_carts SET quantity = quantity - 1 WHERE user_id = %s AND commodity_id = %s",item.getUserId(),item.getCommodityId());
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Collection<Cart> getAll() {
        Collection<Cart> carts = new ArrayList<>();
        String sql = "SELECT alexeyku_carts.user_id,\n" +
                "       alexeyku_carts.commodity_id,\n" +
                "       alexeyku_carts.quantity,\n" +
                "       alexeyku_commodities.name,\n" +
                "       alexeyku_commodities.price\n" +
                "FROM alexeyku_carts\n" +
                "       JOIN alexeyku_commodities ON alexeyku_carts.commodity_id = alexeyku_commodities.commodity_id";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();

            while (rSet.next()) {
                int userId = rSet.getInt("user_id");
                int commodityId = rSet.getInt("commodity_id");
                int quantity = rSet.getInt("quantity");
                String name = rSet.getString("name");
                int price = rSet.getInt("price");
                Cart cart = new Cart(userId,commodityId,quantity, name, price);
                carts.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return carts;
        }
    }

    @Override
    public void remove(int id) {
        throw new IllegalStateException("not implemented by design");
    }

    @Override
    public Cart get(int id) {
        throw new IllegalStateException("not implemented by design");
    }
}
