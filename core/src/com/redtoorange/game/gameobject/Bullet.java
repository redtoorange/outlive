package com.redtoorange.game.gameobject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.redtoorange.game.components.physics.BulletPhysicsComponent;
import com.redtoorange.game.components.rendering.sprite.SpriteComponent;
import com.redtoorange.game.systems.PhysicsSystem;
import com.redtoorange.game.systems.sound.SoundEffect;
import com.redtoorange.game.systems.sound.SoundManager;

/**
 * Bullet.java - Basic bullet class.  Will be initialized inputComponent an Array and spawned/reset as needed (pooled for
 * efficiency).
 *
 * @author - Andrew M.
 * @version - 13/Jan/2017
 */
public class Bullet extends GameObject {
	protected float lifeTime = 0.0f;
	protected float maxLife = 5.0f;
	protected boolean alive = false;
	protected float speed;
	protected BulletPhysicsComponent bulletPhysicsComponent;
	protected SpriteComponent spriteComponent;

	private SoundManager sm = new SoundManager( );

	public Bullet( GameObject parent, Sprite sprite, PhysicsSystem physicsSystem, Vector2 position, float speed ) {
		super( parent, position );

		this.speed = speed;
		spriteComponent = new SpriteComponent( sprite );
		bulletPhysicsComponent = new BulletPhysicsComponent( physicsSystem, this, spriteComponent );

		components.add( bulletPhysicsComponent );
		components.add( spriteComponent );

		sm.addSound( "bullethit", new SoundEffect( "sounds/bullethit.wav", 0.15f ) );
	}

	@Override
	public void update( float deltaTime ) {
		if ( alive ) {
			super.update( deltaTime );

			lifeTime += deltaTime;
			if ( lifeTime >= maxLife )
				kill( );
		}
		sm.update( deltaTime );
	}

	public void preLighting( SpriteBatch batch ) {
		if ( alive ) {
			spriteComponent.draw( batch );
		}
	}

	public void fire( Vector2 position, Vector2 velocity, float rotation ) {
		alive = true;

		spriteComponent.setCenter( position );
		spriteComponent.setRotation( rotation );

		bulletPhysicsComponent.fire( position, velocity, rotation );
	}

	public void kill( ) {
		sm.playSound( "bullethit" );
		this.alive = false;
		lifeTime = 0.0f;
		bulletPhysicsComponent.kill( );
	}

	public boolean isAlive( ) {
		return alive;
	}

	@Override
	public void dispose( ) {
		if ( spriteComponent != null )
			spriteComponent.dispose( );
		if ( bulletPhysicsComponent != null )
			bulletPhysicsComponent.dispose( );
	}

	public float getSpeed( ) {
		return speed;
	}
}