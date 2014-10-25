package cc.rafazz.zombiebird;

import cc.rafazz.screens.GameScreen;
import cc.rafazz.zbhelpers.AssetLoader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ZBGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Gdx.app.log("ZBGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
