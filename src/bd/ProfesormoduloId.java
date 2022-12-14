package bd;
// Generated 19 oct. 2022 13:11:23 by Hibernate Tools 6.0.2.Final

/**
 * ProfesormoduloId generated by hbm2java
 */
public class ProfesormoduloId implements java.io.Serializable {

	private int idModulo;
	private int idProfesor;

	public ProfesormoduloId() {
	}

	public ProfesormoduloId(int idModulo, int idProfesor) {
		this.idModulo = idModulo;
		this.idProfesor = idProfesor;
	}

	public int getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public int getIdProfesor() {
		return this.idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProfesormoduloId))
			return false;
		ProfesormoduloId castOther = (ProfesormoduloId) other;

		return (this.getIdModulo() == castOther.getIdModulo()) && (this.getIdProfesor() == castOther.getIdProfesor());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdModulo();
		result = 37 * result + this.getIdProfesor();
		return result;
	}

	@Override
	public String toString() {
		return "ProfesormoduloId [idModulo=" + idModulo + ", idProfesor=" + idProfesor + "]";
	}

}
