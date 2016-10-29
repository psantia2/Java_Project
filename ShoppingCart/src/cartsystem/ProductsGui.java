package cartsystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

public class ProductsGui extends JFrame {
	
	Checkout ch= new Checkout();
	private JPanel contentPane;
	private JTextField txtCartTotal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductsGui frame = new ProductsGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public ProductsGui() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://host379.hostmonster.com:3306/xlxwebpr_java", "xlxwebpr_javaP2","jav@proj3ct2015");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setBounds(585,53,217,240);
		textArea.setLineWrap(true);
		contentPane.add(textArea);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setBounds(397, 29, 69, 20);
		contentPane.add(lblQty);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(341, 29, 69, 20);
		contentPane.add(lblPrice);
		
		txtAmount = new JLabel("$0.00");
		txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmount.setBounds(585, 0, 217, 42);
		contentPane.add(txtAmount);
		
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
		
		JButton btnNewButton = new JButton("Add to Cart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double temp =ch.populateAmount();
					txtAmount.setText("Total: $ " + Double.toString(temp));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(585, 309, 217, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Clear Cart");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ch.clearCart();
				txtAmount.setText("$0.00");
			}
		});
		btnNewButton_2.setBounds(585, 359, 217, 29);
		contentPane.add(btnNewButton_2);
		
		txtWelcomBack = new JLabel();
		txtWelcomBack.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtWelcomBack.setText("Welcome Back "+UserList.getInstance().nameForLogin);
		txtWelcomBack.setBounds(15, 0, 277, 42);
		contentPane.add(txtWelcomBack);
		
		JButton btnNewButton_1 = new JButton("Check Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartGui cg;
					try {
						cg = new CartGui();
						cg.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_1.setBounds(585, 409, 217, 29);
		contentPane.add(btnNewButton_1);
		
		
		JCheckBox[] box = new JCheckBox[10];
		JLabel[] lbl2 = new JLabel[10];
		JLabel[] lbl3 = new JLabel[10];
		JButton[] btn = new JButton[10];
		st=con.createStatement();
		String sql = "SELECT * FROM products";
		rs = st.executeQuery(sql);
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			System.out.println(description);
			double price = rs.getDouble("price");
			int qty = rs.getInt("qty");
			un++;
			box[un]=createBoxName(id,name);
			lbl2[un]=createLablePrice(price);
			lbl3[un]=createLableQty(qty);
			btn[un] = createBtnDesc(description);
			contentPane.add(box[un]);
			contentPane.add(lbl2[un]);
			contentPane.add(lbl3[un]);
			contentPane.add(btn[un]);
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
						ch.addToList(productID);
						
					} else {
						System.out.println("deselect: "+ productID);
						ch.removeFromList(productID);
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
	
	public JButton createBtnDesc(String description){
		JButton desc = new JButton("Description");
		desc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(description);
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
	private JLabel txtAmount;
	private JLabel txtWelcomBack;
	private JTextArea textArea = new JTextArea();
}
