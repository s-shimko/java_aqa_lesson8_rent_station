package by.htp.rentstation;

public interface ClientInterface {

	void rentIn(RentStation rs, Unit unit, int rentTerm);

	void rentOut(RentStation rs, Unit unit);

}
