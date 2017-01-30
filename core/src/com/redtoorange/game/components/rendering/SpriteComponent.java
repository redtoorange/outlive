package com.redtoorange.game.components.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.redtoorange.game.entities.Entity;

/**
 * SpriteComponent.java - A Render Component that encapsulates a Sprite to allow easy movement and boudning
 * calculations.
 *
 * @author - Andrew M.
 * @version - 20/Jan/2017
 */
public class SpriteComponent extends RenderComponent {
	private Sprite sprite;

	/**
	 * A clone of this is stored inside the component.
	 *
	 * @param sprite Sprite that should be cloned.
	 * @param parent The parent entity.
	 */
	public SpriteComponent( Sprite sprite, Entity parent ) {
		super( parent );

		Vector2 pos = parent.getTransform().getPosition();
		this.sprite = new Sprite( sprite );
		this.sprite.setCenter( pos.x, pos.y );
		this.sprite.setOriginCenter( );
	}

	public void setColor( float r, float g, float b, float a ) {
		sprite.setColor( new Color( r, g, b, a ) );
	}

	@Override
	public void draw( SpriteBatch batch ) {
		setCenter( parent.getTransform().getPosition( ) );
		sprite.draw( batch );
	}

	public Sprite getSprite( ) {
		return sprite;
	}

	/**
	 * Get the Sprite's current rotation value.
	 *
	 * @return A float representing the rotation of the sprite inputComponent degrees
	 */
	public float getRotation( ) {
		return sprite.getRotation( );
	}

	/**
	 * Set the Sprite's rotation inputComponent degrees.
	 *
	 * @param rotation the degree representation the Sprite should be set to
	 */
	public void setRotation( float rotation ) {
		sprite.setRotation( rotation );
	}

	/**
	 * Helper method to allow the grabbing of a new Vector2 reference to the center of the Sprite.
	 *
	 * @return The Center of the Sprite
	 */
	public Vector2 getCenter( ) {
		return new Vector2( getCenterX( ), getCenterY( ) );
	}

	/**
	 * Set the center of the Sprite, this does NOT set the upper left position like a normal position.set()
	 *
	 * @param center Where the center of the sprite should be draw.
	 */
	public void setCenter( Vector2 center ) {
		sprite.setCenter( center.x, center.y );
	}

	public float getCenterX( ) {
		return ( sprite.getX( ) + ( sprite.getWidth( ) / 2f ) );
	}

	public void setCenterX( float x ) {
		sprite.setCenter( x, getCenterY( ) );
	}

	public float getCenterY( ) {
		return ( sprite.getY( ) + ( sprite.getHeight( ) / 2f ) );
	}

	public void setCenterY( float y ) {
		sprite.setCenter( getCenterX( ), y );
	}

	/**
	 * Wrapper for the Sprite getBoundingRectangle() method.
	 *
	 * @return The smallest possible rectangle that will fit around the Sprite Image.
	 */
	public Rectangle getBoundingBox( ) {
		return sprite.getBoundingRectangle( );
	}

	public float getWidth( ) {
		return sprite.getWidth( );
	}

	public float getHeight( ) {
		return sprite.getHeight( );
	}

	@Override
	public void dispose( ) {
		//Nothing to dispose here
	}
}