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
public class Crypto {
	static final int MOD = 1_000_000_007;
	private static int N = 0;
	private static int L = 0;
	private static String K, S;

	public static void main(String[] args) throws IOException {
		MyScanner sc = new MyScanner(new FileReader("crypto.in"));
		N = sc.nextInt();
		L = sc.nextInt();
		K = sc.next();
		S = sc.next();
		PrintWriter out = new PrintWriter(new FileWriter("crypto.out"));
		out.println(crypto());
		out.close();
	}

	private static long crypto() {
		// int[] visited = new int[26];
		// numărul de caractere distincte din S
		int M = (int) S.chars().distinct().count();
		long[] dp = new long[L + 1];
		dp[0] = 1;
		// Parcurg fiecare caracter din K
		for (int i = 0; i < N; i++) {
			char c = K.charAt(i);
			// De la L pt a nu suprascrie
			for (int j = L; j >= 1; j--) {
				long last = dp[j];
				// Dacă caracterul e '?' => alegem oricare dintre caracterele din S
				// putem adauga M noi posibile subsecvente
				if (c == '?') {
					dp[j] = (last * M + dp[j - 1]) % MOD;
				} else if (c == S.charAt(j - 1)) {
					// adaug valoarea lui dp[j-1] la dp
					// Adăugăm numărul de subsecvențe de j-1 care pot fi extinse cu c
					dp[j] = (dp[j] + dp[j - 1]) % MOD;
				}
			}
			if (c == '?') {
				dp[0] = dp[0] * M % MOD;
			}
		}
		return dp[L];
	}
}