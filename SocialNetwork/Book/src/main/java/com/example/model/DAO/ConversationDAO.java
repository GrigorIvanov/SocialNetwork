package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.ConversationException;
import com.example.exceptions.UserExeption;
import com.example.model.Conversation;
import com.example.model.User;
@Component
public class ConversationDAO extends AbstractDAO implements IConversationDAO {

	private static final String ADDING_CHAT_USER_STATEMENT = "INSERT INTO Chat_user VALUES (?,?)";
	private static final String MAKE_CONVERSATION_STATEMENT = "INSERT INTO Conversations VALUES(null, ?)";
	private static final String INSERT_CONVERSATION_TO_USER= "INSERT INTO Chat_user VALUES (?,?)";

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
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			convo.getMembers().add(user);
			user.getConversations().add(convo);

		} catch (SQLException e) {
			throw new ConversationException("This user cant be added to the chat", e);
		}
	}
	
	public void setConversationToUsers(int userId,int conversationId) throws ConversationException {
		
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_CONVERSATION_TO_USER, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, conversationId);
			ps.setInt(2, userId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
		} catch (SQLException e) {
			throw new ConversationException("Conversation cannot be received right now, please try again later.");
		}
	}

	@Override
	public List<Conversation> getConversationById(int userid) throws UserExeption {
		List<Integer> myConvoId=new ArrayList<Integer>();
		List<Conversation> myConversations=new ArrayList<Conversation>();
			try {
				
				PreparedStatement ps = getCon().prepareStatement("SELECT * FROM Chat_user WHERE user_id=?");
				ps.setInt(1, userid);
				ResultSet result = ps.executeQuery();

				while(result.next()) {
					myConvoId.add(result.getInt(1));
				}
				

				for (Integer id : myConvoId ) {
					PreparedStatement ps2=getCon().prepareStatement("SELECT * FROM Conversations WHERE conversation_id=?");
					ps2.setInt(1, id);
					ResultSet result2=ps2.executeQuery();
					Conversation conv= new Conversation();
					conv.setConversationId(result2.getInt(1));
					conv.setTitle(result2.getString(2));
					myConversations.add(conv);
					
				}
				
				
				return myConversations;

			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserExeption("Can't find user with that ID.", e);
			}
	}

}
