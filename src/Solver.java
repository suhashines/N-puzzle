public class Solver {

    Heuristic h ;

    Node source ;

    Node goal ;

    public Solver(int[][]grid){

        source = new Node(grid) ;

        source.d = 0 ;

        source.parent = null ;

        System.out.println(source);
    }

    public void setHeuristic(Heuristic h){
        this.h = h ;
    }

    public int inversionCount(int[]board){

        int count = 0 ;

        for(int i=0;i<board.length;i++){

            for(int j=i+1;j<board.length;j++){

                if(board[j]!=0 && board[j]<board[i]){

                    count ++ ;
                }
            }
        }

        return count ;
    }



    public boolean isSolvable(){

        int N = source.N; ;

        int inversions = inversionCount(source.board);

        int blankRowPosition = N - (source.blank)/N ; // Row position from the bottom

        if (N % 2 != 0)
            return inversions % 2 == 0;


        return (inversions + blankRowPosition)%2 != 0  ;

    }
}
