package pl.camp.it;

import javax.persistence.*;

@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double suma;

    public Order(int id, double suma) {
        this.id = id;
        this.suma = suma;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }
}
