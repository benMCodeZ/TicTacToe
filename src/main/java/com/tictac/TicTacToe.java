package com.tictac;

// util package - from java | * import all util objects
import java.util.*;

// art package - from code | import Ascii object
import code.art.Ascii;

public class TicTacToe {
    
    static final Scanner scan = new Scanner(System.in);
    static final Random rand = new Random();
    static final List<Integer> playerPosition = new ArrayList<Integer>();
    static final List<Integer> aiPosition = new ArrayList<Integer>();
    
    static String playerWin = "Congratulations you won!";
    static String aiWin = "Ai is won";
    
    static char playerCharacter;
    static char aiCharacter;
    static String behavior;
    
    
    /*
      | Method to clear console
    */
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /*
      | Game Board printer |
    */
    /**
     * 
     * @param char[][] {gameBoard} - contain 2d array character
     * ---------------------------------------------------------
     * This method prints the 2d array value in table style
     * ---------------------------------------------------------
     * 
     * */
    private static void printGameBoard(char[][] gameBoard) {
        
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    
    /*
      | Symbol position placement |
    */
    /**
     * 
     * @param char[][] {gameBoard} - contain 2d array character
     * @param int {pos} - contain the position of symbol piece
     * @param String {user} - contain user (player or ai)
     * 
     * */
    private static void placement(char[][] gameBoard, int pos, String user) {
        
        // check if the user value equals player or ai
        // sym = 'X' - if(player)
        // sym = 'O' - if(not) or (ai)
        char sym = ' ';
        if (user.equals("player")) {
            sym = playerCharacter != 'n' ? playerCharacter : 'X';
            playerPosition.add(pos);
        } else {
            sym = aiCharacter != 'n' ? aiCharacter : 'O';
            aiPosition.add(pos);
        }
        
        int i, max = 3;
        int row = 0, col = -2;
        
        for (i = 0; i <= 9; i++) {
            if (col > 4) col = 0;
            if (pos == i) {
                while (i > max) {
                    row += 2;
                    max = max * 2;
                }
                gameBoard[row][col] = sym;
            }
            col += 2;
        }
        
    }
    
    /*
       | Check Game Winner |
    */
    /**
     * 
     * ------------------------------------------------------------
     * This method check if the each player contain the winning pattern
     * If the player contain it, this player is win else lose
     * ------------------------------------------------------------
     * 
     * */
    private static String gameWinner() {
        
        // position pattern;
        int[][] p = {
            {1, 2, 3}, // top row
            {4, 5, 6}, // mid row
            {7, 8, 9}, // bottom row
            {1, 4, 7}, // left column
            {2, 5, 8}, // mid column
            {3, 6, 9}, // right column
            {1, 5, 9}, // cross 1
            {7, 5, 3}, // cross 2
        };
        
        int row;
        
        final List<List> conditions = new ArrayList<List>();
        
        for (row = 0; row < p.length; row++) {
            conditions.add(Arrays.asList(p[row][0], p[row][1], p[row][2]));
        }
        
        for (List c : conditions) {
            if (playerPosition.containsAll(c)) {
                return playerWin;
            } else if (aiPosition.containsAll(c)) {
                return aiWin;
            } else if (playerPosition.size() + aiPosition.size() == 9) {
                return "EQUALS";
            }
        }
        
        
        return "";
    }
    
    /*
      | Game Start |
    */
    private static void startGame() {
        
        System.out.println("\nEnter the character of each user, if you want the default character enter 'n' and it will give you 'X' and 'O' character\n" );
        System.out.print("Enter player character: ");
        playerCharacter = scan.next().charAt(0);
        
        System.out.print("Enter ai character: ");
        aiCharacter = scan.next().charAt(0);
        
        scan.nextLine();
        
        System.out.println("\nEnter the player behavior");
        behavior = scan.nextLine();
        
        boolean isBehaviorEquals = behavior.toLowerCase().equals("player") || behavior.toLowerCase().equals("ai");
        
        while (isBehaviorEquals == false) {
            
            System.out.println("The word you enter is undefined");
            System.out.println("Please enter the correct word");
            behavior = scan.nextLine();
            
            isBehaviorEquals = behavior.toLowerCase().equals("player") || behavior.toLowerCase().equals("ai");
            
        }
        
        // game broard 
        final char[][] gameBoard = {
           {' ','|',' ','|',' '},
           {'_','+','_','+','_'},
           {' ','|',' ','|',' '},
           {'_','+','_','+','_'},
           {' ','|',' ','|',' '},   
        };
        
        printGameBoard(gameBoard);
        
        String user = " ";
        int playerPos = 0, aiPos = 0; // user/computer position
        
        while(true) {
            
            System.out.print("Enter your placement numer (1-9): ");
            //playerPos = scan.nextInt();
            
            boolean isContain;
            
            do {
                
                System.out.println("This position is taken!");
                System.out.print("Enter a correct position: ");
                
                if (behavior.toLowerCase().equals("player")) {
                    
                    playerPos = scan.nextInt();
                    
                } else if (behavior.toLowerCase().equals("ai")){
                    
                    playerPos = rand.nextInt(9) + 1;
                    
                }
                
                isContain = playerPosition.contains(playerPos) || aiPosition.contains(playerPos);
                
            } while(isContain);
            
            System.out.println(playerPos);
            
            user = "player";
            placement(gameBoard, playerPos, user);
            String winner = gameWinner();
            if (winner.length() > 0) {
                printGameBoard(gameBoard);
                System.out.println();
                System.out.println("Result: ");
                System.out.println(winner);
                break;
            }
            
            user = "ai";
            
            aiPos = rand.nextInt(9) + 1;
            
            boolean isContainAI;
            
            do {
                
                aiPos = rand.nextInt(9) + 1;
                isContainAI = playerPosition.contains(aiPos) || aiPosition.contains(aiPos);
                
            } while(isContainAI);
            
            placement(gameBoard, aiPos, user);
            
            printGameBoard(gameBoard);
            
            winner = gameWinner();
            
            System.out.println(winner);
            if (winner.length() > 0) {
                printGameBoard(gameBoard);
                System.out.println();
                System.out.println("Result: ");
                System.out.println(winner);
                break;
            }
            
        }
    }
    
    /*
      | Main method |
    */
    public static void main(String[] args) {
        
        clearConsole();
        
        // game title
        String[][] title = {
           {"-------------------------------------"},
           {" ###  #      ###         ###         "},
           {" #      ###  #   ## ###  #  ### ###  "},
           {" #   #  #    #  # # #    #  # # ##   "},
           {" #   ## ###  #  ### ###  #  ### ###  "},
           {" #           #           #           "},
           {"-------------------------------------"},
        };
        
        String[][] text = {
            {"The Terminal Game"},
        };
        
        String[][] text2 = {
            {"Enter the keyword 'game-start' to start the game"},
        };
        
        final var printTitle = new Ascii(title, 4, 0);
        printTitle.getDisplay();
        
        final var printText = new Ascii(text, 14, 0);
        printText.getDisplay();
        
        final var printText2 = new Ascii(text2, 0, 0);
        printText2.getDisplay();
        
        String keyword = "game-start";
        String userInput = scan.nextLine();
        boolean check = (keyword.toLowerCase().equals(keyword));
        
        if (check) {
            startGame();
        } else {
            System.out.println("Keyword is undefiend, no execution!");
            return;
        }
        
        
    }
}

