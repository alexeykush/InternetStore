package interenetStore.dao;

import interenetStore.dto.Commodity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CommoditiesDaoSql implements DAO<Commodity> {
    private final Connection conn;

    public CommoditiesDaoSql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Commodity item) {
        throw new IllegalStateException("not implemented by design");
    }

    @Override
    public void remove(Commodity item) {
        throw new IllegalStateException("not implemented by design");
    }

    @Override
    public void remove(int id) {
        throw new IllegalStateException("not implemented by design");
    }

    @Override
    public Commodity get(int id) {
        throw new IllegalStateException("not implemented by design");
    }

    @Override
    public Collection<Commodity> getAll() {
        Collection<Commodity> commodities = new ArrayList<>();
        String sql = "SELECT * FROM alexeyku_commodities";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();

            while (rSet.next()) {
                int id = rSet.getInt("commodity_id");
                String name = rSet.getString("name");
                int price = rSet.getInt("price");
                Commodity commodity = new Commodity(id, name, price);
                commodities.add(commodity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return commodities;
        }
    }
}
