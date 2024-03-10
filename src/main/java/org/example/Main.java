package org.example;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");
            String[] input = scanner.nextLine().split(" ");
            String command = input[0].toUpperCase();

            switch (command) {
                case "CASH_IN":
                    int fund = Integer.parseInt(input[1]);
                    paymentService.addFunds(fund);
                    break;
                case "ADD_BILL":
                    try {
                        int id = Integer.parseInt(input[1]);
                        String type = input[2];
                        int amount = Integer.parseInt(input[3]);
                        String date = input[4];
                        String state = input[5];
                        String provider = input[6];
                        Bill bill = new Bill(id, type, amount, DateUtil.parse(date), state, ProviderEnum.valueOf(provider));
                        paymentService.addBill(bill);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid bill id! Please enter a valid integer.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format! Please use the format: dd/MM/yyyy");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid provider! Please enter a valid provider: EVN_HCMC, SAVACO_HCMC, VIETTEL_POST");
                    } catch (Exception e) {
                        System.out.println("Invalid command format!");
                    }
                    break;
                case "LIST_BILL":
                    paymentService.listBills();
                    break;
                case "PAY":
                    List<Integer> billIds = Arrays.stream(Arrays.copyOfRange(input, 1, input.length))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toList());
                    paymentService.payBills(billIds);
                    break;
                case "LIST_PAYMENT":
                    paymentService.showTransactionHistory();
                    break;
                case "SEARCH_BILL_BY_PROVIDER":
                    try {
                        ProviderEnum provider = ProviderEnum.valueOf(input[1]);
                        paymentService.searchBillsByProvider(provider);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid provider! Please enter a valid provider: EVN_HCMC, SAVACO_HCMC, VIETTEL_POST");
                    }
                    break;
                case "SCHEDULE":
                    try {
                        int billId = Integer.parseInt(input[1]);
                        String date = input[2];
                        Schedule schedule = new Schedule(billId, DateUtil.parse(date));
                        paymentService.schedule(schedule);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid bill id! Please enter a valid integer.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format! Please use the format: dd/MM/yyyy");
                    } catch (Exception e) {
                        System.out.println("Invalid command format!");
                    }
                    break;
                case "EXIT":
                    System.out.println("Good bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid command!");
            }
        }

    }
}