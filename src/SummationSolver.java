import java.util.HashMap;
import java.util.HashSet;

/**
 * A class which solves word sum puzzles
 * @author softskeleton
 *
 */
public class SummationSolver {
	  private static int perms;
	  private static String letters;
	  private static String[] words;
	
	public static void permute(int n, int k) {
	    permute(n, k, new HashSet<Integer>(), new int[k]);
	  }
	
	public static void permute(int n, int k, HashSet<Integer> set, int[] permutation) {
	    if (set.size() == k)
	      doPermute(n, k, set, permutation);
	    else {
	      for (int i=0; i<n; i++)
	        if (!set.contains(i)) {
	          permutation[set.size()] = i;
	          set.add(i);
	          permute(n,k,set,permutation);
	          set.remove(i);
	        }
	    }
	  }
	
	public static void doPermute(int n, int k, HashSet<Integer> set, int[] permutation) {
	    HashMap<Character,Integer> charmap = new HashMap<Character,Integer>();
	    for (int i=0; i<k; i++)
	      charmap.put(letters.charAt(i), // "SENDMORY";
	                  permutation[i]);   // [2, 3, 1, 4, 0, 5,... ]
	    int[] vals = new int[3];
	    for (int j=0; j<3; j++) {
	      String word = words[j];
	      if (charmap.get(word.charAt(0)) == 0)
	        return;
	      int val = 0;
	      for (int i=0; i<word.length(); i++)
	        val = 10*val + charmap.get(word.charAt(i));
	      vals[j] = val;
	    }
	    if (vals[0] + vals[1] == vals[2]) {
	      System.out.println(charmap);
	      System.out.format("%8d\n+%7d\n=%7d\n",vals[0],vals[1],vals[2]);
	    }
	  }
	
	 public static void cryptarithm(String s1, String s2, String s3) {
		    words = new String[3];
		    words[0] = s1;
		    words[1] = s2;
		    words[2] = s3;
		    String all = s1+s2+s3;
		    letters = "";
		    for (int i=0; i<all.length(); i++) {
		      char c = all.charAt(i);
		      if (letters.indexOf(c) < 0) letters += c;
		    }
		    permute(10,letters.length());
	  }
}
