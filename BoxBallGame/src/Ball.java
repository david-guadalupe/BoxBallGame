import objectdraw.*;
import java.awt.*;

/**
 * A Ball is an active object that falls at a constant speed.
 *
 * When it reaches the bottom, it determines whether or not it fell in the box
 * and displays an appropriate message. If the ball falls into the box, we
 * instruct the box to move to a new position.
 */
public class Ball extends ActiveObject {

	// constants for smooth ball motion
	private static final int SPEED = 10;
	private static final int PAUSE = 50;
	// Diameter of the ball
	private static final int BALLSIZE = 15;


	// instance variables for ball/game parameters
	private int boxHeight = 400;
	
	// instance variables for box, ball, and text
	private FilledOval fallingBall;
	
	//reference variables for box and message
	private Text refMessage;
	private Box refBox;

	/**
	 * create a falling ball, and start a thread to move it
	 *
	 * @param loc
	 *            starting position
	 * @param BALLSIZE
	 *            diameter
	 * @param box
	 *            target box
	 * @param message
	 *            text
	 */

	public Ball(DrawingCanvas canvas, Location loc, Box box, Text message) {
		// create a ball at the specified starting position
		fallingBall = new FilledOval(loc.getX(), 0, BALLSIZE, BALLSIZE, canvas);
		refBox = box;
		refMessage = message;
		refMessage.sendToFront();

		// start up the thread that moves the ball
		// (this must be the last thing done in this method)
		start();
	}

	/**
	 * thread routine for the falling ball
	 * <P>
	 * Slowly move it downwards until it reaches the bottom of the playing area,
	 * and then determine whether or not it fell within the box. Set the message
	 * text accordingly, and then instruct the box to move to a new position.
	 */
	public void run() {
		//ball moves down the screen
		while (fallingBall.getY() < boxHeight + 50) {
			fallingBall.move(0, SPEED);
			pause(PAUSE);
		}
		fallingBall.removeFromCanvas();
		
		//if the ball is in the box, send correct message and move box. Otherwise send incorrect message
		if (fallingBall.getX() >= refBox.getLeft() && fallingBall.getX() + fallingBall.getWidth() <= refBox.getRight()) {
			refMessage.setText("You got it in!");
			refBox.moveBox();
		} else {
			refMessage.setText("Try again!");
		}

	}
}