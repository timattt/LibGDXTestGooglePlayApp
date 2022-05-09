package com.timattt.pingpong.screens;

import static com.timattt.pingpong.core.Globals.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.timattt.pingpong.core.Globals;
import com.timattt.pingpong.core.ScoreCounter;
import com.timattt.pingpong.objects.Ball;
import com.timattt.pingpong.objects.ComputerPlayer;
import com.timattt.pingpong.objects.HumanPlayer;
import com.timattt.pingpong.objects.Wall;

public class GameScreen extends ScreenAdapter {
	
	// WORLD
	private World world;
	
	// players
	private HumanPlayer player1;
	private ComputerPlayer player2;
	
	// ball
	private Ball ball;
	
	// UI
	private Stage worldStage;
	private Stage guiStage;
	private Label scoreLabel;
	private Table pauseMenu;
	private TextButton pauseButton;
	private TextButton up;
	private TextButton down;
	
	public GameScreen() {
		guiStage = new Stage(Globals.guiViewport);
		worldStage = new Stage(Globals.gameViewport);
		world = new World(new Vector2(0, 0), false);
		world.setContactListener(new ScoreCounter());

		initUi();
		initWorld();
	}
	
	private void initUi() {
		Skin skin = (Skin) assetManager.get("skin/skin.json");
		
		// MAIN SCREEN
		scoreLabel = new Label("Score: 0 - 0", skin);
		
		// COMPONENTS
		pauseMenu = new Table();
		pauseButton = new TextButton("Menu", skin);
		pauseButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setMenuOpened(!pauseMenu.isVisible());
			}
		});
		up = new TextButton("+", skin);
		down = new TextButton("-", skin);
		
		// MAIN TABLE
		Table root = new Table();
		root.setFillParent(true);
		root.align(Align.top);
		root.add(scoreLabel).pad(0, 25, 0, 25).align(Align.left).expandX();
		root.add(pauseButton).pad(25, 25, 0, 25).align(Align.right);
		root.row();
		root.add(new Label("", skin)).align(Align.left).expandX();
		root.add(pauseMenu).align(Align.right);
		root.row();
		root.add(new Label("", skin)).expand();
		root.row();
		root.add(up).prefSize(70, 70).align(Align.left).pad(25, 25, 0, 25);
		root.add(new Label("", skin)).expandX();
		root.row();
		root.add(down).prefSize(70, 70).align(Align.left).pad(0, 25, 25, 25);
		root.add(new Label("", skin)).expandX();
		guiStage.addActor(root);
		

		
		// MENU COMPONENTS
		TextButton restart = new TextButton("Restart", skin);
		restart.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				resetAll();
			}
		});
		TextButton exit = new TextButton("Exit", skin);
		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Globals.game.setScreen(Globals.startScreen);
			}
		});
		
		pauseMenu.add(restart).pad(0, 25, 0, 25).align(Align.right);
		pauseMenu.row();
		pauseMenu.add(exit).pad(0, 25, 0, 25).align(Align.right);
		pauseMenu.setVisible(false);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(guiStage);
		resetAll();
	}
	
	private void resetAll() {
		player1.resetScore();
		player2.resetScore();
		refreshScoreLabel();
		requestBallReset();
		setMenuOpened(false);
	}
	
	private void setMenuOpened(boolean flag) {
		pauseMenu.setVisible(flag);
		if (pauseMenu.isVisible()) {
			pauseButton.setText("Resume");
		} else {
			pauseButton.setText("Menu");
		}
	}

	private void initWorld() {
		player1 = new HumanPlayer(world);
		player2 = new ComputerPlayer(world);
		ball = new Ball(world);
		Wall wall1 = new Wall(0f, WORLD_WIDTH/2-WALL_THICKNESS/2, WORLD_WIDTH, WALL_THICKNESS, world);
		Wall wall2 = new Wall(0f, WALL_THICKNESS/2 - WORLD_WIDTH/2, WORLD_WIDTH, WALL_THICKNESS, world);
		Wall wall3 = new Wall(WALL_THICKNESS/2 - WORLD_WIDTH/2, 0f, WALL_THICKNESS, WORLD_HEIGHT, world, true);
		Wall wall4 = new Wall(WORLD_WIDTH/2-WALL_THICKNESS/2, 0f, WALL_THICKNESS, WORLD_HEIGHT, world, true);
	
		worldStage.addActor(player1);
		worldStage.addActor(player2);
		worldStage.addActor(ball);
		worldStage.addActor(wall1);
		worldStage.addActor(wall2);
		worldStage.addActor(wall3);
		worldStage.addActor(wall4);
	}
	

	@Override
	public void render(float delta) {
		if (!pauseMenu.isVisible()) {
			world.step(1f / 60f, 6, 2);
		}
		
		worldStage.act(delta);
		worldStage.draw();
		guiStage.act(delta);
		guiStage.draw();
		
		ball.checkReset();
	}

	private void refreshScoreLabel() {
		scoreLabel.setText("Score: " + player1.getScore() + " - " + player2.getScore());
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public void addScoreToHuman() {
		player1.addScore();
		refreshScoreLabel();
		requestBallReset();
	}
	
	public void addScoreToComputer() {
		player2.addScore();
		refreshScoreLabel();
		requestBallReset();
	}

	@Override
	public void dispose() {
		worldStage.dispose();
		guiStage.dispose();
	}
	
	public void requestBallReset() {
		ball.requestReset = true;
	}
	
	public boolean isUpPressed() {
		return up.isPressed();
	}
	
	public boolean isDownPressed() {
		return down.isPressed();
	}
}
