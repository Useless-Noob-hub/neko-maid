package nekomaid.controller;


import lombok.RequiredArgsConstructor;
import nekomaid.repository.ChatHistoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    private final ChatHistoryRepository chatHistoryRepository;
    @RequestMapping(value = "/chat" ,produces = "text/html;charset=UTF-8")
    public Flux<String> chat(@RequestParam (defaultValue = "你是谁?")String prompt,String chatId) {

        chatHistoryRepository.save("chat",chatId);

        return chatClient
                .prompt(prompt)
                .advisors(a -> a.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,chatId))
                .stream()
                .content();
    }
}
