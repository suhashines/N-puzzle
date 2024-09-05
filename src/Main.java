import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in) ;

        int n = scn.nextInt() ;

        int [][] grid = new int[n][n] ;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                String num = scn.next();

                if(num.compareTo("*")==0){
                    grid[i][j] = 0 ;
                }else{
                    grid[i][j] = Integer.parseInt(num) ;
                }
            }
        }

        Solver solver = new Solver(grid) ;

        if(!solver.isSolvable()){
            System.out.println("Puzzle instance is not solvable\n");
            return ;
        }

        System.out.println("Hurray,let's solve");

        solver.setHeuristic(new Hamming());
    }
}
