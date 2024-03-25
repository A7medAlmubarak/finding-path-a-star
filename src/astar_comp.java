import java.util.Comparator;

public class astar_comp implements Comparator<Graph> {

    @Override

    public int compare(Graph c1,Graph c2){

        if(c1.costH == c2.costH){
            return 0;
        }
       /* if(c1.costH == c2.costH && c1.moveCount > c2.moveCount){
            return 1;
        }
        if(c1.costH == c2.costH && c1.moveCount < c2.moveCount){
            return -1;
        }*/
        if(c1.costH<c2.costH ){
            return -1;
        } else{
            return 1;
        }
    }



}
