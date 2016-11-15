import greenfoot.*;
import java.util.*;
/**
 * Write a description of class PlayerAi here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerAi extends Actor
{
    /** How long do we keep direction after finding pheromones. */
    private static final int PH_TIME = 1;
    private final int MAX_SPEED = 3;
    /** The maximum movement speed of the car. */
    
    private final int MAX_LAPS = 3;
    private final int SWITCH_EXPLOSION_IMAGE = 5;
    private final String EMPTY_POWER_UP_FIELD = "emptyPowerUp.png";
    
    PheromoneTrail currentPheromone=null;    
    private DisplayPowerUp displayField;
    private int speed = 3;

    private int trailCounter = 0;
    private boolean explosionStarted;
    private GreenfootImage explosionOne;
    private GreenfootImage explosionTwo;
    private GreenfootImage explosionThree;
    private GreenfootImage explosionFour;
    private GreenfootImage explosionFive;
    private GreenfootImage explosionSix;
    private GreenfootImage explosionSeven;
    private GreenfootImage explosionEight;
    private GreenfootImage wreck;
    private int explosionImageCounter;
    private String explosionCarSound;
    private boolean isDestroyed;
    private boolean drivenThroughOil;
    
    private boolean[] checkPoints;
    private int lapCounter;
    private DisplayLap displayLapField;
    private GreenfootImage[] lapImageArray = {new GreenfootImage("NumberOne.png"), new GreenfootImage("NumberTwo.png"), new GreenfootImage("NumberThree.png")};
    private GreenfootImage imageOfCar;
    private Countdown countdown;

    public PlayerAi(String image, DisplayPowerUp display, DisplayLap displayLap, Countdown countdown)
    {
        setImage(image);
        checkPoints = new boolean[3];
        lapCounter = 0;
        displayLapField = displayLap;
        displayLapField.setImage(lapImageArray[0]);
        displayLapField.getImage().scale(20, 30); 
        imageOfCar = new GreenfootImage(image);  
        this.countdown = countdown;
        explosionOne = new GreenfootImage("bigExplosionOne.png");
        explosionTwo = new GreenfootImage("bigExplosionTwo.png");
        explosionThree = new GreenfootImage("bigExplosionThree.png");
        explosionFour = new GreenfootImage("bigExplosionFour.png");
        explosionFive = new GreenfootImage("bigExplosionFive.png");
        explosionSix = new GreenfootImage("bigExplosionSix.png");
        explosionSeven = new GreenfootImage("bigExplosionSeven.png");
        explosionEight = new GreenfootImage("bigExplosionEight.png");
        explosionCarSound = "smallExplosion.wav";
        wreck = new GreenfootImage("wreck.png");
        isDestroyed = false;
        explosionStarted = false;
        explosionImageCounter = 0;
        drivenThroughOil = false;
        displayField = display;
        displayField.setImage(EMPTY_POWER_UP_FIELD);
    }

    public void act()
    {
        List<PheromoneTrail> trailList = ((DeathRaceWorld)getWorld()).getQueuedPheromones();
        if (currentPheromone == null)
        {
            currentPheromone = trailList.get(trailCounter++);
        }
        if(trailCounter != trailList.size() && countdown.canDrive() && !isDestroyed)
        {
            turnTowards(currentPheromone.getX(), currentPheromone.getY());
            move(MAX_SPEED);
            if(findDistanceTo(currentPheromone)<5)
            {
                currentPheromone = trailList.get(trailCounter++);
            }
        }
        else
        {
            currentPheromone = null;
            trailCounter = 0;
        }
        collisionSolidObject();
        collisionMissile();
        collisionMine();
        collisionOil();
        collisionCheckPoint();
        collisionFinnishLine();
        explosionAnimation();
    }
    
    /**
     * Makes the player unable to leave the track and drive through other players.
     */
    private void collisionSolidObject()
    {
        Wall wall = (Wall) getOneObjectAtOffset(0, 0, Wall.class);
        Player player = (Player) getOneObjectAtOffset(0, 0, Player.class);  
        PlayerAi playerAi = (PlayerAi) getOneObjectAtOffset(0, 0, PlayerAi.class);
        if(wall != null || player != null || playerAi != null)
        {
                if(speed > 0)
                {
                    speed = -1;                    
                }
                else if(speed < 0)
                {
                    speed = 1;
                }
                else
                {
                    speed = -2;
                }                       
        }
    }

    public double findDistanceTo(Actor other)
    {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }

    /**
     * Make sure the speed returned is in the range [-SPEED .. SPEED].
     */
    private int capSpeed(int speed)
    {
        if (speed < -MAX_SPEED)
            return -MAX_SPEED;
        else if (speed > MAX_SPEED)
            return MAX_SPEED;
        else
            return MAX_SPEED;
    }
    
    /**
     * When travelling through a checkpoint, mark that checkPoint as true.
     */
    public void collisionCheckPoint()
    {
        CheckPoint checkPoint = (CheckPoint) getOneObjectAtOffset(0, 0, CheckPoint.class);
        if(checkPoint != null)
        {
            int checkPointNumber = checkPoint.getCheckPointNumber();
            checkPoints[checkPointNumber] = true;
        }
    }
    
    /**
     * When reaching the finnishLine, check if all checkPoints have been reached. If yes, set lapCounter++ and checkPoints[] to false.
     */
    public void collisionFinnishLine()
    {
        FinishLine finnishLine = (FinishLine) getOneObjectAtOffset(0, 0, FinishLine.class);        
        if(finnishLine != null)
        {
            boolean allCheckPointsReached = true;
            for(int i = 0; i < checkPoints.length; i++)
            {
                if(checkPoints[i] == false)
                {
                    allCheckPointsReached = false;
                }
            }
            if(allCheckPointsReached == true)
            {
                for(int i = 0; i < checkPoints.length; i++)
                {
                    checkPoints[i] = false;
                }
                lapCounter++;
                setLapVisual(lapCounter);
                if(hasWon())
                {
                    Greenfoot.setWorld(new WinningWorld(imageOfCar));
                }
            }
        }
    }
    
    /**
     * Updates the visual of the lap beneath the player portrait.
     */
    private void setLapVisual(int lap)
    {
        if(lap < MAX_LAPS)
        {
            displayLapField.setImage(lapImageArray[lap]);
            displayLapField.getImage().scale(20, 30);
        }        
    }
    
    /**
     * Checks if the player won. Return true if yes, false if no.
     */
    private boolean hasWon()
    {
        boolean playerWon = false;
        if(lapCounter == MAX_LAPS)
        {
            playerWon = true;
        }
        return playerWon;
    }
    
    /**
     * If the player collides with a missile, start an exsplosion.
     */
    private void collisionMissile()
    {
        Missile missile = (Missile) getOneIntersectingObject(Missile.class);
        if(missile != null && missile.getActivated() == true)
        {
            missile.setExplosionStarted(true);
            explosionStarted = true;
        }
    }
    
    /**
     * If the player collides with a mine, start an explosion.
     */
    private void collisionMine()
    {
        Mine mine = (Mine) getOneIntersectingObject(Mine.class);
        if(mine != null)
        {
            getWorld().removeObject(mine);
            explosionStarted = true;            
        }
    }
    
    /**
     * If the player collides with oil, turn the car a random degree between 0 and 45 to either left or right.
     */
    private void collisionOil()
    {        
        Oil oil = (Oil) getOneIntersectingObject(Oil.class);
        if(oil != null && !drivenThroughOil)
        {
            if(Greenfoot.getRandomNumber(2) == 0)
            {
                int rotation = getRotation() + Greenfoot.getRandomNumber(45);
                setRotation(rotation);
            }
            else
            {
                int rotation = getRotation() - Greenfoot.getRandomNumber(45);
                setRotation(rotation);
            }
            drivenThroughOil = true;
        }
    }
    
    /**
     * Runs the explosion animation for the car and place a wreck afterwards.
     */
    private void explosionAnimation()
    {
        if(explosionStarted)
        {
            if(explosionImageCounter == 0)
            {
                setImage(explosionOne);
                Greenfoot.playSound(explosionCarSound);
                isDestroyed = true;
            }
            else if(explosionImageCounter == 1 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(explosionTwo);
            }
            else if(explosionImageCounter == 2 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(explosionThree);
            }
            else if(explosionImageCounter == 3 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(explosionFour);
            }
            else if(explosionImageCounter == 4 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(explosionFive);
            }
            else if(explosionImageCounter == 5 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(explosionSix);
            }
            else if(explosionImageCounter == 6 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(explosionSeven);
            }
            else if(explosionImageCounter == 7 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(explosionEight);
            }
            else if(explosionImageCounter >= 8 * SWITCH_EXPLOSION_IMAGE)
            {
                setImage(wreck);                
            }
            explosionImageCounter++;
        }
    }
}
