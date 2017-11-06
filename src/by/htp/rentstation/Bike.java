package by.htp.rentstation;

public class Bike extends Equipment {
	
	private String model;
	
	public Bike(Category category, String title, int cost, String model) {
		super(category, title, cost);
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	@Override
	public String toString() {
		return "Bike [model=" + model + ", toString()=" + super.toString() + ", getUnitType()=" + getUnitType()
				+ ", getPenaltyHours()=" + getPenaltyHours() + ", getPenaltyPrice()=" + getPenaltyPrice()
				+ ", getArendator()=" + getArendator() + ", getRentTerm()=" + getRentTerm() + ", isInRent()="
				+ isInRent() + ", getCategory()=" + getCategory() + ", getTitle()=" + getTitle() + "]";
	}
	
	
}
