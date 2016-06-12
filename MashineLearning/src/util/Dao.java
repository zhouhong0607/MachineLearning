package util;

import java.lang.management.RuntimeMXBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Dao
{
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://127.0.0.1:3306/db_tianchi";
	protected static String dbUser = "root";
	protected static String dbPwd = "";

	public static Connection con = null;

	static
	{
		try
		{
			if (con == null)
			{
				Class.forName(dbClassName).newInstance();
				con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
		} catch (Exception ee)
		{
			ee.printStackTrace();
		}
	}

	// 禁止生成dao实例
	private Dao()
	{
	}

	

	// 对数据库的增加、修改和删除的操作
	public static boolean executeUpdate(String sql)
	{
		if (con == null)
		{
			return false;
		}
		try
		{
			Statement stmt = con.createStatement();
			int iCount = stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	// 对数据库的查询操作
	public static ResultSet executeQuery(String sql)
	{
		ResultSet rs;
		try
		{
			if (con == null)
			{
				return null;
			}
			Statement stmt = con.createStatement();
			try
			{
				rs = stmt.executeQuery(sql);
			} catch (SQLException e)
			{
				System.out.println(e.getMessage());
				return null;
			}
		} catch (SQLException e)
		{
			return null;
		}
		return rs;
	}
	
	
}