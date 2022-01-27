/*
 * CS1010J AY2020/21 Semester 1
 * Practical Assessment Ex2: Employees.java
 * 
 * Your student number: Ryan Lim
 * 
 * Write a short and meaningful program description below:
 * 
 * 
 * 
 */

import java.util.*;

class Employees {
  
  // Main method is given. Do NOT modify it!
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
  // This method is given. Do NOT modify it!
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
  // This method is given. Do NOT modify it!
  public static void printArray(int[][] arr, int row1, int row2) {
    
    for (int r = row1; r <= row2; r++) {
      for (int c = 0; c < arr[r].length; c++) {
        System.out.print(arr[r][c] + "\t");
      }
      System.out.println();
    }
  }
  
  /**********************************************************
    *            Write your code below this line            *
    *********************************************************/
  
  // <Write a short description of the method here>
  public static void swap(int[][] employees, int row1, int row2) {
    
    int temp = 0;
    
    for (int i = 0; i < employees[row1].length; i++) {
      temp = employees[row2][i];
      employees[row2][i] = employees[row1][i];
      employees[row1][i] = temp;
    }
  }
  
  
// <Write a short description of the method here>
  public static void sort(int[][] employees) {
    
    int temp;
    boolean done = false;
    
    //sorts by age first
    for (int end = employees.length - 1; end > 0 && !done; end--) {
      done = true;
      for (int i = 0; i < end; i++) {
        if (employees[i][1] > employees[i + 1][1]) {
          swap(employees, i, i+1);
          done = false;
        }
      }
    }
    for (int end = employees.length - 1; end > 0; end--) {
      for (int i = 0; i < end; i++) {
        if (employees[i][2] > employees[i+1][2] && employees[i][1] == employees[i+1][1]) {
          swap(employees, i, i+1);
        }
      }
    }
  }
  
  
// <Write a short description of the method here>
  public static int[][] mostCommonAge(int[][] sortedEmployees) {
    
    int ageFrequency[] = new int[68];
    
    for (int i = 0; i < sortedEmployees.length; i++) {
      ageFrequency[ sortedEmployees[i][1] ]++;
    }
    
    int maxFrequency = 0;
    
    for (int i = 18; i <= 67; i++) {
      if (maxFrequency < ageFrequency[i]) {
        maxFrequency = ageFrequency[i];
      }
    }
    
    boolean[] ageOfMaxFreq = new boolean[68];
    int count = 0;
    
    for (int i = 18; i <= 67; i++) {
      if (ageFrequency[i] == maxFrequency) {
        ageOfMaxFreq[i] = true;
        count++;
      }
    }
    
    int[][] commonAge = new int[count * maxFrequency][3];
    
    int row = 0;
    
    for (int i = 0; i < sortedEmployees.length; i++) {
      
      int age = sortedEmployees[i][1];
      
      if (ageOfMaxFreq[age] == true) {
        for (int j = 0; j < 3; j++) {
          commonAge[row][j] = sortedEmployees[i][j];
        }
        row++;
      }
    }
    
    return commonAge; // stub
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