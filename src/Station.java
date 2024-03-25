import java.util.Scanner;

public class Station {

    public int [][]StationMap; // game Station map
    public int [][]NextStation; // game Station map

    public Station(Graph graph) {
        this.StationMap = graph.map.Station;

        }
    public int[][] getnextpoint( Graph graph ,int i , int j) {
        int x[][] = deepCopy(graph.Cells);
        int current = graph.map.Station[i][j];

        if( graph.map.busRoad[i][j+1] == current+1 ){
            int temp =x[i][j];
            x[i][j]=0;
            x[i][j+1]=temp;
        }
        if( graph.map.busRoad[i][j-1] == current+1 ){
            int temp =x[i][j];
            x[i][j]=0;
            x[i][j-1]=temp;
        }
        if( graph.map.busRoad[i+1][j] == current+1 ){
            int temp =x[i][j];
            x[i][j]=0;
            x[i+1][j]=temp;
        }
        if( graph.map.busRoad[i-1][j] == current+1 ){
            int temp =x[i][j];
            x[i][j]=0;
            x[i-1][j]=temp;
        }
        return x ;

    }
    public int[][] deepCopy(int [][] original){
        int x[][] = new int[original.length][original[0].length];
        for (int i=0 ;i< original.length;i++){
            for(int j =0;j< original[0].length;j++){
                x[i][j]=original[i][j];
            }
        }
        return x;
    }
}
