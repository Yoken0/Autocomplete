import dsa.Merge;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable<Term> {
    // String for the query
    private final String query;
    // weight of the term
    private final long weight;


    // Constructs a term given the associated query string, having weight 0.
    public Term(String query) throws NullPointerException {
        if(query == null) {
            throw new NullPointerException("query is null");
        }
        this.query = query;
        this.weight = 0;
    }

    // Constructs a Term given the associated query string and weight.
    public Term(String query, long weight) throws NullPointerException, IllegalArgumentException {
        if(query == null) {
            throw new NullPointerException("query is null");
        }
        if(weight < 0) {
            throw new IllegalArgumentException("Illegal weight");
        }
        this.query = query;
        this.weight = weight;
    }

    // Returns a string representation of this term.
    public String toString() {
        return this.weight + "\t" + this.query;
    }

    // Returns a comparison of this term and other by query.
    public int compareTo(Term other) {
        return this.query.compareTo(other.query);
    }

    // Returns a comparator for comparing two terms in reverse order of their weights.
    public static Comparator<Term> reverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Returns a comparator for comparing two terms by their prefixes of length r.
    public static Comparator<Term> prefixOrder(int r) {
        return new PrefixOrder(r);
    }

    // Reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        // Returns a comparison of terms v and w by their weights in reverse order.
        public int compare(Term v, Term w) {
            return w.weight < v.weight ? -1 : (w.weight > v.weight ? 1 : 0);
        }
    }

    // Prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        // length of the prefix(first r characters)
        private final int r;

        // Constructs a PrefixOrder given the prefix length.
        PrefixOrder(int r) throws IllegalArgumentException{
            // set r, then check corner case
            this.r = r;
            if(this.r < 0){
                throw new IllegalArgumentException("Illegal r");
            }
            
        }

        // Returns a comparison of terms v and w by their prefixes of length r.
        public int compare(Term v, Term w) {
            // prefix of v and w(first r characters)
            String prefix_v = v.query.substring(0, Math.min(this.r, v.query.length()));
            String prefix_w = w.query.substring(0, Math.min(this.r, w.query.length()));
            return prefix_v.compareTo(prefix_w);
        }
    }
}
