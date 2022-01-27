/*
 * CS1010J AY2020/21 Semester 1
 * Practical Assessment Ex1: ArrayOfInts.java
 * 
 * This program accepts an array of positive integers
 * and performs 3 tasks.
 * 
 * Author: Zhou Lifeng
 */

import java.util.*;

class ArrayOfInts {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the size of array: ");
    int size = input.nextInt();
    
    int[] arr = new int[size];
    System.out.print("Enter the array: ");
    for (int i = 0; i < size; i++) {
      arr[i] = input.nextInt();
    }
    
    System.out.println("Maximum difference = " + maxDiff(arr));
    
    System.out.println("Number of tough numbers = " + countToughNum(arr));
    
    System.out.print("Enter t: ");
    int t = input.nextInt();
    System.out.println("Number of smiling numbers = " + countSmilingNum(arr, t));

    input.close();
  }
  
  // Return the largest difference between adjacent integers 
  public static int maxDiff(int[] arr) {
    int max = 0;
    for (int i = 0; i < arr.length-1; i++) {
      int diff = Math.abs(arr[i] - arr[i+1]);
      max = Math.max(max, diff);
    }
    
    return max;
  }
  
  // Return true if 'num' is a tough number, or false otherwise
  // A tough number can be expressed as num = k*(2^n) + 1
  // where n > 0, k > 0, k is odd and k < 2^n. 
  public static boolean isToughNum(int num) {
    /* 
     * Suppose num is a tough number
     * hence num = k*(2^n) + 1, and
     *       num - 1 = k*(2^n).
     *     Since n > 0, (num-1) must be even. Therefore
     *         if (num-1) is NOT even, return false
     * Next, so long (num-1) is even, keep dividing it by 2
     *     remainder is k; how to check if k < 2^n?
     *             note that num-1 = k*(2^n)
     *             if k < 2^n, k*k < num-1 
     */
    
    int k = num;
    k--;
    
    if (k == 0 || k%2 != 0) {
      return false;
    }
    
    while (k%2 == 0) {
      k /= 2;
    }

    return k*k < num-1;
  }
  
  // Return the number of tough numbers in 'arr'
  public static int countToughNum(int[] arr) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      if ( isToughNum(arr[i]) ) {
        // System.out.println("Tough number: " + arr[i]); // for testing
        count++;
      }
    }
    
    return count;
  }
  
  // Return the number of smiling numbers in the array.
  // A smiling number is a positive integer that has
  // the sum of its digits equals to 't'.
  public static int countSmilingNum(int[] arr, int t) {
    
    int count = 0;
    
    for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      while (arr[i] > 0) {
        sum += arr[i] % 10;
        arr[i] /= 10;
      }
      if (sum == t) {
        count++;
      }
    }
    
    return count;
  }
}