package com.elias.logicanegocio;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.elias.model.Cliente;

public class ProcesarClientes {

	
	SessionFactory sf;
	
	private static ProcesarClientes procesar;
	
	private ProcesarClientes(){
		
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	
	public static synchronized ProcesarClientes getInstancia(){
		
		return(procesar == null)?procesar = new ProcesarClientes():procesar;
	}
	
	public boolean agregarCliente(Cliente cliente){
		
		Session  sesion = sf.openSession();
		
		try{
			sesion.beginTransaction();
			sesion.save(cliente);
			sesion.getTransaction().commit();
		}catch( HibernateException e){
			return false;
		}finally{
			sesion.close();
			
		}
		
		return true;
	}
	
	public boolean modificarCliente(Cliente cliente){
		Session  sesion = sf.openSession();
		
		try{
			sesion.beginTransaction();
			sesion.update(cliente);
			sesion.getTransaction().commit();
		}catch( HibernateException e){
			return false;
		}finally{
			sesion.close();
			
		}
		return true;
	}
	
	public boolean eliminarCliente(Cliente cliente){
		
		Session  sesion = sf.openSession();
		
		try{
			sesion.beginTransaction();
		
			sesion.delete(cliente);
			sesion.getTransaction().commit();
		}catch( HibernateException e){
			
		}finally{
			sesion.close();
			
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> getClientes(){
		Session  sesion = sf.openSession();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try{
			sesion.beginTransaction();
			Query query = sesion.createQuery("from Cliente");
			
			clientes = (ArrayList<Cliente>) query.list();
			sesion.getTransaction().commit();
		}catch( HibernateException e){
			
		}finally{
			sesion.close();
			
		}
		
		return clientes;
	}
	
	public Cliente getCliente(String id){
		Session  sesion = sf.openSession();
		Cliente cliente = new Cliente();
		try{
			sesion.beginTransaction();
			cliente = (Cliente)sesion.get(Cliente.class, Integer.valueOf(id));
			sesion.getTransaction().commit();
		}catch( HibernateException e){
			e.printStackTrace();
		}finally{
			sesion.close();
			
		}
		
		return cliente;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> buscarCliente(String criteria){
		Session  sesion = sf.openSession();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try{
			sesion.beginTransaction();
			Query query = sesion.createQuery("from Cliente where nombre like '"+criteria+ "'");
			
			clientes = (ArrayList<Cliente>) query.list();
			sesion.getTransaction().commit();
		}catch( HibernateException e){
			e.printStackTrace();
		}finally{
			sesion.close();
			
		}
		
		return clientes;
	}
	
	
}
