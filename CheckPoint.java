import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckPoints here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckPoint extends Actor
{
    private int checkPointNumber;
    
    public CheckPoint(int checkPointNumber)
    {
        this.checkPointNumber = checkPointNumber;
        this.getImage().setTransparency(0);
    }
    
    /**
     * Returns the checkPointNumber.
     */
    public int getCheckPointNumber()
    {
        return checkPointNumber;
    }
}
