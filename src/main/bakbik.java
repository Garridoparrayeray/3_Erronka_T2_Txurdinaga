package main;

import java.util.Scanner;

public class bakbik {

	public static void main(String[] args) {
		System.out.println("Sartu zenbakia konprobatzeko bikoitia edo bakoitioa baden: ");
		Scanner scanner = new Scanner(System.in);
		float numero = scanner.nextFloat();
		if (numero % 2 == 0 ) {
			System.out.println("zenbakia bikoitia da.");
		}
		else {
			System.out.println("Zenbakia bakoitia da.");
		}
		scanner.close();

	}

}
