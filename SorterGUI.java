
/**
 * Creates GUI for the number sorter.
 * 
 * author@ GrayKnight
 * version@ Ch9 sorting 1/8/11
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SorterGUI extends JPanel
{
    private JButton minLabel, maxLabel, quantLabel;
    private JComponent[] components = new JComponent[12];

    private JTextArea arrayDisplayArea; //shows the numbers
    private ArrayGenerator myTosser;
    private int[] randomValues; //Array to hold the values
    
    private int myMinValue = 0; //lowest possible value in the array
    private int myMaxValue = 100; //highest possible
    private int myNumQuant = 50; //number of values in the array
    
    private final int FRAME_WIDTH = 300, FRAME_HEIGHT = 600;
    
    
    /**
     * Constructor for objects of class SorterGUI
     */
    public SorterGUI()
    {  
       //instantiate an ArrayGenerator with the default values
       myTosser = new ArrayGenerator(myNumQuant, myMinValue, myMaxValue); 
       
       components[0] = new JLabel(myNumQuant + " Numbers");//quantLabel
       components[1] = new JButton("/\\");//increaseNumBtn 
       components[2] = new JButton("\\/");//decreaseNumBtn 
       components[3] = new JLabel("Lowest Possible = " + myMinValue);//minLabel
       components[4] = new JButton("/\\");//increaseMinBtn 
       components[5] = new JButton("\\/");//decreaseMinBtn 
       components[6] = new JLabel("Highest Possible = " + myMaxValue);//maxLabel
       components[7] = new JButton("/\\");//increaseMaxBtn 
       components[8] = new JButton("\\/");//decreaseMaxBtn 
       components[9] = new JButton("Create Numbers");//makeArrayBtn   
       components[10] = new JButton("Sort /\\");//ascendSortBtn  
       components[11] = new JButton("Sort \\/");//descendSortBtn 
       
       ButtonListener Listener = new ButtonListener();
       for(int x = 0; x < components.length; x++)
       {
           if(components[x] instanceof JButton)
                ((JButton)components[x]).addActionListener(Listener);
                
                //buttons
           if(x%3 != 0) components[x].setPreferredSize(new Dimension(FRAME_WIDTH/4, 20));
                //Labels
           else components[x].setPreferredSize(new Dimension(FRAME_WIDTH/2, 20));
           
       }
       
       for(int x = 0; x < components.length; x++)
       {
           add(components[x]);
       }
       
       arrayDisplayArea = new JTextArea();
       Font font = new Font("courier", Font.PLAIN, 14);
       arrayDisplayArea.setFont(font);
       arrayDisplayArea.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT - 80));
       //arrayDisplayArea.setBackground(Color.RED);
       add(arrayDisplayArea);
       setPreferredSize(new Dimension(FRAME_WIDTH + 20, FRAME_HEIGHT + 20));
    }//end of constructor


    /**
     * Private Listener Class.
     */
    private class ButtonListener implements ActionListener
    {
        private int index = 0;
        private final int INCREMENT = 10;
        
        public void actionPerformed(ActionEvent event)
        {  
            for(int x = 0; x < components.length; x++)
                if(event.getSource() == components[x]) index = x;
            
                if (index < 9) updateArrayParameters(index);
                else
                {
                    if (index == 9) myTosser = new ArrayGenerator(myNumQuant, myMinValue, myMaxValue);
                    if (index == 10) myTosser.ascendingSort();
                    if (index == 11) myTosser.descendingSort();
                    displayArray();
                }
                                
        }//end action performed method    
        
        private void updateArrayParameters(int x)
        {
            if(x == 1 && myNumQuant < 130) //130 would be the maximum number of values
            {
                myNumQuant += INCREMENT;
                ((JLabel)components[0]).setText(myNumQuant + " Numbers");
            }
            if(x == 2 && myNumQuant > INCREMENT) //INCREMENT would be the minimum number of values
            {
                myNumQuant -= INCREMENT;
                ((JLabel)components[0]).setText(myNumQuant + " Numbers");
            }
            if(x == 4 && myMinValue + INCREMENT < myMaxValue) 
            {//the minimum possible value must be smaller than the maximum possible value
                myMinValue += INCREMENT;
                ((JLabel)components[3]).setText("Lowest Possible = " + myMinValue);
            }
            if(x == 5 && myMinValue > -500) 
            {//-500 would be the minimum possible value in the array
                myMinValue -= INCREMENT;
                ((JLabel)components[3]).setText("Lowest Possible = " + myMinValue);
            } 
            if(x == 7 && myMaxValue < 500) 
            {//500 would be the maximum possible value in the array
                myMaxValue += INCREMENT;
                ((JLabel)components[6]).setText("Highest Possible = " + myMaxValue);
            }
            if(x == 8 && myMinValue + INCREMENT < myMaxValue) 
            {//the minimum possible value must be smaller than the maximum possible value
                myMaxValue -= INCREMENT;
                ((JLabel)components[6]).setText("Highest Possible = " + myMaxValue);
            }
        }
        
        private void displayArray()
        {
            int[] array = myTosser.getArray();
            String displayStr = "";
            for(int x = 0; x < array.length; x++)
            {
                String temp = "";
                temp += array[x];
                while(temp.length() < 5) temp = " " + temp; //each # will take 5 spaces
                displayStr += temp + "  ";
                if((x + 1)%5 == 0) displayStr += "\n";
            }
            arrayDisplayArea.setText(displayStr);
        }
        
    }//end inner class
}//end of class
