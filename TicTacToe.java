package tictactoe;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.in;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


///////////////////////////////////////////////// public class
public class TicTacToe extends JFrame implements ActionListener
{
    //initialization 
    
    
    private  Player player1;
    private  Player player2;
    private  Player CAT;
    //track players turn
    private boolean xTurn = true;
    //array of buttons
    private static JButton[] buttonArray = new JButton[9];
    //winning array
    private static int[] winsArray =
            {
                7,56,448,73,146,292,273,84
            };
    //labels
    private JLabel player1Name;
    private JLabel player2Name;
    private JLabel player1Score;
    private JLabel player2Score;
    private JLabel playerCATscore;

    public TicTacToe() //contructor
    {
        Scanner in1;
        Scanner in2;
        in1 = new Scanner(System.in);
        System.out.println("enter player name 1");
        in2 = new Scanner(System.in);
        System.out.println("enter player name 2");
       
        
        
        Font bCourier = new Font("Courier", Font.BOLD, 14); //custimization
        player1 = new Player(in1.nextLine());
        player2 = new Player(in2.nextLine());
        CAT = new Player("CAT");

        player1.setMarker('X');
        player2.setMarker('O');
        //labels
        player1Name = new JLabel(player1.getName());
        player2Name = new JLabel(player2.getName());
        player1Score = new JLabel(player1.getScore() + "");
        player2Score = new JLabel(player2.getScore() + "");
        playerCATscore = new JLabel(CAT.getScore() + "");
        player1Name.setFont(bCourier);
        player2Name.setFont(bCourier);
        playerCATscore.setFont(bCourier);
                
        //load button array + panel
        JPanel gameboard = new JPanel();
        gameboard.setBackground(new Color(85,92,69)); //custimization
        gameboard.setLayout(new GridLayout(6, 3, 3, 3));
        int myBinary = 1;
        for (int index = 0; index < 9; index++)
        {
            //create  button add to btnArray
            buttonArray[index] = new JButton();
            buttonArray[index].setFont(bCourier); //custimazation
            buttonArray[index].setBackground(new Color(130,140,81)); 
            // ad the listener
            buttonArray[index].addActionListener(this);
            //set button attribute to myBinary number
            buttonArray[index].setActionCommand(myBinary + "");
            //add to gameBoard
            gameboard.add(buttonArray[index]);

            //increment mybinary for next buttonj
            myBinary = myBinary * 2;
        }
        gameboard.add(new JLabel("Player 1"));
        gameboard.add(player1Name);
        gameboard.add(player1Score);
        gameboard.add(new JLabel("Player 2"));
        gameboard.add(player2Name);
        gameboard.add(player2Score);
        gameboard.add(new JLabel("CAT"));
        gameboard.add(new JLabel("???"));
        gameboard.add(playerCATscore);
        this.add(gameboard);
        this.setTitle("TIC TAC TOE");
        this.setSize(400, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e);
        System.out.println(player1.getName() + " " + player1.getMarker());
        System.out.println(player2.getName() + " " + player2.getMarker());
        System.out.println("Button Pressed " + e.getActionCommand());
        //casting button
        JButton pressedButton = (JButton) e.getSource();
        if (pressedButton.getText().equals(""))
        {
        if (xTurn)
        {   
            pressedButton.setBackground( new Color(38,139,210)); //custimization
            pressedButton.setText("X");
            if (player1.getMarker() == 'X')
            {              
                //player 1 is currently x and sum his total
                player1.sumTotal(Integer.parseInt(e.getActionCommand()));
                //check if either payer won
                if (checkForWinner(player1.getTotal()))
                {  
                    player1.sumScore();
                    player1Score.setText(player1.getScore() + "");
                    resetGameBoard();
                } else
                {
                    xTurn = false; //O's Turn
                }
            } else
            {               
                //player 2 is currently X
                player2.sumTotal(Integer.parseInt(e.getActionCommand()));
                //check if either payer won
                if (checkForWinner(player2.getTotal()))
                {   
                    
                    player2.sumScore();
                     player2Score.setText(player2.getScore() + "");
                    resetGameBoard();
                } else
                {
                    xTurn = false; //back to O's turn
                }               
            }
        } else 
        {
            pressedButton.setBackground(new Color(84,53,62));
            pressedButton.setText("O");
            
            if (player1.getMarker() == 'O')
            {
                //player 1 is currently O add the total
                player1.sumTotal(Integer.parseInt(e.getActionCommand()));
                //check if either payer won
                if (checkForWinner(player1.getTotal()))
                {
                    player1.sumScore();
                    player1Score.setText(player1.getScore() + "");
                    resetGameBoard();
                } else
                {
                    xTurn = true; //O's turn
                }              
            } else
            {
                //player 2 is currently x
                player2.sumTotal(Integer.parseInt(e.getActionCommand()));
                //check if either payer won
                if (checkForWinner(player2.getTotal()))
                {                 
                    player2.sumScore();
                    player2Score.setText(player2.getScore() + "");
                    resetGameBoard();
                } else
                {
                    xTurn = true; //back to x's turn
                }
            }
            xTurn = true;
        }
        if (player1.getTotal() + player2.getTotal() == 511)
        {
            
            CAT.sumScore();
            playerCATscore.setText(CAT.getScore() + "");
            System.out.println("The CAT WON");
            resetGameBoard();
        }
    } 
    } //end of action
    public boolean checkForWinner(int total)
    {
        for (int index = 0; index < winsArray.length; index++)
        {
            if ((winsArray[index] & total) == winsArray[index])
            {
               
                return true; //bitwise match - 
            }
        }
        return false;

    } //end of check for winner

    public void resetGameBoard()
    {
        //clear the gb 
        for (int index = 0; index < buttonArray.length; index++)
        {
            buttonArray[index].setBackground(new Color(130,140,81));
            buttonArray[index].setText("");
        }
        //reset both players totat to zero
        player1.resetTotal();
        player2.resetTotal();
        if (player1.getMarker() == 'X')
        {
            buttonArray[0].setBackground(new Color(130,140,81));
            player1.setMarker('O');
            player2.setMarker('X');
        } else
        {
            player1.setMarker('X');
            player2.setMarker('O');
        }
        xTurn = true;
    } //end of reset

    public static void main(String[] args)
    {
        
        
        new TicTacToe().setVisible(true);

    } //main
}//class
