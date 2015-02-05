package internet;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.majiba.geowallv2.Usuario;




public class Conexion {

	public String status;
	
	public Conexion() {
		this.status="";
		//guardarRegistros();
		//leerRegistros();
	}

	
	//guardarRegistros
	public void guardarRegistros(){
		// TODO Auto-generated constructor stub
		
			//System.out.println("llega a conexion");
			final Usuario usu= new Usuario();
			
			
			HttpRequest httpPost = new HttpRequest(HttpMethods.GET);			          
			String url="http://majiba.com/geowall/escribirdatos.php?nick="+usu.getNick()+"&puntuacion=" +
					usu.getPuntuacion()+"&id="+usu.getId();
			System.out.println(url);
			httpPost.setUrl(url);
			httpPost.setHeader("Content-Type", "application/json");
			
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
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
}
	