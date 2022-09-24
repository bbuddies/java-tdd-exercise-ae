package com.odde.tdd;

import java.util.ArrayList;
import java.util.List;

public class Prime {
    public List<Integer> factors(int number) {
        ArrayList<Integer> factors = new ArrayList<>();

        for(int candidate = 2;number > 1; candidate++){
            for (;number % candidate == 0; number /= candidate) {
                factors.add(candidate);
            }
        }
        return factors;
    }
}
