package dto;

public class LoginDto {
	private String username;
	private String password;
	
	// alt + shift + s  에서 getter, setter 만들기 (까먹지 말자 !)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
