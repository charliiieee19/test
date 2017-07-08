package Pos;

import java.awt.*;
import java.io.IOException;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PosUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PosUI frame = new PosUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PosUI() {
		setTitle("POS Tagger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.black);
		centerFrame();
		
		textField = new JTextField();
		textField.setBounds(76, 28, 453, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBackground(Color.DARK_GRAY);
		textField.setForeground(Color.WHITE);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(76, 85, 453, 219);
		contentPane.add(textArea);
		textArea.setEditable(false);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textArea.setText("");
			}
		});
		
		
		btnReset.setBounds(76, 340, 101, 29);
		contentPane.add(btnReset);
		btnReset.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnReset.setBackground(Color.BLUE);
		btnReset.setForeground(Color.white);
        // customize the button with your own look
		btnReset.setUI(new StyledButtonUI());
		
		JButton btnTag = new JButton("Tag");
		btnTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MaxentTagger tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");
				
				String sample = textField.getText();
//				String sample = "This is a sample text";
				 
				// The tagged string
				String tagged = tagger.tagString(sample);
				
				textArea.append(tagged+"\n");
				//output the tagged sample string onto your console
//				System.out.println("Input: " + sample);
//				System.out.println("Output: "+ tagged);
				
			}
		});
		btnTag.setBounds(187, 340, 101, 29);
		contentPane.add(btnTag);
		btnTag.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnTag.setBackground(Color.BLUE);
		btnTag.setForeground(Color.white);
        // customize the button with your own look
		btnTag.setUI(new StyledButtonUI());
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(440, 340, 89, 29);
		contentPane.add(btnClose);
		btnClose.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnClose.setBackground(Color.BLUE);
		btnClose.setForeground(Color.white);
        // customize the button with your own look
		btnClose.setUI(new StyledButtonUI());
		
		
	}
	
	private void centerFrame() {

		Dimension windowSize = getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

		int dx = centerPoint.x - windowSize.width / 2;
		int dy = centerPoint.y - windowSize.height / 2;
		setLocation(dx, dy);
	}
	class StyledButtonUI extends BasicButtonUI {

	    @Override
	    public void installUI (JComponent c) {
	        super.installUI(c);
	        AbstractButton button = (AbstractButton) c;
	        button.setOpaque(false);
	        button.setBorder(new EmptyBorder(5, 15, 5, 15));
	    }

	    @Override
	    public void paint (Graphics g, JComponent c) {
	        AbstractButton b = (AbstractButton) c;
	        paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
	        super.paint(g, c);
	    }

	    private void paintBackground (Graphics g, JComponent c, int yOffset) {
	        Dimension size = c.getSize();
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setColor(c.getBackground().darker());
	        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
	        g.setColor(c.getBackground());
	        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
	    }
	}
	 
}

