package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.mooc.domain.Course;
import com.mooc.domain.Student;
import com.mooc.gui.CourseListModel;
import com.mooc.services.UserRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;

public class StudentFactoryView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	protected Student student = new Student();

	
	/**
	 * Create the dialog.
	 * @param tutor 
	 */
	public StudentFactoryView(JFrame parent, List<Course> courses) {
		setTitle("Student Signup");
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
			textFieldFirstName = new JTextField();
			contentPanel.add(textFieldFirstName, "12, 6, 11, 1, fill, default");
			textFieldFirstName.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last name :");
			contentPanel.add(lblLastName, "6, 8");
		}
		{
			textFieldLastName = new JTextField();
			contentPanel.add(textFieldLastName, "12, 8, 11, 1, fill, default");
			textFieldLastName.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email :");
			contentPanel.add(lblEmail, "6, 10");
		}
		{
			textFieldEmail = new JTextField();
			contentPanel.add(textFieldEmail, "12, 10, 11, 1, fill, default");
			textFieldEmail.setColumns(10);
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
			JList listCourses = new JList();
			listCourses.setModel(new CourseListModel(courses));
			listCourses.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					List<Course> studentCourses = student.getCourses();
					studentCourses.clear();
					JList lsm = (JList) e.getSource();
					int[] indices = lsm.getSelectedIndices();
					for (int i = 0; i < indices.length; ++i) {
						Course c = courses.get(i);
						studentCourses.add(c);
					}
				}
			});
			contentPanel.add(listCourses, "12, 14, 11, 1, fill, fill");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Validate");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String firstName = textFieldFirstName.getText();
						String lastName = textFieldLastName.getText();
						String email = textFieldEmail.getText();
						String password = String.valueOf(passwordField.getPassword());
						if (firstName.isEmpty() || lastName.isEmpty()
							|| email.isEmpty() || password.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Empty fields are not allowed");
						} else {
							student.setFirstName(firstName);
							student.setLastName(lastName);
							student.setEmail(email);
							student.setPassword(password);
							UserRemoteService userService = RemoteServiceDelegate.get(UserRemoteService.class);
							if (userService.persist(student)) {
								JOptionPane.showMessageDialog(null, "Student created successfully");
							}
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
