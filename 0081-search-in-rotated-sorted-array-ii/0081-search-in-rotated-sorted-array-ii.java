class Solution {
    public boolean search(int[] a, int t) {
          int lo = 0, hi = a.length - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == t) return true;
        if (a[lo] == a[mid] && a[mid] == a[hi]) { lo++; hi--; continue; }
        if (a[lo] <= a[mid]) {
            if (a[lo] <= t && t < a[mid]) hi = mid - 1;
            else lo = mid + 1;
        } else {
            if (a[mid] < t && t <= a[hi]) lo = mid + 1;
            else hi = mid - 1;
        }
    }
    return false;
    }
}