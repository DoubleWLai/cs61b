public class HelloNumbers {
	public static void main(String[] args) {
		int x = 0;
		int sum = 0;
		while (x < 10) {
			for (int i = 0; i < x+1; i = i + 1) {
				sum = sum + i;
			}
			System.out.print(sum + " ");
			sum = 0;
			x = x + 1;
		}
	}
}