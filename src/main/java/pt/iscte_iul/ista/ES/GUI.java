package pt.iscte_iul.ista.ES;
import javax.swing.*;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Douglas Lino
 * GUI que permite ao utilizador carregar ficheiros (local ou URL), salvar ficheiros e fazer conversões de formato.
 */
	public class GUI extends JFrame implements ActionListener {
		private JPanel cardPanel;
	    private CardLayout cardLayout;
	    private JButton load, upload, save, convert1, convert2;
	    private JTextField url;
	    private JFileChooser fc;
	    private JLabel current;
	    private JFrame frame;

	    public GUI() {
	    	cardLayout = new CardLayout();
	        cardPanel = new JPanel(cardLayout);
	        getContentPane().add(cardPanel);
	        Insets inset = new Insets(10, 10, 10, 10);
	        
	    	JPanel card1 = new JPanel(new GridBagLayout());
	    	
	        load = new JButton("Carregar ficheiro");
	        load.addActionListener(this);
	        
	        GridBagConstraints gbc1 = new GridBagConstraints();
	        gbc1.gridx = 1;
	        gbc1.gridy = 0;
	        gbc1.insets = inset;
	        card1.add(load, gbc1);
	        
	        JLabel label1 = new JLabel("URL");
	        url = new JTextField(10);
	        upload = new JButton("Carregar URL");
	        upload.addActionListener(this);
	       
	        gbc1.gridx=0;
	        gbc1.gridy=2;
	        card1.add(label1, gbc1);
	        gbc1.gridx++;
	        card1.add(url, gbc1);
	        gbc1.gridx++;
	        card1.add(upload, gbc1);
	        
	        cardPanel.add(card1, "Card 1");
	        
	        JPanel card2 = new JPanel(new GridBagLayout());
	        JLabel label2 = new JLabel("Ficheiros carregados:");
	        current = new JLabel("Nenhum ficheiro carregado");
	        
	        GridBagConstraints gbc2 = new GridBagConstraints();
	        gbc2.gridx = 1;
	        gbc2.gridy = 0;
	        gbc2.insets = inset;
	        card2.add(label2, gbc2);
	        
	        gbc2.gridy++;
	        card2.add(current, gbc2);
	        
	        gbc2.gridx = 0;
	        gbc2.gridy++;
	        
	        save = new JButton("Salvar ficheiro");
	        save.addActionListener(this);
	        card2.add(save, gbc2);
	        
	        gbc2.gridx++;
	        convert1 = new JButton("Converter CSV para JSON");
	        convert1.addActionListener(this);
	        card2.add(convert1,gbc2);
	        
	        gbc2.gridx++;
	        convert2 = new JButton("Converter JSON para CSV");
	        convert1.addActionListener(this);
	        card2.add(convert2,gbc2);
	        
	        gbc2.gridx = 1;
	        gbc2.gridy++;

	        JButton back = new JButton("Retornar");
	        back.addActionListener(this);
	        card2.add(back, gbc2);
	        
	        cardPanel.add(card2, "Card 2");

	        frame = new JFrame("Schedule management");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(cardPanel);

	        // Display the window
	        frame.setSize(400, 400);
	        frame.setVisible(true);
	        
	        fc = new JFileChooser();
	    }

	    public void actionPerformed(ActionEvent e) {
	    	if(e.getSource() == load) {
		        int response = fc.showOpenDialog(null);
		        if (response == JFileChooser.APPROVE_OPTION) {
		            System.out.println("Selected file: " + fc.getSelectedFile().getName());
		            current.setText(fc.getSelectedFile().getName());
		            cardLayout.show(cardPanel, "Card 2");
		        }
	    	}else if (e.getSource() == upload){
	            cardLayout.show(cardPanel, "Card 2");
	    	}else if(e.getSource() == save) {
	            fc.setCurrentDirectory(new File(System.getProperty("user.home")));

	            int result = fc.showSaveDialog(frame);

	            if (result == JFileChooser.APPROVE_OPTION) {
	                String filePath = fc.getSelectedFile().getAbsolutePath();

	                try (FileOutputStream fos = new FileOutputStream(filePath)) {
	                    fos.write(filePath.getBytes());
	                } catch (IOException exception) {
	                    exception.printStackTrace();
	                }
	            }
	    	}else if(e.getSource() == convert1) {
	    		
	    	}else if(e.getSource() == convert2) {
	    		
	    	}else {
	            cardLayout.show(cardPanel, "Card 1");
	    	}
            
	    }

	    public static void main(String[] args) {
	        GUI test = new GUI();
	    }
}
