package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.mooc.domain.Course;
import com.mooc.domain.Tutor;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class TutorMainApplication {

	private JFrame frame;
	private Tutor tutor;
	private JTable tableCourses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutorMainApplication window = new TutorMainApplication();
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
	public TutorMainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnLogon = new JMenu("Tutor");
		menuBar.add(mnLogon);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TutorLoginView tutorLogin = new TutorLoginView(frame);
				tutor = tutorLogin.showDialog();
				refreshCourseList();
			}
		});
		mnLogon.add(mntmLogin);

		JMenu mnCourse = new JMenu("Course");
		menuBar.add(mnCourse);
		
		JMenuItem mntmCreateCourse = new JMenuItem("Create Course");
		mntmCreateCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tutor == null) {
					JOptionPane.showMessageDialog(null, "You must login first");
				} else {
					CourseFactoryView courseFactoryView = new CourseFactoryView(frame, tutor);
					courseFactoryView.setVisible(true);
					refreshCourseList();
				}
			}
		});
		mnCourse.add(mntmCreateCourse);
		
		tableCourses = new JTable();
		tableCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCourses.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Course Title", "Course Description"
			}
		));
		frame.getContentPane().add(tableCourses, BorderLayout.CENTER);
	}

	public void refreshCourseList() {
		if (tutor != null) {
			tableCourses.removeAll();
			DefaultTableModel model = (DefaultTableModel) tableCourses.getModel();
			for (Course course : tutor.getCourses()) {
				model.addRow(new Object[]{course.getId(), course.getTitle(), course.getDescription()});
			}
			tableCourses.updateUI();
		}
	}

}
