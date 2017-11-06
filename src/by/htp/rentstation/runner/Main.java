package by.htp.rentstation.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import by.htp.rentstation.Accessorie;
import by.htp.rentstation.Bike;
import by.htp.rentstation.Category;
import by.htp.rentstation.Client;
import by.htp.rentstation.Equipment;
import by.htp.rentstation.RentStation;
import by.htp.rentstation.RentUnit;
import by.htp.rentstation.Unit;

public class Main {

	public static void main(String[] args) throws Exception {

		RentStation rs = new RentStation("Rent Station", 10, 24);

		Unit bike = new Equipment(Category.Summer, "Bike", 10);
		Unit car = new Equipment(Category.Demi, "Car", 50);
		Unit ski = new Equipment(Category.Winter, "Ski", 10);
		Unit boat = new Equipment(Category.Summer, "Boat", 25);
		Unit snowboard = new Equipment(Category.Winter, "Snowboard", 15);
		
		Unit bike_gt = new Bike(Category.Summer, "Bike", 5, "GT");
		
		Unit belt = new Accessorie(Category.Demi, "Belt", 5);
		Unit helmet = new Accessorie(Category.Demi, "Helmet", 5);
		Unit boots = new Accessorie(Category.Demi, "Boots", 5);
		Unit glasses = new Accessorie(Category.Demi, "Glasses", 5);

		rs.addToRentStation(bike);
		rs.addToRentStation(car);
		rs.addToRentStation(ski);
		rs.addToRentStation(boat);
		rs.addToRentStation(snowboard);
		
		rs.addToRentStation(belt);
		rs.addToRentStation(helmet);
		rs.addToRentStation(boots);
		rs.addToRentStation(bike_gt);
		rs.addToRentStation(glasses);

		int input = 0;

		String rentStationMenu = "1. просмотреть список снаряжения, доступного к прокату\r\n"
				+ "2. просмотреть список снаряжения, взятого в прокат\r\n"
				+ "3. взять конкретный элемент снаряжения в прокат.\r\n" + "4. вернуть снаряжение\r\n"
				+ "5. вывести список снаряжения, которое не вернули на станцию в срок.\r\n"
				+ "6. завершить работу пункта проката";

		System.out.println(rentStationMenu);
		Client cl1 = new Client("Ivanov");
		Client cl2 = new Client("Petrov");

		do {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a number to choose:");
			input = Integer.parseInt(in.readLine());

			switch (input) {
			case 1:
				rs.reportRentIn(false);
				System.out.println("");
				break;
			case 2:
				rs.reportRentIn(true);
				System.out.println("");
				break;
			case 3:
				System.out.println("Which client want unit to rent?");
				Client choosenClient = chooseClient(cl1, cl2);

				Unit choosenUnit = chooseUnitToTake(rs);

				System.out.println("What term?");
				int term = Integer.parseInt(in.readLine());

				choosenClient.rentIn(rs, choosenUnit, term);

				System.out.println("");
				break;
			case 4:
				System.out.println("Which client want to back unit?");
				Client choosenClientToBack = chooseClient(cl1, cl2);

				choosenClientToBack.rentOut(rs, chooseUnitToBack(rs, choosenClientToBack));
				System.out.println("");
				break;
			case 5:
				rs.reportPenaltyUnit(rs.getUnitStation());
				System.out.println("");
				break;
			case 6:
				System.out.println("Finish");
				rs.reportEarnings();
				System.out.println("");
				break;
			}
		} while (input != 6);

	}

	static public Client chooseClient(Client cl1, Client cl2) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Client[] arrClient = new Client[2];
		arrClient[0] = cl1;
		arrClient[1] = cl2;
		int clientNum = 1;
		for (Client cl : arrClient) {
			System.out.println(clientNum + ". " + cl.getName());
			clientNum++;
		}
		Client cl = null;
		do {
			int clientNumberToChoose = Integer.parseInt(in.readLine());
			if (clientNumberToChoose == 1) {
				cl = cl1;
			} else if (clientNumberToChoose == 2) {
				cl = cl2;
			} else {
				System.out.println("Wrong client name!");
			}
		} while (cl == null);

		return cl;
	}

	static public Unit chooseUnitToTake(RentStation rs) throws Exception {
		System.out.println("Enter unit number that you want to take:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int countNumberBack = 1;
		for (Unit unit : rs.getUnitStation()) {
			if (unit.isInRent() == false) {
				System.out.println(countNumberBack + ". " + unit.getUnitType() + ": " + unit.getTitle());
			}
			countNumberBack++;
		}

		int unitNumber = 0;
		do {
			unitNumber = Integer.parseInt(in.readLine());
			if (unitNumber > countNumberBack || unitNumber <= 0) {
				System.out.println("Wrong number!");
			}
		} while (unitNumber > countNumberBack || unitNumber <= 0);

		int countNumber = 1;
		Unit unitChoose = null;
		for (Unit unit : rs.getUnitStation()) {
			if (unitNumber == countNumber) {
				unitChoose = unit;
			}
			countNumber++;
		}
		return unitChoose;
	}

	static public Unit chooseUnitToBack(RentStation rs, Client cl) throws Exception {
		System.out.println("Enter unit number that you want to back:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		RentUnit ru = new RentUnit(rs.getUnitStation());

		int countNumberBack = 1;
		for (Unit unit : ru.formRentUnits(rs.getUnitInRentCount(), true)) {
			if (unit.isInRent() == true && cl.getName().equals(unit.getArendator()))
				System.out.println(countNumberBack + ". " + unit.getTitle() + " taken by " + unit.getArendator());
			else {
				System.out.println(countNumberBack + ". " + unit.getTitle() + " taken by " + unit.getArendator());
			}
			countNumberBack++;
		}

		int unitNumberBack = Integer.parseInt(in.readLine());
		int countNumber2Back = 1;
		Unit unitChooseBack = null;
		for (Unit unit : ru.formRentUnits(rs.getUnitInRentCount(), true)) {
			if (unitNumberBack == countNumber2Back) {
				unitChooseBack = unit;
			}
			countNumber2Back++;
		}
		return unitChooseBack;
	}

}
