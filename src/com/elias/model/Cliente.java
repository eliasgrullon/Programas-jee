package com.elias.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Clientes database table.
 * 
 */
@Entity
@Table(name="Clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String apellido;

	private String cedula;

	private String nombre;
	

	@Column(name="tipo_seguro_medico")
	private String tipoSeguroMedico;

	public Cliente() {
	}
	
	

	public Cliente(int id, String apellido, String cedula, String nombre,
			String tipoSeguroMedico) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.cedula = cedula;
		this.nombre = nombre;
		this.tipoSeguroMedico = tipoSeguroMedico;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoSeguroMedico() {
		return this.tipoSeguroMedico;
	}

	public void setTipoSeguroMedico(String tipoSeguroMedico) {
		this.tipoSeguroMedico = tipoSeguroMedico;
	}

}