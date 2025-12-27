package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.UserAppointment;

public class AppointmentDao 
{
	private Connection connection;

	public AppointmentDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public boolean addAppointment(UserAppointment appointment)
	{
		try 
		{
			String query = "insert into user_appointment(userId, fullName, gender, age, appointmentDate, email, phNo, diseases, doctorId, address, status) values(?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, appointment.getUserId());
			preparedStatement.setString(2, appointment.getFullName());
			preparedStatement.setString(3, appointment.getGender());
			preparedStatement.setString(4, appointment.getAge());
			preparedStatement.setString(5, appointment.getAppointmentDate());
			preparedStatement.setString(6, appointment.getEmail());
			preparedStatement.setString(7, appointment.getPhNo());
			preparedStatement.setString(8, appointment.getDiseases());
			preparedStatement.setInt(9, appointment.getDoctorId());
			preparedStatement.setString(10, appointment.getAddress());
			preparedStatement.setString(11, appointment.getStatus());

			if(preparedStatement.executeUpdate()==1)
			{
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<UserAppointment> getAllAppointmentByUserLogin(int userId)
	{
		List<UserAppointment> list = new ArrayList<UserAppointment>();
		UserAppointment appointment = null;

		try 
		{

			String query = "select * from user_appointment where userId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				appointment = new UserAppointment();
				appointment.setId(resultSet.getInt(1));
				appointment.setUserId(resultSet.getInt(2));
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhNo(resultSet.getString(8));
				appointment.setDiseases(resultSet.getNString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));

				list.add(appointment);
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return list;

	}

	public List<UserAppointment> getAllAppointmentByDoctorLogin(int doctorId)
	{
		List<UserAppointment> list = new ArrayList<UserAppointment>();
		UserAppointment appointment = null;

		try 
		{

			String query = "select * from user_appointment where doctorId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				appointment = new UserAppointment();
				appointment.setId(resultSet.getInt(1));
				appointment.setUserId(resultSet.getInt(2));
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhNo(resultSet.getString(8));
				appointment.setDiseases(resultSet.getNString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));

				list.add(appointment);
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return list;

	}

	public UserAppointment getAppointmentById(int id)
	{
		UserAppointment appointment = null;

		try 
		{

			String query = "select * from user_appointment where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				appointment = new UserAppointment();
				appointment.setId(resultSet.getInt(1));
				appointment.setUserId(resultSet.getInt(2));
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhNo(resultSet.getString(8));
				appointment.setDiseases(resultSet.getNString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));

			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return appointment;
	}

	public boolean updateCommentStatus(int id, int doctorId, String comment)
	{
		try {

			String query = "update user_appointment set status=? where id=? and doctorId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, comment);
			preparedStatement.setInt(2, id);
			preparedStatement.setInt(3, doctorId);

			if(preparedStatement.executeUpdate()==1)
			{
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<UserAppointment> getAllAppointment()
	{
		List<UserAppointment> list = new ArrayList<UserAppointment>();
		UserAppointment appointment = null;

		try 
		{

			String query = "select * from user_appointment order by id desc";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				appointment = new UserAppointment();
				appointment.setId(resultSet.getInt(1));
				appointment.setUserId(resultSet.getInt(2));
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhNo(resultSet.getString(8));
				appointment.setDiseases(resultSet.getNString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));

				list.add(appointment);
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return list;

	}
}








