import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;
// https://ocw.cs.pub.ro/courses/pa/tutoriale/coding-tips
/**
 * A class for buffering read operations, inspired from here:
 * https://pastebin.com/XGUjEyMN.
 */
class MyScanner {
	private BufferedReader br;
	private StringTokenizer st;

	public MyScanner(Reader reader) {
		br = new BufferedReader(reader);
	}

	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
public class Feribot {
	private static long N;
	private static long K;
	private static long[] carWeights;
	private static long maxWeight = -1; // Greutate maxima a unei masini (costul minim C)
	// La citire il cautam
	private static long sumWeight = 0; // Suma greutatilor
	public static void main(String [] args) throws IOException {
		MyScanner sc = new MyScanner(new FileReader("feribot.in"));
		N = sc.nextLong();
		carWeights = new long[(int)N];
		K = sc.nextLong();
		for (int i = 0; i < N; i++) {
			carWeights[i] = sc.nextLong();
			if (maxWeight < carWeights[i]) {
				maxWeight = carWeights[i];
			}
			sumWeight += carWeights[i];
		}
		PrintWriter writer = new PrintWriter(new FileWriter("feribot.out"));
		writer.println(feribot());
		writer.close();
	}
	private static long feribot() {
		long min = maxWeight;
		long max = sumWeight;
		long minCost = maxWeight;
		// Căutare binară pentru costul minim
		while (min <= max) {
			long cost = (min + max) / 2;
			if (valid(cost)) { // Verifică dacă costul e valid
				minCost = cost;
				max = cost - 1;
			} else {
				min = cost + 1;
			}
		}
		return minCost;
	}
	// Costul nu poate fi mai mic decât greutatea maximă a unei mașini
	private static boolean valid(long cost) {
		if (cost < maxWeight) {
			return false;
		}
		long costOneFeribot = 0;
		long neededFeribots = 1;
		// Verificăm dacă putem distribui mașinile pe feriboturi
		for (int i = 0; i < N; i++) {
			if (costOneFeribot + carWeights[i] <= cost) {
				costOneFeribot += carWeights[i];
			} else {
				neededFeribots++;
				costOneFeribot = carWeights[i];
				if (neededFeribots > K) {
					// Dacă avem mai multe feriboturi decât K, nu e valid
					return false;
				}
			}
		}
		return true; // cost valid
	}
}
