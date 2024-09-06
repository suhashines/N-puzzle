import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node implements Comparable<Node>{

    public int N ;

    public int[]board ;

    public int blank ;

    public int f;  // f = d + h

    public int d ; // depth of the current node

    Node parent ;

    public Node(int[][]grid){

        N = grid[0].length ;

        board = new int[N*N] ;

        for(int i=0;i<N;i++){

            for(int j=0;j<N;j++){

                if(grid[i][j]==0) blank = N*i + j ;

                board[N*i+j] = grid[i][j] ;
            }
        }
    }

    private Node(int[]board){

        this.board = board ;
        this.N = (int)Math.sqrt(board.length); // restoring the N for child
    }

    public boolean isGoal(){

        for(int i=0;i< board.length;i++){

            if(board[i]!=0 && board[i]!=i+1) return false ;
        }

        return true ;
    }

    private void swap(int i,int j){

        int temp = board[i];
        board[i] = board[j];
        board[j] = temp ;

        // updating the blank position
        if(board[i]==0) blank = i ;
        else blank = j ;
    }

    public List<Node> neighbours(){


        List<Node> nodes = new ArrayList<>();

        if(blank%N != 0){
            // not the leftmost, can swap with the left one

            Node node = new Node(this.board.clone()) ;
            node.swap(blank,blank-1);
            node.parent = this ;
            node.d = this.d + 1 ;
            nodes.add(node) ;

        }

        if((blank+1)%N!=0){

            // not the rightmost, can swap with the right one

            Node node = new Node(this.board.clone()) ;
            node.swap(blank,blank+1) ;
            node.parent = this ;
            node.d = this.d + 1 ;
            nodes.add(node) ;
        }


        if((blank/N)!=0){

            // not the uppermost , can swap with the upper one

            Node node = new Node(this.board.clone()) ;
            node.swap(blank,blank-N) ;
            node.parent = this ;
            node.d = this.d + 1 ;
            nodes.add(node) ;
        }

        if((blank/N)!=N-1){

//            System.out.println("blank "+blank+" N "+N);

            // not the lowermost , can swap with the lower one

            Node node = new Node(this.board.clone()) ;

            node.swap(blank,blank+N) ;
            node.parent = this ;
            node.d = this.d + 1 ;
            nodes.add(node) ;
        }

        return  nodes ;
    }

    @Override
    public boolean equals(Object obj) {

        if(this.getClass()!=obj.getClass()) return false ;

        Node node  = (Node) obj ;

        return Arrays.equals(this.board,node.board) ;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for(int i=0;i<board.length;i++){

            if(i%N == 0) str.append("\n");

            if(board[i]==0) str.append("* ");
            else str.append(board[i]).append(" ");
        }

        return str.toString() ;
    }

    @Override
    public int compareTo(Node o) {
        return this.f - o.f ;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
