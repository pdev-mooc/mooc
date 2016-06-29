package com.mooc.gui.tutor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mooc.domain.Comment;
import com.mooc.domain.Tutor;
import com.mooc.services.CommentRemoteService;
import com.mooc.services.util.RemoteServiceDelegate;

import net.atlanticbb.tantlinger.shef.HTMLEditorPane;


public class CommentFactory extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCommentDate;
	private Tutor tutor;
	private JTextArea textAreaCommentDesc;
	private HTMLEditorPane editor;
	
	public CommentFactory(JFrame parent, Tutor tutor) {
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
			JLabel lblCommentDate = new JLabel("Comment Date Time");
			GridBagConstraints gbc_lblCommentDate = new GridBagConstraints();
			gbc_lblCommentDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblCommentDate.gridx = 2;
			gbc_lblCommentDate.gridy = 0;
			contentPanel.add(lblCommentDate, gbc_lblCommentDate);
		}
		{
			textFieldCommentDate = new JTextField();
			GridBagConstraints gbc_textFieldCommentDate = new GridBagConstraints();
			gbc_textFieldCommentDate.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldCommentDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCommentDate.gridx = 5;
			gbc_textFieldCommentDate.gridy = 0;
			contentPanel.add(textFieldCommentDate, gbc_textFieldCommentDate);
			textFieldCommentDate.setColumns(10);
		}
//		{
//			JLabel lblCommentText = new JLabel("Comment Text");
//			GridBagConstraints gbc_lblCommentText = new GridBagConstraints();
//			gbc_lblCommentText.insets = new Insets(0, 0, 0, 5);
//			gbc_lblCommentText.gridx = 2;
//			gbc_lblCommentText.gridy = 1;
//			contentPanel.add(lblCommentText, gbc_lblCommentText);
//		}
		{
			JLabel lblChapterBody = new JLabel("Description");
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
						Comment comment = new Comment();
						comment.setText(editor.getText());
						comment.setDateTime(new Date());
						CommentRemoteService commentRS = RemoteServiceDelegate.get(CommentRemoteService.class);
						if(commentRS.create(comment)){
							JOptionPane.showMessageDialog(null, "Comment created successfully");
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
