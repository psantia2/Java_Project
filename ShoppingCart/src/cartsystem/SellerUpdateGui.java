package cartsystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class SellerUpdateGui extends JFrame {

	private JPanel contentPane;
	private JTextField textNameField;
	private JTextField textPriceField;
	private JTextField textQtyField;
	private JTextField textCostField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerUpdateGui frame = new SellerUpdateGui(0,0);
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
	public SellerUpdateGui(int whattodo, int id) throws SQLException {
		AddInventory ai=new AddInventory();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNameField = new JTextField();
		textNameField.setBounds(100, 32, 146, 26);
		contentPane.add(textNameField);
		textNameField.setColumns(10);
		
		textPriceField = new JTextField();
		textPriceField.setBounds(100, 84, 146, 26);
		contentPane.add(textPriceField);
		textPriceField.setColumns(10);
		
		textQtyField = new JTextField();
		textQtyField.setBounds(100, 168, 146, 26);
		contentPane.add(textQtyField);
		textQtyField.setColumns(10);
		
		JTextArea textDescription = new JTextArea();
		textDescription.setBounds(275, 52, 301, 135);
		contentPane.add(textDescription);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(15, 35, 69, 20);
		contentPane.add(lblname);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(15, 87, 69, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setBounds(15, 171, 69, 20);
		contentPane.add(lblNewLabel_2);
		
		textCostField = new JTextField();
		textCostField.setBounds(100, 126, 146, 26);
		contentPane.add(textCostField);
		textCostField.setColumns(10);
		
		if(whattodo==1){
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddInventory sugai=new AddInventory();
				try {
					sugai.deleteItem(id);
					SellerProducts sp=new SellerProducts();
					sp.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(100, 210, 115, 29);
		contentPane.add(btnNewButton_2);
		AddInventory sugai = new AddInventory();
		sugai.setfields(id);
		textNameField.setText(sugai.name);
		textPriceField.setText(Double.toString(sugai.price));
		textQtyField.setText(Integer.toString(sugai.qty));
		textCostField.setText(Double.toString(sugai.cost));
		textDescription.setText(sugai.description);
		textDescription.setLineWrap(true);
		}
		
		JLabel lblNewLabel_3 = new JLabel("Cost");
		lblNewLabel_3.setBounds(15, 129, 69, 20);
		contentPane.add(lblNewLabel_3);
		SellerProducts sp = new SellerProducts();
		if(whattodo==0){
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ai.name=textNameField.getText();
				ai.description=textDescription.getText();
				ai.cost=Double.parseDouble(textCostField.getText());
				ai.price=Double.parseDouble(textPriceField.getText());
				ai.qty=Integer.parseInt(textQtyField.getText());
				SellerProducts sp;
				try {
					ai.addtoInventory(UserList.id);
					sp = new SellerProducts();
					sp.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(288, 199, 115, 29);
		contentPane.add(btnNewButton);
		}else{
			JButton btnNewButton1 = new JButton("Edit");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ai.name=textNameField.getText();
					ai.description=textDescription.getText();
					ai.cost=Double.parseDouble(textCostField.getText());
					ai.price=Double.parseDouble(textPriceField.getText());
					ai.qty=Integer.parseInt(textQtyField.getText());
					SellerProducts sp;
					try {
						ai.updateinventory(id);
						sp = new SellerProducts();
						sp.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton1.setText("Edit");
			btnNewButton1.setBounds(288, 199, 115, 29);
			contentPane.add(btnNewButton1);
		}
			
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerProducts sp;
				try {
					sp = new SellerProducts();
					sp.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(447, 199, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(275, 16, 101, 20);
		contentPane.add(lblDescription);
		
	}

	
}
