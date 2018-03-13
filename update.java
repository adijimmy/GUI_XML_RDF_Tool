// Form to update 
package xml;
import  java.awt.*;
import  java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import  javax.swing.*;
import  javax.swing.event.*;


public class update extends JFrame
   implements ActionListener 
{
    String tn="", td="", ta="";
 GridBagConstraints gbc;
 GridBagLayout gbl;
 JTextField tf1,tf2,tag_name;
 JButton b1,b2;
 JComboBox jcbPhones;
 int node= treePanel.node1;
   public update()
   {
     if(treePanel.add_button==true)
         setTitle("Add Node at "+treePanel.node1);
     else if(treePanel.add_btw==true)
         setTitle("Add between Nodes");
     else setTitle("Update Node Tag "+treePanel.node1);
    // this.pb = pb;
     JLabel l1 = new JLabel("Tag Name");
     tag_name= new JTextField(20);
     //jcbPhones.addItemListener(this);
     JLabel l2 = new JLabel("Tag Attribute ");
     tf1 = new JTextField(20);  
     JLabel l3 = new JLabel("Tag Data");
     tf2 = new JTextField(20);
     
     if(treePanel.add_button==false)
     b1= new JButton("Update");
     else b1= new JButton("Add Node");
     b1.addActionListener(this);
     
     
     if(treePanel.update_button)
     {
         tag_name.setText(xmlparser.tag_obj[node].tag_name);
         tf1.setText(xmlparser.tag_obj[node].tag_attribute);
         tf2.setText(xmlparser.tag_obj[node].tag_data);
     }
     gbl = new GridBagLayout();
     gbc  = new GridBagConstraints();
     gbc.anchor = GridBagConstraints.WEST;
     getContentPane().setLayout(gbl);
     displayComponent(l1,0,0,1,1);
     displayComponent(tag_name,1,0,2,1);
     displayComponent(l2,0,1,1,1);
     displayComponent(tf1,1,1,2,1);
     displayComponent(l3,0,2,1,1);
     
     displayComponent(tf2,1,2,1,1);
     displayComponent(b1,1,3,1,1);     
    

     setSize(300,200);
     setVisible(true);


   } // end of init

   
   public void actionPerformed(ActionEvent evt)
   {
        tn = tag_name.getText();
            ta = tf1.getText();
            td= tf2.getText();
            
            if(tn.equals(""))
            {
                JOptionPane.showMessageDialog(this, "The tag name cannot be null"); 
            }
            else {
                
                ErrorCheck();
                System.out.println("Add button status= "+treePanel.add_button);
                if(treePanel.add_button==true || treePanel.add_btw==true)// add new node 
                {
                    
                        xmlparser.node_number++;
                        System.out.println("Node added"+ xmlparser.node_number);
                        int child= xmlparser.node_number;
                        xmlparser.tag_obj[child].parent_name= node;
                        xmlparser.tag_obj[node].child.add(child);
                        xmlparser.tag_obj[child].tag_name=tn; 
                        xmlparser.tag_obj[child].node_index=child; 
                       
                        xmlparser.tag_obj[child].tag_data=td;
                         if(!ta.equals("NULL"))
                                 xmlparser.tag_obj[child].tag_attribute=ta;
                       treePanel.add_button= false;
                       treePanel.add_btw=false;
                }
                else if(treePanel.update_button)// update the clicked node
                {
                    System.out.println("Node updated");
                        xmlparser.tag_obj[node].tag_name=tn; 
                        xmlparser.tag_obj[node].tag_data=td;
                       xmlparser.tag_obj[node].tag_attribute=ta;
                       treePanel.update_button=false;
                }
                
            try {
                treePanel.print_parent_child_relation_edit();
            } catch (IOException ex) {
                Logger.getLogger(update.class.getName()).log(Level.SEVERE, null, ex);
            }
               System.out.println(" Tag Name :"+ xmlparser.tag_obj[node].tag_name+ " Tag Data: "+ xmlparser.tag_obj[node].tag_data + " Tag Att "+ xmlparser.tag_obj[node].tag_attribute);
            }
   

  }
   
  public void displayComponent(Component c, int x, int y, int w, int h)
 { 
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.gridwidth = w;
    gbc.gridheight = h;
    gbl.setConstraints(c,gbc);
    getContentPane().add(c);
 } // end of displayComponent

    public void ErrorCheck() 
    {
        
    }

   
} // end of class     
