package services;

import model.entities.Client;
import model.entities.Sale;
import model.enums.Status;

import java.math.BigDecimal;
import java.util.Random;

public class SaleProducer {

    private static Random random = new Random();
    private static long id = 0;
    private static final BigDecimal ticketPrice = BigDecimal.valueOf(350);
    private static final Long clientId = Long.valueOf(random.nextInt(100));
    private static final Integer amount = random.nextInt(10);

    public static void main(String[] args) {
        Sale sale = generateSale();
        
    }


    private static Client generateClient(){
        return Client
                .builder()
                .id(clientId)
                .name("Client " + clientId)
                .email("client" + clientId + "@email.com")
                .phone(String.valueOf(random.nextInt()))
                .build();
    }

    private static Sale generateSale(){
        Client client = generateClient();
        return Sale
                .builder()
                .id(id++)
                .client(client)
                .amount(amount)
                .totalValue(ticketPrice.multiply(BigDecimal.valueOf(amount)))
                .status(Status.PENDING_PAYMENT)
                .build();
    }

}
