package com.mooc.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

import com.mooc.domain.Student;
import com.mooc.util.SessionUtils;

@ManagedBean
public class FileUploadView {

	private UploadedFile uploadedFile;

	public UploadedFile getFile() {
		return uploadedFile;
	}

	public void setFile(UploadedFile file) {
		this.uploadedFile = file;
	}

	public boolean upload() {
		if (uploadedFile != null) {
			FacesMessage message = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
			Student student = SessionUtils.getLoggedInStudent();

			String baseDir = System.getProperty("jboss.home.dir");
			Path folder = Paths.get(baseDir + "/student/uploads");
			String filename = FilenameUtils.getBaseName(uploadedFile.getFileName()); 
			String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
			try {
				Path file = Files.createTempFile(folder, filename + "-", "." + extension);
				try (InputStream input = uploadedFile.getInputstream()) {
				    Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
				}
				System.out.println("Uploaded file successfully saved in " + file);
				student.setProfilePictureURL("http://localhost:8080/images/" + file.getFileName().toString());
			} catch (IOException e) {
				return false;
			}
			FacesContext.getCurrentInstance().addMessage(null, message);
			return true;
		}
		return false;
	}

}