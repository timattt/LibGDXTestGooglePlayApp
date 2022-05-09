package com.timattt.pingpong.core;

import static com.timattt.pingpong.core.Globals.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.timattt.pingpong.screens.GameScreen;
import com.timattt.pingpong.screens.StartScreen;

public class Pingpong extends Game {
	
	@Override
	public void create () {
		loadAssets();
		
		game = this;
		camera = new OrthographicCamera(100, 100);
		guiViewport = new ScreenViewport();
		gameViewport = new ExtendViewport(100, 100, camera);
		guiViewport.setUnitsPerPixel(0.5f);
		gameScreen = new GameScreen();
		startScreen = new StartScreen();
		
		camera.position.x = camera.position.y = 0;
		
		setScreen(startScreen);
	}
	
	private void loadAssets() {
		assetManager = new AssetManager();
		assetManager.load("player.png", Texture.class);
		assetManager.load("ball.png", Texture.class);
		assetManager.load("wall.png", Texture.class);
		assetManager.load("background.jpg", Texture.class);
		assetManager.load("skin/skin.json", Skin.class);
		assetManager.load("effects/testParticle.p", ParticleEffect.class);
		
		assetManager.finishLoading();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0.6f, 0, 1);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		guiViewport.update(width, height, true);
		gameViewport.update(width, height, false);
		super.resize(width, height);
	}

	@Override
	public void dispose () {
		super.dispose();
		assetManager.dispose();
	}
}
