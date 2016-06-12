package com.mooc.gui.tutor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TutorMainApplication {

	private JFrame frame;

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
				tutorLogin.setVisible(true);
			}
		});
		mnLogon.add(mntmLogin);
		
		JMenu mnCourse = new JMenu("Course");
		menuBar.add(mnCourse);
		
		JMenuItem mntmCreateCourse = new JMenuItem("Create Course");
		mntmCreateCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CourseFactoryView courseFactoryView = new CourseFactoryView(frame);
				courseFactoryView.setVisible(true);
			}
		});
		mnCourse.add(mntmCreateCourse);
	}

}
