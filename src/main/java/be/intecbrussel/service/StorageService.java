package be.intecbrussel.service;

import be.intecbrussel.model.Key;
import be.intecbrussel.model.Product;
import be.intecbrussel.model.Storage;
import be.intecbrussel.repository.StorageRepository;

import java.util.ArrayList;
import java.util.List;

public class StorageService {

    private final StorageRepository storageRepository = new StorageRepository();
    private final ProductService productService = Service.getProductService();

    public void addStorage(Storage storage) {
        for (Product product : storage.getProducts()) {
            System.out.println(product);
            if (product.getId() == 0) {
                productService.addProduct(product);
            } else {
                productService.updateProduct(product);
            }
        }

        if (storage.getId() == 0)
            storageRepository.createStorage(storage);
        else
            storageRepository.updateStorage(storage);
    }

    public Storage getStorage(long id) {
        Storage storage = storageRepository.readStorage(id);
        if (storage == null) {
            throw new RuntimeException("Storage with ID " + id + " not found.");
        }
        return storage;
    }


    public void updateStorage(Storage storage) {
        for (Product product : storage.getProducts()) {
            System.out.println(product);
            if (product.getId() == 0) {
                productService.addProduct(product);
            } else {
                productService.updateProduct(product);
            }
        }

        if (storage.getId() == 0)
            storageRepository.createStorage(storage);
        else
            storageRepository.updateStorage(storage);
    }

    public void deleteStorage(long id) {
        KeyService keyService = Service.getKeyService();
        Storage storage = getStorage(id);
        Key key = storage.getKey();
        keyService.deleteKey(key.getId());

        List<Product> products = storage.getProducts();

        for (Product product : products) {
            productService.deleteProduct(product.getId());
        }

        storageRepository.deleteStorage(id);
    }

    public void removeFromStorage(Product product) {
        Storage storage = storageRepository.readStorage(product);
        storage.setProducts(new ArrayList<>(storage.getProducts()));
        storage.getProducts().remove(product);
        updateStorage(storage);
    }

}
