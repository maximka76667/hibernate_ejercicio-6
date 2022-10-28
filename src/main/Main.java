package main;

import org.hibernate.Session;

import bd.*;

public class Main {

	public static void main(String[] args) {

		Bd bd = new Bd();
		Session session = bd.getSession();

		Menu menu = new Menu(session);

		menu.start();

		menu.close();
		bd.close();
	}

}
