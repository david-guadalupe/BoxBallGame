import objectdraw.*;
import java.awt.*;

/**
 * a box at a randomly chosen position (somewhere at the bottom of the playing
 * area) into which the player attempts to drop balls.
 *
 * We do not create a new box for each ball drop. Rather we: - create a box at
 * the beginning of the game - move it after the completion of each ball fall -
 * change its width in response to difficulty selections
 */
public class Box {

	// instance variables for current/legal positions
	// TODO DESIGN: declarations for range of legal positions
	// TODO DESIGN: declarations for current position
	private static final int BOXHEIGHT = 50;
	private static final int EASYWIDTH = 40;
	private static final int MEDIUMWIDTH = 30;
	private static final int HARDWIDTH = 20;

	// variable for the box that is the target
	private FilledRect gameBox;

	// end and starting point of the box's range
	private int startOfXRange = 450;
	private int endOfXRange = 0;

	private boolean inRange;
	private RandomIntGenerator randomX;

	/**
	 * create a box at the bottom of the game
	 *
	 * @param startOfXRange
	 *            minimum legal X position
	 * @param endOfXRange
	 *            maximum legal X position
	 * @param desiredY
	 *            desired Y position
	 * @param intitialWidth
	 *            initial width
	 * @param canvas
	 *            Drawing canvas
	 */

	public Box(Location loc, int width, DrawingCanvas canvas) {
		gameBox = new FilledRect(loc, width, BOXHEIGHT, canvas);
		inRange = true;
	}

	/**
	 * Choose a new (random) location for the box, and move it there.
	 *
	 * But ... we must make sure the chosen location is legal: the left edge of
	 * the box must be within the playing area the right edge of the box must be
	 * within the playing area
	 */
	public void moveBox() {
		// create a random integer within the range and set it to be the new X
		// coordinate and move the box to that position
		randomX = new RandomIntGenerator(50, 400);
		gameBox.moveTo(randomX.nextValue(), 400);

	}

	/**
	 * set the width of the box to change the difficulty (smaller boxes are
	 * harder to hit).
	 *
	 * Note that changing the width of the box may cause it to no longer fit
	 * within the playing area.
	 *
	 * @param which
	 *            button was pressed
	 */
	// check to see which level of difficulty it is and change the box
	// accordingly
	public void setSize(int boxNumber) {
		if (inRange) {
			if (boxNumber == 1) {
				gameBox.setWidth(MEDIUMWIDTH);
				if (gameBox.getX() + MEDIUMWIDTH >= startOfXRange || gameBox.getX() <= endOfXRange) {
					inRange = false;
				}
			}
			if (boxNumber == 2) {
				gameBox.setWidth(HARDWIDTH);
				if (gameBox.getX() + HARDWIDTH >= startOfXRange || gameBox.getX() <= endOfXRange) {
					inRange = false;
				}
			}
			if (boxNumber == 0) {
				gameBox.setWidth(EASYWIDTH);
				if (gameBox.getX() + EASYWIDTH >= startOfXRange || gameBox.getX() <= endOfXRange) {
					inRange = false;
				}
			}
		}
	}

	/**
	 * X position of the left edge of the box (used to determine whether or not
	 * ball fell in box)
	 *
	 * @return x position of the left edge of the box
	 */
	public double getLeft() {
		return gameBox.getX();
	}

	/**
	 * X position of the right edge of the box (used to determine whether or not
	 * ball fell in box)
	 *
	 * @return x position of the right edge of the box
	 */
	public double getRight() {
		return gameBox.getX() + gameBox.getWidth();
	}
}
