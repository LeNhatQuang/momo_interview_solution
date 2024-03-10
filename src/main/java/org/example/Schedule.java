package org.example;

import java.time.LocalDate;

public class Schedule {
    private int billId;
    private LocalDate scheduledPaymentDate;

    public Schedule(int billId, LocalDate parse) {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public LocalDate getScheduledPaymentDate() {
        return scheduledPaymentDate;
    }

    public void setScheduledPaymentDate(LocalDate scheduledPaymentDate) {
        this.scheduledPaymentDate = scheduledPaymentDate;
    }
}
