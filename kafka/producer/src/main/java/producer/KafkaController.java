package producer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;




@RestController
public class KafkaController {

    private final KafkaProducer producer;
    private final ImageProducer imageProducer;
    private final KafkaTemplate<String, Order> orderKafkaTemplate;

    public KafkaController(KafkaProducer producer, KafkaTemplate<String, Order> orderKafkaTemplate, ImageProducer imageProducer) {
        this.producer = producer;
        this.orderKafkaTemplate = orderKafkaTemplate;
        this.imageProducer = imageProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }

    @GetMapping("/send-order")
    public void sendOrder() {
        Order order = new Order("O1001", 250.75);
        orderKafkaTemplate.send("test-topic", order);    
    }
@PostMapping("/upload")
public ResponseEntity<String> uploadImage(
        @RequestParam("filename") String filename,
        @RequestParam("file") MultipartFile file) throws IOException {

    Path path = Paths.get("/var/" + filename);
    Files.write(path, file.getBytes());
    return ResponseEntity.ok("✅ Saved to " + path);
}

   @PostMapping("/image/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        imageProducer.sendImage(file);
        return "Image uploaded to Kafka: " + file.getOriginalFilename();
    }
}
