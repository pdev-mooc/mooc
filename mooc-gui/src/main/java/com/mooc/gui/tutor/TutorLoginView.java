package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mooc.domain.Person;
import com.mooc.domain.Tutor;
import com.mooc.services.UserRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;

public class TutorLoginView extends JDialog {

	private JPanel contentPane;
	private JTextField emailField;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	private JButton btnTutorLogin;
	private JLabel lblNewLabel_2;
	private Tutor tutor;

	/**
	 * Create the frame.
	 * @param frame 
	 */
	public TutorLoginView(JFrame frame) {
		super(frame);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel_2 = new JLabel("Tutor Login");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 1;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		emailField = new JTextField();
		emailField.setText("gorwell@mooc.com");
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 6;
		gbc_emailField.gridy = 3;
		contentPane.add(emailField, gbc_emailField);
		emailField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 6;
		gbc_passwordField.gridy = 5;
		contentPane.add(passwordField, gbc_passwordField);
		
		btnTutorLogin = new JButton("Login");
		btnTutorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRemoteService userService = RemoteServiceDelegate.get(UserRemoteService.class);
				Person user = userService.findUser(emailField.getText(), new String(passwordField.getPassword()));
				if (user instanceof Tutor) {
					tutor = (Tutor) user;
					JOptionPane.showMessageDialog(null, "Hello " + tutor.getFirstName());
					setVisible(false);
					dispose();
				}
			}
		});
		GridBagConstraints gbc_btnTutorLogin = new GridBagConstraints();
		gbc_btnTutorLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnTutorLogin.gridx = 5;
		gbc_btnTutorLogin.gridy = 7;
		contentPane.add(btnTutorLogin, gbc_btnTutorLogin);
	}

	public Tutor showDialog() {
		setVisible(true);
		return tutor;
	}

}
