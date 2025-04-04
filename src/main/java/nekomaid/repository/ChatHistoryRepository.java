package nekomaid.repository;

import java.util.List;

public interface ChatHistoryRepository {
    /**
     * 保存聊天记录
     * @param type 业务类型
     * @param chatId 会话ID
     */
    void save(String type, String chatId);

    /**
     * 获取聊天记录
     * @param type 业务类型
     * @return 会话ID列表
     */
    List<String> getChatIds(String type);

}
