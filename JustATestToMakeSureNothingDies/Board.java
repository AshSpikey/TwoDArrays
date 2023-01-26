
package MineSweeper;
 
public class Board {
    /*
     * Values 2D array will have a number of different values
     * #, unrevealed space without a mine
     * 1, 2, 3, 4, 5, 6, 7, 8, revealed space
     * 0, user sees a -
     * M, which is a mine, user sees a # 
     * ⚑, mine that user flagged
     * X, non-mine that user flagged, user sees a ⚑
     */






    private String[][] values;
    private int width;
    private int height;
    private int mines;
    private boolean gameLost = false;
    
    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;

        values = new String[width][height];
    }

    public void fillBoard(){
        //this method is called when a board is empty and needs values
        //fills the board with #s (empty spaces) and Ms (mines)

        //loops through all spaces
        for (int wid = 0; wid < width; wid++) {
            for (int high = 0; high < height; high++) {
                values[wid][high] = "#"; //changes everything to a # (unrevealed tile)
            }
        }

        // adding mines
        //loops as many times as there are mines
        for (int i = 0; i < mines; i++) {
            //select a random row and column
            int row = (int) (Math.random() * width);
            int column = (int) (Math.random() * height);
            
            if (!values[row][column].equals("M")) { //if that space isn't a mine:
                values[row][column] = "M"; //make it a mine
            } else { //if it is a mine:
                i--; //try again
            } 
        }
    }


    public String toString(){
        /*
         * what it should look like:
         * 
   0 1 2 3 4 5 6 7 8 9 
  _____________________
0| # M # # # # # # M # 
1| # # # # # # # # # # 
2| # # # # # # # # # # 
3| # # # # M # # # # M 
4| # # # # # # # # # # 
5| # M # # # # M M # # 
6| # # # # # # # # M # 
7| # # # # # # # # # # 
8| # # # # M M # # # # 
9| # # # # # # # # # # 
         */
        //add the first few spaces to offset the numbers on top
        String str = "   ";
        
        //add in the numbers on top
        for (int x = 0; x < height; x++) {
            str += x + " ";
        }

        //down a line, add in spacing for the underlines
        str += "\n  _";

        //add in 2 underlines for every number there will be
        for (int y = 0; y < height; y++) {
            str += "__";
        }
        
        //add a line
        str += "\n";

        //now, for the main event
        //loop through every row
        for(int i = 0; i < width; i++){
            //add in the row number and the spacer
            str += (i) + "| ";
            //loop through every value in that row
            for(int j = 0; j < height; j++){
                //now, we need to print the right thing
                //can't just print the exact thing in values, then we'd tell the user every mine location
                if (values[i][j].equals("M")) { //if it's a mine:
                    str += "# "; //pretend its an unrevealed space
                } else if (values[i][j].equals("X")) { //if its an incorrect flag:
                    str += "⚑ "; //pretend its a correct flag
                } else if (values[i][j].equals("0")) { //if its a 0:
                    str += "- "; //make it a dash for aesthetic reasons
                } else { //if it's anything else:
                    str += values[i][j] + " "; //just use the thing in values, we've got nothing to hide
                }
            }
            //add in a line after every row is done
            str += "\n";
        }
        
        //return
        return str;
    }


    public void placeFlag(int row, int column){ 
        //this method adds or removes a flag
        //if you select a non-flag, it adds a flag
        //if you select a flag, it removes it
        if (values[row][column].equals("M")) { //if they flag an unrevealed mine:
            values[row][column] = "⚑"; //turn it into a flagged mine
        } else if (values[row][column].equals("#")) { //if they flag a non-mine space
            values[row][column] = "X"; //turn into incorrect flagged space
        } else if (values[row][column].equals("⚑")) { //if they want to remove a correct flag
            values[row][column] = "M"; //turn it back into a mine
        } else if (values[row][column].equals("X")) { //if they want to remove an incorrect flag
            values[row][column] = "#"; //turn it back into an unrevealed space
        } else { //if it's a revealed space (aka 0, 1, 2, etc)
            System.out.println("That space is cleared, it cannot have a flag");  //tell them they are goofy
        }
        
    }

    public void clearSpace(int row, int column){
        //this method is for attempting to reveal an unrevealed space
        //if it's not a mine or a flag, it also calculates the number it should display (the amount of mines around it)
        if (values[row][column].equals("M")) { //if you tried revealing a mine
            youLose(); //L + ratio
        } else if (values[row][column].equals("X") || values[row][column].equals("⚑")) { //if you trued revealing a flagged tile
            System.out.println("You have flagged that space!"); //tell them they are goofy
        } else if (values[row][column].equals("#")){ //if you try to reveal an unrevealed space
            //now, the true code begins
            //variable that will store how many mines are around:
            int mines = 0;

            //lots of for loops. Basically, they will loop through all tines in a 3x3 grid around the tile you're revealing and check for mines. 
            //loop through 3 rows around it:
            for (int i = row-1; i < row+2; i++) {
                //make sure those rows are within bounds
                if (i >= 0 && i < width) {
                    //loop through 3 numbers in each row
                    for (int j = column-1; j < column+2; j++) {
                        //make sure they are in bounds
                        if (j >= 0 && j < height) {
                            //if that value is a mine or flagged mine:
                            if (values[i][j].equals("M") || values[i][j].equals("⚑")) {
                                mines++; //increase the amount of mines found around the space
                            }
                        }
                    }
                }
            }
            //after all spaces have been checked
            //change the space to cleared, number is how many mines were dound around it
            values[row][column] = Integer.toString(mines);
        } else { //if you tried to clear a tile that's already cleared (a 1, 0, 3, or other number):
            System.out.println("That space is already cleared!"); //declare them the silliest goose
        }
    }

    public void youLose() { //LLLLLL
        gameLost = true; //then the main method does something with this idk what that's asher's job
    }

    public boolean getGameLost(){
        return gameLost; //pretty simple getter, allows main method to know whether or not the game has been lost
    }

    public boolean checkIfWin() {
        //returns false if there are still tiles to reveal, returns true if all tiles have been revealed

        //check through all values:
        for (String[] x : values) {
            for (String y : x) {
                //if any tile isn't revealed yet:
                if (y.equals("#")) {
                    return false; //the game is not won
                }
            }
        }
        //only gets here if all values have been run through and none are unrevealed tiles
        //therefore, there are no unrevealed tiles
        //therefore, all tiles have been revealed and victory has been achieved
        return true; //W
    }
}
