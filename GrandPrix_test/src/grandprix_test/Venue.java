/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grandprix_test;

/**
 *
 * @author marija
 */
public class Venue {
    private int averageLapTime;
    private double chanceOfRain;
    int numberOfLaps;
    private String venueName;

    public Venue(String venueName, int numberOfLaps, int averageLapTime, double chanceOfRain) {
        this.venueName = venueName;
        this.numberOfLaps = numberOfLaps;
        this.averageLapTime = averageLapTime;
        this.chanceOfRain = chanceOfRain;
    }


    public int getAverageLapTime() {
        return averageLapTime;
    }

    public void setAverageLapTime(int averageLapTime) {
        this.averageLapTime = averageLapTime;
    }

    public double getChanceOfRain() {
        return chanceOfRain;
    }

    public void setChanceOfRain(double chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public int getNumberOfLaps() {
        return numberOfLaps;
    }

    public void setNumberOfLaps(int numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }
    
   
    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    
    @Override
    public String toString() {
        return "Venue name: " + this.venueName + "\n Average Lap Time: " + "\n Number of Laps: " + this.numberOfLaps + this.averageLapTime +  "\n Chance of Rain: " + this.chanceOfRain + "\n\n";
    }
}
