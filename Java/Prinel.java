import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
public class Prinel {
	private static long K = 0;
	private static long N = 0;
	private static long[] target, p;
	public static void main(String[] args) throws IOException {
		MyScanner sc = new MyScanner(new FileReader("prinel.in"));
		N = sc.nextLong();
		K = sc.nextLong();
		target = new long[(int) N];
		p = new long[(int) N];
		for (int i = 0; i < N; i++) {
			target[i] = sc.nextLong();
		}
		for (int i = 0; i < N; i++) {
			p[i] = sc.nextLong();
		}
		PrintWriter writer = new PrintWriter(new FileWriter("prinel.out"));
		writer.println(prinel());
		writer.close();
	}
	// BFS pentru calculul distanțelor minime, de la lab
	// https://ocw.cs.pub.ro/courses/pa/laboratoare/laborator-06
	private static int[] bfs(int target) {
		int[] d = new int[target + 1];
		int[] p = new int[target + 1];
		Arrays.fill(d, Integer.MAX_VALUE);

		Queue<Integer> q = new LinkedList<>();
		d[1] = 0;
		p[1] = -1;

		q.add(1);

		while (!q.isEmpty()) {
			int node = q.poll();

			for (int div = 1; div * div <= node; div++) {
				if (node % div == 0) {
					int[] divisors = new int[] {div, node / div};
					for (int dVal : divisors) {
						int neigh = node + dVal;
						if (neigh <= target && d[node] + 1 < d[neigh]) {
							d[neigh] = d[node] + 1;
							p[neigh] = node;
							q.add(neigh);
						}
					}
				}
			}
		}
		return d;
	}
	// Funcția principală pentru calcularea rezultatului
	private static long prinel() {
		long max = 0;
		long result = -1;
		// Căutăm valoarea maximă din target
		for (long t : target) {
			if (t > max) {
				max = t;
			}
		}
		// Aflam cu bfs drumurile minime pt fiecare target
		// (ce divizori || numere trebuie sa adunam)
		int[] d = bfs((int) max);
		int[] cost = new int[(int) N];
		// cost fiecare numar
		for (int i = 0; i < N; i++) {
			cost[i] = d[(int) target[i]];
		}
		// dinamic ca la knapsack
		int[] dp = new int[(int) K + 1];
		for (int i = 0; i < N; i++) {
			for (int k = (int) K; k >= cost[i]; k--) {
				if (dp[k] < dp[k - cost[i]] + (int) p[i]) {
					dp[k] = dp[k - cost[i]] + (int) p[i];
				}
				if (result < dp[k]) {
					result = dp[k];
				}
			}
		}
		if (result == -1) {
			return 0;
		}
		return result;
	}

}
