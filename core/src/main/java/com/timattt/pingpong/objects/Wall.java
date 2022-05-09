package com.timattt.pingpong.objects;

import static com.timattt.pingpong.core.Globals.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.timattt.pingpong.core.Utilities;

public class Wall extends Actor {

	// parameters
	private Texture ballTexture;
	private Body body;
	private float width;
	private float height;
	
	public Wall(float x, float y, float w, float h, World world) {
		body = Utilities.createBox(x, y, w, h, 0, world, BodyType.StaticBody, ObjectType.WALL);
		width = w;
		height = h;
		ballTexture = assetManager.get("wall.png");
	}
	
	public Wall(float x, float y, float w, float h, World world, boolean deadly) {
		body = Utilities.createBox(x, y, w, h, 0, world, BodyType.StaticBody, ObjectType.DEADLY_WALL);
		width = w;
		height = h;
		ballTexture = assetManager.get("wall.png");
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(ballTexture, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
	}

}
