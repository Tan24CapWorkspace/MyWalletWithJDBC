package com.cg.dao;

/**
 * @author tanmpath
 * */
import com.cg.bean.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountDAOImpl implements AccountDAO{
	
	public boolean addAccount(Account acc) {
		Connection con = null;
		try {
			con = DBHelper.getConnection();
			PreparedStatement stm = con.prepareStatement("insert into account values(?,?,?,?)");
			stm.setInt(1, acc.getAid());
			stm.setLong(4,acc.getMobile());
			stm.setString(2, acc.getAccountholder());
			stm.setDouble(3, acc.getBalance());
			return stm.executeUpdate()!=0 ?true:false;
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public boolean updateAccount(Account acc) {
		Connection con = null;
		try {
			con = DBHelper.getConnection();
			PreparedStatement stm = con.prepareStatement("update account set balance = ? where mobile =?");
			stm.setDouble(1, acc.getBalance());
			stm.setLong(2, acc.getMobile());
			return stm.executeUpdate()!=0 ?true:false;
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public boolean deleteAccount(Account acc) {
		Connection con = null;
		try {
			con = DBHelper.getConnection();
			PreparedStatement stm = con.prepareStatement("delete from account where mobile = ?");
			stm.setLong(1, acc.getMobile());
			return stm.executeUpdate()!=0 ?true:false;
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public Account findAccount(long mobileNo) {
		Connection con = null;
		try {
			con = DBHelper.getConnection();
			PreparedStatement stm = con.prepareStatement("select * from account where mobile = ?");
			stm.setLong(1,mobileNo);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				return new Account(rs.getInt(1), rs.getLong(4), rs.getString(2), rs.getDouble(3));
			}else {
				return null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Map<Long, Account> getAccList() {
		Connection con = null;
		Map<Long, Account> list = new HashMap<Long, Account>();
		try {
			con = DBHelper.getConnection();
			PreparedStatement stm = con.prepareStatement("select * from account");
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				do {
					list.put(rs.getLong(4),new Account(rs.getInt(1), rs.getLong(4), rs.getString(2), rs.getDouble(3)));
				}while(rs.next());
			}
			return list;
		}catch (SQLException e) {
			return list;
		}
	}


	@Override
	public boolean findAccountID(Integer id) {
		Connection con = null;
		try {
			con = DBHelper.getConnection();
			PreparedStatement stm = con.prepareStatement("select * from account where aid = ?");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			return false;
		}
	}
	
}
