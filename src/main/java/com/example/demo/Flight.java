package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


// @Entity
class Flight{
    // todo: add an unique id
    // @Id @GeneratedValue

    public int flightNumber;
    public Date fromDate;
    public Date toDate;
    public String fromAirport;
    public String toAirport;
    public double duration;
    public int capacity; // Capacity of people ?
    public boolean oneWayTrip;
    public ArrayList<String> passengers;
    public Map<Integer, String> seats;
    public boolean checkIn;


    public Flight(int flightNumber){
        this.flightNumber = flightNumber;
        passengers = new ArrayList<>();
        seats = new HashMap<>();

        capacity = 100; // default
        checkIn = false;
    }

    public boolean addPassenger(String name){
        if(capacity <= 0 )
            return false;
        passengers.add(name);
        capacity--;
        return true;
    }

    public boolean checkInPassenger(String name){
        if(!passengers.contains(name)){
            return false;
        }
        
        seats.put(seats.size(), name);
        return true;
    }

    /*
    // I realized later that the customer can't choose their seat...
    public boolean addPassenger( int seat , String name){
        if(seat > capacity)
            return false;
        
        if(passengers.get(seat) != null)
            return false;
        
        passengers.put(seat, name);
        capacity--;
        return true;
    }
    */
}