package com.timattt.pingpong.objects;

import static com.timattt.pingpong.core.Globals.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.timattt.pingpong.core.Utilities;

public abstract class Player extends Actor {

	// parameters
	protected Body body;
	protected int score;
	protected Texture playerTexture;
	
	public Player(float x, float y, World world) {
		score = 0;
		body = Utilities.createBox(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, 0, world, BodyType.DynamicBody, ObjectType.PLAYER);
		playerTexture = assetManager.get("player.png");
	}
 
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(playerTexture, body.getPosition().x - PLAYER_WIDTH / 2, body.getPosition().y - PLAYER_HEIGHT / 2, PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	public int getScore() {
		return score;
	}

	public void addScore() {
		score++;
	}
	
	public void resetScore() {
		score = 0;
	}
}
