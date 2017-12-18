
package tictactoe;

public class Player
{
  private String name="";
  private int total=0;  //total from the board or per game total
  private char marker =' ';
  private int score =0; //meta score over multiple game by this player

    public Player(String name) //constructor
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public int getTotal()
    {
        return total;
    }

    public void sumTotal(int total)
    {
        this.total = this.total+total; //calculates the total
    }
    public void resetTotal() 
    {
        this.total = 0;
    }

    public char getMarker()
    {
        return marker;
    }

    public void setMarker(char marker) //sets up players marker
    {
        this.marker = marker;
    }

    public int getScore() //games one
    {
        return score;
    }

    public void sumScore() //adds one to score when a player has won.
    {
        this.score++;
    }
  
  
  
  
  
  
}//class
