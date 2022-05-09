package com.timattt.pingpong.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.TimeUtils;

public class ScoreCounter implements ContactListener {

	private long lastPlayerTouchTime = 0;
	
	@Override
	public void beginContact(Contact contact) {
		Body body1 = contact.getFixtureA().getBody();
		Body body2 = contact.getFixtureB().getBody();
		
		Body ball = null;
		Body wall = null;
		Body deadlyWall = null;
		Body player = null;

		if (body1.getUserData().toString().equals("BALL")) {
			ball = body1;
		}
		if (body2.getUserData().toString().equals("BALL")) {
			ball = body2;
		}
		if (body1.getUserData().toString().equals("DEADLY_WALL")) {
			deadlyWall = wall = body1;
		}
		if (body2.getUserData().toString().equals("DEADLY_WALL")) {
			deadlyWall = wall = body2;
		}
		if (body1.getUserData().toString().equals("PLAYER")) {
			player = body1;
		}
		if (body2.getUserData().toString().equals("PLAYER")) {
			player = body2;
		}
		
		if (ball != null && (player != null || deadlyWall != null)) {
			lastPlayerTouchTime = TimeUtils.millis();
		}
		
		if (TimeUtils.millis() - lastPlayerTouchTime > 15000) {
			Globals.gameScreen.requestBallReset();
			lastPlayerTouchTime = TimeUtils.millis();
		}
		
		if (ball != null && wall != null) {
			if (wall.getPosition().x < 50f) {
				Globals.gameScreen.addScoreToComputer();
			} else {
				Globals.gameScreen.addScoreToHuman();
			}
		}
	}

	@Override
	public void endContact(Contact contact) {


	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {


	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {


	}

}
