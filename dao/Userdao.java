package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDao 
{
	private Connection connection;

	public UserDao(Connection connection)
	{
		this.connection = connection;
	}

	//register user

	public boolean newUserRegistration(User user)
	{
		boolean check = false;
		try
		{
			String query = "insert into user_details(fullname, email, password) values(?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, user.getFullName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());

			int result = preparedStatement.executeUpdate();

			if(result==1)
			{
				check=true;
				connection.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();

		}

		return check;
	}

	public User login(String email, String pass)
	{
		User user = null;

		try
		{
			String query = "select * from  user_details where email=?  and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, pass);

			ResultSet result = preparedStatement.executeQuery();

			while(result.next())
			{
				user = new User();

				user.setId(result.getInt(1));
				user.setFullName(result.getString(2));
				user.setEmail(result.getString(3));
				user.setPassword(result.getString(4));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean checkOldPassword(int userId, String oldPassword)
	{
		try {

			String query = "select * from user_details where id=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, oldPassword);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setNewPassword(int userId, String newPassword)
	{
		try {

			String query = "update user_details set password=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, userId);

			if(preparedStatement.executeUpdate()==1)
			{
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}






