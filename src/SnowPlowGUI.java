import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.Font;

class SnowPlowGUI extends JFrame implements ActionListener{ 
  
  //Declare Panels  
  JPanel pan1;
  JPanel pan2;
  JPanel pan3;

  //Declare some GUI components
  
  JTextField rows;
  JTextField columns;
  JLabel rowL;
  JLabel columnL;
  JLabel instructionsLabel;
  JButton begin;
  JButton clear;
  JTextArea grid;
  JTextArea gridCleared;
  
  //Declare static variables + Scanner + Random
  static int totalRows;// number of rows in 2d array
  static int totalCols;// number of columns in 2d array
  static Scanner s = new Scanner (System.in);
  static Random rn = new Random();
  
  Color plowColor = new Color(144, 104, 190);
  //Highlighter highlighter = grid.getHighlighter();
  //HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
  public SnowPlowGUI(){
    
    setSize(1366, 768);
    pan1 = new JPanel();
    pan2 = new JPanel();
    pan3 = new JPanel();
    
    rows = new JTextField(10);
    columns = new JTextField(10);

    begin = new JButton("BEGIN");          
    clear = new JButton("CLEAR");
    
    instructionsLabel = new JLabel("Press BEGIN to run snowplow, Press CLEAR to clear text area.");
    rowL = new JLabel("Enter # of rows: ",JLabel.LEFT);
    columnL = new JLabel("Enter # of columns: ",JLabel.LEFT);
    grid = new JTextArea(50,50);
    gridCleared = new JTextArea(50,50);
    
    GridLayout layout1 = new GridLayout(2,1);
    FlowLayout layout2 = new FlowLayout();
    
    setLayout(layout2);
    pan1.setLayout(layout2);//Layout for Pan1
    pan2.setLayout(layout2);//Layout for Pan2
    pan3.setLayout(layout2);
    
    clear.addActionListener(this);
    begin.addActionListener(this);
    
    pan1.add(rowL);
    pan1.add(rows);
    pan1.add(columnL);
    pan1.add(columns);

    pan2.add(begin);
    pan2.add(clear);
    pan2.add(instructionsLabel);
    
    pan3.add(grid);
    pan3.add(gridCleared);
    
    add(pan1);
    add(pan2);
    add(pan3);
    
    setVisible(true);
  }

  
  public void actionPerformed(ActionEvent event) {
    System.out.println(0);
    String command = event.getActionCommand();
    totalRows = Integer.parseInt(rows.getText());
    totalCols = Integer.parseInt(columns.getText());
    
    int [][] array = new int[totalRows][totalCols];//declare 2d array

    if (command.equals("BEGIN")) { 
      set(array);//setting 2 array randomly with 1s and 2s
      int firstCol = find(array);//find and return horizontal index of first 1 aka start point, returns -1 if no 1 is found on first row
      display(array);// display 
      grid.append("\n");
      
      if (-1 == firstCol)
      {
        gridCleared.setText("Plow is not used");
      } else{
        snowplow(0, firstCol, array);
        displayCleared(array);
        gridCleared.append("\n");
      }
    }
    if (command.equals("CLEAR")){
      grid.setText(null);
      gridCleared.setText(null);
    }
  }
    
  
      
  public static void main (String args[]){
    
    SnowPlowGUI frame1= new SnowPlowGUI();
    
  }
  
  public void set (int array[][]){
    for (int r = 0; r<array.length; r++){
      for (int c = 0; c<array[r].length; c++){
        array[r][c]=rn.nextInt(2)+1;
      }
    }
  }
  
  public void display (int array[][]){
    for (int r = 0; r<array.length; r++){
      for (int c = 0; c<array[r].length; c++){
        if (array[r][c]==1){
          grid.append(array[r][c]+" ");
        }else {
          grid.append(array[r][c]+" ");
        }
      }
      grid.append("\n");
    }
  }
  
  public void displayCleared (int array[][]){
    for (int r = 0; r<array.length; r++){
      for (int c = 0; c<array[r].length; c++){
        gridCleared.append(array[r][c]+" ");
      }
      gridCleared.append("\n");
    }
  }
  
  public static int find (int array[][]){
    for (int c = 0; c<array[0].length; c++){
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

  
  public static void snowplow (int row, int col, int array[][]){
    array[row][col] = 0;//sets array index passed into snowplow method as 0
    for (int r = row-1; r < totalRows && r <= row+1; r++){
      if (r>=0){
        for (int c = col-1; c < totalCols && c <= col+1; c++){
          if (c>=0){
            if (1 == array[r][c]){
              snowplow(r, c, array);
            }
          }
        }
      }
    }
  }
}

  

