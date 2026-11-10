/**
 * Connects a {@link Customer} to up to three {@link MenuItem} objects.
 * Items are kept in separate fields (no arrays or collections).
 *
 * @author Otod
 * @version 1.0
 */
public class Order {

    /** Maximum number of items per order. */
    private static final int MAX_ITEMS = 3;

    /** Discount rate given to students. */
    private static final double STUDENT_DISCOUNT_RATE = 0.10;

    /** Width of the item column on the receipt. */
    private static final int RECEIPT_WIDTH = 46;

    /** The customer who placed the order. */
    private Customer customer;

    /** First item slot. */
    private MenuItem item1;

    /** Second item slot. */
    private MenuItem item2;

    /** Third item slot. */
    private MenuItem item3;

    /** Number of items currently in the order. */
    private int itemCount;

    /** Unique order number. */
    private String orderId;

    /** Voucher amount applied when the receipt is printed. */
    private double voucher;

    /**
     * Creates an empty order for a customer.
     *
     * @param orderId  the order number
     * @param customer the customer placing the order
     */
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.itemCount = 0;
        this.voucher = 0;
    }

    /**
     * Adds an item to the first empty slot.
     *
     * @param item the item to add
     * @return true if added, false if the order is full or the item is null
     */
    public boolean addItem(MenuItem item) {
        // Reject missing items
        if (item == null) {
            System.out.println("Error: cannot add a null item.");
            return false;
        }
        // Fill the first empty slot, or report that the order is full
        if (item1 == null) {
            item1 = item;
        } else if (item2 == null) {
            item2 = item;
        } else if (item3 == null) {
            item3 = item;
        } else {
            System.out.println("Order is full. Cannot add " + item.getName()
                    + " [" + item.getItemCode() + "].");
            return false;
        }
        itemCount++;
        return true;
    }

    /**
     * Sums the price of every item (polymorphic calls to computePrice()).
     *
     * @return the subtotal in pesos
     */
    public double computeSubtotal() {
        double subtotal = 0;
        // Add up each filled slot
        if (item1 != null) {
            subtotal += item1.computePrice();
        }
        if (item2 != null) {
            subtotal += item2.computePrice();
        }
        if (item3 != null) {
            subtotal += item3.computePrice();
        }
        return subtotal;
    }

    /**
     * Computes the discount amount (10% for students, otherwise 0).
     *
     * @return the discount in pesos
     */
    public double computeDiscount() {
        // Only students get the discount
        if (customer.isStudent()) {
            return computeSubtotal() * STUDENT_DISCOUNT_RATE;
        } else {
            return 0;
        }
    }

    /**
     * Computes the total after the student discount.
     *
     * @return the total in pesos
     */
    public double computeTotal() {
        return computeSubtotal() - computeDiscount();
    }

    /**
     * Computes the total after the student discount and a voucher.
     * The total never goes below zero.
     *
     * @param voucher the voucher amount in pesos
     * @return the total in pesos
     */
    public double computeTotal(double voucher) {
        double total = computeTotal() - voucher;
        // Never let the total be negative
        if (total < 0) {
            return 0;
        } else {
            return total;
        }
    }

    /**
     * Prints the receipt and awards loyalty points to the customer.
     */
    public void printReceipt() {
        String tag;
        // Show whether the customer is a student
        if (customer.isStudent()) {
            tag = " (Student)";
        } else {
            tag = "";
        }
        System.out.println("Order #" + orderId + " \u2014 " + customer.getName() + tag);
        printLine(item1);
        printLine(item2);
        printLine(item3);
        System.out.printf("Subtotal: %.2f%n", computeSubtotal());
        // Only show the discount line for students
        if (customer.isStudent()) {
            System.out.printf("Student discount (10%%): -%.2f%n", computeDiscount());
        }
        // Only show the voucher line when one is applied
        if (voucher > 0) {
            System.out.printf("Voucher: -%.2f%n", voucher);
        }
        double total = computeTotal(voucher);
        System.out.printf("TOTAL: %.2f%n", total);
        customer.addPoints(total);
        System.out.println("Loyalty points: " + customer.getLoyaltyPoints());
    }

    /**
     * Prints one receipt line: description, dot leader, and price.
     *
     * @param item the item to print (ignored if null)
     */
    private void printLine(MenuItem item) {
        // Skip empty slots
        if (item == null) {
            return;
        }
        String text = item.getDescription() + " ";
        // Pad with dots so the prices line up
        while (text.length() < RECEIPT_WIDTH) {
            text += ".";
        }
        System.out.printf("%s %.2f%n", text, item.computePrice());
    }

    /**
     * Gets the customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the number of items in the order.
     *
     * @return the item count
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Gets the order id.
     *
     * @return the order id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Gets the voucher amount.
     *
     * @return the voucher in pesos
     */
    public double getVoucher() {
        return voucher;
    }

    /**
     * Sets the voucher amount. Negative values are rejected.
     *
     * @param voucher the voucher in pesos
     */
    public void setVoucher(double voucher) {
        // A voucher cannot be negative
        if (voucher < 0) {
            System.out.println("Error: voucher cannot be negative.");
        } else {
            this.voucher = voucher;
        }
    }
}
