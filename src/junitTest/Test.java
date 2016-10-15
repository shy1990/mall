package junitTest;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		new Test().go();
	}

	public void go() {
		Scanner sc = new Scanner(System.in);
		System.out.println("please input a number between[-100000,100000]");
		String[] aa = { "ling", "yi", "er", "san", "si", "wu", "liu", "qi", "ba", "jiu" };
		String a = sc.nextLine();

		ArrayList<Integer> list = new ArrayList<Integer>();
		StringBuffer sb = new StringBuffer();
		if (Integer.valueOf(a) >= 0) {
			sb.append(a);
			System.out.println("the number you inputted is:" + sb);

			for (int i = 0; i < a.length(); i++) {
				// System.out.println(sb.charAt(i));
				list.add(Integer.parseInt(sb.charAt(i) + ""));
			}
			// System.out.println(list);

			for (int i = 0; i < list.size(); i++) {
				int index = list.get(i);
				System.out.print(aa[index] + " ");
			}
		}
		if (Integer.valueOf(a) < 0) {
			sb.append((-Integer.valueOf(a)));
			System.out.println("the number you inputted is: -" + sb);
			for (int i = 0; i < sb.length(); i++) {
				// System.out.println(sb.charAt(i));
				list.add(Integer.parseInt(sb.charAt(i) + ""));
			}
			// System.out.println(list);
			System.out.print("fu ");
			for (int i = 0; i < list.size(); i++) {
				int index = list.get(i);
				System.out.print(aa[index] + " ");
			}
		}

	}
}
