
package test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Java Program to create a text editor using java
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;


class Test extends JFrame implements ActionListener {
	// Text component
	public static  JTextArea t;
        
        public static  JTextField tt;
        
        String temp_data="";

	// Frame
	public static JFrame f;

	// Constructor
	Test()
       
	{
            
         
		// Create a frame
		f = new JFrame("Testing System for Backbone Configuretion ");
                Image im = Toolkit.getDefaultToolkit().getImage(getClass().getResource("n2.png"));
 
    f.setIconImage(im);
    
                
                
               

		try {
			// Set metal look and feel
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			// Set theme to ocean
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
		}
              
		// Text component
		t = new JTextArea();
              
                /////////////////////////
                
               //instantiate frame
    //instantiate textArea
    final UndoManager undo = new UndoManager(); //instantiate an UndoManager
    
    Document doc = t.getDocument();  //instantiate a Document class of the txtArea

    doc.addUndoableEditListener((UndoableEditEvent evt) -> {
        undo.addEdit(evt.getEdit());
                });

    t.getActionMap().put("Undo", new AbstractAction("Undo") {
      @Override
      public void actionPerformed(ActionEvent evt) {
        try {
          if (undo.canUndo()) {
            undo.undo();
            
          }
        } catch (CannotUndoException e) {
        }
      }
    });

    t.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

    
                ///////////////////////////////
                
                
             
   
    // textArea.setBackground(color);
                  
 //Set JTextArea background color to color that you choose
               t.setBackground(Color.white);
              
               t. setForeground(Color.BLACK);
               t.setCaretColor(Color.BLACK);
           
               t. setFont(new Font("Arial", Font.PLAIN, 18));
                
                
            
             
		// Create a menubar
		JMenuBar mb = new JMenuBar();


		// Create amenu for menu
		JMenu m1 = new JMenu("File");

		// Create menu items
		JMenuItem mi1 = new JMenuItem("New");
		JMenuItem mi2 = new JMenuItem("Open");
		JMenuItem mi3 = new JMenuItem("Save");
		JMenuItem mi9 = new JMenuItem("Print");

		// Add action listener
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi9.addActionListener(this);
                  m1.setBackground(Color.white);
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi9);
               

		// Create amenu for menu
		JMenu m2 = new JMenu("Edit");

		// Create menu items
		JMenuItem mi4 = new JMenuItem("cut");
		JMenuItem mi5 = new JMenuItem("copy");
		JMenuItem mi6 = new JMenuItem("paste");

		// Add action listener
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);

                m2.setBackground(Color.white);
		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);
                

		JMenuItem mc = new JMenuItem("New");
                
             
                JMenuItem mcc = new JMenuItem("clear");
                JMenuItem mc2 = new JMenuItem("Testing For Resum");
                JMenuItem mc3 = new JMenuItem("Testing For Suspention");
                
           
                
                 mb.setBackground(Color.white);
                 mc.setBackground(Color.white);
              
                 mcc.setBackground(Color.white);
                 mc2.setBackground(Color.white);
                 mc3.setBackground(Color.white);
                 
                 
		mc.addActionListener(this);
               
                mcc.addActionListener(this);
                mc2.addActionListener(this);
                mc3.addActionListener(this);
                
		mb.add(m1);
		mb.add(m2);
		mb.add(mc);
                  
              
                  mb.add(mcc);
                mb.add(mc2);
                mb.add(mc3);
                 ;
               
                ///////////////////////
                  //JTextField simpleinput=new JTextField();
        
                
                ///////////////////////
                JScrollPane scrolltxt = new JScrollPane(t);
                
               
        

		f.setJMenuBar(mb);
                
		f.add(scrolltxt);
               
		f.setSize(1200, 865);
		f.show();
	}

	// If a button is pressed
        @Override
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();

            switch (s) {
                case "cut":
                    t.cut();
                    break;
                case "copy":
                    t.copy();
                    break;
                case "paste":
                    t.paste();
                    break;
                case "Save":
                    {
                        // Create an object of JFileChooser class
                        JFileChooser j = new JFileChooser("f:");
                        // Invoke the showsSaveDialog function to show the save dialog
                        int r = j.showSaveDialog(null);
                        if (r == JFileChooser.APPROVE_OPTION) {
                            
                            // Set the label to the path of the selected directory
                            File fi = new File(j.getSelectedFile().getAbsolutePath());
                            
                            try {
                                // Create a file writer
                                FileWriter wr = new FileWriter(fi, false);
                                
                                // Write
                                try ( // Create buffered writer to write
                                        BufferedWriter w = new BufferedWriter(wr)) {
                                    // Write
                                    w.write(t.getText());
                                    
                                    w.flush();
                                }
                            }
                            catch (IOException evt) {
                                JOptionPane.showMessageDialog(f, evt.getMessage());
                            }
                        }
                        // If the user cancelled the operation
                        else
                            JOptionPane.showMessageDialog(f, "the user cancelled the operation");
                        break;
                    }
                case "Print":
                    try {
                        // print the file
                        t.print();
                        
                    }
                    catch (PrinterException evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                    break;
                case "Open":
                    {
                        // Create an object of JFileChooser class
                        JFileChooser j = new JFileChooser("f:");
                        // Invoke the showsOpenDialog function to show the save dialog
                        int r = j.showOpenDialog(null);
                        // If the user selects a file
                        if (r == JFileChooser.APPROVE_OPTION) {
                            // Set the label to the path of the selected directory
                            File fi = new File(j.getSelectedFile().getAbsolutePath());
                            
                            try {
                                // String
                                String s2 = "", sl = "";
                                String qq="";
                                ArrayList<String> al = new ArrayList<>();
                                
                                // File reader
                                FileReader fr = new FileReader(fi);
                                
                                // Buffered reader
                                BufferedReader br = new BufferedReader(fr);
                                
                                // Initialize sl
                                sl = br.readLine();
                                sl = sl.trim();
                                al.add(sl);
                                
                                // Take the input from the file
                                while ((s2 = br.readLine()) != null) {
                                    sl = sl + "\n" + s2;
                                    sl = s2.trim();
                                    al.add(sl);
                                    
                                }
                                
                                // Set the text
                                // For loop for iterating over the List
                                for (int i = 0; i < al.size(); i++) {
                                    
                                    // Print all elements of List
                                    //System.out.println(al.get(i));
                                    //data clear
                                    
                                    //out put
                                    qq = qq + "\n" + al.get(i);
                                }
                                
                                
                                t.setText(qq);
                                //System.out.println(al);
                                
                                
                                
                                
                                
                                
                                
                                
                            }
                            catch (IOException evt) {
                                JOptionPane.showMessageDialog(f, evt.getMessage());
                            }
                        }
                        // If the user cancelled the operation
                        else
                            JOptionPane.showMessageDialog(f, "the user cancelled the operation");
                        break;
                    }
                case "New":
                    t.setText("");
                    break;
                case "close":
                    f.setVisible(false);
                    break;
                case "clear":
                    {
               
                
               
                        ArrayList<String> alr = new ArrayList<>();
                        String input =t.getText();
                        input = input.trim();
                        String qq="";
                        
                        // For loop for iterating over the List
                        String[] lines = input.split("\\r?\\n");
                        for (String line : lines) {
                           // System.out.println(line);
                           
                            if(find("^\\s*ip-host description.*?\\d+(M|K)[^ ]*?$",line)){
                                alr.add(line);
                            
                            
                           
                            }else{
                           alr.add( realfind("ip-host description.*?\\d+(M|K)[^ ]*?$",line));
                            }
                            
                        }
                        
                         alr.removeAll(Collections.singletonList(""));
                        for (int i = 0; i < alr.size(); i++) {
                            
                            qq = qq + "\n" + alr.get(i).trim();
                        } 
                      qq=qq.trim();
                 
                        t.setText(qq);
                   
 
        
                        break;
                    }
                
               
                      
                
                  
                   
                
            
                
               
                case "Testing For Resum":
                    // Create an object of JFileChooser class
                    
                    
                    // If the user selects a file
                        
                    try {
                        // String
                        
                        String qq="";
                        ArrayList<String> alr = new ArrayList<>();
                        ArrayList<String> alro = new ArrayList<>();
                        
                        
                        String input =t.getText();
                        input = input.trim();
                        
                        
                        // For loop for iterating over the List
                        
                        
                        String[] lines = input.split("\\r?\\n");
                        for (String line : lines) {
                           // System.out.println(line);
                            if(find("^ip-host description.*?\\d+(M|K)[^ ]*?$",line)){
                                alr.add(line);}
                            
                        }
                        
                        
   int count =0;  
   int count2=alr.size();
     int count3 =0; 
//opreation
for (int i = 0; i < alr.size(); i++) {

    
    if( false==find("b\\d{3,}\\s",alr.get(i)))
    {
          if(find("vlan \\d{4}",alr.get(i))){
              
         if(find("sec-vlan \\d{3,}",alr.get(i))){
      
         count++;
         }else if (find("sec-vlan",alr.get(i))==false)  {
         count++;
         }
          
          }
    }
    
    String a = realfind("\\d{9,16}\\-{0,2}\\.{0,2}[a-zA-Z]{0,1}\\w{0,15}-{0,2}\\.{0,2}\\w{0,15}\\-{0,2}\\.{0,2}\\w{0,15}\\-{0,2}\\.{0,2}\\w{0,15}\\-{0,2}\\.{0,2}\\w{0,15}-{0,2}\\.{0,2}\\w{0,15}\\.{0,2}\\-{0,2}\\d{0,3}\\-{0,1}\\w{0,5}\\-{0,1}\\d{0,3}\\-{0,1}",alr.get(i));
   
    if(false==find("-{2,}",a)){
    if(find("\\d{9,}-\\w+-\\w*",a)){
        count3++;
    }
    
    }
}


t.setText("Total number of inpute \t"+ count2+"\n Number of successful oppretion \t"+ count+"\n"+"Number of correct writing format style \t"+count3+"\n");


                    }
                    catch (Exception evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                    break;
                
                case "Testing For Suspention":
                  
                    try {
                        // String
                        
                        String qq="";
                        ArrayList<String> alr = new ArrayList<>();
                        ArrayList<String> alro = new ArrayList<>();
                        
                        
                        String input =t.getText();
                        input = input.trim();
                        
                        
                        // For loop for iterating over the List
                        
                        
                        String[] lines = input.split("\\r?\\n");
                        for (String line : lines) {
                      //      System.out.println(line);
                            if(find("^ip-host description.*?\\d+(M|K)[^ ]*?$",line)){
                                alr.add(line);}
                            
                        }
                        
       int count =0;  
       int count3=0;
   int count2=alr.size();                   
//opreation
for (int i = 0; i < alr.size(); i++) {
   // System.out.println("yonau");
   
        if( find("b\\d{3,}\\s",alr.get(i)))
    {
          if(find("vlan \\d{2,}",alr.get(i))){
              
        
          if (find("sec-vlan",alr.get(i))==false)  {
         count++;
         }
          
          }
    }
    
    
 String a = realfind("\\d{9,16}\\-{0,2}\\.{0,2}[a-zA-Z]{0,1}\\w{0,15}-{0,2}\\.{0,2}\\w{0,15}\\-{0,2}\\.{0,2}\\w{0,15}\\-{0,2}\\.{0,2}\\w{0,15}\\-{0,2}\\.{0,2}\\w{0,15}-{0,2}\\.{0,2}\\w{0,15}\\.{0,2}\\-{0,2}\\d{0,3}\\-{0,1}\\w{0,5}\\-{0,1}\\d{0,3}\\-{0,1}",alr.get(i));
   
    if(false==find("-{2,}",a)){
    if(find("\\d{9,}-\\w+-\\w*",a)){
        count3++;
    }
    
    }
}
t.setText("Total number of inpute \t"+ count2+"\n Number of successful oppretion \t"+ count+"\n"+"Number of correct writing format style \t"+count3+"\n");



                    }
                    catch (Exception evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                    break;
                
              
            }}      
                
                
        
        
    public String realfind (String x ,String y){
    final String regex = x;
     String ypo="";
        final String string = y;
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        
        while (matcher.find()) {
           // System.out.println("Full match: " + matcher.group(0));
           ypo =  matcher.group(0); 
           
    } 
    
     return ypo;
    }  
    public   String sub(String x  ,String y,String z ){
    final String regex = x;
        final String string = z;
        final String subst = y;
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        
        // The substituted value will be contained in the result variable
        final String result = matcher.replaceAll(subst);
        
        //System.out.println("Substitution result: " + result);
    return  result;
    }
    
    public  boolean find  (String x,String row){
     final String regex = x;
             final String stringt = row;
        final Pattern patternt = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matchert = patternt.matcher(stringt);
              
            return matchert.find();
        }
    public  String[] findLine(String searchWord, String filename) throws IOException
 {
     
    String[] ans = new String[2];
     
     String replace="";
    
    
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            
            while( (line = br.readLine( )) != null){
                
                  if (line.contains("interface vbui"))
                {
                replace = line.replace("interface", "");
                    
                    
                    
                    
                }
                  
                  else if(line.contains("BUP")){
                      replace = line.replace("BUP", "");
                      
                  }
                if (line.contains(searchWord))
                {
                   
                    
                    ans[0]=line;
                    ans[1]=filename+replace;
                    
                 break;
                }
            }
            
        }
        
        return ans;
 } 
    
    public  ArrayList<String> findLine2( String filename) throws IOException
 {
    

   
     ArrayList<String> alro = new ArrayList<>(); 
     ArrayList<String> alro2 = new ArrayList<>(); 
     ArrayList<String> alro3 = new ArrayList<>();
     
     String replace="";
    
        
    
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            
            while( (line = br.readLine( )) != null){
                
            alro.add(realfind("\\d{9,15}",line));
           alro .removeIf(String::isEmpty); 
           
                 
               
            }
           
         ///////////
         
        Set<String> store = new HashSet<>();

        alro.stream().filter((name) -> (store.add(name) == false)).forEachOrdered((name) -> {
            alro2.add(name);
         }); //////////////
         
         
            
        }
          
         alro3.addAll(alro2);
          
        for(String searchWord : alro2){
        try (BufferedReader br2 = new BufferedReader(new FileReader(filename))) {
            String line;
            
            while( (line = br2.readLine( )) != null){
                
                  if (line.contains("interface vbui"))
                {
                replace = line.replace("interface", "");
                    
                    
                    
                    
                }
                if (line.contains(searchWord))
                {
                   
                   
                    alro3.add(filename+replace+"="+line);
                     
                    
                 
                }
                }
            }
            
        }
        
                
      
        
        return alro3;
 }   
    
     
      public  String[] VfindLine(String searchWord, String filename) throws IOException
 {
     
    String[] ans = new String[4];
     
     ArrayList<String> add1 = new ArrayList<>();
boolean ans2=false ;
     
          
  
    
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
             out:
            
             while( (line = br.readLine( )) != null)
            {
            line=line.trim();
              add1.add(line);
                if (line.contains(searchWord))
                { 
                    int x=add1.indexOf(line);
                    ans[0]=line;
                    ans[1]=filename;
                    ans[2]=add1.get(x-1);
                 while( (line = br.readLine( )) != null){
                    
                 if (line.contains(ans[2])){
                     line = br.readLine( );
                 if(line.contains("vrrp")){
                 ans2=true;
                 break out;
                 }
                 }    
                 }                
                   
 
                    
                }
            }
             
                    
        if (ans2)
            ans[3]="T";
        else
            ans[3]="F";
             
            
        }
        
        return ans;
 }
    
    
    
	// Main class
	public static void main(String args[])
	{
            
            
		Test e = new Test();
                
                

        //Popup menu Code
        JPopupMenu jPopupMenu=new JPopupMenu("Edit");

        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem paste=new JMenuItem("Paste");

        jPopupMenu.add(cut);
        jPopupMenu.add(copy);
        jPopupMenu.add(paste);
        //End Popup Menu Code

        f.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(SwingUtilities.isRightMouseButton(mouseEvent)){
                    jPopupMenu.show(f,mouseEvent.getX(),mouseEvent.getY());
                }
            }
        });

        //End Popup Code
        //Example Popup menu
        //Popup menu Code
        JPopupMenu jPopupMenu2=new JPopupMenu("Edit");

        JMenuItem cut2=new JMenuItem("Cut");
        JMenuItem copy2=new JMenuItem("Copy");
        JMenuItem paste2=new JMenuItem("Paste");

        jPopupMenu2.add(cut2);
        jPopupMenu2.add(copy2);
        jPopupMenu2.add(paste2);
        //End Popup Menu Code

        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(SwingUtilities.isRightMouseButton(mouseEvent)){
                    jPopupMenu2.show(t,mouseEvent.getX(),mouseEvent.getY());
                }
            }
        });

        cut2.addActionListener((ActionEvent actionEvent) -> {
            t.cut();
                });

        copy2.addActionListener((ActionEvent actionEvent) -> {
            t.copy();
                });

        paste2.addActionListener((ActionEvent actionEvent) -> {
            t.paste();
                });
                
                
                
	}
}
