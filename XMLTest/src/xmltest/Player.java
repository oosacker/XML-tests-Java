package xmltest;

public class Player {

	private String name;
	private String nation;
	private String club;
	private int age;
	private String position;
	private double market_value;
	
	public Player(String name, String nation, String club, int age, String position, double market_value) {
		super();
		this.name = name;
		this.nation = nation;
		this.club = club;
		this.age = age;
		this.position = position;
		this.market_value = market_value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getMarketValue() {
		return market_value;
	}

	public void setMarketValue(double market_value) {
		this.market_value = market_value;
	}

	@Override
	public String toString() {
		return "\nName: " + name + "\nNation: " + nation + "\nClub: " + club + "\nAge: " + age + "\nPosition: "
				+ position + "\nMarket value: £"+ market_value +"M";
	}


}
