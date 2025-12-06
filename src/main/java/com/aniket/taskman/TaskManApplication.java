package com.aniket.taskman;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManApplication implements CommandLineRunner {

    public static void main(String[] args) {
		SpringApplication.run(TaskManApplication.class, args);
	}

//    @Autowired
    private PaymentService paymentService ; //this can be final too, @AutoWired not used

    public TaskManApplication(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void run(String... args) throws Exception {
        String Payment = paymentService.pay();
        System.out.println("Payment Done : " + Payment);
    }
}
