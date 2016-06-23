package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mooc.domain.Course;
import com.mooc.domain.Student;
import com.mooc.gui.StudentJListItem;

public class EnrolledStudentsView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList listEnrolledStudents;

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public EnrolledStudentsView(JDialog parent, Course course, List<Student> enrolledStudents) {
		super(parent);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			listEnrolledStudents = new JList();
			List<StudentJListItem> items = new ArrayList<>();
			for (Student student : enrolledStudents) {
				items.add(new StudentJListItem(student));
			}
			listEnrolledStudents.setListData(items.toArray());
			contentPanel.add(listEnrolledStudents);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
