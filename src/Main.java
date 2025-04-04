import java.util.InputMismatchException;
import java.util.*;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InputMismatchException{

        OrderManagement im = new ImplementedClass();

        /*
          Menu for Order management system
         */

        outer: while(true){

            System.out.println("--------------------------------------");
            System.out.println("------Order Management System---------");
            System.out.println("--------------MENU--------------------");
            System.out.println("--------------------------------------");

            System.out.println("1--> Add Order");

            System.out.println("2--> View Order");

            System.out.println("3--> View By Order ID");

            System.out.println("4--> Sort Order");

            System.out.println("5--> Delete Order");

            System.out.println("6--> Mark as Delivered");

            System.out.println("7--> Cancel Order");

            System.out.println("8--> Generate Report");

            System.out.println("9--> Update the Delivery address");

            System.out.println("10--> Exit");

            System.out.println("Enter your choice");

            int input;

            //for validating the input of user
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));

            //for iterating through the input until it gets a correct value
            while(true) {
                //for handling the input mismatch exception
                // so if a user enters any other value then it will continue again
                try {

                    input = sc.nextInt();
                    sc.nextLine();
                    if(arr.contains(input)){
                        break;
                    }
                    else {
                        System.out.println("Please select from the above option only!..");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Please select from the above option only!..");

                    sc.nextLine();
                    //continue;

                }
            }

            switch (input){

                case 1:
                    orderOuter: while(true){
                        System.out.println();
                        im.addOrder();

                        System.out.println("Do you want to enter more order details(Y/N)");
                        while(true) {
                            char ch = sc.nextLine().charAt(0);
                            if (ch == 'n' || ch == 'N') {
                                System.out.println("\n\n");
                                break orderOuter;
                            } else if (!(ch == 'y' || ch == 'Y')) {
                                System.out.println("Enter either Y/N..");
                            }else{
                                continue orderOuter;
                            }
                        }
                    }


                    break;


                case 2:
                    System.out.println("\n");
                    im.viewOrder();
                    break;


                case 3:
                    //for displaying user if he enters more incorrect values
                    int count=0;

                    while(true) {

                        System.out.println("\nEnter a order id:");
                        int id = ImplementedClass.takeOrderID();
                        //formatting input into order id form i.e ORD-0000 form
                        String ordId = "ORD-"+String.format("%04d",id);

                        if (containsIgnoreCase(Order.hs,ordId)) {
                            System.out.println();
                            im.viewOrder(ordId);
                            break;
                        }else{

                            count++;
                            if(count<=1) {

                                System.out.println("Enter a valid Order ID ");

                            } else if(count<3){

                                System.out.println("Please check the available Order id here and then enter orderID:");
                                System.out.println("-------Available order id--------");
                                for(String s: Order.hs){

                                    System.out.println(s);

                                }
                                count++;
                            } else{

                                System.out.println("Multiple Incorrect order id is entered \n Going back to the main menu.");
                                break;
                            }

                        }
                    }
                    break;

                case 4:
                    im.sortOrder();
                    break;

                case 5:
                    im.deleteOrder();
                    break;

                case 6:
                    System.out.println();
                    im.markDelivered();
                    break;

                case 7:
                    im.cancelOrder();
                    break;

                case 8:
                    im.generateReport();
                    break;

                case 9:
                    im.updateDeliveryAddress();
                    break;

                case 10:
                    im.exit();
                    break outer;

                default:
                    System.out.println("Please select from the above option only!..");
            }

        }

    }

    private static boolean containsIgnoreCase(HashSet<String> hs, String ordId) {
        //for checking order id ignoring case for view order by id
        for(String s: hs){

            if(s.equalsIgnoreCase(ordId)) {

                return true;
            }

        }
        return false;
    }



}