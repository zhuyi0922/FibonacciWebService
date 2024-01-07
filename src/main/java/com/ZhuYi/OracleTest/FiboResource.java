package com.ZhuYi.OracleTest;

import com.codahale.metrics.annotation.Timed;
import com.google.errorprone.annotations.Var;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Path("/fibonacci")
@Produces(MediaType.APPLICATION_JSON)
public class FiboResource {
    public static class FibonacciInput {
        private int n;
        public int getN() {
            return n;
        }
        public void setN(int n) {
            this.n = n;
        }
    }
    public static class CalculateFibo{
        public static List<Integer> calculateFibo(int n){
            if (n <= 0) {
                return null;
            }
            int[] dp = new int[n];
            dp[0] = 0;
            if (n > 1) {
                dp[1] = 1;
            }
            for (int i = 2; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return Arrays.asList(Arrays.stream(dp).boxed().toArray(Integer[]::new));
        }
        public static List<Integer> sortFibonacci(List<Integer> fibArray) {
            List<Integer> evenList = new ArrayList<>();
            List<Integer> oddList = new ArrayList<>();
            for (int i = fibArray.size()-1; i >= 0; i--) {
                if (fibArray.get(i) % 2 == 0) {
                    evenList.add(fibArray.get(i));
                } else {
                    oddList.add(fibArray.get(i));
                }
            }

            List<Integer> sortedList = new ArrayList<>();
            sortedList.addAll(evenList);
            sortedList.addAll(oddList);
            return sortedList;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public Map<String, List<Integer>> calculateFibonacci(FibonacciInput input) {
        int n = input.getN();
        Map<String, List<Integer>> result = new HashMap<>();
        List<Integer> fibArray = CalculateFibo.calculateFibo(n);
        result.put("list", fibArray);
        result.put("sorted", CalculateFibo.sortFibonacci(fibArray));
        return result;
    }
}

