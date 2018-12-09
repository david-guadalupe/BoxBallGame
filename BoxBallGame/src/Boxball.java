import objectdraw.*;
import java.awt.*;

/**
 * Lab 4 - Box Ball
 * <p>
 * a game in which players attempt to drop balls into a small box
 * <p>
 *
 * Suggested window size: 600x600
 *
 * @author YOUR-NAME-HERE, YOUR-LAB-SECTION-HERE
 */
public class Boxball extends WindowController {

	// constants for the play area and button positions and sizes
	private static final int GAMEWIDTH = 400;
	private static final int GAMEHEIGHT = 400;
	private static final Location GAMELOC = new Location(50, 50);

	// constants for box and ball sizes
	


	// constants for the buttons
	private static final int BUTTONY = 470;
	private static final int BUTTONWIDTH = 75;
	private static final int BUTTONHEIGHT = 50;

	// instance variables for difficulty buttons, text, box
	private Location boxStart = new Location(60, 400);
	private int startWidth = 40;
	
	// create the boxes for the buttons
	private FilledRect easy;
	private FilledRect medium;
	private FilledRect hard;
	
	// text for the buttons
	private Text easyText;
	private Text mediumText;
	private Text hardText;
	// text for correct hits and incorrect misses

	// I.V. for text shown when hit is correct or incorrect

	private Line bar;
	private int lineStartX;
	private int lineStartY;
	private int lineEndX;
	private int lineEndY;
	// play area instance var.
	private FramedRect playArea;

	private Box box;
	
	private int isEasy;
	private int isMedium;
	private int isHard;
	
	private Text message;

	/**
	 * Initialization method, called when the applet starts:
	 * <UL>
	 * <LI>create the playing arena</LI>
	 * <LI>create the title message</LI>
	 * <LI>create the difficulty buttons</LI>
	 * <LI>create the success/fail message</LI>
	 * <LI>create the box</LI>
	 * </UL>
	 */
	public void begin() {
		
		// create the play area
		playArea = new FramedRect(GAMELOC, GAMEWIDTH, GAMEHEIGHT, canvas);
		
		//create the starting bar
		lineStartX = 50;
		lineStartY = 300;
		lineEndX = 450;
		lineEndY = 300;
		bar = new Line(lineStartX, lineStartY, lineEndX, lineEndY, canvas);
		
		// create the buttons
		easy = new FilledRect(100, BUTTONY, BUTTONWIDTH, BUTTONHEIGHT, canvas);
		medium = new FilledRect(200, BUTTONY, BUTTONWIDTH, BUTTONHEIGHT, canvas);
		hard = new FilledRect(300, BUTTONY, BUTTONWIDTH, BUTTONHEIGHT, canvas);
		
		//set the color of each button
		easy.setColor(Color.GREEN);
		medium.setColor(Color.YELLOW);
		hard.setColor(Color.RED);
		
		//initialize the text for each button
		easyText = new Text("Easy", 120, 480, canvas);
		mediumText = new Text("Medium", 220, 480, canvas);
		hardText = new Text("Hard", 320, 480, canvas);
		
		//color of the text for each button
		easyText.setColor(Color.BLACK);
		mediumText.setColor(Color.BLACK);
		hardText.setColor(Color.BLACK);
		
		
		// initialize the box
		box = new Box(boxStart, startWidth, canvas);
		message = new Text("", 200, 450, canvas);
		
	}

	/**
	 * Event handler, called when mouse button is "clicked"
	 * <P>
	 * If click is on a difficulty button, change the box size, and the height
	 * from which balls drop.
	 * <P>
	 * If click was in arena, create a new ball, starting at that X position,
	 * from the difficulty-height.
	 *
	 * @param point
	 *            mouse coordinates at time of click
	 */
	public void onMouseClick(Location point) {
		if (playArea.contains(point)) {
			if (point.getY() < bar.getEnd().getY()) {
				box.getLeft();
				box.getRight(); 
				new Ball(canvas, point,box,message );
			}
		}
		if (medium.contains(point)) {
			isMedium = 1;
			box.setSize(isMedium);
			bar.moveTo(50, 200);
			box.moveBox();
			
		} else if (hard.contains(point)) {
			isHard = 2;
			box.setSize(isHard);
			bar.moveTo(50, 150);
			box.moveBox();
		} else if (easy.contains(point)) {
			isEasy = 0;
			box.setSize(isEasy);
			bar.moveTo(50, 300);
			box.moveBox();
		}
	}
}