/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grandprix_test;

import java.util.Scanner;
import java.io.IOException;
import java.util.Collections;

/**
 *
 * @author marija
 */
public class GrandPrix_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        Championship championship = new Championship("vozaci.txt", "staze.txt");

        // Pitaj za broj trka
        int numRaces = 0;
        while (numRaces < 3 || numRaces > 5) {
            System.out.print("Enter the number of races in the season (3-5): ");
            numRaces = scanner.nextInt();
        }
        
        for(int noOfRaces = 0; noOfRaces < numRaces; noOfRaces++){
            
        championship.prepareForTheRace(); 
        System.out.println("CHECK TIME");
        System.out.println("print everything " + championship.printDrivers());
                    
        System.out.println("And this are this season's starting positions:");
        for(int j = 0; j < championship.getDrivers().size(); j++){
            System.out.println((j+1) + "." + championship.getDrivers().get(j).getName() + " " +championship.getDrivers().get(j).getRanking());
        }
        
        System.out.println("\n Available venues:");
        for (int i = 0; i < championship.getVenues().size(); i++) {
            System.out.println((i+1) + ". " + championship.getVenues().get(i).getVenueName());
        }

        System.out.print("\n Enter the number of the venue you want to use: " + "*****************************************************");
        int venueNumber = scanner.nextInt();
        
        Venue selectedVenue = championship.getVenues().get(venueNumber - 1);
        int selectedVenueNoOfLaps = selectedVenue.numberOfLaps;
        int index = championship.getVenues().indexOf(selectedVenue);
             
        System.out.println("\nChosen venue is: " + selectedVenue.getVenueName());  
        System.out.println("And how many laps does it have ?: " + "It has " + selectedVenueNoOfLaps + " laps");  
            
        for(int noOfLaps = 0; noOfLaps < selectedVenueNoOfLaps; noOfLaps++){
            championship.driveAverageLapTime(index);
            System.out.println("\n Add " + championship.getVenues().get(index).getAverageLapTime() + " to all drivers");
                
            championship.applySpecialSkills(noOfLaps);
                    
            championship.checkMechanicalProblem();
                
            if(noOfLaps == 1){
                championship.rainCheck();
            }
                
            championship.rainProblem(index);
                
            championship.rewardingDrivers();
                
            System.out.println("\n CHECK TIME");
            System.out.println("\n print everything " + championship.printDrivers());
                    
            championship.printLeader(noOfLaps);
            }
        
        String venueName = championship.getVenues().get(index).getVenueName();
        championship.printWinnersAfterRace(venueName);
              
        // If the selected venue was found in the list, remove it
        if (index >= 0) {
            championship.getVenues().remove(selectedVenue);
        }
            
        championship.resetAfterVenue();
        }
        
    championship.printChampion(numRaces);
    }
}
