package SystemManagementInventory;

public interface Perishable {
    void setToPerishable();
    boolean checkPerishable();
    void handleExpiration();
}
