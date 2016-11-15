import greenfoot.*;
import java.awt.Color;

/**
 * Pheromones are dropped by ants when they want to communicate something to
 * other ants.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class PheromoneTrail extends Actor
{
    private final static int MAX_INTENSITY = 180;
    private int intensity;

    /**
     * Create a new drop of pheromone with full intensity.
     */
    public PheromoneTrail()
    {
        intensity = MAX_INTENSITY;
        updateImage();
    }

    /**
     * Make the image. The size and transparency are proportional to the intensity.
     */
    private void updateImage()
    {
        int size = intensity / 3 + 5;
        GreenfootImage image = new GreenfootImage(size + 1, size + 1);
        int alpha = intensity / 3;
        image.setColor(new Color(255, 255, 255, alpha));
        image.fillOval(0, 0, size, size);
        image.setColor(Color.DARK_GRAY);
        image.fillRect(size / 2, size / 2, 2, 2);   // small dot in the middle
        setImage(image);
        getImage().setTransparency(0);
    }
}