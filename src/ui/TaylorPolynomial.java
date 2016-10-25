package ui;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import operations.Factorial;
import operations.Operation;
import javax.swing.JTextArea;

public class TaylorPolynomial {

	/**
	 * Shows the UI for a taylor Polynomial and then calculates it
	 * @throws CloneNotSupportedException 
	 * @wbp.parser.entryPoint
	 */
	public static void showTaylorPolynomial(Operation o, JFrame parent) throws CloneNotSupportedException {
		JDialog dialog = new JDialog(parent);
		dialog.setTitle("Taylor Polynomial");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.setBounds(100, 100, 450, 300);
		dialog.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 438, 266);
		dialog.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBounds(6, 6, 438, 266);
		textArea.setWrapStyleWord(true);
		textArea.setAutoscrolls(true);

		//calculations
		double degree = NumberInputBox.show(parent, "Enter the degree of the taylor polynomial:");
		double c = NumberInputBox.show(parent, "Enter the center of the taylor polynomial:");
		String result = "";
		Operation tempDerivative = (Operation) o.clone();
		
		if (degree <= 0){
			return;
		}
		
		degree = (double)Math.round(degree);
		
		//formula for taylor poly- f(c) + ... + nth der of f(c)(x-c)^n/n!
		BigDecimal first = o.evaluate(new BigDecimal(c));
		result += (first.doubleValue() == 0 ? "" : first);
		for(int i = 1; i <= degree; i++){
			//get the next derivative
			tempDerivative = tempDerivative.derivative();
			//simplify the operation
			tempDerivative = Operation.simplify(tempDerivative);
			//print it out
			System.out.println(i + " derivative of f is: " + tempDerivative.toString());
			BigDecimal newEval = tempDerivative.evaluate(new BigDecimal(c));
			//get the coefficient of the term
			newEval = newEval.divide(Factorial.fact(new BigDecimal(i)), 6, RoundingMode.HALF_UP);
			if(newEval.doubleValue() == 0){continue;} //don't write 0 terms
			if(newEval.doubleValue() != 1){
				result += " + " + toFraction(newEval.doubleValue());
			}
			if(c > 0){
				result += "(x-" + c + ")^" + i;
			} else if(c < 0){
				result += "(x+" + -c + ")^" + i; //this way you don't have x--4
			} else {
				result += "x^" + i;
			}
			
		}
		if(result.startsWith(" + ")){
			result = result.substring(3);
		}
		textArea.setText("P" + CalculusMain.subscript("" + (int)degree) + "(x)= " + result);
		
		dialog.setVisible(true);

	}
	
	public static double factorial(double input){
		if(input <= 1) {return 1;}
		return input * factorial(input-1);
	}
	
	public static String toFraction(double input){
		if(input == Math.ceil(input)){ return "" + input;}
		for(int i = 2; i <= 1000; i++){
			if((i*input)%1 == 0){
				return "(" + (long)(i*input) + "/" + i + ")";
			}
		}
		return "" + input;
	}
}
