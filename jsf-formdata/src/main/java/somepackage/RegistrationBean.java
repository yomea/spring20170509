package somepackage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegistrationBean {
	private String firstName, lastName, emailAddress;

	public String getFirstName() {
		return (firstName);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String doRegistration() {
		if (FormUtils.isAnyMissing(firstName, lastName, emailAddress)) {
			return ("registration-success");
		} else {
			return ("registration-error");
		}
	}

}
