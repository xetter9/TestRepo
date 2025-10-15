package producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ImageProducer {

   @Value("${app.kafka.image-topic}")
   private String imageTopic;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public ImageProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendImage(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        kafkaTemplate.send(imageTopic, file.getOriginalFilename(), fileBytes);
        System.out.println("✅ Sent image: " + file.getOriginalFilename() + " (" + fileBytes.length + " bytes)");
    }    

}
