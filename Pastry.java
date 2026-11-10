/**
 * Represents a baked item that can be warmed for an extra fee.
 *
 * @author Otod
 * @version 1.0
 */
public class Pastry extends MenuItem {

    /** Fee charged for warming the pastry. */
    private static final double WARMING_FEE = 10;

    /** Whether the pastry is served warm. */
    private boolean warmed;

    /**
     * Creates an unnamed pastry that is not warmed.
     */
    public Pastry() {
        super();
        this.warmed = false;
    }

    /**
     * Creates a pastry with full details.
     *
     * @param name      the pastry name
     * @param basePrice the base price in pesos
     * @param itemCode  the menu code
     * @param warmed    true if the pastry is to be warmed
     */
    public Pastry(String name, double basePrice, String itemCode, boolean warmed) {
        super(name, basePrice, itemCode);
        this.warmed = warmed;
    }

    /**
     * Computes the price, adding the warming fee if warmed.
     *
     * @return the price of this pastry in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Warming costs an extra 10 pesos
        if (warmed) {
            price += WARMING_FEE;
        }
        return price;
    }

    /**
     * Describes the pastry, noting if it is warmed.
     *
     * @return the description text
     */
    @Override
    public String getDescription() {
        String text = super.getDescription();
        // Mention the warming only when it applies
        if (warmed) {
            text += " (Warmed)";
        }
        return text;
    }

    /**
     * Checks whether the pastry is warmed.
     *
     * @return true if warmed
     */
    public boolean isWarmed() {
        return warmed;
    }

    /**
     * Sets whether the pastry is warmed.
     *
     * @param warmed true to warm the pastry
     */
    public void setWarmed(boolean warmed) {
        this.warmed = warmed;
    }
}
