package cc.rafazz.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {

	private Random r;

	private Rectangle skullUp, skullDown, barUp, barDown;

	public static final int VERTICAL_GAP = 45;
	public static final int SKULL_WIDTH = 24;
	public static final int SKULL_HEIGHT = 11;
	
	private static final int X_UPDATE_RATIO = 10;
	
	private float groundY;
	private boolean isScored = false;
	
	private boolean isMovable = false;	// This identifies if it is a movable one
	private boolean movingUp = false;
	private int updateX = 0;
	private int xCounter = 0;

	// When Pipe's constructor is invoked, invoke the super (Scrollable)
	// constructor
	public Pipe(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		r = new Random();
		skullUp = new Rectangle();
		skullDown = new Rectangle();
		barUp = new Rectangle();
		barDown = new Rectangle();
		
		isMovable = r.nextBoolean();
		movingUp = r.nextBoolean();
		
		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);

		if (isMovable) {
			// movingUp = (height <= 15);
			// Gdx.app.log("Pipe", "Height: " + height + " movingUp: " + movingUp);
			xCounter++;
			if (movingUp) {
				height += updateX;
				movingUp = (height < 105);
			} else {
				height -= updateX;
				movingUp = (height < 15); 
			}
			if (xCounter > X_UPDATE_RATIO) {
				updateX = 1;
				xCounter = 0;
			} else {
				updateX = 0;
				xCounter++;
			}
		}
		
		// The set() method allows you to set the top left corner's x, y
		// coordinates,
		// along with the width and height of the rectangle

		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				groundY - (position.y + height + VERTICAL_GAP));

		// Our skull width is 24. The bar is only 22 pixels wide. So the skull
		// must be shifted by 1 pixel to the left (so that the skull is centered
		// with respect to its bar).

		// This shift is equivalent to: (SKULL_WIDTH - width) / 2
		skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
				- SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
		skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
				SKULL_WIDTH, SKULL_HEIGHT);

	}

	public boolean collides(Bird bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
					|| Intersector.overlaps(bird.getBoundingCircle(), barDown)
					|| Intersector.overlaps(bird.getBoundingCircle(), skullUp) || Intersector
						.overlaps(bird.getBoundingCircle(), skullDown));
		}
		return false;
	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		// Change the height to a random number
		height = r.nextInt(90) + 15;
		isScored = false;
		isMovable = r.nextBoolean();
		movingUp = r.nextBoolean();
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}

	public Rectangle getSkullUp() {
		return skullUp;
	}

	public Rectangle getSkullDown() {
		return skullDown;
	}

	public Rectangle getBarUp() {
		return barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean isScored) {
		this.isScored = isScored;
	}
}