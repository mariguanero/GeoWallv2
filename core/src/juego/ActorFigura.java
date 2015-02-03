package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorFigura extends Actor implements Disposable{

	private TextureRegion figura;//=new Sprite(new Texture("Imagenes/figuras.png"), 0, 0, 256, 256);
	
	
	private int valorfigura=0;
	
	public ActorFigura(int valor){
		figura=obtenersombra(valor);
		this.valorfigura=valor;
	}
	
	public TextureRegion obtenersombra(int valorfigura){
		int tamanofiguras= 256;		
		int valorx=valorfigura*256;
		figura = new Sprite(new Texture("Imagenes/figuras1.png"), valorx, 0, tamanofiguras, tamanofiguras);		
		return figura;		
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a*parentAlpha);		
		
		batch.draw(figura,Gdx.graphics.getWidth()/3, 0);
		//Color col = getColor();
		//batch.setColor(1, 1, 1, col.a*parentAlpha);		
		
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		new Texture("Imagenes/figuras1.png").dispose();
	}

	public void cambiarfigura(int valor){
		//this.figura.scroll(256, 0);		
		this.figura.setRegion(valor*256, 0, 256, 256);
		this.valorfigura=valor;
	}
	public TextureRegion getFigura() {
		return figura;
	}

	public void setFigura(TextureRegion figura) {
		this.figura = figura;
	}

	public int getValorfigura() {
		return valorfigura;
	}

	public void setValorfigura(int valorfigura) {
		this.valorfigura = valorfigura;
	}
}
