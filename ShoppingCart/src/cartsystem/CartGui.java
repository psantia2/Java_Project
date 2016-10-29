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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class CartGui extends JFrame {
	Checkout ch= new Checkout();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartGui frame = new CartGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CartGui() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAmount = new JLabel("$0.00");
		txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtAmount.setText("Total: $"+Double.toString(ch.populateAmount()));
		txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmount.setBounds(585, 0, 217, 42);
		contentPane.add(txtAmount);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setBounds(397, 29, 69, 20);
		contentPane.add(lblQty);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(341, 29, 69, 20);
		contentPane.add(lblPrice);
		
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
		btnNewButton_3.setBounds(585, 428, 217, 29);
		contentPane.add(btnNewButton_3);
		
		
		JButton btnBack = new JButton("Back to Products");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductsGui p;
				try {
					p = new ProductsGui();
					p.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setBounds(585, 308, 217, 29);
		contentPane.add(btnBack);
		
		JButton btnclear = new JButton("Clear Selected");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ch.removefromCart();
				contentPane.revalidate();
			}
		});
		btnclear.setBounds(585, 348, 217, 29);
		contentPane.add(btnclear);
		
		JButton btnNewButton = new JButton("Pay");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Transaction Completed");
				for(int i =0;i<ch.idvector.size();i++){
				try {
					Payment p = new Payment(ch.returnVect(i),ch.returnqty(i));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnNewButton.setBounds(585, 388, 217, 29);
		contentPane.add(btnNewButton);
		
		
		
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		for(int i =0;i<ch.idvector.size();i++){			
			JCheckBox[] box = new JCheckBox[10];
			JLabel[] lbl2 = new JLabel[10];
			JLabel[] lbl3 = new JLabel[10];
			JSpinner[] spin= new JSpinner[10];
			st=con.createStatement();
			String sql = "SELECT * FROM products WHERE id ="+ch.returnVect(i);
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
				spin[un]=createSpinnerQty(id);
				contentPane.add(box[un]);
				contentPane.add(lbl2[un]);
				contentPane.add(lbl3[un]);
				contentPane.add(spin[un]);
			}
		}
		con.close();
	}
	public JCheckBox createBoxName(int productID, String pn){
		JCheckBox product = new JCheckBox(pn);
		//whaatttttt
		product.setToolTipText("<html><p width=\"350\">"+pn+"</p></html>");
			y = 50 + y;
			product.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(product.isSelected()){
						System.out.println("incheckbox: "+ productID);
						ch.tobeRemoved(productID);
						
					} else {
						System.out.println("deselect: "+ productID);
						ch.removeFromRemove(productID);
					}
				}
			});
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
	
	
	public JSpinner createSpinnerQty(int id){
		JSpinner spnqty = new JSpinner();
		spnqty.setValue(1);
		spnqty.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int val = (Integer)spnqty.getValue();
				try {
					double temp;
					temp=ch.updateQty(id, val);
					txtAmount.setText("$"+Double.toString(temp));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		spnqty.setBounds(450, y, 115, 29);
		return spnqty;
	}
	int y = 0;
	public int un=0;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private JLabel txtAmount;
}
