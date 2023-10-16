package be.intecbrussel;

import be.intecbrussel.model.Key;
import be.intecbrussel.model.Person;
import be.intecbrussel.model.Product;
import be.intecbrussel.model.Storage;
import be.intecbrussel.service.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class App {

    public static void main(String[] args) {

        ProductService productService = Service.getProductService();
        PersonService personService = Service.getPersonService();
        StorageService storageService = Service.getStorageService();
        KeyService keyService = Service.getKeyService();

        Storage storage1 = new Storage("Fruits");
        Storage storage2 = new Storage("Cars");
        storageService.addStorage(storage1);
        storageService.addStorage(storage2);

        Product product1 = new Product("Apple", 0.75);
        Product product2 = new Product("Banana", 0.69);
        Product product3 = new Product("Grapefruit", 1200);
        Product product4 = new Product("Ferrari", 100_000);
        storage1.addProduct(product1, product2, product3);

        productService.addProduct(product4);
        storage2.addProduct(product4);
        storageService.updateStorage(storage2);

        Key key1 = new Key(storage1);
        Key key2 = new Key(storage2);
        keyService.addKey(key1);
        keyService.addKey(key2);

        Person john = new Person("John");
        Person jane = new Person("Jane");
        personService.addPerson(john);
        personService.addPerson(jane);

        john.addKey(key1);
        jane.addKey(key1);
        jane.addKey(key2);
        personService.updatePerson(john);
        personService.updatePerson(jane);

//        storageService.deleteStorage(1);
        keyService.deleteKey(1);
//        productService.deleteProduct(1);
//        personService.deletePerson("John");

    }

}
