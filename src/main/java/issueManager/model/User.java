package issueManager.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
	public static final GuestUser GUESTUSER = new GuestUser();
	private static final Logger log = LoggerFactory.getLogger(User.class);
	// userId 를 email로 대신 
	@NotBlank
	@Email(message="email형식에 맞게 입력해주세요.")
	private String email;
	@Size(min=2,max=10,message="이름은 최소 2글자, 최대10글자 입니다.")
	@NotBlank
	private String name;
	@Size(min=6,max=12,message="비밀번호는 최소 6글자, 최대12글자 입니다.")
	@NotBlank
	private String password;

	public User() {}

	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSameUser(User user){
		//parameter user = DB에서 찾아 온 user  
		// true이면, DB에서 찾아온 user와 loginUser가 같은 사람.
		// false이면, DB에서 찾아온 user와 loginUser가 다름 또는 GUESTUSER 상태. 
		return checkUserEmail(user.getEmail());
	}
	
	public boolean checkUserEmail(String newUserEmail) {
		//this.email = email, this는 loginUser 인 상태 
		//login user는 GUESTUSER 또는 session에서 저장된 USER인 상태이다. 
		if(email == null){
			//email == null 이면, GEUSTUSER이다. 
			return false;
		}
		return email.equals(newUserEmail);
	}
	
	public boolean isGuestUser() {
		return false;
	}
	
	private static class GuestUser extends User {
		//GuestUser생성 시 모든 것을 User에게 상속 받되, isGuestUser = true 인 상태로 생성됨.
		@Override
		public boolean isGuestUser() {
			return true;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + "]";
	}

	public String encryptPassword(String originalPass) {
		//log.debug("User plain : {}", originalPass);
        //password hashed 후에 다시 저장하기! 
        String hashed = BCrypt.hashpw(originalPass, BCrypt.gensalt(11));
        log.debug("hashed pw: {}", hashed);
        return hashed;
	}

	public boolean matchPassword(String originalPass, String savedPass) {
		return BCrypt.checkpw(originalPass,savedPass);
	}

}
