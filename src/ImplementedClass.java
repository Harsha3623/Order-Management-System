import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ImplementedClass  implements OrderManagement{

    //Scanner class
    static Scanner sc = new Scanner(System.in);

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
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|   Order Id    |      Order Description       |        Delivery Address           |       Order Date        |       Amount       |      Delivery Date      |   Status  |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

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
            int id = takeOrderID();
            String ordId = "ORD-"+String.format("%04d",id);
            if(Order.hs.contains(ordId)){

                for(Order order: Order.arr){

                    //ifOrder Id equals to array list order id
                    if(order.getOrderId().trim().equals(ordId)){

                        if((order.getStatus().trim()).equals("Cancelled")){

                            System.out.println("\nThe order"+order.getOrderId()+" cannot be delivered it is cancelled by the user ..");
                            break;

                        }else if((order.getStatus().trim()).equals("Delivered")){

                            //Order is already delivered to the user
                            System.out.println("\nThe order"+order.getOrderId()+" is already delivered on "+order.getDeliveryDate()+" and cannot deliver again.");
                            break;

                        }else {

                            //else deliver the order to the customer
                            order.setStatus("Delivered");
                            LocalDateTime curDate = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss a");
                            String currentDate = (curDate.format(formatter)).toUpperCase();
                            order.setDeliveryDate(currentDate);

                            System.out.println("\nThe order "+order.getOrderId()+" is successfully delivered to customer..");
                            break;
                        }
                    }
                }
                System.out.println("\n\nDo you want to mark another Order as Delivered(Y/N)");

                while(true) {

                    char ch = sc.nextLine().charAt(0);
                    if (ch == 'Y' || ch == 'y') {

                        continue outer;

                    } else if (ch == 'n' || ch == 'N') {

                        break outer;

                    } else {

                        System.out.println("\nplease enter either Yes or no option..");
                    }
                }
            }else if(count<2){

                System.out.println("\nThe order id is not present in the orders. enter valid order id.");
                count++;

            }else if(count<3){

                System.out.println("\nPlease check the available order id for delivering the order:");

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

            System.out.println("\nEnter valid order id: ");
            int id = takeOrderID();
            //formatting the input into required order id format
            String orderId = "ORD-"+String.format("%04d",id);

            if(Order.hs.contains(orderId)){

                for(Order order: Order.arr){

                    if((order.getOrderId().trim()).equals(orderId)){
                        //what is the order is delivered
                        //need to print the order is delivered
                        if((order.getStatus().trim()).equals("Delivered")){

                            System.out.println("\nThe order "+order.getOrderId()+" is delivered on "+order.getDeliveryDate()+" and cannot be cancelled.");
                        }
                        else if((order.getStatus().trim()).equals("In Progress")){

                            order.setStatus("Cancelled");
                            //String str = null;
                            //updating from null to NA
                            order.setDeliveryDate("NA");
                            System.out.println("\nThe order "+order.getOrderId()+" cancelled successfully.");

                        }else {

                            //if order is cancelled already by the user
                            System.out.println("\nOrder "+order.getOrderId()+" has been already cancelled.");

                        }
                    }
                }

                while(true){

                    System.out.println("\n\nDo you want to cancel another Order(Y/N)");
                    char ch = sc.nextLine().charAt(0);

                    if(ch=='Y'||ch=='y'){

                        continue outer;

                    }else if(ch=='n'||ch=='N'){

                        break outer;

                    }else {

                        System.out.println("\nPlease enter either yes(Y) or NO(N) option.");

                    }
                }
            }else if(count<2){
                System.out.println("\nThe order id is not present in the orders. enter valid order id.");
                count++;
            }else if(count<3){

                System.out.println("\nPlease check the available order id for Canceling the order:");
                System.out.println("-------------Available order id-----------");
                for (String s: Order.hs){
                    System.out.println(s);
                }
                System.out.println("\n");
                count++;
            }else {
                System.out.println("\nMultiple incorrect  order id is entered \nGoing back to main menu\n\n");
                break;
            }
        }
        System.out.println("\n\n");
    }

    @Override
    public void deleteOrder() {
        int count=0;

        outer: while (true){

            System.out.println("\nEnter a valid order id for deleting the order:");
            int id = takeOrderID();
            String orderId = "ORD-"+String.format("%04d",id);

            if(Order.hs.contains(orderId)){

                for(Order order: Order.arr){

                    if((order.getOrderId().trim()).equals(orderId)) {
                        //double authentication while deleting the order details

                        while(true) {
                            System.out.println("\nConfirm to Delete the order Details(y/n)");
                            char cnfrm = sc.nextLine().charAt(0);

                            if (cnfrm == 'y' | cnfrm == 'Y') {

                                Order.arr.remove(order);
                                System.out.println("The order "+orderId+" has been removed successfully");
                                //need to remove the order id from hash set for better usage after deleting
                                //in the array list
                                Order.hs.remove(orderId);
                                break;

                            } else if (cnfrm == 'n' | cnfrm == 'N') {
                                System.out.println("The oder "+orderId+" is not deleted");
                                break;

                            } else {
                                System.out.println("Enter either yes or no.");
                            }
                        }
                        break;
                    }
                }
                while (true){

                    System.out.println("\n\nDo you want to delete another order(Y/N)");
                    char ch = sc.nextLine().charAt(0);
                    if(ch=='Y' | ch=='y'){
                        continue outer;

                    } else if (ch=='N'| ch=='n') {

                        break outer;

                    }else {

                        System.out.println("\nEnter either yes or no option..");
                    }
                }
            }else if(count<2){

                System.out.println("\nThe order id is not present in the orders.");
                count++;

            }else if(count<3){

                System.out.println("\nPlease check the available order id for deleting the order details:");
                System.out.println("-------------Available order id-----------");
                for (String s: Order.hs){
                    System.out.println(s);
                }
                count++;

            }else {

                System.out.println("\nMultiple incorrect  order id is entered \nGoing back to main menu\n\n");
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

        outer: while(true) {

            System.out.println("----------Chose Sort Order property---------");
            System.out.println("1--> OrderID");
            System.out.println("2--> Order Description");
            System.out.println("3--> Delivery Address");
            System.out.println("4--> Order Date");
            System.out.println("5--> Amount");
            System.out.println("6--> Delivery Date Time ");
            System.out.println("7--> Exit");

            ArrayList<Integer> numbers= new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
            int number;

            while(true) {
                try {

                    number = sc.nextInt();
                    sc.nextLine();
                    if(numbers.contains(number)){
                        if(number==7){
                            break outer;
                        }
                        break;
                    }else {
                        System.out.println("Please enter above options only.");
                    }

                } catch (InputMismatchException e) {

                    System.out.println("Please enter above options only.");
                    sc.nextLine();

                }
            }

            int ascOrDesc;

            System.out.println("----------Chose Sort Order property---------");
            System.out.println("1--> Ascending");
            System.out.println("2--> Descending");

            while(true) {

                //handling input mismatch exception
                try {

                    ascOrDesc = sc.nextInt();
                    sc.nextLine();

                    if(ascOrDesc==1 | ascOrDesc==2){

                        break;
                    }else {
                        System.out.println("Please enter above options only.");

                    }

                }catch (InputMismatchException e){

                    System.out.println("Please enter above options only.");
                    sc.nextLine();
                }
            }
            //creating the local array list for sorting purpose
            ArrayList<Order> array = Order.arr;

            //providing new line
            System.out.println("\n\n");
            switch (number){
                case 1:
                    //sort Based On user inputs;
                    if(ascOrDesc==1) {

                        array.sort(Comparator.comparing(Order::getOrderId));
                        System.out.println("Successfully sorted by order-id in Ascending order\n");
                    }else{

                        array.sort(Comparator.comparing(Order::getOrderId).reversed());
                        System.out.println("Successfully sorted by order-id in Descending order\n");
                    }

                    viewOrder(array);
                    break outer;

                case 2:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparing(Order::getOrderDescription));
                        System.out.println("Successfully sorted by Order Description in Ascending order\n");
                    }else {

                        array.sort(Comparator.comparing(Order::getOrderDescription).reversed());
                        System.out.println("Successfully sorted by Order Description in Descending order\n");
                    }

                    viewOrder(array);
                    break outer;

                case 3:

                    if(ascOrDesc==1){
                        System.out.println("Successfully sorted by Delivery address in Ascending order\n");
                        array.sort(Comparator.comparing(Order::getDeliveryAddress));

                    }else{

                        array.sort(Comparator.comparing(Order::getDeliveryAddress).reversed());
                        System.out.println("Successfully sorted by Delivery address in Descending order\n");
                    }

                    viewOrder(array);
                    break outer;

                case 4:

                    if(ascOrDesc==1){


                        array.sort(Comparator.comparing(Order::getOrderDateForSorting));

                        System.out.println("Successfully sorted by Order date in Ascending order\n");
                    }else{

                        array.sort(Comparator.comparing(Order::getOrderDateForSorting).reversed());

                        System.out.println("Successfully sorted by Order date in Descending order\n");
                    }

                    viewOrder(array);
                    break outer;

                case 5:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparingDouble(Order::getAmount));

                        System.out.println("Successfully sorted by Amount in Ascending order\n");
                    }else{

                        array.sort(Comparator.comparingDouble(Order::getAmount).reversed());

                        System.out.println("Successfully sorted by Amount in Descending order\n");
                    }

                    viewOrder(array);
                    break outer;

                case 6:

                    if(ascOrDesc==1){

                        array.sort(Comparator.comparing(Order::getDeliveryDateForSorting));
                        System.out.println("Successfully sorted by Delivery Date in Ascending order\n");

                    }else{

                        array.sort(Comparator.comparing(Order::getDeliveryDateForSorting).reversed());
                        System.out.println("Successfully sorted by Delivery Date in Descending order\n");

                    }

                    viewOrder(array);
                    break outer;

                default:
                    System.out.println("Enter a proper value ");

            }

        }
        System.out.println("\n\n");
    }

    @Override
    //view order for sorting option
    public void viewOrder(List<Order> array){

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|   Order Id    |      Order Description       |        Delivery Address           |       Order Date        |       Amount       |      Delivery Date      |   Status  |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Order order : array) {

            System.out.println(order.toString());

        }

        System.out.println("\n\n");
    }

    @Override
    public void generateReport() {

        outer: while(true) {

            System.out.println("-------------Choose Report Generation Option-----------");
            System.out.println("1--> Export All");
            System.out.println("2--> By Status");
            System.out.println("3-->exit");

            int number;
            while (true) {
                try {

                    number = sc.nextInt();
                    sc.nextLine();

                    if(number==1 | number==2 | number==3){
                        break;
                    }else {
                        System.out.println("Please enter the above options only..");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Please enter the above options only..");
                    sc.nextLine();

                }
            }
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
                    break outer;

                case 2:
                    //based on status
                    System.out.println("-------------Choose Report Generation Option-----------");
                    System.out.println("1--> In Progress");
                    System.out.println("2--> Delivered");
                    System.out.println("3--> Cancelled");
                    //handling exception and also iterating till user enter correct value
                    int choice ;
                    while(true) {
                        try {

                            choice = sc.nextInt();
                            sc.nextLine();
                            if(choice==1 | choice==2 | choice==3 ){
                                break;
                            } else {
                                System.out.println("Please enter the above options only..");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Please enter the above options only..");
                            sc.nextLine();
                        }
                    }
                    //for storing the orders based on the user input
                    ArrayList<Order> arrayByStatus;
                    switch (choice){

                        case 1:

                            arrayByStatus=sortBasedOnStatus(Order.arr,"In Progress");
                            //check whether it is empty or not

                                writeToFile(arrayByStatus);

                            break outer;

                        case 2:

                            arrayByStatus = sortBasedOnStatus(Order.arr,"Delivered");

                            //check whether it is empty or not

                                writeToFile(arrayByStatus);

                            break outer;

                        case 3:

                            arrayByStatus = sortBasedOnStatus(Order.arr,"Cancelled");

                            //check whether it is empty or not

                                writeToFile(arrayByStatus);

                            break outer;

                        default:
                            System.out.println("\nEnter above options only.");
                    }
                    //case 2 break
                    break;

                default:
                    System.out.println("Enter above options only.");
            }
        }
    }

    //sorting based on the user requirement which is passed in sort method
    private ArrayList<Order> sortBasedOnStatus(ArrayList<Order> arr, String str) {

        ArrayList<Order> array = new ArrayList<>();

        for(Order order: arr){

            if(order.getStatus().trim().equals(str)) {

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

    //updating the delivery address of the order
    @Override
    public void updateDeliveryAddress() {
        System.out.println("Enter a Order ID: (-1 to return to the main method)");
        String ordId;
        int count=1,input;
        //looping till update happens

        while(true) {
            //taking the user input

            input= takeOrderID();
            if(input==-1){
                return;
            }

            //format the orderID in the proper format for fetching the details
            ordId="ORD-"+String.format("%04d",input);
            //ordId = sc.nextLine();

            //checking whether it is present in the hashset
            if (Order.hs.contains(ordId)) {
                for(Order order: Order.arr){

                    if((order.getOrderId().trim()).equals(ordId)){

                        //reading the updated address from user and updating it
                        System.out.println("Enter Updated Delivery Address:");
                        String deliveryAddress = sc.nextLine();
                        if(deliveryAddress.trim().equals("exit")){
                            return;
                        }

                        order.setDeliveryAddress(deliveryAddress);
                        System.out.println("\nThe Address has been updated.\n");
                        viewOrder(ordId);

                    }
                }
                break;
            } else if(count<2){

                count++;
                System.out.println("Enter a valid order id");

            } else if (count<3) {

                count++;
                System.out.println("Available Order ID");

                for(String s: Order.hs){

                    System.out.println(s);

                }

                System.out.println("Enter a valid order id");

            }else{

                System.out.println("\nMultiple incorrect  order id is entered \nGoing back to main menu\n\n");
                break;
            }
        }

    }


    //taking the orderID from the user  and avoiding the exception raised when user enter string value
    public static int takeOrderID(){
        int id;

        while(true) {

            try {
                id = sc.nextInt();
                sc.nextLine();
                return id;

            } catch (InputMismatchException e) {

                System.out.println("Enter a valid order id");
                sc.nextLine();

            }
        }

    }
}