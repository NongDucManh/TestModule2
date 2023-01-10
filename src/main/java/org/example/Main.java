package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Admin\\IdeaProjects\\Module2_Test\\src\\main\\java\\org\\example\\Data\\products.csv");
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        menuProductManager( productManager, scanner, file);
    }

    public static void menuProductManager(ProductManager productManager, Scanner scanner, File file) {
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("9. Thoát");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    productManager.displayProductManager();
                    break;
                case 2:
                    productManager.addProduct(scanner);
                    break;
                case 3:
                    productManager.updateProductById(scanner);
                    break;
                case 4:
                    productManager.deleteById(scanner);
                    break;
                case 5:
                    productManager.displayByPriceUp();
                    break;
                case 6:
                    productManager.displayProductByMaxPrice();
                    break;
                case 7:
                    productManager.read(file);
                    break;
                case 8:
                    productManager.write(file);
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Enter choice: 0~9");
            }
        }
            while (true) ;
        }

}