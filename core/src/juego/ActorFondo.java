package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorFondo extends Actor implements Disposable{

	private TextureRegion mifondo;
	private float v_fondo=2;//0.5f;
	private float valory=Gdx.graphics.getHeight();
	
	
	public ActorFondo(){
		mifondo=new TextureRegion(new Texture("Imagenes/muro.jpg"));			
		int tamanow=new Texture("Imagenes/muro.jpg").getWidth();		
		int tamanoh=new Texture("Imagenes/muro.jpg").getHeight();
		setSize(tamanow,tamanoh );
		
		//obtenersombra(this.valorfigura);
	}
		
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);		
		valory=valory-v_fondo;
		batch.draw(mifondo, 0, valory, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Color col = getColor();
		//batch.setColor(1, 1, 1, col.a*parentAlpha);		
		
		//batch.draw(sombra,Gdx.graphics.getWidth()/3, valory);
	}
	public TextureRegion getMifondo() {
		return mifondo;
	}

	public void setMifondo(TextureRegion mifondo) {
		this.mifondo = mifondo;
	}

	public float getV_fondo() {
		return v_fondo;
	}

	public void setV_fondo(float v_fondo) {
		this.v_fondo = v_fondo;
	}


	public float getValory() {
		return valory;
	}


	public void setValory(float valory) {
		this.valory = valory;
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub	
		new Texture("Imagenes/muro.jpg").dispose();
	}

	
	

}
