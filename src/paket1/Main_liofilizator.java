//Main_liofilizator
package paket1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import javax.swing.JTextPane;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Main_liofilizator {

	static SerialPort chosenPort;
	static int apcisa = 0;
	static float vrednostT1 = 0;
	static float vrednostT2 = 0;
	static float vrednostT3 = 0;
	static float vrednostT4 = 0;
	static float vrednostT5 = 0;
	static float vrednostT6 = 0;
	static float vrednostM1 = 0;
	static String debag = "";
	static int conectTest = 0;
	static Thread threadLabel;
	static boolean flagZaStatusCon = true;
	static int debBr = 0;
	//public static final String DATABASE_URL="jdbc:sqlite:Akvizicija_sa_vage.db";
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		JFrame windows = new JFrame();
		windows.getContentPane().setLayout(null);
		boolean flag = true;
		
		
		JSlider slider = new JSlider();
		slider.setBounds(0, 0, 0, 0);
		slider.setMaximum(100);
		slider.setOrientation(JSlider.VERTICAL);
		windows.getContentPane().add(slider);
		//windows.pack();
		
		//Prozor koji sadrzi elemente
		windows.setTitle("Akvizicija sa vage");
		windows.setSize(1016, 660);
		windows.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		windows.setResizable(false);
		
		//Pravljenje grafika
		XYSeries series = new XYSeries("temperatura 1");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("temperatura 1", "n uzorka", "t/celsius", dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 11, 250, 280);
		windows.getContentPane().add(chartPanel);

		//Pravljenje grafika
		XYSeries series2 = new XYSeries("temperatura 2");
		XYSeriesCollection dataset2 = new XYSeriesCollection(series2);
		JFreeChart chart2 = ChartFactory.createXYLineChart("temperatura 2", "n uzorka", "t/celsius", dataset2);
		ChartPanel chartPanel_2 = new ChartPanel(chart2);
		chartPanel_2.setBounds(249, 11, 250, 280);
		windows.getContentPane().add(chartPanel_2);
		
		//Pravljenje grafika
		XYSeries series3 = new XYSeries("temperatura 3");
		XYSeriesCollection dataset3 = new XYSeriesCollection(series3);
		JFreeChart chart3 = ChartFactory.createXYLineChart("temperatura 3", "n uzorka", "t/celsius", dataset3);
		ChartPanel chartPanel_3 = new ChartPanel(chart3);
		chartPanel_3.setBounds(495, 11, 250, 280);
		windows.getContentPane().add(chartPanel_3);
		
		//Pravljenje grafika
		XYSeries series4 = new XYSeries("temperatura 4");
		XYSeriesCollection dataset4 = new XYSeriesCollection(series4);
		JFreeChart chart4 = ChartFactory.createXYLineChart("temperatura 4", "n uzorka", "t/celsius", dataset4);
		ChartPanel chartPanel_4 = new ChartPanel(chart4);
		chartPanel_4.setBounds(0, 289, 250, 280);
		windows.getContentPane().add(chartPanel_4);
		
		//Pravljenje grafika
		XYSeries series5 = new XYSeries("temperatura 5");
		XYSeriesCollection dataset5 = new XYSeriesCollection(series5);
		JFreeChart chart5 = ChartFactory.createXYLineChart("temperatura 5", "n uzorka", "t/celsius", dataset5);
		ChartPanel chartPanel_5 = new ChartPanel(chart5);
		chartPanel_5.setBounds(249, 289, 250, 280);
		windows.getContentPane().add(chartPanel_5);
		
		//Pravljenje grafika
		XYSeries series6 = new XYSeries("temperatura 6");
		XYSeriesCollection dataset6 = new XYSeriesCollection(series6);
		JFreeChart chart6 = ChartFactory.createXYLineChart("temperatura 6", "n uzorka", "t/celsius", dataset6);
		ChartPanel chartPanel_6 = new ChartPanel(chart6);
		chartPanel_6.setBounds(495, 289, 250, 280);
		windows.getContentPane().add(chartPanel_6);
		
		//Pravljenje grafika
		XYSeries series7 = new XYSeries("masa 1");
		XYSeriesCollection dataset7 = new XYSeriesCollection(series7);
		JFreeChart chart7 = ChartFactory.createXYLineChart("masa 1", "n uzorka", "m/gram", dataset7);
		ChartPanel chartPanel_7 = new ChartPanel(chart7);
		chartPanel_7.setBounds(743, 11, 250, 280);
		windows.getContentPane().add(chartPanel_7);
				
				
		
		//PRAVLJENJE EXIT BUTTON-A
		JButton exitButton = new JButton("EXIT");
		exitButton.setBounds(321, 580, 92, 34);
		windows.getContentPane().add(exitButton);
		JButton dbToxlsButton = new JButton("Save .xls");
		dbToxlsButton.setBounds(219, 580, 92, 34);
		windows.getContentPane().add(dbToxlsButton);
		JButton connectButton = new JButton("Connect");
		connectButton.setBounds(112, 580, 97, 34);
		windows.getContentPane().add(connectButton);
		
		//ComboBOX
		JComboBox<String> portList = new JComboBox<String>();
		portList.setBounds(10, 580, 92, 33);
		windows.getContentPane().add(portList);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(753, 297, 240, 323);
		windows.getContentPane().add(textPane);
		
		JLabel label = new JLabel("LIOFILIZATOR");
		label.setBounds(434, 584, 108, 21);
		windows.getContentPane().add(label);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel = new JLabel("Vreme");
		lblNewLabel.setBounds(568, 588, 177, 14);
		windows.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		//System.out.println("Tabele kreirane uspesno");
		
		Thread thread = new Thread(){
            public void run(){
            	while(1==1) {
            		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

 				   Date today = Calendar.getInstance().getTime();        
 				   lblNewLabel.setText(String.valueOf(df.format(today)));
                 try {
 					sleep(1000);
 				} catch (InterruptedException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
            	}
            	
            }
        };
        thread.start();
		
		
		
		
		
		
		connectButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				int i = okcancel("Da li ste sigurni ?");			    
			    if(i == 0) {
			    	if(conectTest > 0) {
			    		chosenPort.closePort();
						portList.setEnabled(true);
						exitButton.setEnabled(true);
						dbToxlsButton.setEnabled(true);
						connectButton.setText("Connect");
			    		textPane.setText("Konektovanje je okoncano.");
			    		conectTest = 0;
			    	}else {
			    		
			    	
			    	textPane.setText("Konektovanje je pokrenuto.");
			    	conectTest++;
			    
				
				if(connectButton.getText().equals("Connect")) {
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
					if(chosenPort.openPort()) {
						connectButton.setText("Disconect");
						portList.setEnabled(false);
						exitButton.setEnabled(false);
						dbToxlsButton.setEnabled(false);
					}
					
					Thread tred = new Thread() {
						@Override public void run(){
							InputStream in = chosenPort.getInputStream();

							String broj = "0";
							try
							{
							   int brojacBRE = 0;
							   boolean flagBRE1 = true;
							   String stringBRE = "";
							   
							   while(flag) {
								 
								   char kar = (char)in.read();
								   
								   debag += kar;
								   if(kar == '\n') {
									   textPane.setText(debag);
								   }
								   //System.out.println(kar);
								   if(flagBRE1 == false) {
									   if(kar != '<') {
										   stringBRE = stringBRE + kar;
										   //System.out.print(kar);
									   }else {
										   String t1 = "";
										   String t2 = "";
										   String t3 = "";
										   String t4 = "";
										   String t5 = "";
										   String t6 = "";
										   String m1 = "";
										   int brojacstringBRE = 0;
										   
										   for(int i = 0; i < stringBRE.length(); i++) {
											   if(brojacstringBRE == 0) {
												   if(!Character.isWhitespace(stringBRE.charAt(i))) {
													   t1 = t1 + stringBRE.charAt(i);
												   }else {
													   brojacstringBRE++;
													   continue;
												   }
											   }
											   if(brojacstringBRE == 1) {
												   if(!Character.isWhitespace(stringBRE.charAt(i))) {
													   t2 = t2 + stringBRE.charAt(i);
												   }else {
													   brojacstringBRE++;
													   continue;
												   }
											   }
											   if(brojacstringBRE == 2) {
												   if(!Character.isWhitespace(stringBRE.charAt(i))) {
													   t3 = t3 + stringBRE.charAt(i);
												   }else {
													   brojacstringBRE++;
													   continue;
												   }
											   }
											   if(brojacstringBRE == 3) {
												   if(!Character.isWhitespace(stringBRE.charAt(i))) {
													   t4 = t4 + stringBRE.charAt(i);
												   }else {
													   brojacstringBRE++;
													   continue;
												   }
											   }
											   if(brojacstringBRE == 4) {
												   if(!Character.isWhitespace(stringBRE.charAt(i))) {
													   t5 = t5 + stringBRE.charAt(i);
												   }else {
													   brojacstringBRE++;
													   continue;
												   }
											   }
											   if(brojacstringBRE == 5) {
												   if(!Character.isWhitespace(stringBRE.charAt(i))) {
													   t6 = t6 + stringBRE.charAt(i);
												   }else {
													   brojacstringBRE++;
													   continue;
												   }
											   }
											   if(brojacstringBRE == 6) {
												   if(stringBRE.charAt(i) != '<') {
													   m1 = m1 + stringBRE.charAt(i);
												   }else {
													   brojacstringBRE = 0;
													   break;
												   }
											   }
											   
										   }
										   vrednostT1 = Float.parseFloat(t1);
										   vrednostT2 = Float.parseFloat(t2);
										   vrednostT3 = Float.parseFloat(t3);
										   vrednostT4 = Float.parseFloat(t4);
										   vrednostT5 = Float.parseFloat(t5);
										   vrednostT6 = Float.parseFloat(t6);
										   vrednostM1 = Float.parseFloat(m1);
										   //System.out.println(vrednost);
										   //slider.setValue(vrednost);
										   series.add(apcisa++, vrednostT1);
										   apcisa--;
										   series2.add(apcisa++, vrednostT2);
										   apcisa--;
										   series3.add(apcisa++, vrednostT3);
										   apcisa--;
										   series4.add(apcisa++, vrednostT4);
										   apcisa--;
										   series5.add(apcisa++, vrednostT5);
										   apcisa--;
										   series6.add(apcisa++, vrednostT6);
										   apcisa--;
										   series7.add(apcisa++, vrednostM1);
										   broj = "0";
										   windows.repaint();
										   
										   
										   //UZORKOVANJE TRENUTNOG VREMENA
										   DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

										   // Get the date today using Calendar object.
										   Date today = Calendar.getInstance().getTime();        
										   // Using DateFormat format method we can create a string 
										   // representation of a date with the defined format.
										   String reportDate = df.format(today);
										   // Print what date is today!
										   //System.out.println("Report Date: " + reportDate);
										   //OVDE IDE ZA POPUNJAVANJE TABELE
										 //POPUNJAVANJE TABELA
										   Connection c = null;
									       Statement stmt = null;
										   try {
									            Class.forName("org.sqlite.JDBC");
									            c = DriverManager.getConnection("jdbc:sqlite:Akvizicija_sa_vage.db");

									            //System.out.println("Uspesno konektovano na bazu");

									            stmt = c.createStatement();
									            String sql = "INSERT INTO artikal (id,temperatura_1,temperatura_2,temperatura_3,temperatura_4,temperatura_5,temperatura_6,jedinice,masa_1,jedinica,vreme) " +
									                    "VALUES ("+apcisa+", "+vrednostT1+", "+vrednostT2+", "+vrednostT3+", "+vrednostT4+", "+vrednostT5+", "+vrednostT6+", '[C]', "+vrednostM1+", '[g]', '"+reportDate+"' );";
									            stmt.executeUpdate(sql);
									            stmt.close();


									        } catch ( Exception e ) {
									            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
									            textPane.setText(e.getClass().getName() + ": " + e.getMessage());
									        } finally{
									            try {
									                /*Zatvaramo konekciju sa bazom u slucaju da se desi neki
									                   izuzetak ili ako sve uspe uspesno da se izvrsi
									                 */
									                c.close();
									            } catch (SQLException e) {
									                e.printStackTrace();
									                textPane.setText("Problem kod unosenja podataka u bazu!"); // ovde ce da se stavlja konkatanira debag :D
									                
									            }
									        }
									        //System.out.println("Uspesno ubacene vrednosti");
										   
										   flagBRE1 = true;
										   stringBRE = "";
										   System.out.println(vrednostT1);
										   System.out.println(vrednostT2);
										   System.out.println(vrednostT3);
										   System.out.println(vrednostT4);
										   System.out.println(vrednostT5);
										   System.out.println(vrednostT6);
										   System.out.println(vrednostM1);
										   System.out.println();
										   
										   //textPane.setText(debag); // ovde ce da se stavlja konkatanira debag :D
										   
										   
										   series.add(apcisa, vrednostT1);
										   series2.add(apcisa, vrednostT2);
										   series3.add(apcisa, vrednostT3);
										   series4.add(apcisa, vrednostT4);
										   series5.add(apcisa, vrednostT5);
										   series6.add(apcisa, vrednostT6);
										   series7.add(apcisa, vrednostM1);
										   broj = "0";
										   windows.repaint();
									   }
								   }
								   if(kar == '>') {
									   brojacBRE++;
									   if(brojacBRE == 3) {
										   brojacBRE = 0;
										   flagBRE1 = false;
									   }
								   }
								   if(kar == '<') {
									   debBr++;
									   if(debBr>2) {
										   textPane.setText(debag);
									   }
								   }
								   
								   
								   //UZORKOVANJE TRENUTNOG VREMENA
								   DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

								   // Get the date today using Calendar object.
								   Date today = Calendar.getInstance().getTime();        
								   // Using DateFormat format method we can create a string 
								   // representation of a date with the defined format.
								   String reportDate = df.format(today);
								   // Print what date is today!
								   //System.out.println("Report Date: " + reportDate);
								   
								   
								   
							   }
							   brojacBRE = 0;
							   flagBRE1 = true;
							   stringBRE = "";
							   System.out.println();
							} catch (Exception e) {
								//textPane.setText("Problem sa serial portom"); // ovde ce da se stavlja konkatanira debag :D
							}
						}
					};
					tred.start();
				}else {
					//diskonektuj
					//flag = false;
					/*
					chosenPort.closePort();
					portList.setEnabled(true);
					exitButton.setEnabled(true);
					dbToxlsButton.setEnabled(true);
					connectButton.setText("Connect");*/
					//series.clear();
					
				}
			    }
			}
			}
		});
		
		
		
		dbToxlsButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				int i = okcancel("Da li ste sigurni ?");			    
			    if(i == 0) {
			    	textPane.setText("Sacuvavanje u .xls file je pokrenuto.");	
			    
				Thread tred2 = new Thread() {
					@Override
					public void run(){
						
						
							//OVDE POCINJE CITANJE IZ BAZE PODATAKA I PISANJE U TXT
							Connection c = null;
						    Statement stmt = null;
							try {
								Class.forName("org.sqlite.JDBC");
					            c = DriverManager.getConnection("jdbc:sqlite:Akvizicija_sa_vage.db");
					            stmt = c.createStatement();
								ResultSet rs = stmt.executeQuery( "SELECT * FROM artikal" );
								/*
								 * String sql = "INSERT INTO artikal (id,temperatura_1,temperatura_2,temperatura_3,temperatura_4,temperatura_5,temperatura_6,jedinice,masa_1,jedinica,vreme) " +
									                    "VALUES ("+apcisa+", "+vrednostT1+", "+vrednostT2+", "+vrednostT3+", "+vrednostT4+", "+vrednostT5+", "+vrednostT6+", '[C]', "+vrednostM1+", '[g]', '"+reportDate+"' );";
								 */
								//PRIPREMA ZA EXCEL
								HSSFWorkbook wb = new HSSFWorkbook();
		                        HSSFSheet sheet = wb.createSheet("Excel Sheet");
		                        HSSFRow rowhead = sheet.createRow((short) 0);
		                        rowhead.createCell((short) 0).setCellValue("id");
		                        rowhead.createCell((short) 1).setCellValue("temperatura_1");
		                        rowhead.createCell((short) 2).setCellValue("temperatura_2");
		                        rowhead.createCell((short) 3).setCellValue("temperatura_3");
		                        rowhead.createCell((short) 4).setCellValue("temperatura_4");
		                        rowhead.createCell((short) 5).setCellValue("temperatura_5");
		                        rowhead.createCell((short) 6).setCellValue("temperatura_6");
		                        rowhead.createCell((short) 7).setCellValue("jedinice");
		                        rowhead.createCell((short) 8).setCellValue("masa_1");
		                        rowhead.createCell((short) 9).setCellValue("jedinica");
		                        rowhead.createCell((short) 10).setCellValue("vreme");
		                        int index = 1;
		                        while (rs.next()) {

		                                HSSFRow row = sheet.createRow((short) index);
		                                row.createCell((short) 0).setCellValue(rs.getInt(1));
		                                row.createCell((short) 1).setCellValue(rs.getInt(2));
		                                row.createCell((short) 2).setCellValue(rs.getString(3));
		                                row.createCell((short) 3).setCellValue(rs.getString(4));
		                                row.createCell((short) 4).setCellValue(rs.getString(5));
		                                row.createCell((short) 5).setCellValue(rs.getString(6));
		                                row.createCell((short) 6).setCellValue(rs.getString(7));
		                                row.createCell((short) 7).setCellValue(rs.getString(8));
		                                row.createCell((short) 8).setCellValue(rs.getString(9));
		                                row.createCell((short) 9).setCellValue(rs.getString(10));
		                                row.createCell((short) 10).setCellValue(rs.getString(11));
		                                index++;
		                        }
		                        FileOutputStream fileOut = new FileOutputStream("Prikupljeni_podatci.xls");
		                        wb.write(fileOut);
		                        fileOut.close();
		                        rs.close();
		                        stmt.close();
		                        c.close();	
		                        textPane.setText("Uspesno ste kreirali .xls file i memorisali u njega sve do sada.");
								
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								textPane.setText("problem sa citanjem iz baze podataka."); // ovde ce da se stavlja konkatanira debag :D
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								textPane.setText("Nema .db fajla za citanje."); // ovde ce da se stavlja konkatanira debag :D
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								textPane.setText("Problem sa serijskom komunikacijom 2."); // ovde ce da se stavlja konkatanira debag :D
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								textPane.setText("Nema klase."); // ovde ce da se stavlja konkatanira debag :D
								e.printStackTrace();
							}
					}
				};
				tred2.start();
			}
				
				
			}
		});
		
		
		windows.setVisible(true);
		
		
		//-----sada ide rad sa serijalizacijom
		
		// OVDE POCINJE BIRANJE PORTA KOJI CEMO DA KORISTIMO
		//boolean flag = true;

		SerialPort comPort[] = SerialPort.getCommPorts();
		
		
		
		//System.out.println("Izaberite port: ");
		int i = 1;
		for(SerialPort port : comPort) {
			//System.out.println(i++ + ". " + port.getSystemPortName());
			portList.addItem(port.getSystemPortName());
			
		}
		
		//PRAVLJENJE BAZE PODATAKA
		Connection c = null;
        try {
            //Inicjalizujemo drajver za SQLite
            Class.forName("org.sqlite.JDBC");
            //Upostavljamo konekciju sa bazom
            c = DriverManager.getConnection("jdbc:sqlite:Akvizicija_sa_vage.db");
            //SQL naredbe koje zelimo da posaljemo bazi
        } catch ( Exception e )
        /*Hvatamo bilo kakav izuzetak koji moze da znaci
           da ne mozemo da uspostavimo konekciju sa bazom
         */
        
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            textPane.setText(e.getClass().getName() + ": " + e.getMessage()); // ovde ce da se stavlja konkatanira debag :D
        } finally{
            try {
                /*Zatvaramo konekciju sa bazom u slucaju da se desi neki
                   izuzetak ili ako sve uspe uspesno da se izvrsi
                 */
                c.close();
            } catch (SQLException e) {
            	textPane.setText("Problem sa bazom 3"); // ovde ce da se stavlja konkatanira debag :D
                e.printStackTrace();
            }
        }
        //System.out.println("Uspesno kreirao bazu podataka");
        
        
        //KREIRANJE TABELA
        //Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Akvizicija_sa_vage.db");
            //System.out.println("Uspesno konektovano na bazu");

            /*
               Sve kolone imaju postavljeno NOT NULL mora se svakoj
               koloni navesti vrednost
               Kolona id je proglasena za primarni kljuc sa kljucnim recima
               PRRIMARY KEY
             */
            stmt = c.createStatement();
            String sql = "CREATE TABLE artikal " +
                    "(id      INT PRIMARY KEY     NOT NULL," +
                    " temperatura_1   FLOAT    NOT NULL, " +
                    " temperatura_2   FLOAT    NOT NULL, " +
                    " temperatura_3   FLOAT    NOT NULL, " +
                    " temperatura_4   FLOAT    NOT NULL, " +
                    " temperatura_5   FLOAT    NOT NULL, " +
                    " temperatura_6   FLOAT    NOT NULL, " +
                    " jedinice    TEXT     NOT NULL, " +
                    " masa_1   FLOAT    NOT NULL, " +
                    " jedinica    TEXT     NOT NULL, " +
                    " vreme    TEXT     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


        } catch ( Exception e ) {
        	textPane.setText(e.getClass().getName() + ": " + e.getMessage());
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally{
            try {
                /*Zatvaramo konekciju sa bazom u slucaju da se desi neki
                   izuzetak ili ako sve uspe uspesno da se izvrsi
                 */
                c.close();
            } catch (SQLException e) {
            	textPane.setText("Problem sa bazom 4");
                e.printStackTrace();
            }
        }
		
		exitButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				int i = okcancel("Da li ste sigurni ?");			    
			    if(i == 0) {
			    	//JOptionPane.showMessageDialog(new JFrame(), "Eggs are not supposed to be green.");
					//BRISE DB FILE
					boolean result = new File("Akvizicija_sa_vage.db").delete();
					boolean resultat = new File("-.txt").delete();
					//IZLAZI IZ PROGRAMA
					System.exit(0);
			    }
			    
			    
				
			}
		});
		
		
		//-------------------
		

	}
	public static int okcancel(String theMessage) {
	    int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
	        "Upozorenje", JOptionPane.OK_CANCEL_OPTION);
	    return result;
	  }
}


