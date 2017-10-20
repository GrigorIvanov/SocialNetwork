package book.java.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import book.java.model.Conversation;
import book.java.model.Message;
import book.java.model.User;

public class MessageDAO extends AbstractDAO implements IMessageDAO{

	private static final String ADD_MESSAGE_STATEMENT = "INSERT INTO Messages Values (null, ?, ?)";

	public void sendMessage(User sender, Message message, Conversation convo) {
		if (convo != null && sender != null && message != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_MESSAGE_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, convo.getConversationId());
				ps.setString(2, message.getContent());
				//
				//Maybe add sender in DB ? 
				message.setSender(sender.getEmail());
				//
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				convo.setConversationId(rs.getInt(1));
				convo.getMessages().add(message);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
