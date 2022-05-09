package com.timattt.pingpong.core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.timattt.pingpong.screens.GameScreen;
import com.timattt.pingpong.screens.StartScreen;

public class Globals {

	// GLOBAL INSTANCES
	public static OrthographicCamera camera;
	public static Pingpong game;
	public static ScreenViewport guiViewport;
	public static ExtendViewport gameViewport;
	
	// screens
	public static GameScreen gameScreen;
	public static StartScreen startScreen;

	// Resource manager
	public static AssetManager assetManager;
	
	// TIME
	public static final long AI_UPDATE_TIME = 200;
	
	// SIZES
	// BALL
	public static final float BALL_WIDTH = 5f;
	public static final float BALL_HEIGHT = 5f;
	
	// PLAYER
	public static final float PLAYER_WIDTH = 5f;
	public static final float PLAYER_HEIGHT = 20f;
	
	// WALL
	public static final float WALL_THICKNESS = 2f;
	
	// WORLD
	public static final float WORLD_WIDTH = 100f;
	public static final float WORLD_HEIGHT = 100f;
}
