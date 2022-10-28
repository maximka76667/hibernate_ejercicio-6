package bd;
// Generated 19 oct. 2022 13:11:23 by Hibernate Tools 6.0.2.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Profesor generated by hbm2java
 */
public class Profesor implements java.io.Serializable {

	private int id;
	private String nombre;
	private String ape1;
	private String ape2;
	private Integer tipoFuncionario;
	private Direccion direccion;
	private Set profesormodulos = new HashSet(0);
	private Set correoelectronicos = new HashSet(0);

	public Profesor() {
	}

	public Profesor(int id) {
		this.id = id;
	}

	public Profesor(int id, String nombre, String ape1, String ape2, Integer tipoFuncionario, Direccion direccion,
			Set profesormodulos, Set correoelectronicos) {
		this.id = id;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.tipoFuncionario = tipoFuncionario;
		this.direccion = direccion;
		this.profesormodulos = profesormodulos;
		this.correoelectronicos = correoelectronicos;
	}

	public Profesor(int id, String nombre, String ape1, String ape2, Integer tipoFuncionario) {
		this.id = id;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.tipoFuncionario = tipoFuncionario;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApe1() {
		return this.ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return this.ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	public Integer getTipoFuncionario() {
		return this.tipoFuncionario;
	}

	public void setTipoFuncionario(Integer tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Set getProfesormodulos() {
		return this.profesormodulos;
	}

	public void setProfesormodulos(Set profesormodulos) {
		this.profesormodulos = profesormodulos;
	}

	public Set getCorreoelectronicos() {
		return this.correoelectronicos;
	}

	public void setCorreoelectronicos(Set correoelectronicos) {
		this.correoelectronicos = correoelectronicos;
	}

	@Override
	public String toString() {
		return "\nP" + id + " " + nombre + " " + ape1 + " " + ape2 + ", " + tipoFuncionario + ", \nDireccion: "
				+ direccion.toStringSinProfesor() + "\n" + "Correos electronicos:" + correoelectronicos;
	}

	public String toStringSinDireccion() {
		return "P" + id + " " + nombre + " " + ape1 + " " + ape2 + ", " + tipoFuncionario + "\n"
				+ "Correos electronicos:" + correoelectronicos;
	}

	public String toStringConModulos() {
		return "P" + id + " " + nombre + " " + ape1 + " " + ape2 + ", " + tipoFuncionario + ", \nDireccion: "
				+ direccion.toStringSinProfesor() + "\nModulos: " + profesormodulos + "\n" + "Correos electronicos:"
				+ correoelectronicos;
	}

}
