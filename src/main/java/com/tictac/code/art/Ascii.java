package code.art;

/**
 * 
 * ----------------------------------------------------------
 * This object print the 2d array list in table style
 * and to display the 2d array call the method "getDisplay()"
 * ----------------------------------------------------------
 * 
 * */
public final class Ascii {
    
    public String[][] arr;
    public int x;
    public int y;
    
    /*
      | Ascii object constructor |
    */
    /**
     * 
     * @param String[][] {arr} - this contain 2d array ascii list
     * @param int {x} - this variable is x position of ascii
     * @param int {y} - this variable is y position of ascii
     * 
     * */
    public Ascii(String[][] arr, int x, int y) {
        this.arr = arr;
        this.x = x;
        this.y = y;
    }
    
    /**
     * 
     * ------------------------------------------------------
     * This method display the 2d array in console
     * -------------------------------------------------------
     * 
     * */
    public void getDisplay() {
        
        String indent = "";
        
        for (int posX = 0; posX < this.x; posX++) {
            indent += " ";
        }
        
        for (int posY = 0; posY < this.y; posY++) {
            System.out.println();
        }
        
        for (String[] i : this.arr) {
            
            System.out.print(indent);
            
            for (String k : i) {
                
                System.out.print(k);
            }
            
            System.out.println();
        }
    }
}
