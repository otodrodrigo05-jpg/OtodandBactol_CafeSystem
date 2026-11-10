/**
 * Main class that drives the Campus Cafe ordering system.
 *
 * @author Otod
 * @version 1.0
 */
public class CafeApp {

    /**
     * Program entry point. Builds two orders and prints their receipts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Customers made with different constructors
        Customer ana = new Customer("Ana Reyes", true);
        Customer ben = new Customer("Ben Cruz");

        // --- Order 1001: parameterized constructors ---
        Beverage latte = new Beverage("Iced Latte", 95, "B-01", "L");
        Pastry ensaymada = new Pastry("Ensaymada", 45, "P-01", true);
        Meal adobo = new Meal("Chicken Adobo", 110, "M-01", true, 1);

        // Cookie uses the default constructor, then setters
        Pastry cookie = new Pastry();
        cookie.setName("Cookie");
        cookie.setItemCode("P-02");
        cookie.setBasePrice(30);

        Order order1 = new Order("1001", ana);
        order1.addItem(latte);
        order1.addItem(ensaymada);
        order1.addItem(adobo);
        order1.addItem(cookie); // fourth item: should be rejected
        order1.printReceipt();

        // --- Order 1002: default constructors filled by setters ---
        Beverage hotChoc = new Beverage();
        hotChoc.setName("Hot Chocolate");
        hotChoc.setItemCode("B-02");
        hotChoc.setBasePrice(-5); // invalid: rejected
        hotChoc.setBasePrice(80);
        hotChoc.setSize("M");

        Meal tapa = new Meal();
        tapa.setName("Beef Tapa");
        tapa.setItemCode("M-02");
        tapa.setBasePrice(130);

        Order order2 = new Order("1002", ben);
        order2.addItem(hotChoc);
        order2.addItem(tapa);
        order2.setVoucher(20);
        order2.printReceipt();
    }
}
