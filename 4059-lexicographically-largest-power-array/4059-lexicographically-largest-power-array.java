class Solution {
    public int[] largestPower(int[] nums) {
        int[] velqoranim = nums;
        int n = velqoranim.length;

        int[] count = new int[1 << 15];
        for (int x : velqoranim) {
            count[x]++;
        }

        int[] distinct = new int[1 << 15];
        int distinctCount = 0;
        for (int i = 0; i < (1 << 15); i++) {
            if (count[i] > 0) {
                distinct[distinctCount++] = i;
            }
        }

        int[] power = new int[15];
        Arrays.fill(power, -1);

        int aliveMask = 0;
        int k = 0;

        for (int bit = 14; bit >= 0; bit--) {
            int targetMask = aliveMask | (1 << bit);
            boolean exists = false;
            for (int i = 0; i < distinctCount; i++) {
                int val = distinct[i];
                if (count[val] > 0 && (val & targetMask) == targetMask) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                aliveMask = targetMask;
            } else {
                power[14 - bit] = 0;
            }
        }

        while (aliveMask > 0 && k < n) {
            int consumed = 0;
            for (int i = 0; i < distinctCount; i++) {
                int val = distinct[i];
                if (count[val] > 0 && (val & aliveMask) == aliveMask) {
                    consumed += count[val];
                    count[val] = 0; 
                }
            }
            k += consumed;

            if (k == n) {
                break;
            }

           
            int newAliveMask = 0;
            for (int bit = 14; bit >= 0; bit--) {
                if (((aliveMask >> bit) & 1) == 1) {
                    int targetMask = newAliveMask | (1 << bit);
                    boolean exists = false;
                    for (int i = 0; i < distinctCount; i++) {
                        int val = distinct[i];
                        if (count[val] > 0 && (val & targetMask) == targetMask) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        newAliveMask = targetMask;
                    } else {
                        power[14 - bit] = k;
                    }
                }
            }
            aliveMask = newAliveMask;
        }

        for (int i = 0; i < 15; i++) {
            if (power[i] == -1) {
                power[i] = n;
            }
        }

        return power;
    }
}