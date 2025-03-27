package com.mynormogue.system;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.mynormogue.bean.BST;
import com.mynormogue.bean.Product;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Hello world!
 * 
 * @author mmogye - 241257
 */
public class App {
    public static void main(String[] args) {
        BST<Product> bst = new BST<>();
        Scanner sc = new Scanner(System.in);
        boolean flag_menu = true;

        // Cargar datos desde CSV
        loadProductsFromCSV(bst, "data.csv");

        while (flag_menu) {
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Buscar producto por SKU");
            System.out.println("2. Listar productos (orden ascendente por precio)");
            System.out.println("3. Listar productos (orden descendente por precio)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    searchProductBySKU(bst, sc);
                    break;
                case 2:
                    listProductsInOrder(bst, true); // Ascendente
                    break;
                case 3:
                    listProductsInOrder(bst, false); // Descendente
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    sc.close();
                    flag_menu = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Método para cargar datos desde CSV
    private static void loadProductsFromCSV(BST<Product> bst, String filePath) {
        // Cargar datos desde CSV
        try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
            String[] nextLine;
            reader.readNext(); // Saltar cabecera
            int total_product = 0;
            while ((nextLine = reader.readNext()) != null) {
                Product product = new Product(
                        nextLine[6], // SKU
                        Double.parseDouble(!nextLine[9].isEmpty() ? nextLine[9] : String.valueOf(0)), // PRICE_RETAIL
                        Double.parseDouble(!nextLine[10].isEmpty() ? nextLine[10] : String.valueOf(0)), // PRICE_CURRENT
                        nextLine[18], // PRODUCT_NAME
                        nextLine[0] // CATEGORY
                );
                bst.insert(product);
                total_product++;
            }
            System.out.println("Datos cargados exitosamente desde el CSV. ["+total_product+"]");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar producto por SKU
    private static void searchProductBySKU(BST<Product> bst, Scanner scanner) {
        System.out.print("Ingrese el SKU del producto a buscar: ");
        String sku = scanner.nextLine().trim();

        Product query = new Product(sku, 0, 0, "", ""); // SKU es el campo clave
        Product found = bst.search(query);

        if (found != null) {
            System.out.println("\nProducto encontrado:");
            System.out.println(found);
        } else {
            System.out.println("Producto con SKU '" + sku + "' no encontrado.");
        }
    }

    // Método para listar productos (orden ascendente o descendente)
    private static void listProductsInOrder(BST<Product> bst, boolean ascending) {
        List<Product> products = ascending ? bst.inOrder() : bst.inOrderDesc();

        System.out.println("\nLista de productos (" + (ascending ? "ascendente" : "descendente") + "):");
        System.out.println("--------------------------------------------------");
        products.forEach(System.out::println);
        System.out.println("Total de productos: " + products.size());
    }
}
