package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.majiba.geowallv2.GeoWallStart;
import com.majiba.geowallv2.Pantalla;

public class PantallaJuego extends Pantalla {

	public Stage escenario;
	private ActorFondo fondo; 
	private ActorSombra sombra;
	private ActorFigura figura;
	//contf es un contador de figuras-- nivel es el nivel cuyo maximo sera 15
	private int contf=0, nivel=10;
	
	//Sonido del cambio
	private Sound change;

	
	
	public PantallaJuego(GeoWallStart game) {
		super(game);
		// TODO Auto-generated constructor stub
		escenario = new Stage(new ScreenViewport());
		change = Gdx.audio.newSound(Gdx.files.internal("Sonido/change.mp3"));
		fondo= new ActorFondo();
		sombra= new ActorSombra(0);
		sombra.setColor(0,0,0,1);
		
		figura = new ActorFigura(0);
		
		escenario.addActor(fondo);
		escenario.addActor(sombra);
		escenario.addActor(figura);
		
		
		controloFigura();

	
		
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		renderizarJuego();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
	public void renderizarJuego(){
		
		Gdx.gl.glClearColor(0.7f, 1, 1, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.input.setInputProcessor(escenario);

		escenario.act();
		controlaFondo();
		escenario.draw();
	}
	
	public void controlaFondo(){		
		if(fondo.getValory()<=0){			
			int nAle = (int)MathUtils.random(nivel);
			sombra.cambiarsombra(nAle);
			fondo.setValory(Gdx.graphics.getHeight());			
			sombra.setValory(Gdx.graphics.getHeight());
			System.out.println("ha llegado abajo");
			//figura.getFigura().setRegion(256, 0, 256, 256);
			//figura.cambiarfigura(1);
			//figura.getFigura().s
		}
	}
	public void controloFigura(){
		System.out.println("hola es controlofigura");	
		escenario.addListener(new InputListener(){
			@Override
			//Cuando este es pulsado se realiza dicha accion
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//Actores.get(0).addAction(Actions.color(Color.GREEN, 1));
				contf++;
				if(contf>15){
					contf=0;
				}
				//cambio la figura
    			figura.cambiarfigura(contf);
    			//le pongo sonido 
    			change.play();
				return true;
			}			
		});	
	}

}
