package com.spectacles.spectacles.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private double total;

    // getters + setters
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}