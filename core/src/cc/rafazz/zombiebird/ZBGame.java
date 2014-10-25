package cc.rafazz.zombiebird;

import cc.rafazz.screens.SplashScreen;
import cc.rafazz.zbhelpers.AssetLoader;

import com.badlogic.gdx.Game;

public class ZBGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
