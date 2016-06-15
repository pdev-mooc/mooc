package com.mooc.gui.student;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.mooc.domain.Student;
import com.mooc.gui.tutor.StudentFactoryView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentMainApplication {

	private JFrame frame;
	protected Student student;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMainApplication window = new StudentMainApplication();
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
	public StudentMainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MOOC Student Application");
		Dimension dim = frame.getToolkit().getScreenSize();
		frame.setMinimumSize(new Dimension(dim.width / 2, dim.height / 2));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnStudent = new JMenu("Student");
		menuBar.add(mnStudent);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				StudentLoginView studentLoginView = new StudentLoginView(frame);
				student = studentLoginView.showDialog();
			}
		});
		mnStudent.add(mntmLogin);
		
		JMenuItem mntmRegister = new JMenuItem("Register");
		mntmRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				StudentFactoryView studentFactoryView = new StudentFactoryView(frame, null);
				studentFactoryView.setVisible(true);
			}
		});
		mnStudent.add(mntmRegister);
	}

}
