package Array;

public class RemoveSubFoldersFromFileSystem1233 {
	public static void main(String[] args) {
		String original = "a/b/c";
		
		String notRelated = "c/a"; // -2  add 
		String a = "a/b";          // 2   add (delete original)
		String b = "a/b/c";        // 0   pass
		String c = "a/b/c/d";      // -2  pass
		String d = "a/b/b/d";      // 1   add
		
		System.out.println("not related: "+original.compareTo(notRelated));
		System.out.println("a: "+original.compareTo(a));
		System.out.println("b: "+original.compareTo(b));
		System.out.println("c: "+original.compareTo(c));
		System.out.println("d: "+original.compareTo(d));
		
		System.out.println(a.compareTo(original));
		System.out.println(d.compareTo(original));
		
	}
}
