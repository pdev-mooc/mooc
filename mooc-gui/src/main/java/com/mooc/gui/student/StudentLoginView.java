package com.mooc.gui.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.mooc.domain.Student;
import com.mooc.domain.Tutor;
import com.mooc.services.UserRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;

public class StudentLoginView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected Student student;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public StudentLoginView(JFrame parent) {
		super(parent);
		setTitle("Student Login");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 386, 155);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(10, 26, 67, 14);
		contentPanel.add(lblNewLabel);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(129, 26, 241, 20);
		contentPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 60, 100, 14);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 60, 241, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserRemoteService userService = RemoteServiceDelegate.get(UserRemoteService.class);
						Person user = userService.findUser(textFieldEmail.getText(), new String(passwordField.getPassword()));
						if (user instanceof Student) {
							student = (Student) user;
							JOptionPane.showMessageDialog(null, "Hello " + student.getFirstName());
							setVisible(false);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Login or password is incorrect");
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public Student showDialog() {
		setVisible(true);
		return student;
	}

}
