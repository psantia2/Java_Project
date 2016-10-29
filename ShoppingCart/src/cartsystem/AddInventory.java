package cartsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddInventory {
	public void addtoInventory(int id) throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		st=con.createStatement();
		String sql2 = "INSERT INTO products (name,description,price,cost,qty,sId) VALUE "
				+ "('"+name+"','"+description+"','"+price+"','"+cost+"',"+qty+","+id+")";
		System.out.println(sql2);
		st.executeUpdate(sql2);
		con.close();
	}
	
	public void setfields(int id) throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		st=con.createStatement();
		String sql = "SELECT * FROM products WHERE id="+id;
		rs=st.executeQuery(sql);
		while(rs.next()){
			name=rs.getString("name");
			description=rs.getString("description");
			price=rs.getDouble("price");
			qty=rs.getInt("qty");
			cost=rs.getDouble("cost");
		}
		con.close();
	}
	public void updateinventory(int id) throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		st=con.createStatement();
		String sql2 = "UPDATE products SET name='"+name+"',description='"+description+"',price='"+price+"',cost='"+cost+"',qty="+qty+" WHERE id="+id;
		st.executeUpdate(sql2);
		con.close();
	}
	
	public void deleteItem(int id) throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		st=con.createStatement();
		String sql = "DELETE FROM products WHERE id="+id;
		st.executeUpdate(sql);
		con.close();
	}
public String name;
public String description;
int qty;
double price;
double cost;
private Connection con; 
private Statement st;
private ResultSet rs;
}
