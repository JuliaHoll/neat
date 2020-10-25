package letscode.sarafan.controller;

import letscode.sarafan.exception.NotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;


@RestController
@RequestMapping("message")
public class MessageController {

    private int counter = 13;
    private String text;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "Я помню чудное мгновенье:");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Передо мной явилась ты,");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Как мимолетное виденье,");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("text", "Как гений чистой красоты.");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "5");
            put("text", "В томленьях грусти безнадежной,");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "6");
            put("text", "В тревогах шумной суеты,");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "7");
            put("text", "Звучал мне долго голос нежный");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "8");
            put("text", "И снились милые черты.");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "9");
            put("text", "Шли годы. Бурь порыв мятежный");
            put("Date", "24.10.2020 12:00");
        }});

        add(new HashMap<String, String>() {{
            put("id", "10");
            put("text", "Рассеял прежние мечты,");
            put("Date", "24.10.2020 12:00");
        }});

        add(new HashMap<String, String>() {{
            put("id", "11");
            put("text", "И я забыл твой голос нежный,");
            put("Date", "24.10.2020 12:00");
        }});
        add(new HashMap<String, String>() {{
            put("id", "12");
            put("text", "Твои небесные черты.");
            put("Date", "24.10.2020 12:00");
        }});
    }};


    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }
}

