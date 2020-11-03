package security;

import java.io.Serializable;
public class JwtRequest implements Serializable {
	
	private static final long serialVersionUID = -4551066319296944052L;
	private String username;
	private String password;
	private String mobile;	
	private String otp;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
	}
	public JwtRequest(String username, String password, String mobile, String otp) {
		this.setUsername(username);
		this.setPassword(password);
		this.setMobile(mobile);
		this.setOtp(otp);
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
}