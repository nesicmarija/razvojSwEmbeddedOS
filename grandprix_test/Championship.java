/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grandprix_test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author marija
 */
public class Championship {
    private ArrayList<Driver> drivers;
    private ArrayList<Venue> venues;
    private final int MINOR_MECHANICAL_FAULT = 5;
    private final int MAJOR_MECHANICAL_FAULT = 3;
    private final int UNRECOVERABLE_MECHANICAL_FAULT = 1;
    final int FRONT_ROW_DELAY = 3;
    final int SECOND_ROW_DELAY = 5;
    final int THIRD_ROW_DELAY = 7;
    final int LOWER_HALF_OF_THE_FIELD_DELAY = 10;
    final int MINOR_REPARE_TIME = 20;
    final int MAJOR_REPARE_TIME = 120;
    final int FIRST_PLACE_REWARD = 8;
    final int SECOND_PLACE_REWARD = 5;
    final int THIRD_PLACE_REWARD = 5;
    final int FOURTH_PLACE_REWARD = 1;

    public Championship(String driversFilePath, String venuesFilePath) throws FileNotFoundException {
        this.drivers = new ArrayList<>();
        this.venues = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(venuesFilePath));
            String line;
        while ((line = reader.readLine()) != null) {
            // split the line into an array of values
            String[] values = line.split(",");
             
        // create a new Venue object based on the values
            Venue venue = new Venue( values[0],Integer.parseInt(values[1]), Integer.parseInt(values[2]), Double.parseDouble(values[3]));
        
        // add the new Venue object to the venues ArrayList
            venues.add(venue);
           // System.out.println(venue);
        }
        reader.close();
        } catch (IOException e) {
            System.out.println("Error reading venues file: " + e.getMessage());
        }
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(driversFilePath));
            String line;
        while ((line = reader.readLine()) != null) {
            // split the line into an array of values
            String[] values = line.split(",");
            
        // create a new Diver object based on the values
            Driver driver = new Driver( values[0],Integer.parseInt(values[1]), values[2]);
        
        // add the new Driver object to the venues ArrayList
            drivers.add(driver);
          //  System.out.println(driver);
        }
        reader.close();
        } catch (IOException e) {
            System.out.println("Error reading driver file: " + e.getMessage());
        }    
        
        

    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    public int getMINOR_MECHANICAL_FAULT() {
        return MINOR_MECHANICAL_FAULT;
    }

    public int getMAJOR_MECHANICAL_FAULT() {
        return MAJOR_MECHANICAL_FAULT;
    }

    public int getUNRECOVERABLE_MECHANICAL_FAULT() {
        return UNRECOVERABLE_MECHANICAL_FAULT;
    }
    
    public String printDrivers() {
        String s = new String();
        for(Driver d : drivers) {
        s = s + "Name: " + d.getName() + "\nSpecial skill: " + d.getSpecialSkill() +
                "\nAccumulated time: " + d.getAccumulatedTime() +  "\nAccumulated points: " + d.getAccumulatedPoints()+"\n Ranking: " + d.getRanking()+"\n\n";
        }
        return s;
    }
    
    public void prepareForTheRace(){
        
        Collections.sort(this.drivers, new DriverRankingComparator(1));
         
        if(this.drivers.get(1).isEligibleToRace())
            this.drivers.get(1).setAccumulatedTime(this.drivers.get(1).getAccumulatedTime() + FRONT_ROW_DELAY);
        if(this.drivers.get(2).isEligibleToRace())
            this.drivers.get(2).setAccumulatedTime(this.drivers.get(2).getAccumulatedTime() + SECOND_ROW_DELAY);
        if(this.drivers.get(3).isEligibleToRace())
            this.drivers.get(3).setAccumulatedTime(this.drivers.get(3).getAccumulatedTime() + THIRD_ROW_DELAY);
        for (int i = 4; i < drivers.size(); i++) {
            if(this.drivers.get(i).isEligibleToRace())
                this.drivers.get(i).setAccumulatedTime(this.drivers.get(i).getAccumulatedTime() + LOWER_HALF_OF_THE_FIELD_DELAY);
        }
    }
    
    void driveAverageLapTime(int i){
        for (Driver driver : drivers) {
            if(driver.isEligibleToRace())
                driver.setAccumulatedTime(driver.getAccumulatedTime() +   this.venues.get(i).getAverageLapTime());
       }
    }
    
    void applySpecialSkills(int lapNo){
        for (Driver driver : drivers) {
           if(driver.isEligibleToRace())
               driver.useSpecialSkill(lapNo);
       }
    }
  
    void checkMechanicalProblem(){
     
        for (Driver driver : drivers) {
            int probability = RNG.getRandomValue(0, 99);
            System.out.println(probability);
            
            if(probability < 5){
                driver.setAccumulatedTime(driver.getAccumulatedTime() + MINOR_REPARE_TIME);
                System.out.println(driver.getName() + "has minor mechanical fault");
            }
            
            if(probability > 4 && probability < 8){
                driver.setAccumulatedTime(driver.getAccumulatedTime() + MAJOR_REPARE_TIME);
                System.out.println(driver.getName() + "has minor mechanical fault");
            }
            
            if(probability == 9){
                driver.setEligibleToRace(false);
                System.out.println(driver.getName() + "HAS EXPERIENCED AN IRREPARABLE MECHANICAL FAILURE");
                System.out.println("It's a shame, let's root for him in the future, thanks for the race" + driver.getName());
            }
            
        }
    }

    void printLeader(int lap){
         Collections.sort(this.drivers, new AccumulatedTime(1));
         System.out.println(this.drivers.get(0).getName() +  "is first place after " + lap + " lap");
    }
    
    void printWinnersAfterRace(String venueName){
        Collections.sort(this.drivers, new AccumulatedTime(1));
        System.out.println("First round over!  ");
        System.out.println("Let's see the stats after " + venueName);
        System.out.println(this.drivers.get(0).getName() +  "is first place in this round");
        System.out.println(this.drivers.get(1).getName() +  "is second place in this round");
        System.out.println(this.drivers.get(2).getName() +  "is third place in this round");
        System.out.println(this.drivers.get(0).getName() +  "is forth place in this round");
    }
    
    void rewardingDrivers(){
        Collections.sort(this.drivers, new AccumulatedTime(1));
        
        if(this.drivers.get(0).isEligibleToRace())
            this.drivers.get(0).setAccumulatedPoints(this.drivers.get(0).getAccumulatedPoints() + FIRST_PLACE_REWARD);
        if(this.drivers.get(1).isEligibleToRace())
            this.drivers.get(1).setAccumulatedPoints(this.drivers.get(1).getAccumulatedPoints() + SECOND_PLACE_REWARD);
        if(this.drivers.get(2).isEligibleToRace())
            this.drivers.get(2).setAccumulatedPoints(this.drivers.get(2).getAccumulatedPoints() + THIRD_PLACE_REWARD);
        if(this.drivers.get(3).isEligibleToRace())
            this.drivers.get(3).setAccumulatedPoints(this.drivers.get(3).getAccumulatedPoints() + FOURTH_PLACE_REWARD);
                
    }
    
     void printChampion(int numOfRaces){
         
     }

    
    
}