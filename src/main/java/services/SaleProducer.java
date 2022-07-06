package services;

import model.entities.Client;
import model.entities.Sale;
import model.enums.Status;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import serializers.SaleSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

import static java.lang.Thread.sleep;

public class SaleProducer {

    private static Random random = new Random();
    private static long id = 0;
    private static BigDecimal ticketPrice = BigDecimal.valueOf(350);
    private static Long clientId = (long) random.nextInt(100);
    private static Integer amount = random.nextInt(10);

    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SaleSerializer.class.getName());

        KafkaProducer<String, Sale> producer = new KafkaProducer<>(properties);

        while(true){
            Sale sale = generateSale();
            ProducerRecord<String, Sale> record = new ProducerRecord<>("ticket-sales", sale);
            producer.send(record);
            sleep(5000);
        }

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
