package ca.ubc.ece.cpen221.mp3.cryptarithm;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SolveCryptarithm {

	static public void main(String[] args) {
			try {
				Cryptarithm cryptarithm = new Cryptarithm(args);
				List<Map<Character,Integer>> solutions = cryptarithm.solve();
				System.out.print(solutions.size() + " solution(s): ");
				System.out.print(solutions);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
}
