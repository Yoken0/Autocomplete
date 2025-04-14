import dsa.Merge;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a that equals the search key, or -1, according to the order induced by
    // the comparator c.
    public static <T> int firstIndexOf(T[] a, T key, Comparator<T> c)throws NullPointerException{
        if(a == null || key == null || c == null) {
            throw new NullPointerException("a, key, or c is null");
        }
        // set lo and hi boundaries
        int lo = 0;
        int hi = a.length - 1;
        // set the index to -1
        int index = -1;
        while(lo <= hi){
                int mid = lo + (hi - lo)/2;
                // when mid is smaller than key
                if(c.compare(a[mid], key) < 0){
                    // set lo to mid + 1 so the minimum value is after the mid
                    lo = mid + 1;
                }
                // when mid is bigger than key
                if(c.compare(a[mid], key) > 0){
                    // set hi to mid - 1 so the maximum value is before the mid
                    hi = mid - 1;
                }
                // when mid is == key
                if(c.compare(a[mid], key) == 0){
                    index = mid;
                    hi = mid - 1;
                }
            }
        return index;
    }

    // Returns the index of the last key in a that equals the search key, or -1, according to the order induced by
    // the comparator c.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {
     if(a == null || key == null || c == null) {
        throw new NullPointerException("a, key, or c is null");
    }
    // set low and hi boundaries
    int lo = 0;
    int hi = a.length - 1;
    // set the index to -1
    int index = -1;
    while(lo <= hi){
        int mid = lo + (hi - lo)/2;
        // when mid is < key
        if(c.compare(a[mid], key) < 0){
            // set lo to mid + 1 so the minimum value is after the mid
            lo = mid + 1;
        }
        // when mid is > key
        if(c.compare(a[mid], key) > 0){
            // set hi to mid - 1 so the maximum value is before the mid
            hi = mid - 1;
        }
        // when mid is == key
        if(c.compare(a[mid], key) == 0){
            index = mid;
            lo = mid + 1;
        }
    }
    return index;
}
}
