package cartsystem;

import java.sql.*;

public class DBConnection {

		public DBConnection(){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
				st = con.createStatement();
				System.out.println("...Connected");
			}catch(Exception ex){
				System.out.println("Connection failed: " + ex);
			}
		}
		public void getData(){
			try{
				String query = "select * from user";
				rs = st.executeQuery(query);
				System.out.println("Records from database");
				while(rs.next()){
					String name = rs.getString("name");
					System.out.println("Name: " +name);
				}
			}catch(Exception ex){
				System.out.println("Error: " + ex);
			}
		}
		public void insertinto(String u){
			try{
				System.out.println(u);
				st = con.createStatement();
				st.executeUpdate(u);
			}catch(Exception ex){System.out.println("Error: "+ex);}
		}
private Connection con;
private Statement st;
private ResultSet rs;
}
