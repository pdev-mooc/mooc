package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mooc.domain.Student;
import com.mooc.domain.Tutor;
import com.mooc.services.CourseRemoteService;
import com.mooc.services.UserRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.List;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentFactoryView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	
	/**
	 * Create the dialog.
	 */
	public StudentFactoryView(JFrame parent, Tutor tutor) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JLabel lblFirstName = new JLabel("First name :");
			contentPanel.add(lblFirstName, "6, 6");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "12, 6, 11, 1, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last name :");
			contentPanel.add(lblLastName, "6, 8");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "12, 8, 11, 1, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email :");
			contentPanel.add(lblEmail, "6, 10");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "12, 10, 11, 1, fill, default");
			textField_2.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			contentPanel.add(lblPassword, "6, 12");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "12, 12, 11, 1, fill, default");
		}
		{
			JLabel lblCourses = new JLabel("Course(s) :");
			contentPanel.add(lblCourses, "6, 14");
		}
		{
			Choice list = StudentFactoryCourses.createChoice();
			contentPanel.add(list, "12, 14, 11, 1");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Validate");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Student student = new Student();
						//student.setTitle(textFieldCourseTitle.getText());
						student.setFirstName(textField.getText());
						student.setLastName(textField_1.getText());
						student.setEmail(textField_2.getText());
						student.setPassword(String.valueOf(passwordField.getPassword()));
						UserRemoteService userService = RemoteServiceDelegate.get(UserRemoteService.class);
						if (userService.create(student)) {
							JOptionPane.showMessageDialog(null, "Student created successfully");
						}
						setVisible(false);
						dispose();
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
