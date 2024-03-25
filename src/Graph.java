import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Graph {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // 1 => walk  , 2 => taxi , 3 =>microservice
    public int [][]Cells; // game cells
    public int [][]Finish; // game cells

    public Map map;

    public int moveType; // game movetype 0=> start 1 => walk  2=>taxi  3=> micro
    public int moveCount=0;
    public double moneyCost; // game moneyCost
    public double moneyStart; // game moneyCost

    public double timeCost; // game timeCost
    public double energyCost; // game energyCost

    public double taxiSpeed; // game energyCost
    public double microSpeed; // game energyCost
    public double stationWaitTime; // game energyCost

    public double walkSpeed;
    public int Priority=1;
    public int getPriority(){
        return this.Priority;
    }

    public double costH;

    public int getHeuristic(){
        return this.manhattanHeuristic();
    }
    public ArrayList<Graph> children = new ArrayList<Graph>();

    public Graph parent;

    public Graph( int [][] x , int [][]fin , Map map ,double moneyCost ){
        this.Cells= deepCopy(x);
        this.Finish = deepCopy(fin);
        this.map = new Map(1);
        this.moneyStart = moneyCost;
        this.taxiSpeed = 10;
        this.walkSpeed = 4.5;
    }
    public Graph() {

        this.map = new Map(1);
        int[][] cell = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} };

        int[][] fin = { {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1, 0, 0, 0, 0, 0, 0, 0, 0,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} };
        System.out.println("Please enter start X [1-8] : ");
        Scanner ss2 = new Scanner(System.in);
        int ix1 = ss2.nextInt();
        System.out.println("Please enter start Y [1-8]: ");
        Scanner ss3 = new Scanner(System.in);
        int iy1 = ss3.nextInt();

        System.out.println("Please enter end X [1-8]: ");
        Scanner ss4 = new Scanner(System.in);
        int ix2 = ss4.nextInt();
        System.out.println("Please enter end Y [1-8]: ");
        Scanner ss5 = new Scanner(System.in);
        int iy2 = ss5.nextInt();

        System.out.println("Please enter money u have ");
        Scanner ss = new Scanner(System.in);
        int innn = ss.nextInt();

        if( innn<1 || ix1<1 || ix1>9|| ix2<1 || ix2>9|| iy1<1 || iy1>9 || iy2<1 || iy2>9){ System.out.println("somthing went wrong"); return; }

        cell[ix1][iy1]=1;
        fin[ix2][iy2]=1;

        System.out.println("Please enter station Wait Time ");
        Scanner sss = new Scanner(System.in);
        int in = sss.nextInt();

        this.Cells = deepCopy(cell);
        this.Finish = deepCopy(fin);
        this.taxiSpeed =10;
        this.walkSpeed = 4.5;
        this.energyCost=100;
        this.moneyCost = innn;
        this.moneyStart=innn;
        this.stationWaitTime = in;
        this.timeCost=1;
        //  this.MAT = new boolean [NODE_NUMBER][NODE_NUMBER];
    }

    public void display(){
        String st=new String();
        for (int i=0 ;i< this.Cells.length;i++) {
            System.out.print("     ");
            for (int j = 0; j < this.Cells[0].length; j++) {
                //  System.out.print(x[i][j] +"    ");
                if( this.map.Station[i][j]>0 ){ st="S";  } else{ st=" "; }
                if (this.Cells[i][j] > 0 && this.Finish[i][j]>0 ) {
                    System.out.print(ANSI_GREEN_BACKGROUND + " " + st + " " + ANSI_RESET);
                    continue;
                }
                if (this.Cells[i][j] > 0 && this.moveType == 0) {
                    System.out.print(ANSI_PURPLE_BACKGROUND + " " + st + " " + ANSI_RESET);
                    continue;
                }
                if (this.Cells[i][j] > 0 && this.moveType == 1) {
                    System.out.print(ANSI_CYAN_BACKGROUND + " " + st + " " + ANSI_RESET);
                    continue;
                }

                if (this.Cells[i][j] > 0 && this.moveType == 2) {
                    System.out.print(ANSI_YELLOW_BACKGROUND + " " + st + " " + ANSI_RESET);
                    continue;
                }
                if (this.Cells[i][j] > 0 && this.moveType == 3) {
                    System.out.print(ANSI_RED_BACKGROUND + " " + st + " " + ANSI_RESET);
                    continue;
                }
                if (this.Cells[i][j] == 0) {
                    System.out.print(ANSI_WHITE_BACKGROUND + " " + st + " " + ANSI_RESET);
                    continue;
                }
                if (this.Cells[i][j]==-1) {
                    System.out.print(ANSI_BLACK_BACKGROUND + " " + st + " " + ANSI_RESET);
                    continue;
                }


            }
            System.out.println();
        }
        String st2=new String();
        if(moveType==0) {st2="Start";}
        if(moveType==1) {st2="Walk";}
        if(moveType==2) {st2="Taxi";}
        if(moveType==3) {st2="bus";}

        System.out.println("money cost :"+ this.moneyCost +" | energy :"+this.energyCost +" |Time : "+this.timeCost +
                '\n' +"Manhattan Heuristic :" + this.getHeuristic() +" |Move type :"+st2 +"total Heuristic: "+this.costH);



        // System.out.println(Arrays.deepToString(this.Cells));

        System.out.println(); //System.out.println();
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
    int manhattanHeuristic()
    {
        int GoalTileX = this.findX(this.Finish);
        int GoalTileY = this.findY(this.Finish);
        int playerPosX = this.findX(this.Cells);
        int playerPosY = this.findY(this.Cells);
        int differenceOfX = GoalTileX - playerPosX;
        int differenceofY = GoalTileY - playerPosY;
        int sum=0;

        sum+= Math.abs(differenceOfX) + Math.abs(differenceofY);
        return sum;
    }

    public boolean[][] deepCopy(boolean [][] original){
        boolean x[][] = new boolean[original.length][original[0].length];
        for (int i=0 ;i< original.length;i++){
            for(int j =0;j< original[0].length;j++){
                x[i][j]=original[i][j];
            }
        }
        return x;
    }

    public boolean[][] taxiGetNextState(){
        boolean [][] check= new boolean[this.Cells.length][this.Cells[0].length];
        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =this.Cells[0].length -1 ;j>=0 ;j--){
                if( this.map.Taxi[i][j] == -1 || this.map.Taxi[i][j] == 0 ){check[i][j]=false;}
                if( this.map.Taxi[i][j] == 1 ){check[i][j]=true;}
            }
        }
        return check;
    }
    public boolean[][] walkGetNextState(){
        boolean [][] check= new boolean[this.Cells.length][this.Cells[0].length];
        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =this.Cells[0].length -1 ;j>=0 ;j--){
                if( this.Cells[i][j] == -1 ){check[i][j]=false;}
                if( this.Cells[i][j] == 0 ){check[i][j]=true;}
            }
        }
        return check;
    }

    public Graph walkMoveRight(){
        boolean [][] check = deepCopy(this.walkGetNextState()) ;
        boolean chang = false;
        int [][] x =  deepCopy(this.Cells);
        for (int i=0 ;i< this.Cells.length-1;i++){
            for(int j =0;j< this.Cells[0].length-1;j++){
                if( this.Cells[i][j]>0 && check[i][j+1]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i][j+1]=temp;
                }
            }
        }
        if( chang && this.energyCost>=10 ){
            Graph child = new Graph( x ,this.Finish , this.map, this.moneyCost);
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 1;
            child.moneyCost = this.moneyCost + 0;
            child.timeCost = this.timeCost + 1/walkSpeed ;
            child.energyCost = this.energyCost -10;
            child.moveCount=this.moveCount+1;
            return child;
        }
        return this;
    }

    public Graph walkMoveLeft(){
        boolean [][] check = deepCopy(this.walkGetNextState()) ;
        int [][] x =  deepCopy(this.Cells);
        boolean chang = false;

        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =0;j< this.Cells[0].length;j++){
                if(this.Cells[i][j]>0 && check[i][j-1]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i][j-1]=temp;
                }
            }
        }
        if(chang && this.energyCost>=10){
            Graph child = new Graph( x ,this.Finish , this.map,this.moneyCost);
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 1;
            child.moneyCost = this.moneyCost + 0;
            child.timeCost = this.timeCost + 1/walkSpeed ;
            child.energyCost = this.energyCost -10;
            child.moveCount=this.moveCount+1;
            return child;
        }
        return this;
    }

    public Graph walkMoveUp(){
        boolean [][] check = deepCopy(this.walkGetNextState()) ;
        int [][] x =  deepCopy(this.Cells);
        boolean chang = false;
        for (int i=this.Cells.length-1 ;i>=0 ;i--){
            for(int j =0;j< this.Cells[0].length;j++){
                if(this.Cells[i][j]>0 && check[i-1][j]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i-1][j]=temp;
                }
            }
        }
        if( chang && this.energyCost>=10){
            Graph child = new Graph( x ,this.Finish , this.map,this.moneyCost);
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 1;
            child.moneyCost = this.moneyCost + 0;
            child.timeCost = this.timeCost + 1/walkSpeed ;
            child.energyCost = this.energyCost -10;
            child.moveCount=this.moveCount+1;
            return child;
        }
        return this;
    }

    public Graph walkMoveDown(){
        boolean [][] check = deepCopy(this.walkGetNextState()) ;
        int [][] x =  deepCopy(this.Cells);
        boolean chang = false;

        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =0;j< this.Cells[0].length;j++){
                if(this.Cells[i][j]>0 && check[i+1][j]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i+1][j]=temp;
                }
            }
        }

        if( chang && this.energyCost>=10){
            Graph child = new Graph( x ,this.Finish , this.map,this.moneyCost );
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 1;
            child.moneyCost = this.moneyCost + 0;
            child.timeCost = this.timeCost + 1/walkSpeed ;
            child.energyCost = this.energyCost -10;
            child.moveCount=this.moveCount+1;
            return child;
        }
        return this;
    }


    public Graph taxiMoveRight(){
        boolean [][] check = deepCopy(this.taxiGetNextState()) ;
        int ii=0;
        int jj=0;
        boolean chang = false;
        int [][] x =  deepCopy(this.Cells);
        for (int i=0 ;i< this.Cells.length-1;i++){
            for(int j =0;j< this.Cells[0].length-1;j++){
                if( this.Cells[i][j]>0 && check[i][j+1]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i][j+1]=temp;
                    ii=i;
                    jj=j;
                }}}
        if( chang && this.moneyCost>1000){
            Graph child = new Graph( x ,this.Finish , this.map,this.moneyCost);
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 2;
            child.moneyCost = this.moneyCost - 1000;
            child.timeCost = this.timeCost + 1/this.map.TaxiSpeed[ii][jj] ;
            if(this.energyCost<100)
            child.energyCost = this.energyCost + 5;
            child.moveCount=this.moveCount+1;
            return child;
            // v = d/t
        }
        return this;
    }

    public Graph taxiMoveLeft(){
        boolean [][] check = deepCopy(this.taxiGetNextState()) ;
        int [][] x =  deepCopy(this.Cells);
        boolean chang = false;
        int ii=0;
        int jj=0;
        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =0;j< this.Cells[0].length;j++){
                if(this.Cells[i][j]>0 && check[i][j-1]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i][j-1]=temp;
                    ii=i; jj=j;
                }
            }
        }
        if(chang && this.moneyCost>1000){
            Graph child = new Graph( x ,this.Finish , this.map,this.moneyCost);
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 2;
            child.moneyCost = this.moneyCost - 1000;
            child.timeCost = this.timeCost + 1/this.map.TaxiSpeed[ii][jj] ;
            if(this.energyCost<100)
            child.energyCost = this.energyCost + 5;
            child.moveCount=this.moveCount+1;
            return child;
        }
        return this;
    }

    public Graph taxiMoveUp(){
        boolean [][] check = deepCopy(this.taxiGetNextState()) ;
        int [][] x =  deepCopy(this.Cells);
        boolean chang = false;
        int ii=0;
        int jj=0;
        for (int i=this.Cells.length-1 ;i>=0 ;i--){
            for(int j =0;j< this.Cells[0].length;j++){
                if(this.Cells[i][j]>0 && check[i-1][j]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i-1][j]=temp;
                    ii=i; jj=j;
                }
            }
        }
        if( chang && this.moneyCost>1000){
            Graph child = new Graph( x ,this.Finish , this.map,this.moneyCost);
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 2;
            child.moneyCost = this.moneyCost - 1000;
            child.timeCost = this.timeCost + 1/this.map.TaxiSpeed[ii][jj] ;
            if(this.energyCost<100) child.energyCost = this.energyCost + 5;
            child.moveCount=this.moveCount+1;
            return child;
        }
        return this;
    }

    public Graph taxiMoveDown(){
        boolean [][] check = deepCopy(this.taxiGetNextState()) ;
        int [][] x =  deepCopy(this.Cells);
        boolean chang = false;
        int ii=0;
        int jj=0;
        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =0;j< this.Cells[0].length;j++){
                if(this.Cells[i][j]>0 && check[i+1][j]){
                    chang=true;
                    int temp =x[i][j];
                    x[i][j]=0;
                    x[i+1][j]=temp;
                    ii=i; jj=j;
                }
            }
        }

        if( chang && this.moneyCost>1000){
            Graph child = new Graph( x ,this.Finish , this.map ,this.moneyCost);
            child.Cells=deepCopy(x);
            children.add(child);
            child.parent= this;
            child.moveType = 2;
            child.moneyCost = this.moneyCost - 1000;
            child.timeCost = this.timeCost + 1/this.map.TaxiSpeed[ii][jj] ;
            if(this.energyCost<100)
            child.energyCost = this.energyCost + 5;
            child.moveCount=this.moveCount+1;
            return child;
        }
        return this;
    }

    public boolean isFinal(){
        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =0;j< this.Cells[0].length;j++){
                if( this.Cells[i][j] > 0 && this.Cells[i][j] != this.Finish[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSame(Graph g){
        boolean check= true;
        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =0;j< this.Cells[0].length;j++){
                if( this.Cells[i][j] != g.Cells[i][j] ){
                    check = false;
                }
            }
        }

        return check;
    }

    public Graph rideBus(){
        for (int i=0 ;i< this.Cells.length;i++){
            for(int j =0;j< this.Cells[0].length;j++){
                if( this.Cells[i][j]>0 && this.map.Station[i][j]>0){
                    int x = findNextStationX(i,j);
                    int y = findNextStationY(i,j);
                    return busMove(this, x , y ,this.map.Station[i][j] )  ;
                }
            }
        }
        return this;
    }

    public Graph busMove( Graph graph, int x , int y , int value){
        if( graph.energyCost <= 0){// graph.children.remove(0);
            return this; }
        if( graph.map.findX(graph)==x && graph.map.findY(graph)==y ){
            return graph;
        }
        int [][]arr = deepCopy( graph.map.getnextpoint(graph , value) );
        //System.out.println(graph.moneyCost);
        Graph child = new Graph( arr ,this.Finish , this.map,graph.moneyCost);
        graph.children.add(child);
        child.parent= graph;
        child.moveType = 3;
        if( graph.moveType!=3 ){child.moneyCost = graph.moneyCost - 400;
            child.timeCost = graph.timeCost+ graph.stationWaitTime;
        }
        else{ child.moneyCost = graph.moneyCost+0 ;  }
        child.timeCost = graph.timeCost + 1/this.map.busSpeed[this.map.findX(child)][this.map.findY(child)];
        child.energyCost = graph.energyCost - 5;
        child.moveCount=this.moveCount+1;
        Graph g = busMove(child ,x,y , value );
        if(g.isSame(this)){return this;}
        //graph.children.remove(graph.children.size()-1);
        return g;
    }

    public int findNextStationX(int x , int y){
        int stationCurrent = this.map.Station[x][y];
        for (int i=0 ;i< this.map.Station.length;i++){
            for(int j =0;j< this.map.Station[0].length;j++){
                if( this.map.Station[i][j] == stationCurrent+1 ){
                    return i;
                }
            }
        }
        return this.map.firstStaionX;
    }
    public int findNextStationY(int x , int y){
        int stationCurrent = this.map.Station[x][y];
        for (int i=0 ;i< this.map.Station.length;i++){
            for(int j =0;j< this.map.Station[0].length;j++){
                if( this.map.Station[i][j] == stationCurrent+1 ){
                    return j;
                }
            }
        }
        return this.map.firstStaionY;
    }

    public int findX(int[][] x){
        for (int i=0 ;i< x.length;i++){
            for(int j =0;j< x[0].length;j++){
                if( x[i][j]>0 ){
                    return i;
                }
            }
        }
        return -1;
    }
    public int findY(int[][] x){
        for (int i=0 ;i< x.length;i++){
            for(int j =0;j< x[0].length;j++){
                if( x[i][j]>0 ){
                    return j;
                }
            }
        }
        return -1;
    }

    public  void ExpandMove(){
        if(this.isFinal()) return;
       // System.out.println(this.moneyCost);
        rideBus();
        taxiMoveUp();
        taxiMoveDown();
        taxiMoveRight();
        taxiMoveLeft();
        walkMoveRight();
        walkMoveLeft();
        walkMoveUp();
        walkMoveDown();



    }



}
