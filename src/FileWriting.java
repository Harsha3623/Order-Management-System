import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileWriting extends Thread {

    //ArrayList for storing data
    private final ArrayList<Order> array;

    public FileWriting(ArrayList<Order> array) {
        this.array = array;
    }

    LocalDateTime curdate = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH-mm-ss");
    String currentDate = curdate.format(formatter);

    String filename = "C:\\Users\\Harsha\\Desktop\\ReportFiles\\Order_Report " + currentDate + ".txt";




    @Override
    public void run() {
    File myfile = new File(filename);
        try {
            if(myfile.createNewFile()){
               try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
                   writer.write("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                   writer.newLine();
                   writer.write("|   Order Id    |      Order Description       |        Delivery Address           |    Order Date     |       Amount       |   Delivery Date    |   Status  |");
                   writer.newLine();
                   writer.write("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                   writer.newLine();
                   for (Order order: array){
                       String orderData = String.format("|%-10s|%-20s|%-25s|%-20s|%-10.2f|%-20s|%-11s|"
                                      ,order.getOrderId(),order.getOrderDescription(),order.getDeliveryAddress(),order.getOrderDate(),order.getAmount(),order.getDeliveryDate(),order.getStatus());
                       writer.write(orderData);
                       writer.newLine();
                   }
                   writer.write("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                   System.out.println("Order Report has been generated.");
               }catch (IOException e) {
                   throw new RuntimeException(e);
               }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.run();
    }
}
