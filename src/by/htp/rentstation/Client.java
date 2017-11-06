package by.htp.rentstation;

public class Client implements ClientInterface {

	private String name;
	private int unitClientCount = 0;
	private int eqClientCount = 0;

	public Client(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getUnitClientCount() {
		return unitClientCount;
	}

	public final void rentIn(RentStation rs, Unit unit, int rentTerm) {
		if (eqClientCount != 3 && unit.getUnitType().equals("Equipment")) {
			if (rentTerm >= 1 && rentTerm <= rs.termLimit) {

				if (unit.isInRent() == true) {
					System.out.println(unit.getTitle() + " already in rent");
				} else {
					eqClientCount++;

					rentInActions(rs, unit, rentTerm);
				}

			} else {
				System.out.println("Rent Term >= 1 hour and <= " + rs.termLimit + " hours. " + unit.getTitle()
						+ " couldn't rent!");
			}

		} else if (unit.getUnitType().equals("Accessorie")) {
			rentInActions(rs, unit, rentTerm);
		}

		else {
			System.out.println("This client has 3 equipments, it's a limit!");
		}
	}

	public final void rentOut(RentStation rs, Unit unit) {
		if (unit.isInRent() == false) {
			System.out.println(unit.getTitle() + " not in rent");
		} else if (unit.isInRent() == true && name.equals(unit.getArendator())) {
			unitClientCount--;
			if (unit.getUnitType().equals("Equipment")) {
				eqClientCount--;
			}
			System.out.println(unit.getTitle() + " returned from " + name + ". Units in rent: " + unitClientCount
					+ ". Penalty is " + unit.calcPenalty(rs.passedTime));
			unit.setInRent(false);
			RentStation.penaltyEarning = RentStation.penaltyEarning + unit.getPenaltyPrice();
			rs.setUnitInRentCount(rs.getUnitInRentCount() - 1);
			rs.setUnitNotInRentCount(rs.getUnitNotInRentCount() + 1);
			unit.setArendator(null);
		} else {
			System.out.println("It's not client's unit!");
		}
	}

	private void rentInActions(RentStation rs, Unit unit, int rentTerm) {
		unit.setInRent(true);
		unit.setRentTerm(rentTerm);
		unit.setArendator(name);
		unitClientCount++;
		System.out.println(unit.getTitle() + " taken in rent by " + name + ". Units in rent: " + unitClientCount);
		rs.setUnitInRentCount(rs.getUnitInRentCount() + 1);
		rs.setUnitNotInRentCount(rs.getUnitNotInRentCount() - 1);
		rs.earning = rs.earning + unit.getCost();
	}

}
