package com.ss.service;

import com.ss.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static com.ss.service.UiState.*;


// state machine to hand ui
public class UiStateMachine {
    UiState state = UiState.MAIN;

    public void dispatch() {
        boolean running = true;
        while(running) {
            switch (state) {
                case MAIN: doMain(); break;
                case FLIGHT_MAIN: doFlightMain(); break;
                case FLIGHT_ADD: doFlightAdd(); break;
                case FLIGHT_UPDATE: doFlightUpdate(); break;
                case FLIGHT_DELETE: doFlightDelete(); break;
                case FLIGHT_READ: doFlightRead(); break;
                case AIRPORT_MAIN: doAirportMain(); break;
                case AIRPORT_ADD: doAirportAdd(); break;
                case AIRPORT_UPDATE: doAirportUpdate(); break;
                case AIRPORT_DELETE: doAirportDelete(); break;
                case AIRPORT_READ: doAirportRead(); break;
                case TRAVELER_MAIN: doTravelerMain(); break;
                case TRAVELER_ADD: doTravelerAdd(); break;
                case TRAVELER_UPDATE: doTravelerUpdate(); break;
                case TRAVELER_DELETE: doTravelerDelete(); break;
                case TRAVELER_READ: doTravelerRead(); break;
                case EMPLOYEE_MAIN: doEmployeeMain(); break;
                case EMPLOYEE_ADD: doEmployeeAdd(); break;
                case EMPLOYEE_UPDATE: doEmployeeUpdate(); break;
                case EMPLOYEE_DELETE: doEmployeeDelete(); break;
                case EMPLOYEE_READ: doEmployeeRead(); break;
                case EXIT: running = false; break;
            }
        }
    }

    private void doMain() {
        System.out.println("1) Flight operations");
        System.out.println("2) Airport operations");
        System.out.println("3) Traveler operations");
        System.out.println("4) Employee operations");
        System.out.println("5) Exit");

        Scanner in = new Scanner(System.in);
        int op = in.nextInt();
        switch(op) {
            case 1: this.state = FLIGHT_MAIN; break;
            case 2: this.state = AIRPORT_MAIN; break;
            case 3: this.state = TRAVELER_MAIN; break;
            case 4: this.state = EMPLOYEE_MAIN; break;
            case 5: this.state = EXIT; break;
            default: System.out.println("Select one of the available options");
            break;
        }
    }

    private void doFlightMain() {
        System.out.println("1) Add Flight");
        System.out.println("2) Update Flight");
        System.out.println("3) Delete Flight");
        System.out.println("4) List Flights");
        System.out.println("5) Go back");

        Scanner in = new Scanner(System.in);
        int op = in.nextInt();
        switch(op) {
            case 1: this.state = FLIGHT_ADD; break;
            case 2: this.state = FLIGHT_UPDATE; break;
            case 3: this.state = FLIGHT_DELETE; break;
            case 4: this.state = FLIGHT_READ; break;
            case 5: this.state = MAIN; break;
            default: System.out.println("Select one of the available options");
                break;
        }
    }

    private void doFlightAdd() {
        System.out.println("Add Flight");
        System.out.println("a. Origin/Departure Airports");
        AdminService ad = new AdminService();
        List<Airport> ap = ad.listAirports();
        for (Airport a : ap) {
            System.out.println(a.getAirportCode());
        }
        String origin = null;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the listed airports for origin");
            Scanner in = new Scanner(System.in);
            origin = in.nextLine();
            for (Airport a : ap) {
                if (a.getAirportCode().equals(origin)) { match = true; }
            }
        } while(!match);
        String destination = null;
        do {
            match = false;
            System.out.println("Pick one of the listed airports for destination");
            Scanner in = new Scanner(System.in);
            destination = in.nextLine();
            for (Airport a : ap) {
                if (a.getAirportCode().equals(destination)) { match = true; }
            }
        } while(!match);
        Flight f = new Flight();
        f.setRoute(new Route(new Airport(origin, "ph"), new Airport(destination, "ph")));
        f.setAirplane(new Airplane()); f.getAirplane().setId(1);
        f.setDepartureTime(LocalDateTime.now());
        f.setReservedSeats(50);
        f.setSeatPrice(200);
        ad.addFlight(f);
        this.state = FLIGHT_MAIN;
    }
    private void doFlightUpdate() {
        AdminService ad = new AdminService();
        List<Flight> flights = ad.listFlights();
        for (Flight f : flights) {
            System.out.println(f.getId() + "   " + f.getRoute().getOrigin().getAirportCode()
                    + " --> " + f.getRoute().getDestination().getAirportCode() + "   " +
                    f.getDepartureTime());
        }
        int updateId = -1;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the listed flight IDs to be updated");
            Scanner in = new Scanner(System.in);
            updateId = in.nextInt();
            for (Flight f : flights) {
                if (updateId == f.getId()) { match = true; }
            }
        } while(!match);

        ad = new AdminService();
        List<Airport> ap = ad.listAirports();
        for (Airport a : ap) {
            System.out.println(a.getAirportCode());
        }
        String origin = null;
        match = false;
        do {
            match = false;
            System.out.println("Pick one of the listed airports for origin");
            Scanner in = new Scanner(System.in);
            origin = in.nextLine();
            for (Airport a : ap) {
                if (a.getAirportCode().equals(origin)) { match = true; }
            }
        } while(!match);
        String destination = null;
        do {
            match = false;
            System.out.println("Pick one of the listed airports for destination");
            Scanner in = new Scanner(System.in);
            destination = in.nextLine();
            for (Airport a : ap) {
                if (a.getAirportCode().equals(destination)) { match = true; }
            }
        } while(!match);
        Flight f = new Flight();
        f.setId(updateId);
        f.setRoute(new Route(new Airport(origin, "ph"), new Airport(destination, "ph")));
        f.setAirplane(new Airplane()); f.getAirplane().setId(1);
        f.setDepartureTime(LocalDateTime.now());
        f.setReservedSeats(50);
        f.setSeatPrice(200);
        ad.updateFlight(f);
        this.state = FLIGHT_MAIN;
    }
    private void doFlightDelete() {
        AdminService ad = new AdminService();
        List<Flight> flights = ad.listFlights();
        for (Flight f : flights) {
            System.out.println(f.getId() + "   " + f.getRoute().getOrigin().getAirportCode()
                    + " --> " + f.getRoute().getDestination().getAirportCode() + "   " +
                    f.getDepartureTime());
        }
        int deleteId = -1;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the listed flight IDs to be deleted");
            Scanner in = new Scanner(System.in);
            deleteId = in.nextInt();
            for (Flight f : flights) {
                if (deleteId == f.getId()) { match = true; }
            }
        } while(!match);
        Flight f = new Flight();
        f.setId(deleteId);
        ad.deleteFlight(f);
        this.state = FLIGHT_MAIN;
    }
    private void doFlightRead() {
        AdminService ad = new AdminService();
        List<Flight> flights = ad.listFlights();
        for (Flight f : flights) {
            System.out.println(f.getId() + "   " + f.getRoute().getOrigin().getAirportCode()
                    + " --> " + f.getRoute().getDestination().getAirportCode() + "   " +
                    f.getDepartureTime());
        }
        this.state = FLIGHT_MAIN;
    }

    private void doAirportMain() {
        System.out.println("1) Add Airport");
        System.out.println("2) Update Airport");
        System.out.println("3) Delete Airport");
        System.out.println("4) List Airports");
        System.out.println("5) Go back");

        Scanner in = new Scanner(System.in);
        int op = in.nextInt();
        switch(op) {
            case 1: this.state = AIRPORT_ADD; break;
            case 2: this.state = AIRPORT_UPDATE; break;
            case 3: this.state = AIRPORT_DELETE; break;
            case 4: this.state = AIRPORT_READ; break;
            case 5: this.state = MAIN; break;
            default: System.out.println("Select one of the available options");
                break;
        }
    }
    private void doAirportAdd() {
        String iata_id = null;
        boolean match = false;
        do {
            System.out.println("Give airport code to be added (3 characters)");
            Scanner in = new Scanner(System.in);
            iata_id = in.nextLine();
            if (iata_id.length() == 3) { match = true; }
        } while (!match);
        System.out.println("Give city name to be added");
        Scanner in = new Scanner(System.in);
        String city = in.nextLine();
        Airport ap = new Airport(iata_id, city);
        AdminService ad = new AdminService();
        ad.addAirport(ap);
        this.state = AIRPORT_MAIN;
    }
    private void doAirportUpdate() {
        AdminService ad = new AdminService();
        List<Airport> aps = ad.listAirports();
        for (Airport ap : aps) {
            System.out.println(ap.getAirportCode() + "   " + ap.getCityName());
        }
        String updateId = null;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the listed airport codes to be updated");
            Scanner in = new Scanner(System.in);
            updateId = in.nextLine();
            for (Airport ap : aps) {
                if (updateId.equals(ap.getAirportCode())) { match = true; }
            }
        } while(!match);
        System.out.println("Give a new city name");
        Scanner in = new Scanner(System.in);
        String city = in.nextLine();
        Airport ap = new Airport(updateId, city);
        ad = new AdminService();
        ad.updateAirport(ap, ap);
        this.state = AIRPORT_MAIN;
    }
    private void doAirportDelete() {
        AdminService ad = new AdminService();
        List<Airport> aps = ad.listAirports();
        for (Airport ap : aps) {
            System.out.println(ap.getAirportCode() + "   " + ap.getCityName());
        }
        String deleteId = null;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the listed airport codes to be deleted");
            Scanner in = new Scanner(System.in);
            deleteId = in.nextLine();
            for (Airport ap : aps) {
                if (deleteId.equals(ap.getAirportCode())) { match = true; }
            }
        } while(!match);
        ad.deleteAirport(new Airport(deleteId, "ph"));
        this.state = AIRPORT_MAIN;
    }
    private void doAirportRead() {
        AdminService ad = new AdminService();
        List<Airport> aps = ad.listAirports();
        for (Airport ap : aps) {
            System.out.println(ap.getAirportCode() + "   " + ap.getCityName());
        }
        this.state = AIRPORT_MAIN;
    }

    private void doTravelerMain() {
        System.out.println("1) Add Traveler");
        System.out.println("2) Update Traveler");
        System.out.println("3) Delete Traveler");
        System.out.println("4) List Travelers");
        System.out.println("5) Go back");

        Scanner in = new Scanner(System.in);
        int op = in.nextInt();
        switch(op) {
            case 1: this.state = TRAVELER_ADD; break;
            case 2: this.state = TRAVELER_UPDATE; break;
            case 3: this.state = TRAVELER_DELETE; break;
            case 4: this.state = TRAVELER_READ; break;
            case 5: this.state = MAIN; break;
            default: System.out.println("Select one of the available options");
                break;
        }
    }
    private void doTravelerAdd() {
        Scanner in = new Scanner(System.in);
        Traveler t = new Traveler();
        System.out.println("Input given name for new traveler");
        t.setGivenName(in.nextLine());
        System.out.println("Input family name for new traveler");
        t.setFamilyName(in.nextLine());
        System.out.println("Input username for new traveler");
        t.setUserName(in.nextLine());
        System.out.println("Input email for new traveler");
        t.setEmail(in.nextLine());
        System.out.println("Input password for new traveler");
        t.setPassword(in.nextLine());
        System.out.println("Input phone number for new traveler");
        t.setPhone(in.nextLine());
        AdminService ad = new AdminService();
        ad.addTraveler(t);
        this.state = TRAVELER_MAIN;
    }
    private void doTravelerUpdate() {
        AdminService ad = new AdminService();
        List<Traveler> travelers = ad.listTravelers();
        for (Traveler t : travelers) {
            System.out.println(t.getId() + "   " + t.getGivenName() + " " +
                    t.getFamilyName() + " " + t.getUserName());
        }
        int updateId = -1;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the Traveler IDs to be updated");
            Scanner in = new Scanner(System.in);
            updateId = in.nextInt();
            for (Traveler t : travelers) {
                if (updateId == t.getId()) { match = true; }
            }
        } while(!match);
        Scanner in = new Scanner(System.in);
        Traveler t = new Traveler();
        t.setId(updateId);
        System.out.println("Input new given name for traveler");
        t.setGivenName(in.nextLine());
        System.out.println("Input new family name for traveler");
        t.setFamilyName(in.nextLine());
        System.out.println("Input new username for traveler");
        t.setUserName(in.nextLine());
        System.out.println("Input new email for traveler");
        t.setEmail(in.nextLine());
        System.out.println("Input new password for traveler");
        t.setPassword(in.nextLine());
        System.out.println("Input new phone number for traveler");
        t.setPhone(in.nextLine());
        ad.updateTraveler(t);
        this.state = TRAVELER_MAIN;
    }
    private void doTravelerDelete() {
        AdminService ad = new AdminService();
        List<Traveler> travelers = ad.listTravelers();
        for (Traveler t : travelers) {
            System.out.println(t.getId() + "   " + t.getGivenName() + " " +
                    t.getFamilyName() + " " + t.getUserName());
        }
        int deleteId = -1;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the Traveler IDs to be deleted");
            Scanner in = new Scanner(System.in);
            deleteId = in.nextInt();
            for (Traveler t : travelers) {
                if (deleteId == t.getId()) { match = true; }
            }
        } while(!match);
        Traveler t = new Traveler();
        t.setId(deleteId);
        ad.deleteTraveler(t);
        this.state = TRAVELER_MAIN;
    }
    private void doTravelerRead() {
        AdminService ad = new AdminService();
        List<Traveler> travelers = ad.listTravelers();
        for (Traveler t : travelers) {
            System.out.println(t.getId() + "   " + t.getGivenName() + " " +
                    t.getFamilyName() + " " + t.getUserName());
        }
        this.state = TRAVELER_MAIN;
    }

    private void doEmployeeMain() {
        System.out.println("1) Add Employee");
        System.out.println("2) Update Employee");
        System.out.println("3) Delete Employee");
        System.out.println("4) List Employees");
        System.out.println("5) Go back");

        Scanner in = new Scanner(System.in);
        int op = in.nextInt();
        switch(op) {
            case 1: this.state = EMPLOYEE_ADD; break;
            case 2: this.state = EMPLOYEE_UPDATE; break;
            case 3: this.state = EMPLOYEE_DELETE; break;
            case 4: this.state = EMPLOYEE_READ; break;
            case 5: this.state = MAIN; break;
            default: System.out.println("Select one of the available options");
                break;
        }
    }
    private void doEmployeeAdd() {
        Scanner in = new Scanner(System.in);
        Employee e = new Employee();
        System.out.println("Input given name for new Employee");
        e.setGivenName(in.nextLine());
        System.out.println("Input family name for new Employee");
        e.setFamilyName(in.nextLine());
        System.out.println("Input username for new Employee");
        e.setUserName(in.nextLine());
        System.out.println("Input email for new Employee");
        e.setEmail(in.nextLine());
        System.out.println("Input password for new Employee");
        e.setPassword(in.nextLine());
        System.out.println("Input phone number for new Employee");
        e.setPhone(in.nextLine());
        AdminService ad = new AdminService();
        ad.addEmployee(e);
        this.state = EMPLOYEE_MAIN;
    }
    private void doEmployeeUpdate() {
        AdminService ad = new AdminService();
        List<Employee> employees = ad.listEmployees();
        for (Employee e : employees) {
            System.out.println(e.getId() + "   " + e.getGivenName() + " " +
                    e.getFamilyName() + " " + e.getUserName());
        }
        int updateId = -1;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the Employee IDs to be updated");
            Scanner in = new Scanner(System.in);
            updateId = in.nextInt();
            for (Employee e : employees) {
                if (updateId == e.getId()) { match = true; }
            }
        } while(!match);
        Scanner in = new Scanner(System.in);
        Employee e = new Employee();
        e.setId(updateId);
        System.out.println("Input new given name for employee");
        e.setGivenName(in.nextLine());
        System.out.println("Input new family name for employee");
        e.setFamilyName(in.nextLine());
        System.out.println("Input new username for employee");
        e.setUserName(in.nextLine());
        System.out.println("Input new email for employee");
        e.setEmail(in.nextLine());
        System.out.println("Input new password for employee");
        e.setPassword(in.nextLine());
        System.out.println("Input new phone number for employee");
        e.setPhone(in.nextLine());
        ad.updateEmployee(e);
        this.state = EMPLOYEE_MAIN;
    }
    private void doEmployeeDelete() {
        AdminService ad = new AdminService();
        List<Employee> employees = ad.listEmployees();
        for (Employee e : employees) {
            System.out.println(e.getId() + "   " + e.getGivenName() + " " +
                    e.getFamilyName() + " " + e.getUserName());
        }
        int deleteId = -1;
        boolean match = false;
        do {
            match = false;
            System.out.println("Pick one of the Employee IDs to be deleted");
            Scanner in = new Scanner(System.in);
            deleteId = in.nextInt();
            for (Employee e : employees) {
                if (deleteId == e.getId()) { match = true; }
            }
        } while(!match);
        Employee e = new Employee();
        e.setId(deleteId);
        ad.deleteEmployee(e);
        this.state = EMPLOYEE_MAIN;
    }
    private void doEmployeeRead() {
        AdminService ad = new AdminService();
        List<Employee> employees = ad.listEmployees();
        for (Employee e : employees) {
            System.out.println(e.getId() + "   " + e.getGivenName() + " " +
                    e.getFamilyName() + " " + e.getUserName());
        }
        this.state = EMPLOYEE_MAIN;
    }
}
