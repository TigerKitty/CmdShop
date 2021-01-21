import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

public class Order {
    private User user;
    private Product product;
    private int ammount;
    private float totalCost;
    private float receipts;
    private String orderDate;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getReceipts() {
        return receipts;
    }

    public void setReceipts(float receipts) {
        this.receipts = receipts;
    }
}
