package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.example.model.Conversation;
import com.example.model.Message;
import com.example.model.User;

@Component
public class MessageDAO extends AbstractDAO implements IMessageDAO{

	private static final String ADD_MESSAGE_STATEMENT = "INSERT INTO Messages Values (null, ?, ?,?)";
	public void sendMessage(User sender, Message message, Conversation convo) {
		if (convo != null && sender != null && message != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_MESSAGE_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, convo.getConversationId());
				ps.setString(2, message.getContent());
				ps.setInt(3, sender.getUserId());
				
				ps.executeUpdate();
				message.setSender(sender);
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
