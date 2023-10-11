package be.intecbrussel.service;

public class Service {

    private static ProductService productService;
    private static StorageService storageService;

    static {
        productService = new ProductService();
        storageService = new StorageService();
    }

    public static ProductService getProductService() {
        return productService;
    }

    public static StorageService getStorageService() {
        return storageService;
    }
}
