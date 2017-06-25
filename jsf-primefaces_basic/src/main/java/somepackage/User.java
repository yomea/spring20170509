package somepackage;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class User {

	private String username;

	private String passworld;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassworld() {
		return passworld;
	}

	public void setPassworld(String passworld) {
		this.passworld = passworld;
	}

	public String register() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(username.length()<3) {
			FacesMessage errorMessage = new FacesMessage("姓名长度不能少于3个字符");
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);//设置严重性为ERROR
			context.addMessage("message", errorMessage);
			return null;
		} else {
			return ("info");
		}
	}

}
