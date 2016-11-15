import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Oil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Oil extends Weapon
{
    private final int OIL_NUMBER = 2;
    
    public Oil()
    {
        setImage("oilActive.png");
    }
    
    /**
     * Return the number of the weapon to indentify it.
     */
    public int getNumberOfWeapon()
    {
        return OIL_NUMBER;
    }
}
