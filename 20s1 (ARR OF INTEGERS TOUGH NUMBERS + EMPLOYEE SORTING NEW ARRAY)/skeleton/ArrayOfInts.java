/*
 * CS1010J AY2020/21 Semester 1
 * Practical Assessment Ex1: ArrayOfInts.java
 * 
 * Your student number: Ryan Lim
 * 
 * Write a short and meaningful program description below:
 * 
 * 
 * 
 */

import java.util.*;

class ArrayOfInts {
  
  // Main method is given. Do NOT modify it!
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
  
  /**********************************************************
    *            Write your code below this line            *
    *********************************************************/
  
  // Returns maximum difference between adjacent integers in array: arr
  public static int maxDiff(int[] arr) {
    
    int diff = 0;
    
    for (int i = 0; i < arr.length - 1; i++) {
      if (Math.abs(arr[i] - arr[i+1]) > diff) {
        diff = Math.abs(arr[i] - arr[i+1]);
      }
    }
    
    return diff;
  }
  
  
  // Returns true if number is a tough number (i.e.  can be expressed in the form k*2^n + 1 where k < 2^n and k is odd
  public static boolean isToughNum(int num) {
    
    int newNum = num - 1;
    
    int k = 0;
    int n = 0;
    
    while (newNum > 1 && newNum%2 == 0) {
      newNum /= 2;
      n++;
      k = newNum;
    }
    
    if (k%2 == 1 && k < Math.pow(2, n)) {
      return true;
    } else {
      return false;
    }
  }
  
  
  // Returns the number of tough numbers in the given array
  public static int countToughNum(int[] arr) {
    
    int count = 0;
    
    for (int i = 0; i < arr.length; i++) {
      if (isToughNum(arr[i])) {
        count++;
      }
    }
    return count;
  }
  
  
  // Returns the number of smiling numbers in the given array
  public static int countSmilingNum(int[] arr, int t) {
    
    int count = 0;
    
    for (int i = 0; i < arr.length; i++) {
      
      int sum = 0;
      
      while (arr[i] > 10) {
        sum += arr[i]%10;
        arr[i] /= 10;
      }
      sum += arr[i]%10;
      
      if (sum == t) {
        count++;
      }
    }
    return count;
  }
}