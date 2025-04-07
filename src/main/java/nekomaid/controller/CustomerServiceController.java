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

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class CustomerServiceController {

    private final ChatClient serviceChatClient;

    private final ChatHistoryRepository chatHistoryRepository;
    @RequestMapping(value = "/service" ,produces = "text/html;charset=UTF-8")
    public String service(@RequestParam String prompt,String chatId) {

        chatHistoryRepository.save("service",chatId);

        return serviceChatClient
                .prompt()
                .user(prompt)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY,chatId))
                .call()
                .content();
    }
}
