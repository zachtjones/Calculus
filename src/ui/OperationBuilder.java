package ui;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import operations.Add;
import operations.Constant;
import operations.Cos;
import operations.Cot;
import operations.Csc;
import operations.Divide;
import operations.E;
import operations.Factorial;
import operations.Ln;
import operations.Log;
import operations.Multiply;
import operations.Negative;
import operations.Operation;
import operations.Pi;
import operations.Power;
import operations.Sec;
import operations.Sin;
import operations.Subtract;
import operations.Tan;
import operations.Variable;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;

public class OperationBuilder {
	
	private Operation o;
	
	public OperationBuilder(){}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public Operation show(Window parent, String text){
		final JDialog jd = new JDialog();
		jd.setModalityType(ModalityType.APPLICATION_MODAL);
		jd.setModal(true);
		jd.setBounds(100, 100, 400, 400);
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jd.getContentPane().setLayout(null);
		jd.setTitle(text);
		
		JButton btnAddition = new JButton("Addition");
		btnAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				OperationBuilder ob2 = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the first part of the addition");
				Operation second = ob2.show(jd, "Enter the second part of the addition");
				o = new Add(first, second);
				jd.dispose();
			}
		});
		btnAddition.setBounds(6, 15, 117, 29);
		jd.getContentPane().add(btnAddition);
		
		JButton btnSubtraction = new JButton("Subtraction");
		btnSubtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				OperationBuilder ob2 = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the first part of the subtraction");
				Operation second = ob2.show(jd, "Enter the second part of the subtraction");
				o = new Subtract(first, second);
				jd.dispose();
			}
		});
		btnSubtraction.setBounds(135, 15, 117, 29);
		jd.getContentPane().add(btnSubtraction);
		
		JButton btnMulitplication = new JButton("Mulitplication");
		btnMulitplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				OperationBuilder ob2 = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the first part of the multiplication");
				Operation second = ob2.show(jd, "Enter the second part of the multiplication");
				o = new Multiply(first, second);
				jd.dispose();
			}
		});
		btnMulitplication.setBounds(264, 15, 117, 29);
		jd.getContentPane().add(btnMulitplication);
		
		JButton btnDivision = new JButton("Division");
		btnDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				OperationBuilder ob2 = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the top part of the division");
				Operation second = ob2.show(jd, "Enter the bottom part of the division");
				o = new Divide(first, second);
				jd.dispose();
			}
		});
		btnDivision.setBounds(6, 59, 117, 29);
		jd.getContentPane().add(btnDivision);
		
		JButton btnPower = new JButton("Power");
		btnPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				OperationBuilder ob2 = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the base of the power");
				Operation second = ob2.show(jd, "Enter the exponent of the power");
				o = new Power(first, second);
				jd.dispose();
			}
		});
		btnPower.setBounds(135, 59, 117, 29);
		jd.getContentPane().add(btnPower);
		
		JButton btnFactorial = new JButton("Factorial");
		btnFactorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the factorial");
				o = new Factorial(first);
				jd.dispose();
			}
		});
		btnFactorial.setBounds(264, 59, 117, 29);
		jd.getContentPane().add(btnFactorial);
		
		JButton btnSin = new JButton("Sin");
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the sin");
				o = new Sin(first);
				jd.dispose();
			}
		});
		btnSin.setBounds(6, 103, 117, 29);
		jd.getContentPane().add(btnSin);
		
		JButton btnCos = new JButton("Cos");
		btnCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the cos");
				o = new Cos(first);
				jd.dispose();
			}
		});
		btnCos.setBounds(135, 103, 117, 29);
		jd.getContentPane().add(btnCos);
		
		JButton btnTan = new JButton("Tan");
		btnTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the tan");
				o = new Tan(first);
				jd.dispose();
			}
		});
		btnTan.setBounds(264, 103, 117, 29);
		jd.getContentPane().add(btnTan);
		
		JButton btnCsc = new JButton("Csc");
		btnCsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the csc");
				o = new Csc(first);
				jd.dispose();
			}
		});
		btnCsc.setBounds(6, 147, 117, 29);
		jd.getContentPane().add(btnCsc);
		
		JButton btnSec = new JButton("Sec");
		btnSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the sec");
				o = new Sec(first);
				jd.dispose();
			}
		});
		btnSec.setBounds(135, 147, 117, 29);
		jd.getContentPane().add(btnSec);
		
		JButton btnCot = new JButton("Cot");
		btnCot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the cot");
				o = new Cot(first);
				jd.dispose();
			}
		});
		btnCot.setBounds(264, 147, 117, 29);
		jd.getContentPane().add(btnCot);
		
		JButton btnLn = new JButton("Ln");
		btnLn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the ln");
				o = new Ln(first);
				jd.dispose();
			}
		});
		btnLn.setBounds(6, 191, 117, 29);
		jd.getContentPane().add(btnLn);
		
		JButton btnLog = new JButton("Log");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the log");
				o = new Log(first);
				jd.dispose();
			}
		});
		btnLog.setBounds(135, 191, 117, 29);
		jd.getContentPane().add(btnLog);
		
		JButton btnE = new JButton("E");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o = new E();
				jd.dispose();
			}
		});
		btnE.setBounds(264, 191, 117, 29);
		jd.getContentPane().add(btnE);
		
		JButton btnPi = new JButton("Pi (π)");
		btnPi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o = new Pi();
				jd.dispose();
			}
		});
		btnPi.setBounds(6, 235, 117, 29);
		jd.getContentPane().add(btnPi);
		
		JButton btnNumber = new JButton("Number");
		btnNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o = new Constant(new BigDecimal("" + NumberInputBox.show(jd, "Enter a number")));
				jd.dispose();
			}
		});
		btnNumber.setBounds(135, 235, 117, 29);
		jd.getContentPane().add(btnNumber);
		
		JButton btnVariablex = new JButton("Variable (" + CalculusMain.variableLetter + ")");
		btnVariablex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o = new Variable();
				jd.dispose();
			}
		});
		btnVariablex.setBounds(264, 235, 117, 29);
		jd.getContentPane().add(btnVariablex);
		
		JButton btnNegative = new JButton("Negative");
		btnNegative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation first = ob.show(jd, "Enter the inside of the negative");
				o = new Negative(first);
				jd.dispose();
			}
		});
		btnNegative.setBounds(6, 279, 117, 29);
		jd.getContentPane().add(btnNegative);
		
		JButton btnSqrt = new JButton("Square root");
		btnSqrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation temp = ob.show(jd, "Enter the inside of the square root");
				o = new Power(temp, new Constant(new BigDecimal(1).divide(new BigDecimal(2)))); //exactly 1/2
				jd.dispose();
			}
		});
		btnSqrt.setBounds(135, 279, 117, 29);
		jd.getContentPane().add(btnSqrt);
		
		JButton btnEx = new JButton("e^");
		btnEx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationBuilder ob = new OperationBuilder();
				Operation temp = ob.show(jd, "Enter the power of e");
				o = new Power(new E(), temp);
				jd.dispose();
			}
		});
		btnEx.setBounds(264, 279, 117, 29);
		jd.getContentPane().add(btnEx);
		
		JButton btnFromFile = new JButton("function from file");
		btnFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				if (jfc.showOpenDialog(jd) == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					try {
						o = Operation.fromFile(f.getAbsolutePath());
						jd.dispose();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(jd, "Could not read the file");
					}
				}
				
			}
		});
		btnFromFile.setBounds(6, 320, 375, 29);
		jd.getContentPane().add(btnFromFile);
		
		
		jd.setVisible(true);
		return o;
	}
}
