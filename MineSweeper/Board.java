package MineSweeper;

public class Board {
    /*
     * Values 2D array will have a number of different values
     * #, unrevealed tile
     * 1,2,3,4,5,6,7,8 revealed tile
     * -, revealed tile, means that there are 0 mines around
     * M, which is a mine, user sees a # 
     * ⚑, mine that the user flagged
     * X, non-mine that the user flagged, user sees a ⚑
     */






    private String[][] values;
    private int width;
    private int height;
    private int mines;
    
    public Board(int width, int height, int mines) {
        
        
        this.width = width;
        this.height = height;
        this.mines = mines;

        values = new String[width][height];
    }

    public void fillBoard(){
        for (int wid = 0; wid < width; wid++) {
            for (int high = 0; high < height; high++) {
                values[wid][high] = "#";

            }

        }

        for (int i = 0; i < mines; i++) {
            int row = (int) (Math.random() * width);
            int column = (int) (Math.random() * height);
            if (!values[row][column].equals("M")) {
                values[row][column] = "M";
            } else {
                i--;
            }
        }
    }


    public String toString(){
        String str = "";
        for(String[] array: values){
            for(String i: array){
                str += i + " ";
            }
            str += "\n";
        }
        
        return str;
    }


    public void placeFlag(int row, int column){

        //"⚑"
    }

    public void clearSpace(int row, int column){
        
    }

}
