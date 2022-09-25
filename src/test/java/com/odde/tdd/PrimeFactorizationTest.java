package com.odde.tdd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeFactorizationTest {

    @Test
    public void no_prime_factor_for_1 () {
        PrimeFactorization primeFactorization = new PrimeFactorization();
        List<Integer> factors = primeFactorization.factorize(1);
        assertEquals(Arrays.asList(), factors);
    }

    @Test
    public void no_prime_factor_for_2 () {
        PrimeFactorization primeFactorization = new PrimeFactorization();
        List<Integer> factors = primeFactorization.factorize(2);
        assertEquals(Arrays.asList(2), factors);
    }

    @Test
    public void no_prime_factor_for_6 () {
        PrimeFactorization primeFactorization = new PrimeFactorization();
        List<Integer> factors = primeFactorization.factorize(6);
        assertEquals(Arrays.asList(2,3), factors);
    }

    @Test
    public void no_prime_factor_for_8 () {
        PrimeFactorization primeFactorization = new PrimeFactorization();
        List<Integer> factors = primeFactorization.factorize(8);
        assertEquals(Arrays.asList(2,2,2), factors);
    }
}
