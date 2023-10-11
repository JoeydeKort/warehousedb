package be.intecbrussel.service;

import be.intecbrussel.model.Product;
import be.intecbrussel.model.Storage;
import be.intecbrussel.repository.StorageRepository;

import java.util.ArrayList;


public class StorageService {

    private StorageRepository storageRepository = new StorageRepository();

    public void addStorage(Storage storage) {
        ProductService productService = Service.getProductService();
        for (Product product : storage.getProducts()) {
            if (product.getId() == 0) {
                productService.addProduct(product);
            } else {
                productService.updateProduct(product);
            }
        }

        if (storage.getId() == 0 ) {
            storageRepository.createStorage(storage);
        } else {
            storageRepository.updateStorage(storage);
        }

    }

    public Storage getStorage(long id) {
        return storageRepository.readStorage(id);
    }

    public void updateStorage(Storage storage) {
        ProductService productService = Service.getProductService();
        for (Product product : storage.getProducts()) {
            if (product.getId() == 0) {
                productService.addProduct(product);
            } else {
                productService.updateProduct(product);
            }
        }

        if (storage.getId() == 0 ) {
            storageRepository.createStorage(storage);
        } else {
            storageRepository.updateStorage(storage);
        }
    }

    public void deleteStorage(long id) {
        storageRepository.deleteStorage(id);
    }

    public void removeFromStorage(Product product) {
        Storage storage = storageRepository.readStorage(product);
        storage.setProducts(new ArrayList<>(storage.getProducts()));
        storage.getProducts().remove(product);
        updateStorage(storage);
    }


}
