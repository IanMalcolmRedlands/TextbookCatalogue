package textbookcatalogue;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Textbook {
    private SimpleIntegerProperty sku;
    private SimpleStringProperty title;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty quantity;

    public Textbook(String sku, String title, String price, String quantity) throws IllegalArgumentException {
        int skuInt, quantInt;
        double priceDub;

        if (sku.length()*title.length()*price.length()*quantity.length()==0) {
            throw new IllegalArgumentException("All fields must be filled");
        }

        try { skuInt = Integer.parseInt(sku); }
        catch (IllegalArgumentException error) { throw new IllegalArgumentException("SKU value is not a valid number"); }
        if (skuInt < 0) { throw new IllegalArgumentException("SKU must be positive"); }

        try { quantInt = Integer.parseInt(quantity); }
        catch (IllegalArgumentException error) { throw new IllegalArgumentException("Quantity value is not a valid number"); }
        
        try { priceDub = Double.parseDouble(price); }
        catch (IllegalArgumentException error) { throw new IllegalArgumentException("Price value is not a valid double"); }

        this.sku = new SimpleIntegerProperty(this, "SKU", skuInt);
        this.title = new SimpleStringProperty(this, "Title", title);
        this.price = new SimpleDoubleProperty(this, "Price", priceDub);
        this.quantity = new SimpleIntegerProperty(this, "Quantity", quantInt);
    }

    public Textbook(int sku, String title, double price, int quantity) {
        this.sku = new SimpleIntegerProperty(this, "SKU", sku);
        this.title = new SimpleStringProperty(this, "Title", title);
        this.price = new SimpleDoubleProperty(this, "Price", price);
        this.quantity = new SimpleIntegerProperty(this, "Quantity", quantity);
    }

    @Override
    public String toString() {
        return String.format("SKU: %d; TITLE '%s'; PRICE: $%.2f; QUANTITY: %d", getSku(), getTitle(), getPrice(), getQuantity());
    }

    public int getSku() { return sku.get(); }
    public void setSku(int sku) { this.sku.set(sku); }
    public SimpleIntegerProperty skuProperty() { return sku; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public SimpleIntegerProperty quantityProperty() { return quantity; }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public SimpleStringProperty titleProperty() { return title; }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public SimpleDoubleProperty priceProperty() { return price; }
}
