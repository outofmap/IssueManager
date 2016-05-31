package issueManager.model;

import java.util.HashMap;
import java.util.List;

public class Project {
	Long projectId;
	String name;
	HashMap<String, User> userList;
	
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
	public HashMap<String, User> getUserList() {
		return userList;
	}
	
	public void setUserList(HashMap<String, User> userList) {
		this.userList = userList;
	}
	
	public User addUserList(User user){
		return this.userList.put(user.getEmail(), user);
	}
	
	public boolean hasUser(String email){
		return this.userList.containsKey(email);
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
	
	
	
}
