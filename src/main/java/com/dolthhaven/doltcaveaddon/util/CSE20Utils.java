package com.dolthhaven.doltcaveaddon.util;

public class CSE20Utils {
    public static int getLargestIndexInArray(int[] oxy) {
        int large = Integer.MIN_VALUE;
        int ret = 0;
        for (int i = 0; i < oxy.length; i++) {
            if (oxy[i] > large) {
                large = oxy[i];
                ret = i;
            }
        }
        return ret;
    }
}
