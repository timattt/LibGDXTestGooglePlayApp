package com.timattt.pingpong.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.timattt.pingpong.objects.ObjectType;

public class Utilities {

	public static Body createBox(float x, float y, float width, float height, float restitution, World world, BodyType phy, ObjectType type) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = phy;
		bodydef.position.set(x, y);
		
		Body body = world.createBody(bodydef);
		body.setUserData(type);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		
		FixtureDef fxt = new FixtureDef();
		fxt.shape = shape;
		fxt.density = 10000;
		fxt.restitution = restitution;
		
		body.createFixture(fxt);
		
		shape.dispose();
		return body;
	}
	
}
