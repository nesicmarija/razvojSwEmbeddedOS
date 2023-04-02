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
            System.out.print("Unesite broj trka u sezoni (3-5): ");
            numRaces = scanner.nextInt();
        }
        
        for(int noOfRaces = 0; noOfRaces < numRaces; noOfRaces++){
            
       
            System.out.println("Available venues:");
            for (int i = 0; i < championship.getVenues().size(); i++) {
                System.out.println((i+1) + ". " + championship.getVenues().get(i).getVenueName());
            }

            System.out.print("Enter the number of the venue you want to use: ");
            int venueNumber = scanner.nextInt();
        
            Venue selectedVenue = championship.getVenues().get(venueNumber - 1);

            System.out.println("Izabrali ste venue: " + championship.getVenues().get(venueNumber - 1).getVenueName());  
            System.out.println("Sa brojem krugova: " + championship.getVenues().get(venueNumber - 1).getNumberOfLaps());  
            
            int index = championship.getVenues().indexOf(selectedVenue);
            System.out.println(index);

            int noOfLaps = championship.getVenues().get(index).numberOfLaps;
            System.out.println("Broj krugova: " + noOfLaps);
            // If the selected venue was found in the list, remove it
            if (index >= 0) {
                championship.getVenues().remove(selectedVenue);
            }
        
               /*  System.out.println("Available venues:");
            for (int i = 0; i < championship.getVenues().size(); i++) { 
                System.out.println((i+1) + ". " + championship.getVenues().get(i).getVenueName());
            }
        */
            System.out.println(noOfLaps);
            
            for(int i = 0; i < noOfLaps; i++ ){
                System.out.println(i);
                
                championship.prepareForTheRace(); //azuriramo nakon svake trke
                Collections.sort(championship.getDrivers(), new DriverPointsComparator(1));
                System.out.println("Starting positions:");
                for(int j = 0; j < championship.getDrivers().size(); j++){
                    System.out.println((j+1) + "." + championship.getDrivers().get(j).getName() + " " +championship.getDrivers().get(j).getRanking());
                }
            
            }
            
            
        }
            

    }
    
}
