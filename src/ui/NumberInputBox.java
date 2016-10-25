package ui;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;

public class NumberInputBox {
	private static JTextField textField;
	private static JButton btnOk;
	private static double value = 0;

	/**
	 * Shows a dialog prompting for a valid number
	 * @param parent The window to show this dialog on top of.
	 * @param text The text to display
	 * @wbp.parser.entryPoint
	 */
	public static double show(Window parent, String text) {
		final JDialog jd = new JDialog(parent);
		jd.setResizable(false);
		jd.setSize(449, 101);
		jd.setModalityType(ModalityType.APPLICATION_MODAL);
		jd.setLocation(parent.getLocation());
		jd.setModal(true);
		jd.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 41, 320, 28);
		jd.getContentPane().add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					value = Double.parseDouble(textField.getText());
					btnOk.setEnabled(true);
				} catch (NumberFormatException e1){
					btnOk.setEnabled(false);
				}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					value = Double.parseDouble(textField.getText());
					btnOk.setEnabled(true);
				} catch (NumberFormatException e1){
					btnOk.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					value = Double.parseDouble(textField.getText());
					btnOk.setEnabled(true);
				} catch (NumberFormatException e1){
					btnOk.setEnabled(false);
				}
			}
		});
		textField.addActionListener(new ActionListener() { //enter is typed

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnOk.isEnabled()){
					jd.dispose();
				}
			}
			
		});
		
		
		JLabel lblText = new JLabel(text);
		lblText.setBounds(6, 13, 438, 16);
		jd.getContentPane().add(lblText);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jd.dispose();
			}
		});
		btnOk.setEnabled(false);
		btnOk.setBounds(327, 42, 117, 29);
		jd.getContentPane().add(btnOk);
		
		jd.setVisible(true);
		return value;
	}
}
