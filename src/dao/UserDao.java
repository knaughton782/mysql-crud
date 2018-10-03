package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {

	//performs all crud operations, maps DB to Social entity
	
	private final Connection connection = ConnectionHandler.getConnection();
	private final String CREATE_USER = "INSERT INTO user(username, email, password) VALUES (?,?,?)";
	private final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	private final String GET_ALL_USERS = "SELECT * FROM user";
	private final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
	private final String UPDATE_USER = "UPDATE user SET username = ?, email = ?, password = ? WHERE id = ?";
			
	
	public void createUser(String username, String email, String password) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_USER);
		ps.setString(1, username);
		ps.setString(2, email);
		ps.setString(3, password);
		ps.executeUpdate();
	}
	
	public User getUserById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return mapResultsToUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
	}
	
	//maps result set row to user entity
	private User mapResultsToUser(int id, String username, String email, String password) {
		return new User(id, username, email, password);
	}
	
	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<User>();
		PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			//returns true if there is another row and false if there isn't more data
			users.add(mapResultsToUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return users;
	}
	
	public void deleteUserById (int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateUser(int id, String username, String email, String password) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
		ps.setString(1, username);
		ps.setString(2, email);
		ps.setString(3, password);
		ps.setInt(4, id);
		ps.executeUpdate();
	}
}	

