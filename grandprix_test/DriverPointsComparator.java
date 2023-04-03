/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grandprix_test;
import java.util.Comparator;
/**
 *
 * @author marija
 */
public class DriverPointsComparator implements Comparator<Driver>{
    
    int dir = 1;

    public DriverPointsComparator(int dir) {
        if(dir!=1 && dir!=-1){
			dir = 1;
		}
		this.dir = dir;
    }
    @Override
    public int compare(Driver d1, Driver d2) {
        return dir*Integer.compare(d1.getAccumulatedPoints(), d2.getAccumulatedPoints());
	}
}

