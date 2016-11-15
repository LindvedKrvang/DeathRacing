import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinningWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinningWorld extends World
{
    private static final int WORLD_WIDTH = 1280;
    private static final int WORLD_HEIGHT = 600;
    
    /**
     * Constructor for objects of class WinningWorld.
     * 
     */
    public WinningWorld(GreenfootImage imageOfWinner)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        setBackground("WinScreen.png");
        addObject(new DisplayWinner(imageOfWinner), WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
    }
    
    public void act()
    {
        checkEnter();
    }
    
    /**
     * If enter has been clicked, set world to introworld.
     */
    private void checkEnter()
    {
        String key = Greenfoot.getKey();
        
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
}
