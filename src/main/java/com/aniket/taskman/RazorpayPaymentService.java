package com.aniket.taskman;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="payment.provider", havingValue = "razorpay")
public class RazorpayPaymentService implements PaymentService {

    @Override
    public String pay(){
        String Payment = "Razorpay Payment failed";
        System.out.println("Payment from : " + Payment);
        return Payment;
    }

}
