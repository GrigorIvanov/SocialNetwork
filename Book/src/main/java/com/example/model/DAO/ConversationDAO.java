package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.exceptions.ConversationException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Conversation;
import com.example.model.User;
import com.example.model.DBConnection;

public class ConversationDAO extends AbstractDAO implements IConversationDAO {

	private static final String ADDING_CHAT_USER_STATEMENT = "INSERT INTO Chat_user VALUES (?,?)";
	private static final String MAKE_CONVERSATION_STATEMENT = "INSERT INTO Conversations VALUES(null, ?)";

	public int MakeConversation(User creator, Conversation convo) throws ConversationException, UserExeption {
		if (convo != null && creator != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(MAKE_CONVERSATION_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, convo.getTitle());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				convo.getMembers().add(creator);
				creator.getConversations().add(convo);

				/**
				 * here we add now the creator(user) of the chat in the Chat_User table
				 */
				this.AddUserToConversation(creator, convo);

				return rs.getInt(1);
			} catch (SQLException e) {
				throw new ConversationException("This conversation can't be made", e);
			}
		}
		return 0;
	}

	public void AddUserToConversation(User user, Conversation convo) throws ConversationException, UserExeption {
		try {
			PreparedStatement ps = getCon().prepareStatement(ADDING_CHAT_USER_STATEMENT);
			ps.setInt(1, convo.getConversationId());
			ps.setInt(2, user.getUserId());
			try {
				ps.executeUpdate();// thats the problem in the fucking dao
			} catch (Exception e) {
				e.printStackTrace();
			}
			convo.getMembers().add(user);
			user.getConversations().add(convo);

		} catch (SQLException e) {
			throw new ConversationException("This user cant be added to the chat", e);
		}
	}

}
