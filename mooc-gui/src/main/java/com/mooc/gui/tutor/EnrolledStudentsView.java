package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mooc.domain.Course;
import com.mooc.domain.Student;
import com.mooc.gui.StudentJListItem;
import com.mooc.services.StudentRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnrolledStudentsView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList listEnrolledStudents;

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public EnrolledStudentsView(JDialog parent, Course course, List<Student> enrolledStudents) {
		super(parent);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			List<StudentJListItem> items = new ArrayList<>();
			for (Student student : enrolledStudents) {
				items.add(new StudentJListItem(student));
			}
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[] {30, 0, 0, 0, 0, 0, 0, 30, 0, 30, 30, 0, 30, 0};
			gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
		}
		listEnrolledStudents = new JList();
		listEnrolledStudents.setSelectedIndex(0);
		List<StudentJListItem> items = new ArrayList<>();
		for (Student student : enrolledStudents) {
			items.add(new StudentJListItem(student));
		}
		listEnrolledStudents.setListData(items.toArray());
		contentPanel.add(listEnrolledStudents);
		GridBagConstraints gbc_listEnrolledStudents = new GridBagConstraints();
		gbc_listEnrolledStudents.insets = new Insets(0, 0, 5, 0);
		gbc_listEnrolledStudents.gridheight = 2;
		gbc_listEnrolledStudents.gridwidth = 10;
		gbc_listEnrolledStudents.fill = GridBagConstraints.BOTH;
		gbc_listEnrolledStudents.gridx = 1;
		gbc_listEnrolledStudents.gridy = 0;
		contentPanel.add(listEnrolledStudents, gbc_listEnrolledStudents);
		{
			JButton btnNewButton = new JButton("Unregister Selected Students");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List selectedItems = listEnrolledStudents.getSelectedValuesList();
					if (selectedItems.size() > 0) {
						StudentRemoteService studentRemoteService = RemoteServiceDelegate.get(StudentRemoteService.class);

						for (Object obj : selectedItems) {
							StudentJListItem studentListItem = (StudentJListItem) obj;
							Student student = studentListItem.getStudent();
							student.getCourses().remove(course);
							studentRemoteService.persist(student);
						}

						JOptionPane.showMessageDialog(null, selectedItems.size() + " students have been unregistered from this course.");
						setVisible(false);
						dispose();
					}
				}
			});
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 2;
			contentPanel.add(btnNewButton, gbc_btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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

	public void showDialog() {
		setVisible(true);
	}

}
