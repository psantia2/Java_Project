package cartsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sales {
	public double getRevenue(int id) throws SQLException{
		double rev=0;
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		String sql = "SELECT revenue FROM sells WHERE sid="+id;
		st=con.createStatement();
		rs=st.executeQuery(sql);
		while(rs.next()){
			rev+=rs.getDouble("revenue");
		}
		con.close();
		return (double)Math.round(rev*100)/100;
	}
	public double getCost(int id) throws SQLException{
		double cost=0;
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		String sql = "SELECT cost FROM products WHERE sId="+id;
		st=con.createStatement();
		rs=st.executeQuery(sql);
		while(rs.next()){
			cost+=rs.getDouble("cost");
		}
		con.close();
		return (double)Math.round(cost*100)/100;
	}
	private Connection con;
	private Statement st;
	private ResultSet rs;
}
