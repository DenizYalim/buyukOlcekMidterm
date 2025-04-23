package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    // PAGING NEEDED done
    @GetMapping("/query-flights")
    public ArrayList<Flight> queryFlight(@RequestParam(defaultValue = "0") int page){
        ArrayList<Flight> flights = new ArrayList<>();

        int start = page * 10;
        int end = Math.min(start + 10, repo.getArrayList().size());

        for(int i = 0; i < repo.getArrayList().size(); i++){
             Flight a = repo.getArrayList().get(i);
        if (a.capacity > 0) // Only flights that are not full
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
     // PAGING NEEDED
    @PostMapping("/query-flight-passanger-list")
    public ArrayList<String> queryFlightPassengerList(@RequestBody FlightPassengerRequest fpr, @RequestParam(defaultValue = "0") int page){
    int flightNumber = fpr.flightNumber;
    ArrayList<String> allPassengers = repo.getFlight(flightNumber).getPassengers();

    int start = page * 10;
    int end = Math.min(start + 10, allPassengers.size());

    return new ArrayList<>(allPassengers.subList(start, end));
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