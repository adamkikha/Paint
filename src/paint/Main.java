/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Adam
 */
public class Main extends JFrame {

    /**
     * Creates new form Main
     */
    private JButton clearBtn, colorbtn , undo , redo , rectangle, square , triangle , circle , line;
    private JRadioButton draw , move , resize , copy , delete , color;
    private JCheckBox fill;
    private ButtonGroup modes;
    private DrawingArea d;
    private enum Buttons{Clear, Color, Undo , Redo , Rectangle , Square , Circle , Line , Triangle}
   

    private ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
          
           
            switch (Buttons.valueOf(button.getText())){
                case Clear:
                    d.clear();
                    break;
                case Color:
                	new JColorChooser();
                	Color colorr = JColorChooser.showDialog(null, "Pick a color", Color.black);       
                    d.setPaint(colorr);
                    break;
                case Redo:
                	d.redo();
                	break;
                case Undo:
                	d.undo();
                	break;
				case Circle:
					d.setShape(2);
					break;
				case Rectangle:
					d.setShape(0);
					break;
				case Square:
					d.setShape(1);
					break;
				case Line:
					d.setShape(4);
					break;
				case Triangle:
					d.setShape(3);
					break;
            }
        }
    };
    private enum Modes{Draw , Move , Resize , Copy , Color , Delete}
    private ChangeListener changelistner = new ChangeListener() {
		
		public void stateChanged(ChangeEvent e) {
			JRadioButton r = (JRadioButton) e.getSource();
			if (r.isSelected()) {
				switch (Modes.valueOf(r.getText())) {
					case Color:
						d.setMode(4);
						break;
					case Copy:
						d.setMode(3);
						break;
					case Delete:
						d.setMode(5);
						break;
					case Draw:
						d.setMode(0);
						break;
					case Move:
						d.setMode(1);
						break;
					case Resize:
						d.setMode(2);
						break;
				}
			}
			
			
		}
	};
    public Boolean getFill() {
    	return fill.isSelected();
    }
    public Main() {
        initComponents();
        
        d = new DrawingArea(this);
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(d,BorderLayout.CENTER);
        JPanel colors = new JPanel() , controls = new JPanel();
        modes = new ButtonGroup();
        
        clearBtn = new JButton("Clear",new ImageIcon ("rubber.png"));
        clearBtn.addActionListener(actionListener);
        colorbtn = new JButton("Color",new ImageIcon ("chromatic.png"));
        colorbtn.addActionListener(actionListener);
        undo = new JButton("Undo",new ImageIcon ("undo.png"));
        undo.addActionListener(actionListener);
        redo = new JButton("Redo", new ImageIcon ("redo.png"));
        redo.addActionListener(actionListener);
        
        colors.add(colorbtn);
        colors.add(clearBtn);
        colors.add(undo);
        colors.add(redo);
        content.add(colors, BorderLayout.NORTH);

        fill = new JCheckBox("Fill");
        rectangle = new JButton("Rectangle",new ImageIcon ("rectangle.png"));
        rectangle.setHorizontalTextPosition(JButton.CENTER);
		rectangle.setVerticalTextPosition(JButton.BOTTOM);
        rectangle.addActionListener(actionListener);
        square = new JButton("Square",new ImageIcon ("square.png"));
        square.setHorizontalTextPosition(JButton.CENTER);
     	square.setVerticalTextPosition(JButton.BOTTOM);
        square.addActionListener(actionListener);
        circle = new JButton("Circle",new ImageIcon ("circle.png"));
        circle.setHorizontalTextPosition(JButton.CENTER);
     	circle.setVerticalTextPosition(JButton.BOTTOM);
        circle.addActionListener(actionListener);
        triangle = new JButton("Triangle",new ImageIcon ("triangle.png"));
        triangle.setHorizontalTextPosition(JButton.CENTER);
     	triangle.setVerticalTextPosition(JButton.BOTTOM);
        triangle.addActionListener(actionListener);
        line = new JButton("Line", new ImageIcon ("line.png"));
        line.setHorizontalTextPosition(JButton.CENTER);
     	line.setVerticalTextPosition(JButton.BOTTOM);
        line.addActionListener(actionListener);
        draw = new JRadioButton("Draw",true);
        draw.addChangeListener(changelistner);
        move = new JRadioButton("Move");
        move.addChangeListener(changelistner);
        resize = new JRadioButton("Resize");
        resize.addChangeListener(changelistner);
        delete = new JRadioButton("Delete");
        delete.addChangeListener(changelistner);
        copy = new JRadioButton("Copy");
        copy.addChangeListener(changelistner);
        color = new JRadioButton("Color");
        color.addChangeListener(changelistner);

        modes.add(draw);
        modes.add(move);
        modes.add(resize);
        modes.add(copy);
        modes.add(color);
        modes.add(delete);
        
        controls.add(fill);
        controls.add(rectangle);
        controls.add(square);
        controls.add(circle);
        controls.add(triangle);
        controls.add(line);
        controls.add(draw);
        controls.add(move);
        controls.add(resize);
        controls.add(copy);
        controls.add(color);
        controls.add(delete);
        content.add(controls,BorderLayout.SOUTH);
        }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        setResizable(false);
        pack();
        
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main frame = new Main();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
