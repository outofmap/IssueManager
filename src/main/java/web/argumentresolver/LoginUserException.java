package web.argumentresolver;

import org.springframework.core.ErrorCoded;

public class LoginUserException extends Exception implements ErrorCoded { 
	private static final long serialVersionUID = 1L;
	private static final String ERROR_CODE = "exception.login.User.";
    
    @Override
    public String getErrorCode() {
        return ERROR_CODE;
    }
}
