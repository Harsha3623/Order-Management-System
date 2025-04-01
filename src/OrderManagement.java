import java.util.ArrayList;
import java.util.List;

public interface OrderManagement {
     void addOrder();

     void viewOrder();

     //method overloading
     void viewOrder(String orderID);

     //view order for sorting option
     void viewOrder(List<Order> array);

     void markDelivered();

     void cancelOrder();

     void deleteOrder();

     void exit();

     void sortOrder();

     void generateReport();
}
