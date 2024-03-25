import java.util.ArrayList;
import java.util.PriorityQueue;

public class Astar2 {



    ArrayList<Graph> visitedcells = new ArrayList<Graph>();


    // A* ALG

    public ArrayList<Graph> Astar2 (Graph root , int AstarType){

        ArrayList <Graph> path = new ArrayList<Graph>();
        ArrayList <Graph> openedcells = new ArrayList<Graph>();


        PriorityQueue<Graph> myq = new PriorityQueue (new astar_comp());
        PriorityQueue<Graph> sol = new PriorityQueue (new astar_comp());

        root.costH = calcHeuristic(root,AstarType);
        myq.add(root);
        openedcells.add(root);
        boolean goal = false;
        int moves=0;


        while(!myq.isEmpty()){

            Graph current = myq.poll();
            visitedcells.add(current);

            moves++;


            current.ExpandMove();

            for(int i=0;i<current.children.size();i++){

                Graph currentchild = current.children.get(i);
                currentchild.costH = calcHeuristic(currentchild,AstarType);

                //System.out.println(currentchild.moneyCost);

                if(currentchild.isFinal()){
                    sol.add(currentchild);
                }
                if(!contain(openedcells,currentchild )){
                    myq.add(currentchild);
                    openedcells.add(currentchild);
                    //System.out.println(currentchild.costH);

                }
                else{
                    myq.removeIf(e -> e.costH > currentchild.costH && isSame(e,currentchild) ); //
                }
            }
        }
        if(sol.size()>0){
            System.out.println();
            System.out.println("---------------------- Goal found ! ------------------------");
            goal = true;

            printpath(path,sol.poll());
            System.out.println("-------------");

            System.out.println("Visited nodes : "+visitedcells.size());
            System.out.println("-------------");

            return path;

        }


        return path;
    }

    public double calcHeuristic(Graph graph , int AstarType){
        switch (AstarType){
            case 1:
                return (graph.getHeuristic() + (graph.moneyStart - graph.moneyCost)/100  + graph.moveCount );
            case 2:
                return (graph.getHeuristic() +  (100 - graph.energyCost)/100 ) + graph.moveCount;
            case 3:
                return (graph.getHeuristic() + graph.timeCost*60 +
                        (graph.moneyStart - graph.moneyCost)/100
                        + (100 - graph.energyCost)/100 )  + graph.moveCount;
        }

        return 0;
    }
    public boolean isSame(Graph g1 , Graph g2){
        boolean check= false;
        for (int i=0 ;i< g1.Cells.length;i++){
            for(int j =0;j< g1.Cells[0].length;j++){
                if( g1.Cells[i][j] == g2.Cells[i][j] && g1.costH == g2.costH ){
                    check = true;
                }
            }
        }
        return check;
    }

    public void printpath(ArrayList list,Graph c){
        System.out.println();
        System.out.println();
        System.out.println("Path to solution is : ");
        System.out.println("----------------------------");
        System.out.println();
        int count=0;
        Graph current = c;
        list.add(current);

        while(current.parent != null){
            current = current.parent;
            list.add(current);
            count++;

        }
        System.out.println("total KM : "+count);

    }

    public static boolean contain(ArrayList<Graph> list,Graph c){

        boolean doesit = false;

        for(Graph element : list){
            if(element.isSame(c) && element.moveType == c.moveType)
                doesit=true;
        }

        return doesit;
    }

}
