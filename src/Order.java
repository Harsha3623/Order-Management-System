import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {

    //for storing the order id so that we can avoid opening file again and again
    public static HashSet<String> hs = new HashSet<>();

    //for storing file content to the list avoid opening file
    public static ArrayList<Order> arr = new ArrayList<>();

    /// auto generating order id
    private static int count;

    Scanner sc = new Scanner(System.in);

    //static block which reads the file when program starts
    static {

            try {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Harsha\\IdeaProjects\\Order Management System 2\\src\\Order_Transaction.txt"));
                String line;

                while((line= reader.readLine())!=null){

                    String[] data = line.split("#,; ' ,;#");
                    hs.add(data[0]);
                    //checking for the order id which can be placed after that
                    String[] str = data[0].split("-");

                    if(Integer.parseInt(str[1])>count){

                        count=Integer.parseInt(str[1]);

                    }


                    arr.add(new Order(data[0],data[1],data[2],data[3],Double.parseDouble(data[4]),data[5],data[6]));
                }
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
    }

    private String orderId;
    private String orderDescription;
    private String deliveryAddress;
    private String orderDate;
    private double amount;
    private String status;
    private String deliveryDate;
    private boolean bool = false;


    public Order(){

            String orderId = "ORD-" + String.format("%04d", (++count));
            String orderDescription = FetchOrderDescription();
            if(bool){
                return;
            }
            String deliveryAddress = FetchDeliveryAddress();
            if (bool){
                return;
            }

            setOrderDate();
            double amount = CalculateAmount();
            if (bool){
                return;
            }
            setStatus("In Progress");

            arr.add(new Order(orderId, orderDescription, deliveryAddress, orderDate, amount, deliveryDate, status));
            System.out.println("Order Added Successfully.\n");
            hs.add(orderId);

    }


    private Order(String orderId, String orderDescription, String deliveryAddress, String orderDate, double amount, String deliveryDate, String status) {

        this.orderId = orderId;
        this.orderDescription = orderDescription;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.status = status;

    }

    public String FetchOrderDescription() {

        while(true) {

            System.out.println("Enter the Order Description: ");
            String des = sc.nextLine();

            if(des.isEmpty() | des.length()<3){

                System.out.println("Enter a proper product description..");

            }else if(des.trim().equals("exit")) {
                bool = true;
                return "";
            }else{

                return des;
            }
        }
    }

    public String FetchDeliveryAddress() {

        while(true) {

            System.out.println("Enter the delivery address:");
            String addres = sc.nextLine();
            if(addres.isEmpty() | addres.length()<3){

                System.out.println("Enter correct delivery address ");

            }else if(addres.trim().equals("exit")){
                bool= true;
                return "";
            }else {

                return addres;
            }
        }
    }

    public void setOrderDate() {

        LocalDateTime curdate = LocalDateTime.now();
        DateTimeFormatter formattter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss a");
        this.orderDate = (curdate.format(formattter)).toUpperCase();
        setDeliveryDate(curdate);

    }

    public double CalculateAmount() {

        while (true) {

            System.out.println("enter the amount: (or enter -1 to exit)");

            double amount;
            try {

                amount = sc.nextDouble();
                sc.nextLine();


            }catch (InputMismatchException e){

                System.out.println("Please enter Amount only No strings are allowed.");
                sc.nextLine();
                continue;

            }

            if(amount==-1){
                bool= true;
                return 0;
            }else if (amount < 10&& amount>0) {

                System.out.println("please enter a correct value.");

            } else {

                return amount;
            }
        }
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public void setDeliveryDate(LocalDateTime curDate) {

            LocalDateTime deldate = curDate.plusDays(5);
            DateTimeFormatter formattter = DateTimeFormatter.ofPattern("yyy-MM-dd");
            this.deliveryDate = deldate.format(formattter);
    }



    //getter of OrderId
    public String getOrderId() {
        return orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    //for sorting the dates properly
    public String getOrderDateForSorting(){
        String ordDate = orderDate;
        ordDate.replaceAll("AM", "").replaceAll("PM", "");
        return ordDate;
    }

    //for sorting delivery date properly
    public String getDeliveryDateForSorting(){
        String delDate = deliveryDate;
        delDate.replaceAll("AM", "").replaceAll("PM", "");
        return delDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    //setting the delivery date by updating the array list in
    //implementing class
    public void setDeliveryDate(String deliveryDate){
        this.deliveryDate=deliveryDate;
    }


    //overriding toString method for writing back to file
    @Override
    public String toString(){
        String result;

        String str,des;

        if(deliveryAddress.length()>35 && orderDescription.length()>30){

            str= deliveryAddress.substring(0,30)+"...";
            des=orderDescription.substring(0,25)+"...";
            result = String.format("|%15s|%30s|%35s|%25s|%20.2f|%25s|%11s|", orderId, des, str, orderDate, amount, deliveryDate, status);

        }else if(deliveryAddress.length()>35){

            str= deliveryAddress.substring(0,30);
            str+="...";
            result = String.format("|%15s|%30s|%35s|%25s|%20.2f|%25s|%11s|", orderId, orderDescription, str, orderDate, amount, deliveryDate, status);

        }else if(orderDescription.length()>30){

            des=orderDescription.substring(0,25)+"...";
            result = String.format("|%15s|%30s|%35s|%25s|%20.2f|%25s|%11s|", orderId, des, deliveryAddress, orderDate, amount, deliveryDate, status);

        }else {

            result = String.format("|%15s|%30s|%35s|%25s|%20.2f|%25s|%11s|", orderId, orderDescription, deliveryAddress, orderDate, amount, deliveryDate, status);

        }
        return result;
    }

    //setter for updating the user delivery address
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
