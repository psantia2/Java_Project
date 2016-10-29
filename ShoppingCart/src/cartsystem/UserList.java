package cartsystem;

import java.sql.*;

public class UserList{
	DBConnection connect = new DBConnection();
	private static UserList instance = new UserList();
	private UserList(){try {
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public static UserList getInstance(){
		return instance;
	}
	
	public void getUserinfo(String u, String p){
		username = u;
		password = p;
	}
	
	public void getType(String t){
		if(t=="Buyer"){usertype =1;}
		else{usertype=0;}
	}
	
	public void checkLogin(String u, String p){
		try{
			st=con.createStatement();
			String sql = "SELECT id, name, type, count(*) FROM user WHERE name='"+u+"' AND password='"+p+"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int count = rs.getInt(1);
				System.out.println(count+" "+u);
				if(count>0){
					id = rs.getInt("id");
					typeForLogin = rs.getInt("type");
					nameForLogin = rs.getString("name");
				}
			}
		}catch(Exception ex){
			System.out.println("Error55: "+ex);
		}
		System.out.println("id = "+id);
	}
	
	public void insertBuyer(){	
		String sql = "INSERT INTO user(name,password,type)VALUES('"+username+"','"+password+"',"+usertype+")";
		connect.insertinto(sql);
	}
	
	public void insertSeller(){
		String sql = "INSERT INTO user(name,password,type)VALUES('"+username+"','"+password+"',"+usertype+")";
		connect.insertinto(sql);
	}
	
	public boolean checkDuplicate(String u){
		try{
			st=con.createStatement();
			String sql = "SELECT count(*) FROM user WHERE name='"+u+"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int count = rs.getInt(1);
				System.out.println(count+" "+u);
				if(count>0){
					System.out.println("");
					return true;
					}
			}
		}catch(Exception ex){
			System.out.println("Error55: "+ex);
		}
		return false;
	}
////////////////////////////////////////////////////////////////////////////////	
String username;
String password;
int usertype;
private Connection con;
private Statement st;
private ResultSet rs;
static public int id;
String nameForLogin;
int typeForLogin;
////////////////////////////////////////////////////////////////////////////////
}
