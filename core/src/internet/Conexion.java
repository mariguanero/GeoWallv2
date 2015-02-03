package internet;

//import com.badlogic.gdx.ApplicationAdapter;

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
import com.majiba.geowallv2.Usuario;




public class Conexion {

	//BitmapFont font;
	//SpriteBatch batch;
	//String url, mensaje="Registrando Puntaje.. espere";
	//String httpMethod = Net.HttpMethods.GET;
	//String solicitud_variables = null;
	//HttpRequest httpsolicitud;
	public String status;
	public Array<String> nicks= new Array(), puntu= new Array();
	
	public Conexion() {
		this.status="";
		//guardarRegistros();
		leerRegistros();
	}

	
	public String leerRegistros(){
		// TODO Auto-generated constructor stub
		
			//System.out.println("llega a conexion");
		
			//teamview(compartir escritorio)
			HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
			httpPost.setUrl("http://majiba.com/config1.php");
			httpPost.setHeader("Content-Type", "application/json");
			String output = "";
			
			Gdx.net.sendHttpRequest (httpPost, new HttpResponseListener() {
			

			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				// TODO Auto-generated method stub
				Json jso= new Json();
				status = httpResponse.getResultAsString();
				
				Array highScores = jso.fromJson(Array.class, status);
				
				for(int i=0; i< highScores.size;i++){
					//System.out.println(highScores.get(i));
					//Json Ayudajson = new Json();
					String ayudastring = ""+highScores.get(i);
					int juas = ayudastring.indexOf("puntuacionmax")-2 ;
					//System.out.println(juas);
					String nick1 = ayudastring.substring(8, juas);
					String mxpuntuacion= ayudastring.substring(juas+17, ayudastring.length()-2);
					
					
					nicks.add(nick1);
					puntu.add(mxpuntuacion);
									
				}
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
			//System.out.println(status);
			return status;
	}
	
	
	
	
	//guardarRegistros
	public void guardarRegistros(){
		// TODO Auto-generated constructor stub
		
			//System.out.println("llega a conexion");
			final Usuario usu= new Usuario();
			
			
			HttpRequest httpPost = new HttpRequest(HttpMethods.GET);			          
			String url="http://majiba.com/geowall/config2.php?nick="+usu.getNick()+"&puntuacion=" +
					usu.getPuntuacion()+"&id="+usu.getId();
			System.out.println(url);
			httpPost.setUrl(url);
			httpPost.setHeader("Content-Type", "application/json");
			String output = "";
			
			Gdx.net.sendHttpRequest (httpPost, new HttpResponseListener() {
			
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				// TODO Auto-generated method stub
				status = httpResponse.getResultAsString();				
				int newid= Integer.parseInt(status);
				usu.setId(newid);
				usu.guardar();

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


	public String getStatus() {
		leerRegistros();
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Array<String> getNicks() {
		leerRegistros();
		return nicks;
	}


	public void setNicks(Array<String> nicks) {
		this.nicks = nicks;
	}


	public Array<String> getPuntu() {
		leerRegistros();
		return puntu;
	}


	public void setPuntu(Array<String> puntu) {
		this.puntu = puntu;
	}
	
	
	
	
	
	
}
	