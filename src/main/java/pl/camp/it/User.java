package pl.camp.it;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@Column(unique = true)
    private String name;
    //@Column(name = "nazwisko")
    private String surname;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public User(int id, String name, String surname, Sex sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public enum Sex {
        MEN,
        WOMEN,
        OTHER
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", orders=" + orders +
                '}';
    }
}
