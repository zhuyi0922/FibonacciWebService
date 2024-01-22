package com.ZhuYi.OracleTest;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Path("/fibonacci")
@Produces(MediaType.APPLICATION_JSON)
public class FiboResource {
    public static class FibonacciInput {
        private BigInteger n;
        public BigInteger getN() {
            return n;
        }
        public void setN(BigInteger n) {
            this.n = n;
        }
    }
    public static class CalculateFibo{
        public static List<BigInteger> calculateFibo(BigInteger n){
            if (n.compareTo(BigInteger.ONE) < 0) {
                return null;
            }
            BigInteger[] dp = new BigInteger[n.intValue()];
            dp[0] = BigInteger.ZERO;
            if (n.compareTo(BigInteger.ONE) > 0) {
                dp[1] = BigInteger.ONE;
            }
            for (int i = 2; i < n.intValue(); i++) {
                dp[i] = dp[i - 1].add(dp[i - 2]);
                if (i == 80){
                    System.out.println(dp[i]);
                }
            }
            return Arrays.asList(dp);
        }
        public static List<BigInteger> sortFibonacci(List<BigInteger> fibArray) {
            List<BigInteger> evenList = new ArrayList<>();
            List<BigInteger> oddList = new ArrayList<>();
            for (int j = fibArray.size()-1; j >= 0; j--) {
                if (fibArray.get(j).mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    evenList.add(fibArray.get(j));
                } else {
                    oddList.add(fibArray.get(j));
                }
            }

            List<BigInteger> sortedList = new ArrayList<>();
            sortedList.addAll(evenList);
            sortedList.addAll(oddList);
            return sortedList;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public Map<String, List<String>> calculateFibonacci(FibonacciInput input) {
        BigInteger n = input.getN();
        Map<String, List<String>> result = new HashMap<>();
        List<BigInteger> fibArray = CalculateFibo.calculateFibo(n);
        if (fibArray == null) {
            return result;
        }
        result.put("list", fibArray.stream().map(BigInteger::toString).collect(Collectors.toList()));
        result.put("sorted", CalculateFibo.sortFibonacci(fibArray).stream().map(BigInteger::toString).collect(Collectors.toList()));
        return result;
    }
}

