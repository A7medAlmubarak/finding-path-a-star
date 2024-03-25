import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Map {
    public int [][]Finish; // game end state
    public int [][]Taxi; // game Taxi map
    public double [][]TaxiSpeed;
    public int [][]Station; // game Station map
    public int [][]busRoad; // game bus Road
    public double [][]busSpeed;

    public int firstStaionX;
    public int firstStaionY;

    public int maxBusRoad;


    public Map( int level ){
      /*  switch (level){
            case '1':*/
        int[][] taxi    = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                            {-1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
                            {-1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
                            {-1, 1, 1, 1, 0, 0, 1, 1, 1,-1},
                            {-1, 1, 1, 1, 0, 0, 1, 1, 1,-1},
                            {-1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
                            {-1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
                            {-1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
                            {-1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
                            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} };

        double[][] taxiSpeed ={{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                            {-1,50,50,50,50,50,50,50,50,-1},
                            {-1,50,50,50,50,50,50,50,50,-1},
                            {-1,50,50,50, 0, 0,50,50,50,-1},
                            {-1,50,50,50, 0, 0,50,50,50,-1},
                            {-1,50,50,50,50,50,50,50,50,-1},
                            {-1,50,50,50,50,50,50,50,50,-1},
                            {-1,50,50,50,50,50,50,50,50,-1},
                            {-1,50,50,50,50,50,50,50,50,-1},
                            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} };

        int[][] station = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 1, 0, 0, 2, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 4, 0, 0, 3, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} };

        int[][] busRoad = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 1, 2, 3, 4, 0, 0,-1},
                            {-1, 0, 0,14, 0, 0, 5, 0, 0,-1},
                            {-1, 0, 0,13, 0, 0, 6, 0, 0,-1},
                            {-1, 0, 0,12, 0, 0, 7, 0, 0,-1},
                            {-1, 0, 0,11,10, 9, 8, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} };

        double[][] busSpeed = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0,30,30,30,30, 0, 0,-1},
                            {-1, 0, 0,30, 0, 0,30, 0, 0,-1},
                            {-1, 0, 0,30, 0, 0,30, 0, 0,-1},
                            {-1, 0, 0,30, 0, 0,30, 0, 0,-1},
                            {-1, 0, 0,30,30,30,30, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} };

                this.maxBusRoad = 14;
                this.Taxi= deepCopy(taxi);
                this.Station=deepCopy(station);
                this.busRoad=deepCopy(busRoad);
                this.TaxiSpeed=deepCopy(taxiSpeed);
                this.busSpeed=deepCopy(busSpeed);
                this.firstStaionX = 2;
                this.firstStaionY=3;


            /*    break;
        }*/


/*
        System.out.println("Please insert UR loacation x ");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();

        System.out.println("Please insert UR loacation y ");
        input = new Scanner(System.in);
        int y = input.nextInt();
        if( x<1 || x>8 || y<1 || y>8 ){ System.err.println("invalid input ! ");  return; }
*/


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
    public double[][] deepCopy(double [][] original){
        double x[][] = new double[original.length][original[0].length];
        for (int i=0 ;i< original.length;i++){
            for(int j =0;j< original[0].length;j++){
                x[i][j]=original[i][j];
            }
        }
        return x;
    }

    public int[][] getnextpoint( Graph graph , int value) {
        int x[][] = deepCopy(graph.Cells);
        int i = findX(graph);
        int j = findY(graph);
        int current = graph.map.busRoad[i][j];
        if(this.maxBusRoad == graph.map.busRoad[i][j] ){
            current =0;
        }

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
        //System.out.println(Arrays.deepToString(x));
        return x ;
    }

    public int findX(Graph graph){
        for (int i=0 ;i< graph.Cells.length;i++){
            for(int j =0;j< graph.Cells[0].length;j++){
                if( graph.Cells[i][j]>0 ){
                    return i;
                }
            }
        }
        return -1;
    }
    public int findY(Graph graph){
        for (int i=0 ;i< graph.Cells.length;i++){
            for(int j =0;j< graph.Cells[0].length;j++){
                if( graph.Cells[i][j]>0 ){
                    return j;
                }
            }
        }
        return -1;
    }





}
