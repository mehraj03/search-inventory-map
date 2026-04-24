package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class SearchInventoryMap {
    public static void main(String[] args) {
        HashMap<String, Product> inventory = loadInventory();
        Scanner scanner = new Scanner(System.in);

        String again = "yes";
        while (again.equalsIgnoreCase("yes")) {
            System.out.print("Enter the product name to seach for: ");
            String SeachName = scanner.nextLine();

            Product found = inventory.get(SeachName);

            if (found != null) {
                System.out.printf("Found! SKU: %d NAME: %s Price: $%.2f%n",
                        found.getSku(), found.getName(), found.getPrice());
            } else {
                System.out.println("Product not found: " + SeachName);
            }
            System.out.println("Do you want to search again? (yes/no): ");
            again = scanner.nextLine();
        }

        System.out.println("Goodbye!");
    }
        public static HashMap<String, Product> loadInventory() {
            HashMap<String, Product> inventory = new HashMap<String, Product>();
            String fileName = "src/main/resources/inventory.csv";
            try {
                FileReader reader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line = bufferedReader.readLine();
                while (line !=null) {
                    String[] tokens = line.split("\\|");
                    int sku = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    double price = Double.parseDouble(tokens[2]);
                    Product product = new Product(sku,name,price);
                    inventory.put(name, product);
                    line = bufferedReader.readLine();

                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                System.err.println("couyldnt find the file: " + fileName);
            } catch (IOException e) {
                System.err.println("IO exception while reqding the file.");
            }
            return inventory;
        }

}
