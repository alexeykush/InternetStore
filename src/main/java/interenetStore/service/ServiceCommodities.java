package interenetStore.service;

import interenetStore.dao.DAO;
import interenetStore.dto.Commodity;

import java.util.Collection;

public class ServiceCommodities {
    private DAO<Commodity> commodityDAO;

    public ServiceCommodities(DAO<Commodity> commodityDAO) {
        this.commodityDAO = commodityDAO;
    }

    public Collection<Commodity> getAll(){
        return commodityDAO.getAll();
    }
}
