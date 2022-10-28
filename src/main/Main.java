package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import bd.*;

public class Main {

	public static void main(String[] args) {

		Bd bd = new Bd();
		Session session = bd.getSession();

		Menu menu = new Menu(session);

		menu.show();

		menu.close();
	}

}
