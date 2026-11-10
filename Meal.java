/**
 * Represents a rice meal that can be upsized and given extra sides.
 *
 * @author Otod
 * @version 1.0
 */
public class Meal extends MenuItem {

    /** Extra charge for upsizing the meal. */
    private static final double UPSIZE_FEE = 20;

    /** Charge for each extra side. */
    private static final double SIDE_FEE = 12;

    /** Whether the meal is upsized. */
    private boolean upsized;

    /** Number of extra sides ordered. */
    private int extraSides;

    /**
     * Creates an unnamed regular meal with no extra sides.
     */
    public Meal() {
        super();
        this.upsized = false;
        this.extraSides = 0;
    }

    /**
     * Creates a meal with full details.
     *
     * @param name       the meal name
     * @param basePrice  the base price in pesos
     * @param itemCode   the menu code
     * @param upsized    true if upsized
     * @param extraSides number of extra sides (must not be negative)
     */
    public Meal(String name, double basePrice, String itemCode, boolean upsized, int extraSides) {
        super(name, basePrice, itemCode);
        this.upsized = upsized;
        this.extraSides = 0;
        setExtraSides(extraSides);
    }

    /**
     * Computes the price: base + upsize fee + 12 per extra side.
     *
     * @return the price of this meal in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Upsizing adds a flat fee
        if (upsized) {
            price += UPSIZE_FEE;
        }
        return price + (SIDE_FEE * extraSides);
    }

    /**
     * Describes the meal with its upsize status and number of sides.
     *
     * @return the description text
     */
    @Override
    public String getDescription() {
        String sizeText;
        // Pick the wording for the size
        if (upsized) {
            sizeText = "Upsized";
        } else {
            sizeText = "Regular";
        }
        String sideText;
        // Use singular for exactly one side
        if (extraSides == 1) {
            sideText = "1 side";
        } else {
            sideText = extraSides + " sides";
        }
        return super.getDescription() + " " + sizeText + ", " + sideText;
    }

    /**
     * Checks whether the meal is upsized.
     *
     * @return true if upsized
     */
    public boolean isUpsized() {
        return upsized;
    }

    /**
     * Sets whether the meal is upsized.
     *
     * @param upsized true to upsize
     */
    public void setUpsized(boolean upsized) {
        this.upsized = upsized;
    }

    /**
     * Gets the number of extra sides.
     *
     * @return the extra side count
     */
    public int getExtraSides() {
        return extraSides;
    }

    /**
     * Sets the number of extra sides. Negative values are rejected.
     *
     * @param extraSides the new count
     */
    public void setExtraSides(int extraSides) {
        // Cannot order a negative number of sides
        if (extraSides < 0) {
            System.out.println("Error: extra sides cannot be negative.");
        } else {
            this.extraSides = extraSides;
        }
    }
}
