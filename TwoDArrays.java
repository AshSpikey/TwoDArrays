public class TwoDArrays {
    public static void main(String[] args){
        
        // Two ways to initalize arrays, with values, or with parametes of the lengths 
        String[][] fortnite = {{"A", "B", "C"},{}};
        
        int[][] battlePass = new int[2][5];
            // The first number (2) is the rows, the second (5) is the columns 


        // to iterate through an array
        for(int[]b: battlePass){
            // first getting the array inside of the 2d array
            for(int a: b){
                // then grabbing the individual value of each array
                System.out.println(a);
            }
            System.out.println();
        }

        // the variable fortnite[0][2] would equal "c"

        String var = fortnite[0][2];
        System.out.println(var);

    }
}
