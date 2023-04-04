/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grandprix_test;

/**
 *
 * @author marija
 */
public class Driver implements Comparable<Driver>{
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;
    private int accumulatedTime;
    private int accumulatedPoints;
    private boolean rainTires;

    public Driver(String name, int ranking, String specialSkill) {
        this.name = name;
        this.ranking = ranking;
        this.specialSkill = specialSkill;
        this.accumulatedTime = 0;
        this.accumulatedPoints = 0;
        this.eligibleToRace = true;
        this.rainTires = false;
    }
    
    final int CORNERING_L = 1;
    final int CORNERING_H = 8;
    
    final int BREAKING_L = 1;
    final int BREAKING_H = 8;
    
    final int OVERTAKING_L = 10;
    final int OVERTAKING_H = 20;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(String specialSkill) {
        this.specialSkill = specialSkill;
    }

    public boolean isEligibleToRace() {
        return eligibleToRace;
    }

    public void setEligibleToRace(boolean eligibleToRace) {
        this.eligibleToRace = eligibleToRace;
    }

    public int getAccumulatedTime() {
        return accumulatedTime;
    }

    public void setAccumulatedTime(int accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

    public boolean getRainTires() {
        return rainTires;
    }

    public void setRainTires(boolean rainTires) {
        this.rainTires = rainTires;
    }
    
    public void useSpecialSkill(int lapNo)
    {
        int bonusTime = 0;
        
        if(this.specialSkill.equals("Cornering")){
            bonusTime = RNG.getRandomValue(CORNERING_L, CORNERING_H);
             System.out.println(this.name + " used Cornering!" + bonusTime);
        }
        
        if(this.specialSkill.equals("Braking")){
            bonusTime = RNG.getRandomValue(BREAKING_L, BREAKING_H);
            System.out.println(this.name + " used Breaking!" + bonusTime);
        }
        
        if(this.specialSkill.equals("Overtaking")){
            if(lapNo % 3 == 2){
                bonusTime = RNG.getRandomValue(OVERTAKING_L, OVERTAKING_H);
                System.out.println(this.name + " used Overtaking!" + bonusTime);
            }
        }
        
        this.accumulatedTime -= bonusTime;
    }
    
    @Override
    public String toString() {
        return "Driver name: " + this.name + "\n Ranking: " +  this.ranking + "\n Special skill: " + this.specialSkill + "\n Accumulated time: " + this.accumulatedTime +  "\n Accumulated points: " + this.accumulatedPoints + "\n\n";
    }
    
    @Override
    public int compareTo(Driver d) {
        return -Integer.compare(d.accumulatedTime, this.accumulatedTime);
    }
    
}
