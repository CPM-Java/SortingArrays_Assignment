/**
 * SorterMain is the starting point for execution.
 * Contains the code to intitialize the frame
 * 
 * author@ GrayKnight
 * version@ Ch9 sorting 1/8/11
 */

 import javax.swing.JFrame;

public class SorterMain
{

	public static void main(String[] Args)
	{
	    JFrame frame = new JFrame("Sorting Madness");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(new SorterGUI());
	    frame.pack();
	    frame.setLocation(225,30);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}

}
