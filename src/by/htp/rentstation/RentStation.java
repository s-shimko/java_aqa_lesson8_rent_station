package by.htp.rentstation;

import java.util.Arrays;

public class RentStation implements RentStationInterface {

	private String title;
	private int rsCapacity;
	private Unit[] unitStation;
	private int unitCount;
	private static int unitInRentCount;
	private static int unitNotInRentCount;
	static int passedTime;
	static int termLimit = 72;
	static public double earning;
	static public double penaltyEarning;

	public RentStation(String title, int rsCapacity, int passedTime) {
		this.title = title;
		this.rsCapacity = rsCapacity;
		unitStation = new Unit[rsCapacity];
		this.passedTime = passedTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRsCapacity() {
		return rsCapacity;
	}

	public void setRsCapacity(int rsCapacity) {
		this.rsCapacity = rsCapacity;
	}

	public Unit[] getUnitStation() {
		return unitStation;
	}

	public void setUnitStation(Unit[] unitStation) {
		this.unitStation = unitStation;
	}

	public int getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}

	public int getUnitInRentCount() {
		return unitInRentCount;
	}

	public void setUnitInRentCount(int unitInRentCount) {
		this.unitInRentCount = unitInRentCount;
	}

	public int getUnitNotInRentCount() {
		return unitNotInRentCount;
	}

	public void setUnitNotInRentCount(int unitNotInRentCount) {
		this.unitNotInRentCount = unitNotInRentCount;
	}

	public static int getPassedTime() {
		return passedTime;
	}

	public static void setPassedTime(int passedTime) {
		RentStation.passedTime = passedTime;
	}

	public static int getTermLimit() {
		return termLimit;
	}

	public static void setTermLimit(int termLimit) {
		RentStation.termLimit = termLimit;
	}

	public static double getEarning() {
		return earning;
	}

	public static void setEarning(double earning) {
		RentStation.earning = earning;
	}

	public static double getPenaltyEarning() {
		return penaltyEarning;
	}

	public static void setPenaltyEarning(double penaltyEarning) {
		RentStation.penaltyEarning = penaltyEarning;
	}

	public void addToRentStation(Unit unit) {
		unitStation[unitCount] = unit;
		unitCount++;
		unitNotInRentCount++;
	}

	// Print All Equipment array
	public void reportRentStation() {
		System.out.println("Equipment available for rent in " + title + ":");
		int number = 1;
		for (Unit u : unitStation) {
			System.out.println(number + ". " + u.getCategory() + " " + u.getTitle() + " - " + u.getCost() + "BYN");
			number++;
		}
	}

	// Print Rent Units if $rentIn value = true, and print Units not in rent $rentIn
	// value = false
	public void reportRentIn(boolean rentIn) {
		RentUnit ru = new RentUnit(unitStation);
		Unit[] inRent;

		if (rentIn == true) {

			inRent = ru.formRentUnits(unitInRentCount, true);

			if (inRent.length > 0) {
				System.out.println("Rent Unit in " + title + ":");
				printRentUnits(inRent);

			} else {
				System.out.println("No Unit in rent!");
			}
		}

		else {

			inRent = ru.formRentUnits(unitNotInRentCount, false);

			if (inRent.length > 0) {
				System.out.println("Not Rent Unit in " + title + ":");
				printRentUnits(inRent);

			} else {
				System.out.println("All Unit in rent!");
			}

		}
	}

	public static void printRentUnits(Unit[] inRent) {
		if (inRent.length > 0) {
			int number = 1;
			for (Unit u : inRent) {
				if (u.getArendator() == null) {
					System.out.println(u);
				} else {
					System.out.println(u);
				}
				number++;
			}
		}
	}

	public static void printRentUnits(Unit[] inRent, int number) {
		if (inRent.length > 0) {
			for (Unit u : inRent) {
				System.out.println(number + ". " + u.getCategory() + " " + u.getTitle() + " - " + u.getCost() + "BYN");
				number++;
			}
		}
	}

	public void checkRentTerm(Unit unit) {
		if (passedTime > unit.getRentTerm() && unit.isInRent() == true && unit.getArendator() != null) {
			double penalty = unit.calcPenalty(passedTime);
			System.out.print(unit.getArendator() + ": Penalty for " + unit.getTitle() + ": " + penalty + "BYN\n");
		} else if (unit.isInRent() == false) {
			System.out.print(unit.getArendator() + ": Can't check " + unit.getTitle() + " - Equipment not in rent.\n");
		} else {
			System.out.print(unit.getArendator() + ": No penalty for " + unit.getTitle() + ". Time left: "
					+ (unit.getRentTerm() - passedTime) + " hours\n");
		}
	}

	public void reportPenaltyUnit(Unit[] unitStation) {
		System.out.println("Overdue Equipment:");
		double calcPenalty;
		int n = 0;
		for (Unit unit : unitStation) {
			calcPenalty = unit.calcPenalty(passedTime);
			if (calcPenalty > 0 && unit.isInRent() == true) {
				n++;
				System.out.print(n + ". ");
				checkRentTerm(unit);
			}

		}
		if (n == 0) {
			System.out.println("No overdue units.");
		}
	}

	public void reportEarnings() {
		System.out.println(passedTime + " hour report");
		System.out.println("Earnings by cost: " + earning);
		System.out.println("Earnings by penalty: " + penaltyEarning);
	}

}
