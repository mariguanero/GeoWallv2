package menu;

import personalizar.PantallaPersonal;
import internet.PantallaRanking;
import juego.PantallaJuego;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.majiba.geowallv2.GeoWallStart;
import com.majiba.geowallv2.Pantalla;


public class PantallaUI extends Pantalla {
	
	public PantallaUI(GeoWallStart game) {
		super(game);
		// TODO Auto-generated constructor stub
		//System.out.println("hola");
	}


	//public Pantalla scene2d;
	private Stage escenariomenu;
	//private Button btnNewGame; 
	
	
	private Button btnNewGame, btnLoadGame, btnSettings, btnQuit;
	

			
	/*public PantallaUI(GeoWallStart game) {
		super(game);
		
	}*/
	
	@Override
	public void show() {
		Skin skin = new Skin(Gdx.files.internal("Textos/uiskin.json"));
		btnNewGame = new TextButton("START!!", skin);
		btnLoadGame = new TextButton("Option", skin);
		btnSettings = new TextButton("Ranking", skin);
		btnQuit = new TextButton("Quit", skin);
		
		// He incluido el ejemplo de los botones animados porque consideré
		// que sería el más divertido para todos. Realmente no hay código
		// en esta sesión, porque son sólo distintos snippets de código.
		
		
		
		// Posicionamos los elementos en una tabla.
		final int w = 300, h = 50, sep = 20;
		Table tblLayout = new Table();
		tblLayout.add(btnNewGame).width(w).height(h).space(sep).row();
		tblLayout.add(btnLoadGame).width(w).height(h).space(sep).row();
		tblLayout.add(btnSettings).width(w).height(h).space(sep).row();
		tblLayout.add(btnQuit).width(w).height(h).space(sep).row();
		
		// Al hacer clic en el botón nuevo juego, los botones se van.
		btnNewGame.addListener(pulsaboton(0));
		btnSettings.addListener(pulsaboton(1));
		btnQuit.addListener(pulsaboton(2));
		btnLoadGame.addListener(pulsaboton(3));
		
		escenariomenu = new Stage(new ScreenViewport());
		tblLayout.setFillParent(true);
		escenariomenu.addActor(tblLayout);
		Gdx.input.setInputProcessor(escenariomenu);
		//this.game.setScreen(scene2d);
		
		
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
		
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenariomenu.act();
		escenariomenu.draw();
		
		
		/*if(btnNewGame.isTouchable()){
			game.setScreen(game.scene2d);
		}*/				
		
	}
	 	
	@Override
	public void resize(int width, int height) {
		escenariomenu.getViewport().update(width, height);
	}
	
	//Elimino listener
	public void eliminolistener(){
		//Gdx.input.getInputProcessor()
	}
	
	
	//Aqui defino el efecto de desaparecer del menu,
	//es donde estará el meollo de la cuestion
	//
	public ChangeListener pulsaboton(final int valormenu){
		ChangeListener cambio= new ChangeListener() {
			//btnNewGame.addCaptureListener(new InputListener() {
	
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// Píxeles verticales que se desplazarán los botones. Movemos
				// el alto de la pantalla porque es matemáticamente imposible
				// que se sigan viendo los botones tras la animación.
				final int desplY = Gdx.graphics.getHeight();
				
				// Segundos de delay entre el movimiento de cada botón.
				final float espera = 0.1f;
				
				// Cuanto tiempo dura la animación del botón volando.
				final float duracion = 0.5f;
				
				final Pantalla pantallaR=new PantallaRanking(game);
				btnNewGame.addAction(
					sequence(
						delay(0 * espera), 
						parallel(
							moveBy(0, desplY, duracion),
							fadeOut(duracion)
						)
					)
				);			
				
				btnLoadGame.addAction(
					sequence(
						delay(2 * espera),
						parallel(
							moveBy(0, desplY, duracion), 
							fadeOut(duracion)
						)
					)
				);
				
				btnSettings.addAction(
					sequence(
						delay(3 * espera),
						parallel(
							moveBy(0, desplY, duracion), 
							fadeOut(duracion)
						)
					)
				);
				btnQuit.addAction(
					sequence(
						delay(2 * espera),
						parallel(
							moveBy(0, desplY, duracion), 
							fadeOut(duracion)
						),
						run (new Runnable(){
							public void run (){				
								//Controlo que pantalla se lanza
								switch(valormenu){
									case 0:
										Gdx.gl.glClearColor(0.7f, 1, 1, 0.5f);
										Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
										game.setScreen(new PantallaJuego(game));										
									break;
									case 1:																				
										game.setScreen(pantallaR);
									break;
									case 2:										
										Gdx.app.exit();
									break;
									
									case 3:
										game.setScreen(new PantallaPersonal(game));
									break;
									
									default:
										//game.setScreen(game.ui);									
								}
								
								
								
							}
						})
					)
				);
		}
			
				
				
				
		};
		
		return cambio;
		
	}

}