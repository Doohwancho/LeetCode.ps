package one;

class makeAnagram {

	public static boolean isAnagram(String s, String t) {
		int rst = 0;
		int a = s.length();
		int b = t.length();

		for (int i = 0; i < a; i++) {
			char[] S = s.toCharArray();
			char[] T = t.toCharArray();

			for (int j = 0; j < b; j++) {
				if (S[i] == T[j]) {
					rst += 1;
					break;
				}
			}
		}
		return rst == s.length()?true:false;
	}

	public static void main(String[] args) {
		String s = "cat";
		String t = "act";
		System.out.println(isAnagram(s, t));

	}
}
