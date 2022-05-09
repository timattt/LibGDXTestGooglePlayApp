package com.timattt.pingpong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.timattt.pingpong.core.Globals;

import static com.timattt.pingpong.core.Globals.*;

public class HumanPlayer extends Player {

	public HumanPlayer(World world) {
		super(WALL_THICKNESS + PLAYER_WIDTH/2-WORLD_WIDTH/2, 0, world);
		body.setFixedRotation(true);
	}

	@Override
	public void act(float delta) {
		body.setLinearVelocity(0, 0);
		
		if (Gdx.input.isKeyPressed(Input.Keys.W) || Globals.gameScreen.isUpPressed()) {
			up();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S) || Globals.gameScreen.isDownPressed()) {
			down();
		}
	}
	
	public void up() {
		body.setLinearVelocity(0, WORLD_WIDTH / 2);
	}
	
	public void down() {
		body.setLinearVelocity(0, -WORLD_WIDTH/2);
	}

}
