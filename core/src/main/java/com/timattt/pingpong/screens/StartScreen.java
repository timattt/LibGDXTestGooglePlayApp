package com.timattt.pingpong.screens;

import static com.timattt.pingpong.core.Globals.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.timattt.pingpong.core.Globals;

public class StartScreen extends ScreenAdapter {

	// Stage
	private Stage stage;
	
	public StartScreen() {
		stage = new Stage(Globals.guiViewport);
		
		Skin skin = assetManager.get("skin/skin.json");
		
		Image bg = new Image((Texture) assetManager.get("background.jpg"));
		bg.setFillParent(true);
		
		stage.addActor(bg);
		
        Table root = new Table();
        root.setFillParent(true);
		
		TextButton play = new TextButton("Play", skin);
		TextButton exit = new TextButton("Exit", skin);

		root.add(play).prefWidth(200).prefHeight(50).pad(25);
		root.row();
		root.add(exit).prefWidth(200).prefHeight(50).pad(25);
		stage.addActor(root);
		
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Globals.game.setScreen(Globals.gameScreen);
			}
		});
		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
