package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class Bill {
    @Printable(header = "Id")
    private int id;
    @Printable(header = "Type")
    private String type;
    @Printable(header = "Amount")
    private int amount;
    @Printable(header = "Due date")
    private LocalDate dueDate;
    @Printable(header = "State")
    private String state;
    @Printable(header = "Provider")
    private ProviderEnum provider;

    public Bill(int id, String type, int amount, LocalDate dueDate, String state, ProviderEnum provider) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = state;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ProviderEnum getProvider() {
        return provider;
    }

    public void setProvider(ProviderEnum provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
