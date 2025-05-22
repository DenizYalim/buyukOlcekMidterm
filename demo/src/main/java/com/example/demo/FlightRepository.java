package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

// DB connection
@Repository
class FlightRepository{
    private ArrayList<Flight> arrayList;

    public FlightRepository() {
    arrayList = new ArrayList<>();
    }

    public ArrayList<Flight> getArrayList(){
        return arrayList;
    }

    public Boolean add(Flight flight){
        for (Flight a : arrayList){
            if(a.flightNumber == flight.flightNumber){
                return false;
            }
        }
        arrayList.add(flight);
        return true;
    }

    public Flight getFlight(int flightNumber){
        for(int a = 0; a < arrayList.size(); a++){
            if(arrayList.get(a).flightNumber == flightNumber){
                    return arrayList.get(a);
                }
        }
        
        return null;
    }
}