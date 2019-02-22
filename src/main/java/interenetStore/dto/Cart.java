package interenetStore.dto;

public class Cart {
    private int userId;
    private int commodityId;
    private int quantity;
    private String name;
    private int price;

    public Cart(int userId, int commodityId) {
        this.userId = userId;
        this.commodityId = commodityId;
    }

    public Cart(int userId, int commodityId, int quantity, String name, int price) {
        this(userId, commodityId);
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }


    public int getUserId() {
        return userId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
