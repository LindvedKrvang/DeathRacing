import greenfoot.*;
import java.awt.Color;
import java.util.*;

/**
 * The Death Race is starting, fasten your seatbelts.
 * 
 * @author Group 3 
 * @version 1
 */
public class DeathRaceWorld extends World
{
    private static List<PheromoneTrail> trailList = new ArrayList();
    private static final int WORLD_WIDTH =1280;
    private static final int WORLD_HEIGHT = 600;
    private int gameBackCounter;
    private GreenfootImage image1;
    private GreenfootImage image2;
    private GreenfootImage image3;
    private GreenfootImage image4;
    private boolean someOneWon;
    private GreenfootSound music = new GreenfootSound("music.wav");

    public DeathRaceWorld()
    { 
        // calling the other constructor with gameStarted = false.
        this(false); 
        Greenfoot.setSpeed(50);
        gameBackCounter = 0;
    }

    /**
     * Constructor for objects of class DeathRaceWorld.
     */
    public DeathRaceWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1, false); 
        if (gameStarted)
        {
            prepare();
            music.play();
            setPaintOrder(Missile.class, Player.class, PlayerAi.class);
            Countdown countdown = new Countdown();
            addObject(countdown, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
            GreenfootImage background = getBackground();
            background.setColor(Color.black);
            //Adds the displayContainers for the PowerUps.
            DisplayPowerUp disOne = new DisplayPowerUp();
            addObject(disOne, 90, 70);
            DisplayPowerUp disTwo = new DisplayPowerUp();
            addObject(disTwo, 90, 225);
            DisplayPowerUp disThree = new DisplayPowerUp();
            addObject(disThree, 90, 377 ); 
            DisplayPowerUp disFour = new DisplayPowerUp();
            addObject(disFour, 90, 525);
            //Add the fields to display the laps.
            DisplayLap lapPlayerOne = new DisplayLap();
            addObject(lapPlayerOne, 100, 125);
            DisplayLap lapPlayerTwo = new DisplayLap();
            addObject(lapPlayerTwo, 100, 280);
            DisplayLap lapPlayerThree = new DisplayLap();
            addObject(lapPlayerThree, 100, 435);
            DisplayLap lapPlayerFour = new DisplayLap();
            addObject(lapPlayerFour, 100, 580);
            //Add the "Lap" field.
            addObject(new LabField(), 50, 125);
            addObject(new LabField(), 50, 280);
            addObject(new LabField(), 50, 435);
            addObject(new LabField(), 50, 580);
            //Add the invisible finnishLine and the invisible checkPoints.
            addObject(new FinishLine(), 918, WORLD_HEIGHT - 60);
            addObject(new CheckPoint(0), WORLD_WIDTH - 65, 200);
            addObject(new CheckPoint(1), 500, 100);
            addObject(new CheckPoint(2), 500, WORLD_HEIGHT - 170);
            //Add Players.
            addObject(new Player("up","down","left","right", "space", "Player10000.png", 1, disOne, lapPlayerOne, countdown), 837, WORLD_HEIGHT - 43);
            addObject(new Player("w","s","a","d", "e", "Player20000.png", 2, disTwo, lapPlayerTwo, countdown), 765, WORLD_HEIGHT - 43);
            //addObject(new Player("i","k","j","l", "o", "Player30000.png", 3, disThree, lapPlayerThree, countdown), 837, WORLD_HEIGHT - 43);
            //addObject(new Player("8","5","4","6", "9", "Player40000.png", 4, disFour, lapPlayerFour, countdown), 837, WORLD_HEIGHT - 78);
            //Add the AI.
            createPheromones();
            addObject(new PlayerAi("Player30000.png", disThree, lapPlayerThree, countdown), 801, WORLD_HEIGHT - 78);
            addObject(new PlayerAi("Player40000.png", disFour, lapPlayerFour, countdown), 873, WORLD_HEIGHT - 78);
            //Add PowerUps.
            addObject(new PowerUp(500, WORLD_HEIGHT - 70), 500, WORLD_HEIGHT - 70);
            addObject(new PowerUp(375, WORLD_HEIGHT - 170), 375, WORLD_HEIGHT - 170);
            addObject(new PowerUp(250, 90), 250, 90);
            addObject(new PowerUp(WORLD_WIDTH - 100, 90), WORLD_WIDTH - 100, 90);
            addObject(new PowerUp(WORLD_WIDTH - 100, WORLD_HEIGHT - 200), WORLD_WIDTH - 100, WORLD_HEIGHT - 200);
            
            someOneWon = false;

        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    /**
     * Return the trailList.
     */
    public static List<PheromoneTrail> getQueuedPheromones()
    {
        return trailList;
    }

    public void act()
    {        
        changeImage();
        gameBackCounter++;
    }

    /**
     * Change the image on runtime to give the idea of motion.
     */
    public void changeImage()
    {
        // Switching on the gameBackCounter to perform image changes.
        switch (gameBackCounter)
        {
            case 1: setBackground ("DeathRaceBack0000.png");
            break;                    
            case 20: setBackground ("DeathRaceBack0001.png");
            break;                   
            case 40: setBackground ("DeathRaceBack0002.png");
            break;                     
            case 60: setBackground ("DeathRaceBack0000.png");
            break;                     
            case 80: setBackground ("DeathRaceBack0001.png");
            break;                                                               
            case 100: setBackground ("DeathRaceBack0002.png");
            break;
            case 120: gameBackCounter = 0;
            break;
        }

    }
    
    public void displayWinner(GreenfootImage image)
    {
        if(someOneWon)
        {
            
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        WallXLength wallxlength = new WallXLength();
        addObject(wallxlength,805,152);
        WallXLength wallxlength2 = new WallXLength();
        addObject(wallxlength2,840,156);
        WallXLength wallxlength3 = new WallXLength();
        addObject(wallxlength3,872,156);
        WallXLength wallxlength4 = new WallXLength();
        addObject(wallxlength4,907,154);
        WallXLength wallxlength5 = new WallXLength();
        addObject(wallxlength5,960,160);
        WallXLength wallxlength6 = new WallXLength();
        addObject(wallxlength6,984,161);
        WallXLength wallxlength7 = new WallXLength();
        addObject(wallxlength7,1119,4);
        WallXLength wallxlength8 = new WallXLength();
        addObject(wallxlength8,1153,5);
        WallXLength wallxlength9 = new WallXLength();
        addObject(wallxlength9,1117,111);
        WallXLength wallxlength10 = new WallXLength();
        addObject(wallxlength10,1147,110);
        WallXLength wallxlength11 = new WallXLength();
        addObject(wallxlength11,1149,266);
        WallXLength wallxlength12 = new WallXLength();
        addObject(wallxlength12,1110,263);
        WallXLength wallxlength13 = new WallXLength();
        addObject(wallxlength13,1053,261);
        WallXLength wallxlength14 = new WallXLength();
        addObject(wallxlength14,1010,261);
        WallXLength wallxlength15 = new WallXLength();
        addObject(wallxlength15,939,259);
        WallXLength wallxlength16 = new WallXLength();
        addObject(wallxlength16,888,262);
        WallXLength wallxlength17 = new WallXLength();
        addObject(wallxlength17,842,265);
        WallXLength wallxlength18 = new WallXLength();
        addObject(wallxlength18,810,262);
        WallXLength wallxlength19 = new WallXLength();
        addObject(wallxlength19,758,265);
        WallXLength wallxlength20 = new WallXLength();
        addObject(wallxlength20,719,268);
        WallXLength wallxlength21 = new WallXLength();
        addObject(wallxlength21,664,266);
        WallXLength wallxlength22 = new WallXLength();
        addObject(wallxlength22,625,266);
        WallXLength wallxlength23 = new WallXLength();
        addObject(wallxlength23,614,264);
        WallXLength wallxlength24 = new WallXLength();
        addObject(wallxlength24,645,154);
        WallXLength wallxlength25 = new WallXLength();
        addObject(wallxlength25,588,157);
        WallXLength wallxlength26 = new WallXLength();
        addObject(wallxlength26,512,155);
        WallXLength wallxlength27 = new WallXLength();
        addObject(wallxlength27,467,147);
        WallXLength wallxlength28 = new WallXLength();
        addObject(wallxlength28,409,142);
        WallXLength wallxlength29 = new WallXLength();
        addObject(wallxlength29,365,144);
        WallXLength wallxlength30 = new WallXLength();
        addObject(wallxlength30,323,149);
        WallXLength wallxlength31 = new WallXLength();
        addObject(wallxlength31,292,151);
        WallXLength wallxlength32 = new WallXLength();
        addObject(wallxlength32,258,46);
        WallXLength wallxlength33 = new WallXLength();
        addObject(wallxlength33,330,47);
        WallXLength wallxlength34 = new WallXLength();
        addObject(wallxlength34,362,48);
        WallXLength wallxlength35 = new WallXLength();
        addObject(wallxlength35,407,50);
        WallXLength wallxlength36 = new WallXLength();
        addObject(wallxlength36,469,52);
        WallXLength wallxlength37 = new WallXLength();
        addObject(wallxlength37,501,50);
        WallXLength wallxlength38 = new WallXLength();
        addObject(wallxlength38,577,48);
        WallXLength wallxlength39 = new WallXLength();
        addObject(wallxlength39,624,49);
        WallXLength wallxlength40 = new WallXLength();
        addObject(wallxlength40,655,50);
        WallXLength wallxlength41 = new WallXLength();
        addObject(wallxlength41,242,264);
        WallXLength wallxlength42 = new WallXLength();
        addObject(wallxlength42,297,271);
        WallXLength wallxlength43 = new WallXLength();
        addObject(wallxlength43,336,260);
        WallXLength wallxlength44 = new WallXLength();
        addObject(wallxlength44,402,266);
        WallXLength wallxlength45 = new WallXLength();
        addObject(wallxlength45,442,266);
        WallXLength wallxlength46 = new WallXLength();
        addObject(wallxlength46,487,263);
        WallXLength wallxlength47 = new WallXLength();
        addObject(wallxlength47,719,380);
        WallXLength wallxlength48 = new WallXLength();
        addObject(wallxlength48,650,383);
        WallXLength wallxlength49 = new WallXLength();
        addObject(wallxlength49,576,379);
        WallXLength wallxlength50 = new WallXLength();
        addObject(wallxlength50,527,376);
        WallXLength wallxlength51 = new WallXLength();
        addObject(wallxlength51,487,374);
        WallXLength wallxlength52 = new WallXLength();
        addObject(wallxlength52,450,374);
        WallXLength wallxlength53 = new WallXLength();
        addObject(wallxlength53,412,374);
        WallXLength wallxlength54 = new WallXLength();
        addObject(wallxlength54,351,375);
        WallXLength wallxlength55 = new WallXLength();
        addObject(wallxlength55,303,377);
        WallXLength wallxlength56 = new WallXLength();
        addObject(wallxlength56,242,378);
        WallXLength wallxlength57 = new WallXLength();
        addObject(wallxlength57,209,380);
        WallXLength wallxlength58 = new WallXLength();
        addObject(wallxlength58,279,485);
        WallXLength wallxlength59 = new WallXLength();
        addObject(wallxlength59,321,488);
        WallXLength wallxlength60 = new WallXLength();
        addObject(wallxlength60,384,485);
        WallXLength wallxlength61 = new WallXLength();
        addObject(wallxlength61,435,485);
        WallXLength wallxlength62 = new WallXLength();
        addObject(wallxlength62,485,488);
        WallXLength wallxlength63 = new WallXLength();
        addObject(wallxlength63,554,488);
        WallXLength wallxlength64 = new WallXLength();
        addObject(wallxlength64,616,488);
        WallXLength wallxlength65 = new WallXLength();
        addObject(wallxlength65,675,486);
        WallXLength wallxlength66 = new WallXLength();
        addObject(wallxlength66,759,488);
        WallXLength wallxlength67 = new WallXLength();
        addObject(wallxlength67,809,490);
        WallXLength wallxlength68 = new WallXLength();
        addObject(wallxlength68,905,489);
        WallXLength wallxlength69 = new WallXLength();
        addObject(wallxlength69,957,488);
        WallXLength wallxlength70 = new WallXLength();
        addObject(wallxlength70,1012,486);
        WallXLength wallxlength71 = new WallXLength();
        addObject(wallxlength71,1092,483);
        WallXLength wallxlength72 = new WallXLength();
        addObject(wallxlength72,1134,482);
        WallXLength wallxlength73 = new WallXLength();
        addObject(wallxlength73,1138,377);
        WallXLength wallxlength74 = new WallXLength();
        addObject(wallxlength74,1100,375);
        WallXLength wallxlength75 = new WallXLength();
        addObject(wallxlength75,1044,375);
        WallXLength wallxlength76 = new WallXLength();
        addObject(wallxlength76,1006,373);
        WallXLength wallxlength77 = new WallXLength();
        addObject(wallxlength77,1126,597);
        WallXLength wallxlength78 = new WallXLength();
        addObject(wallxlength78,1049,595);
        WallXLength wallxlength79 = new WallXLength();
        addObject(wallxlength79,987,593);
        WallXLength wallxlength80 = new WallXLength();
        addObject(wallxlength80,903,595);
        WallXLength wallxlength81 = new WallXLength();
        addObject(wallxlength81,851,595);
        WallXLength wallxlength82 = new WallXLength();
        addObject(wallxlength82,793,594);
        WallXLength wallxlength83 = new WallXLength();
        addObject(wallxlength83,734,594);
        WallXLength wallxlength84 = new WallXLength();
        addObject(wallxlength84,658,589);
        WallXLength wallxlength85 = new WallXLength();
        addObject(wallxlength85,591,588);
        WallXLength wallxlength86 = new WallXLength();
        addObject(wallxlength86,525,592);
        WallXLength wallxlength87 = new WallXLength();
        addObject(wallxlength87,460,591);
        WallXLength wallxlength88 = new WallXLength();
        addObject(wallxlength88,408,591);
        WallXLength wallxlength89 = new WallXLength();
        addObject(wallxlength89,338,592);
        WallXLength wallxlength90 = new WallXLength();
        addObject(wallxlength90,285,598);
        WallXLength wallxlength91 = new WallXLength();
        addObject(wallxlength91,252,596);
        wallxlength77.setLocation(1125,570);
        wallxlength78.setLocation(1022,559);
        wallxlength77.setLocation(1155,595);
        wallxlength79.setLocation(1075,595);
        wallxlength80.setLocation(997,595);
        wallxlength81.setLocation(918,595);
        wallxlength81.setLocation(916,596);
        wallxlength82.setLocation(836,595);
        wallxlength83.setLocation(743,596);
        wallxlength83.setLocation(756,596);
        wallxlength84.setLocation(677,596);
        wallxlength85.setLocation(599,595);
        wallxlength86.setLocation(546,549);
        wallxlength87.setLocation(518,595);
        wallxlength88.setLocation(439,595);
        wallxlength89.setLocation(361,595);
        wallxlength90.setLocation(282,594);
        wallxlength91.setLocation(206,594);
        wallxlength59.setLocation(299,527);
        wallxlength60.setLocation(359,485);
        wallxlength61.setLocation(432,519);
        wallxlength62.setLocation(438,485);
        wallxlength63.setLocation(515,486);
        wallxlength64.setLocation(594,485);
        wallxlength66.setLocation(748,526);
        wallxlength65.setLocation(672,485);
        wallxlength67.setLocation(751,485);
        wallxlength66.setLocation(826,486);
        wallxlength69.setLocation(961,525);
        wallxlength68.setLocation(905,487);
        wallxlength70.setLocation(986,486);
        wallxlength71.setLocation(1066,487);
        wallxlength72.setLocation(1131,485);
        wallxlength75.setLocation(1038,434);
        wallxlength74.setLocation(1117,438);
        wallxlength76.setLocation(1019,376);
        wallxlength73.setLocation(1098,376);
        wallxlength75.setLocation(1171,377);
        wallxlength47.setLocation(700,376);
        wallxlength49.setLocation(549,414);
        wallxlength48.setLocation(626,376);
        wallxlength50.setLocation(549,377);
        wallxlength52.setLocation(413,450);
        wallxlength51.setLocation(472,376);
        wallxlength53.setLocation(366,416);
        wallxlength54.setLocation(393,375);
        wallxlength55.setLocation(318,375);
        wallxlength56.setLocation(241,360);
        wallxlength57.setLocation(241,376);
        wallxlength46.setLocation(546,318);
        wallxlength45.setLocation(457,265);
        wallxlength44.setLocation(406,222);
        wallxlength43.setLocation(378,266);
        wallxlength42.setLocation(297,212);
        wallxlength41.setLocation(298,264);
        wallxlength42.setLocation(219,264);
        removeObject(wallxlength56);
        removeObject(wallxlength59);
        removeObject(wallxlength61);
        removeObject(wallxlength86);
        removeObject(wallxlength69);
        removeObject(wallxlength78);
        removeObject(wallxlength74);
        removeObject(wallxlength49);
        removeObject(wallxlength52);
        removeObject(wallxlength53);
        removeObject(wallxlength46);
        wallxlength23.setLocation(580,304);
        wallxlength22.setLocation(640,329);
        wallxlength21.setLocation(625,267);
        wallxlength20.setLocation(702,267);
        wallxlength19.setLocation(815,331);
        wallxlength18.setLocation(782,267);
        wallxlength17.setLocation(860,312);
        wallxlength16.setLocation(861,266);
        wallxlength15.setLocation(966,310);
        wallxlength14.setLocation(940,265);
        wallxlength13.setLocation(1018,263);
        wallxlength12.setLocation(1095,262);
        wallxlength13.setLocation(1018,265);
        wallxlength12.setLocation(1093,300);
        wallxlength11.setLocation(1129,266);
        wallxlength12.setLocation(1073,268);
        wallxlength10.setLocation(1154,166);
        wallxlength9.setLocation(1132,113);
        removeObject(wallxlength15);
        removeObject(wallxlength17);
        removeObject(wallxlength19);
        removeObject(wallxlength22);
        removeObject(wallxlength23);
        removeObject(wallxlength44);
        wallxlength8.setLocation(1157,59);
        wallxlength7.setLocation(1101,5);
        wallxlength8.setLocation(1181,5);
        removeObject(wallxlength10);
        wallxlength6.setLocation(946,186);
        wallxlength5.setLocation(965,157);
        wallxlength4.setLocation(903,130);
        wallxlength3.setLocation(890,156);
        wallxlength.setLocation(799,119);
        wallxlength2.setLocation(803,156);
        wallxlength3.setLocation(884,157);
        removeObject(wallxlength4);
        removeObject(wallxlength6);
        removeObject(wallxlength);
        wallxlength24.setLocation(705,163);
        wallxlength25.setLocation(635,156);
        wallxlength24.setLocation(672,219);
        wallxlength26.setLocation(555,156);
        wallxlength27.setLocation(495,109);
        wallxlength28.setLocation(475,156);
        wallxlength29.setLocation(396,156);
        wallxlength30.setLocation(333,110);
        wallxlength31.setLocation(283,157);
        wallxlength30.setLocation(336,157);
        wallxlength32.setLocation(231,46);
        wallxlength33.setLocation(309,45);
        wallxlength34.setLocation(359,91);
        wallxlength35.setLocation(387,46);
        wallxlength36.setLocation(460,47);
        wallxlength37.setLocation(501,89);
        wallxlength36.setLocation(461,45);
        wallxlength36.setLocation(463,46);
        wallxlength38.setLocation(543,46);
        wallxlength39.setLocation(617,105);
        wallxlength40.setLocation(623,46);
        wallxlength39.setLocation(692,46);
        removeObject(wallxlength27);
        removeObject(wallxlength37);
        removeObject(wallxlength34);
        removeObject(wallxlength24);
        WallYLength wallylength = new WallYLength();
        addObject(wallylength,1168,215);
        WallYLength wallylength2 = new WallYLength();
        addObject(wallylength2,1163,147);
        WallYLength wallylength3 = new WallYLength();
        addObject(wallylength3,1107,157);
        WallYLength wallylength4 = new WallYLength();
        addObject(wallylength4,1005,114);
        WallYLength wallylength5 = new WallYLength();
        addObject(wallylength5,1266,94);
        WallYLength wallylength6 = new WallYLength();
        addObject(wallylength6,1272,166);
        WallYLength wallylength7 = new WallYLength();
        addObject(wallylength7,1264,242);
        wallylength3.setLocation(1100,155);
        wallylength2.setLocation(1164,152);
        wallylength5.setLocation(1262,101);
        wallylength6.setLocation(1262,171);
        wallylength7.setLocation(1262,250);
        wallylength.setLocation(1164,222);
        WallYLength wallylength8 = new WallYLength();
        addObject(wallylength8,1266,478);
        wallylength8.setLocation(1263,485);
        WallYLength wallylength9 = new WallYLength();
        addObject(wallylength9,883,370);
        WallYLength wallylength10 = new WallYLength();
        addObject(wallylength10,847,368);
        wallylength9.setLocation(885,374);
        wallylength10.setLocation(836,371);
        WallYLength wallylength11 = new WallYLength();
        addObject(wallylength11,148,155);
        WallYLength wallylength12 = new WallYLength();
        addObject(wallylength12,147,499);
        wallylength11.setLocation(148,151);
        wallylength11.setLocation(146,152);
        wallylength12.setLocation(146,495);
        WallRotated wallrotated = new WallRotated();
        addObject(wallrotated,832,292);
        wallrotated.setLocation(792,282);
        WallRotated wallrotated2 = new WallRotated();
        addObject(wallrotated2,837,302);
        wallrotated2.setLocation(815,302);
        WallRotated wallrotated3 = new WallRotated();
        addObject(wallrotated3,859,330);
        wallrotated3.setLocation(834,322);
        wallylength10.setLocation(834,371);
        WallRotated wallrotated4 = new WallRotated();
        addObject(wallrotated4,1237,31);
        wallrotated4.setLocation(1214,16);
        WallRotated wallrotated5 = new WallRotated();
        addObject(wallrotated5,1239,31);
        wallrotated5.setLocation(1237,32);
        WallRotated wallrotated6 = new WallRotated();
        addObject(wallrotated6,1252,50);
        wallrotated6.setLocation(1254,51);
        WallRotated wallrotated7 = new WallRotated();
        addObject(wallrotated7,1264,53);
        wallrotated7.setLocation(1264,67);
        WallRotated wallrotated8 = new WallRotated();
        addObject(wallrotated8,1233,387);
        WallRotated wallrotated9 = new WallRotated();
        addObject(wallrotated9,1248,404);
        WallRotated wallrotated10 = new WallRotated();
        addObject(wallrotated10,1259,421);
        wallrotated8.setLocation(1214,384);
        wallrotated9.setLocation(1241,403);
        wallrotated10.setLocation(1258,422);
        WallRotated wallrotated11 = new WallRotated();
        addObject(wallrotated11,1238,443);
        wallrotated11.setLocation(1267,439);
        WallRotated wallrotated12 = new WallRotated();
        addObject(wallrotated12,731,50);
        WallRotated wallrotated13 = new WallRotated();
        addObject(wallrotated13,750,78);
        WallRotated wallrotated14 = new WallRotated();
        addObject(wallrotated14,770,97);
        WallRotated wallrotated15 = new WallRotated();
        addObject(wallrotated15,785,120);
        wallrotated12.setLocation(713,56);
        wallrotated13.setLocation(744,77);
        wallrotated14.setLocation(764,97);
        wallrotated15.setLocation(775,118);
        WallYLength wallylength13 = new WallYLength();
        addObject(wallylength13,783,177);
        wallylength13.setLocation(766,118);
        WallRotated wallrotated16 = new WallRotated();
        addObject(wallrotated16,544,161);
        WallRotated wallrotated17 = new WallRotated();
        addObject(wallrotated17,571,176);
        WallRotated wallrotated18 = new WallRotated();
        addObject(wallrotated18,590,196);
        WallRotated wallrotated19 = new WallRotated();
        addObject(wallrotated19,604,218);
        wallrotated16.setLocation(534,164);
        wallrotated17.setLocation(557,179);
        wallrotated18.setLocation(580,202);
        wallrotated19.setLocation(592,223);
        WallYLength wallylength14 = new WallYLength();
        addObject(wallylength14,692,208);
        wallylength14.setLocation(588,224);
        WallRotated wallrotated20 = new WallRotated();
        addObject(wallrotated20,475,286);
        WallRotated wallrotated21 = new WallRotated();
        addObject(wallrotated21,491,317);
        WallRotated wallrotated22 = new WallRotated();
        addObject(wallrotated22,496,334);
        wallrotated20.setLocation(471,284);
        wallrotated22.setLocation(538,363);
        wallrotated21.setLocation(515,344);
        wallrotated20.setLocation(497,323);
        WallYLength wallylength15 = new WallYLength();
        addObject(wallylength15,484,298);
        wallylength15.setLocation(492,305);
        WallRotated wallrotated23 = new WallRotated();
        addObject(wallrotated23,189,579);
        WallRotated wallrotated24 = new WallRotated();
        addObject(wallrotated24,171,567);
        WallRotated wallrotated25 = new WallRotated();
        addObject(wallrotated25,164,554);
        wallrotated23.setLocation(198,583);
        wallrotated24.setLocation(176,569);
        wallrotated25.setLocation(158,552);
        WallRotated wallrotated26 = new WallRotated();
        addObject(wallrotated26,141,534);
        wallrotated26.setLocation(143,532);
        WallRotated wallrotated27 = new WallRotated();
        addObject(wallrotated27,202,259);
        WallRotated wallrotated28 = new WallRotated();
        addObject(wallrotated28,176,251);
        WallRotated wallrotated29 = new WallRotated();
        addObject(wallrotated29,161,229);
        wallrotated27.setLocation(205,259);
        wallrotated28.setLocation(183,249);
        wallrotated29.setLocation(166,233);
        WallRotated wallrotated30 = new WallRotated();
        addObject(wallrotated30,140,202);
        wallrotated30.setLocation(150,218);
        WallRotated wallrotated31 = new WallRotated();
        addObject(wallrotated31,146,200);
        wallrotated31.setLocation(142,202);
        WallRotated wallrotated32 = new WallRotated();
        addObject(wallrotated32,723,248);
        WallRotated wallrotated33 = new WallRotated();
        addObject(wallrotated33,694,237);
        WallRotated wallrotated34 = new WallRotated();
        addObject(wallrotated34,659,195);
        wallrotated32.setLocation(728,256);
        wallrotated33.setLocation(701,241);
        wallrotated34.setLocation(684,225);
        WallRotated wallrotated35 = new WallRotated();
        addObject(wallrotated35,655,189);
        wallrotated35.setLocation(671,210);
        wallrotated18.setLocation(578,203);
        wallrotated19.setLocation(595,221);
        wallrotated16.setLocation(535,166);
        wallrotated17.setLocation(556,181);
        wallrotated18.setLocation(576,198);
        wallrotated19.setLocation(594,216);
        wallrotated19.setLocation(593,221);
        wallylength14.setLocation(587,223);
        wallrotated18.setLocation(577,201);
        wallrotated33.setLocation(702,242);
        wallrotated34.setLocation(687,225);
        wallrotated35.setLocation(673,208);
        WallYLength wallylength16 = new WallYLength();
        addObject(wallylength16,664,182);
        wallylength16.setLocation(671,196);
        WallRotated wallrotated36 = new WallRotated();
        addObject(wallrotated36,943,478);
        WallRotated wallrotated37 = new WallRotated();
        addObject(wallrotated37,915,470);
        WallRotated wallrotated38 = new WallRotated();
        addObject(wallrotated38,885,443);
        wallrotated37.setLocation(923,465);
        wallrotated38.setLocation(904,446);
        WallRotated wallrotated39 = new WallRotated();
        addObject(wallrotated39,868,418);
        wallrotated39.setLocation(884,425);
        WallRotatedOther wallrotatedother = new WallRotatedOther();
        addObject(wallrotatedother,932,302);
        wallrotatedother.setLocation(1055,20);
        wallrotatedother.setLocation(1061,14);
        WallRotatedOther wallrotatedother2 = new WallRotatedOther();
        addObject(wallrotatedother2,1032,26);
        WallRotatedOther wallrotatedother3 = new WallRotatedOther();
        addObject(wallrotatedother3,1014,38);
        wallrotatedother2.setLocation(1038,29);
        wallrotatedother3.setLocation(1016,45);
        WallRotatedOther wallrotatedother4 = new WallRotatedOther();
        addObject(wallrotatedother4,1003,59);
        wallrotatedother4.setLocation(1004,65);
        wallrotatedother3.setLocation(1020,46);
        WallRotatedOther wallrotatedother5 = new WallRotatedOther();
        addObject(wallrotatedother5,180,51);
        WallRotatedOther wallrotatedother6 = new WallRotatedOther();
        addObject(wallrotatedother6,164,73);
        WallRotatedOther wallrotatedother7 = new WallRotatedOther();
        addObject(wallrotatedother7,140,88);
        wallrotatedother5.setLocation(208,52);
        wallrotatedother5.setLocation(212,50);
        wallrotatedother6.setLocation(183,67);
        wallrotatedother7.setLocation(159,87);
        WallRotatedOther wallrotatedother8 = new WallRotatedOther();
        addObject(wallrotatedother8,141,102);
        wallrotatedother8.setLocation(146,106);
        wallrotatedother7.setLocation(165,86);
        WallRotatedOther wallrotatedother9 = new WallRotatedOther();
        addObject(wallrotatedother9,1091,241);
        wallrotatedother9.setLocation(1108,192);
        WallRotatedOther wallrotatedother10 = new WallRotatedOther();
        addObject(wallrotatedother10,1094,223);
        wallrotatedother10.setLocation(1098,210);
        WallRotatedOther wallrotatedother11 = new WallRotatedOther();
        addObject(wallrotatedother11,1080,244);
        wallrotatedother11.setLocation(1081,229);
        WallRotatedOther wallrotatedother12 = new WallRotatedOther();
        addObject(wallrotatedother12,1076,250);
        wallrotatedother12.setLocation(1065,245);
        WallRotatedOther wallrotatedother13 = new WallRotatedOther();
        addObject(wallrotatedother13,1026,289);
        wallrotatedother13.setLocation(1043,259);
        WallRotatedOther wallrotatedother14 = new WallRotatedOther();
        addObject(wallrotatedother14,1135,293);
        wallrotatedother14.setLocation(1205,369);
        WallRotatedOther wallrotatedother15 = new WallRotatedOther();
        addObject(wallrotatedother15,1140,357);
        wallrotatedother15.setLocation(1229,355);
        WallRotatedOther wallrotatedother16 = new WallRotatedOther();
        addObject(wallrotatedother16,1189,339);
        wallrotatedother16.setLocation(1247,338);
        WallRotatedOther wallrotatedother17 = new WallRotatedOther();
        addObject(wallrotatedother17,1193,322);
        wallrotatedother17.setLocation(1259,322);
        WallXLength wallxlength92 = new WallXLength();
        addObject(wallxlength92,1178,278);
        removeObject(wallxlength92);
        WallYLength wallylength17 = new WallYLength();
        addObject(wallylength17,1264,296);
        wallylength17.setLocation(1260,305);
        WallRotatedOther wallrotatedother18 = new WallRotatedOther();
        addObject(wallrotatedother18,916,289);
        wallrotatedother18.setLocation(944,275);
        WallRotatedOther wallrotatedother19 = new WallRotatedOther();
        addObject(wallrotatedother19,871,290);
        wallrotatedother19.setLocation(919,290);
        WallRotatedOther wallrotatedother20 = new WallRotatedOther();
        addObject(wallrotatedother20,884,293);
        wallrotatedother20.setLocation(900,309);
        WallRotatedOther wallrotatedother21 = new WallRotatedOther();
        addObject(wallrotatedother21,870,325);
        wallrotatedother21.setLocation(883,328);
        WallRotatedOther wallrotatedother22 = new WallRotatedOther();
        addObject(wallrotatedother22,736,428);
        wallrotatedother22.setLocation(777,476);
        WallRotatedOther wallrotatedother23 = new WallRotatedOther();
        addObject(wallrotatedother23,748,436);
        wallrotatedother23.setLocation(799,466);
        WallRotatedOther wallrotatedother24 = new WallRotatedOther();
        addObject(wallrotatedother24,764,425);
        wallrotatedother24.setLocation(816,450);
        WallRotatedOther wallrotatedother25 = new WallRotatedOther();
        addObject(wallrotatedother25,775,412);
        wallrotatedother25.setLocation(829,434);
        WallRotatedOther wallrotatedother26 = new WallRotatedOther();
        addObject(wallrotatedother26,788,414);
        wallrotatedother26.setLocation(839,420);
        WallRotatedOther wallrotatedother27 = new WallRotatedOther();
        addObject(wallrotatedother27,764,525);
        wallrotatedother27.setLocation(1204,588);
        WallRotatedOther wallrotatedother28 = new WallRotatedOther();
        addObject(wallrotatedother28,1130,556);
        wallrotatedother28.setLocation(1228,578);
        WallRotatedOther wallrotatedother29 = new WallRotatedOther();
        addObject(wallrotatedother29,1165,542);
        wallrotatedother29.setLocation(1247,562);
        WallRotatedOther wallrotatedother30 = new WallRotatedOther();
        addObject(wallrotatedother30,1138,526);
        wallrotatedother30.setLocation(1259,546);
        WallRotatedOther wallrotatedother31 = new WallRotatedOther();
        addObject(wallrotatedother31,1141,522);
        wallrotatedother31.setLocation(1269,531);
        WallRotatedOther wallrotatedother32 = new WallRotatedOther();
        addObject(wallrotatedother32,174,389);
        wallrotatedother32.setLocation(208,381);
        WallRotatedOther wallrotatedother33 = new WallRotatedOther();
        addObject(wallrotatedother33,137,386);
        wallrotatedother33.setLocation(186,391);
        WallRotatedOther wallrotatedother34 = new WallRotatedOther();
        addObject(wallrotatedother34,189,386);
        wallrotatedother34.setLocation(173,404);
        WallRotatedOther wallrotatedother35 = new WallRotatedOther();
        addObject(wallrotatedother35,157,393);
        wallrotatedother35.setLocation(155,423);
        WallRotatedOther wallrotatedother36 = new WallRotatedOther();
        addObject(wallrotatedother36,154,411);
        wallrotatedother36.setLocation(145,438);
        WallYLength wallylength18 = new WallYLength();
        addObject(wallylength18,145,443);
        wallylength18.setLocation(147,469);
        wallrotated25.setLocation(140,554);
        wallrotated26.setLocation(127,534);
        wallrotated24.setLocation(158,572);
        wallrotated23.setLocation(185,586);
        wallrotatedother36.setLocation(111,440);
        wallrotatedother35.setLocation(123,421);
        wallrotatedother34.setLocation(135,403);
        wallrotatedother33.setLocation(152,388);
        wallrotatedother32.setLocation(171,379);
        wallrotatedother22.setLocation(799,476);
        wallrotatedother23.setLocation(831,467);
        wallrotatedother24.setLocation(844,449);
        wallrotatedother25.setLocation(856,434);
        wallrotatedother26.setLocation(866,420);
        wallrotated22.setLocation(515,363);
        wallrotated21.setLocation(483,344);
        wallrotated20.setLocation(469,323);
        wallrotated19.setLocation(618,221);
        wallrotated18.setLocation(601,201);
        wallrotated17.setLocation(576,180);
        wallrotated16.setLocation(551,166);
        wallrotated16.setLocation(572,166);
        wallrotated17.setLocation(586,181);
        wallrotated28.setLocation(165,250);
        wallrotated27.setLocation(180,260);
        wallrotated28.setLocation(159,250);
        wallrotated29.setLocation(148,232);
        wallrotated30.setLocation(126,217);
        wallrotated31.setLocation(114,199);
        wallrotatedother8.setLocation(121,103);
        wallrotatedother7.setLocation(130,83);
        wallrotatedother6.setLocation(145,65);
        wallrotatedother5.setLocation(171,49);
        wallrotated12.setLocation(740,56);
        wallrotated13.setLocation(772,77);
        wallrotated14.setLocation(788,96);
        wallrotated15.setLocation(799,118);
        wallrotated35.setLocation(639,207);
        wallrotated34.setLocation(648,224);
        wallrotated33.setLocation(663,242);
        wallrotated32.setLocation(689,256);
        wallrotatedother4.setLocation(974,65);
        wallrotatedother3.setLocation(985,47);
        wallrotatedother2.setLocation(1000,27);
        wallrotatedother.setLocation(1025,14);
        wallrotated4.setLocation(1246,16);
        wallrotated5.setLocation(1266,31);
        wallrotated6.setLocation(1254,51);
        wallrotated6.setLocation(1254,51);
        wallrotated6.setLocation(1254,51);
        wallrotated6.setLocation(1254,51);
        wallrotated6.setLocation(1254,51);
        wallrotated6.setLocation(1254,51);
        wallrotated6.setLocation(1274,50);
        wallrotated6.setLocation(1274,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,50);
        wallrotated6.setLocation(1278,47);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1276,67);
        wallrotated7.setLocation(1277,52);
        wallrotated7.setLocation(1279,49);
        wallrotated6.setLocation(1279,38);
        wallrotatedother17.setLocation(1259,322);
        wallrotatedother17.setLocation(1259,322);
        wallrotatedother17.setLocation(1259,322);
        wallrotatedother17.setLocation(1259,322);
        wallrotatedother14.setLocation(1236,368);
        wallrotatedother15.setLocation(1252,355);
        wallrotatedother16.setLocation(1270,339);
        wallrotatedother16.setLocation(1273,339);
        wallrotatedother17.setLocation(1276,322);
        wallrotatedother17.setLocation(1276,322);
        wallrotatedother17.setLocation(1276,322);
        wallrotatedother17.setLocation(1278,329);
        wallrotatedother17.setLocation(1278,329);
        wallrotatedother16.setLocation(1273,347);
        wallrotatedother17.setLocation(1278,334);
        wallrotatedother18.setLocation(911,275);
        wallrotatedother19.setLocation(891,290);
        wallrotatedother20.setLocation(868,308);
        wallrotatedother21.setLocation(856,328);
        wallrotated.setLocation(819,282);
        wallrotated2.setLocation(845,302);
        wallrotated3.setLocation(865,322);
        wallrotatedother21.setLocation(861,328);
        wallrotatedother21.setLocation(864,328);
        wallrotatedother21.setLocation(864,319);
        wallrotated3.setLocation(865,319);
        wallrotated39.setLocation(858,425);
        wallrotated38.setLocation(873,446);
        wallrotated37.setLocation(880,465);
        wallrotated36.setLocation(905,477);
        wallrotated8.setLocation(1246,384);
        wallrotated9.setLocation(1272,403);
        wallrotated10.setLocation(1272,422);
        wallrotated10.setLocation(1272,422);
        wallrotated10.setLocation(1272,422);
        wallrotated10.setLocation(1272,422);
        wallrotated10.setLocation(1273,405);
        wallrotated11.setLocation(1273,433);
        wallrotated11.setLocation(1275,406);
        wallylength8.setLocation(1264,470);
        wallylength8.setLocation(1264,471);
        wallrotatedother31.setLocation(1269,531);
        wallrotatedother31.setLocation(1269,531);
        wallrotatedother31.setLocation(1269,531);
        wallrotatedother31.setLocation(1269,531);
        wallrotatedother27.setLocation(1237,589);
        wallrotatedother28.setLocation(1267,578);
        wallrotatedother29.setLocation(1279,564);
        wallrotatedother30.setLocation(1259,546);
        wallrotatedother30.setLocation(1259,546);
        wallrotatedother30.setLocation(1259,546);
        wallrotatedother30.setLocation(1270,546);
        wallrotatedother30.setLocation(1273,546);
        wallrotatedother30.setLocation(1276,546);
        wallrotatedother30.setLocation(1276,546);
        wallrotatedother30.setLocation(1276,555);
        wallrotatedother31.setLocation(1275,531);
        wallrotatedother31.setLocation(1275,531);
        wallrotatedother31.setLocation(1275,539);
        wallrotatedother31.setLocation(1276,545);
        wallrotatedother31.setLocation(1278,549);
        WallYLength wallylength19 = new WallYLength();
        addObject(wallylength19,1129,436);
        wallylength8.setLocation(1264,452);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1129,436);
        wallylength19.setLocation(1263,523);
        wallrotatedother13.setLocation(1079,258);
        wallrotatedother12.setLocation(1098,244);
        wallrotatedother11.setLocation(1109,229);
        wallrotatedother10.setLocation(1124,209);
        wallrotatedother9.setLocation(1126,198);
        wallrotatedother9.setLocation(1122,206);
        wallrotatedother10.setLocation(1122,209);
    }
    
    /**
     * Create the pheromones for the AI to follow.
     */
    public void createPheromones()
    {
        PheromoneTrail pheromonetrail = new PheromoneTrail();
        addObject(pheromonetrail,909,540);
        trailList.add(pheromonetrail);

        PheromoneTrail pheromonetrail2 = new PheromoneTrail();
        addObject(pheromonetrail2,957,541);
        trailList.add(pheromonetrail2);

        PheromoneTrail pheromonetrail3 = new PheromoneTrail();
        addObject(pheromonetrail3,1000,549);
        trailList.add(pheromonetrail3);

        PheromoneTrail pheromonetrail4 = new PheromoneTrail();
        addObject(pheromonetrail4,1090,553);
        trailList.add(pheromonetrail4);

        PheromoneTrail pheromonetrail5 = new PheromoneTrail();
        addObject(pheromonetrail5,1138,552);
        trailList.add(pheromonetrail5);

        PheromoneTrail pheromonetrail6 = new PheromoneTrail();
        addObject(pheromonetrail6,1190,546);
        trailList.add(pheromonetrail6);

        PheromoneTrail pheromonetrail7 = new PheromoneTrail();
        addObject(pheromonetrail7,1221,498);
        trailList.add(pheromonetrail7);

        PheromoneTrail pheromonetrail8 = new PheromoneTrail();
        addObject(pheromonetrail8,1196,435);
        trailList.add(pheromonetrail8);

        PheromoneTrail pheromonetrail9 = new PheromoneTrail();
        addObject(pheromonetrail9,1150,435);
        trailList.add(pheromonetrail9);

        PheromoneTrail pheromonetrail10 = new PheromoneTrail();
        addObject(pheromonetrail10,1095,445);
        trailList.add(pheromonetrail10);

        PheromoneTrail pheromonetrail11 = new PheromoneTrail();
        addObject(pheromonetrail11,1025,443);
        trailList.add(pheromonetrail11);

        PheromoneTrail pheromonetrail12 = new PheromoneTrail();
        addObject(pheromonetrail12,969,438);
        trailList.add(pheromonetrail12);

        PheromoneTrail pheromonetrail13 = new PheromoneTrail();
        addObject(pheromonetrail13,930,392);
        trailList.add(pheromonetrail13);

        PheromoneTrail pheromonetrail14 = new PheromoneTrail();
        addObject(pheromonetrail14,968,323);
        trailList.add(pheromonetrail14);

        PheromoneTrail pheromonetrail15 = new PheromoneTrail();
        addObject(pheromonetrail15,1005,326);
        trailList.add(pheromonetrail15);

        PheromoneTrail pheromonetrail16 = new PheromoneTrail();
        addObject(pheromonetrail16,1053,330);
        trailList.add(pheromonetrail16);

        PheromoneTrail pheromonetrail17 = new PheromoneTrail();
        addObject(pheromonetrail17,1111,319);
        trailList.add(pheromonetrail17);

        PheromoneTrail pheromonetrail18 = new PheromoneTrail();
        addObject(pheromonetrail18,1163,321);
        trailList.add(pheromonetrail18);

        PheromoneTrail pheromonetrail19 = new PheromoneTrail();
        addObject(pheromonetrail19,1200,286);
        trailList.add(pheromonetrail19);

        PheromoneTrail pheromonetrail20 = new PheromoneTrail();
        addObject(pheromonetrail20,1210,229);
        trailList.add(pheromonetrail20);

        PheromoneTrail pheromonetrail21 = new PheromoneTrail();
        addObject(pheromonetrail21,1210,179);
        trailList.add(pheromonetrail21);

        PheromoneTrail pheromonetrail22 = new PheromoneTrail();
        addObject(pheromonetrail22,1210,126);
        trailList.add(pheromonetrail22);

        PheromoneTrail pheromonetrail23 = new PheromoneTrail();
        addObject(pheromonetrail23,1196,75);
        trailList.add(pheromonetrail23);

        PheromoneTrail pheromonetrail24 = new PheromoneTrail();
        addObject(pheromonetrail24,1157,47);
        trailList.add(pheromonetrail24);

        PheromoneTrail pheromonetrail25 = new PheromoneTrail();
        addObject(pheromonetrail25,1114,39);
        trailList.add(pheromonetrail25);

        PheromoneTrail pheromonetrail26 = new PheromoneTrail();
        addObject(pheromonetrail26,1088,66);
        trailList.add(pheromonetrail26);

        PheromoneTrail pheromonetrail27 = new PheromoneTrail();
        addObject(pheromonetrail27,1071,96);
        trailList.add(pheromonetrail27);

        PheromoneTrail pheromonetrail28 = new PheromoneTrail();
        addObject(pheromonetrail28,1030,185);
        trailList.add(pheromonetrail28);

        PheromoneTrail pheromonetrail29 = new PheromoneTrail();
        addObject(pheromonetrail29,990,211);
        trailList.add(pheromonetrail29);

        PheromoneTrail pheromonetrail30 = new PheromoneTrail();
        addObject(pheromonetrail30,949,231);
        trailList.add(pheromonetrail30);

        PheromoneTrail pheromonetrail31 = new PheromoneTrail();
        addObject(pheromonetrail31,868,225);
        trailList.add(pheromonetrail31);

        PheromoneTrail pheromonetrail32 = new PheromoneTrail();
        addObject(pheromonetrail32,810,228);
        trailList.add(pheromonetrail32);

        PheromoneTrail pheromonetrail33 = new PheromoneTrail();
        addObject(pheromonetrail33,762,224);
        trailList.add(pheromonetrail33);

        PheromoneTrail pheromonetrail34 = new PheromoneTrail();
        addObject(pheromonetrail34,729,168);
        trailList.add(pheromonetrail34);

        PheromoneTrail pheromonetrail35 = new PheromoneTrail();
        addObject(pheromonetrail35,685,122);
        trailList.add(pheromonetrail35);

        PheromoneTrail pheromonetrail36 = new PheromoneTrail();
        addObject(pheromonetrail36,644,113);
        trailList.add(pheromonetrail36);

        PheromoneTrail pheromonetrail37 = new PheromoneTrail();
        addObject(pheromonetrail37,590,106);
        trailList.add(pheromonetrail37);

        PheromoneTrail pheromonetrail38 = new PheromoneTrail();
        addObject(pheromonetrail38,536,108);
        trailList.add(pheromonetrail38);

        PheromoneTrail pheromonetrail39 = new PheromoneTrail();
        addObject(pheromonetrail39,478,110);
        trailList.add(pheromonetrail39);

        PheromoneTrail pheromonetrail40 = new PheromoneTrail();
        addObject(pheromonetrail40,428,105);
        trailList.add(pheromonetrail40);

        PheromoneTrail pheromonetrail41 = new PheromoneTrail();
        addObject(pheromonetrail41,378,107);
        trailList.add(pheromonetrail41);

        PheromoneTrail pheromonetrail42 = new PheromoneTrail();
        addObject(pheromonetrail42,329,102);
        trailList.add(pheromonetrail42);

        PheromoneTrail pheromonetrail43 = new PheromoneTrail();
        addObject(pheromonetrail43,271,105);
        trailList.add(pheromonetrail43);

        PheromoneTrail pheromonetrail44 = new PheromoneTrail();
        addObject(pheromonetrail44,220,109);
        trailList.add(pheromonetrail44);

        PheromoneTrail pheromonetrail45 = new PheromoneTrail();
        addObject(pheromonetrail45,204,159);
        trailList.add(pheromonetrail45);

        PheromoneTrail pheromonetrail46 = new PheromoneTrail();
        addObject(pheromonetrail46,220,201);
        trailList.add(pheromonetrail46);

        PheromoneTrail pheromonetrail47 = new PheromoneTrail();
        addObject(pheromonetrail47,265,215);
        trailList.add(pheromonetrail47);

        PheromoneTrail pheromonetrail48 = new PheromoneTrail();
        addObject(pheromonetrail48,317,226);
        trailList.add(pheromonetrail48);

        PheromoneTrail pheromonetrail49 = new PheromoneTrail();
        addObject(pheromonetrail49,374,204);
        trailList.add(pheromonetrail49);

        PheromoneTrail pheromonetrail50 = new PheromoneTrail();
        addObject(pheromonetrail50,425,193);
        trailList.add(pheromonetrail50);

        PheromoneTrail pheromonetrail51 = new PheromoneTrail();
        addObject(pheromonetrail51,481,219);
        trailList.add(pheromonetrail51);

        PheromoneTrail pheromonetrail52 = new PheromoneTrail();
        addObject(pheromonetrail52,523,258);
        trailList.add(pheromonetrail52);

        PheromoneTrail pheromonetrail53 = new PheromoneTrail();
        addObject(pheromonetrail53,562,294);
        trailList.add(pheromonetrail53);

        PheromoneTrail pheromonetrail54 = new PheromoneTrail();
        addObject(pheromonetrail54,605,321);
        trailList.add(pheromonetrail54);

        PheromoneTrail pheromonetrail55 = new PheromoneTrail();
        addObject(pheromonetrail55,648,314);
        trailList.add(pheromonetrail55);

        PheromoneTrail pheromonetrail56 = new PheromoneTrail();
        addObject(pheromonetrail56,703,300);
        trailList.add(pheromonetrail56);

        PheromoneTrail pheromonetrail57 = new PheromoneTrail();
        addObject(pheromonetrail57,758,348);
        trailList.add(pheromonetrail57);

        PheromoneTrail pheromonetrail58 = new PheromoneTrail();
        addObject(pheromonetrail58,781,385);
        trailList.add(pheromonetrail58);

        PheromoneTrail pheromonetrail59 = new PheromoneTrail();
        addObject(pheromonetrail59,769,419);
        trailList.add(pheromonetrail59);

        PheromoneTrail pheromonetrail60 = new PheromoneTrail();
        addObject(pheromonetrail60,718,445);
        trailList.add(pheromonetrail60);

        PheromoneTrail pheromonetrail61 = new PheromoneTrail();
        addObject(pheromonetrail61,670,411);
        trailList.add(pheromonetrail61);

        PheromoneTrail pheromonetrail62 = new PheromoneTrail();
        addObject(pheromonetrail62,629,426);
        trailList.add(pheromonetrail62);

        PheromoneTrail pheromonetrail63 = new PheromoneTrail();
        addObject(pheromonetrail63,574,420);
        trailList.add(pheromonetrail63);

        pheromonetrail61.setLocation(664,425);
        pheromonetrail63.setLocation(566,421);

        PheromoneTrail pheromonetrail64 = new PheromoneTrail();
        addObject(pheromonetrail64,507,421);
        trailList.add(pheromonetrail63);

        PheromoneTrail pheromonetrail65 = new PheromoneTrail();
        addObject(pheromonetrail65,463,421);
        trailList.add(pheromonetrail65);

        PheromoneTrail pheromonetrail66 = new PheromoneTrail();
        addObject(pheromonetrail66,417,422);
        trailList.add(pheromonetrail66);

        PheromoneTrail pheromonetrail67 = new PheromoneTrail();
        addObject(pheromonetrail67,382,424);
        trailList.add(pheromonetrail67);

        PheromoneTrail pheromonetrail68 = new PheromoneTrail();
        addObject(pheromonetrail68,328,429);
        trailList.add(pheromonetrail68);

        PheromoneTrail pheromonetrail69 = new PheromoneTrail();
        addObject(pheromonetrail69,283,433);
        trailList.add(pheromonetrail69);

        PheromoneTrail pheromonetrail70 = new PheromoneTrail();
        addObject(pheromonetrail70,250,432);
        trailList.add(pheromonetrail70);

        PheromoneTrail pheromonetrail71 = new PheromoneTrail();
        addObject(pheromonetrail71,205,498);
        trailList.add(pheromonetrail71);

        pheromonetrail70.setLocation(215,447);
        pheromonetrail71.setLocation(199,494);

        PheromoneTrail pheromonetrail72 = new PheromoneTrail();
        addObject(pheromonetrail72,223,537);
        trailList.add(pheromonetrail72);

        PheromoneTrail pheromonetrail73 = new PheromoneTrail();
        addObject(pheromonetrail73,263,567);
        trailList.add(pheromonetrail73);

        PheromoneTrail pheromonetrail74 = new PheromoneTrail();
        addObject(pheromonetrail74,306,549);
        trailList.add(pheromonetrail74);

        PheromoneTrail pheromonetrail75 = new PheromoneTrail();
        addObject(pheromonetrail75,358,541);
        trailList.add(pheromonetrail75);

        PheromoneTrail pheromonetrail76 = new PheromoneTrail();
        addObject(pheromonetrail76,404,530);
        trailList.add(pheromonetrail76);

        PheromoneTrail pheromonetrail77 = new PheromoneTrail();
        addObject(pheromonetrail77,449,527);
        trailList.add(pheromonetrail77);

        PheromoneTrail pheromonetrail78 = new PheromoneTrail();
        addObject(pheromonetrail78,496,534);
        trailList.add(pheromonetrail78);

        PheromoneTrail pheromonetrail79 = new PheromoneTrail();
        addObject(pheromonetrail79,545,530);
        trailList.add(pheromonetrail79);

        PheromoneTrail pheromonetrail80 = new PheromoneTrail();
        addObject(pheromonetrail80,595,534);
        trailList.add(pheromonetrail80);

        PheromoneTrail pheromonetrail81 = new PheromoneTrail();
        addObject(pheromonetrail81,652,533);
        trailList.add(pheromonetrail81);

        PheromoneTrail pheromonetrail82 = new PheromoneTrail();
        addObject(pheromonetrail82,701,533);
        trailList.add(pheromonetrail82);

        PheromoneTrail pheromonetrail83 = new PheromoneTrail();
        addObject(pheromonetrail83,752,533);
        trailList.add(pheromonetrail83);

        PheromoneTrail pheromonetrail84 = new PheromoneTrail();
        addObject(pheromonetrail84,822,539);
        trailList.add(pheromonetrail84);

        PheromoneTrail pheromonetrail85 = new PheromoneTrail();
        addObject(pheromonetrail85,862,540);
        trailList.add(pheromonetrail85);

        PheromoneTrail pheromonetrail86 = new PheromoneTrail();
        addObject(pheromonetrail,900,535);
        trailList.add(pheromonetrail86);

        pheromonetrail36.setLocation(642,75);
        pheromonetrail37.setLocation(587,81);
        pheromonetrail38.setLocation(536,90);
        pheromonetrail39.setLocation(482,96);
        pheromonetrail40.setLocation(428,98);
        pheromonetrail43.setLocation(273,69);
        pheromonetrail42.setLocation(325,78);
        pheromonetrail41.setLocation(375,92);
        pheromonetrail43.setLocation(267,69);
        pheromonetrail44.setLocation(197,115);
        pheromonetrail43.setLocation(248,74);
        pheromonetrail42.setLocation(313,76);
        pheromonetrail45.setLocation(177,167);
        pheromonetrail46.setLocation(205,224);
        pheromonetrail47.setLocation(262,228);
        pheromonetrail48.setLocation(313,205);
        pheromonetrail49.setLocation(368,185);
        pheromonetrail50.setLocation(425,189);
        pheromonetrail51.setLocation(475,223);
        pheromonetrail54.setLocation(604,333);
        pheromonetrail55.setLocation(656,344);
        pheromonetrail56.setLocation(702,299);
        pheromonetrail56.setLocation(719,291);
        pheromonetrail58.setLocation(807,371);
        pheromonetrail57.setLocation(774,332);
        pheromonetrail56.setLocation(724,304);
        pheromonetrail55.setLocation(669,328);
        pheromonetrail54.setLocation(607,344);
        pheromonetrail55.setLocation(671,336);
        pheromonetrail56.setLocation(730,295);
        pheromonetrail56.setLocation(719,289);
        pheromonetrail55.setLocation(674,333);
        pheromonetrail54.setLocation(614,344);
        pheromonetrail53.setLocation(558,315);
        pheromonetrail57.setLocation(771,324);
        pheromonetrail58.setLocation(807,375);
        pheromonetrail59.setLocation(772,425);
        pheromonetrail60.setLocation(712,453);
        pheromonetrail62.setLocation(614,395);
        pheromonetrail61.setLocation(661,429);
        pheromonetrail61.setLocation(657,427);
        pheromonetrail60.setLocation(712,455);
        pheromonetrail60.setLocation(713,456);
        pheromonetrail61.setLocation(658,427);
        pheromonetrail70.setLocation(203,430);
        pheromonetrail71.setLocation(173,494);
        pheromonetrail70.setLocation(204,432);
        pheromonetrail69.setLocation(269,416);
        pheromonetrail68.setLocation(322,454);
        pheromonetrail67.setLocation(372,441);
        pheromonetrail66.setLocation(419,410);
        pheromonetrail65.setLocation(465,402);
        pheromonetrail64.setLocation(509,425);
        pheromonetrail64.setLocation(516,422);
        pheromonetrail65.setLocation(468,413);
        pheromonetrail66.setLocation(417,417);
        pheromonetrail62.setLocation(608,400);
        pheromonetrail62.setLocation(610,402);
        pheromonetrail60.setLocation(718,457);
        pheromonetrail61.setLocation(662,436);
        pheromonetrail62.setLocation(615,406);
        pheromonetrail62.setLocation(614,412);
        pheromonetrail69.setLocation(285,409);
        pheromonetrail68.setLocation(324,458);
        pheromonetrail65.setLocation(474,427);
        pheromonetrail66.setLocation(415,437);
        pheromonetrail67.setLocation(373,413);
        pheromonetrail69.setLocation(264,415);
        pheromonetrail68.setLocation(318,414);
        pheromonetrail70.setLocation(208,446);
        pheromonetrail72.setLocation(204,549);
        pheromonetrail73.setLocation(261,545);
        pheromonetrail74.setLocation(307,525);
        pheromonetrail73.setLocation(255,545);
        pheromonetrail74.setLocation(306,523);
        pheromonetrail75.setLocation(356,515);
        pheromonetrail76.setLocation(404,529);
        pheromonetrail83.setLocation(776,533);
        pheromonetrail82.setLocation(732,533);
        pheromonetrail83.setLocation(777,539);
        pheromonetrail81.setLocation(680,533);
        pheromonetrail80.setLocation(620,535);
        pheromonetrail79.setLocation(562,537);
        pheromonetrail78.setLocation(508,532);
        pheromonetrail77.setLocation(458,527);
        pheromonetrail65.setLocation(468,426);
        pheromonetrail64.setLocation(520,413);
        pheromonetrail64.setLocation(518,423);
        pheromonetrail65.setLocation(469,430);
        pheromonetrail61.setLocation(660,432);
        pheromonetrail62.setLocation(611,416);
        pheromonetrail67.setLocation(362,416);
        pheromonetrail68.setLocation(313,414);
        pheromonetrail69.setLocation(252,415);
        pheromonetrail.setLocation(921,540);
        pheromonetrail3.setLocation(1023,566);
        pheromonetrail2.setLocation(968,549);
        pheromonetrail3.setLocation(1026,564);
        pheromonetrail4.setLocation(1095,565);
        pheromonetrail5.setLocation(1148,561);
        pheromonetrail7.setLocation(1244,486);
        pheromonetrail7.setLocation(1244,483);
        pheromonetrail6.setLocation(1197,531);
        pheromonetrail9.setLocation(1143,436);
        pheromonetrail8.setLocation(1193,448);
        pheromonetrail9.setLocation(1146,439);
        pheromonetrail13.setLocation(918,368);
        pheromonetrail13.setLocation(915,373);
        pheromonetrail12.setLocation(951,425);
        pheromonetrail13.setLocation(912,378);
        pheromonetrail11.setLocation(1022,425);
        pheromonetrail10.setLocation(1094,408);
        pheromonetrail9.setLocation(1152,412);
        pheromonetrail7.setLocation(1242,483);
        pheromonetrail8.setLocation(1206,438);
        pheromonetrail7.setLocation(1241,486);
        pheromonetrail10.setLocation(1094,426);
        pheromonetrail11.setLocation(1035,452);
        pheromonetrail12.setLocation(965,435);
        pheromonetrail11.setLocation(1042,457);
        pheromonetrail13.setLocation(912,367);
        pheromonetrail15.setLocation(1002,326);
        pheromonetrail15.setLocation(1017,294);
        pheromonetrail16.setLocation(1065,343);
        pheromonetrail17.setLocation(1122,317);
        pheromonetrail18.setLocation(1162,304);
        pheromonetrail15.setLocation(1015,296);
        pheromonetrail16.setLocation(1057,321);
        pheromonetrail17.setLocation(1114,344);
        pheromonetrail18.setLocation(1171,323);
        pheromonetrail19.setLocation(1208,283);
        pheromonetrail14.setLocation(959,315);
        pheromonetrail66.setLocation(411,419);
        pheromonetrail65.setLocation(467,417);
        pheromonetrail64.setLocation(515,418);
        pheromonetrail34.setLocation(732,165);
        pheromonetrail28.setLocation(1057,144);
        pheromonetrail29.setLocation(1029,192);
        pheromonetrail30.setLocation(994,230);
        pheromonetrail31.setLocation(921,222);
        pheromonetrail32.setLocation(846,231);
        pheromonetrail33.setLocation(783,213);
        pheromonetrail34.setLocation(723,174);
        pheromonetrail33.setLocation(774,221);
        pheromonetrail32.setLocation(847,238);
        pheromonetrail31.setLocation(918,239);
        pheromonetrail30.setLocation(983,224);
        pheromonetrail36.setLocation(635,96);
        pheromonetrail38.setLocation(533,80);
        pheromonetrail40.setLocation(427,104);
        pheromonetrail41.setLocation(373,92);
        pheromonetrail32.setLocation(845,235);
        pheromonetrail32.setLocation(844,240);
        pheromonetrail27.setLocation(1054,96);
        pheromonetrail27.setLocation(1053,97);
        pheromonetrail26.setLocation(1073,61);
        pheromonetrail28.setLocation(1061,151);
        pheromonetrail21.setLocation(1225,179);
        pheromonetrail21.setLocation(1237,179);
        pheromonetrail22.setLocation(1222,126);
        pheromonetrail20.setLocation(1227,228);
        pheromonetrail21.setLocation(1239,179);
        pheromonetrail21.setLocation(1235,179);
        pheromonetrail20.setLocation(1230,228);
        pheromonetrail20.setLocation(1230,236);
        pheromonetrail19.setLocation(1205,281);
        pheromonetrail18.setLocation(1167,318);
        pheromonetrail21.setLocation(1240,180);
        pheromonetrail26.setLocation(1067,56);
        pheromonetrail27.setLocation(1041,101);
        pheromonetrail29.setLocation(1030,195);
        pheromonetrail28.setLocation(1061,152);
        pheromonetrail28.setLocation(1068,152);
        pheromonetrail11.setLocation(1035,457);

        /**
        PheromoneTrail pheromonetrail87 = new PheromoneTrail();
        addObject(pheromonetrail2,1213,491);
        trailList.add(pheromonetrail87);

        PheromoneTrail pheromonetrail3 = new PheromoneTrail();
        addObject(pheromonetrail3,1180,432);
        trailList.add(pheromonetrail3);

        PheromoneTrail pheromonetrail4 = new PheromoneTrail();
        addObject(pheromonetrail4,960,410);
        trailList.add(pheromonetrail4);

        PheromoneTrail pheromonetrail5 = new PheromoneTrail();
        addObject(pheromonetrail5,940,364);

        trailList.add(pheromonetrail5);
        PheromoneTrail pheromonetrail6 = new PheromoneTrail();
        addObject(pheromonetrail6,983,319);
        trailList.add(pheromonetrail6);
        PheromoneTrail pheromonetrail7 = new PheromoneTrail();
        addObject(pheromonetrail7,1163,314);
        trailList.add(pheromonetrail7);
        PheromoneTrail pheromonetrail8 = new PheromoneTrail();
        addObject(pheromonetrail8,1211,265);
        trailList.add(pheromonetrail8);
        PheromoneTrail pheromonetrail9 = new PheromoneTrail();
        addObject(pheromonetrail9,1210,157);
        trailList.add(pheromonetrail9);*/
    }
}
