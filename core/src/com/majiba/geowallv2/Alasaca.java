package com.majiba.geowallv2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Alasaca {
	private FileHandle handle;
	private Usuario usu;
	//LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	


	
	public Alasaca(){
		handle = Gdx.files.local("archivo.bin");
		
		//leer();
	}
	
	
	public void escribir(Usuario user){
    	ByteArrayOutputStream out = null;
    	ObjectOutputStream oos= null;
    	
    	try{
    		out = new ByteArrayOutputStream();
    		oos = new ObjectOutputStream(out);
    		
    		usu= user;
    		oos.writeObject(usu);
    		
    		oos.flush();
    		
    		byte[] datos= out.toByteArray();
    		handle.writeBytes(datos, false);
    		
    		System.out.println("ha Guardado el usuario en el archivo");
    		
    	}catch(IOException e){
    		e.printStackTrace();
    		System.out.println("No ha guardado el usuario");
    		
    	}
    }
    
    public Usuario leer() {
    	ByteArrayInputStream in = null;
    	ObjectInputStream ois = null;
    	
    	try {
    		in = new ByteArrayInputStream(handle.readBytes());
    		ois= new ObjectInputStream(in);
    		
    		try {
				usu = (Usuario) ois.readObject();
				//System.out.println("Ha leido al usuario del archivo");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				usu = new Usuario();
				System.out.println("en Teoria a creado un usuario nuevo");
				System.out.println(usu);
			}
			
    		
    		
    		
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		return usu;
    }
	
	

}
