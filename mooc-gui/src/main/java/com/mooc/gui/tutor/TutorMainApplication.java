package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.mooc.domain.Course;
import com.mooc.domain.Tutor;

public class TutorMainApplication {

	private JFrame frmMoocMainWindow;
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
					window.frmMoocMainWindow.setVisible(true);
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
		frmMoocMainWindow = new JFrame();
		frmMoocMainWindow.setTitle("MOOC Main Window");
		Dimension dim = frmMoocMainWindow.getToolkit().getScreenSize();
		frmMoocMainWindow.setMinimumSize(new Dimension(dim.width / 2, dim.height / 2));
		frmMoocMainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frmMoocMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMoocMainWindow.setJMenuBar(menuBar);
		
		JMenu mnLogon = new JMenu("Tutor");
		menuBar.add(mnLogon);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TutorLoginView tutorLogin = new TutorLoginView(frmMoocMainWindow);
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
					CourseFactoryView courseFactoryView = new CourseFactoryView(frmMoocMainWindow, tutor);
					courseFactoryView.setVisible(true);
					refreshCourseList();
				}
			}
		});
		mnCourse.add(mntmCreateCourse);
		//
		
		JMenu mnStudent = new JMenu("Student");
		menuBar.add(mnStudent);
		
		JMenuItem mntmAddStudent = new JMenuItem("Add Student");
		
		mntmAddStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tutor == null) {
					JOptionPane.showMessageDialog(null, "You must login first");
				} else {
					//CourseFactoryView courseFactoryView = new CourseFactoryView(frmMoocMainWindow, tutor);
					//courseFactoryView.setVisible(true);
					//refreshCourseList();
				}
			}
		});
		mnStudent.add(mntmAddStudent);
		//
		
		
		Box verticalBox = Box.createVerticalBox();
		frmMoocMainWindow.getContentPane().add(verticalBox, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		verticalBox.add(scrollPane);
		
		tableCourses = new JTable();
//		frame.getContentPane().add(tableCourses, BorderLayout.SOUTH);
		tableCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCourses.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Course Title", "Course Description", "Action", "Action"}) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
			    return false;
			}
		});
		tableCourses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() > 1) {
					Integer index = (Integer) tableCourses.getModel().getValueAt(tableCourses.getSelectedRow(), 0);
					Course course = tutor.getCourses().get(index);
					if (tableCourses.getSelectedColumn() == 3) {
						ChapterFactoryView chapterFactoryView = new ChapterFactoryView(frmMoocMainWindow, course);
						chapterFactoryView.setVisible(true);
					} else if (tableCourses.getSelectedColumn() == 4) {
						CourseView courseView = new CourseView(frmMoocMainWindow, course);
						courseView.setVisible(true);
					}
				}
				
			}
		});
		scrollPane.setViewportView(tableCourses);
	}

	public void refreshCourseList() {
		if (tutor != null) {
			DefaultTableModel model = (DefaultTableModel) tableCourses.getModel();
			for(int i = model.getRowCount() - 1; i >= 0; i--) {
			   model.removeRow(i);
			}
			for (int i = 0; i < tutor.getCourses().size(); ++i) {
				Course course = tutor.getCourses().get(i);
				model.addRow(new Object[]{i, course.getTitle(), course.getDescription(), "Add New Chapter", "View Course Details"});
			}
			frmMoocMainWindow.repaint();
		}
	}

}
