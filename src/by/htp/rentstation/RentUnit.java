package by.htp.rentstation;

public final class RentUnit {

	private Unit[] units;

	public RentUnit(Unit[] units) {
		this.units = units;
	}

	// Form RentUnits array according $isInRent: if true - only units in rent, if
	// false - not in rent
	public Unit[] formRentUnits(int unitInRentCount, boolean isInRent) {
		Unit[] inRent = new Unit[unitInRentCount];

		int num = 0;
		for (Unit u : units) {
			if (u.isInRent() == isInRent) {
				inRent[num] = u;
				num++;
			}
		}
		return inRent;
	}

	// Print list of Rent Units if Unit array not empty
	public void printRentUnits(Unit[] inRent) {
		if (inRent.length > 0) {
			int number = 1;
			for (Unit u : inRent) {
				System.out.println(number + ". " + u.getCategory() + " " + u.getTitle() + " - " + u.getCost() + "BYN");
				number++;
			}
		}
	}
}
