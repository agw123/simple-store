package bean;

public class User {

	private long id;
	private String username;
	private String password;
	private int userType;
	
	public User() {}

	public User(long id, String username, String password, int userType) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	public User(long id, String username, int userType) {
		this.id = id;
		this.username = username;
		this.userType = userType;
	}

	public User(long id, String username) {
		this.id = id;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}
	
	
}
