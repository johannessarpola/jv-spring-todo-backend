package fi.johannes.dto;

public class TodoDto {
	

	UserDto creator;
	
	String entry;
	String created;
	String deadline;
	Boolean done;

	public TodoDto() {
	}

	public UserDto getCreatorEmail() {
		return creator;
	}

	public void setCreator(UserDto creator) {
		this.creator = creator;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	
}
