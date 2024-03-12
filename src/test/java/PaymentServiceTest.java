import org.example.Bill;
import org.example.DateUtil;
import org.example.PaymentService;
import org.example.ProviderEnum;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PaymentServiceTest {
    @Test
    public void testAddFunds() {
        PaymentService paymentService = new PaymentService();
        paymentService.addFunds(1000);
        assertEquals(1000, paymentService.getBalance());
    }

    @Test
    public void testPayBillSufficientFunds() {
        PaymentService paymentService = new PaymentService();
        paymentService.addFunds(1000);
        Bill bill = new Bill(1, "ELECTRIC", 500, DateUtil.parse("30/11/2020"), "NOT_PAID", ProviderEnum.EVN_HCMC);
        Bill bill1 = new Bill(2, "ELECTRIC", 300, DateUtil.parse("30/11/2020"), "NOT_PAID", ProviderEnum.SAVACO_HCMC);
        paymentService.addBill(bill);
        paymentService.addBill(bill1);
        List<Integer> ids = Arrays.asList(1, 2);
        paymentService.listBills();
        paymentService.payBills(ids);
        paymentService.showTransactionHistory();
        assertEquals("PAID", bill.getState());
        assertEquals(200, paymentService.getBalance());
    }

    @Test
    public void testPayBillInsufficientFunds() {
        PaymentService paymentService = new PaymentService();
        paymentService.addFunds(100);
        Bill bill = new Bill(1, "ELECTRIC", 500, DateUtil.parse("30/11/2020"), "NOT_PAID", ProviderEnum.EVN_HCMC);
        paymentService.addBill(bill);
        List<Integer> ids = Collections.singletonList(1);
        paymentService.payBills(ids);
        assertEquals("NOT_PAID", bill.getState());
        assertEquals(100, paymentService.getBalance());
    }
}
