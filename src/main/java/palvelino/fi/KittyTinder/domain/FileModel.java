package palvelino.fi.KittyTinder.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class FileModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fileid;
	private String fileName, mimeType, base64str;

	@Lob
	private byte[] file;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "filemodel")
	private List<Kitty> kitties;

	
	public FileModel() {}
	
	public FileModel(String fileName, String mimeType, byte[] file) {
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.file = file;
	}

	public long getFileid() {
		return fileid;
	}

	public void setFileid(long fileid) {
		this.fileid = fileid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}


}
