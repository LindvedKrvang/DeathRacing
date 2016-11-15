import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayWinner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayWinner extends Actor
{    
    public DisplayWinner(GreenfootImage imageOfWinner)
    {
        setImage(imageOfWinner);
        getImage().scale(300, 120);
    }   
}
