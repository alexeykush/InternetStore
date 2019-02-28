package internetStore.dao;

import internetStore.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class UsersDaoSql implements DAO<User> {

    private final Connection conn;

    public UsersDaoSql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(User item) {
        String sql = "INSERT INTO alexeyku_users (user_id,name,surname,login,password) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,item.getId());
            stm.setString(2,item.getName());
            stm.setString(3,item.getSurname());
            stm.setString(4,item.getLogin());
            stm.setString(5,item.getPassword());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(User item) {
        throw new IllegalStateException("not implemented by design");
    }

    @Override
    public void remove(int id) {
        throw new IllegalStateException("not implemented by design");
    }

    @Override
    public User get(int id) {
        String sql = String.format("SELECT * FROM alexeyku_users WHERE user_id = %d;",id);
        User user = null;
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rSet = stm.executeQuery();

            if(rSet.next()){
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                String login = rSet.getString("login");
                String password = rSet.getString("password");
                user = new User(name,surname,login,password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return user;
        }

    }

    @Override
    public Collection<User> getAll() {
        throw new IllegalStateException("not implemented by design");
    }
}
