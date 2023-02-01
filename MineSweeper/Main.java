package MineSweeper;

import java.util.Scanner;

import javax.xml.transform.Source;
 
public class Main{
    public static void main(String[] args) throws InterruptedException{
        // the printStr is used as the str that is printed slowly 
        String printStr = "";
        int boardRows = 0;
        int boardColumns = 0;
        int boardMines = 0;
        
        // Creating Scanner object  
        Scanner sc = new Scanner(System.in);

        // Intro messages 
        printStr = ("Weclome to Minesweeper!\nYou will be given a choice to either place a flag or choose a square to explore.\nEnjoy!\n\nWhat is your name?\n");
        slowText(printStr);
        String name = sc.nextLine();
        printStr = ("Hello, " + name + "\nYou are a very important person, you need to clear a mindfield so our troops can safely make it out of a warzone! Get to work!");
        slowText(printStr);
        printStr = ("A quick note! If you decide to make a board bigger than 10x10, the numbers will not line up... so like dont? Or if you want to, be prepared for some weirdness with the display!");
        slowText(printStr);

        // Creating board object with custom sizes
        printStr = ("You now have the option to customize the board!");
        slowText(printStr);
        

        // getting custom rows 
        while(true){
            boolean loopVar = true;
            while(loopVar){
                loopVar = false;
                printStr = ("\n\tHow many rows would you like?\n\t");
                slowText(printStr);

                String boardRowsString = sc.nextLine();
                try{
                    boardRows = Integer.parseInt(boardRowsString);
                }catch (Exception e){
                    printStr = ("\tYou did not enter a valid integer.");
                    slowText(printStr);
                    loopVar = true;
                }

            }
        if(boardRows <= 0){
            printStr = ("That answer is less than or equal to 0. Stop it.");
            slowText(printStr);
        }else{
            break;
        }
        }
        

        // getting custom columns 
        while(true){
        printStr = ("\tHow many columns would you like to have?\n");
        slowText(printStr);
        boardColumns = sc.nextInt();
        if(boardColumns <= 0){
            printStr = ("That answer is less than or equal to 0. Stop it.");
            slowText(printStr);
        }else{
            break;
        }
        }
        


        // getting custom mines 
        while(true){
        printStr = ("\tHow many mines would you like?\n");
        slowText(printStr);
        boardMines = sc.nextInt();

        if(boardMines > (boardColumns * boardRows)){ 
            printStr = ("You have more mines that spaces. That is not possible. Stop it.");
            slowText(printStr);
        }else{
            break;
        }
        }
        Board gameBoard = new Board(boardRows, boardColumns, boardMines);

        // Filling said board with mines 
        gameBoard.fillBoard();
        System.out.println(gameBoard);

        
        
        // Game loop. Ends when the boolean value is true, thus when the player loses
        while(!gameBoard.getGameLost()){
            printStr = ("Would you like to place a flag or explore an area? (flag/explore)\n");
            slowText(printStr);
            String decision = sc.nextLine().toLowerCase();

            if(decision.equals("flag")){
                printStr = ("If you would like to remove a flag, place a flag in the same space in which the flag already is.\nWhich area would you like to place a flag? Please write your answer as: row, column.\nSeperate your answer with a comma. Make sure to not add a space after the comma!\n");
                slowText(printStr);
                String answer = sc.nextLine();
                int indexOfComma = answer.indexOf(",");
                int row = Integer.parseInt(answer.substring(0,indexOfComma));
                int column = Integer.parseInt(answer.substring(indexOfComma + 1));
                gameBoard.placeFlag(row, column);
                ClearTerminal();
                System.out.println(gameBoard);
            }else if(decision.equals("explore")){
                printStr = ("Which area would you like to explore? Please write your answer as: row, column.\nSeperate your answer with a comma. Make sure to not add a space after the comma!\n");
                slowText(printStr);
                String answer = sc.nextLine();
                int indexOfComma = answer.indexOf(",");
                int row = Integer.parseInt(answer.substring(0,indexOfComma));
                int column = Integer.parseInt(answer.substring(indexOfComma + 1));
                gameBoard.clearSpace(row, column);
                ClearTerminal();
                System.out.println(gameBoard);
            }else{
                printStr = ("You did not enter a valid answer.\n");
                slowText(printStr);
            }
        }

        if(gameBoard.getGameLost()){
            printStr = ("You hit a bomb and lost! Try again!");
            slowText(printStr);
        }
    } 


    // Extra static methods 
    public static void slowText(String string) throws InterruptedException{
        // Causes the text to print out letter by letter
        System.out.println();
        for(int i = 0; i < string.length(); i ++){
            System.out.print(string.substring(i, i + 1));
            Thread.sleep(4);
        }
    }

    public static void ClearTerminal(){
        // Clears the terminal
        System.out.println("\033[H\033[2J");
    }
}
