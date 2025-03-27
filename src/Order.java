import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    //for storing the orderid so that we can avoid opening file again and again
    public static HashSet<String> hs = new HashSet<>();

    //for storing file content to the list avoid opening file
    public static ArrayList<Order> arr = new ArrayList<>();

    /**
     * todo
     * auto generating order id
     */
    private static int count;

    Scanner sc = new Scanner(System.in);
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
    //todo update the file path to correct file
    //final static String filePath = "C:\\Users\\Harsha\\IdeaProjects\\Order Management System 2\\src\\data.txt";

    public Order(String orderId, String orderDescription, String deliveryAddress, String orderDate, double amount, String deliveryDate, String status) {
        this.orderId = orderId;
        this.orderDescription = orderDescription;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public void setOrderDescription() {
        while(true) {
            System.out.println("Enter the Order Description: ");
            String des = sc.nextLine();

            if(des.isEmpty() | des.length()<3){
                System.out.println("Enter a proper product description..");
                continue;
            }else {
                this.orderDescription=des;
                break;
            }
        }
    }

    public void setDeliveryAddress() {
        while(true) {
            System.out.println("Enter the delivery address:");
            String addres = sc.nextLine();
            if(addres.isEmpty() | addres.length()<3){
                System.out.println("Enter correct delivery address ");
                continue;
            }else {
                this.deliveryAddress=addres;
                break;
            }
        }
    }

    public void setOrderDate() {
        LocalDateTime curdate = LocalDateTime.now();
        DateTimeFormatter formattter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        String orderDate = curdate.format(formattter);
        this.orderDate = orderDate;
        setDeliveryDate(curdate);
    }

    public void setAmount() {
        while (true) {
            System.out.println("enter the amount: ");

            double amount=0;
            try {
                amount = sc.nextDouble();
                sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Please enter Amount only No strings are allowed.");
                sc.nextLine();
                continue;
            }

            if (amount < 10) {
                System.out.println("please enter a correct value.");
                continue;
            } else {
                this.amount = amount;
                break;
            }
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeliveryDate(LocalDateTime curDate) {
            LocalDateTime deldate = curDate.plusDays(5);
            DateTimeFormatter formattter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
            String deliveryDate = deldate.format(formattter);
            this.deliveryDate = deliveryDate;

    }

    public Order(){
        setOrderId();
        setOrderDescription();
        setDeliveryAddress();
        setOrderDate();
        setAmount();
        setStatus("In Progress");
        arr.add(new Order(orderId,orderDescription,deliveryAddress,orderDate,amount,deliveryDate,status));
        hs.add(orderId);
    }



    //setting the orderId
    public void setOrderId() {
//        while(true) {
//            System.out.println("Enter the Order ID: ");
//            String ordId = sc.nextLine();
//            if(ordId.isEmpty()){
//                System.out.println("Enter a proper orderId..");
//            }else if (hs.contains(ordId)) {
//                System.out.println("The order id is present in the set ");
//            } else {
//                this.orderId = "ORD-"+(++count);
//                break;
//            }
//        }
        this.orderId="ORD-"+(++count);
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
    public String toString(){
        String str,des;
        if(deliveryAddress.length()>35 && orderDescription.length()>30){
            str= deliveryAddress.substring(0,30)+"...";
            des=orderDescription.substring(0,25)+"...";
            return String.format("|%15s|%30s|%35s|%2s|%20.2f|%20s|%11s|",orderId,des,str,orderDate,amount,deliveryDate,status);
        }else if(deliveryAddress.length()>35){
            str= deliveryAddress.substring(0,30);
            str+="...";
            return String.format("|%15s|%30s|%35s|%2s|%20.2f|%20s|%11s|",orderId,orderDescription,str,orderDate,amount,deliveryDate,status);
        }else if(orderDescription.length()>30){
            des=orderDescription.substring(0,25)+"...";
            return String.format("|%15s|%30s|%35s|%20s|%20.2f|%20s|%11s|",orderId,des,deliveryAddress,orderDate,amount,deliveryDate,status);
        }else {
            return String.format("|%15s|%30s|%35s|%2s|%20.2f|%20s|%11s|", orderId, orderDescription, deliveryAddress, orderDate, amount, deliveryDate, status);
        }
    }
}
