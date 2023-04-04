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
    final int RAIN_CHANGE_DELAY = 10;
    final int RAIN_DELAY = 5;
    
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
        for(int i = 0; i < this.drivers.size(); i++) {
            Collections.sort(this.drivers, new DriverPointsComparator(-1));
            this.drivers.get(i).setRanking(i + 1); 
            if(this.drivers.get(i).isEligibleToRace()){
                s = s + "Name: " + this.drivers.get(i).getName() + "\nSpecial skill: " +  this.drivers.get(i).getSpecialSkill() +
                "\nAccumulated time: " +  this.drivers.get(i).getAccumulatedTime() +  "\nAccumulated points: " +  this.drivers.get(i).getAccumulatedPoints()+"\n Ranking: " +  this.drivers.get(i).getRanking()+"\n\n";
            }    
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
    
    void resetAfterVenue(){
        System.out.println("reset");
         for(int i = 0; i < this.getDrivers().size(); i++){
                this.getDrivers().get(i).setEligibleToRace(true);
                this.getDrivers().get(i).setAccumulatedTime(0);
                this.getDrivers().get(i).setRainTires(false);
            }
    }
  
    void checkMechanicalProblem(){
     
        for (Driver driver : drivers) {
            int probability = RNG.getRandomValue(0, 99);
           //int probability = 9;
            System.out.println("Random number for probability for mechanical problem: " + probability);
            if(driver.isEligibleToRace()){
                if(probability < 5){
                    driver.setAccumulatedTime(driver.getAccumulatedTime() + MINOR_REPARE_TIME);
                    System.out.println(driver.getName() + " has experienced minor mechanical fault");
            }
            }
            
            if(driver.isEligibleToRace()){
                if(probability > 4 && probability < 8){
                    driver.setAccumulatedTime(driver.getAccumulatedTime() + MAJOR_REPARE_TIME);
                    System.out.println(driver.getName() + " has experienced a major mechanical fault");
                }
            }
            
            if(driver.isEligibleToRace()){
                if(probability == 9){
                    driver.setEligibleToRace(false);
                    System.out.println(driver.getName() + " HAS EXPERIENCED AN IRREPARABLE MECHANICAL FAILURE");
                    System.out.println("See you for the next venue " + driver.getName() + "!");
            }
            }
            
        }
    }
    
    void rainCheck(){
        for (Driver driver : drivers) {
            int probability = RNG.getRandomValue(0, 99);
            System.out.println("Eandom number for rain probability: " + probability);
            if(driver.isEligibleToRace()){
                if(probability < 51){
                    driver.setRainTires(true);
                    System.out.println("Driver " + driver.getName() + " changed tires for rain");
                     driver.setAccumulatedTime(driver.getAccumulatedTime() + RAIN_CHANGE_DELAY);                   
                }
            }
        }
    }
    
    void rainProblem(int venue){
        double chanceOfRain = this.venues.get(venue).getChanceOfRain();
        System.out.println(chanceOfRain);
        int probability = RNG.getRandomValue(0, 99);
        //int probability = 1;
        double chanceOfRaining = chanceOfRain * 100; 
        System.out.println(probability);
        if(probability < chanceOfRaining){
            for (Driver driver : drivers){            
                if(driver.isEligibleToRace()){
                    if(driver.getRainTires() == false){
                        System.out.println("Driver " + driver.getName() + " doesn't have tires for rain!");
                        driver.setAccumulatedTime(driver.getAccumulatedTime() + RAIN_DELAY);  
                    }
                                     
                }
            }
        }
    }

    void printLeader(int lap){
             System.out.println(this.drivers.get(0).getName() +  "is first place after " + (lap + 1) + ". lap");
    }
         
         
    
    void printWinnersAfterRace(String venueName){
        System.out.println("Race over!  ");
        System.out.println("Let's see the stats after " + venueName);
        System.out.println(this.drivers.get(0).getName() +  " is first place in this round");
        System.out.println(this.drivers.get(1).getName() +  " is second place in this round");
        System.out.println(this.drivers.get(2).getName() +  " is third place in this round");
        System.out.println(this.drivers.get(3).getName() +  " is fourth place in this round");
    }
    
    void rewardingDrivers(){
        Collections.sort(this.drivers, new AccumulatedTime(1));
        int reward = 8;
        for(int i = 0; i < this.drivers.size(); i++){
            if (this.drivers.get(i).isEligibleToRace())
            {
             this.drivers.get(i).setAccumulatedPoints(this.drivers.get(i).getAccumulatedPoints() + reward);
             System.out.println(this.drivers.get(i).getName() +  " will get "+ reward + " points for lap");
                switch (reward) {
                    case 8:
                        reward = 5;
                        break;
                    case 5:
                        reward = 3;
                        break;
                    case 3:
                        reward = 1;
                        break;
                    case 1:
                        reward = 0;
                        break;
                    default:
                        break;
                }                     
            }
        }
    }
     void printChampion(int numOfRaces){
         System.out.println("And after " + numOfRaces + " races " +  " \n CHAMPION IS " + this.drivers.get(0).getName() + " ,congratulations!!!! *_*)");
     }   
}