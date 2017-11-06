package by.htp.rentstation;

public interface RentStationInterface {

	void reportRentIn(boolean rentIn);

	void reportPenaltyUnit(Unit[] unitStation);
	
	void checkRentTerm(Unit u);

	void reportEarnings();

}
