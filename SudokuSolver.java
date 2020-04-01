import java.util.*;

public class SudokuSolver {
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int k, loops;
		loops = input.nextInt();
		input.nextLine();
		input.nextLine();
		int[][] grid;
		
		for(k = 0; k < loops; k++)
		{
			String str = "";
			for(int r = 0; r < 9; r++)
			{
				str += input.nextLine();
			}
			grid = altReadIn(str);
			//printSudoku(grid);
			if (solveSudoku(grid) == true)
			{
				System.out.printf("\nTest case %d: \n", k+1);
				printSudoku(grid);
			}
			else
			{
				System.out.println("No Solution\n");
			}
			input.nextLine();
		}
		
	}
	
	//Reads in grid using string of sudoku puzzle
	public static int[][] altReadIn(String str)
	{
		int[][] grid = new int[9][9];
		str = str.replaceAll(" ", "");
		int k = 0;
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				grid[i][j] = Character.getNumericValue(str.charAt(k++));
			}
		}
		return grid;
	}
	
	//Reads in a grid
	public static int[][] readIn()
	{
		Scanner input = new Scanner(System.in);
		int[][] grid = new int[9][9];
		
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				grid[i][j] = input.nextInt();
		
		input.close();
		return grid;
	}
	
	//Returns false if there are empty slots, true otherwise.
	public static boolean isFinished(int[][] grid)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(grid[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	//Houses the control for the three types of safe move searches
	public static boolean isSafeMove(int[][] grid, int row, int col, int current_Num)
	{
		int startRow = ((int)(row / 3)) * 3;
		int startCol = ((int)(col / 3)) * 3;
		return isValidRowMove(grid, row, current_Num)
				&& isValidColMove(grid, col, current_Num)
				&& isValidBoxMove(grid, startRow, startCol, current_Num);
	}
	
	//Returns true if the current number doesn't exists in the row.
	public static boolean isValidRowMove(int[][] grid, int row, int current_Num)
	{
		for(int i = 0; i < 9; i++)
		{
			if(current_Num == grid[row][i])
				return false;
		}
		return true;	
	}
	
	//Returns true if the current number doesn't exists in the column.
	public static boolean isValidColMove(int[][] grid, int col, int current_Num)
	{
		for(int i = 0; i < 9; i++)
		{
			if(current_Num == grid[i][col])
				return false;
		}
		return true;	
	}
	
	//Returns true if the move is valid, false otherwise.
	public static boolean isValidBoxMove(int[][] grid, int startRow, int startCol, int current_Num)
	{
		    for (int row = 0; row < 3; row++)
		    {
		        for (int col = 0; col < 3; col++)
		        {
		            if (grid[row+startRow][col+startCol] == current_Num)
		                return false;
		        }
		    }
		    return true;
	}

	//Solves the sudoku puzzle
	public static boolean solveSudoku(int[][] grid/*, int row, int col*/)
	{
		//Checks if the grid is filled
		if(isFinished(grid))
			return true;
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(grid[i][j] != 0)
				{
					continue;
				}
				for (int num = 1; num <= 9; num++)
				{
					if (isSafeMove(grid, i, j, num))
					{
						grid[i][j] = num;	
						if(solveSudoku(grid))
						{
							return true;
						}else{
							grid[i][j] = 0;
						}
					}
				}
				return false;
			}
		}
		return true;
	}
	
	//Prints out the sudoku puzzle
	public static void printSudoku(int[][] grid)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.printf("%d ", grid[i][j]);
			}
			System.out.println();
		}
	}
}
