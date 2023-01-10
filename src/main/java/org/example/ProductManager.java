package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManager {
    private ArrayList<Product> productManager;

    public ProductManager() {
        this.productManager = new ArrayList<>();
    }

    public ArrayList<Product> getProductManager() {
        return productManager;
    }

    public void displayProductManager() {
        if (!productManager.isEmpty()) {
            for (int i = 0; i < productManager.size(); i++) {
                System.out.println(productManager.get(i));
            }
        } else {
            System.out.println("Không có sản phẩm nào!");
        }
    }
    public Product creatProduct(Scanner scanner) {
        System.out.println("Thông tin sản phẩm mới: ");
        int id = -1;
        String name = null;
        int price = -1;
        int quantity = -1;
        String description = null;
        try {
            System.out.println("Nhập mã sản phẩm muốn thêm: ");
            id = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập tên sản phẩm: ");
            name = scanner.nextLine();
            System.out.println("Nhập giá sản phẩm: ");
            price = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập số lượng sản phẩm: ");
            quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập mô tả của sản phẩm: ");
            description = scanner.nextLine();
        }
        catch (IndexOutOfBoundsException|NumberFormatException e) {
            e.printStackTrace();
            creatProduct(scanner);
        }
        return new Product(id, name, price, quantity, description);
    }
    public void addProduct(Scanner scanner) {
        productManager.add(creatProduct(scanner));
    }
    public void deleteById(Scanner scanner) {
        int indexToDelete = searchIdOfProduct(scanner);
        if (indexToDelete != -1) {
            productManager.remove(indexToDelete);
            System.out.println("Danh sách sản phẩm sau khi xóa!");
            displayProductManager();
        }
    }
    public int searchIdOfProduct(Scanner scanner) {
        System.out.println("Nhập id sản phầm:");
        int index = -1;
        int idToSearch = -1;
        try {
            idToSearch = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i).getId() == idToSearch) {
                index = i;
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Không tìm được sản phẩm với mã sản phẩm trên.");
            searchIdOfProduct(scanner);
        }
        return index;
    }
    public void updateProductById(Scanner scanner) {
        int indexToRewrite = searchIdOfProduct(scanner);
        if (indexToRewrite != -1) {
            System.out.println("Thông tin sản phẩm muốn sửa: ");
            for (int i = 0; i < productManager.size(); i++) {
                if (i == indexToRewrite) {
                    try {
                        System.out.println("Nhập mã id sản phẩm muốn sửa: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        productManager.get(i).setId(id);
                        System.out.println("Nhập tên mới cho sản phẩm: ");
                        String name = scanner.nextLine();
                        productManager.get(i).setName(name);
                        System.out.println("Nhập giá mới cho sản phẩm: ");
                        int price = Integer.parseInt(scanner.nextLine());
                        productManager.get(i).setPrice(price);
                        System.out.println("Nhập số lượng mới cho sản phẩm: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        productManager.get(i).setQuantity(quantity);
                        System.out.println("Nhập mô tả mới cho sản phẩm: ");
                        String description = scanner.nextLine();
                        productManager.get(i).setDescription(description);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            displayProductManager();

        }
    }
        public void displayProductByMaxPrice() {
            int indexMax = 0;
            System.out.println("Sản phẩm có giá đắt nhất: ");
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getPrice() > productManager.get(indexMax).getPrice()) {
                    indexMax = i;
                }
            }
            System.out.println(productManager.get(indexMax));
        }
    public void displayByPriceUp() {
        System.out.println("Sắp xếp sản phẩm tăng dần: ");
        productManager.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        });
        displayProductManager();
    }
    public void read(File file){
        Product product = null;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                String[] strings = text.split(",");
                product = new Product(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]),
                        Integer.parseInt(strings[3]), strings[4]);
                productManager.add(product);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(File file){
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Product product: productManager){
                String write = product.getId() + "," + product.getName() + "," + product.getPrice() + ","
                        + product.getQuantity() + ","
                        + product.getDescription() + "\n";
                bufferedWriter.write(write);
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
