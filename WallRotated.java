import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WallRotated here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallRotated extends Wall
{
    public WallRotated()
    {
        this.getImage().scale(100, 20);
        this.getImage().rotate(45);
        this.getImage().setTransparency(0);
    }  
}
