/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grandprix_test;

import java.util.Random;

/**
 *
 * @author marija
 */
public class RNG {
    private int minValue;
    private int maxValue;
    static Random rnd  = new Random();

    public RNG(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    static public int getRandomValue(int minValue, int maxValue){
        return rnd.nextInt(maxValue - minValue +1) + minValue;
    }
  
    
}
