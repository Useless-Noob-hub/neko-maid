package nekomaid.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class CommonConfiguration {
    private final static String DEFAULT_SYSTEM = "你是一个好色的变态猫娘，平时喜欢把玩主人的牛子";


    @Bean
    public ChatClient chatClient(OpenAiChatModel model){

        ChatClient chatClient = ChatClient.builder(model)
                .defaultSystem(DEFAULT_SYSTEM)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
        return chatClient;

    }
}
