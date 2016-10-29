package cartsystem;
import java.sql.*;
import java.util.Vector;
public class Checkout{
	static Vector<Vector<Integer>> idvector = new Vector<Vector<Integer>>(10,2);
	static Vector<Integer> removeid= new Vector<Integer>(10,2);
	
	public void addToList(int id){
		if(!checkDuplicate(id)){
			
			Vector<Integer> v = new Vector<Integer>(2);
			v.add(id);
			v.add(new Integer(1));
			idvector.add(v);
			System.out.println("Add Successfull: "+id);
		}
	}
	
	public void removeFromList(int id){
		for(int i=0;i<idvector.size();i++){
			if(idvector.get(i).get(0) == id){
				idvector.remove(i);
				System.out.println("Delete Successful= "+id);
			}
		}
	}
	public double populateAmount()throws SQLException{
		total = 0;
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		for(int i=0;i<idvector.size();i++){
			st=con.createStatement();
			String sql = "SELECT price FROM products WHERE id ="+idvector.get(i).get(0);
			rs = st.executeQuery(sql);
			while(rs.next()){
				total += rs.getDouble("price");
			}
		}
		con.close();
		return total;
	}
	
	
	public void clearCart(){
		idvector.clear();
	}
	public int returnVect(int i){
		return idvector.get(i).get(0);
	}
	
	public int returnqty(int i){
		return idvector.get(i).get(1);
	}
	
	public boolean checkDuplicate(int id){
		for(int i=0;i<idvector.size();i++){
			if(id==idvector.get(i).get(0))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean checkDuplicate2(int id){
		for(int i=0;i<removeid.size();i++){
			if(id==removeid.get(i))
			{
				return true;
			}
		}
		return false;
	}
	
	public void tobeRemoved(int id){
		if(!checkDuplicate2(id)){
			removeid.add(id);
			System.out.println("To Be Removed: "+id);
		}
		
	}
	
	public void removeFromRemove(int id){
		for(int i=0;i<removeid.size();i++){
			if(removeid.get(i) == id){
				removeid.remove(i);
			}
		}
	}
	
	public void removefromCart(){
		for(int i=0;i<removeid.size();i++)
		{
			for(int j=0;j<idvector.size();j++){
				if(removeid.get(i)==idvector.get(j).get(0)){
					idvector.remove(j);
					System.out.println("Removed worked");
				}
			}
			
		}
		
	}
	
	public double updateQty(int id, int newqty) throws SQLException{
		for(int j=0;j<idvector.size();j++){
			if(idvector.get(j).get(0)==id){
				idvector.get(j).set(1, newqty);
				System.out.println("new qty worked");
			}
		}
		
		total = 0; 
		double temp = 0;
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		for(int i=0;i<idvector.size();i++){
			st=con.createStatement();
			String sql = "SELECT id,price FROM products WHERE id ="+idvector.get(i).get(0);
			rs = st.executeQuery(sql);
			while(rs.next()){
				temp = rs.getDouble("price") * getQty(rs.getInt("id"));
				total += temp;
			}
		}
		con.close();
		return Math.round(total*100)/100;
	}
	
	public int getQty(int id){
		for(int j=0;j<idvector.size();j++){
			if(idvector.get(j).get(0)==id){
				return idvector.get(j).get(1);
			}
		}
		return 1;
	}
	
	double total;
	private Connection con; 
	private Statement st;
	private ResultSet rs;
}
