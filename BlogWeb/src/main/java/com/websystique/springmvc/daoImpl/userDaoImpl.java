package com.websystique.springmvc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.hathim.blog.model.User;
import com.websystique.springmvc.dao.userDao;

public class userDaoImpl implements userDao {
	
	private DataSource dataSource;
	Connection con = null;
	PreparedStatement ps = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hathim");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
	
	@Override
	public void save(User user){
		String query = "insert into user (`name`,`password`,username) values (?,?,?)";
		
		
	
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getUserName());
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public ArrayList fetch(User user){
		ArrayList dataList = null;
		String query = "SELECT * FROM `user` WHERE username = '"+user.getUserName()+"' AND PASSWORD ='"+user.getPassword()+"'";
			
			// TODO Auto-generated catch block
		ResultSet dataSet = null;
		Statement stmt = null;
		try {
			dataSource = getDataSource();
			con = dataSource.getConnection();
			stmt = con.createStatement();
			dataSet = stmt.executeQuery(query);
			// Converting ResultSet to ArrayList<String[]>.
			dataList = rsToStrArr(dataSet);

			dataSet.close();
			stmt.close();
		} catch (SQLException sqlExp) {
		} finally {
			if (dataSet != null) {
				try {
					dataSet.close();
				} catch (SQLException sqlExp) {
					// dataSet = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					con.close();
				} catch (SQLException sqlExp) {
					// stmt = null;
				}

			}
			
		}
	return dataList;
	}
	
	private ArrayList rsToStrArr(ResultSet dataSet) throws SQLException {

		ArrayList dataStr = new ArrayList();
		
		if (dataSet != null) {
			ResultSetMetaData rsMetaData = dataSet.getMetaData();
			int colCtr = rsMetaData.getColumnCount();
			int index = 0;

			while (dataSet.next()) {
				String[] resObj = new String[colCtr];
				for (int cntr = 0; cntr < colCtr; cntr++) {
					resObj[cntr] = dataSet.getString(cntr + 1);
				}
				dataStr.add(index, resObj);
				resObj = null;
				index++;
			}
		}

		if (dataStr.toString().equals("[]")) {
			dataStr = null;
		}
		return dataStr;
	}
	
	@Override
	public ArrayList checkUserNameAlreadyExist(String username){
		ArrayList dataList = null;
		String query = "SELECT * FROM `user` WHERE username = '"+username+"'";
			
			// TODO Auto-generated catch block
		ResultSet dataSet = null;
		Statement stmt = null;
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			dataSet = stmt.executeQuery(query);
			// Converting ResultSet to ArrayList<String[]>.
			dataList = rsToStrArr(dataSet);

			dataSet.close();
			stmt.close();
		} catch (SQLException sqlExp) {
		} finally {
			if (dataSet != null) {
				try {
					dataSet.close();
				} catch (SQLException sqlExp) {
					// dataSet = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					con.close();
				} catch (SQLException sqlExp) {
					// stmt = null;
				}

			}
			
		}
	return dataList;
	}
	

}
