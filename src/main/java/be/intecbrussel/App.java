package be.intecbrussel;

import be.intecbrussel.model.Product;
import be.intecbrussel.model.Storage;
import be.intecbrussel.service.ProductService;
import be.intecbrussel.service.Service;
import be.intecbrussel.service.StorageService;


public class App {

    public static void main(String[] args) {

        //m1SimpleEntity();
        m2StorageEntity();
    }

    private static void m1SimpleEntity() {

        ProductService productService = Service.getProductService();
        Product apple = new Product("apple", 0.75);
        productService.addProduct(apple);

        Product product = productService.getProduct(1);
        System.out.println(product);

        apple.setName("Red apple");
        productService.updateProduct(apple);

        Product product1 = productService.getProduct(1);
        System.out.println(product1);

        productService.deleteProduct(1);

        Product product2 = productService.getProduct(1);
        System.out.println(product2);

    }

    private static void m2StorageEntity() {

        StorageService storageService = Service.getStorageService();

        Storage storage = new Storage("Fruits");
        storageService.addStorage(storage);

        Product product01 = new Product("Banana", 0.55);
        Product product02 = new Product("apple", 0.65);
        Product product03 = new Product("Melon", 1.35);

        storage.addProduct(product01, product02, product03);
        storageService.updateStorage(storage);

    }

}
