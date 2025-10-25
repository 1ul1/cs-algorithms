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
class Stock {
	int currentValue, minValue, maxValue;
	int loss;
	int profit;
	Stock(int currentValue, int minValue, int maxValue) {
		this.currentValue = currentValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		loss = currentValue - minValue;
		profit = maxValue - currentValue;
	}
//    @Override
//    public int compareTo(Stock o) {
//        if (this.profit - this.loss > o.profit - o.loss) {
//            return -1;
//        } else if (this.profit - this.loss < o.profit - o.loss) {
//            return 1;
//        } else {return 0;}
//    }
}
public class Stocks {
	private static int N;
	private static int B;
	private static int L;
	private static Stock[] stocks;
	public static void main(String[] args) throws IOException {
		MyScanner sc = new MyScanner(new FileReader("stocks.in"));
		N = sc.nextInt();
		stocks = new Stock[N];
		B = sc.nextInt();
		L = sc.nextInt();
		for (int i = 0; i < N; i++) {
			stocks[i] = new Stock(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		PrintWriter writer = new PrintWriter(new FileWriter("stocks.out"));
		writer.println(stocksFunc());
		writer.close();
	}
	// Funcția principală pentru calculul profitului maxim
	// dinamic ca la knapsack
	private static int stocksFunc() {
		// DP pentru profit, vizite și update
		int[][] dp = new int[L + 1][B + 1], visited = new int[L + 1][B + 1];
		visited[0][0] = 1; // starea initiala
		int profit = 0;
		int maxProfit = 0;
		// Iterăm prin toate stocurile și bugetele posibile
		for (int i = 0; i < N; i++) {
			// iteram astfel bugetul ca sa nu suprascriem
			for (int b = B; b >= stocks[i].currentValue; b--) {
				for (int l = L; l >= stocks[i].loss; l--) {
					int bPrev = b - stocks[i].currentValue;
					int pPrev = l - stocks[i].loss;
					// Verificăm dacă putem adăuga stocul
					if ((bPrev >= 0 && pPrev >= 0) && visited[pPrev][bPrev] == 1) {
						profit = dp[pPrev][bPrev] + stocks[i].profit;
						if (visited[l][b] == 0 || dp[l][b] < profit) {
							dp[l][b] = profit;
							visited[l][b] = 1;
							if (maxProfit < dp[l][b]) {
								// Actualizare profit maxim
								maxProfit = dp[l][b];
							}
						}
					}
				}
			}
		}
		return maxProfit;
	}
}
