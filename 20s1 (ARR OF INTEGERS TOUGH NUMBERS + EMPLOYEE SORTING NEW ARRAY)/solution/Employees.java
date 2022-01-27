/*
 * CS1010J AY2020/21 Semester 1
 * Practical Assessment Ex2: Employees.java
 * 
 * This program sorts the employees by age, and then by salary.
 * It also prints those employees whose age is most common.
 * 
 * Author: Zhou Lifeng
 */

import java.util.*;

class Employees {
  
  public static void main(String[] args) {
    
    int[][] employees = readInput();
    sort(employees);
    System.out.println("Sorted by age, then by salary:");
    printArray(employees, 0, employees.length-1);
    
    int[][] mostCommon = mostCommonAge(employees);
    System.out.println("Employees with the most common age:");
    printArray(mostCommon, 0, mostCommon.length-1);
  }
  
  // Read user input, create 2D array and return it.
  public static int[][] readInput() {
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of rows: ");
    int numRows = input.nextInt();
    
    int[][] mtx = new int[numRows][3];
    
    System.out.println("Enter data: ");
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < 3; col++) {
        mtx[row][col] = input.nextInt();
      }
    }
    input.close(); // just to avoid warning from DrJava
    
    return mtx;
  }
  
  // Print 2D array from row1 to row2
  public static void printArray(int[][] arr, int row1, int row2) {
    
    for (int r = row1; r <= row2; r++) {
      for (int c = 0; c < arr[r].length; c++) {
        System.out.print(arr[r][c] + "\t");
      }
      System.out.println();
    }
  }
  
  // Swap row1 and row2 of the employees array
  public static void swap(int[][] employees, int row1, int row2) {
    for (int col = 0; col < 3; col++) { // each row has 3 elements
      int temp = employees[row1][col];
      employees[row1][col] = employees[row2][col];
      employees[row2][col] = temp;
    }
  }
  
  // Sort the 'employees' array by age in non-decreasing order.
  // For those employees with the same age, 
  // further sort by salary in non-decreasing order.
  // If two employees have the same age and salary,
  // their relative order in the original array should be preserved.
  public static void sort(int[][] employees) {
    
    int numRows = employees.length;
    boolean done = false;
    
    // enhanced bubble sort
    for (int end = numRows-1; end>0 && !done; end--) {
      done = true;
      for (int row = 0; row < end; row++) {
        if ( (employees[row][1] > employees[row+1][1]) ||
            (employees[row][1] == employees[row+1][1] && employees[row][2] > employees[row+1][2]) ) {
          swap(employees, row, row+1);
          done = false; // there is a change, so not sorted yet
        }
      }
    }
  }
  
  // Compute sum of the rectangular portion of table
  // Assumption: employee age is between [18, 67], both ends inclusive
  public static int[][] mostCommonAge(int[][] sortedEmployees) {
    
    // countAge[18] to countAge[67] stores frequency of respective ages
    int[] freqAge = new int[68]; // default to 0
    for (int row = 0; row < sortedEmployees.length; row++) {
      freqAge[ sortedEmployees[row][1] ]++;
    }
    
    // look for the maximum frequency among ages
    int maxFreq = freqAge[18];
    for (int i = 19; i <= 67; i++) {
      if (maxFreq < freqAge[i]) {
        maxFreq = freqAge[i];
      }
    }
    
    // check which age has the maximum frequency, mark it as true
    int count = 0;
    boolean[] ageOfMaxFreq = new boolean[68]; // default to false
    for (int i = 18; i <= 67; i++) {
      if (freqAge[i] == maxFreq) {
        ageOfMaxFreq[i] = true;
        count++;
      }
    }
    
    // For example, count = 2, maxFreq = 3
    // That means there are 2 most common ages, each has 3 appearance
    // So the number of employee with most common age is 2*3 = 6
    int[][] commonAge = new int[count*maxFreq][3];
    int idx = 0;
    for (int row = 0; row < sortedEmployees.length; row++) {
      int age = sortedEmployees[row][1];
      if ( ageOfMaxFreq[age] ) { // employee with most common age
        for (int col = 0; col < 3; col++) {
          commonAge[idx][col] = sortedEmployees[row][col];
        }
        idx++;
      }
    }
    
    return commonAge;
  }
}

/*  sample run #1 inputs
 4
 114 60 10000
 115 60 6000
 117 35 5000
 118 60 30000
 */

/*  sample run #2 inputs
 10
 111 35 4500
 112 27 6600
 113 25 2000
 114 60 10000
 115 60 6000
 117 35 5000
 118 60 30000
 119 27 6600
 122 40 4000
 123 35 3400
 */