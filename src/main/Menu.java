package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import bd.Cicloformativo;
import bd.Correoelectronico;
import bd.Direccion;
import bd.Modulo;
import bd.Municipios;
import bd.Profesor;
import bd.Profesormodulo;
import bd.ProfesormoduloId;

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
//		while (true) {
//			System.out.println("Menu");
//			System.out.println("1. Mostrar un ciclo formativo");
//			System.out.println("2. Mostrar un profesor");
//			System.out.println("3. Añadir un departamento con empleados");
//			System.out.println("4. Modificar un departamento");
//			System.out.println("5. Modificar un empleado");
//			System.out.println("6. Eliminar un departamento");
//			System.out.println("7. Eliminar un empleado");
//			System.out.println("8. Mostrar todos los departamentos");
//			System.out.println("9. Mostrar los empleados de un departamento");
//			System.out.println("10. Mostrar todos los empleados");
//
//			int option = askInteger("Elige opción del menu: ");
		int option = 7;
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
//				showDepartamentos();
			break;

		case 9:
//				showEmpleadosDepartamento();
			break;

		case 10:
//				showEmpleados();
			break;

		default:
			close();
			return;
		}
	}

//	}

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
//		int idCiclo = askInteger("Id cicle: ");

		Cicloformativo ciclo = session.get(Cicloformativo.class, 1);
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

	public void showProfesor() {
		Profesor profesor = session.get(Profesor.class, 1072);
		System.out.println(profesor.toStringConModulos());
	}

	public void showMunicipio() {
		Municipios municipio = session.get(Municipios.class, 2);
		System.out.println(municipio.toStringConProfesor());
	}

	public void showProfesores() {
		List<Profesor> profesores = getQueryList("FROM Profesor", Profesor.class);
		System.out.println(profesores);
	}

	public void showModulos() {
		List<Modulo> modulos = getQueryList("From Modulo", Modulo.class);
		System.out.println(modulos);
	}

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
			int idModulo = askInteger("Id modulo: ");
			if (idModulo == -1) {
				break;
			}

			Modulo modulo = session.get(Modulo.class, idModulo);
			ProfesormoduloId profesormoduloId = new ProfesormoduloId(idModulo, id);
			Profesormodulo pm = new Profesormodulo(profesormoduloId, profesor, modulo);

			profesormodulos.add(pm);
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
			Profesormodulo pm = new Profesormodulo(profesormoduloId, profesor, modulo);

			profesormodulos.add(pm);
		}

		modulo.setProfesormodulos(profesormodulos);

		// Transactions
		handleTransaction(modulo, "s");
		for (Profesormodulo profesormodulo : profesormodulos) {
			handleTransaction(profesormodulo, "s");
		}

		return modulo;
	}

	public Cicloformativo addCicloformativo() {
		int idCiclo = askInteger("Id ciclo: ");
		String nombreCiclo = askString("Nombre ciclo: ");
		int numeroHoras = askInteger("Numero horas: ");

		Cicloformativo ciclo = new Cicloformativo(idCiclo, nombreCiclo, numeroHoras);
	}

}
