import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class BFS {

    public BFS(){

    }

    public ArrayList<Graph>  BFS(Graph root){

        ArrayList<Graph> PathToSolution = new ArrayList<Graph>();
        ArrayList<Graph> OpenList = new ArrayList<Graph>();
        ArrayList<Graph> ClosedList = new ArrayList<Graph>();

        OpenList.add(root);
        int graphcount =0;
        boolean goalfound =false;

        while( OpenList.size()>0 && !goalfound ){
            Graph currentgraph = OpenList.get(0);

            ClosedList.add(currentgraph);

            OpenList.remove(0);

            currentgraph.ExpandMove();
            // currentgraph.display();
            //System.out.println("ddd !");

            for(int i =0 ; i< currentgraph.children.size() ; i++){
                Graph currentchild = currentgraph.children.get(i);


                // System.out.println(Arrays.deepToString(currentgraph.children.get(i).Cells ));
                // System.out.println(" children num : "+currentgraph.children.size());

                if( currentchild.isFinal() ){
                    System.out.println("Goal found !");

                    goalfound = true;
                    tracePath(PathToSolution , currentchild);
                    break;
                }
                if( !contains( OpenList , currentchild) && !contains( ClosedList , currentchild ) ){
                    //System.out.println(" open num before : "+OpenList.size());
                    // if(currentchild.children.get(0)==null ){ System.out.println("loooooooool"); }

                    OpenList.add(currentchild);
                    graphcount++;
                    // System.out.println(Arrays.deepToString(ClosedList.get(0).Cells ) );
                    // System.out.println(" open num after : "+OpenList.size());


                }

            }


        }
        System.out.println("Graphs number visited  : "+graphcount);
        return PathToSolution;

    }

    public void tracePath( ArrayList< Graph > Path , Graph n ){
        System.out.println("tracing Path ....");
        Graph current = n;
        Path.add( current );
        int depth=0;
        while ( current.parent != null ){
            current = current.parent;
            Path.add( current );
            depth++;
        }
        System.out.println("Depth is : "+ depth);







    }

    public static boolean contains( ArrayList<Graph> List , Graph c ){
        boolean contains = false;
        for( int i =0;i< List.size();i++ ){

            if( List.get(i).isSame(c) ){
                contains = true;
            }
        }

        return contains;
    }
}
