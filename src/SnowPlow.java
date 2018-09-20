import java.util.Scanner;
import java.util.Random;

class SnowPlow{
  
  static int totalRows;// number of rows in 2d array
  static int totalCols;// number of columns in 2d array
  static Scanner s = new Scanner (System.in);
  static Random rn = new Random();
  
  public static void main (String args[]){
	  
	System.out.println("Enter number of rows: ");
    totalRows = s.nextInt();
    
    System.out.println("Enter number of columns: ");
    totalCols = s.nextInt();
    
    int [][] array = new int[totalRows][totalCols];//declare 2d array
    
    set(array);//setting 2 array randomly with 1s and 2s
    
    System.out.println("Grid before snowplow:");
    display(array);// display
    
    int firstCol = find(array);//find and return horizontal index of first 1 aka start point, returns -1 if no 1 is found on first row
    if (-1 != firstCol)
    {
        SnowPlow(0, firstCol, array);
    } else 
    {
        System.out.println("Plow is not used");
    }

    System.out.println();
    
    System.out.println("Grid after snowplow");
    display(array);
    
  }
  
  public static void set (int array[][]){
    for (int r = 0; r<array.length; r++){
      for (int c = 0; c<array[r].length; c++){
        array[r][c]=rn.nextInt(2)+1;
      }
    }
  }
  
  public static void display (int array[][]){
    for (int r = 0; r<array.length; r++){
      for (int c = 0; c<array[r].length; c++){
        System.out.print(array[r][c]+" ");
      }
      System.out.println();
    }
  }
  
  public static int find (int array[][]){
    for (int c = 0; c<array[0].length; ++c){
      if (array[0][c]==1){// finding the first 1 and returning it
        return c;
      }
    }
    return -1;// if nothing was returned, method returns -1
  }
  
 /*
  * There are a total of 9 scenarios and 8 ways to check if snowplow goes on
  * 
  *  9 Scenarios:
  *     1 2 3 
  *     4 5 6 
  *     7 8 9
  * 
  * 1. r==0, c==0
  * 2. r==0, c!=0&&c!=col-1
  * 3. r==0, c==col-1
  * 4. r!=0&&r!=row-1, c==0
  * 5. r!=0&&r!=row-1, c!=0&&c!=col-1
  * 6. r!=0&&r!=row-1, c==col-1
  * 7. r==row-1, c==0
  * 8. r==row-1, c!=0&&c!=col-1
  * 9. r==row-1, c==col-1
  * 
  * 
  *  8 ways to check if snowplow goes on (letter I is current index)
  *     1 2 3 
  *     4 I 5
  *     6 7 8
  * 
  *
  */
  

  
  public static void SnowPlow (int row, int col, int array[][]){
    array[row][col] = 0;//sets array index passed into snowplow method as 0
    for (int r = row-1; r < totalRows && r <= row+1; r++)
    {
      if (r < 0) continue;
      for (int c = col-1; c < totalCols && c <= col+1; ++c)
      {
        if (c < 0 || c == col && r == row) continue;
        if (1 == array[r][c])
          SnowPlow(r, c, array);
      }
    }
  }
  
}

  


        

