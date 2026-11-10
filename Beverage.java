/**
 * Represents a drink sold at the campus cafe.
 * The price depends on the selected size.
 *
 * @author Otod
 * @version 1.0
 */
public class Beverage extends MenuItem {

    /** Extra charge for a medium drink. */
    private static final double MEDIUM_FEE = 15;

    /** Extra charge for a large drink. */
    private static final double LARGE_FEE = 25;

    /** Size of the drink: "S", "M", or "L". */
    private String size;

    /**
     * Creates a small, unnamed beverage.
     */
    public Beverage() {
        super();
        this.size = "S";
    }

    /**
     * Creates a beverage with full details.
     *
     * @param name      the drink name
     * @param basePrice the base (small) price in pesos
     * @param itemCode  the menu code
     * @param size      "S", "M", or "L"
     */
    public Beverage(String name, double basePrice, String itemCode, String size) {
        super(name, basePrice, itemCode);
        this.size = "S";
        setSize(size);
    }

    /**
     * Computes the final price based on size.
     *
     * @return the price of this beverage in pesos
     */
    @Override
    public double computePrice() {
        // Large adds 25, Medium adds 15, Small uses the base price
        if (size.equals("L")) {
            return basePrice + LARGE_FEE;
        } else if (size.equals("M")) {
            return basePrice + MEDIUM_FEE;
        } else {
            return basePrice;
        }
    }

    /**
     * Describes the beverage including its size.
     *
     * @return the description text
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " Size: " + size;
    }

    /**
     * Gets the size.
     *
     * @return "S", "M", or "L"
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size. Only S, M, or L are accepted; otherwise the old value is kept.
     *
     * @param size the new size
     */
    public void setSize(String size) {
        // Accept only the three valid sizes (case-insensitive)
        if (size != null && (size.equalsIgnoreCase("S")
                || size.equalsIgnoreCase("M")
                || size.equalsIgnoreCase("L"))) {
            this.size = size.toUpperCase();
        } else {
            System.out.println("Error: size must be S, M, or L.");
        }
    }
}
