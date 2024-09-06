public class Manhattan implements Heuristic{
    @Override
    public int execute(int[] board) {
        int count = 0 ;

        int N = board.length;

        for(int i=0 ; i<N ; i++){

            if(board[i]==0) continue ;

            int dr = Math.abs((board[i]-1)/N - (i/ N)) ;

            int dc = Math.abs((board[i]-1)%N - (i% N)) ;

            count += dr + dc ;
        }

        return count ;
    }
}
