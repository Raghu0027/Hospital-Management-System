package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Specialist;

public class SpecialistDao 
{
	private Connection connection;

	public SpecialistDao(Connection connection)
	{
		this.connection=connection;
	}

	public boolean addSpecialist(String specialistName)
	{
		try
		{
			String query = "insert into specialist(name) values(?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, specialistName);

			if(preparedStatement.executeUpdate() == 1)
			{
				return true;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Specialist> getAllSpecialist()
	{
		List<Specialist> list = new ArrayList<>();
		Specialist sObj;
		try {
			String query = "select * from specialist";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				sObj =new Specialist();
				sObj.setId(resultSet.getInt(1));
				sObj.setSpecialistName(resultSet.getString(2));
				list.add(sObj);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}