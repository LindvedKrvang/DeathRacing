import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Powerup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUp extends Actor
{
    private int theKindOfPowerUp;
    private String imageOfPowerUp;
    private String[] visualsOfPowerUps = {"minePowerUp.png", "oilPowerUp.png", "missilePowerUp.png"};
    private boolean beenPickedUp;
    private boolean timerStarted;
    private int timer;
    private int xCoordinate;
    private int yCoordinate;
    
    public PowerUp(int xCoordinate, int yCoordinate)
    {
        theKindOfPowerUp = Greenfoot.getRandomNumber(visualsOfPowerUps.length);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        beenPickedUp = false;
        timerStarted = false;
    }
    
    /**
     * Act - do whatever the Powerup wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        relocate();
    }    
    
    /**
     * Return the kind of PowerUp.
     */
    public int getKindOfPowerUp()
    {
        return theKindOfPowerUp;
    }
    
    /**
     * Gets the name of the png for the PowerUp.
     */
    public String getVisualOfPowerUp()
    {
        String nameOfVisual = visualsOfPowerUps[theKindOfPowerUp];
        return nameOfVisual;
    }
    
    /**
     * Set beenPickedUp.
     */
    public void setBeenPickedUp(boolean beenPickedUp)
    {
        this.beenPickedUp = beenPickedUp;
    }
    
    /**
     * When picked up. Relocate to outside of the screen. After a while relocate it back at its original position.
     */
    private void relocate()
    {
        if(beenPickedUp == true && timerStarted == false)
        {
            timer = 0;
            beenPickedUp = false;
            timerStarted = true;
        }
        if(timerStarted)
        {
            timer++;
        }
        if(timer >= 200)
        {
            setLocation(xCoordinate, yCoordinate);
            timerStarted = false;
            theKindOfPowerUp = Greenfoot.getRandomNumber(visualsOfPowerUps.length);
        }
    }    
}
