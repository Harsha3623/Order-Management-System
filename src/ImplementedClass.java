import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ImplementedClass  implements OrderManagement{

    //Scanner class
    Scanner sc = new Scanner(System.in);

    //add order tot the arraylist
    @Override
    public void addOrder() {

        new Order();
    }


    //viewing all the order list sort based on the order id
    @Override
    public void viewOrder() {

    ArrayList<Order> array = Order.arr;

        array.sort(Comparator.comparing(Order::getOrderId));
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|   Order Id    |      Order Description       |        Delivery Address           |     Order Date     |       Amount       |   Delivery Date    |   Status  |");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Order order : array) {

                System.out.println(order.toString());
            }

        System.out.println("\n\n");

    }

    //method overloading for view order by id and view order
    @Override
    public void viewOrder(String orderID) {

        ArrayList<Order> array=Order.arr;

        System.out.println("--------------------------");
        System.out.println("    Order Details: ");
        System.out.println("--------------------------");
        for(Order order: array){

            if(order.getOrderId().trim().equalsIgnoreCase(orderID)){
                System.out.println("Order ID: "+order.getOrderId());
                System.out.println("Order Description: "+order.getOrderDescription());
                System.out.println("Order Delivery Address: "+order.getDeliveryAddress());
                System.out.println("Order Date: "+order.getOrderDate());
                System.out.println("Order Amount: "+order.getAmount());
                System.out.println("Order Delivery Date: "+order.getDeliveryDate());
                System.out.println("Order Status: "+order.getStatus());
                System.out.println();
                break;
            }
        }

        System.out.println("\n\n");
    }

    @Override
    public void markDelivered() {

        int count=0;

        outer: while(true){

            System.out.println("Enter a valid order id: ");

            String ordId = sc.nextLine();
            if(Order.hs.contains(ordId)){

                for(Order order: Order.arr){

                    //ifOrder Id equals to array list order id
                    if(order.getOrderId().trim().equals(ordId)){

                        if((order.getStatus().trim()).equals("Cancelled")){

                            System.out.println("The order cannot be delivered it is cancelled by the user..");
                            break;

                        }else if((order.getStatus().trim()).equals("Delivered")){

                            //Order is already delivered to the user
                            System.out.println("The order is already delivered and cannot deliver again.");
                            break;

                        }else {

                            //else deliver the order to the customer
                            order.setStatus("Delivered");
                            LocalDateTime curDate = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
                            String currentDate = curDate.format(formatter);
                            order.setDeliveryDate(currentDate);
                            System.out.println("The order is delivered to the customer..");
                            break;
                        }
                    }
                }
                System.out.println("Do you want to mark another Order as Delivered(Y/N)");

                while(true) {

                    char ch = sc.nextLine().charAt(0);
                    if (ch == 'Y' || ch == 'y') {

                        continue outer;

                    } else if (ch == 'n' || ch == 'N') {

                        break outer;

                    } else {

                        System.out.println("please enter either Yes or no option..\n");
                    }
                }
            }else if(count<2){

                System.out.println("\nThe order id is not present in the orders. enter valid order id.");
                count++;

            }else if(count<5){

                System.out.println("\nPlease check the available order id for deleting the order details:");
                System.out.println("-------------Available order id-----------");
                for (String s: Order.hs){

                    System.out.println(s);

                }
                System.out.println("\n");
                count++;

            }else {

                System.out.println("Multiple incorrect  order id is entered \nGoing back to main menu\n\n");
                break;

            }
        }
        System.out.println("\n\n");
    }

    @Override
    public void cancelOrder() {

        int count=0;

        outer: while(true){

            System.out.println("enter valid order id: ");
            String orderId = sc.nextLine();

            if(Order.hs.contains(orderId)){

                for(Order order: Order.arr){

                    if((order.getOrderId().trim()).equals(orderId)){
                        //what is the order is delivered
                        //need to print the order is delievred
                        if((order.getStatus().trim()).equals("Delivered")){

                            System.out.println("The order is delivered and cannot be cancelled.");
                        }
                        else if((order.getStatus().trim()).equals("In Progress")){

                        order.setStatus("Cancelled");
                        //String str = null;
                        order.setDeliveryDate((String) null);
                            System.out.println("The order has been cancelled successfully.");

                        }else {

                            //if order is cancelled already by the user
                            System.out.println("Order has been already cancelled.");

                        }
                    }
                }

                while(true){

                    System.out.println("Do you want to cancel another Order(Y/N)");
                    char ch = sc.nextLine().charAt(0);

                    if(ch=='Y'||ch=='y'){

                        continue outer;

                    }else if(ch=='n'||ch=='N'){

                        break outer;

                    }else {

                        System.out.println("Please enter either yes(Y) or NO(N) option.");

                    }
                }
            }else if(count<3){
                System.out.println("The order id is not present in the orders. enter valid order id.");
                count++;
            }else if(count<5){

                System.out.println("Please check the available order id for deleting the order details:");
                System.out.println("-------------Available order id-----------");
                for (String s: Order.hs){
                    System.out.println(s);
                }
                count++;
            }else {
                System.out.println("Multiple incorrect  order id is entered \nGoing back to main menu\n\n");
                break;
            }
        }
        System.out.println("\n\n");
    }

    @Override
    public void deleteOrder() {
        int count=0;

        outer: while (true){

            System.out.println("Enter a valid order id for deleting the order:");
            String orderId = sc.nextLine();

            if(Order.hs.contains(orderId)){

                for(Order order: Order.arr){

                    if((order.getOrderId().trim()).equals(orderId)){
                        //double authentication while deleting the order details

                        while(true) {
                            System.out.println("Confirm to Delete the order Details(y/n)");
                            char cnfrm = sc.nextLine().charAt(0);

                            if (cnfrm == 'y' | cnfrm == 'Y') {

                                Order.arr.remove(order);
                                System.out.println("The order has been removed successfully");
                                //need to remove the order id from hash set for better usage after deleting
                                //in the array list
                                Order.hs.remove(orderId);
                                break;

                            } else if (cnfrm == 'n' | cnfrm == 'N') {
                                System.out.println("The oder is not deleted");
                                break;

                            } else {
                                System.out.println("Enter either yes or no.");
                            }
                        }
                        break;
                    }
                }
                while (true){

                    System.out.println("Do you want to delete another order(Y/N)");
                    char ch = sc.nextLine().charAt(0);
                    if(ch=='Y' | ch=='y'){
                        continue outer;

                    } else if (ch=='N'| ch=='n') {

                        break outer;

                    }else {

                        System.out.println("Enter either yes or no option..");
                    }
                }
            }else if(count<2){

                System.out.println("The order id is not present in the orders. enter valid order id.");
                count++;

            }else if(count<5){

                System.out.println("Please check the available order id for deleting the order details:");
                System.out.println("-------------Available order id-----------");
                for (String s: Order.hs){
                    System.out.println(s);
                }
                count++;

            }else {

                System.out.println("Multiple incorrect  order id is entered \nGoing back to main menu\n\n");
                break;

            }
        }
        System.out.println("\n\n");
    }

    @Override
    public void exit() {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Harsha\\IdeaProjects\\Order Management System 2\\src\\Order_Transaction.txt"))){

        for(Order order: Order.arr){

            writer.write(order.getOrderId()+"#,; ' ,;#"+order.getOrderDescription()+"#,; ' ,;#"+
                    order.getDeliveryAddress()+"#,; ' ,;#"+order.getOrderDate()+"#,; ' ,;#"+order.getAmount()+
                    "#,; ' ,;#"+order.getDeliveryDate()+"#,; ' ,;#"+order.getStatus());

            writer.newLine();

        }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void sortOrder() {

        while(true) {

            System.out.println("----------Chose Sort Order property---------");
            System.out.println("1--> OrderID");
            System.out.println("2--> Order Description");
            System.out.println("3--> Delivery Address");
            System.out.println("4--> Order Date");
            System.out.println("5--> Amount");
            System.out.println("6--> Delivery Date Time ");
            System.out.println("7--> Exit");

            int number;

            try {

                number = sc.nextInt();
                sc.nextLine();

                if(number==7){

                    break;
                }

            }catch (InputMismatchException e){

                System.out.println("Enter a Integer value.");
                sc.nextLine();
               continue;

            }

            int ascOrDesc;

            while(true) {

                System.out.println("----------Chose Sort Order property---------");
                System.out.println("1--> Ascending");
                System.out.println("2--> Descending");

                //handling input mismatch exception
                try {

                    ascOrDesc = sc.nextInt();
                    sc.nextLine();

                    if(ascOrDesc==1 | ascOrDesc==2){

                        break;
                    }else {
                        System.out.println("enter a either 1 or 2");

                    }

                }catch (InputMismatchException e){

                    System.out.println("Enter Integer Value .");
                    sc.nextLine();
                }
            }
            //creating the local array list for sorting purpose
            ArrayList<Order> array = Order.arr;

            switch (number){
                case 1:
                    //sort Based On user inputs;
                    if(ascOrDesc==1) {

                        array.sort(Comparator.comparing(Order::getOrderId));
                    }else{

                        array.sort(Comparator.comparing(Order::getOrderId).reversed());
                    }

                    viewOrder(array);
                    break;

                case 2:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparing(Order::getOrderDescription));

                    }else {

                        array.sort(Comparator.comparing(Order::getOrderDescription).reversed());
                    }

                    viewOrder(array);
                    break;

                case 3:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparing(Order::getDeliveryAddress));

                    }else{

                        array.sort(Comparator.comparing(Order::getDeliveryAddress).reversed());

                    }

                    viewOrder(array);
                    break;

                case 4:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparing(Order::getOrderDate));

                    }else{

                        array.sort(Comparator.comparing(Order::getOrderDate).reversed());

                    }

                    viewOrder(array);
                    break;

                case 5:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparingDouble(Order::getAmount));

                    }else{

                        array.sort(Comparator.comparingDouble(Order::getAmount).reversed());

                    }

                    viewOrder(array);
                    break;

                case 6:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparing(Order::getDeliveryDate));

                    }else{

                        array.sort(Comparator.comparing(Order::getDeliveryDate).reversed());

                    }

                    viewOrder(array);
                    break;

                default:
                    System.out.println("Enter a proper value ");

            }

        }
        System.out.println("\n\n");
    }

    @Override
    //view order for sorting option
    public void viewOrder(List<Order> array){

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|   Order Id    |      Order Description       |        Delivery Address           |     Order Date     |       Amount       |   Delivery Date    |   Status  |");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Order order : array) {

            System.out.println(order.toString());

        }

        System.out.println("\n\n");
    }

    @Override
    public void generateReport() {

        while(true) {

            System.out.println("-------------Choose Report Generation Option-----------");
            System.out.println("1--> Export All");
            System.out.println("2--> By Status");
            System.out.println("3-->exit");

            int number = sc.nextInt();
            sc.nextLine();

            if(number==3){

                break;

            }

            switch (number){
                case 1:

                    //for exporting all the orders to the report
                    //sorting based on the order id and then generating the report
                    ArrayList<Order> array = Order.arr;
                    array.sort(Comparator.comparing(Order::getOrderId));

                    //method for generating report
                    writeToFile(array);
                    break;

                case 2:
                    //based on status
                    System.out.println("-------------Choose Report Generation Option-----------");
                    System.out.println("1--> In Progress");
                    System.out.println("2--> Delivered");
                    System.out.println("3--> Cancelled");

                    int choice = sc.nextInt();
                    sc.nextLine();
                    //for storing the orders based on the user input
                    ArrayList<Order> arrayByStatus;
                    switch (choice){

                        case 1:

                            arrayByStatus=sortBasedOnStatus(Order.arr,"In Progress");

                            writeToFile(arrayByStatus);

                            break;

                        case 2:

                            arrayByStatus = sortBasedOnStatus(Order.arr,"Delivered");

                            writeToFile(arrayByStatus);

                            break;

                        case 3:

                            arrayByStatus = sortBasedOnStatus(Order.arr,"Cancelled");

                            writeToFile(arrayByStatus);

                            break;

                        default:
                            System.out.println("Enter a correct value.");
                    }
                    break;

                default:
                    System.out.println("enter a proper value.");
            }
        }
    }

    //sorting based on the user requirement which is passed in sort method
    private ArrayList<Order> sortBasedOnStatus(ArrayList<Order> arr, String str) {

        ArrayList<Order> array = new ArrayList<>();

        for(Order order: arr){

            if(order.getStatus().trim().equals(str)){

                array.add(order);
            }
        }
        //sorting based on order id
        array.sort(Comparator.comparing(Order::getOrderId));

        return array;
    }

    //thread operation for writing to file
    private void writeToFile(ArrayList<Order> array){

        FileWriting fw = new FileWriting(array);
        //starting the thread
        fw.start();

        try {
            //waiting for the process to complete

            fw.join();

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }
    }
}