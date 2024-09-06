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

        solver.setHeuristic(new Manhattan());

        solver.solve();

        solver.setHeuristic(new Hamming());

        solver.solve();
    }
}
