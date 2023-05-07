package Timetable;
import javax.swing.*;


import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import Load.*;
import Save.*;
import Convert.*;

/**
 * @author Douglas Lino
 * Classe que permite ao utilizador carregar ficheiros (local ou URL), salvar ficheiros (local ou GITHUB)e fazer conversões de formato.
 */
	public class GUI extends JFrame implements ActionListener {
		private JPanel cardPanel;
	    private CardLayout cardLayout;
	    private JButton load, upload, save, csvToJson, jsonToCsv, local, online, confirm, return1, return2, schedule;
	    private JTextField url, username, repository, name, token;
	    private JFileChooser fc;
	    private JLabel current, label4, label5, label6, label7;
	    private JFrame frame;
	    private File file;
	    private List<CalendarEvent> events;

	    
	    
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
	        csvToJson = new JButton("Converter CSV para JSON");
	        csvToJson.addActionListener(this);
	        card2.add(csvToJson,gbc2);
	        
	        gbc2.gridx++;
	        jsonToCsv = new JButton("Converter JSON para CSV");
	        jsonToCsv.addActionListener(this);
	        card2.add(jsonToCsv,gbc2);
	        
	        gbc2.gridx = 0;
	        gbc2.gridy++;

	        return1 = new JButton("Retornar");
	        return1.addActionListener(this);
	        card2.add(return1, gbc2);
	        
	        gbc2.gridx=2;
	        schedule = new JButton("Ver horário");
	        schedule.addActionListener(this);
	        card2.add(schedule, gbc2);
	        
	        cardPanel.add(card2, "Card 2");
	        
	        JPanel card3 = new JPanel(new GridBagLayout());
	        JLabel label3 = new JLabel("Selecione a forma de gravar");
	        
	        GridBagConstraints gbc3 = new GridBagConstraints();
	        gbc3.gridx = 1;
	        gbc3.gridy = 0;
	        gbc3.insets = inset;
	        
	        card3.add(label3, gbc3);
	        
	        local = new JButton("Localmente");
	        gbc3.gridx = 0;
	        gbc3.gridy++;
	        card3.add(local, gbc3);
	        local.addActionListener(this);
	        
	        gbc3.gridy = 1;
	        online = new JButton("Online");
	        gbc3.gridx=2;
	        card3.add(online, gbc3);
	        online.addActionListener(this);
	    
	        gbc3.gridy++;
	        gbc3.gridx=0;
	        label4 = new JLabel("Username");
	        label4.setVisible(false);
	        card3.add(label4, gbc3);
	        
	        gbc3.gridx++;
	        username = new JTextField(10);
	        username.setVisible(false);
	        card3.add(username, gbc3);
	        
	        gbc3.gridx++;
	        label5 = new JLabel("GITHUB Repository");
	        label5.setVisible(false);
	        card3.add(label5, gbc3);
	        
	        gbc3.gridx++;
	        repository = new JTextField(10);
	        repository.setVisible(false);
	        card3.add(repository, gbc3);
	        
	        gbc3.gridy++;
	        gbc3.gridx=0;
	        label6 = new JLabel("Token");
	        label6.setVisible(false);
	        card3.add(label6, gbc3);
	        
	        gbc3.gridx++;
	        token = new JTextField(10);
	        token.setVisible(false);
	        card3.add(token, gbc3);
	        
	        gbc3.gridx++;
	        label7 = new JLabel("Nome");
	        label7.setVisible(false);
	        card3.add(label7, gbc3);
	        
	        gbc3.gridx++;
	        name = new JTextField(10);
	        name.setVisible(false);
	        card3.add(name, gbc3);
	        
	        return2 = new JButton("Retornar");
	        gbc3.gridx = 0;
	        gbc3.gridy++;
	        card3.add(return2, gbc3);
	        return2.addActionListener(this);
	        
	        gbc3.gridx=2;
	        confirm = new JButton("Confirmar");
	        confirm.setVisible(false);
	        card3.add(confirm, gbc3);
	        confirm.addActionListener(this);
	        
	        cardPanel.add(card3, "Card 3");

	        frame = new JFrame("Schedule management");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(cardPanel);

	        // Display the window
	        frame.setSize(800, 400);
	        frame.setVisible(true);
	        
	        fc = new JFileChooser();
	    }
		/**
		 * Função para carregar um ficheiro local
		 */
			    
	    public void load() {
	    	int response = fc.showOpenDialog(null);
	        if (response == JFileChooser.APPROVE_OPTION) {
	        	file = fc.getSelectedFile();
	            current.setText(fc.getSelectedFile().getName());
	            cardLayout.show(cardPanel, "Card 2");
	        }
	    }
	    
	    /**
	     * Função para carregar um ficheiro online (webcal com uma lista de eventos ou ficheiro obtido a partir de um URI)
	     */
	    
	    public void upload() {
	    	 if (url.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(frame, "Please enter a url.");
                 token.requestFocus();
             }else {
	    		try {
	    			if(url.getText().substring(0,6).equals("webcal")) {
	    				events = WebcalCalendarImporter.importEventsFromWebcal(WebcalCalendarImporter.WebcaltoURI(url.getText()));
	    				current.setText("Web calendar");
	    			}else { 
	    				file = URLFileDownloader.downloadFileFromURL(url.getText());
	    				current.setText(file.getName());
	    			}
		            cardLayout.show(cardPanel, "Card 2");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
             }
	    }
	    
	    /**
	     * Função para salvar um ficheiro localmente
	     */
	    
	    public void saveLocal() {
	    	fc.setCurrentDirectory(new File(System.getProperty("user.home")));

            int result = fc.showSaveDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fc.getSelectedFile().getAbsolutePath();
                try {
					SaveJson.saveLocalmente(file.getAbsolutePath(), filePath);
				} catch (IOException e2) {
					e2.printStackTrace();
				}   
            }
	    }
	    
	    /**
	     * Função para revelar os campos que necessitam de ser preenchidos para salvar online
	     */
	    
	    public void setVisible() {
    		username.setVisible(true);
    		token.setVisible(true);
    		repository.setVisible(true);
    		name.setVisible(true);
    		confirm.setVisible(true);
    		label4.setVisible(true);
    		label5.setVisible(true);
    		label6.setVisible(true);
    		label7.setVisible(true);
	    }
	    
	    /**
	     * Função para salvar um ficheiro online, apenas funciona se os campos username, name, repository e
	     * token não forem vazios e estiverem corretos.
	     */
	    
	    public void saveOnline(){
	   		 if (username.getText().isEmpty()) {
	             JOptionPane.showMessageDialog(frame, "Please enter your username.");
	             username.requestFocus();
	         }
			 if (name.getText().isEmpty()) {
	             JOptionPane.showMessageDialog(frame, "Please enter the name.");
	             name.requestFocus();
	         }
			 if (repository.getText().isEmpty()) {
	             JOptionPane.showMessageDialog(frame, "Please enter the repository.");
	             repository.requestFocus();
	         }
			 if (token.getText().isEmpty()) {
	             JOptionPane.showMessageDialog(frame, "Please enter your token.");
	             token.requestFocus();
	         }
			 if(!username.getText().isEmpty() || !name.getText().isEmpty() || !repository.getText().isEmpty() || !token.getText().isEmpty()) {
	    		try {
					SaveJson.saveOnline(username.getText(), repository.getText(), token.getText(), file.getAbsolutePath(), name.getText());
				} catch (IOException ioe2) {
					ioe2.printStackTrace();
				}
			 }
	    }	
	    
	    /**
	     * Função para fazer conversões 
	     * @param type Sinaliza o tipo da conversão (1 - csv para json, 2 - json para csv)
	     */
	    
	    public void convert(int type) {
	    	if(type == 1 )
	    		file = CsvToJson.convert(file);
	    	else
	    		file = JsonToCsv.convert(file);
    		fc.setSelectedFile(file);
    		current.setText(file.getName());
	    }
	    
	    
	    public void actionPerformed(ActionEvent e) {
	    	if(e.getSource() == load) {
	    		load();
	    	}else if (e.getSource() == upload){	    		
	    		upload();
	    	}else if(e.getSource() == save) {
	    		cardLayout.show(cardPanel, "Card 3");
	    	}else if(e.getSource() == local ) {
	           saveLocal();
	    	}else if(e.getSource() == online) {
	    		setVisible();
	    	}else if(e.getSource() == confirm) {
	    		saveOnline();
	    	}else if(e.getSource() == csvToJson) {
	    		convert(1);
	    	}else if(e.getSource() == jsonToCsv) {
	    		convert(2);
	    	}else if(e.getSource() == return1){
	            cardLayout.show(cardPanel, "Card 1");
	    	}else if(e.getSource() == return2) {
	    		cardLayout.show(cardPanel, "Card 2");
	    	}else if(e.getSource() == schedule) {
	    		MyLauncher.main(null);
	    	}
            
	    }

	    public static void main(String[] args) {
	        GUI test = new GUI();
	    }
}
