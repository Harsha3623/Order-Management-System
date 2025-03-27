import java.util.ArrayList;

public interface OrderManagement {
     public abstract void addOrder();

     public abstract void viewOrder();

     //method overloading
     void viewOrder(String orderID);

     //view order for sorting option
     void viewOrder(ArrayList<Order> array);

     void markDelivered();

     void cancelOrder();

     void deleteOrder();

     void exit();

     void sortOrder();


     void generateReport();
}
