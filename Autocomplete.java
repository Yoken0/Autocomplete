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
}
