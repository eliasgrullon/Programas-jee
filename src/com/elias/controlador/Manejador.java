package com.elias.controlador;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.elias.logicanegocio.ProcesarClientes;
import com.elias.model.Cliente;



@ManagedBean
@RequestScoped
public class Manejador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String apellido;

	private String cedula;

	private String nombre;
	private String tipoSeguroMedico;
	
	private String criterio;
	
	private ProcesarClientes procesador;
	
	private boolean buscando = false;
	private String[] tipos;

	public String[] getTipos() {
		return tipos;
	}
	
	public Manejador(){
		
		procesador = ProcesarClientes.getInstancia();
		tipos = new String[4];
		tipos[0] = "ARS universal";
		tipos[1] = "ARS palic salud";
		tipos[2] = "ARS todos Unidos";
		tipos[3] = "ARS Familia";
	}
	
	public String procesarNuevoCliente(){
		
		
		if(procesador.agregarCliente(new Cliente(id,apellido,cedula,nombre,tipoSeguroMedico))){
			
			System.out.println("tamo aqui");
			return "Index.xhtml?faces-redirect=true";
		}
			
		System.out.println("tamo fuera");
		return null;
	}
	

	
	public String eliminarCliente(String id){
		
		
		
		procesador.eliminarCliente(procesador.getCliente(id));
		
		return "Index";
		
	}
	
	public String nuevo(){
		
		return "Nuevo.xhtml?faces-redirect=true";
	}
	
	public String buscar(){
		
		if(criterio.trim().equals("")){
			return "Index";
		}
		else{
			
			buscando = true;
		}
		return "";
	}
	
	public String modificarCliente(){
		
		if(procesador.modificarCliente(new Cliente(id,apellido,cedula,nombre,tipoSeguroMedico))){
			
			System.out.println("tamo aqui");
			return "Index.xhtml?faces-redirect=true";
		}
			
		System.out.println("tamo fuera");
		return null;
		
	}
	
	public String asignarCLiente(String id){
		
		 Cliente cls = procesador.getCliente(id);
		 
		 
		 System.out.println(cls.getApellido() + "por aqui");
		   this.id = cls.getId();
		   nombre = cls.getNombre();
		   apellido = cls.getApellido();
		   cedula = cls.getCedula();
		   tipoSeguroMedico = cls.getTipoSeguroMedico();
		   
		   return "Modificar.xhtml";
		 
	}
	
	public ArrayList<Cliente> obtenerResultado(){
		
		System.out.println(buscando);
		buscando = false;
		return procesador.buscarCliente(criterio);
	}
	
	 public ArrayList<Cliente> getClientes(){
		  
		  System.out.println("pase por aqui");
			
			return procesador.getClientes();
		}

	
	public String cancelar(){
		
		return "Index.xhtml?faces-redirect=true";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoSeguroMedico() {
		return tipoSeguroMedico;
	}

	public void setTipoSeguroMedico(String tipoSeguroMedico) {
		this.tipoSeguroMedico = tipoSeguroMedico;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public boolean isBuscando() {
		return buscando;
	}

	public void setBuscando(boolean buscando) {
		this.buscando = buscando;
	}

	
	
	

}
