package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    long cbu;
    double amount;
    String type;

    public Transaction(Long _cbu, Double _amount, String _type) {
        this.cbu = _cbu;
        this.amount = _amount;
        this.type = _type;
    }

    public Transaction() {}
    public String getType() {
        return this.type;
    }
    public Long getId() { return this.id; }
    public Long getCbu() {
        return this.cbu;
    }
    public double getAmount() {
        return this.amount;
    }
}
