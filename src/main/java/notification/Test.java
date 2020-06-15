package notification;

public class Test {

	private String name;
	private String cin;
	private String token;

	public Test() {
		super();
	}

	public Test(String name, String cin, String token) {
		super();
		this.name = name;
		this.cin = cin;
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
