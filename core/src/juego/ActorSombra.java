package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorSombra extends Actor implements Disposable{

	private TextureRegion sombra;//=new Sprite(new Texture("Imagenes/figuras.png"), 0, 0, 256, 256);
	private float v_fondo=2;//0.5f;
	private float valory=Gdx.graphics.getHeight();
	private int valorsombra=0;
	
	public ActorSombra(int valor){
		sombra=obtenersombra(valor);
		this.valorsombra=valor;
	}
	
	public TextureRegion obtenersombra(int valorfigura){
		int tamanofiguras= 128;
		int valorx=valorfigura*128;
		sombra = new Sprite(new Texture("Imagenes/figuras.png"), valorx, 0, tamanofiguras, tamanofiguras);		
		return sombra;		
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);		
		valory=valory-v_fondo;
		batch.draw(sombra,Gdx.graphics.getWidth()/3, valory);
		//Color col = getColor();
		//batch.setColor(1, 1, 1, col.a*parentAlpha);		
		
		
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
	public void cambiarsombra(int valor){
		//this.figura.scroll(256, 0);
		this.valorsombra=valor;
		this.sombra.setRegion(valor*128, 0, 128, 128);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub	
		new Texture("Imagenes/figuras.png").dispose();
	}

	public TextureRegion getSombra() {
		return sombra;
	}

	public void setSombra(TextureRegion sombra) {
		this.sombra = sombra;
	}

	public int getValorsombra() {
		return valorsombra;
	}

	public void setValorsombra(int valorsombra) {
		this.valorsombra = valorsombra;
	}
}
