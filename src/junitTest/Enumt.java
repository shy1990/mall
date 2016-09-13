package junitTest;

public class Enumt {
	public static void main(String[] args) {
		MenuTest mt = MenuTest.BLACK;

		System.out.println(mt.getName());

		/*
		 * for (MenuTest c : MenuTest.values()) { System.out.println(c.name() +
		 * "--" + c.getName() + "--" + c.getStyle()); }
		 */
	}
}
