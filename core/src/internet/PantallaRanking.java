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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
import com.majiba.geowallv2.Usuario;

public class PantallaRanking extends Pantalla {

	private Stage escenariopuntuacion;
	private Button btnVolv;
	
	private String status;
	
	private Array<String> nicks= new Array<String>(), puntu= new Array<String>();
	private int position=0;
	Table tblLayout = new Table();

	
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
		controlarescenario();

		escenariopuntuacion.act();
		escenariopuntuacion.draw();
		
	}

	public void controlarescenario(){		
		//this.tblLayout.moveBy(0, position);
		//System.out.println(position);
		
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
		tlnick.setFontScale(2);
		tlpunt.setFontScale(1.5f);
		tlnick.setColor(0.5f, 0.1f, 0.1f, 1);
		tlpunt.setColor(0.5f, 0.1f, 0.1f, 1);
		
		// Posicionamos los elementos en una tabla.
			
			final int h = (int) tlnick.getHeight(), sep = Gdx.graphics.getWidth()/2;
						
			int w=(int) tlpunt.getWidth();
			tblLayout.add(tlnick).spaceRight(sep);			
			tblLayout.add(tlpunt).width(w).height(h).row();
			//Para cambiar la vista del usuario
			Usuario usuaux= new Usuario();
			
			Label label_aux_ni =new Label("", skin);
			
			Label label_aux_pu = new Label("", skin);
			for(int i=0; i<nicks.size;i++){
				//Para el cambio de color;
				float verde= 1;
				float rojo= 0;
				float azul=0;
				if(nicks.size*0.5f<i){
					rojo=1;
					verde=0;
				}
				if(nicks.size*0.2f< i && i< nicks.size*0.5f){
					rojo=1;
					verde=1;
				}
				//if (nicks.size)
				//if(nicks.size*0.5f>i)
				label_aux_ni =new Label(nicks.get(i),skin);
				
				label_aux_pu = new Label(puntu.get(i),skin);
				label_aux_ni.setColor(rojo, verde, azul, 0.5f);
				label_aux_pu.setColor(rojo, verde, azul, 0.5f);

				if(nicks.get(i).equalsIgnoreCase(usuaux.getNick()) && Integer.parseInt(puntu.get(i))==usuaux.getPuntuacion()){
					position=i;
					//System.out.println("reconoce que es igual");
					label_aux_ni.setFontScale(1.8f);
					tblLayout.add(label_aux_ni).spaceRight(sep);
					
					label_aux_pu.setFontScale(1.8f);
					tblLayout.add(label_aux_pu).width(w).height(h).spaceRight(sep).row();
				}else{
					tblLayout.add(label_aux_ni).spaceRight(sep);					
					tblLayout.add(label_aux_pu).width(w).height(h).spaceRight(sep).row();
				}
			}
			
		
			//
			tblLayout.top().setY(-Gdx.graphics.getHeight()/2);
			//Para Controlar el Boton de Volver
			escenariopuntuacion = new Stage(new ScreenViewport());
			tblLayout.setFillParent(true);
			escenariopuntuacion.addActor(tblLayout);
			escenariopuntuacion.addActor(btnVolv);
			//controlarescenario();
			//System.out.println("position: "+position+" Separacion: "+ h+" Producto : " +h*position);
			tblLayout.addAction(Actions.moveTo(0,  position*h-(Gdx.graphics.getHeight()/3), 5));
			
			//MoveByAction move= new MoveByAction();
			//move.setActor(tblLayout);
			//move.setDuration(20);
			//move.setTime(5);
			/*this.(
					moveBy(0, position*sep, 2) 
					//fadeOut(duracion)
				)
*/

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
					String mxpuntuacion= ayudastring.substring(juas+16, ayudastring.length()-2);
					
					
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
	