package projectv2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.peer.MenuPeer;
import javax.swing.JTabbedPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

public class projectv2 {

	private JFrame frmshopeiei;
	private JTextField tf_login_user;
	private JTextField tf_login_password;
	private ResultSet rec;
	private JTextField tf_add_phoneNumber;
	private JTextField tf_add_firstName;
	private JTextField tf_add_lastName;
	private JTextField tf_add_address;
	private JButton bt_clear;
	private JTextField tf_add_password;
	private JTextField tf_remove_user;
	private String command;
	private JTextField tf_edit_user;
	private JTextField tf_edit_phoneNumber;
	private JTextField tf_edit_password;
	private JTextField tf_edit_firstName;
	private JTextField tf_edit_address;
	private JTextField tf_edit_lastName;
	private JTable tb_product;
	private JTextField tf_addProduct_barcode;
	private JTextField tf_addProduct_name;
	private JTextField tf_addProduct_price;
	private JButton bt_addProduct, bt_addProduct_barcode, bt_addProduct_clear, bt_editProduct_clear, bt_edit_search;
	private JTextField tf_editProduct_name;
	private JTextField tf_editProduct_barcode;
	private JTextField tf_editProduct_price;
	private JPanel page_checkbill;
	private JTable table;
	private JTextField tf_checkbill_member;
	private JTextField tf_checkbill_barcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					projectv2 window = new projectv2();
					window.frmshopeiei.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public projectv2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void sendData(String message) {
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(9090);
	        Socket socket = listener.accept();
	        socket.setKeepAlive(true);
	        System.out.println("Client Connected");
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Client response: " + in.readLine());
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Sending Message...");
            out.write(message + "\n");
            out.flush();
            socket.close();
            listener.close();
		}catch (IOException e1) {
			System.out.println("sendData() Error");
			e1.printStackTrace();
		}
	}
	
	public void getDatabase(String qry) {
		try {
			Connection connect = DriverManager.getConnection("jdbc:ucanaccess://D:/Project/projectv2/shopeiei.accdb");
			Statement s = connect.createStatement();
			this.rec = s.executeQuery(qry);
		}catch (Exception e) {
			System.out.println("getDatabase() Error");
		}
	}
	
	public void updateDatabase(String qry) {
		try {
			Connection connect = DriverManager.getConnection("jdbc:ucanaccess://D:/Project/projectv2/shopeiei.accdb");
			Statement s = connect.createStatement();
			s.executeUpdate(qry);
		}catch (Exception e) {
			System.out.println("updateDatabase() Error");
		}
	}
	
	public static boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static String getNum(String strNum) {
	    try {
	        int d = Integer.parseInt(strNum);
	        return Integer.toString(d);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return "";
	    }
	}
	
	private void initialize() {
		frmshopeiei = new JFrame();
		frmshopeiei.setTitle("\u0E42\u0E1B\u0E23\u0E41\u0E01\u0E23\u0E21\u0E08\u0E31\u0E14\u0E01\u0E32\u0E23\u0E23\u0E49\u0E32\u0E19\u0E04\u0E49\u0E32 ::ShopEiEi::");
		frmshopeiei.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				sendData("0"); //สั่งปิดไฟเมื่อปิดโปรแกรม
			}
		});
		frmshopeiei.setBounds(100, 100, 775, 500);
		frmshopeiei.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmshopeiei.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel page_login = new JPanel();
		frmshopeiei.getContentPane().add(page_login, "name_259029853320400");
		page_login.setLayout(null);
		
		tf_login_user = new JTextField();
		tf_login_user.setBounds(297, 134, 251, 20);
		page_login.add(tf_login_user);
		tf_login_user.setColumns(10);
		
		JLabel label = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(81, 134, 201, 20);
		page_login.add(label);
		
		JPanel page_menu = new JPanel();
		frmshopeiei.getContentPane().add(page_menu, "name_259057677869400");
		page_menu.setLayout(null);
		
		JLabel label_1 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E1C\u0E48\u0E32\u0E19 :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(81, 184, 201, 20);
		page_login.add(label_1);
		
		JPanel page_member = new JPanel();
		frmshopeiei.getContentPane().add(page_member, "name_259061979964300");
		page_member.setLayout(null);
		
		JPanel page_product = new JPanel();
		frmshopeiei.getContentPane().add(page_product, "name_259064293933900");
		page_product.setLayout(null);
		
		JButton bt_login = new JButton("\u0E40\u0E02\u0E49\u0E32\u0E2A\u0E39\u0E48\u0E23\u0E30\u0E1A\u0E1A");
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//เอาข้อมูลlogin ไปเปรียบเทียบใน database ผ่าน method getDatabase
					getDatabase("SELECT * FROM user_data WHERE phoneNumber='" + tf_login_user.getText() + "' AND password = '" + tf_login_password.getText() + "' AND type = 'admin'");
					//เช็คว่า database ส่งข้อมูลกลับมาไหม ถ้าส่งกลับมาแปลว่าชื่อและรหัสผ่านถูกต้อง
					if (rec.next()) {
						page_login.setVisible(false);  //เปลี่ยนหน้า
						page_menu.setVisible(true);
						sendData("1");  //สั่งเปิดไฟ
						tf_login_password.setText("");
					}else {
						JOptionPane.showMessageDialog(page_login, "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง!!");
					}
				}catch (Exception e) {
					System.out.println("Error");
				}
			}
		});
		bt_login.setBounds(297, 234, 123, 20);
		page_login.add(bt_login);
		
		tf_login_password = new JPasswordField();
		tf_login_password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					bt_login.doClick();
				}
			}
		});
		tf_login_password.setBounds(297, 184, 251, 20);
		page_login.add(tf_login_password);
		tf_login_password.setColumns(10);
		
		JButton bt_logout = new JButton("\u0E2D\u0E2D\u0E01\u0E08\u0E32\u0E01\u0E23\u0E30\u0E1A\u0E1A");
		bt_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(page_menu, "ต้องการออกจากระบบหรือไม่?") == JOptionPane.YES_OPTION) {
					page_menu.setVisible(false);
					page_login.setVisible(true);
					sendData("0");
				}
			}
		});
		bt_logout.setBounds(10, 11, 175, 23);
		page_menu.add(bt_logout);
		
		JButton bt_member = new JButton("\u0E08\u0E31\u0E14\u0E01\u0E32\u0E23\u0E2A\u0E21\u0E32\u0E0A\u0E34\u0E01");
		bt_member.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page_menu.setVisible(false);
				page_member.setVisible(true);
				tf_add_password.setEditable(false);
				bt_clear.doClick();
			}
		});
		bt_member.setBounds(10, 70, 175, 49);
		page_menu.add(bt_member);
		
		JButton bt_pay = new JButton("\u0E04\u0E34\u0E14\u0E40\u0E07\u0E34\u0E19");
		bt_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page_menu.setVisible(false);
				page_checkbill.setVisible(true);
				
			}
		});
		bt_pay.setBounds(10, 190, 175, 49);
		page_menu.add(bt_pay);
		
		JButton bt_product = new JButton("\u0E08\u0E31\u0E14\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		bt_product.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page_menu.setVisible(false);
				page_product.setVisible(true);
				getDatabase("SELECT barcode, productName, price FROM product_data");
				tb_product.setModel(DbUtils.resultSetToTableModel(rec));
				bt_editProduct_clear.doClick();
				bt_addProduct_clear.doClick();
				
				
			}
		});
		bt_product.setBounds(10, 130, 175, 49);
		page_menu.add(bt_product);
		
		JButton bt_member_back = new JButton("\u0E22\u0E49\u0E2D\u0E19\u0E01\u0E25\u0E31\u0E1A");
		bt_member_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page_member.setVisible(false);
				page_menu.setVisible(true);
			}
		});
		bt_member_back.setBounds(10, 11, 175, 23);
		page_member.add(bt_member_back);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 739, 405);
		page_member.add(tabbedPane);
		
		JPanel pn_add_member = new JPanel();
		tabbedPane.addTab("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E2A\u0E21\u0E32\u0E0A\u0E34\u0E01", null, pn_add_member, null);
		pn_add_member.setLayout(null);
		
		JLabel label_2 = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(63, 55, 112, 14);
		pn_add_member.add(label_2);
		
		JLabel lb_add_phoneNumberStatus = new JLabel("");
		lb_add_phoneNumberStatus.setBounds(185, 83, 250, 20);
		pn_add_member.add(lb_add_phoneNumberStatus);
		
		JButton bt_check = new JButton("\u0E15\u0E23\u0E27\u0E08\u0E2A\u0E2D\u0E1A");
		bt_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDatabase("SELECT * FROM user_data where phoneNumber = '" + tf_add_phoneNumber.getText() + "'");
				try {
					if (rec.next()) {
						lb_add_phoneNumberStatus.setText("เบอร์นี้ไม่สามารถใช้งานได้");
						lb_add_phoneNumberStatus.setForeground(new Color(255, 0, 0));
					}else if (tf_add_phoneNumber.getText().length() == 10 && isNumeric(tf_add_phoneNumber.getText()) && getNum(tf_add_phoneNumber.getText()).length() == 9) {
						lb_add_phoneNumberStatus.setText("เบอร์นี้สามารถใช้งานได้");
						lb_add_phoneNumberStatus.setForeground(new Color(51, 153, 51));
					}else {
						lb_add_phoneNumberStatus.setText("เบอร์นี้ไม่สามารถใช้งานได้");
						lb_add_phoneNumberStatus.setForeground(new Color(255, 0, 0));
					}
				} catch (SQLException e1) {
					System.out.println("Error phone check");
					e1.printStackTrace();
				}
			}
		});
		bt_check.setBounds(385, 52, 105, 20);
		pn_add_member.add(bt_check);
		
		tf_add_phoneNumber = new JTextField();
		tf_add_phoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					bt_check.doClick();
				}
			}
		});
		tf_add_phoneNumber.setColumns(10);
		tf_add_phoneNumber.setBounds(185, 52, 190, 20);
		pn_add_member.add(tf_add_phoneNumber);
		
		JComboBox cb_add_type = new JComboBox();
		cb_add_type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cb_add_type.getSelectedItem().toString() == "admin") {
					tf_add_phoneNumber.setEditable(true);
					tf_add_password.setEditable(true);
				}else if (cb_add_type.getSelectedItem().toString() == "member") {
					tf_add_password.setEditable(false);
					tf_add_password.setText("");
				}
			}
		});
		cb_add_type.setModel(new DefaultComboBoxModel(new String[] {"member", "admin"}));
		cb_add_type.setBounds(185, 21, 112, 20);
		pn_add_member.add(cb_add_type);
		
		JLabel label_3 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E1C\u0E48\u0E32\u0E19 :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(63, 117, 112, 14);
		pn_add_member.add(label_3);
		
		tf_add_firstName = new JTextField();
		tf_add_firstName.setColumns(10);
		tf_add_firstName.setBounds(185, 145, 190, 20);
		pn_add_member.add(tf_add_firstName);
		
		tf_add_lastName = new JTextField();
		tf_add_lastName.setColumns(10);
		tf_add_lastName.setBounds(471, 145, 190, 20);
		pn_add_member.add(tf_add_lastName);
		
		JLabel label_4 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D :");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(63, 148, 112, 14);
		pn_add_member.add(label_4);
		
		JLabel label_5 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25 :");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(349, 148, 112, 14);
		pn_add_member.add(label_5);
		
		tf_add_address = new JTextField();
		tf_add_address.setColumns(10);
		tf_add_address.setBounds(185, 176, 476, 20);
		pn_add_member.add(tf_add_address);
		
		JLabel label_6 = new JLabel("\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48 :");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(63, 179, 112, 14);
		pn_add_member.add(label_6);
		
		tf_add_password = new JPasswordField();
		tf_add_password.setBounds(185, 114, 190, 20);
		pn_add_member.add(tf_add_password);
		tf_add_password.setColumns(10);
		
		bt_clear = new JButton("clear");
		bt_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb_add_type.setSelectedIndex(0);
				lb_add_phoneNumberStatus.setText("");
				tf_add_phoneNumber.setText("");
				tf_add_password.setText("");
				tf_add_firstName.setText("");
				tf_add_lastName.setText("");
				tf_add_address.setText("");
			}
		});
		bt_clear.setBounds(635, 343, 89, 23);
		pn_add_member.add(bt_clear);
		
		JButton bt_add_user = new JButton("\u0E15\u0E01\u0E25\u0E07");
		bt_add_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				getDatabase("SELECT * FROM user_data WHERE phoneNumber = '" + tf_add_phoneNumber.getText() + "'");
				try {
					if (rec.next() || tf_add_phoneNumber.getText().length() != 10 || !isNumeric(tf_add_phoneNumber.getText()) || tf_add_firstName.getText().length() < 3||
							tf_add_lastName.getText().length() < 3 || tf_add_address.getText().length() < 3 || getNum(tf_add_phoneNumber.getText()).length() != 9) {
						JOptionPane.showMessageDialog(pn_add_member, "ข้อมูลไม่ถูกต้อง!!");
					}else {
						if (cb_add_type.getSelectedItem().toString() == "admin" && tf_add_password.getText().length() > 4) {
							updateDatabase("INSERT INTO user_data (phoneNumber, password, type, firstName, lastName, address, registeredDate) VALUES ('" + tf_add_phoneNumber.getText() + "', '" +
									tf_add_password.getText() + "', '" + cb_add_type.getSelectedItem().toString() + "', '" + tf_add_firstName.getText() + "', '" + tf_add_lastName.getText() + "', '" +
												tf_add_address.getText() + "', '" + dateFormat.format(date).toString() + "')");
									sendData(URLEncoder.encode("เพิ่มสมาชิก :", "UTF-8") + tf_add_phoneNumber.getText() + ",  " + URLEncoder.encode(tf_add_firstName.getText(), "UTF-8") + "  " + URLEncoder.encode(tf_add_lastName.getText(), "UTF-8") + URLEncoder.encode("   วันที่ :", "UTF-8") + dateFormat.format(date).toString());
									JOptionPane.showMessageDialog(pn_add_member, "เพิ่มสมาชิกสำเร็จ!!");
									bt_clear.doClick();
						}else if (cb_add_type.getSelectedItem().toString() == "member") {
							updateDatabase("INSERT INTO user_data (phoneNumber, password, type, firstName, lastName, address, registeredDate) VALUES ('" + tf_add_phoneNumber.getText() + "', '" +
									tf_add_password.getText() + "', '" + cb_add_type.getSelectedItem().toString() + "', '" + tf_add_firstName.getText() + "', '" + tf_add_lastName.getText() + "', '" +
												tf_add_address.getText() + "', '" + dateFormat.format(date).toString() + "')");
									sendData(URLEncoder.encode("เพิ่มสมาชิก :", "UTF-8") + tf_add_phoneNumber.getText() + ",  " + URLEncoder.encode(tf_add_firstName.getText(), "UTF-8") + "  " + URLEncoder.encode(tf_add_lastName.getText(), "UTF-8") + URLEncoder.encode("   วันที่ :", "UTF-8") + dateFormat.format(date).toString());
									JOptionPane.showMessageDialog(pn_add_member, "เพิ่มสมาชิกสำเร็จ!!");
									bt_clear.doClick();
						}else {
							JOptionPane.showMessageDialog(pn_add_member, "ข้อมูลไม่ถูกต้อง!!");
						}
					}
				} catch (SQLException e1) {
					System.out.println("Error updateDatabase");
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		bt_add_user.setBounds(539, 343, 89, 23);
		pn_add_member.add(bt_add_user);
		
		JPanel pn_edit_member = new JPanel();
		tabbedPane.addTab("\u0E41\u0E01\u0E49\u0E44\u0E02\u0E2A\u0E21\u0E32\u0E0A\u0E34\u0E01", null, pn_edit_member, null);
		pn_edit_member.setLayout(null);
		
		tf_edit_user = new JTextField();
		tf_edit_user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10) {
					bt_edit_search.doClick();
				}
			}
		});
		tf_edit_user.setColumns(10);
		tf_edit_user.setBounds(155, 12, 331, 20);
		pn_edit_member.add(tf_edit_user);
		
		JComboBox cb_edit_type = new JComboBox();
		cb_edit_type.setModel(new DefaultComboBoxModel(new String[] {"\u0E17\u0E31\u0E49\u0E07\u0E2B\u0E21\u0E14", "\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C", "\u0E0A\u0E37\u0E48\u0E2D", "\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25"}));
		cb_edit_type.setBounds(10, 12, 135, 20);
		pn_edit_member.add(cb_edit_type);
		
		JComboBox cb_edit_searchResult = new JComboBox();
		cb_edit_searchResult.setBounds(10, 55, 476, 20);
		pn_edit_member.add(cb_edit_searchResult);
		
		bt_edit_search = new JButton("\u0E04\u0E49\u0E19\u0E2B\u0E32");
		bt_edit_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb_edit_searchResult.removeAllItems();
				switch (cb_edit_type.getSelectedIndex()) {
				case 0:
					command = "SELECT * FROM user_data";
					break;
				case 1:
					command = "SELECT * FROM user_data where phoneNumber = '" +tf_edit_user.getText() + "'";
					break;
				case 2:
					command = "SELECT * FROM user_data where firstName = '" +tf_edit_user.getText() + "'";
					break;
				case 3:
					command = "SELECT * FROM user_data where lastName = '" +tf_edit_user.getText() + "'";
					break;
				}
				try {
					getDatabase(command);
					while (rec.next()) {
						cb_edit_searchResult.addItem(rec.getString("phoneNumber") + ",  " + rec.getString("firstName") + "  " + rec.getString("lastName"));  
					}
	
				}catch (Exception q) {
					System.out.println("Error remove getDatabase");
				}
			}
		});
		bt_edit_search.setBounds(496, 11, 89, 23);
		pn_edit_member.add(bt_edit_search);
		
		tf_edit_phoneNumber = new JTextField();
		tf_edit_phoneNumber.setColumns(10);
		tf_edit_phoneNumber.setBounds(155, 139, 190, 20);
		pn_edit_member.add(tf_edit_phoneNumber);
		
		tf_edit_phoneNumber.setEditable(false);
		
		JLabel label_7 = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C :");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(33, 142, 112, 14);
		pn_edit_member.add(label_7);
		
		JLabel lb_edit_type = new JLabel("");
		lb_edit_type.setBounds(155, 114, 96, 14);
		pn_edit_member.add(lb_edit_type);
		
		JLabel lblType = new JLabel("Type  :");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setBounds(33, 117, 112, 14);
		pn_edit_member.add(lblType);
		
		tf_edit_password = new JPasswordField();
		tf_edit_password.setEditable(true);
		tf_edit_password.setColumns(10);
		tf_edit_password.setBounds(155, 170, 190, 20);
		pn_edit_member.add(tf_edit_password);
		
		JLabel label_8 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E1C\u0E48\u0E32\u0E19 :");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(33, 173, 112, 14);
		pn_edit_member.add(label_8);
		
		JLabel label_9 = new JLabel("\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48 :");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(33, 235, 112, 14);
		pn_edit_member.add(label_9);
		
		JLabel label_10 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D :");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(33, 204, 112, 14);
		pn_edit_member.add(label_10);
		
		tf_edit_firstName = new JTextField();
		tf_edit_firstName.setColumns(10);
		tf_edit_firstName.setBounds(155, 201, 190, 20);
		pn_edit_member.add(tf_edit_firstName);
		
		tf_edit_address = new JTextField();
		tf_edit_address.setColumns(10);
		tf_edit_address.setBounds(155, 232, 476, 20);
		pn_edit_member.add(tf_edit_address);
		
		tf_edit_lastName = new JTextField();
		tf_edit_lastName.setColumns(10);
		tf_edit_lastName.setBounds(441, 201, 190, 20);
		pn_edit_member.add(tf_edit_lastName);
		
		JLabel label_11 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25 :");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(319, 204, 112, 14);
		pn_edit_member.add(label_11);
		
		JButton bt_edit_select = new JButton("\u0E40\u0E25\u0E37\u0E2D\u0E01");
		bt_edit_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb_edit_searchResult.getItemCount() < 1){
					JOptionPane.showMessageDialog(pn_edit_member, "ข้อมูลไม่ถูกต้อง!!");
				}else {
					getDatabase("SELECT * FROM user_data WHERE phoneNumber = '" + cb_edit_searchResult.getSelectedItem().toString().split(",")[0] + "'");
					try {
						if (rec.next()) {
							tf_edit_firstName.setText(rec.getString("firstName"));
							tf_edit_phoneNumber.setText(rec.getString("phoneNumber"));
							lb_edit_type.setText(rec.getString("type"));
							tf_edit_lastName.setText(rec.getString("lastName"));
							tf_edit_address.setText(rec.getString("address"));
							if (rec.getString("type").equals("member")) {
								tf_edit_password.setText("");
								tf_edit_password.setEditable(false);
							}else {
								tf_edit_password.setText(rec.getString("password"));
								tf_edit_password.setEditable(true);
							}
						}else {
							JOptionPane.showMessageDialog(pn_edit_member, "ข้อมูลไม่ถูกต้อง!!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		bt_edit_select.setBounds(496, 54, 89, 23);
		pn_edit_member.add(bt_edit_select);
		
		JButton bt_edit_clear = new JButton("clear");
		bt_edit_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lb_edit_type.setText("");
				tf_edit_password.setEditable(true);
				tf_edit_password.setText("");
				tf_edit_address.setText("");
				tf_edit_firstName.setText("");
				tf_edit_lastName.setText("");
				tf_edit_phoneNumber.setText("");
				tf_edit_address.setText("");
			}
		});
		bt_edit_clear.setBounds(635, 343, 89, 23);
		pn_edit_member.add(bt_edit_clear);
		
		JButton bt_edit_ok = new JButton("\u0E15\u0E01\u0E25\u0E07");
		bt_edit_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_edit_phoneNumber.getText().length() == 10) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					updateDatabase("UPDATE user_data SET password = '" + tf_edit_password.getText() + "', firstName = '" + tf_edit_firstName.getText() + "', lastName = '" + tf_edit_lastName.getText() + "', address = '" +
							tf_edit_address.getText() + "' WHERE phoneNumber = '" + tf_edit_phoneNumber.getText() + "'");
					
					try {
						sendData(URLEncoder.encode("แก้ไขสมาชิก  :" + tf_edit_phoneNumber.getText() + ",  " + tf_edit_firstName.getText() + "  " + tf_edit_lastName.getText() + "   วันที่ : " + dateFormat.format(date).toString(), "UTF-8"));
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					bt_edit_clear.doClick();
					JOptionPane.showMessageDialog(pn_edit_member, "แก้ไขข้อมูลสำเร็จ!!");
					
				}
				
			}
		});
		bt_edit_ok.setBounds(539, 343, 89, 23);
		pn_edit_member.add(bt_edit_ok);
		
		JPanel pn_remove_member = new JPanel();
		tabbedPane.addTab("\u0E25\u0E1A\u0E2A\u0E21\u0E32\u0E0A\u0E34\u0E01", null, pn_remove_member, null);
		pn_remove_member.setLayout(null);
		
		JComboBox cb_remove_type = new JComboBox();
		cb_remove_type.setModel(new DefaultComboBoxModel(new String[] {"\u0E17\u0E31\u0E49\u0E07\u0E2B\u0E21\u0E14", "\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C", "\u0E0A\u0E37\u0E48\u0E2D", "\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25"}));
		cb_remove_type.setBounds(53, 67, 135, 20);
		pn_remove_member.add(cb_remove_type);
		
		tf_remove_user = new JTextField();
		tf_remove_user.setBounds(198, 67, 331, 20);
		pn_remove_member.add(tf_remove_user);
		tf_remove_user.setColumns(10);
		
		JComboBox cb_remove_searchResult = new JComboBox();
		cb_remove_searchResult.setBounds(53, 128, 476, 20);
		pn_remove_member.add(cb_remove_searchResult);
		
		JLabel lb_addProduct_check = new JLabel("");
		lb_addProduct_check.setBounds(476, 103, 164, 14);
		page_product.add(lb_addProduct_check);
		
		JButton bt_remove_search = new JButton("\u0E04\u0E49\u0E19\u0E2B\u0E32");
		bt_remove_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb_remove_searchResult.removeAllItems();
				switch (cb_remove_type.getSelectedIndex()) {
				case 0:
					command = "SELECT * FROM user_data";
					break;
				case 1:
					command = "SELECT * FROM user_data where phoneNumber = '" +tf_remove_user.getText() + "'";
					break;
				case 2:
					command = "SELECT * FROM user_data where firstName = '" +tf_remove_user.getText() + "'";
					break;
				case 3:
					command = "SELECT * FROM user_data where lastName = '" +tf_remove_user.getText() + "'";
					break;
				}
				try {
					getDatabase(command);
					while (rec.next()) {
						if (!rec.getString("phoneNumber").equals("0942640821")) {
							cb_remove_searchResult.addItem(rec.getString("phoneNumber") + ",  " + rec.getString("firstName") + "  " + rec.getString("lastName")); 
						} 
					}
	
				}catch (Exception q) {
					System.out.println("Error remove getDatabase");
				}
			}
		});
		bt_remove_search.setBounds(539, 66, 89, 23);
		pn_remove_member.add(bt_remove_search);
		
		JButton bt_remove_clear = new JButton("clear");
		bt_remove_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb_remove_type.setSelectedIndex(0);
				cb_remove_searchResult.removeAllItems();
				tf_remove_user.setText("");
			}
		});
		bt_remove_clear.setBounds(635, 343, 89, 23);
		pn_remove_member.add(bt_remove_clear);
		
		JButton bt_remove = new JButton("\u0E15\u0E01\u0E25\u0E07");
		bt_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb_remove_searchResult.getItemCount() < 1){
					JOptionPane.showMessageDialog(pn_remove_member, "ข้อมูลไม่ถูกต้อง!!");
				}
				else if(JOptionPane.showConfirmDialog(pn_remove_member, "ต้องการลบสมาชิกหรือไม่?") == JOptionPane.YES_OPTION) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					try {
						sendData(URLEncoder.encode("ลบสมาชิก :", "UTF-8") + URLEncoder.encode(cb_remove_searchResult.getSelectedItem().toString(), "UTF-8") + URLEncoder.encode("   วันที่ :", "UTF-8") + dateFormat.format(date).toString());
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateDatabase("DELETE FROM user_data WHERE phoneNumber = '" + cb_remove_searchResult.getSelectedItem().toString().split(",")[0] + "'");
					cb_remove_searchResult.removeItem(cb_remove_searchResult.getSelectedItem().toString());
					JOptionPane.showMessageDialog(pn_remove_member, "ลบสมาชิกสำเร็จ!!");
				}
			}
		});
		bt_remove.setBounds(539, 343, 89, 23);
		pn_remove_member.add(bt_remove);
		
		tf_addProduct_barcode = new JTextField();
		tf_addProduct_barcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					bt_addProduct_barcode.doClick();
				}
			}
		});
		tf_addProduct_barcode.setBounds(476, 72, 164, 20);
		page_product.add(tf_addProduct_barcode);
		tf_addProduct_barcode.setColumns(10);
		
		bt_addProduct_barcode = new JButton("\u0E22\u0E37\u0E19\u0E22\u0E31\u0E19");
		bt_addProduct_barcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getDatabase("SELECT * FROM product_data WHERE barcode = '" + tf_addProduct_barcode.getText().toString() + "'");
				try {
					if (rec.next()) {
						lb_addProduct_check.setText("barcode ถูกใช้งานแล้ว");
						lb_addProduct_check.setForeground(new Color(255, 0, 0));
						tf_addProduct_barcode.setText("");
					}else if (tf_addProduct_barcode.getText().toString().length() < 5){
						lb_addProduct_check.setText("barcode ไม่ถูกต้อง");
						lb_addProduct_check.setForeground(new Color(255, 0, 0));
						tf_addProduct_barcode.setText("");
					}else {
						lb_addProduct_check.setText("สามารถใช้งานได้");
						lb_addProduct_check.setForeground(new Color(51, 153, 51));
						tf_addProduct_barcode.setEditable(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		bt_addProduct_barcode.setBounds(650, 71, 89, 23);
		page_product.add(bt_addProduct_barcode);
		
		tf_addProduct_name = new JTextField();
		tf_addProduct_name.setColumns(10);
		tf_addProduct_name.setBounds(476, 128, 164, 20);
		page_product.add(tf_addProduct_name);
		
		tf_addProduct_price = new JTextField();
		tf_addProduct_price.setColumns(10);
		tf_addProduct_price.setBounds(476, 159, 164, 20);
		page_product.add(tf_addProduct_price);
		

		bt_addProduct_clear = new JButton("clear");
		bt_addProduct_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_addProduct_barcode.setText("");
				tf_addProduct_barcode.setEditable(true);
				lb_addProduct_check.setText("");
				tf_addProduct_name.setText("");
				tf_addProduct_price.setText("");
			}
		});
		bt_addProduct_clear.setBounds(569, 190, 70, 23);
		page_product.add(bt_addProduct_clear);
		
		bt_addProduct = new JButton("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		bt_addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				getDatabase("SELECT * FROM product_data WHERE barcode = '" + tf_addProduct_barcode.getText().toString() + "'");
				try {
					if (rec.next() || tf_addProduct_barcode.getText().toString().length() < 5) {
						JOptionPane.showMessageDialog(null, "กรอก barcode ให้ถูกต้อง!!");
					}else {
						if(!isNumeric(tf_addProduct_price.getText())) {
							JOptionPane.showMessageDialog(null, "กรอกจำนวนเงินให้ถูกต้อง!!");
						}else if(tf_addProduct_name.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "กรอกชื่อสินค้า!!");
						}else {
							updateDatabase("INSERT INTO product_data(barcode, productName, price) VALUES('" + tf_addProduct_barcode.getText() + "', '" + tf_addProduct_name.getText() + "', " + tf_addProduct_price.getText() + ")");
							getDatabase("SELECT barcode, productName, price FROM product_data");
							tb_product.setModel(DbUtils.resultSetToTableModel(rec));
							sendData((URLEncoder.encode("เพิ่มสินค้า :" + tf_addProduct_name.getText() + "   ราคา :" + tf_addProduct_price.getText() + "บาท" + "   วันที่ :" + dateFormat.format(date).toString(), "UTF-8")));
							bt_addProduct_clear.doClick();
							JOptionPane.showMessageDialog(null, "เพิ่มสินค้าสำเร็จ!!");
						}
					}
				} catch (SQLException u) {
					// TODO Auto-generated catch block
					u.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bt_addProduct.setBounds(476, 190, 94, 23);
		page_product.add(bt_addProduct);
		
		JLabel label_14 = new JLabel("-------------------------------------------------------------------");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(390, 217, 359, 23);
		page_product.add(label_14);
		
		JLabel lblBarcode = new JLabel("Barcode :");
		lblBarcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBarcode.setBounds(390, 75, 76, 14);
		page_product.add(lblBarcode);
		
		JLabel label_13 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32 :");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setBounds(390, 131, 76, 14);
		page_product.add(label_13);
		
		JLabel label_15 = new JLabel("\u0E23\u0E32\u0E04\u0E32 :");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		label_15.setBounds(390, 162, 76, 14);
		page_product.add(label_15);
		
		JLabel label_16 = new JLabel("\u0E1A\u0E32\u0E17");
		label_16.setBounds(650, 162, 46, 14);
		page_product.add(label_16);
		
		JButton button = new JButton("\u0E22\u0E49\u0E2D\u0E19\u0E01\u0E25\u0E31\u0E1A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page_product.setVisible(false);
				page_menu.setVisible(true);
			}
		});
		button.setBounds(10, 11, 175, 23);
		page_product.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setBounds(10, 46, 370, 404);
		page_product.add(scrollPane);
		
		tb_product = new JTable();
		tb_product.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tb_product.getSelectedRow();
				TableModel model = tb_product.getModel();
				tf_editProduct_barcode.setText(model.getValueAt(i, 0).toString());
				tf_editProduct_name.setText(model.getValueAt(i, 1).toString());
				tf_editProduct_price.setText(model.getValueAt(i, 2).toString());
			}
		});
		scrollPane.setViewportView(tb_product);
		
		JLabel label_12 = new JLabel("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_12.setBounds(531, 47, 94, 14);
		page_product.add(label_12);
		
		tf_editProduct_name = new JTextField();
		tf_editProduct_name.setColumns(10);
		tf_editProduct_name.setBounds(476, 307, 164, 20);
		page_product.add(tf_editProduct_name);
		
		tf_editProduct_barcode = new JTextField();
		tf_editProduct_barcode.setColumns(10);
		tf_editProduct_barcode.setBounds(476, 276, 164, 20);
		page_product.add(tf_editProduct_barcode);
		
		tf_editProduct_barcode.setEditable(false);
		
		JLabel label_17 = new JLabel("\u0E41\u0E01\u0E49\u0E44\u0E02/\u0E25\u0E1A \u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_17.setBounds(531, 251, 94, 14);
		page_product.add(label_17);
		
		JLabel label_18 = new JLabel("Barcode :");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setBounds(390, 279, 76, 14);
		page_product.add(label_18);
		
		JLabel label_19 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32 :");
		label_19.setHorizontalAlignment(SwingConstants.RIGHT);
		label_19.setBounds(390, 310, 76, 14);
		page_product.add(label_19);
		
		JLabel label_20 = new JLabel("\u0E23\u0E32\u0E04\u0E32 :");
		label_20.setHorizontalAlignment(SwingConstants.RIGHT);
		label_20.setBounds(390, 341, 76, 14);
		page_product.add(label_20);
		
		tf_editProduct_price = new JTextField();
		tf_editProduct_price.setColumns(10);
		tf_editProduct_price.setBounds(476, 338, 164, 20);
		page_product.add(tf_editProduct_price);
		
		bt_editProduct_clear = new JButton("clear");
		bt_editProduct_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tf_editProduct_barcode.setText("");
				tf_editProduct_name.setText("");
				tf_editProduct_price.setText("");
			}
		});
		bt_editProduct_clear.setBounds(569, 403, 70, 23);
		page_product.add(bt_editProduct_clear);
		
		JLabel label_21 = new JLabel("\u0E1A\u0E32\u0E17");
		label_21.setBounds(650, 341, 46, 14);
		page_product.add(label_21);
		
		JButton bt_editProduct = new JButton("\u0E41\u0E01\u0E49\u0E44\u0E02\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		bt_editProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				if (tf_editProduct_barcode.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "กรุณาเลือกข้อมูล!!");
				}else if(tf_editProduct_name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "กรุณากรอกชื่อสินค้า!!");
				}else if(!isNumeric(tf_editProduct_price.getText())) {
					JOptionPane.showMessageDialog(null, "กรอกจำนวนเงินให้ถูกต้อง!!");
				}else {
					updateDatabase("UPDATE product_data SET productName = '" + tf_editProduct_name.getText() + "', price = " + tf_editProduct_price.getText() + " WHERE barcode = '" + tf_editProduct_barcode.getText() + "'");
					try {
						sendData((URLEncoder.encode("แก้ไขสินค้า :" + tf_editProduct_name.getText() + "   ราคา :" + tf_editProduct_price.getText() + "บาท" + "   วันที่ :" + dateFormat.format(date).toString(), "UTF-8")));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					getDatabase("SELECT barcode, productName, price FROM product_data");
					tb_product.setModel(DbUtils.resultSetToTableModel(rec));
					JOptionPane.showMessageDialog(null, "แก้ไขสินค้าสำเร็จ!!");
					bt_editProduct_clear.doClick();
				}
			}
		});
		bt_editProduct.setBounds(476, 369, 164, 23);
		page_product.add(bt_editProduct);
		
		JButton bt_removeProduct = new JButton("\u0E25\u0E1A\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		bt_removeProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				if (tf_editProduct_barcode.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "กรุณาเลือกข้อมูล!!");
				}else {
					if (JOptionPane.showConfirmDialog(null, "ต้องการลบสินค้าหรือไม่?") == JOptionPane.YES_OPTION) {
						updateDatabase("DELETE FROM product_data WHERE barcode = '" + tf_editProduct_barcode.getText() + "'");
						getDatabase("SELECT barcode, productName, price FROM product_data");
						tb_product.setModel(DbUtils.resultSetToTableModel(rec));
						try {
							sendData((URLEncoder.encode("ลบสินค้า :" + tf_editProduct_name.getText() + "   ราคา :" + tf_editProduct_price.getText() + "บาท" + "   วันที่ :" + dateFormat.format(date).toString(), "UTF-8")));
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "ลบสินค้าสำเร็จ!!");
						bt_editProduct_clear.doClick();
					}
				}
			}
		});
		bt_removeProduct.setBounds(476, 403, 94, 23);
		page_product.add(bt_removeProduct);
		
		page_checkbill = new JPanel();
		frmshopeiei.getContentPane().add(page_checkbill, "name_378008694663200");
		page_checkbill.setLayout(null);
		
		JButton bt_checkbill_back = new JButton("\u0E22\u0E49\u0E2D\u0E19\u0E01\u0E25\u0E31\u0E1A");
		bt_checkbill_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page_checkbill.setVisible(false);
				page_menu.setVisible(true);
			}
		});
		bt_checkbill_back.setBounds(10, 11, 175, 23);
		page_checkbill.add(bt_checkbill_back);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 739, 286);
		page_checkbill.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"barcode", "\u0E0A\u0E37\u0E48\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32", "\u0E23\u0E32\u0E04\u0E32", "\u0E08\u0E33\u0E19\u0E27\u0E19"
			}
		));
		scrollPane_1.setViewportView(table);
		
		tf_checkbill_member = new JTextField();
		tf_checkbill_member.setBounds(99, 340, 131, 20);
		page_checkbill.add(tf_checkbill_member);
		tf_checkbill_member.setColumns(10);
		
		JLabel label_22 = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E2A\u0E21\u0E32\u0E0A\u0E34\u0E01 :");
		label_22.setHorizontalAlignment(SwingConstants.RIGHT);
		label_22.setBounds(0, 343, 89, 14);
		page_checkbill.add(label_22);
		
		JButton bt_checkbill_memberCheck = new JButton("\u0E15\u0E23\u0E27\u0E08\u0E2A\u0E2D\u0E1A");
		bt_checkbill_memberCheck.setBounds(240, 339, 106, 23);
		page_checkbill.add(bt_checkbill_memberCheck);
		
		tf_checkbill_barcode = new JTextField();
		tf_checkbill_barcode.setBounds(99, 371, 131, 20);
		page_checkbill.add(tf_checkbill_barcode);
		tf_checkbill_barcode.setColumns(10);
		
		JButton button_1 = new JButton("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		button_1.setBounds(240, 370, 106, 23);
		page_checkbill.add(button_1);
		
		JLabel label_23 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32 :");
		label_23.setHorizontalAlignment(SwingConstants.RIGHT);
		label_23.setBounds(10, 374, 79, 14);
		page_checkbill.add(label_23);
		
		JButton bt_checkbill_memberClear = new JButton("clear");
		bt_checkbill_memberClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_checkbill_member.setEditable(true);
				tf_checkbill_member.setText("");
			}
		});
		bt_checkbill_memberClear.setBounds(352, 339, 89, 23);
		page_checkbill.add(bt_checkbill_memberClear);
	}
}
