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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mooc.domain.Chapter;
import com.mooc.domain.Course;
import com.mooc.services.ChapterRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;

import net.atlanticbb.tantlinger.shef.HTMLEditorPane;

public class ChapterFactoryView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldChapterTitle;
	private JTextField textFieldChapterRecap;
	private HTMLEditorPane editor;

	/**
	 * Create the dialog.
	 */
	public ChapterFactoryView(JFrame parent, Course course) {
		setBounds(100, 100, 822, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblChapterTitle = new JLabel("Title");
			GridBagConstraints gbc_lblChapterTitle = new GridBagConstraints();
			gbc_lblChapterTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblChapterTitle.gridx = 2;
			gbc_lblChapterTitle.gridy = 0;
			contentPanel.add(lblChapterTitle, gbc_lblChapterTitle);
		}
		{
			textFieldChapterTitle = new JTextField();
			GridBagConstraints gbc_textFieldChapterTitle = new GridBagConstraints();
			gbc_textFieldChapterTitle.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldChapterTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldChapterTitle.gridx = 5;
			gbc_textFieldChapterTitle.gridy = 0;
			contentPanel.add(textFieldChapterTitle, gbc_textFieldChapterTitle);
			textFieldChapterTitle.setColumns(10);
		}
		{
			JLabel lblChapterRecap = new JLabel("Recap");
			GridBagConstraints gbc_lblChapterRecap = new GridBagConstraints();
			gbc_lblChapterRecap.insets = new Insets(0, 0, 5, 5);
			gbc_lblChapterRecap.gridx = 2;
			gbc_lblChapterRecap.gridy = 1;
			contentPanel.add(lblChapterRecap, gbc_lblChapterRecap);
		}
		{
			textFieldChapterRecap = new JTextField();
			GridBagConstraints gbc_textFieldChapterRecap = new GridBagConstraints();
			gbc_textFieldChapterRecap.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldChapterRecap.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldChapterRecap.gridx = 5;
			gbc_textFieldChapterRecap.gridy = 1;
			contentPanel.add(textFieldChapterRecap, gbc_textFieldChapterRecap);
			textFieldChapterRecap.setColumns(10);
		}
		{
			JLabel lblChapterBody = new JLabel("Body");
			GridBagConstraints gbc_lblChapterBody = new GridBagConstraints();
			gbc_lblChapterBody.insets = new Insets(0, 0, 0, 5);
			gbc_lblChapterBody.gridx = 2;
			gbc_lblChapterBody.gridy = 2;
			contentPanel.add(lblChapterBody, gbc_lblChapterBody);
		}
		{
			GridBagConstraints gbc_editorPane = new GridBagConstraints();
			gbc_editorPane.insets = new Insets(0, 0, 0, 5);
			gbc_editorPane.fill = GridBagConstraints.BOTH;
			gbc_editorPane.gridx = 5;
			gbc_editorPane.gridy = 2;
//			contentPanel.add(editorPane, gbc_editorPane);
			editor = new HTMLEditorPane();
			contentPanel.add(editor, gbc_editorPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ChapterRemoteService chapterService = RemoteServiceDelegate.get(ChapterRemoteService.class);
						String title = textFieldChapterTitle.getText();
						String recap = textFieldChapterRecap.getText();
						String body = editor.getText();
						Chapter chapter = new Chapter(title, recap, body, course);
						if (chapterService.create(chapter)) {
							JOptionPane.showMessageDialog(null, "Chapter was successfully added");
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
