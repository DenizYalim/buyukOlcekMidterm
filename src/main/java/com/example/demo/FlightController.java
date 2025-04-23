package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FlightController{
    FlightRepository repo;

    public FlightController() {
        repo = new FlightRepository();
    }
    /*
    @GetMapping("/")
    public String hub(){
        return "";
    }

    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/testGET")
    public String hello(){
        return "hellao";
    }
    */

    // Adds a Flight
    @GetMapping("/query-flights")
    public ArrayList<Flight> queryFlight(){
        /*
        Flight a = new Flight();
        a.capacity = 123;
        a.duration = 1;
        a.toAirport = "betterLand";

        //return repo.arrayList;    
        ArrayList<Flight> abc = new ArrayList<>();
        abc.add(a); 
        return abc;
        */
       return repo.getArrayList();
    }

    // {"flightNumber":0,"fromDate":null,"toDate":null,"fromAirport":null,"toAirport":"betterLand","duration":1.0,"capacity":123,"oneWayTrip":false}
    // Lists available flights, no seat ones shouldn't be allowed
    @PostMapping("/add-flight")
    public boolean addFlight(@RequestBody Flight flight){
        return repo.add(flight);
    }


    // Flight number, Passenger Name(
    // Buy ticket       ? UPDATE ? PATCH
    @PutMapping("/buy-ticket")
    public boolean buyTicket(@RequestBody TicketRequest ticketRequest){
        int flightNum = ticketRequest.flightNumber;
        // date is unnecesarry
        String name = ticketRequest.passengerName;

        Flight flight = repo.getFlight(flightNum);
        if(flight != null){
            return flight.addPassenger(name);
        }
        return false;
    }
    
     // Query Flight Passanger List      POST
    @PostMapping("/Query-flight-PassangerList")
    public ArrayList<String> queryFlightPassengerList(@RequestBody FlightPassengerRequest fpr){
        int flightNumber = fpr.flightNumber;

        return repo.getFlight(flightNumber).passengers;
    }
    
    // PUT PASSENGERS INTO SEATS
    @PutMapping("/check-in")
    public boolean checkInPassengers(@RequestBody CheckinRequest request){
        System.out.println("testss" + request.flightNumber);
        return repo.getFlight(request.flightNumber).checkInPassenger(request.passengerName);
    }
}