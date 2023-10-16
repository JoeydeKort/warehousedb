package be.intecbrussel.service;

public class Service {

    private static final ProductService productService;
    private static final StorageService storageService;
    private static final KeyService keyService;
    private static final PersonService personService;

    static {
        productService = new ProductService();
        storageService = new StorageService();
        keyService = new KeyService();
        personService = new PersonService();
    }

    public static ProductService getProductService() {
        return productService;
    }

    public static StorageService getStorageService() {
        return storageService;
    }

    public static KeyService getKeyService() {
        return keyService;
    }

    public static PersonService getPersonService() {
        return personService;
    }
}
