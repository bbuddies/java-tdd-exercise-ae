package com.odde.tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * 分解质因数
 */
public class PrimeFactorization {

    public static void main (String[] args) {
        List<Integer> res = factorize(1);
        System.out.println(res);
    }

    // 分解（递归）
    static List<Integer> factorize(Integer t) {
        List<Integer> res = new ArrayList<>();
        int de = 2;
        while (de <= t) {
            if (t%de==0) {
                res.add(de);
                break;
            }
            de++;
        }
        if (de < t) {
            res.addAll(factorize(t/de));
        }
        return res;
    }
}
