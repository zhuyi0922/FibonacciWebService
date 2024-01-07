package com.ZhuYi.OracleTest;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.*;

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
            }
            return Arrays.asList(dp);
        }
        public static List<BigInteger> sortFibonacci(List<BigInteger> fibArray) {
            List<BigInteger> evenList = new ArrayList<>();
            List<BigInteger> oddList = new ArrayList<>();
            for (int i = fibArray.size()-1; i >= 0; i--) {
                if (fibArray.get(i).mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    evenList.add(fibArray.get(i));
                } else {
                    oddList.add(fibArray.get(i));
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
    public Map<String, List<BigInteger>> calculateFibonacci(FibonacciInput input) {
        BigInteger n = input.getN();
        Map<String, List<BigInteger>> result = new HashMap<>();
        List<BigInteger> fibArray = CalculateFibo.calculateFibo(n);
        result.put("list", fibArray);
        if (fibArray == null) {
            return result;
        }
        result.put("sorted", CalculateFibo.sortFibonacci(fibArray));
        return result;
    }
}

