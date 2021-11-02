package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter cliente data: ");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Birth date (DD/MM/YYYY): ");
        Date date = sdf.parse(sc.next());

        Client client = new Client(name, email, date);
        System.out.println("Enter order data:");
        System.out.print("Status: ");
        String status = sc.next();
        System.out.print("How many items to this order: ");
        int items = sc.nextInt();

        Order order = new Order(new Date(),OrderStatus.valueOf(status),client);
        OrderItem ordemItem;
        for (int i = 1; i<=items; i++){
            System.out.println("Enter #"+ i + " item data:");
            System.out.print("Product name: ");
            String productName = sc.next();
            System.out.print("Product price: ");
            Double productPrice = sc.nextDouble();
            System.out.print("Quantity: ");
            int quantity = sc.nextInt();

            ordemItem = new OrderItem(quantity,productPrice, new Product(productName, productPrice));
            order.addItem(ordemItem);
        }
        System.out.println();
        System.out.println("ORDER SUMMARY: ");
        System.out.println(order);
        System.out.println("Order items: ");

        for (OrderItem oi : order.getList()){
            System.out.printf("%s, $%.2f, Quantity: %d, Subtotal: $%.2f \n",oi.getProduct().getName(),
                    oi.getPrice(),oi.getQuantity(),oi.subTotal());
        }

        System.out.printf("Total price: $%.2f", order.total());



    }
}
