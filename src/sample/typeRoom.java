package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class typeRoom {
    private int type;
    private double price;
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final IntegerProperty number = new SimpleIntegerProperty();

    public final int getQuantity() {
        return quantity.get();
    }

    public final void setQuantity(int value) {
        quantity.set(value);
    }

    public final IntegerProperty quantityProperty() {
        return quantity;
    }

    public final int getNuumber() {
        return number.get();
    }

    public final void setNumber(int value) {
        number.set(value);
    }

    public final IntegerProperty numberProperty() {
        return number;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }
}
