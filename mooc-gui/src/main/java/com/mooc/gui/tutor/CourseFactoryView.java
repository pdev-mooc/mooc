package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mooc.domain.Course;
import com.mooc.domain.Tutor;
import com.mooc.services.CourseRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;

public class CourseFactoryView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCourseTitle;
	private Tutor tutor;
	private JTextArea textAreaCourseDesc;

	/**
	 * Create the dialog.
	 * @param tutor 
	 */
	public CourseFactoryView(JFrame parent, Tutor tutor) {
		super(parent);
		setModalityType(ModalityType.APPLICATION_MODAL);
		this.tutor = tutor;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCourseTitle = new JLabel("Course Title");
			GridBagConstraints gbc_lblCourseTitle = new GridBagConstraints();
			gbc_lblCourseTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblCourseTitle.gridx = 2;
			gbc_lblCourseTitle.gridy = 0;
			contentPanel.add(lblCourseTitle, gbc_lblCourseTitle);
		}
		{
			textFieldCourseTitle = new JTextField();
			GridBagConstraints gbc_textFieldCourseTitle = new GridBagConstraints();
			gbc_textFieldCourseTitle.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldCourseTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCourseTitle.gridx = 5;
			gbc_textFieldCourseTitle.gridy = 0;
			contentPanel.add(textFieldCourseTitle, gbc_textFieldCourseTitle);
			textFieldCourseTitle.setColumns(10);
		}
		{
			JLabel lblCourseDescription = new JLabel("Course Description");
			GridBagConstraints gbc_lblCourseDescription = new GridBagConstraints();
			gbc_lblCourseDescription.insets = new Insets(0, 0, 0, 5);
			gbc_lblCourseDescription.gridx = 2;
			gbc_lblCourseDescription.gridy = 1;
			contentPanel.add(lblCourseDescription, gbc_lblCourseDescription);
		}
		{
			textAreaCourseDesc = new JTextArea();
			GridBagConstraints gbc_textAreaCourseDesc = new GridBagConstraints();
			gbc_textAreaCourseDesc.fill = GridBagConstraints.BOTH;
			gbc_textAreaCourseDesc.gridx = 5;
			gbc_textAreaCourseDesc.gridy = 1;
			contentPanel.add(textAreaCourseDesc, gbc_textAreaCourseDesc);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Course course = new Course();
						course.setTitle(textFieldCourseTitle.getText());
						course.setDescription(textAreaCourseDesc.getText());
						course.setTutor(tutor);
						CourseRemoteService courseRS = RemoteServiceDelegate.get(CourseRemoteService.class);
						if (courseRS.create(course)) {
							JOptionPane.showMessageDialog(null, "Course created successfully");
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

}
