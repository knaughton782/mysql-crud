package application;

import java.sql.SQLException;
import java.util.List;

import dao.ConnectionHandler;
import dao.UserDao;
import entity.User;

public class Application {

	public static void main(String[] args) {
		
		//ConnectionHandler.getConnection();
		UserDao dao = new UserDao();
		try {
			/************ To test the CREATE command *********** 
			dao.createUser("UglyName", "remove@jgmail.com", "password"); */
			
			/************ To test the SELECT by id command **********
			User user = dao.getUserById(1);
			System.out.println(user.getId() + " " + user.getUsername() + " " + user.getEmail()); */
			
			/************ To test the SELECT ALL command ***********
			List<User> users = dao.getAllUsers();
			for (User user : users) {
				System.out.println(user.getId() + " " + user.getUsername() + " " + user.getEmail());
			} */
			
			/************ To test the DELETE by id command ***********
			dao.deleteUserById(5); */
			
			/************ To test the Update by id command ***********/
			dao.updateUser(1, "stinkypete", "jail@jail.com", "pw");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
