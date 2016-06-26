package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.fop.render.pdf.PDFContentGenerator;
import org.xml.sax.SAXException;

import com.mooc.domain.Chapter;
import com.mooc.domain.Course;
import com.mooc.domain.Student;
import com.mooc.fop.PDFGenerator;
import com.mooc.gui.ChapterComboBoxModel;
import com.mooc.services.ChapterRemoteService;
import com.mooc.services.StudentRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;

import net.atlanticbb.tantlinger.shef.HTMLEditorPane;

public class CourseView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Course course;
	private JComboBox comboBox;
	private HTMLEditorPane htmlEditor;
	private List<Student> enrolledStudents;
	private JLabel lblNbEnrolledStudents;

	/**
	 * Create the dialog.
	 */
	public CourseView(JFrame parent, Course course) {
		super(parent);
		setModalityType(ModalityType.APPLICATION_MODAL);
		this.course = course;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTitle = new JLabel("Title:");
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitle.gridx = 1;
			gbc_lblTitle.gridy = 0;
			contentPanel.add(lblTitle, gbc_lblTitle);
		}
		{
			JLabel lblCourseTitle = new JLabel(course.getTitle());
			GridBagConstraints gbc_lblCourseTitle = new GridBagConstraints();
			gbc_lblCourseTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblCourseTitle.gridx = 4;
			gbc_lblCourseTitle.gridy = 0;
			contentPanel.add(lblCourseTitle, gbc_lblCourseTitle);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			GridBagConstraints gbc_lblDescription = new GridBagConstraints();
			gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescription.gridx = 1;
			gbc_lblDescription.gridy = 1;
			contentPanel.add(lblDescription, gbc_lblDescription);
		}
		{
			JLabel lblCourseDescription = new JLabel(course.getDescription());
			GridBagConstraints gbc_lblCourseDescription = new GridBagConstraints();
			gbc_lblCourseDescription.insets = new Insets(0, 0, 5, 0);
			gbc_lblCourseDescription.gridx = 4;
			gbc_lblCourseDescription.gridy = 1;
			contentPanel.add(lblCourseDescription, gbc_lblCourseDescription);
		}
		{
			Box verticalBox = Box.createVerticalBox();
			GridBagConstraints gbc_verticalBox = new GridBagConstraints();
			gbc_verticalBox.insets = new Insets(0, 0, 5, 5);
			gbc_verticalBox.gridx = 0;
			gbc_verticalBox.gridy = 2;
			contentPanel.add(verticalBox, gbc_verticalBox);
		}
		{
			JLabel lblSelectChapter = new JLabel("Select Chapter");
			GridBagConstraints gbc_lblSelectChapter = new GridBagConstraints();
			gbc_lblSelectChapter.insets = new Insets(0, 0, 5, 5);
			gbc_lblSelectChapter.gridx = 1;
			gbc_lblSelectChapter.gridy = 3;
			contentPanel.add(lblSelectChapter, gbc_lblSelectChapter);
		}
		{
			comboBox = new JComboBox();
			comboBox.setMaximumRowCount(128);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 4;
			gbc_comboBox.gridy = 3;
			for (Chapter chapter : course.getChapters()) {
				comboBox.addItem(new ChapterComboBoxModel(chapter));
			}
			comboBox.setSelectedIndex(-1);
			comboBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					Chapter chapter = ((ChapterComboBoxModel) e.getItem()).getChapter();
					htmlEditor.setVisible(true);
					htmlEditor.setText(chapter.getBody());
					htmlEditor.updateUI();
					repaint();
				}
			});
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			htmlEditor = new HTMLEditorPane();
			htmlEditor.setVisible(false);
			{
				JLabel lblEnrolledStudents = new JLabel("Nb. Enrolled Students");
				GridBagConstraints gbc_lblEnrolledStudents = new GridBagConstraints();
				gbc_lblEnrolledStudents.insets = new Insets(0, 0, 5, 5);
				gbc_lblEnrolledStudents.gridx = 1;
				gbc_lblEnrolledStudents.gridy = 4;
				contentPanel.add(lblEnrolledStudents, gbc_lblEnrolledStudents);
			}
			{
				lblNbEnrolledStudents = new JLabel("New label");
				GridBagConstraints gbc_lblNbEnrolledStudents = new GridBagConstraints();
				gbc_lblNbEnrolledStudents.insets = new Insets(0, 0, 5, 0);
				gbc_lblNbEnrolledStudents.gridx = 4;
				gbc_lblNbEnrolledStudents.gridy = 4;
				contentPanel.add(lblNbEnrolledStudents, gbc_lblNbEnrolledStudents);
			}
			{
				JButton btnViewEnrolledStudents = new JButton("View Enrolled Students");
				btnViewEnrolledStudents.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						EnrolledStudentsView enrolledStudentsView = new EnrolledStudentsView(CourseView.this, course, enrolledStudents);
						enrolledStudentsView.showDialog();
						updateEnrolledStudentsList();
					}
				});
				GridBagConstraints gbc_btnViewEnrolledStudents = new GridBagConstraints();
				gbc_btnViewEnrolledStudents.insets = new Insets(0, 0, 5, 0);
				gbc_btnViewEnrolledStudents.gridx = 4;
				gbc_btnViewEnrolledStudents.gridy = 5;
				contentPanel.add(btnViewEnrolledStudents, gbc_btnViewEnrolledStudents);
			}
			{
				JButton btnNewButton = new JButton("Generate PDF");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							FileOutputStream fos = new FileOutputStream(new File("course-overview.xml"));
							PrintWriter printW = new PrintWriter(fos);
							printW.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
							printW.write("<root>");
							printW.write("<course-title>" + course.getTitle() + "</course-title>");
							printW.write("<course-description>" + course.getDescription() + "</course-description>");
							printW.write("</root>");
							printW.close();
							try {
								PDFGenerator pdfGen = new PDFGenerator();
								if (pdfGen.generatePDF(new FileInputStream(new File("course-overview.xml")), "course-"
										+ course.getTitle().toLowerCase() + "-overview.pdf")) {
									JOptionPane.showMessageDialog(null, "PDF docs created successfully");
								} else {
									JOptionPane.showMessageDialog(null, "Failed to create PDF doc!");
								}
							} catch (SAXException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton.gridx = 4;
				gbc_btnNewButton.gridy = 6;
				contentPanel.add(btnNewButton, gbc_btnNewButton);
			}
			GridBagConstraints gbc_editorPane = new GridBagConstraints();
			gbc_editorPane.gridwidth = 4;
			gbc_editorPane.fill = GridBagConstraints.BOTH;
			gbc_editorPane.gridx = 1;
			gbc_editorPane.gridy = 7;
			contentPanel.add(htmlEditor, gbc_editorPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (comboBox.getSelectedItem() != null) {
							Chapter ch = ((ChapterComboBoxModel) comboBox.getSelectedItem()).getChapter();
							// Persist data
							ChapterRemoteService chapterService = RemoteServiceDelegate.get(ChapterRemoteService.class);
							ch.setBody(htmlEditor.getText());
							chapterService.update(ch);
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
		updateEnrolledStudentsList();
	}

	private void updateEnrolledStudentsList() {
		// Fetch the list of enrolled students
//		StudentCourseRemoteService studentCourseRemoteService = RemoteServiceDelegate.get(StudentCourseRemoteService.class);
//		List<StudentCourse> studentCourse = studentCourseRemoteService.findAll();
		StudentRemoteService studentRemoteService = RemoteServiceDelegate.get(StudentRemoteService.class);
		enrolledStudents = studentRemoteService.findEnrolledStudentsByCourseId(course.getId());
		lblNbEnrolledStudents.setText(String.valueOf(enrolledStudents.size()));
		repaint();
	}

}
