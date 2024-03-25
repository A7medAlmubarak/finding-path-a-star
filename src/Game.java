import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;




public class Game {
    public Game() {

        Graph ob = new Graph(); //Instance of elf class
        ob.display();

        while (!ob.isFinal()) {
            System.out.println("(1) => Walk Mode | (2) => Taxi mode | (3) => Bus Mode | (4) => BFS | " +'\n'+
                    "(5) => A Star best cost money | (6) =>  A Star best cost energy | (7) => A Star OPT cost");

            Scanner s = new Scanner(System.in);
            char inn = s.next().charAt(0);
            boolean taxiMode = false;
            boolean walkMode = false;
            boolean busMode = false;

            switch (inn) {
                case '1': {
                    walkMode = true;
                    while (!ob.isFinal() && walkMode) {
                        System.out.println("(w) => up | (s) => down | (a) => left | (d) => right | (e) => exit WalkMode");
                        Scanner ss = new Scanner(System.in);
                        char innn = ss.next().charAt(0);

                        switch (innn) {
                            case 'w': {
                                ob = ob.walkMoveUp();
                                ob.display();

                                break;
                            }
                            case 's': {
                                ob = ob.walkMoveDown();
                                ob.display();

                                break;
                            }
                            case 'a': {
                                ob = ob.walkMoveLeft();
                                ob.display();

                                break;
                            }
                            case 'd': {
                                ob = ob.walkMoveRight();
                                ob.display();
                                break;
                            }
                            case 'e': {
                                walkMode = false;
                                break;
                            }
                        }
                        System.out.println("money cost :"+ ob.moneyCost +"\n"+"yeb");

                    }
                    break;
                }


                case '2': {
                    taxiMode = true;
                    while (!ob.isFinal() && taxiMode) {
                        System.out.println("(w) => up | (s) => down | (a) => left | (d) => right | (e) => exit TaxiMode");
                        Scanner ss = new Scanner(System.in);
                        char innn = ss.next().charAt(0);

                        switch (innn) {
                            case 'w': {
                                ob = ob.taxiMoveUp();
                                ob.display();

                                break;
                            }
                            case 's': {
                                ob = ob.taxiMoveDown();
                                ob.display();

                                break;
                            }
                            case 'a': {
                                ob = ob.taxiMoveLeft();
                                ob.display();

                                break;
                            }
                            case 'd': {
                                ob = ob.taxiMoveRight();
                                ob.display();
                                break;
                            }
                            case 'e': {
                                taxiMode = false;
                                break;
                            }
                        }
                        System.out.println("money cost :"+ ob.moneyCost +"\n"+"yeb");

                    }
                    break;
                }
                case '3': {
                    busMode = true;
                    while (!ob.isFinal() && busMode) {
                        System.out.println("(y) => Go to next Station | (n) => exit BusMode");
                        Scanner ss = new Scanner(System.in);
                        char innn = s.next().charAt(0);

                        switch (innn) {
                            case 'y': {
                                ob = ob.rideBus();
                                ob.display();

                                break;
                            }

                            case 'n': {
                                busMode = false;
                                break;
                            }
                        }

                    }
                    break;
                }

                case '4': {
                    long startTime = System.currentTimeMillis(); // cal time
                    BFS ui = new BFS();


                    ArrayList<Graph> solution = ui.BFS(ob);
                    if (solution.size() > 0) {
                        Collections.reverse(solution);
                        for (int i = 0; i < solution.size(); i++) {
                            solution.get(i).display();
                        }
                    } else {
                        System.out.println("there is no solution .... ");
                        return;

                    }
                    long endTime = System.currentTimeMillis(); // end cal time
                    System.out.println("****************************************"+'\n'+ "               U won !!!!         "+'\n'+ "****************************************");

                    System.out.println("Compile time : " + (endTime - startTime) + " milliseconds");
                    return;


                    //break;
                }
                case '5': {
                    long startTime = System.currentTimeMillis(); // cal time
                    Astar2 ui = new Astar2();


                    ArrayList<Graph> solution = ui.Astar2(ob , 1);
                    if (solution.size() > 0) {
                        Collections.reverse(solution);
                        for (int i = 0; i < solution.size(); i++) {
                            solution.get(i).display();
                        }
                    } else {
                        System.out.println("there is no solution .... ");
                        return;

                    }
                    long endTime = System.currentTimeMillis(); // end cal time
                    System.out.println("****************************************"+'\n'+ "               U won !!!!         "+'\n'+ "****************************************");

                    System.out.println("Compile time : " + (endTime - startTime) + " milliseconds");
                    return;


                    //break;
                }
                case '6': {
                    long startTime = System.currentTimeMillis(); // cal time
                    Astar2 ui = new Astar2();


                    ArrayList<Graph> solution = ui.Astar2(ob , 2);
                    if (solution.size() > 0) {
                        Collections.reverse(solution);
                        for (int i = 0; i < solution.size(); i++) {
                            solution.get(i).display();
                        }
                    } else {
                        System.out.println("there is no solution .... ");
                        return;

                    }
                    long endTime = System.currentTimeMillis(); // end cal time
                    System.out.println("****************************************"+'\n'+ "               U won !!!!         "+'\n'+ "****************************************");

                    System.out.println("Compile time : " + (endTime - startTime) + " milliseconds");
                    return;


                    //break;
                }
                case '7': {
                    long startTime = System.currentTimeMillis(); // cal time
                    Astar2 ui = new Astar2();


                    ArrayList<Graph> solution = ui.Astar2(ob , 3);
                    if (solution.size() > 0) {
                        Collections.reverse(solution);
                        for (int i = 0; i < solution.size(); i++) {
                            solution.get(i).display();

                        }
                    } else {
                        System.out.println("there is no solution .... ");
                        return;

                    }
                    long endTime = System.currentTimeMillis(); // end cal time
                    System.out.println("****************************************"+'\n'+ "               U won !!!!         "+'\n'+ "****************************************");

                    System.out.println("Compile time : " + (endTime - startTime) + " milliseconds");
                    return;


                    //break;
                }


            }

            //ob.display();

        }
    }


}
