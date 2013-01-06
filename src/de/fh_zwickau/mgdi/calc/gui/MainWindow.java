package de.fh_zwickau.mgdi.calc.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.fh_zwickau.mgdi.calc.algo.Algorithm;
import de.fh_zwickau.mgdi.calc.algo.BinaryDiv;
import de.fh_zwickau.mgdi.calc.algo.BinaryMul;
import de.fh_zwickau.mgdi.calc.algo.Dec2Bin;
import de.fh_zwickau.mgdi.calc.algo.Dec2Hex;
import de.fh_zwickau.mgdi.calc.algo.Gleitkomma;
import de.fh_zwickau.mgdi.calc.algo.GrayCode;
import de.fh_zwickau.mgdi.calc.algo.IAlgorithm.ReturnType;


public class MainWindow extends JFrame 
{
	
	enum AlgoType
	{
		  DEC2BIN("Dec2Bin", new Dec2Bin())
		, DEC2HEX("Dec2Hex", new Dec2Hex())
		, GRAYCODE("Graycode", new GrayCode())
		, BINARY_DIV("Binary Div", new BinaryDiv())
		, BINARY_MUL("Binary Mul", new BinaryMul())
		, COMMA("Gleitkomma", new Gleitkomma())
		
		;
		
		String name;
		
		Algorithm algo;
		
		AlgoType(String name, Algorithm algo)
		{
			this.name = name;
			this.algo = algo;
		}
		
		Algorithm algorithm()
		{
			return algo;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	LayoutManager layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
	
	JComboBox comboBox = new JComboBox();
	JTextField textField = new JTextField("");
	JTextArea out = new JTextArea();
	JButton okBtn = new JButton("Calc");
	JButton helpBtn = new JButton("Help");
	MyListener listener = new MyListener();
	
	/** SERIALIZE ID*/
	private static final long serialVersionUID = 1L;

	public MainWindow() 
	{
		
		super();

		setSize(new Dimension(640, 480));
		this.setLayout(layout);
		this.setTitle("Algorithms for course: " +
				"Mathematical Basics of " +
				"Computer Science");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(listener);
		//
		JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		ImageIcon icon;
		try
		{
			icon = new ImageIcon("./WhzLogo80.jpg");
		}
		catch(Exception e)
		{
			icon = new ImageIcon("./res/WhzLogo80.jpg");
		}
		icon.setImage(icon.getImage().getScaledInstance(300, -1, -1));
		//header.add(new JLabel("CALCULATOR FOR BINARY ALGORITHMS"));
		header.add(new JLabel(icon));
		this.getContentPane().add(header);
		//
		JPanel line = new JPanel();
		line.setLayout(new BoxLayout(line, BoxLayout.X_AXIS));
		JPanel left = new JPanel();
		line.add(left);
		JPanel right = new JPanel();
		line.add(right);
		//
		left.setLayout(new FlowLayout(FlowLayout.LEFT));
		right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(line);
		//
		for(AlgoType algo : AlgoType.values())
		{
			comboBox.addItem(algo);
		}
		comboBox.addKeyListener(listener);
		left.add(new JLabel("Algorithm:", JLabel.RIGHT));
		left.add(comboBox);
		//
		left.add(new JLabel("Params:", JLabel.RIGHT));
		textField.setMinimumSize(new Dimension(300, 50));
		textField.setToolTipText("Input field for algorhtm params");
		textField.setPreferredSize(new Dimension(200, 30));
		textField.addActionListener(listener);
		textField.addKeyListener(listener);
		left.add(textField);
		//
		left.add(okBtn);
		okBtn.addActionListener(listener);
		right.add(helpBtn);
		helpBtn.addActionListener(listener);
		//
		JScrollPane pane = new JScrollPane();
		pane.setPreferredSize(new Dimension(-1, 99999));
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//pane.setAutoscrolls(true);
		out.setEditable(false);
		out.setBackground(Color.black);
		out.setForeground(Color.white);
		Font f = new Font("Monospaced", Font.PLAIN, 14);
		out.setFont(f);
		pane.getViewport().add(out);
		this.getContentPane().add(pane);
	
		textField.requestFocus();
		textField.requestFocusInWindow();
		textField.requestFocus(false);
	}
	
	public void doCalc()
	{
		String[] args = textField.getText().split(" ");
		AlgoType algo = ((AlgoType)comboBox.getSelectedItem());
		
		//CHECK PARAMS
		if(!(algo.algorithm() instanceof GrayCode))
		{
			ReturnType paramCheck = 
				algo.algorithm().checkParams(args);
			if(!paramCheck.success)
			{
				out.append("ERROR: " + paramCheck.output  + "\n\n");
				printHelp();
				return;
			}
		}
		
		//CHECK SUCCES
		ReturnType ret = algo.algorithm().calc(args);
		out.append(ret.output);
		if(ret.success == false)
		{
			out.append("\n\n");
			printHelp();
		}
		out.setCaretPosition(0);
				
	}

	public void printHelp() {
		AlgoType algo = ((AlgoType)comboBox.getSelectedItem());
		out.append("Help for Command '" + algo.name + "':\n");
		out.append(algo.algorithm().getHelpText());
		out.append("\n\n" + algo.algorithm().params() + "\n");
		
	}
	
	/**
	 * LISTENER
	 * 
	 * @author tomha
	 *
	 */
	class MyListener implements ActionListener, KeyListener
	{

		@Override
		public void actionPerformed(ActionEvent ev) {
			
			if(ev.getSource() == okBtn || ev.getSource() == textField)
			{
				out.setText("");
				doCalc();
			}
			else if(ev.getSource() == helpBtn)
			{
				out.setText("");
				printHelp();
			}
		}

		@Override
		public void keyPressed(KeyEvent ev) {
			
			//COMBOBOX SHORTCUT
			if(ev.isAltDown())
			{
				int kCode = ev.getKeyCode();
				if(kCode >= KeyEvent.VK_1
					&& kCode <= KeyEvent.VK_9)
				{
					int idx = kCode - KeyEvent.VK_1; 
					if(idx < comboBox.getItemCount())
					{
						comboBox.setSelectedIndex(idx);
					}
				}
			}
			
			if(ev.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				out.setText("BYE!");
				System.exit(0);
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
