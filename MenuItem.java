/**
 * Abstract parent of every item sold at the campus cafe.
 * Each subclass supplies its own pricing rule through {@link #computePrice()}.
 *
 * @author Otod
 * @version 1.0
 */
public abstract class MenuItem {

    /** Display name of the item. */
    protected String name;

    /** Base price of the item in pesos, before any extras. */
    protected double basePrice;

    /** Unique menu code, e.g. "B-01". */
    private String itemCode;

    /**
     * Creates an unnamed item with a price of 0.0 and no code.
     */
    public MenuItem() {
        this.name = "Unnamed Item";
        this.basePrice = 0.0;
        this.itemCode = "N/A";
    }

    /**
     * Creates an item with the given details. The price is validated.
     *
     * @param name      the item name
     * @param basePrice the base price in pesos (must not be negative)
     * @param itemCode  the menu code
     */
    public MenuItem(String name, double basePrice, String itemCode) {
        this.name = name;
        this.basePrice = 0.0;
        setBasePrice(basePrice);
        this.itemCode = itemCode;
    }

    /**
     * Computes the final price of this item. Each subclass defines its own rule.
     *
     * @return the final price in pesos
     */
    public abstract double computePrice();

    /**
     * Returns a short description made of the name and item code.
     *
     * @return the description text
     */
    public String getDescription() {
        return name + " [" + itemCode + "]";
    }

    /**
     * Gets the item name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item name. Blank or null names are rejected.
     *
     * @param name the new name
     */
    public void setName(String name) {
        // Only accept a real, non-blank name
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: name cannot be blank.");
        }
    }

    /**
     * Gets the base price.
     *
     * @return the base price in pesos
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price. Negative values are rejected.
     *
     * @param basePrice the new base price in pesos
     */
    public void setBasePrice(double basePrice) {
        // A price below zero makes no sense, so keep the old value
        if (basePrice < 0) {
            System.out.println("Error: base price cannot be negative.");
        } else {
            this.basePrice = basePrice;
        }
    }

    /**
     * Gets the item code.
     *
     * @return the item code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code. Blank or null codes are rejected.
     *
     * @param itemCode the new item code
     */
    public void setItemCode(String itemCode) {
        // Only accept a real, non-blank code
        if (itemCode != null && !itemCode.trim().isEmpty()) {
            this.itemCode = itemCode;
        } else {
            System.out.println("Error: item code cannot be blank.");
        }
    }
}
