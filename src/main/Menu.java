package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import bd.*;

public class Menu {

	private Session session;
	private Scanner entrada;

	public Menu(Session session) {
		this.session = session;
		this.entrada = new Scanner(System.in);
	}

	// Funciones comunes

	public void close() {
		System.out.println("FIN");
	}

	public void show() {
		System.out.println("---Menu---");
		System.out.println("1. Mostrar un ciclo formativo");
		System.out.println("2. Mostrar un profesor");
		System.out.println("3. Mostrar un municipio");
		System.out.println("4. Mostrar todos los profesores");
		System.out.println("5. Mostrar todos los módulos");
		System.out.println("6. Añadir un profesor");
		System.out.println("7. Añadir un modulo");
		System.out.println("8. Añanir un ciclo");
		System.out.println("0. Finalizar");
	}

	public void start() {
		while (true) {
			show();
			int option = askInteger("Elige opción del menu: ");
			switch (option) {
			case 1:
				showCicloFormativo();
				break;

			case 2:
				showProfesor();
				break;

			case 3:
				showMunicipio();
				break;

			case 4:
				showProfesores();
				break;

			case 5:
				showModulos();
				break;

			case 6:
				addProfesor();
				break;

			case 7:
				addModulo();
				break;

			case 8:
				addCicloFormativo();
				break;

			default:
				close();
				return;
			}
		}

	}

	public int askInteger(String message) {
		System.out.print(message);
		int number = entrada.nextInt();
		entrada.nextLine();
		return number;
	}

	public String askString(String message) {
		System.out.print(message);
		return entrada.nextLine();
	}

	public BigDecimal askBigDecimal(String message) {
		System.out.print(message);
		return entrada.nextBigDecimal();
	}

	public <T> List<T> getQueryList(String queryString, Class<T> queryType) {
		TypedQuery<T> query = session.createQuery(queryString, queryType);
		return query.getResultList();
	}

	public <T, K> List<T> getQueryList(String queryString, K parameter, Class<T> queryType) {
		TypedQuery<T> query = session.createQuery(queryString + " :parameter", queryType);
		query.setParameter("parameter", parameter);
		return query.getResultList();
	}

	public <T> void showOptions(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("  " + i + ". " + list.get(i));
		}
	}

	public <T> T handleTransaction(T entidad, String type) {
		session.beginTransaction();
		switch (type) {
		case "s":
			save(entidad);
			break;

		case "d":
			delete(entidad);
			break;

		case "u":
			update(entidad);
			break;

		default:
			break;
		}
		session.getTransaction().commit();
		return entidad;
	}

	public <T> void save(T entidad) {
		session.save(entidad);
	}

	public <T> void delete(T entidad) {
		session.delete(entidad);
	}

	public <T> void update(T entidad) {
		session.update(entidad);
	}

	// ***Funciones comunes***

	public void showCicloFormativo() {
		// 1a
		int idCiclo = askInteger("Id ciclo: ");

		Cicloformativo ciclo = session.get(Cicloformativo.class, idCiclo);

		// Comprobar si el ciclo existe
		if (ciclo == null) {
			System.out.println("Ciclo no encontrado");
			return;
		}

		System.out.println(ciclo);

		// 1b
		List<Modulo> modulos = new ArrayList<Modulo>(ciclo.getModulos());

		// 1i
		for (Modulo modulo : modulos) {
			System.out.println(modulo);
			List<Profesormodulo> profesoresmodulos = new ArrayList<Profesormodulo>(modulo.getProfesormodulos());
			List<Profesor> profesores = new ArrayList<Profesor>();

			for (Profesormodulo profesoresmodulo : profesoresmodulos) {
				profesores.add(profesoresmodulo.getProfesor());
			}

			System.out.println(profesores);
		}

	}

	// 2
	public void showProfesor() {
		int idProfesor = askInteger("Id profesor: ");
		Profesor profesor = session.get(Profesor.class, idProfesor);

		if (profesor == null) {
			System.out.println("Profesor no encontrado");
			return;
		}

		System.out.println(profesor.toStringConModulos());
	}

	// 3
	public void showMunicipio() {
		int idMunicipio = askInteger("Id profesor: ");
		Municipios municipio = session.get(Municipios.class, idMunicipio);
		if (municipio == null) {
			System.out.println("Municipio no encontrado");
			return;
		}
		System.out.println(municipio.toStringConProfesor());
	}

	// 4
	public void showProfesores() {
		List<Profesor> profesores = getQueryList("FROM Profesor", Profesor.class);
		System.out.println(profesores);
	}

	// 5
	public void showModulos() {
		List<Modulo> modulos = getQueryList("FROM Modulo", Modulo.class);
		System.out.println(modulos);
	}

	// 6
	public Profesor addProfesor() {
		int id = askInteger("Id: ");
		String nombre = askString("Nombre: ");
		String ape1 = askString("Primer apellido: ");
		String ape2 = askString("Segundo apellido: ");
		Integer tipoFuncionario = askInteger("Tipo funcionario: ");

		String calle = askString("Calle: ");
		int numero = askInteger("Numero: ");
		String provincia = askString("Provincia: ");

		int idMunicipio = askInteger("Id municipio: ");
		Municipios municipio = session.get(Municipios.class, idMunicipio);

		Profesor profesor = new Profesor(id, nombre, ape1, ape2, tipoFuncionario);

		// Direccion
		Direccion direccion = new Direccion(municipio, profesor, calle, numero, provincia, provincia);
		profesor.setDireccion(direccion);

		// Modulos
		Set<Profesormodulo> profesormodulos = new HashSet<Profesormodulo>();

		while (true) {
			int idModulo = askInteger("Id modulo (-1 para acabar): ");
			if (idModulo == -1) {
				break;
			}

			Modulo modulo = session.get(Modulo.class, idModulo);
			ProfesormoduloId profesormoduloId = new ProfesormoduloId(idModulo, id);
			Profesormodulo profesormodulo = new Profesormodulo(profesormoduloId, profesor, modulo);

			profesormodulos.add(profesormodulo);
		}

		profesor.setProfesormodulos(profesormodulos);

		// Correo electronicos
		Set<Correoelectronico> correoselectronicos = new HashSet<Correoelectronico>();

		while (true) {
			String direccionCorreo = askString("Correo electronico (cadena vacia para finalizar): ");

			if (direccionCorreo.equals("")) {
				break;
			}

			int idCorreo = askInteger("Id correo: ");

			Correoelectronico correoelectronico = new Correoelectronico(idCorreo, profesor, direccionCorreo);
			correoselectronicos.add(correoelectronico);
		}

		profesor.setCorreoelectronicos(correoselectronicos);

		// Transactions
		handleTransaction(profesor, "s");
		handleTransaction(direccion, "s");
		for (Profesormodulo profesormodulo : profesormodulos) {
			handleTransaction(profesormodulo, "s");
		}
		for (Correoelectronico correoelectronico : correoselectronicos) {
			handleTransaction(correoelectronico, "s");
		}

		return profesor;
	}

	// 7
	public Modulo addModulo() {
		int idModulo = askInteger("Id modulo: ");
		int idCiclo = askInteger("Id ciclo: ");

		Cicloformativo cicloformativo = session.get(Cicloformativo.class, idCiclo);

		String nombre = askString("Nombre modulo: ");
		int numeroHoras = askInteger("Numero horas: ");

		Modulo modulo = new Modulo(idModulo, cicloformativo, nombre, numeroHoras);

		// Profesores
		Set<Profesormodulo> profesormodulos = new HashSet<Profesormodulo>();

		while (true) {
			int idProfesor = askInteger("Id profesor: ");
			if (idProfesor == -1) {
				break;
			}

			Profesor profesor = session.get(Profesor.class, idProfesor);
			ProfesormoduloId profesormoduloId = new ProfesormoduloId(idModulo, idProfesor);
			Profesormodulo profesormodulo = new Profesormodulo(profesormoduloId, profesor, modulo);

			profesormodulos.add(profesormodulo);
		}

		modulo.setProfesormodulos(profesormodulos);

		// Transactions
		handleTransaction(modulo, "s");
		for (Profesormodulo profesormodulo : profesormodulos) {
			handleTransaction(profesormodulo, "s");
		}

		return modulo;
	}

	// 8
	public Cicloformativo addCicloFormativo() {
		int idCiclo = askInteger("Id ciclo: ");
		String nombreCiclo = askString("Nombre ciclo: ");
		int numeroHoras = askInteger("Numero horas: ");

		Cicloformativo ciclo = new Cicloformativo(idCiclo, nombreCiclo, numeroHoras);

		return handleTransaction(ciclo, "s");
	}

}
