import dsa.Merge;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Autocomplete {
    // array of terms
    private final Term[] terms; 

    // Constructs an Autocomplete data structure from an array of terms.
    public Autocomplete(Term[] terms) throws NullPointerException {
        this.terms = terms;
        if (terms == null) {
            throw new NullPointerException("terms is null");
        }
        Merge.sort(this.terms);
    }

    // Returns all terms that start with prefix, in descending order of their weights.
    public Term[] allMatches(String prefix) throws NullPointerException {
        if(prefix == null) {
            throw new NullPointerException("prefix is null");
        }
        
        // create a Term with the prefix
        Term prefixTerm = new Term(prefix, prefix.length());
        // create the prefix comparator
        Comparator<Term> prefixOrder = Term.prefixOrder(prefix.length());
        // get the index of the first term that matches the prefix
        int Firstindex = BinarySearchDeluxe.firstIndexOf(this.terms, prefixTerm, prefixOrder);
        // number of matches
        int n = numberOfMatches(prefix);
        // array to store the matches
        Term[] matches = new Term[n];
        for(int i = 0;i < n;i++){
            matches[i] = this.terms[Firstindex + i];
        }
        // create the comparator to sort by reverse order
        Comparator<Term> weightOrder = Term.reverseWeightOrder();
        // sort the matches by reverse order of weight
        Merge.sort(matches, weightOrder);
        return matches;
        
    }

    // Returns the number of terms that start with prefix.
    public int numberOfMatches(String prefix) throws NullPointerException{
        if(prefix == null) {
            throw new NullPointerException("prefix is null");
        }
        Term prefixTerm = new Term(prefix, prefix.length());
        Comparator<Term> prefixOrder = Term.prefixOrder(prefix.length());
        // First instance of the prefix
        int i = BinarySearchDeluxe.firstIndexOf(this.terms, prefixTerm, prefixOrder);
        // Last instance of the prefix
        int j = BinarySearchDeluxe.lastIndexOf(this.terms, prefixTerm, prefixOrder);
        if(i == -1 || j == -1) {
            return 0;
        }
        // the difference between the first index and the last index will give us the amount of matches
        return j - i + 1;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Autocomplete autocomplete = new Autocomplete(terms);
        StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            String msg = " matches for \"" + prefix + "\", in descending order by weight:";
            if (results.length == 0) {
                msg = "No matches";
            } else if (results.length > k) {
                msg = "First " + k + msg;
            } else {
                msg = "All" + msg;
            }
            StdOut.printf("%s\n", msg);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println("  " + results[i]);
            }
            StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        }
    }
}
