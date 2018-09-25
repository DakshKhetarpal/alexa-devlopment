package alexa_dev;

public class User {
	private String name;
	private String balance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User() {
	}
	public User(String name, String balance) {
		super();
		this.name = name;
		this.balance = balance;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
