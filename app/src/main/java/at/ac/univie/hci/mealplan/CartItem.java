package at.ac.univie.hci.mealplan;

public class CartItem {
    private String cartItemMeasurement;
    private String cartItemName;
    private boolean isChecked;

    public CartItem(String cartItemMeasurement, String cartItemName) {
        this.cartItemMeasurement = cartItemMeasurement;
        this.cartItemName = cartItemName;
        this.isChecked = false;
    }

    public String getCartItemMeasurement() {
        return cartItemMeasurement;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
