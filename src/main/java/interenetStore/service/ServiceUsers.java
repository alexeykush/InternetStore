package interenetStore.service;

import interenetStore.dao.DAO;
import interenetStore.dto.User;

public class ServiceUsers {
    private DAO<User> userDAO;

    public ServiceUsers(DAO<User> dao) {
        this.userDAO = dao;
    }

    public boolean userExists(User user){
        return userDAO.get(user.getId()) != null;
    }

    public boolean checkPassword(User user){
        return userDAO.get(user.getId()).getPassword().equals(user.getPassword());
    }

    public void add(User item) {
        userDAO.add(item);
    }

    public User get(int id) {
        return userDAO.get(id);
    }

}
