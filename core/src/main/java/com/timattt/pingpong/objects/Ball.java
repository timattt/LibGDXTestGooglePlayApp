package com.timattt.pingpong.objects;

import static com.timattt.pingpong.core.Globals.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.timattt.pingpong.core.Utilities;

public class Ball extends Actor {

	// Physics
	private Body body;

	// Resources
	private ParticleEffect particle;
	private Texture ballTexture;
	
	// hit and reset
	public boolean requestReset = false;
	public long lastHitTime = 0; 
	
	public Ball(World world) {
		body = Utilities.createBox(0, 0, BALL_WIDTH, BALL_HEIGHT, 1, world, BodyType.DynamicBody, ObjectType.BALL);
		reset();
		ballTexture = assetManager.get("ball.png");
		particle = assetManager.get("effects/testParticle.p");
		particle.start();
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(	ballTexture,
					body.getPosition().x - BALL_WIDTH / 2,
					body.getPosition().y - BALL_HEIGHT / 2,
					BALL_WIDTH/2,
					BALL_HEIGHT/2,
					BALL_WIDTH,
					BALL_HEIGHT,
					1f,
					1f,
					body.getAngle() * 180f/3.1415f,
					0,
					0,
					ballTexture.getWidth(),
					ballTexture.getHeight(),
					false,
					false
					);
		particle.draw(batch);
	}
	
	@Override
	public void act(float delta) {
		particle.setPosition(body.getPosition().x, body.getPosition().y);
		particle.update(delta);
	}

	public void reset() {
		body.setTransform(0f, 0f, 0f);
		body.setLinearVelocity((Math.random() < 0.5 ? WORLD_WIDTH/2 : -WORLD_WIDTH/2), 0);
	}
	
	public float getY() {
		return body.getPosition().y;
	}

	public void checkReset() {
		if (this.requestReset) {
			this.requestReset = false;
			this.reset();
		}
		
	}
}
