import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Mover is an actor that also has 'move' and 'turn' ability. Both moving and turning 
 * are relative to its current position. When moving, the Mover will move in the direction 
 * it is currently facing.
 * 
 * Both 'move' and 'turn' methods are available with or without parameters.
 * 
 * The 'Mover' class is a subclass of Actor. It can be used by creating subclasses, or
 * copied into scenarios and edited inline.
 * 
 * The initial direction is to the right. Thus, this class works best with images that
 * face right when not rotated.
 * 
 * This is extended from the Mover class.
 * Credit to Michael Kolling
 * 
 * @author MathManiac(via Greenfoot)
 * @version Unknown
 */
public class SmoothMover extends Actor
{
    private static final double WALKING_SPEED = 0.1;
    protected double speed = 0;
    protected double maxspeed;
    protected String keyDown;
    protected String keyRight;
    protected String keyLeft;
    protected String keyUp;


    public SmoothMover(String keyUp, String keyDown, String keyLeft, String keyRight){
        maxspeed = 3;
        this.keyDown = keyDown;
        this.keyUp=keyUp;
        this.keyLeft=keyLeft;
        this.keyRight=keyRight;
        //this.pad=pad;
    }

    public void act() 
    {
      keyboardAccel();
    }

    /**
     * Turn 90 degrees to the right (clockwise).
     */
    public void turn()
    {
        turn(90);
    }

    /**
     * Move a bit forward in the current direction.
     */
    public void move()
    {
        move(WALKING_SPEED);
    }

    /**
     * Move a specified distance forward in the current direction.
     */
    public void move(double distance)
    {
        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(angle) * distance);
        int y = (int) Math.round(getY() + Math.sin(angle) * distance);

        setLocation(x, y);
    }

    public void keyboardAccel()
    {
        if (Greenfoot.isKeyDown(keyUp)) {
            speed += 0.04;
            if (speed > maxspeed) 
            {
                speed = maxspeed;
            }
        } else {
            speed -= 0.03;
            if (speed < 0) 
            {
                speed = 0;
            }
        }
        move(speed);
    }

    /**
     * Sets the speed of the player.
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    /**
     * Return the speed of the player.
     */
    public double getSpeed()
    {
        return speed;
    }
}
