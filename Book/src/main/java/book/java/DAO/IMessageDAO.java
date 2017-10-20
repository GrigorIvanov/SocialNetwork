package book.java.DAO;

import book.java.model.Conversation;
import book.java.model.Message;
import book.java.model.User;

public interface IMessageDAO {
	void sendMessage(User sender,Message message, Conversation convo);
}
