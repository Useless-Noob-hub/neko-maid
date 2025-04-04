package nekomaid.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;
    @RequestMapping(value = "/chat" ,produces = "text/html;charset=UTF-8")
    public Flux<String> chat(@RequestParam (defaultValue = "你是谁?")String prompt) {
        return chatClient
                .prompt(prompt)
                .stream()
                .content();
    }
}
