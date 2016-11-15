import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * makes a countdown before game start.
 * 
 * @ 
 * @
 */
public class Countdown extends Actor
{
     private GreenfootImage numberOne;
     private GreenfootImage numberTwo;
     private GreenfootImage numberThree;
     private GreenfootImage go;
     private int imageSwitch;
     private int startTime;
     public boolean canDrive;
     private String countdownSound;
     
    /**
     * Act - do whatever the Countdown wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        imageSwitch();
        startTime ++;
    }  
    
    /**
     * construter.
     */
    public Countdown()
    {
        numberOne = new GreenfootImage("NumberOne.png");
        numberTwo = new GreenfootImage("NumberTwo.png");
        numberThree = new GreenfootImage("NumberThree.png");
        go = new GreenfootImage("GoImage.png");
        startTime = 0;
        canDrive = false;
        countdownSound = "countdown.wav";
    }
    
    /**
     * switchs image when a set amounts of act has been done.
     */
    private void imageSwitch()
    {
        if (startTime == 0)
        {
            Greenfoot.playSound(countdownSound);
            setImage(numberThree);
        }
        else if (startTime == 50)
        {
            setImage(numberTwo);
        }
        else if (startTime == 95)
        {
            setImage(numberOne);
        }
        else if (startTime == 145)
        {
            setImage(go);
            canDrive = true;
        }
        else if (startTime == 170)
        {
            this.getImage().setTransparency(0);
        }    
    }
    
    /**
     * Return the canDrive boolean.
     */
    public boolean canDrive()
    {
        return canDrive;
    }
}







