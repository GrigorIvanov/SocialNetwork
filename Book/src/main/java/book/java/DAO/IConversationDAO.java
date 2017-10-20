package book.java.DAO;

import book.java.model.Conversation;
import book.java.model.User;

public interface IConversationDAO {
	
	void MakeConversation(User member, Conversation convo);
	void AddUserToConversation(User user, Conversation convo);
	
}
