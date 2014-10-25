package cc.rafazz.zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	// Preferences:
	public static Preferences prefs;
	
	// Textures:
	public static Texture texture;
	public static TextureRegion bg, grass;
	public static TextureRegion skullUp, skullDown, bar;
	public static TextureRegion bird, birdDown, birdUp;

	// Animation:
	public static Animation birdAnimation;

	// Sounds:
	public static Sound dead, coin, flap;

	// Fonts:
	public static BitmapFont font, shadow;

	public static void load() {

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 153, 0, 17, 12);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		birdUp.flip(false, true);

		TextureRegion[] birds = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		skullUp = new TextureRegion(texture, 192, 0, 24, 14);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		
		// Load and scale the fonts:
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		font.setScale(.25f, -.25f);
		shadow.setScale(.25f, -.25f);
		
		// Load the preferences:
		prefs = Gdx.app.getPreferences("cc.rafazz.ZB");
		if(!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
		dead.dispose();
		coin.dispose();
		flap.dispose();
		font.dispose();
		shadow.dispose();
	}
	
	// Receives an integer and maps it to the String highScore in prefs
	public static void setHighScore(int val) {
	    prefs.putInteger("highScore", val);
	    prefs.flush();
	}

	// Retrieves the current high score
	public static int getHighScore() {
	    return prefs.getInteger("highScore");
	}
}
