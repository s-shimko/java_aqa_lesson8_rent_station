package by.htp.rentstation;

public abstract class Unit {

	private Category category;
	private String title;
	private int cost;
	private boolean inRent = false;
	private int rentTerm = 0;
	private String arendator;
	private double penaltyPrice;
	private int penaltyHours;
	private double penaltyPercentage = 0.1;
	protected String unitType;

	public Unit(Category category, String title, int cost) {
		this.category = category;
		this.title = title;
		this.cost = cost;
	}

	public String getUnitType() {
		return unitType;
	}

	public int getPenaltyHours() {
		return penaltyHours;
	}

	public double getPenaltyPrice() {
		return penaltyPrice;
	}

	public String getArendator() {
		return arendator;
	}

	public void setArendator(String arendator) {
		this.arendator = arendator;
	}

	public int getRentTerm() {
		return rentTerm;
	}

	public void setRentTerm(int rentTerm) {
		this.rentTerm = rentTerm;
	}

	public boolean isInRent() {
		return inRent;
	}

	public void setInRent(boolean inRent) {
		this.inRent = inRent;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public double calcPenalty(int passedTime) {
		RentStation.passedTime = passedTime;
		if (passedTime > rentTerm && inRent == true) {
			this.penaltyHours = passedTime - rentTerm;
			penaltyPrice = cost * penaltyPercentage * this.penaltyHours;
		}
		return penaltyPrice;
	}

	@Override
	public String toString() {
		return "Unit=" + category + ", title=" + title + ", cost=" + cost + ", inRent=" + inRent
				+ ", rentTerm=" + rentTerm + ", arendator=" + arendator + ", penaltyPrice=" + penaltyPrice
				+ ", penaltyHours=" + penaltyHours + "]";
	}




}
