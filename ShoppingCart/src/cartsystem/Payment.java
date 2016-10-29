package cartsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Payment {
	public Payment(int id,int qty) throws SQLException{
		AddtoSalesList(id, qty);
		DeductfromInv(id,qty);
	}
	public void DeductfromInv(int id,int qty) throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		st=con.createStatement();
		String sql2 = "UPDATE products SET qty= qty-"+qty+" WHERE id="+id;
		System.out.println(sql2);
		st.executeUpdate(sql2);
		con.close();
	}
	public void AddtoSalesList(int id, int qty) throws SQLException{
		double temp =0;
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		String sql = "SELECT price, sId FROM products WHERE id="+id;
		st=con.createStatement();
		System.out.println(sql);
		rs=st.executeQuery(sql);
		double price=0;
		int sid=0;
		while(rs.next()){
			price = rs.getDouble("price");
			temp = price * qty;
			sid = rs.getInt("sId");
		}
		st=con.createStatement();
		String sql2 = "INSERT INTO sells (sid,revenue) VALUE ('"+sid+"','"+temp+"')";
		st.executeUpdate(sql2);
		con.close();
	}

	private Connection con; 
	private Statement st;
	private ResultSet rs;
	
}
