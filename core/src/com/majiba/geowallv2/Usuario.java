package com.majiba.geowallv2;

import com.badlogic.gdx.Gdx;




public class Usuario implements java.io.Serializable{

	private String nick="";
    private int puntuacion;
    private int id;
    boolean sonido;
    public Usuario(){
    	
    	
    	//Lectura del archivo
    	boolean existe = Gdx.files.local("archivo.bin").exists();
    	if(existe){
    		Alasaca guardar = new Alasaca();
    		System.out.println("EL archivo existe");
    		nick = guardar.leer().nick;
    		id= guardar.leer().id;
    		//id=25;
    		sonido=	guardar.leer().sonido;
    		//Para pruebas
    		puntuacion = guardar.leer().puntuacion;
    		
    		//Debo acer la comprobacion para los intentos
    	}else{    		
    		nick ="missing";
    		id=0;  
    		puntuacion =0;  		
        	sonido=true;
        	guardar();        
    	}
    	

    	
    }
    public Usuario(String nick,int puntu){
    	this.nick= nick;
    	this.puntuacion= puntu;
    	
    }
    
    
 	public String getNick() {
		return nick;
	}



	public void setNick(String nick) {
		this.nick = nick;
	}

	public void guardar() {
		Alasaca guardar = new Alasaca();
		guardar.escribir(this);
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isSonido() {
		return sonido;
	}


	public void setSonido(boolean sonido) {
		this.sonido = sonido;
	}


	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

    
}

