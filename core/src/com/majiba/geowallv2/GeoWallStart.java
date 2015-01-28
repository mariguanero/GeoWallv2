package com.majiba.geowallv2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GeoWallStart extends Game {
	SpriteBatch batch;
	Texture figuras;
	TextureRegion miFigura;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		figuras = new Texture("Imagenes/figuras.png");
		miFigura = new Sprite(figuras, 0, 256, 256, 256);
		//miFigura.setPosition(0, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(miFigura, Gdx.graphics.getWidth()/3, 0, Gdx.graphics.getWidth()/3, Gdx.graphics.getWidth()/3);
		//batch.draw(img, 0, 0);
		batch.end();
	}
}
