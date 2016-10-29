package cartsystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	UserList u = UserList.getInstance();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() throws SQLException { 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(35, 59, 200, 50);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setText("Password");
		txtPassword.setBounds(35, 149, 200, 50);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogIn = new JButton("Sign In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				u.getUserinfo(txtUsername.getText(), txtPassword.getText());
				u.getType(rdbtnValue);
				u.checkLogin(txtUsername.getText(), txtPassword.getText());
				if(u.id!=0&&u.typeForLogin==1){
					ProductsGui p;
					try {
						p = new ProductsGui();
						p.setVisible(true);
						dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else if(u.id!=0&&u.typeForLogin==0){
					SellerProducts sp;
					try{
						sp  = new SellerProducts();
						sp.setVisible(true);
						dispose();
					} catch(SQLException e){
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Incorrect Login");
				}
			}
		});
		contentPane.setLayout(null);
		btnLogIn.setBounds(89, 247, 146, 52);
		contentPane.add(btnLogIn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				u.getUserinfo(txtUsername.getText(), txtPassword.getText());
				u.getType(rdbtnValue);
				if(!u.checkDuplicate(txtUsername.getText())){
					if(rdbtnValue=="Buyer"){
						u.insertBuyer();
						JOptionPane.showMessageDialog(null, "Success, log in now");
					}
					else{
						u.insertSeller();
						JOptionPane.showMessageDialog(null, "Success, log in now");
						}
				}else{
					JOptionPane.showMessageDialog(null, "Username already taken");
					}
				txtUsername.setText("Username");
				txtPassword.setText("Password");
				//frame.setVisible(false);
				}
			});
		btnRegister.setBounds(302, 247, 146, 52);
		contentPane.add(btnRegister);
		
		JRadioButton rdbtnBuyer = new JRadioButton("Buyer");
		rdbtnBuyer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnBuyer.isSelected()){
					rdbtnValue = rdbtnBuyer.getText();
				}
			}
		});
		rdbtnBuyer.setBounds(302, 84, 155, 44);
		contentPane.add(rdbtnBuyer);
		
		JRadioButton rdbtnSeller = new JRadioButton("Seller");
		rdbtnSeller.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSeller.isSelected()){
					rdbtnValue=rdbtnSeller.getText();
				}
			}
		});
		rdbtnSeller.setBounds(302, 155, 155, 44);
		contentPane.add(rdbtnSeller);
	}
	
String rdbtnValue;
private JTextField txtUsername;
private JPasswordField txtPassword;
}
