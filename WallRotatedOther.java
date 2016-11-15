import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WallRotatedOther here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallRotatedOther extends Wall
{
    public WallRotatedOther()
    {
        this.getImage().scale(100, 20);
        this.getImage().rotate(135);
        this.getImage().setTransparency(0);
    }   
}
