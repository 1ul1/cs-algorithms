import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class P1 {
	private static int N;
	private static int[] v = null;
	public static void main(String[] args) throws IOException {
		MyScanner scanner = new MyScanner(new FileReader("p1.in"));
		N = scanner.nextInt();
		v = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			v[i] = scanner.nextInt();
		}
		p1();
	}
	//	PrintWriter writer = new PrintWriter(new FileWriter("feribot.out"));
	//		writer.println(feribot());
	//		writer.close();
	private static void p1() throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter("p1.out"));
		// Definesc ArrayList<Integer>[] distances in care salves
		// indicii nodurilor aflate la o anumita distanta fata de sursa
		ArrayList<Integer>[] distances = new ArrayList[N + 1];
		// Nu afisez direct rezultatul deoarece nu stiu
		// cand o sa dau de o distanta care nu are niciun nod
		// De aceea folosesc string out
		String out;
		out = String.format("%d\n", N - 1);
		int currentDistance = 0;
		//		for (int i = 1; i <= N; i++) {
		//			if (currentDistance + 1 < v[i]) {
		//				return;
		//			}
		//			if (currentDistance < v[i]) {
		//				currentDistance++;
		//				distances[currentDistance] = new ArrayList<>();
		//			}
		//			distances[currentDistance].add(i);
		//		}
		for (int d = 0; d <= N; d++) {
			distances[d] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			distances[v[i]].add(i);
		}
		// verific daca exista elemente cu distanta 0
		// (in afara de sursa) => nu se poate forma niciun graf.
		if (distances[0].size() != 1) {
			writer.println("-1");
			writer.close();
			return;
		}
		// Creez muchiile conectand nodurile curente
		// cu primele noduri cu distanta exact mai mica.
		for (int i = 1; i <= N; i++) {
			if (distances[i].size() == 0 && i < N) {
				if (distances[i + 1].size() != 0) {
					writer.println("-1");
					writer.close();
					return;
				}	else {
					break;
				}
			}
			for (int j = 0; j < distances[i].size(); j++) {
				out = out + String.format("%d %d\n", distances[i - 1].get(0), distances[i].get(j));
			}
		}
		writer.print(out);
		writer.close();
	}
}
