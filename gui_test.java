package xml;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.*;

 
public  class gui_test 
{
    public gui_test() throws IOException
    {
        xmlparser.counter++;
            draw_Frame(); 
    }
    // draw panel 
    
        public void draw_Frame() throws IOException
        {
            if(xmlparser.frame_first_time==true)
            {
                xmlparser.frame_first_time= false;
                        xmlparser.frame = new JFrame();
            
                    try
                    {
                          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    }
                      catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
                      {
                              System.out.println("Unable to set Look and feel : "+e);             
                      } 
                    xmlparser.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C://Users//cyber//Documents//NetBeansProjects//XML//src//xml//icon.png"));
            }
           
                dump_xml dumpit = new dump_xml();
                 
            
                    xmlparser.frame.setTitle("XML PARSER TREE");
                     xmlparser.frame.setVisible(true);
                     xmlparser.frame.setSize(1360,720);
                        
                     //setLocation(JFrame);
                     xmlparser.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    // setMenuBar();
             System.out.println("First time = "+xmlparser.frame_first_time+ " Called sdf,sm,= "+"No. of times gui test class called "+xmlparser.counter);
            treePanel gui= new treePanel();
            xmlparser.frame.add(gui);
        }

    public void setMenuBar() 
    {
        JMenuBar mb= new JMenuBar();
        JMenu functions= new JMenu("Funtions");
        mb.add(functions);
        JMenuItem edit= new JMenuItem("Swap");
        JMenuItem delete= new JMenuItem("Delete Subtree");
        JMenuItem delete_node= new JMenuItem("Delete Single");
        functions.add(edit);
        functions.add(delete);
        functions.add(delete_node);
        xmlparser.frame.add(mb,BorderLayout.NORTH);   
    }
}
