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

    public Championship(String driversFilePath, String venuesFilePath) throws FileNotFoundException {
        this.drivers = new ArrayList<>();
        this.venues = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(venuesFilePath));
            String line;
        while ((line = reader.readLine()) != null) {
            // split the line into an array of values
            String[] values = line.split(",");
        
        /*    System.out.println("values[0]: " + values[0]);
            System.out.println("values[1]: " + values[1]);
            System.out.println("values[2]: " + values[2]);
            System.out.println("values[3]: " + values[3]);
*/
            
        // create a new Venue object based on the values
            Venue venue = new Venue( values[0],Integer.parseInt(values[1]), Integer.parseInt(values[2]), Double.parseDouble(values[3]));
        
        // add the new Venue object to the venues ArrayList
            venues.add(venue);
            System.out.println(venue);
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Error reading venues file: " + e.getMessage());
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



}
