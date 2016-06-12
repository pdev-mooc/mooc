package com.mooc.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.mooc.domain.Person;
import com.mooc.services.UserRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;
import com.mooc.services.util.ServiceLocator;
import javax.swing.JPasswordField;

public class MainApplication {

	private JFrame frame;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication window = new MainApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Email");
		frame.getContentPane().add(lblNewLabel, "4, 2");
		
		emailTextField = new JTextField();
		frame.getContentPane().add(emailTextField, "8, 2, fill, default");
		emailTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		frame.getContentPane().add(lblNewLabel_1, "4, 4");
		
		passwordTextField = new JPasswordField();
		frame.getContentPane().add(passwordTextField, "8, 4, fill, default");
		passwordTextField.setColumns(10);
		
		JButton btnLoginButton = new JButton("Login");
		btnLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRemoteService userService = RemoteServiceDelegate.get(UserRemoteService.class);
	            Person user = userService.findUser(emailTextField.getText(), new String(passwordTextField.getPassword()));
	            JOptionPane.showMessageDialog(null, "Hello " + user.getFirstName());
			}
		});
		frame.getContentPane().add(btnLoginButton, "4, 6");
	}

}
