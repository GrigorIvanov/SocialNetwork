package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.exceptions.ConversationException;
import com.example.model.Conversation;
import com.example.model.User;
import com.example.model.DBConnection;


public class ConversationDAO extends AbstractDAO implements IConversationDAO{

	private static final String MAKE_CONVERSATION_STATEMENT = "INSERT INTO Conversations VALUES(null, ?)";

	public int MakeConversation(User member, Conversation convo) throws ConversationException {
		if (convo != null && member != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(MAKE_CONVERSATION_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, convo.getTitle());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				convo.getMembers().add(member);
				member.getChat().add(convo);
				return rs.getInt(1);
			} catch (SQLException e) {
				throw new ConversationException("This conversation can't be made",e);
			}
		}
		return 0;
	}

	

	public void AddUserToConversation(User user, Conversation convo) {
		convo.getMembers().add(user);
		user.getChat().add(convo);
	}

}
