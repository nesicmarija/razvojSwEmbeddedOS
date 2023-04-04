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
public class AccumulatedTime implements Comparator<Driver>{
    int dir = 1;

    public AccumulatedTime(int dir) {
        if(dir!=1 && dir!=-1){
			dir = 1;
		}
		this.dir = dir;
    }
    
    @Override
    public int compare(Driver d1, Driver d2) {
        int result = Integer.compare(d1.getAccumulatedTime(), d2.getAccumulatedTime());
            if (result ==0){
                int random = RNG.getRandomValue(0, 1);
                return (random == 0) ? -1 : 1;
            }
        return dir * result;
	}
}
