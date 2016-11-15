import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mine extends Weapon
{
    private final int MINE_NUMBER = 1;
    private GreenfootImage imageOne;
    private GreenfootImage imageTwo;
    
    public Mine()
    {
        imageOne = new GreenfootImage("mineActiveOne.png");
        imageTwo = new GreenfootImage("mineActiveTwo.png");
        setImage(imageOne);
    }
    
    /**
     * Act - do whatever the Mine wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        animation();
    }
    
    /**
     * Return the number of the weapon to indentify it.
     */
    public int getNumberOfWeapon()
    {
        return MINE_NUMBER;
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
}
