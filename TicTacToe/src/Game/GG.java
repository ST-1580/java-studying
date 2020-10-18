package Game;

import java.util.Scanner;
import Players.*;

public class GG {
    public static void main (String[] args) {
        int result = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Please give type of board (matrix = 1, romb = 2)");
        while (true) {
            if (in.hasNextInt()) {
                int type = in.nextInt();
                if (type == 1) {
                    System.out.println("Please give number of people (2, 3 or 4)");
                    String h = in.nextLine();
                    final Game game;
                    int z = 0;
                    while (true) {
                        if (in.hasNextInt()) {
                            z = in.nextInt();
                            if (z == 2) {
                                System.out.println("Please give type of opposite player (human = 1, random = 2, sequential = 3");
                                h = in.nextLine();
                                while (true) {
                                    if (in.hasNextInt()) {
                                        int s = in.nextInt();
                                        if (s == 1) {
                                            game = new Game(false, new HumanPlayer(), new HumanPlayer());
                                            break;
                                        } else if (s == 2) {
                                            game = new Game(false, new HumanPlayer(), new RandomPlayer());
                                            break;
                                        } else if (s == 3) {
                                            game = new Game(false, new HumanPlayer(), new SequentialPlayer());
                                            break;
                                        }  else {
                                            System.out.println("Wrong input. Please try again");
                                        }
                                    } else {
                                        String ss = in.nextLine();
                                        System.out.println("Wrong input. Please try again");
                                    }
                                }
                                break;
                            } else if (z == 3) {
                                game = new Game(false, new HumanPlayer(), new HumanPlayer(), new HumanPlayer());
                                break;
                            } else if (z == 4) {
                                game = new Game(false, new HumanPlayer(), new HumanPlayer(), new HumanPlayer(), new HumanPlayer());
                                break;
                            } else {
                                System.out.println("Wrong input. Please try again");
                            }
                        } else {
                            String ss = in.nextLine();
                            System.out.println("Wrong input. Please try again");
                        }
                    }
                    System.out.println("Please give n, m, k");
                    h = in.nextLine();
                    int n = 0, m = 0, k = 0;
                    while (true) {
                        if (in.hasNextInt()) {
                            n = in.nextInt();
                            if (in.hasNextInt()) {
                                m = in.nextInt();
                                if (in.hasNextInt()) {
                                    k = in.nextInt();
                                    if (n > 0 && m > 0 && k <= Math.max(m , n) && k > 0) {
                                        break;
                                    } else {
                                        System.out.println("Wrong input. Please try again");
                                    }
                                } else {
                                    String ss = in.nextLine();
                                    System.out.println("Wrong input. Please try again");
                                }
                            } else {
                                String ss = in.nextLine();
                                System.out.println("Wrong input. Please try again");
                            }
                        }  else {
                            String ss = in.nextLine();
                            System.out.println("Wrong input. Please try again");
                        }
                    }
                    result = game.play(new ServerTicTacToeBoard(n, m, k), n, m, z);
                    break;
                } else if (type == 2){
                    System.out.println("Please give number of people (2, 3 or 4)");
                    String h = in.nextLine();
                    final GameRomb game;
                    int z = 0;
                    while (true) {
                        if (in.hasNextInt()) {
                            z = in.nextInt();
                            if (z == 2) {
                                System.out.println("Please give type of opposite player (human = 1, random = 2, sequential = 3)");
                                h = in.nextLine();
                                while (true) {
                                    if (in.hasNextInt()) {
                                        int s = in.nextInt();
                                        if (s == 1) {
                                            game = new GameRomb(false, new HumanPlayer(), new HumanPlayer());
                                            break;
                                        } else if (s == 2) {
                                            game = new GameRomb(false, new HumanPlayer(), new RandomPlayer());
                                            break;
                                        } else if (s == 3) {
                                            game = new GameRomb(false, new HumanPlayer(), new SequentialPlayer());
                                            break;
                                        } else {
                                            System.out.println("Wrong input. Please try again");
                                        }
                                    } else {
                                        String ss = in.nextLine();
                                        System.out.println("Wrong input. Please try again");
                                    }
                                }
                                break;
                            } else if (z == 3) {
                                game = new GameRomb(false, new HumanPlayer(), new HumanPlayer(), new HumanPlayer());
                                break;
                            } else if (z == 4) {
                                game = new GameRomb(false, new HumanPlayer(), new HumanPlayer(), new HumanPlayer(), new HumanPlayer());
                                break;
                            } else {
                                System.out.println("Wrong input. Please try again");
                            }
                        } else {
                            String ss = in.nextLine();
                            System.out.println("Wrong input. Please try again");
                        }
                    }
                    System.out.println("Please give n - size of figure and k");
                    h = in.nextLine();
                    int n = 0, k = 0;
                    while (true) {
                        if(in.hasNextInt()) {
                            n = in.nextInt();
                            if (in.hasNextInt()) {
                                k = in.nextInt();
                                if (n > 0 && k > 0 && k <= n) {
                                    break;
                                } else {
                                    System.out.println("Wrong input. Please try again");
                                }
                            } else {
                                String ss = in.nextLine();
                                System.out.println("Wrong input. Please try again");
                            }
                        } else {
                            String ss = in.nextLine();
                            System.out.println("Wrong input. Please try again");
                        }
                    }
                    result = game.play(new ServerTicTacToeRomb(n, n, k), n, n, z);
                    break;
                } else {
                    System.out.println("Wrong input. Please try again");
                }
            } else {
                String ss = in.nextLine();
                System.out.println("Wrong input. Please try again");
            }
        }
        System.out.println("Game result: win " + result);
    }
}
