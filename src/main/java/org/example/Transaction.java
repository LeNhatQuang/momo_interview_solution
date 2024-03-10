package org.example;

import java.time.LocalDate;

public class Transaction {
    private LocalDate paymentDate;
    private Bill bill;

    public Transaction(LocalDate paymentDate, Bill bill) {
        this.paymentDate = paymentDate;
        this.bill = bill;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
