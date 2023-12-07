package ir.sajjadyosefi.android.xTubeless.classes.model;

import java.io.Serializable;


public class NotifyItem implements Serializable {


	private String message = null;
	private String user_image = null;
	private String link = null;
	private String date = null;
	private String user_id = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser_image() {
		return  user_image ;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



}
