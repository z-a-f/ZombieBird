package cc.rafazz.screens;

import cc.rafazz.gameworld.GameRenderer;
import cc.rafazz.gameworld.GameWorld;
import cc.rafazz.zbhelpers.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

	public GameScreen() {
		Gdx.app.log("GameScreen", "Attached");
		
		runTime = 0;
		// Get the dimensions:
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);

		int midPointY = (int) (gameHeight / 2);

		// Initialize
		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world, (int)gameHeight, midPointY);

		// input handler:
		Gdx.input.setInputProcessor(new InputHandler(world));
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta); // Update the world:
		renderer.render(runTime); // Render the updates:
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void dispose() {
		// Leave blank
	}

}