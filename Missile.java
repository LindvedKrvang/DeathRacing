import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Missile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Missile extends Weapon
{
    private final int MISSILE_NUMBER = 3;
    private final int SWITCH_IMAGE = 10;
    private final int SWITCH_EXPLOSION_IMAGE = 10;
    private final int READY_TO_ACTIVATE = 30;
    private boolean activated;
    private GreenfootImage imageOne;
    private GreenfootImage imageTwo;
    private GreenfootImage explosionOne;
    private GreenfootImage explosionTwo;
    private GreenfootImage explosionThree;
    private GreenfootImage explosionFour;
    private int activatedTimer;
    private int timer;
    private int explosionTimer;
    private int explosionImageCounter;
    private boolean explosionStarted;
    private String missileExplosionSound;
    
    public Missile(int rotation)
    {
        imageOne = new GreenfootImage("missileActiveOne.png");
        imageTwo = new GreenfootImage("missileActiveTwo.png");
        explosionOne = new GreenfootImage("missileExplosionOne.png");
        explosionTwo = new GreenfootImage("missileExplosionTwo.png");
        explosionThree = new GreenfootImage("missileExplosionThree.png");
        explosionFour = new GreenfootImage("missileExplosionFour.png");
        setImage(imageOne);
        setRotation(rotation);
        timer = 0;
        explosionTimer = 0;
        explosionImageCounter = 0;
        explosionStarted = false;
        activated = false;
        activatedTimer = 0;
        missileExplosionSound = "smallExplosion.wav";
    }
    
    /**
     * Act - do whatever the Missile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
        collisionWall();
        controlTimer();
        timer++;
        explosionAnimation();
    } 
    
    /**
     * Makes the missile move if it is not destroyed.
     */
    private void movement()
    {
        if(!explosionStarted)
        {
            move(5);
        }
    }
    
    /**
     * Remove the object if it hits a wall.
     */
    private void collisionWall()
    {
        Wall wall = (Wall) getOneIntersectingObject(Wall.class);
        if(wall != null)
        {
            //getWorld().removeObject(this)
            explosionStarted = true;
        }
    }
    
    /**
     * Runs the animation of the explosion and then removes the object.
     */
    private void explosionAnimation()
    {
        if(explosionStarted)
        {            
            if(explosionImageCounter == 0)
            {
                Greenfoot.playSound(missileExplosionSound);
                setImage(explosionOne);
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
            else if(explosionImageCounter >= 4 * SWITCH_EXPLOSION_IMAGE)
            {                
                getWorld().removeObject(this);
            }
            explosionImageCounter++;
        }
    }
    
    /**
     * Switch the image of the missile.
     */
    private void animation()
    {
        if(getImage() == imageOne)
        {
            setImage(imageTwo);
        }
        else
        {
           setImage(imageOne); 
        }
    }
    
    /**
     * Checks whether it's time to switch image.
     */
    private void controlTimer()
    {
        if(timer >= 10 && explosionStarted != true)
        {
            animation();
            timer = 0;
        }
        if(!activated)
        {
            activatedTimer++;
        }
        if(activatedTimer == READY_TO_ACTIVATE)
        {
            activated = true;
        }
    }
    
    /**
     * Return the number of the weapon to indentify it.
     */
    public int getNumberOfWeapon()
    {
        return MISSILE_NUMBER;
    }
    
    /**
     * Makes it able to start th explosion from outside the class.
     */
    public void setExplosionStarted(boolean explosionStarted)
    {
        this.explosionStarted = explosionStarted;
    }
    
    /**
     * Returns the activated boolean.
     */
    public boolean getActivated()
    {
        return activated;
    }
}
