package be.intecbrussel.service;

import be.intecbrussel.model.Product;
import be.intecbrussel.repository.ProductRepository;


public class ProductService {


    private final ProductRepository productRepository = new ProductRepository();

    public void addProduct(Product product) {
        productRepository.createProduct(product);
    }

    public Product getProduct(long id) {
        return productRepository.readProduct(id);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(long id) {
        StorageService storageService = Service.getStorageService();
        storageService.removeFromStorage(getProduct(id));
        productRepository.deleteProduct(id);
    }

}
