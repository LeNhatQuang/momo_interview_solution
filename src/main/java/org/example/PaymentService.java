package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PaymentService {
    private final AtomicInteger balance = new AtomicInteger();
    private final Set<Bill> bills;
    private final List<Transaction> transactions;
    private final List<Schedule> schedules;

    public PaymentService() {
        transactions = new ArrayList<>();
        bills = new HashSet<>();
        schedules = new ArrayList<>();
    }

    public void addFunds(int amount) {
        System.out.println("Your available balance: " + balance.addAndGet(amount));
    }

    public void addBill(Bill bill) {
        if(!bills.add(bill)) {
            System.out.println("Bill with id " + bill.getId() + " already exists.");
        }
    }

    public void listBills() {
        Printer.getInstance().printList(bills);
    }

    public void payBills(List<Integer> ids) {
        LinkedList<Bill> selectedBills = bills.stream()
                .filter(bill -> ids.contains(bill.getId()))
                .sorted(Comparator.comparing(Bill::getDueDate))
                .collect(Collectors.toCollection(LinkedList::new));

        ids.stream().filter(id -> selectedBills.stream().noneMatch(bill -> bill.getId() == id))
                .forEach(id -> System.out.println("Sorry! Not found a bill with id " + id));

        selectedBills.stream()
            .filter(bill -> !bill.getState().equals("PAID"))
            .forEach(bill -> {
                if (bill.getAmount() <= balance.get()) {
                    bill.setState("PAID");
                    transactions.add(new Transaction(LocalDate.now(), bill));
                    balance.addAndGet(-bill.getAmount());
                    System.out.println("Payment has been completed for Bill with id " + bill.getId() + ". Your current balance is: " + balance);
                } else {
                    System.out.println("Sorry! Not enough fund to proceed with payment.");
                }
            });
    }

    public void searchBillsByProvider(ProviderEnum provider) {
        bills.stream()
                .filter(bill -> bill.getProvider() == provider)
                .forEach(bill -> System.out.println(billToString(bill)));
    }

    public void showTransactionHistory() {
        transactions.stream()
                .sorted(Comparator.comparing(Transaction::getPaymentDate))
                .forEach(transaction -> System.out.println(DateUtil.format(transaction.getPaymentDate()) + " " + billToString(transaction.getBill())));
    }

    public void schedule(Schedule schedule) {
        if (bills.stream().noneMatch(bill -> bill.getId() == schedule.getBillId())) {
            System.out.println("Sorry! Not found a bill with id " + schedule.getBillId());
            return;
        }

        bills.stream()
            .filter(bill -> bill.getState().equals("PAID"))
            .findFirst()
            .ifPresent(bill -> System.out.println("Sorry! Bill with id " + schedule.getBillId() + " has already been paid."));

        schedules.add(schedule);
        System.out.println("Bill with id " + schedule.getBillId() + " has been scheduled for payment on " + DateUtil.format(schedule.getScheduledPaymentDate()));
    }

    public int getBalance() {
        return balance.get();
    }

    private String billToString(Bill bill) {
        StringBuilder builder = new StringBuilder();
        builder.append(bill.getId())
            .append(". ")
            .append(bill.getType())
            .append(" ")
            .append(bill.getAmount())
            .append(" ")
            .append(bill.getDueDate())
            .append(" ")
            .append(bill.getState())
            .append(" ")
            .append(bill.getProvider());
        return builder.toString();
    }
}
