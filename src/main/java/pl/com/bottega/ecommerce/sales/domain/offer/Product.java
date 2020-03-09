package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;
import java.util.Objects;

public class Product {

    private String ID;
    private Money price;
    private String type;
    private Date snapshotDate;

    public Product(String ID, Money price, String type, Date snapshotDate) {
        this.ID = ID;
        this.price = price;
        this.type = type;
        this.snapshotDate = snapshotDate;
    }

    public String getID() {
        return ID;
    }

    public Money getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return Objects.equals(ID, product.ID)
               && Objects.equals(price, product.price)
               && Objects.equals(type, product.type)
               && Objects.equals(snapshotDate, product.snapshotDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, price, type, snapshotDate);
    }
}
