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
        ArrayList<Flight> flights = new ArrayList<>();
        for(Flight a : repo.getArrayList()){
            if(a.capacity > 0) // Only return flights that are not full.
                flights.add(a);
        }

       return flights;
    }

    // Lists available flights, no seat ones shouldn't be allowed
    @PostMapping("/add-flight")
    public boolean addFlight(@RequestBody Flight flight){
        return repo.add(flight);
    }

     // Query Flight Passanger List      POST
    @PostMapping("/query-flight-passanger-list")
    public ArrayList<String> queryFlightPassengerList(@RequestBody FlightPassengerRequest fpr){
        int flightNumber = fpr.flightNumber;

        return repo.getFlight(flightNumber).getPassengers();
    }

    @PutMapping("/buy-ticket")
    public boolean buyTicket(@RequestBody TicketRequest ticketRequest){
        System.out.println("Request received: " + ticketRequest + "  " + ticketRequest.flightNumber + " name: "+ ticketRequest.passengerName); // debug

        int flightNum = ticketRequest.flightNumber; // date is unnecesarry
        String name = ticketRequest.passengerName;

        Flight flight = repo.getFlight(flightNum);
        if(flight != null){
            return flight.addPassenger(name);
        }
        
        return false;
    }
   
   @PutMapping("/check-in")
   public boolean checkIn(@RequestBody CheckinRequest checkInRequest){
                System.out.println("Request received: " + checkInRequest + "  " + checkInRequest.flightNumber + " name: "+ checkInRequest.passengerName); // debug

        int flightNum = checkInRequest.flightNumber; // date is unnecesarry
        String name = checkInRequest.passengerName;

        Flight flight = repo.getFlight(flightNum);

        if(flight != null){
            return flight.checkInPassenger(name);
        }
        
        return false;
   }
}