package bd;
// Generated 19 oct. 2022 13:11:23 by Hibernate Tools 6.0.2.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Modulo generated by hbm2java
 */
public class Modulo implements java.io.Serializable {

	private int idModulo;
	private Cicloformativo cicloformativo;
	private String nombre;
	private Integer numeroHoras;
	private Set<Profesormodulo> profesormodulos = new HashSet<Profesormodulo>(0);

	public Modulo() {
	}

	public Modulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public Modulo(int idModulo, Cicloformativo cicloformativo, String nombre, Integer numeroHoras) {
		this.idModulo = idModulo;
		this.cicloformativo = cicloformativo;
		this.nombre = nombre;
		this.numeroHoras = numeroHoras;
	}

	public Modulo(int idModulo, Cicloformativo cicloformativo, String nombre, Integer numeroHoras,
			Set<Profesormodulo> profesormodulos) {
		this.idModulo = idModulo;
		this.cicloformativo = cicloformativo;
		this.nombre = nombre;
		this.numeroHoras = numeroHoras;
		this.profesormodulos = profesormodulos;
	}

	public int getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public Cicloformativo getCicloformativo() {
		return this.cicloformativo;
	}

	public void setCicloformativo(Cicloformativo cicloformativo) {
		this.cicloformativo = cicloformativo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroHoras() {
		return this.numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public Set<Profesormodulo> getProfesormodulos() {
		return this.profesormodulos;
	}

	public void setProfesormodulos(Set<Profesormodulo> profesormodulos) {
		this.profesormodulos = profesormodulos;
	}

	@Override
	public String toString() {
		return "\nM" + idModulo + " " + nombre + ", " + numeroHoras + " horas";
	}

}
