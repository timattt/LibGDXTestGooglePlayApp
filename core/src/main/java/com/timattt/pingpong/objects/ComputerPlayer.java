package com.timattt.pingpong.objects;

import static com.timattt.pingpong.core.Globals.*;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.timattt.pingpong.core.Globals;

public class ComputerPlayer extends Player {

	// AI
	private long lastUpdate = 0;
	
	public ComputerPlayer(World world) {
		super(-WALL_THICKNESS - PLAYER_WIDTH/2+WORLD_WIDTH/2, 0f, world);
		body.setFixedRotation(true);
	}

	@Override
	public void act(float delta) {
		if (TimeUtils.millis() - lastUpdate > AI_UPDATE_TIME) {
			lastUpdate = TimeUtils.millis();
		} else {
			return;
		}

		if (body.getPosition().y > Globals.gameScreen.getBall().getY()) {
			body.setLinearVelocity(0, -WORLD_WIDTH/2);
		} else {
			body.setLinearVelocity(0, WORLD_WIDTH/2);
		}
	}

}
