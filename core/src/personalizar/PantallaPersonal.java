package personalizar;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.majiba.geowallv2.GeoWallStart;
import com.majiba.geowallv2.Pantalla;
import com.majiba.geowallv2.Usuario;


public class PantallaPersonal extends Pantalla{
		
	private Stage escenariopciones;
	
	Button btnVolv,btnGuardar; 
	TextArea tanick;	
	CheckBox chcksonido, chckvibracion;
	Table tblLayout = new Table();
	
	Usuario user;

	// TODO Auto-generated constructor stub
	public PantallaPersonal(GeoWallStart game) {
			super(game);
			user= new Usuario();
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenariopciones.act();
		escenariopciones.draw();
		
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		
		Skin skin = new Skin(Gdx.files.internal("Textos/uiskin.json"));
		int miniheight= Gdx.graphics.getHeight()/8;
		int mitad= Gdx.graphics.getWidth()/2;
		//int miniwidth= Gdx.graphics.getWidth()/8;
		
		/*Botones*/
		btnVolv = new TextButton("Back", skin);
		btnVolv.setPosition(0, 0);
		btnVolv.setWidth(Gdx.graphics.getWidth()/2);
		btnVolv.setHeight(miniheight);
		btnVolv.addListener(QueHacemos(false));
		btnGuardar = new TextButton("Save", skin);
		btnGuardar.setPosition(Gdx.graphics.getWidth()/2, 0);
		btnGuardar.setWidth(Gdx.graphics.getWidth()/2);
		btnGuardar.setHeight(miniheight);
		btnGuardar.addListener(QueHacemos(true));
		
		/*Tabla */
		//Puntuacion
		Label tapunt= new Label(""+user.getPuntuacion(),skin);
		tapunt.setAlignment(Align.center);
		tapunt.setX(Gdx.graphics.getWidth()/2-tapunt.getWidth()/2);
		tapunt.setY(miniheight*7);		
		tapunt.setFontScale(3);
		tapunt.setColor(0.9f,0.1f,0.1f,0.8f);
		
		//Definimos el label y el TextArea del nick
		Label tlnick= new Label("NICK: ",skin);
		tlnick.setX(mitad-13-tlnick.getWidth());
		tlnick.setY(miniheight*5);
		tanick= new TextArea(user.getNick(), skin);
		tanick.setX(mitad+13);
		tanick.setY(miniheight*5-tanick.getHeight()/5);		
		tanick.setAlignment(Align.bottom);
		tanick.setColor(1, 1, 1, -0.5f);
		
		//Checkbox		
		chcksonido= new CheckBox("Sound", skin);
		chcksonido.setX(mitad-chcksonido.getWidth()/2);
		chcksonido.setY(miniheight*3);
		chcksonido.left();
		//chcksonido.align(Align.right);
		chcksonido.setChecked(user.isSonido());
	
		//Escenario nuevo
		escenariopciones = new Stage(new ScreenViewport());

		//Le añadimos los distintos actores
		escenariopciones.addActor(tapunt);
		escenariopciones.addActor(tlnick);
		escenariopciones.addActor(tanick);
		escenariopciones.addActor(chcksonido);
			
			
		escenariopciones.addActor(btnVolv);
		escenariopciones.addActor(btnGuardar);
		Gdx.input.setInputProcessor(escenariopciones);
	}		
	
	
	
	
	//Intento de Volver
	public ChangeListener QueHacemos(final boolean valor){
		
		ChangeListener cambio= new ChangeListener() {
			
	
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(valor){
					
					btnGuardar.addAction(
						sequence(
							delay(2 * 0.5f),
						run (new Runnable(){
							public void run (){	
									
									System.out.println("sonido chcksonido "+chcksonido.isChecked());
									if(chcksonido.isChecked()){
										System.out.println("sta activada");
										game.musica.play();
									}else{
										System.out.println("sta desactivada");										
										game.musica.stop();										
									}
									//System.out.println("Vibracion chckvibracion "+chckvibracion.);
									
									user.setNick(tanick.getText().toString());
									user.setSonido(chcksonido.isChecked());
									
									//Controlo que pantalla se lanza
									
									user.guardar();
									game.setScreen(game.ui);																										
								}
							})
						)
					);
				}else{

					btnVolv.addAction(
						sequence(
							delay(2 * 0.5f),
						run (new Runnable(){
								public void run (){				
									//Controlo que pantalla se lanza
									game.setScreen(game.ui);																										
								}
							})
						)
					);
				}
				
		}
			
				
				
				
		};
		
		return cambio;
		
	}
	

		
}

