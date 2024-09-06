import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Solver {


    Heuristic h ;

    Node source ;

    Node goal ;

    int explored ; // total number of nodes created in the process

    int expanded ; // total nodes expanded using the heuristic

    public Solver(int[][]grid){

        source = new Node(grid) ;

        source.d = 0 ;

        source.parent = null ; // source's optimal value is not needed as it will be the default first node

//        System.out.println(source);
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


    public void solve(){

        if(!isSolvable()){

            System.out.println("The puzzle instance is not solvable");
            return ;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        // initially source is the minimum ;

        Node min = source ;

//        Set<Node> visited = new HashSet<>();

        while(min!=null && !min.isGoal()){

            for(Node node:min.neighbours()){

                if(min.parent == null || !min.parent.equals(node)){

                    // set the optimal function value for each node
                    node.f = node.d + h.execute(node.board) ;

                    pq.add(node);

                    explored ++ ;
                }
            }

            expanded ++ ;

            min = pq.poll() ;

        }

        // after the end of this loop minimum will hold the goal state
        goal = min ;

        System.out.println("heuristic :"+h.getClass().getSimpleName());
        System.out.println("total moves :"+goal.d);
        System.out.println("total explored :"+explored);
        System.out.println("total expanded :"+expanded);
        System.out.println("------Steps-------");
        printSteps(goal);

    }

    public void printSteps(Node goal){

        if(goal==null) return ;

        printSteps(goal.parent);

        System.out.println(goal+"\n");
    }

}
