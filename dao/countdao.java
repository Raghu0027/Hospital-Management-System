package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CountDao 
{
	private Connection connection;

	public CountDao(Connection connection)
	{
		this.connection = connection;
	}

	public int doctorCount()
	{
		int cnt = 0;

		try {

			String query = "select * from doctor_details";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				cnt++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public int specialistCount()
	{
		int cnt = 0;

		try {

			String query = "select * from specialist";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				cnt++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public int appointmentCount()
	{
		int cnt = 0;

		try {
			String query = "select * from user_appointment";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public int appointmentCountById(int doctorId)
	{
		int cnt = 0;

		try {
			String query = "select * from user_appointment where doctorId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public int userCount() 
	{
		int cnt  = 0;

		try {

			String query = "select * from user_details";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				cnt++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}


}