import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.StringTokenizer;

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

public class Badgpt {
	private static final long MOD = 1000000007;
	private static String encoding;
	private static int len = 0;
	static PrintWriter writer;

	public static void main(String[] args) throws IOException {
		MyScanner sc = new MyScanner(new FileReader("badgpt.in"));
		writer = new PrintWriter(new FileWriter("badgpt.out"));
		encoding = sc.nextLine();
		len = encoding.length();
		writer.println(badgpt());
		writer.close();
	}

	private static long badgpt() {
		long result = 1;
		for (int i = 0; i < len - 1; i++) {
			if (encoding.charAt(i) == 'n' || encoding.charAt(i) == 'u') {
				int j = i + 1;
				while (j < len && Character.isDigit(encoding.charAt(j))) {
					j++;
				}
				long n = 0;
				if (i + 1 == j - 1) {
					n = Character.getNumericValue(encoding.charAt(i + 1));
					i++;
				} else {
					String nString = encoding.substring(i + 1, j);
					n = Long.parseLong(nString) % MOD;
					i = j - 1;
				}
				if (n > 1) {
					result = (result * nthFibonacci(n + 1)) % MOD;
				}
			}
		}
		return result;
	}
	// https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
	static long nthFibonacci(long n) {
		// Handle the edge cases
		if (n <= 1) {
			return n;
		}

		// Create an array to store Fibonacci numbers
		long[] dp = new long[(int) n + 1];

		// Initialize the first two Fibonacci numbers
		dp[0] = 0;
		dp[1] = 1;

		// Fill the array iteratively
		for (int i = 2; i <= n; ++i) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		// Return the nth Fibonacci number
		return dp[(int) n];
	}
}
