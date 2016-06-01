package issueManager.model;

public class Issue {
	private Long issueId;
	private String writer;
	private String title;
	private String contents;
	private Long proejctId;
	private String user_email;
	private String status;
	private Long labelId;
	private Long milestoneId;
	private String assignee;
	private Long file_Id;
	private String assignee_email;

	public Issue() {
	};

	public Issue(Long id, String writer, String title, String userEmail) {
		this.issueId = id;
		this.writer = writer;
		this.title = title;
		this.user_email = userEmail;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getProejctId() {
		return proejctId;
	}

	public void setProejctId(Long proejctId) {
		this.proejctId = proejctId;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Long getFile_Id() {
		return file_Id;
	}

	public void setFile_Id(Long file_Id) {
		this.file_Id = file_Id;
	}

	public String getAssignee_email() {
		return assignee_email;
	}

	public void setAssignee_email(String assignee_email) {
		this.assignee_email = assignee_email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
		result = prime * result + ((proejctId == null) ? 0 : proejctId.hashCode());
		result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (issueId == null) {
			if (other.issueId != null)
				return false;
		} else if (!issueId.equals(other.issueId))
			return false;
		if (proejctId == null) {
			if (other.proejctId != null)
				return false;
		} else if (!proejctId.equals(other.proejctId))
			return false;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", writer=" + writer + ", title=" + title + ", contents=" + contents
				+ ", proejctId=" + proejctId + ", user_email=" + user_email + ", status=" + status + ", labelId="
				+ labelId + ", milestoneId=" + milestoneId + ", assignee=" + assignee + ", file_Id=" + file_Id
				+ ", assignee_email=" + assignee_email + "]";
	}

}
