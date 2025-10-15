package consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

/*
groupId identifies a consumer group in Kafka.
*/

@Service
public class KafkaConsumer {

    /*@KafkaListener(topics = "test-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received string: " + message);
    }

    @KafkaListener(topics = "test-topic", groupId = "my-group")
     public void listenInt(Integer number) {
        System.out.println("Received number: " + number);
    }*/


    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void consume(Order order) {
        System.out.println("Received order: " + order.getId() + " price: " + order.getPrice());
    }

@KafkaListener(topics = "image-topic", groupId = "my-group1",
                   containerFactory = "imageKafkaListenerContainerFactory")
    public void consumeImage(ImageMessage message) throws IOException {
        String outputPath = Paths.get("/var/" + message.getFilename()).toString();
    try (FileOutputStream fos = new FileOutputStream(outputPath)) {
        fos.write(message.getData());
    }
    System.out.println("✅ Image saved as: " + outputPath);
    }
}
