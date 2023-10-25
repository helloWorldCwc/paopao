package com.yupi.springbootinit.utils;

/**
 * @Author cwc
 * @Date 2023/10/25 9:44
 * @Version 1.0
 */
public class SimilarityUtil {
    public static double editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? dp[i - 1][j - 1]
                            : 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return 1 - (double) dp[m][n] / Math.max(m, n);
    }

    public static void main(String[] args) {
        System.out.println(editDistance("hello ", "hello,world"));
        System.out.println(editDistance("hello ", "hello "));
    }
}
