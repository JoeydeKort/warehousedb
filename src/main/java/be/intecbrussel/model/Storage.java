package be.intecbrussel.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "storage")
    private List<Product> products;

    protected Storage() {}

    public Storage(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public long getId() {return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        for (Product product : products) {
            if (product.getStorage() != this) {
                product.setStorage(this);
            }
        }
    }

    private void addProduct(Product product){
        this.products.add(product);
        product.setStorage(this);
    }

    public void addProduct(Product... products){
        for (Product product : products) {
            addProduct(product);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return id == storage.id && Objects.equals(name, storage.name) && Objects.equals(products, storage.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

}
