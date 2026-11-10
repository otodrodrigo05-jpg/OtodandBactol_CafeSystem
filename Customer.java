/**
 * Represents the person placing an order. Customers earn loyalty points
 * and students receive a discount.
 *
 * @author Otod
 * @version 1.0
 */
public class Customer {

    /** Pesos that must be spent to earn one loyalty point. */
    private static final double PESOS_PER_POINT = 50;

    /** Customer's name. */
    private String name;

    /** True if the customer is a student. */
    private boolean student;

    /** Accumulated loyalty points (never negative). */
    private int loyaltyPoints;

    /**
     * Creates a guest customer who is not a student.
     */
    public Customer() {
        this("Guest", false);
    }

    /**
     * Creates a non-student customer with a name.
     *
     * @param name the customer's name
     */
    public Customer(String name) {
        this(name, false);
    }

    /**
     * Creates a customer with a name and student status.
     *
     * @param name    the customer's name
     * @param student true if the customer is a student
     */
    public Customer(String name, boolean student) {
        this.name = name;
        this.student = student;
        this.loyaltyPoints = 0;
    }

    /**
     * Adds loyalty points directly.
     *
     * @param points the points to add (negative values are rejected)
     */
    public void addPoints(int points) {
        // Points may only be added, never removed
        if (points < 0) {
            System.out.println("Error: points cannot be negative.");
        } else {
            loyaltyPoints += points;
        }
    }

    /**
     * Adds one loyalty point for every 50 pesos spent.
     *
     * @param amountSpent the amount spent in pesos
     */
    public void addPoints(double amountSpent) {
        // Ignore negative amounts; otherwise convert pesos to whole points
        if (amountSpent < 0) {
            System.out.println("Error: amount spent cannot be negative.");
        } else {
            loyaltyPoints += (int) (amountSpent / PESOS_PER_POINT);
        }
    }

    /**
     * Gets the customer's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer's name. Blank names are rejected.
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
     * Checks whether the customer is a student.
     *
     * @return true if a student
     */
    public boolean isStudent() {
        return student;
    }

    /**
     * Sets the student status.
     *
     * @param student true if a student
     */
    public void setStudent(boolean student) {
        this.student = student;
    }

    /**
     * Gets the loyalty points.
     *
     * @return the points
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Sets the loyalty points. Negative values are rejected.
     *
     * @param loyaltyPoints the new points total
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        // Points can never go below zero
        if (loyaltyPoints < 0) {
            System.out.println("Error: loyalty points cannot be negative.");
        } else {
            this.loyaltyPoints = loyaltyPoints;
        }
    }
}
