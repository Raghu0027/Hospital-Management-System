package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.DoctorDetails;

public class DoctorDetailsDao {
	private Connection connection;

	public DoctorDetailsDao(Connection connection) {
		this.connection = connection;
	}

	public boolean registerDoctor(DoctorDetails doctor) {
		try {
			String query = "insert into doctor_details(fullName, dob, qualification, specialist, email, mobNo, password) values(?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, doctor.getFullName());
			preparedStatement.setString(2, doctor.getDob());
			preparedStatement.setString(3, doctor.getQualification());
			preparedStatement.setString(4, doctor.getSpecialist());
			preparedStatement.setString(5, doctor.getEmail());
			preparedStatement.setString(6, doctor.getMobNo());
			preparedStatement.setString(7, doctor.getPassword());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<DoctorDetails> getAllDoctor()
	{
		List<DoctorDetails> list = new ArrayList<DoctorDetails>();
		DoctorDetails doc;

		try
		{
			String query = "select * from doctor_details";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				doc = new DoctorDetails();

				doc.setId(resultSet.getInt(1));
				doc.setFullName(resultSet.getString(2));
				doc.setDob(resultSet.getString(3));
				doc.setQualification(resultSet.getString(4));
				doc.setSpecialist(resultSet.getString(5));
				doc.setEmail(resultSet.getString(6));
				doc.setMobNo(resultSet.getString(7));
				doc.setPassword(resultSet.getString(8));
				list.add(doc);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public DoctorDetails getDoctorDetailById(int id)
	{
		DoctorDetails doc=null;

		try {

			String query = "select * from doctor_details where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet =preparedStatement.executeQuery();

			while(resultSet.next())
			{
				doc = new DoctorDetails();

				doc.setId(resultSet.getInt(1));
				doc.setFullName(resultSet.getString(2));
				doc.setDob(resultSet.getString(3));
				doc.setQualification(resultSet.getString(4));
				doc.setSpecialist(resultSet.getString(5));
				doc.setEmail(resultSet.getString(6));
				doc.setMobNo(resultSet.getString(7));
				doc.setPassword(resultSet.getString(8));
			}

		} catch (Exception e) {
			e.printStackTrace();		}

		return doc;
	}

	public boolean updateDoctorDetails(DoctorDetails doctor)
	{
		try {
			String query = "update doctor_details set fullName=?, dob=?, qualification=?, specialist=?, email=?, mobNo=?, password=? where id=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, doctor.getFullName());
			preparedStatement.setString(2, doctor.getDob());
			preparedStatement.setString(3, doctor.getQualification());
			preparedStatement.setString(4, doctor.getSpecialist());
			preparedStatement.setString(5, doctor.getEmail());
			preparedStatement.setString(6, doctor.getMobNo());
			preparedStatement.setString(7, doctor.getPassword());
			preparedStatement.setInt(8, doctor.getId());
			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return false;
	}

	public boolean deleteDoctorById(int id)
	{
		try
		{
			String query = "delete from doctor_details where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			if(preparedStatement.executeUpdate()==1)
			{
				return true;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public DoctorDetails doctorLogin(String email, String pass)
	{
		DoctorDetails doc = null;

		try 
		{
			String query = "select * from doctor_details where email=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,pass);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				doc = new DoctorDetails();
				doc.setId(resultSet.getInt(1));
				doc.setFullName(resultSet.getString(2));
				doc.setDob(resultSet.getString(3));
				doc.setQualification(resultSet.getString(4));
				doc.setSpecialist(resultSet.getString(5));
				doc.setEmail(resultSet.getString(6));
				doc.setMobNo(resultSet.getString(7));
				doc.setPassword(resultSet.getString(8));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return doc;
	}

	public boolean checkOldPassword(int doctorId, String oldPassword)
	{
		try {

			String query = "select * from doctor_details where id=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
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

	public boolean setNewPassword(int doctorId, String newPassword)
	{
		try {

			String query = "update doctor_details set password=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, doctorId);

			if(preparedStatement.executeUpdate()==1)
			{
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editDoctorDetails(DoctorDetails doctor)
	{
		try {
			String query = "update doctor_details set fullName=?, dob=?, qualification=?, specialist=?, email=?, mobNo=? where id=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, doctor.getFullName());
			preparedStatement.setString(2, doctor.getDob());
			preparedStatement.setString(3, doctor.getQualification());
			preparedStatement.setString(4, doctor.getSpecialist());
			preparedStatement.setString(5, doctor.getEmail());
			preparedStatement.setString(6, doctor.getMobNo());
			preparedStatement.setInt(7, doctor.getId());
			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return false;
	}

}









