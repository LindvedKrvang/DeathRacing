import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends SmoothMover
{
    private final int SWITCH_EXPLOSION_IMAGE = 5;
    private final int NO_POWER_UP = 0;
    private final int MINE = 1;
    private final int OIL = 2;
    private final int MISSILE = 3;
    private final int AMOUNT_OF_POWER_UPS = 4;
    private final int BOUNCE_TIMER = 2;
    private final int OIL_TIMER_MAX = 10;
    private final int MAX_LAPS = 3;
    private final String EMPTY_POWER_UP_FIELD = "emptyPowerUp.png";
    private GreenfootImage image1;
    private boolean canBounce;
    private int timer;
    private int currentPowerUp;
    private PowerUp[] powerUpArray;
    private int player;
    private DisplayPowerUp displayField;
    private String keyShoot;
    private int oilTimer;
    private boolean drivenThroughOil;
    
    private GreenfootImage explosionOne;
    private GreenfootImage explosionTwo;
    private GreenfootImage explosionThree;
    private GreenfootImage explosionFour;
    private GreenfootImage explosionFive;
    private GreenfootImage explosionSix;
    private GreenfootImage explosionSeven;
    private GreenfootImage explosionEight;
    private GreenfootImage wreck;
    private boolean explosionStarted;
    private int explosionImageCounter;

    private boolean isDestroyed;
    
    private boolean[] checkPoints;
    private int lapCounter;
    private DisplayLap displayLapField;
    private GreenfootImage[] lapImageArray = {new GreenfootImage("NumberOne.png"), new GreenfootImage("NumberTwo.png"), new GreenfootImage("NumberThree.png")};
    private Countdown countdown;
    private GreenfootImage imageOfCar;
    
    private String explosionCarSound;
    
    /**
     * Create a player and initialize its image.
     */
    public Player(String keyUp, String keyDown, String keyLeft, String keyRight, String keyShoot, String image, int player, DisplayPowerUp display, DisplayLap displayLap, Countdown countdown)
    {        
        super(keyUp,keyDown,keyLeft,keyRight);
        this.player = player;
        this.keyShoot = keyShoot;
        displayField = display;
        displayField.setImage(EMPTY_POWER_UP_FIELD);
        setImage(image);
        canBounce = true;
        currentPowerUp = NO_POWER_UP;
        powerUpArray = new PowerUp[AMOUNT_OF_POWER_UPS];
        drivenThroughOil = false;
        oilTimer = 0;
        
        imageOfCar = new GreenfootImage(image);        
        explosionOne = new GreenfootImage("bigExplosionOne.png");
        explosionTwo = new GreenfootImage("bigExplosionTwo.png");
        explosionThree = new GreenfootImage("bigExplosionThree.png");
        explosionFour = new GreenfootImage("bigExplosionFour.png");
        explosionFive = new GreenfootImage("bigExplosionFive.png");
        explosionSix = new GreenfootImage("bigExplosionSix.png");
        explosionSeven = new GreenfootImage("bigExplosionSeven.png");
        explosionEight = new GreenfootImage("bigExplosionEight.png");
        wreck = new GreenfootImage("wreck.png");
        explosionStarted = false;
        explosionImageCounter = 0;
        isDestroyed = false;
        checkPoints = new boolean[3];
        lapCounter = 0;
        displayLapField = displayLap;
        displayLapField.setImage(lapImageArray[0]);
        displayLapField.getImage().scale(20, 30); 
        this.countdown = countdown;
        explosionCarSound = "smallExplosion.wav";
    }

    /**
     * Act - do whatever the Player1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!isDestroyed && countdown.canDrive())
        {
            keyboardAccel();
            checkKeypress();
            collisionSolidObject();
            timerOn();
            collisionPowerUp();            
            collisionMine();    
            collisionOil();
            collisionCheckPoint();
            collisionFinnishLine();
            useWeapon(); 
        }  
        collisionMissile();
        explosionAnimation();     
    }

    /**
     * Check whether a control key on the keyboard has been pressed.
     * If it has, react accordingly.
     */
    private void checkKeypress()
    {
        if (Greenfoot.isKeyDown(keyLeft) && super.speed > 0) 
        {
            turn(-3);
        }
        if (Greenfoot.isKeyDown(keyRight)&& super.speed > 0) 
        {
            turn(3);
        }
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
            if(canBounce)
            {
                if(getSpeed() > 0)
                {
                    setSpeed(-1);                    
                }
                else if(getSpeed() < 0)
                {
                    setSpeed(1);
                }
                else
                {
                    setSpeed(-2);
                }
                canBounce = false;
            }            
        }
    }

    /**
     * When the player can't bounce, starts a timer, when the timer reaches BOUNCE_TIMER. Set canBounce = true and reset timer.
     */
    private void timerOn()
    {
        if(!canBounce)
        {
            timer++;
        }
        if(timer > BOUNCE_TIMER)
        {
            canBounce = true;
            timer = 0;
        }
        if(drivenThroughOil)
        {
            oilTimer++;
        }
        if(oilTimer >= OIL_TIMER_MAX)
        {
            drivenThroughOil = false;
            oilTimer = 0;
        }
    } 
   
    
    /**
    *Pick up a powerUp when driving over one.
    */
    private void collisionPowerUp()
    {
        PowerUp powerUp = (PowerUp) getOneObjectAtOffset(0, 0, PowerUp.class);
        if(powerUp != null)
        {
            currentPowerUp = powerUp.getKindOfPowerUp() + 1;
            displayField.setImage(powerUp.getVisualOfPowerUp());
            powerUp.setLocation(-50, -50);
            powerUp.setBeenPickedUp(true);
        }
    }
    
    /**
    *Checks if the player has a weapon. If yes, return a reference to a new weapon of the given type.
    */
    private Weapon getWeapon()
    {
        Weapon weapon;
        if(currentPowerUp != NO_POWER_UP)
        {
            if(currentPowerUp == MINE)
            {
                weapon = new Mine();
            }
            else if(currentPowerUp == OIL)
            {
                weapon = new Oil();
            }
            else
            {
                weapon = new Missile(getRotation());
            }
        }
        else
        {
            weapon = null;
        }
        return weapon;
    }
    
    /**
     * If the player has a powerUp, use it when the shoot key is pressed.
     */
    private void useWeapon()
    {
        Weapon weapon = getWeapon();
        if(weapon != null && Greenfoot.isKeyDown(keyShoot))
        {
            getWorld().addObject(weapon, getX(), getY());
            weapon.setRotation(getRotation());
            weapon.move(-25);
            displayField.setImage(EMPTY_POWER_UP_FIELD);
            currentPowerUp = NO_POWER_UP;
        }
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
     * Return the image of the car.
     */
    public GreenfootImage getImageOfCar()
    {
        return imageOfCar;
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
