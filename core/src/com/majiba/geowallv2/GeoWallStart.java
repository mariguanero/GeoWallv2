package com.majiba.geowallv2;

import juego.PantallaJuego;
import ui.PantallaUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GeoWallStart extends Game {
	SpriteBatch batch;
	Texture figuras;
	TextureRegion miFigura;
	public Music musica;
	public Pantalla ui =new PantallaUI(this);
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	
		setScreen(ui);
		musica = Gdx.audio.newMusic(Gdx.files.internal("Sonido/LOOP NOLO.mp3"));
		//musica.play();
		//musica.setLooping(true);
		
		/*Para pruebas*/
		//setScreen(new PantallaJuego(this));
	}

	@Override
	public void render () {
		super.render();
		
	}

	@Override
	public void setScreen(Screen screen) {
		// TODO Auto-generated method stub
		super.setScreen(screen);
		System.out.println("hola setScreen"+screen);
		
	}
}
