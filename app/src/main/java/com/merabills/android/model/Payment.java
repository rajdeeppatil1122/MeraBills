package com.merabills.android.model;

import java.io.Serializable;

public class Payment implements Serializable {
    private final String type;
    private final double amount;
    private final String provider;
    private final String reference;

    public Payment(String type, double amount, String provider, String reference) {
        this.type = type;
        this.amount = amount;
        this.provider = provider;
        this.reference = reference;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getProvider() { return provider; }
    public String getReference() { return reference; }
}
