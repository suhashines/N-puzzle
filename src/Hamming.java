public class Hamming implements Heuristic{

    @Override
    public int execute(int[] board) {
        int count = 0 ;

        for(int i=0;i<board.length;i++){

            if(board[i]!=0 && board[i]!=i+1){

                count ++ ;
            }
        }

        return count ;
    }
}
