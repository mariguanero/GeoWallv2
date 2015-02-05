package juego;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.majiba.geowallv2.GeoWallStart;
import com.majiba.geowallv2.Pantalla;
import com.majiba.geowallv2.Usuario;
import internet.Conexion;

public class PantallaJuego extends Pantalla {

	public Stage escenario;
	private ActorFondo fondo; 
	private ActorSombra sombra;
	private ActorFigura figura;
	//contf es un contador de figuras-- nivel es el nivel cuyo maximo sera 15
	private int contf=0, nivel=0, puntos=13, contn=0;
	
	//Sonido del cambio
	private Sound change, finmuro, error;
	
	//TextField puntoss
	private TextField puntoss;
	Skin skin = new Skin(Gdx.files.internal("Textos/uiskin.json"));
	
	
	public PantallaJuego(GeoWallStart game) {
		super(game);
		// TODO Auto-generated constructor stub
		escenario = new Stage(new ScreenViewport());
		change = Gdx.audio.newSound(Gdx.files.internal("Sonido/change.mp3"));
		finmuro = Gdx.audio.newSound(Gdx.files.internal("Sonido/final-muro.mp3"));
		error = Gdx.audio.newSound(Gdx.files.internal("Sonido/fallo.mp3"));
		fondo= new ActorFondo();
		sombra= new ActorSombra(0);
		sombra.setColor(0,0,0,1);
		
		figura = new ActorFigura(0);
		
		escenario.addActor(fondo);
		escenario.addActor(sombra);
		escenario.addActor(figura);
		
		//Para la puntuacion
		
		puntoss= new TextField(""+puntos, skin);

		puntoss.setWidth(Gdx.graphics.getWidth()/8);
		puntoss.setAlignment(Align.right);
		puntoss.setColor(0, 0, 0, 0.15f);
		puntoss.setPosition(Gdx.graphics.getWidth()-puntoss.getWidth(), Gdx.graphics.getHeight()-puntoss.getHeight());
		escenario.addActor(puntoss);
		//Fin puntuacion
		
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
			contn++;
			if(sombra.getValorsombra()==figura.getValorfigura()){
				acerto();
			}else{
				fallo();
			}
			System.out.println(puntos);
			controlapunt();
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
				if(contf>nivel){
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
	
	public void fallo(){
		fondo.setValory(Gdx.graphics.getHeight());
		escenario.getActors().removeValue(sombra, true);
		escenario.getActors().removeValue(fondo, true);
		escenario.getActors().removeValue(figura, true);
		sombra.dispose();
		figura.dispose();
		fondo.dispose();
		error.dispose();
		Usuario usu = new Usuario();
		if(usu.getPuntuacion()<puntos){
			usu.setPuntuacion(puntos);	
			usu.guardar();
		}
		Conexion con = new Conexion();
		con.guardarRegistros();
	}
	public void acerto(){
		finmuro.play();
		this.puntos+= nivel*13;
		
		if(contn>nivel){
			nivel++;
			contn=0;
			System.out.println("Sube de nivel");
			subenivel();
		}
		System.out.println("Nivel "+ nivel +" puntos "+puntos);
		int nAle = (int)MathUtils.random(nivel);
		sombra.cambiarsombra(nAle);
		fondo.setValory(Gdx.graphics.getHeight());			
		sombra.setValory(Gdx.graphics.getHeight());
	}

	public void subenivel(){
				
		TextField textsub= new TextField("level UP!!", skin);
		textsub.setColor(0, 0, 0, 0);
		escenario.getActors().removeValue(textsub, true);
		textsub.addAction(
			sequence(
				delay(0 * 0.5f), 
				parallel(
					moveBy(0, Gdx.graphics.getHeight(), 2),										
					fadeOut(0)					
				)																
			)			
		);		
		escenario.addActor(textsub);		
		textsub.setPosition(Gdx.graphics.getWidth()/2-textsub.getWidth()/2 , Gdx.graphics.getHeight()/2-textsub.getHeight()/2);					
	}

	public void controlapunt(){
		puntoss.setText(""+puntos);
	}

}
