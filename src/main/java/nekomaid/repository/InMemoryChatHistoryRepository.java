package nekomaid.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryChatHistoryRepository implements ChatHistoryRepository{
    private Map<String,List<String>> chatHistory;

    @Override
    public void save(String type, String chatId) {
        List<String> chatIds = chatHistory.computeIfAbsent(type, k -> new ArrayList<>());
        if(chatIds.contains(chatId)){
            return;
        }
        chatIds.add(chatId);
    }

    @Override
    public List<String> getChatIds(String type) {
        return chatHistory.getOrDefault(type, List.of());
    }
}
