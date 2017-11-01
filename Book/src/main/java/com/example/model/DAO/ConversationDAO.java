package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.model.Conversation;
import com.example.model.User;
import com.example.model.DBConnection;


public class ConversationDAO extends AbstractDAO implements IConversationDAO{

	private static final String MAKE_CONVERSATION_STATEMENT = "INSERT INTO Conversations VALUES(null, ?)";

	public void MakeConversation(User member, Conversation convo) {
		if (convo != null && member != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(MAKE_CONVERSATION_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, convo.getTitle());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				convo.setConversationId(rs.getInt(1));
				convo.getMembers().add(member);
				member.getChat().add(convo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

	public void AddUserToConversation(User user, Conversation convo) {
		convo.getMembers().add(user);
		user.getChat().add(convo);
	}

}
