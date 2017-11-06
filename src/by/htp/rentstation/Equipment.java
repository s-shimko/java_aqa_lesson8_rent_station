package by.htp.rentstation;

public class Equipment extends Unit {
	
	public static String type = "Equipment";

	public Equipment(Category category, String title, int cost) {
		super(category, title, cost);
		super.unitType = this.type;
	}
	
	public static String getType() {
		return type;
	}

	@Override
	public String toString() {
		return getType() + ": Category=" + getCategory()
				+ ", Title=" + getTitle() + ", Cost=" + getCost() + "BYN" + ", PenaltyPrice=" + getPenaltyPrice() + ", Arendator=" + getArendator()
				+ ", RentTerm=" + getRentTerm() + ", isInRent=" + isInRent() + ", penaltyHours=" + getPenaltyHours();
	}
	
}
