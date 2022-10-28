package main;

import java.util.List;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import bd.*;

public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Bd bd = new Bd();
		Session session = bd.getSession();

		// 1a
		System.out.print("Id cicle: ");
		int idCiclo = input.nextInt();

		Cicloformativo ciclo = session.get(Cicloformativo.class, idCiclo);
		System.out.println(ciclo);

		// 1b
		TypedQuery<Modulo> queryModulos = session.createQuery("FROM Modulo WHERE idCiclo=" + idCiclo, Modulo.class);
		List<Modulo> modulos = queryModulos.getResultList();

		System.out.println(modulos);

		for (Modulo modulo : modulos) {
			TypedQuery<Profesor> queryProfesores = session.createQuery(
					"FROM Profesormodulo JOIN Profesor ON Profesor.id = Profesormodulo.idProfesor WHERE idModulo="
							+ modulo.getIdModulo());
			List<Profesor> profesores = queryProfesores.getResultList();
			System.out.println(profesores);
		}

		input.close();
		bd.close();
	}

}
