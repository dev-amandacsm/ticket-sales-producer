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

    private static final Random random = new Random();
    private static long id = 0;

    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SaleSerializer.class.getName());

        KafkaProducer<String, Sale> producer = new KafkaProducer<>(properties);

        while(true){
            Sale sale = generateSale();
            ProducerRecord<String, Sale> record = new ProducerRecord<>("sales", sale);
            producer.send(record);
            sleep(3000);
        }

    }

    private static Client generateClient(){
        Long clientId = (long) random.nextInt(100);
        return Client
                .builder()
                .id(clientId)
                .name("Client " + clientId)
                .email("client" + clientId + "@email.com")
                .phone(String.valueOf(random.nextInt(999999999)))
                .build();
    }

    private static Sale generateSale(){
        Integer amount = random.nextInt(10);
        BigDecimal ticketPrice = BigDecimal.valueOf(350);
        return Sale
                .builder()
                .id(id++)
                .client(generateClient())
                .amount(amount)
                .totalValue(ticketPrice.multiply(BigDecimal.valueOf(amount)))
                .status(Status.PENDING_PAYMENT)
                .build();
    }
}
