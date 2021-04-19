package com.ss.service;

import com.ss.entity.Airplane;
import com.ss.entity.Airport;
import com.ss.entity.Flight;
import com.ss.entity.Route;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static com.ss.service.UiState.*;

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
                case TRAVELER_MAIN: doAirportMain(); break;
                case TRAVELER_ADD: doAirportAdd(); break;
                case TRAVELER_UPDATE: doAirportUpdate(); break;
                case TRAVELER_DELETE: doAirportDelete(); break;
                case TRAVELER_READ: doAirportRead(); break;
                case EMPLOYEE_MAIN: doAirportMain(); break;
                case EMPLOYEE_ADD: doAirportAdd(); break;
                case EMPLOYEE_UPDATE: doAirportUpdate(); break;
                case EMPLOYEE_DELETE: doAirportDelete(); break;
                case EMPLOYEE_READ: doAirportRead(); break;
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
        System.out.println(origin + " --> " + destination);
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
        System.out.println("Update Flight Stub");
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
        System.out.println("Airport Add");
        this.state = AIRPORT_MAIN;
    }
    private void doAirportUpdate() {
        System.out.println("Airport Update");
        this.state = AIRPORT_MAIN;
    }
    private void doAirportDelete() {
        System.out.println("Airport Delete");
        this.state = AIRPORT_MAIN;
    }
    private void doAirportRead() {
        System.out.println("Airport Read");
        this.state = AIRPORT_MAIN;
    }


}
