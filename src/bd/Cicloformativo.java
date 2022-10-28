package bd;
// Generated 19 oct. 2022 13:11:23 by Hibernate Tools 6.0.2.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Cicloformativo generated by hbm2java
 */
public class Cicloformativo implements java.io.Serializable {

	private int idCiclo;
	private String nombreCiclo;
	private Integer horas;
	private Set<Modulo> modulos = new HashSet<Modulo>(0);

	public Cicloformativo() {
	}

	public Cicloformativo(int idCiclo) {
		this.idCiclo = idCiclo;
	}

	public Cicloformativo(int idCiclo, String nombreCiclo, Integer horas, Set<Modulo> modulos) {
		this.idCiclo = idCiclo;
		this.nombreCiclo = nombreCiclo;
		this.horas = horas;
		this.modulos = modulos;
	}

	public int getIdCiclo() {
		return this.idCiclo;
	}

	public void setIdCiclo(int idCiclo) {
		this.idCiclo = idCiclo;
	}

	public String getNombreCiclo() {
		return this.nombreCiclo;
	}

	public void setNombreCiclo(String nombreCiclo) {
		this.nombreCiclo = nombreCiclo;
	}

	public Integer getHoras() {
		return this.horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Set<Modulo> getModulos() {
		return this.modulos;
	}

	public void setModulos(Set<Modulo> modulos) {
		this.modulos = modulos;
	}

	@Override
	public String toString() {
		return "CF" + idCiclo + " " + nombreCiclo + ", " + horas + "h";
	}

}
