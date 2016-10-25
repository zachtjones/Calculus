package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import operations.Add;
import operations.Constant;
import operations.Cos;
import operations.DefiniteIntegral;
import operations.IllegalIntegralException;
import operations.Multiply;
import operations.Operation;
import operations.Power;
import operations.Sin;
import operations.Subtract;
import operations.Variable;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ItemEvent;

public class CalculusMain {
	
	private static int numMode = 0; //0 is function, 1 parametric, 2 polar, 3 summation
	
	public final static String theta = "θ";
	public final static String sigma = "∑";
	public static String variableLetter = "x";
	
	private static Operation function1 = new Variable(), function2 = new Variable();
	
	private static JLabel lblFunction;
	private static JFrame frame;
	private static JButton btnSetFunction, btnTaylorPolynomial, btnSummation, btnTrapezoidalRule, 
	btnSimpsonsRule, btnCalculateNthDerivative, btnYt, btnLengthOfCurve, btnVolumeOfRevolution, 
	btnSaveFx, btnSaveYt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		System.out.println("Main started");
		initialize();
		System.out.println("Main completed");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frame = new JFrame("Calculus-Main");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel content = new JPanel();
		content.setLayout(null);
		
		frame.setContentPane(content);
		
		btnSetFunction = new JButton("f(x) = ");
		btnSetFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation temp = ob.show(frame, "Map out the function, using the order of operations");
				if(temp != null){
					function1 = temp;
					setLabel();
				}
			}
		});
		btnSetFunction.setBounds(6, 41, 143, 43);
		frame.getContentPane().add(btnSetFunction);
		
		lblFunction = new JLabel("f(x) = ");
		lblFunction.setBounds(6, 86, 438, 16);
		frame.getContentPane().add(lblFunction);
		
		btnTaylorPolynomial = new JButton("Taylor Polynomial");
		btnTaylorPolynomial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TaylorPolynomial.showTaylorPolynomial(function1, frame);
				} catch (CloneNotSupportedException e1) { //should never happen
					System.out.println("Could not copy the function for the taylor polynomial");
				}
			}
		});
		btnTaylorPolynomial.setBounds(6, 157, 143, 29);
		frame.getContentPane().add(btnTaylorPolynomial);
		
		btnSummation = new JButton("partial sums");
		btnSummation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Summation.showSummation(function1, frame);
			}
		});
		btnSummation.setBounds(6, 198, 143, 29);
		content.add(btnSummation);
		
		btnTrapezoidalRule = new JButton("trapezoidal rule");
		btnTrapezoidalRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double start = NumberInputBox.show(frame, "Enter the start of the definite integral:");
				double end = NumberInputBox.show(frame, "Enter the end of the definite integral:");
				int numSubIntervals = (int)NumberInputBox.show(frame, "Enter the number of subintervals:");
				
				try {
					BigDecimal result = DefiniteIntegral.trapeziodalRule(numSubIntervals, new BigDecimal("" + start), new BigDecimal("" + end), function1);
					JOptionPane.showMessageDialog(frame, "The trapeziodal rule from " + start + " to " + end + " with " + numSubIntervals + " subintervals is: " + result);
				} catch (IllegalIntegralException e1) {
					JOptionPane.showMessageDialog(frame, "Error using the trapezoidal rule:\n" + e1.getMessage());
				} catch (ArithmeticException e1){
					JOptionPane.showMessageDialog(frame, "Error using the trapeziodal rule:\n" + e1.getMessage());
				}
			}
		});
		btnTrapezoidalRule.setBounds(6, 239, 143, 29);
		content.add(btnTrapezoidalRule);
		
		btnSimpsonsRule = new JButton("Simpson's rule");
		btnSimpsonsRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double start = NumberInputBox.show(frame, "Enter the start of the definite integral:");
				double end = NumberInputBox.show(frame, "Enter the end of the definite integral:");
				int numSubIntervals = (int)NumberInputBox.show(frame, "Enter the number of subintervals:");
				
				try {
					BigDecimal result = DefiniteIntegral.simpsonsRule(numSubIntervals, new BigDecimal("" + start), new BigDecimal("" + end), function1);
					JOptionPane.showMessageDialog(frame, "Simpson's rule from " + start + " to " + end + " with " + numSubIntervals + " subintervals is: " + result);
				} catch (IllegalIntegralException e1) {
					JOptionPane.showMessageDialog(frame, "Error using Simpson's rule:\n" + e1.getMessage());
				} catch(ArithmeticException e1){
					JOptionPane.showMessageDialog(frame, "Error using Simpson's rule:\n" + e1.getMessage());
				}
			}
		});
		btnSimpsonsRule.setBounds(6, 280, 143, 29);
		content.add(btnSimpsonsRule);
		
		btnCalculateNthDerivative = new JButton("calculate nth derivative");
		btnCalculateNthDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					switch(numMode){
					case 0://function
						int degree = (int) NumberInputBox.show(frame, "Enter the degree of the derivative (1 for dy/dx, 2 for d2y/dx2, ...)");
						Operation temp = (Operation)function1.clone();
						for(int i = 0; i < degree; i++){
							temp = temp.derivative();
							temp = Operation.simplify(temp);
						}
						JOptionPane.showMessageDialog(frame, "d" + superscript(""+degree) + "y/dx" + superscript(""+degree) + " = " + temp.toString());
						break;
					case 1://parametric
						int degree1 = (int) NumberInputBox.show(frame, "Enter the degree of the derivative (1 for dy/dx, 2 for d2y/dx2, ...)");
						Operation temp1 = (Operation)function1.clone();
						for(int i = 0; i < degree1; i++){
							temp1 = temp1.derivative();
							temp1 = Operation.simplify(temp1);
						}
						Operation temp2 = (Operation)function2.clone();
						for(int i = 0; i < degree1; i++){
							temp2 = temp2.derivative();
							temp2 = Operation.simplify(temp2);
						}
						JOptionPane.showMessageDialog(frame, "d" + superscript(""+degree1) + "x/dt" + superscript(""+degree1) + " = " + temp1.toString() + "\n" + 
								"d" + superscript(""+degree1) + "y/dt" + superscript(""+degree1) + " = " + temp2.toString());
						break;
					case 2://polar
						Operation temp3 = (Operation)function1.clone();
						Operation x = new Multiply((Operation)temp3.clone(), new Cos(new Variable())); //rcos(theta)
						Operation y = new Multiply((Operation)temp3.clone(), new Sin(new Variable())); //rsin(theta)
						x = x.derivative(); //dx/dtheta
						y = y.derivative(); //dy/dtheta
						x = Operation.simplify(x);
						y = Operation.simplify(y);
						JOptionPane.showMessageDialog(frame, "dy/dx = (" + x.toString() + ")/(" + y.toString() + ")");
						break;
					case 3://not visible
						break;
					}
					
				
				} catch (Exception e1) {
					System.out.println("could not calculate the nth derivative");
				}
			}
		});
		btnCalculateNthDerivative.setBounds(161, 156, 190, 29);
		content.add(btnCalculateNthDerivative);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				numMode = comboBox.getSelectedIndex();
				updateMode();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rectangular", "Parametric", "Polar", "Series"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(6, 6, 182, 27);
		content.add(comboBox);
		
		btnYt = new JButton("y(t) =");
		btnYt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation temp = ob.show(frame, "Map out the function, using the order of operations");
				if(temp != null){
					function2 = temp;
					setLabel();
				}
			}
		});
		btnYt.setBounds(161, 41, 143, 43);
		content.add(btnYt);
		
		btnLengthOfCurve = new JButton("length of curve");
		btnLengthOfCurve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					switch(numMode){
					case 0://function
						//integral from x1 to x2 of sqrt((dy/dx)^2+1)
						Operation dydx = function1.derivative();
						Operation insideOfIntegral = new Power(
								new Add(
										new Power(dydx, new Constant(new BigDecimal(2))), 
										new Constant(new BigDecimal(1))), 
								new Constant(new BigDecimal(0.5)));
						BigDecimal start = new BigDecimal(NumberInputBox.show(frame, "Enter the start (an x number)")).setScale(6, RoundingMode.HALF_UP);
						BigDecimal end = new BigDecimal(NumberInputBox.show(frame, "Enter the end (an x number)")).setScale(6, RoundingMode.HALF_UP);
						//use simpson's with 1000 subintervals
						BigDecimal value = DefiniteIntegral.simpsonsRule(1000, start, end, insideOfIntegral);
						JOptionPane.showMessageDialog(frame, "The length of the curve from x = " + start + " to x = " + end + " is: " + value);
						break;
					case 1://parametric
						//integral from t1 to t2 of sqrt((dx/dt)^2+(dy/dt)^2)
						Operation dxdt = function1.derivative();
						Operation dydt = function2.derivative();
						Operation insideOfIntegralpara = new Power(
								new Add(
										new Power(dxdt, new Constant(new BigDecimal(2))),
										new Power(dydt, new Constant(new BigDecimal(2)))),
								new Constant(new BigDecimal(0.5)));
						BigDecimal startPara = new BigDecimal(NumberInputBox.show(frame, "Enter the start (a t number)")).setScale(6, RoundingMode.HALF_UP);
						BigDecimal endPara = new BigDecimal(NumberInputBox.show(frame, "Enter the end (a t number)")).setScale(6, RoundingMode.HALF_UP);
						//use simpson's with 1000 subintervals
						BigDecimal valuePara = DefiniteIntegral.simpsonsRule(1000, startPara, endPara, insideOfIntegralpara);
						JOptionPane.showMessageDialog(frame, "The length of the curve from t = " + startPara + " to t = " + endPara + " is: " + valuePara);
						break;
					case 2://polar
						//integral from theta1 to theta2 of sqrt(r^2+(dr/dtheta)^2)
						Operation r2 = new Power(Operation.simplify(function1), new Constant(new BigDecimal(2)));
						Operation drdtheta2 = new Power(function1.derivative(), new Constant(new BigDecimal(2)));
						Operation insideOfIntegralPolar = new Power(new Add(r2, drdtheta2), new Constant(new BigDecimal(0.5)));
						BigDecimal startPolar = new BigDecimal(NumberInputBox.show(frame, "Enter the start (a " + theta + " number)")).setScale(6, RoundingMode.HALF_UP);
						BigDecimal endPolar = new BigDecimal(NumberInputBox.show(frame, "Enter the end (a " + theta + " number)")).setScale(6, RoundingMode.HALF_UP);
						//use simpson's with 1000 subintervals
						BigDecimal valuePolar = DefiniteIntegral.simpsonsRule(1000, startPolar, endPolar, insideOfIntegralPolar);
						JOptionPane.showMessageDialog(frame, "The length of the curve from " + theta + " = " + startPolar + " to " + theta + " = " + endPolar + " is: " + valuePolar);		
						break;
					case 3://not visible
						break;
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Could not calculate the length of curve \nMessage:" + e1.getMessage());
				}
			}
		});
		btnLengthOfCurve.setBounds(161, 198, 190, 29);
		content.add(btnLengthOfCurve);
		
		btnVolumeOfRevolution = new JButton("volume of revolution");
		btnVolumeOfRevolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "The volume will always be from the curve to the axis of revolution from \nx = a to x = b");
				if(numMode == 0){
					String temp = JOptionPane.showInputDialog("Enter the formula for the axis, y = constant. Enter only the constant. \nUse only a number for the constant like 3.141593 instead of pi");
					BigDecimal formula = null;
					try{
						formula = new BigDecimal(temp);
					} catch (Exception ex){
						JOptionPane.showMessageDialog(frame, "Could not get the equation from the formula you entered");
						return;
					}
					//revolved about y=formula-use disk
					//v=pi*integral from a to b (x numbers) of radius^2 dx
					Operation r = new Subtract(function1, new Constant(formula)); //r = height of function - line equation
					Operation r2 = new Power(r, new Constant(new BigDecimal(2)));
					BigDecimal start = new BigDecimal(NumberInputBox.show(frame, "Enter the start of the revolution (an x number)"));
					BigDecimal end = new BigDecimal(NumberInputBox.show(frame, "Enter the end of the revolution(an x number)"));

					try {
						BigDecimal integralResult = DefiniteIntegral.simpsonsRule(1000, start, end, r2);
						BigDecimal finalresult = integralResult.multiply(new BigDecimal(Math.PI)).setScale(6, RoundingMode.HALF_UP).abs(); //*pi and round to 6 dp
						JOptionPane.showMessageDialog(frame, "The volume of revolution from x=" + start + " to x=" + end + " along axis y=" + formula + "is " + finalresult);

					} catch (IllegalIntegralException e1) {
						JOptionPane.showMessageDialog(frame, "Could not take the definite integral");
					}
				} //other two modes button not shown
			}
		});
		btnVolumeOfRevolution.setBounds(161, 239, 190, 29);
		content.add(btnVolumeOfRevolution);
		
		btnSaveFx = new JButton("save f(x)");
		btnSaveFx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser();
					jfc.setDialogTitle("Pick the file of the function");
					jfc.setMultiSelectionEnabled(false);
					jfc.setFileFilter(new FileFilter(){
						@Override
						public boolean accept(File f) {
							return f.getName().endsWith(".calc");
						}
						@Override
						public String getDescription() {
							return "Calculus Operation files (*.calc)";
						}
					});
					if (jfc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
						File f = jfc.getSelectedFile();
						if(!f.getAbsolutePath().endsWith(".calc")){
							f = new File(f.getAbsolutePath()+ ".calc");
						}
						BufferedWriter bw = new BufferedWriter(new FileWriter(f));
						bw.write(function1.toFile());
						bw.flush();
						bw.close();
					}
					JOptionPane.showMessageDialog(frame, "Save completed.");
				} catch (IOException e1){
					JOptionPane.showMessageDialog(frame, "Could not save to file.");
				}
			}
		});
		btnSaveFx.setBounds(6, 114, 143, 29);
		content.add(btnSaveFx);
		
		btnSaveYt = new JButton("save y(t)");
		btnSaveYt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser();
					jfc.setDialogTitle("Pick the file of the function");
					jfc.setMultiSelectionEnabled(false);
					jfc.setFileFilter(new FileFilter(){
						@Override
						public boolean accept(File f) {
							return f.getName().endsWith(".calc");
						}
						@Override
						public String getDescription() {
							return "Calculus Operation files (*.calc)";
						}
					});
					if (jfc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
						File f = jfc.getSelectedFile();
						if(!f.getAbsolutePath().endsWith(".calc")){
							f = new File(f.getAbsolutePath()+ ".calc");
						}
						BufferedWriter bw = new BufferedWriter(new FileWriter(f));
						bw.write(function2.toFile());
						bw.flush();
						bw.close();
					}
					JOptionPane.showMessageDialog(frame, "Save completed.");
				} catch (IOException e1){
					JOptionPane.showMessageDialog(frame, "Could not save to file.");
				}
			}
		});
		btnSaveYt.setBounds(161, 114, 143, 29);
		content.add(btnSaveYt);
		
		frame.setSize(400, 400);
		frame.setLocation(100,100);
		frame.setVisible(true);
		System.out.println("Set frame visible");
		updateMode();
	}
	
	private static void updateMode() {
		if (function1 == null) {function1 = new Variable();}
		if (function2 == null) {function2 = new Variable();}
		setLabel();
		switch (numMode){
		case 0://rectangular f(x)
			variableLetter = "x";
			btnTaylorPolynomial.setVisible(true);
			btnTrapezoidalRule.setVisible(true);
			btnSimpsonsRule.setVisible(true);
			btnSummation.setVisible(false);
			btnYt.setVisible(false);
			btnSetFunction.setText("f(x) = ");
			btnSaveFx.setText("save f(x)");
			btnSaveYt.setVisible(false);
			btnCalculateNthDerivative.setVisible(true);
			btnCalculateNthDerivative.setText("calculate nth derivative");
			btnLengthOfCurve.setVisible(true);
			btnVolumeOfRevolution.setVisible(true);
			break;
		case 1://parametric x(t), y(t)
			variableLetter = "t";
			btnTaylorPolynomial.setVisible(false);
			btnTrapezoidalRule.setVisible(false);
			btnSimpsonsRule.setVisible(false);
			btnSummation.setVisible(false);
			btnYt.setVisible(true);
			btnSetFunction.setText("x(t) = ");
			btnSaveFx.setText("save x(t)");
			btnSaveYt.setVisible(true);
			btnCalculateNthDerivative.setVisible(true);
			btnCalculateNthDerivative.setText("calculate nth derivative");
			btnLengthOfCurve.setVisible(true);
			btnVolumeOfRevolution.setVisible(false);
			break;
		case 2://polar r(θ)
			variableLetter = theta;
			btnTaylorPolynomial.setVisible(false);
			btnTrapezoidalRule.setVisible(false);
			btnSimpsonsRule.setVisible(false);
			btnSummation.setVisible(false);
			btnYt.setVisible(false);
			btnSetFunction.setText("r(" + theta + ") = ");
			btnSaveFx.setText("save r(" + theta +")");
			btnSaveYt.setVisible(false);
			btnCalculateNthDerivative.setVisible(true);
			btnCalculateNthDerivative.setText("calculate dy/dx");
			btnLengthOfCurve.setVisible(true);
			btnVolumeOfRevolution.setVisible(false);
			break;
		case 3://summation
			variableLetter = "n";
			btnTaylorPolynomial.setVisible(false);
			btnTrapezoidalRule.setVisible(false);
			btnSimpsonsRule.setVisible(false);
			btnSummation.setVisible(true);
			btnYt.setVisible(false);
			btnSaveFx.setText("save a=");
			btnSaveYt.setVisible(false);
			btnSetFunction.setText("a = ");
			btnCalculateNthDerivative.setVisible(false);
			btnLengthOfCurve.setVisible(false);
			btnVolumeOfRevolution.setVisible(false);
			break;
		}
	}
	
	private static void setLabel(){
		switch(numMode){
		case 0:
			lblFunction.setText("f(x) = " + function1.toString()); 
			break;
		case 1:
			lblFunction.setText("x(t) = " + function1.toString() + " y(t) = " + function2.toString());
			break;
		case 2:
			lblFunction.setText("r(" + theta + ") = " + function1.toString());
			break;
		case 3:
			lblFunction.setText("a = " + function1.toString());
			break;
		}
		
	}
	
	/**
	 * Converts a string to subscripts
	 * @param str The input string
	 * @return str to subcripts
	 */
	public static String subscript(String str) {
	    str = str.replaceAll("0", "₀");
	    str = str.replaceAll("1", "₁");
	    str = str.replaceAll("2", "₂");
	    str = str.replaceAll("3", "₃");
	    str = str.replaceAll("4", "₄");
	    str = str.replaceAll("5", "₅");
	    str = str.replaceAll("6", "₆");
	    str = str.replaceAll("7", "₇");
	    str = str.replaceAll("8", "₈");
	    str = str.replaceAll("9", "₉");
	    return str;
	}
	/**
	 * Converts a string to superscripts
	 * @param str The input string
	 * @return str to superscipts
	 */
	public static String superscript(String str) {
	    str = str.replaceAll("0", "⁰");
	    str = str.replaceAll("1", "¹");
	    str = str.replaceAll("2", "²");
	    str = str.replaceAll("3", "³");
	    str = str.replaceAll("4", "⁴");
	    str = str.replaceAll("5", "⁵");
	    str = str.replaceAll("6", "⁶");
	    str = str.replaceAll("7", "⁷");
	    str = str.replaceAll("8", "⁸");
	    str = str.replaceAll("9", "⁹");         
	    return str;
	}	
}
