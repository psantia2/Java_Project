package cartsystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SellerProducts extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerProducts frame = new SellerProducts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SellerProducts() throws SQLException {
		Sales s= new Sales();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 910, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Log Out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login l;
				try {
					l = new Login();
					l.setVisible(true);
					dispose();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				
		});
		btnNewButton_3.setBounds(585, 459, 217, 29);
		contentPane.add(btnNewButton_3);
		JButton btnAdd = new JButton("Add Inventory");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerUpdateGui sug;
				try {
					sug = new SellerUpdateGui(0,0);
					sug.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(585, 414, 217, 29);
		contentPane.add(btnAdd);
		double revenue=s.getRevenue(UserList.id);
		double cost=s.getCost(UserList.id);
		double profit=revenue-cost;
		profit=(double)Math.round(profit*100)/100;
		
		txtYourInventory = new JLabel();
		txtYourInventory.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtYourInventory.setText("Here is your inventory");
		txtYourInventory.setBounds(15, 0, 384, 42);
		contentPane.add(txtYourInventory);
		
		txtProfit = new JLabel("$0.00");
		txtProfit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtProfit.setHorizontalAlignment(SwingConstants.CENTER);
		txtProfit.setText("Profit: $"+Double.toString(profit));
		txtProfit.setBounds(585, 207, 273, 42);
		contentPane.add(txtProfit);
		
		JLabel txtRevenue = new JLabel("Revenue");
		txtRevenue.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtRevenue.setHorizontalAlignment(SwingConstants.CENTER);
		txtRevenue.setText("Revenue: $"+Double.toString(revenue));
		txtRevenue.setBounds(585, 265, 273, 42);
		contentPane.add(txtRevenue);
		
		JLabel txtCost = new JLabel("Cost");
		txtCost.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtCost.setHorizontalAlignment(SwingConstants.CENTER);
		txtCost.setText("Cost: $"+Double.toString(cost));
		txtCost.setBounds(585, 322, 273, 42);
		contentPane.add(txtCost);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setBounds(397, 29, 69, 20);
		contentPane.add(lblQty);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(341, 29, 49, 20);
		contentPane.add(lblPrice);
		
		
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		JLabel[] box = new JLabel[10];
		JLabel[] lbl2 = new JLabel[10];
		JLabel[] lbl3 = new JLabel[10];
		JButton[] btn = new JButton[10];
		st=con.createStatement();
		String sql = "SELECT * FROM products WHERE sId ="+UserList.id;
		rs = st.executeQuery(sql);
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			int qty = rs.getInt("qty");
			un++;
			box[un]=createBoxName(id,name);
			lbl2[un]=createLablePrice(price);
			lbl3[un]=createLableQty(qty);
			btn[un] = createBtnEdit(id);
			contentPane.add(box[un]);
			contentPane.add(lbl2[un]);
			contentPane.add(lbl3[un]);
			contentPane.add(btn[un]);
		}
		con.close();
	}
	public JLabel createBoxName(int productID, String pn){
		JLabel product = new JLabel(pn);
		//whaatttttt
		product.setToolTipText("<html><p width=\"350\">"+pn+"</p></html>");
			y = 50 + y;
		product.setBounds(15, y, 320, 29);
		return product;
	}
	public JLabel createLablePrice(double pp){
		JLabel price = new JLabel("$"+pp);
			price.setBounds(350, y, 115, 29);
		return price;
	}
	public JLabel createLableQty(int pq){
		JLabel qty = new JLabel(""+pq);
			qty.setBounds(400, y, 115, 29);
		return qty;
	}
	
	public JButton createBtnEdit(int id){
		JButton desc = new JButton("Edit");
		desc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerUpdateGui sug;
				try {
					sug = new SellerUpdateGui(1,id);
					
					sug.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
			desc.setBounds(450, y, 115, 29);
		return desc;
	}

	public int y = 0;
	public int un=0;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private JLabel txtProfit;
	private JLabel txtYourInventory;
}
