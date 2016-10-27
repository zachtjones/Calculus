package ui;
import java.awt.Window;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.JDialog;
import operations.Operation;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Summation {

	/**
	 * Launch the summation.
	 * @wbp.parser.entryPoint
	 */
	public static void showSummation(Operation o, Window parent) {
		JDialog d = new JDialog(parent);
		d.setTitle("Partial sums");
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		d.setModal(true);
		d.setBounds(100, 100, 450, 300);
		d.getContentPane().setLayout(null);
		

		JLabel lblSummation = new JLabel("");
		lblSummation.setBounds(6, 6, 438, 16);
		d.getContentPane().add(lblSummation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 438, 238);
		d.getContentPane().add(scrollPane);
		
		JTextArea txtrResult = new JTextArea();
		scrollPane.setViewportView(txtrResult);
		txtrResult.setWrapStyleWord(true);
		txtrResult.setAutoscrolls(true);
		txtrResult.setText("First 10 partial sums: ");
		
		int start = (int)NumberInputBox.show(d, "Enter the start of the summation");
		
		for(int i = start; i < start + 10; i++){
			txtrResult.append("" + partialsum(start, i, o));
			if(i != start + 9){
				txtrResult.append(", ");
			}
		}
		txtrResult.append("\n");
		
		txtrResult.append("S" + CalculusMain.subscript("100") + " =" + partialsum(start, start+100, o) + "\n");
		
		txtrResult.append("S" + CalculusMain.subscript("1000") + " =" + partialsum(start, start+1000, o) + "\n");
				
		d.setVisible(true);
	}
	
	/**
	 * Computes a partial sum of a function o from start to end (inclusive). 
	 * If (end - start) is very large, this may take a long time to compute
	 * @param start The start to compute the sum from
	 * @param end The end to compute the sum to
	 * @param o The function to compute the sum of from start to end
	 * @return
	 */
	public static BigDecimal partialsum(long start, long end, Operation o){
		BigDecimal returnValue = new BigDecimal(0);
		for(long i = start; i <= end; i++){
			returnValue = returnValue.add(o.evaluate(new BigDecimal(i)));
		}
		returnValue = returnValue.round(new MathContext(6));
		return returnValue;
	}
	
}
