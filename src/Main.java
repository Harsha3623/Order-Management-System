import java.util.InputMismatchException;
import java.util.*;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        OrderManagement im= new ImplementedClass();

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

            System.out.println("9--> Exit");

            System.out.println("Enter your choice");

            int input;
            //for handling the input mismatch exception
            // so if a user enters any other value then it will continue again
            try {

                input = sc.nextInt();
                sc.nextLine();

            }catch (InputMismatchException e){

                System.out.println("Enter a proper value");
                sc.nextLine();
                continue;

            }

            switch (input){

                case 1:
                    im.addOrder();
                    break;


                case 2:
                    im.viewOrder();
                    break;


                case 3:
                    //for displaying user if he enters more incorrect values
                    int count=0;

                    while(true) {

                        System.out.println("Enter a order id:");
                        String ordId = sc.nextLine();
                        if (containsIgnoreCase(Order.hs,ordId)) {

                            im.viewOrder(ordId);
                            break;
                        }else{

                            count++;
                            if(count<=2) {

                                System.out.println("Enter a valid Order ID ");

                            }else if(count<4){

                                System.out.println("Please check the available Order id here and then enter a proper orderID:");
                                System.out.println("-------Available order id--------");
                                for(String s: Order.hs){

                                    System.out.println(s);

                                }
                                count++;
                            } else{

                                System.out.println("Multiple Incorrect order id is entered \n Going back to the main menu.");
                                break;
                            }
                            //continue;
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
                    im.markDelivered();
                    break;

                case 7:
                    im.cancelOrder();
                    break;

                case 8:
                    im.generateReport();
                    break;

                case 9:
                    im.exit();
                    break outer;

                default:
                    System.out.println("Please enter a valid number in the menu!...");
            }
        }

    }

    private static boolean containsIgnoreCase(HashSet<String> hs, String ordId) {
        //for checking order id ignoring case for view order by id
        for(String s: hs){

            if(s.equalsIgnoreCase(ordId)){
                return true;
            }

        }
        return false;
    }

}