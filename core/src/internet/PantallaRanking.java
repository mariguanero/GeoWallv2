package internet;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.majiba.geowallv2.GeoWallStart;
import com.majiba.geowallv2.Pantalla;

public class PantallaRanking extends Pantalla {

	private Stage escenariopuntuacion;
	Button btnVolv,btnGuardar;
	
	String status;
	
	Array<String> nicks= new Array<String>(), puntu= new Array<String>();
	
	public PantallaRanking(GeoWallStart game) {
		super(game);
		leerRegistros();
	}
	



	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenariopuntuacion.act();
		escenariopuntuacion.draw();
	}




	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		Skin skin = new Skin(Gdx.files.internal("Textos/uiskin.json"));
		//leerRegistros();
		
		btnVolv = new TextButton("Volver", skin);
		btnVolv.setPosition(0, 0);
		btnVolv.setWidth(Gdx.graphics.getWidth()/2);
		btnVolv.setHeight(50);
		btnVolv.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {	
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
		});		
		
					
		//Definimos el label y el TextArea del nick
		Label tlnick= new Label("NICK",skin);
		Label tlpunt= new Label("Puntuacion",skin);
		
		tlnick.setColor(0.5f, 0.1f, 0.1f, 1);
		tlpunt.setColor(0.5f, 0.1f, 0.1f, 1);
		
		// Posicionamos los elementos en una tabla.
			
			final int h = (int) tlnick.getHeight(), sep = Gdx.graphics.getWidth()/2;
			Table tblLayout = new Table();
			int w=(int) tlpunt.getWidth();
			tblLayout.add(tlnick).spaceRight(sep);			
			tblLayout.add(tlpunt).width(w).height(h).row();
			for(int i=0; i<nicks.size;i++){
				
				tblLayout.add(new Label(nicks.get(i),skin)).spaceRight(sep);
				tblLayout.add(new Label(puntu.get(i),skin)).width(w).height(h).spaceRight(sep).row();				
			}
			
		
			

			//Para Controlar el Boton de Volver
			escenariopuntuacion = new Stage(new ScreenViewport());
			tblLayout.setFillParent(true);
			escenariopuntuacion.addActor(tblLayout);
			escenariopuntuacion.addActor(btnVolv);
			

			Gdx.input.setInputProcessor(escenariopuntuacion);
			
			
	}




	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
	
	
	public void leerRegistros(){
		// TODO Auto-generated constructor stub
		
			//System.out.println("llega a conexion");
		
			//teamview(compartir escritorio)
			HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
			httpPost.setUrl("http://majiba.com/geowall/leerdatos.php");
			httpPost.setHeader("Content-Type", "application/json");
			Gdx.net.sendHttpRequest (httpPost, new HttpResponseListener() {
			
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				
				// TODO Auto-generated method stub
				Json jso= new Json();
				status = httpResponse.getResultAsString();
				System.out.println(status);
				Array<?> highScores = jso.fromJson(Array.class, status);
				
				for(int i=0; i< highScores.size;i++){
					String ayudastring = ""+highScores.get(i);
					int juas = ayudastring.indexOf("puntuacionmax")-1 ;
					String nick1 = ayudastring.substring(8, juas);
					String mxpuntuacion= ayudastring.substring(juas+17, ayudastring.length()-2);
					
					
					nicks.add(nick1);
					puntu.add(mxpuntuacion);
									
				}
				
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run () {
						System.out.println(nicks.size);
						
					}
				});
			}

			@Override
			public void failed(Throwable t) {
				// TODO Auto-generated method stub		
			}

			@Override
			public void cancelled() {
				// TODO Auto-generated method stub		
			}
		});
	}
	
	//guardarRegistros
	
	
	//Para los botones de cambiar
public ChangeListener QueHacemos(final boolean valor){
		
		ChangeListener cambio= new ChangeListener() {
			
	
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(valor){
				
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
	