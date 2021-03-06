package issueManager.model;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Project {
	private Long projectId;
	@NotBlank
	@Size(min=2,max=100,message="최소2글자, 최대 100글자입니다.")
	private String name;
	private List<User> userList;
	
	public Project(){};
	
	public Project(long projectId, String name) {
		this.projectId = projectId;
		this.name = name;
	};

	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((userList == null) ? 0 : userList.hashCode());
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
		Project other = (Project) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", userList=" + userList + "]";
	}

	public boolean hasUser(String email) {
		for (User user : this.userList) {
			if(user.checkUserEmail(email)){
				return true;
			}
		}
		return false;
	}
	
}
