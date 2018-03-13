/*
XML MAIN FILE
*/


package xml;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 class node_position
{
    public int node_x=0;
    public int node_y=0;
    public node_position()
    {
        node_x=0;
        node_y=0;
    }
}
public class treePanel extends JPanel implements MouseListener, ActionListener
{
    public static Dimension panel_area_size= new Dimension(1300,760);
    public static int line_x1=0, line_x2=0, line_y1=0, line_y2=0,parent_node=0,i=0,j=0,node_number,node1=0,node2=0,first=0,first_node=0;
    public static boolean edit_button= false,delete_button=false,delete_single=false,update_button=false,add_button=false,add_btw=false, getcontent_button=false;
    public static int unit_width=0,h=0,w=0,x,y;
    public static int unit_height=0,node_num=0;
    public static int k=0;
    public static Cursor cursor_type= new Cursor(Cursor.HAND_CURSOR); 
    public static int grid[][]= new int[1000][1000];  //used for searching the node and query
    public static int cal_position_in_level[] = new int[1002];  
    public static node_position node_location[] = new node_position[1002];
    public treePanel()throws IOException
    {
        
        for(int node=0;node<=xmlparser.node_number+2;node++)
        {
            node_location[node]= new node_position();
        }
        
       
        
        setBackground(Color.white);
        addMouseListener(this);
        setPreferredSize(panel_area_size);
        setCursor(cursor_type);
        setBorder(BorderFactory.createLineBorder(Color.black, 2));
        JButton edit= new JButton("Swap Subtree");
        add(edit);
        edit.setBounds(100, 100, 50, 50);
        edit.addActionListener(new ActionListener(){ 
        @Override
            public void actionPerformed(ActionEvent e)
            {
                  edit_button=true;
                   // edit_tree();
                    
            }
        });
        JButton delete= new JButton("Delete Subtree");
        add(delete);
        delete.addActionListener(new ActionListener(){ 
        @Override
            public void actionPerformed(ActionEvent e)
            {
                  delete_button=true;
                   // edit_tree();
                    
            }
        });
        
        JButton delete_node= new JButton("Delete Node");
        add(delete_node);
        delete_node.addActionListener(new ActionListener(){ 
        @Override
            public void actionPerformed(ActionEvent e)
            {
                  delete_single=true;
                   // edit_tree();
                    
            }
        });
        JButton update_btn= new JButton("Update");
        add(update_btn);
        update_btn.addActionListener(new ActionListener(){ 
        @Override
            public void actionPerformed(ActionEvent e)
            {
                  update_button=true;
                   // edit_tree();
                    
            }
        });
        
        JButton add_btn= new JButton("Add Node");
        add(add_btn);
        add_btn.addActionListener(new ActionListener(){ 
        @Override
            public void actionPerformed(ActionEvent e)
            {
                  add_button=true;
                  System.out.println("Add button in teeepanel "+add_button);
                   // edit_tree();
                    
            }
        });
        
        JButton add_between= new JButton("Add Between Nodes");
        add(add_between);
        add_between.addActionListener(new ActionListener(){ 
        @Override
            public void actionPerformed(ActionEvent e)
            {
                  add_btw=true;
                  System.out.println("Add button in teeepanel "+add_button);
                   // edit_tree();
                    
            }
        });
        
        JButton getContent= new JButton("Get Content");
        add(getContent);
        getContent.addActionListener(new ActionListener(){ 
        @Override
            public void actionPerformed(ActionEvent e)
            {
                  
                  System.out.println("Get content Clicked");
            try {
                 getcontent_button= true;
                getContent gc= new getContent();
                 getcontent_button=false;
            } catch (IOException ex) {
                Logger.getLogger(treePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
         //creating the paint area panel
        make_grid(); //stores which node is at grid[i][j]
        find_cell_number(); 
       
    }
    
    // creating the graphics and paint component of the application
    @Override
    public void paintComponent(Graphics g)
    {
         g.setColor(Color.WHITE);
         g.fillRect(0, 0, this.getWidth(), this.getHeight());
                         
         Graphics2D g2 = (Graphics2D)g;
             w= this.getWidth();
             h = this.getHeight();
              

            
            unit_height= h/(2*(xmlparser.max_depth+1));
            unit_width= w/(2*xmlparser.maxm_diameter);
            
           
             addNode(g2); // all it for creating the nodes and edges
    } // paint component ended
    
    
    public void addNode(Graphics2D g2d)
    {
       // System.out.println("Unit height"+ unit_height+ " and unit width= " + unit_width);
        for(int i=0;i<=2*xmlparser.max_depth+2;i++)
        {
            for(int j=0;j<=2*xmlparser.maxm_diameter+2;j++)
            {
                if(grid[i][j]!=-1)
                {
                    
                   // System.out.println("Node = "+grid[i][j]+" at i ="+i +" and j= "+j);
                    node_num= grid[i][j];
                    y= node_location[node_num].node_y;
                    x= node_location[node_num].node_x; 
                    //System.out.println("x "+x+" y= "+y);
                    g2d.setColor(Color.BLACK);
                    if(node_num==0)
                    {
                         g2d.setColor(Color.CYAN);
                        g2d.fillRect(x*unit_width,y*unit_height,unit_width , unit_height/2);
                         g2d.drawString(xmlparser.tag_obj[node_num].tag_name,x*unit_width+2,y*unit_height+10);
                         g2d.setColor(Color.BLACK);
                       
                    }
                    else {
                       // g2d.setColor(Color.BLACK);
                        g2d.drawRect(x*unit_width,y*unit_height,unit_width , unit_height/2);
                         g2d.drawString(xmlparser.tag_obj[node_num].tag_name,x*unit_width,y*unit_height);
                        g2d.setColor(Color.BLACK);
                    }
                    
                   
                    parent_node= xmlparser.tag_obj[node_num].parent_name;
                    line_x1=node_location[parent_node].node_x;
                    line_y1= node_location[parent_node].node_y;
                    line_x2= x;
                    line_y2=y;
                    g2d.drawLine(unit_width*line_x1+15, unit_height*line_y1, unit_width*line_x2, unit_height*line_y2);
                }
            }
        }
    }// end of addNode
    
    
    // find the location of each node 
    public void find_cell_number()
    {
        System.out.println("Maxm depth after ="+xmlparser.max_depth+" and maxm diameter after= "+xmlparser.maxm_diameter);
        for(int idx=0;idx<=xmlparser.max_depth;idx++)
        {
            if(xmlparser.level_diameter[idx]>0)
            cal_position_in_level[idx]= xmlparser.maxm_diameter/xmlparser.level_diameter[idx];
        }
        int x=0,y=0;
        for(int idx=0;idx<=xmlparser.max_depth;idx++)// run for each loop
        {
            k=1;
            x= (2*idx);
           if(idx==0)
               x++;
            for(int node=0;node<=xmlparser.node_number;node++)
            {
                if(xmlparser.tag_obj[node].level_number==idx && xmlparser.tag_obj[node].presence==true)
                {
                    y=k*cal_position_in_level[idx]; k=k+2;
                    grid[x][y]=node; // node is situtated at cell (x,y)
                    node_location[node].node_x=y;
                    node_location[node].node_y=x;
                }
            }
        }
    }
   
    
    // divide the whole window in grids and initialize it by -1// unvisited
    public void make_grid()
    {
        for(int i=0;i<=(2*xmlparser.max_depth+4);i++)
        {
            for(int j=0;j<=(2*xmlparser.maxm_diameter+4);j++)
                grid[i][j]=-1;
        }
    }
   //end of making grid

    @Override
    public void mouseClicked(MouseEvent e) {
        
       int x= e.getX();
       int y=e.getY();
       j= x/unit_width;
       i= y/unit_height;
      
       //JOptionPane.showMessageDialog(this, "i "+i+" j ="+j);
       if(grid[i][j]!=-1 && edit_button==false && delete_button==false && delete_single==false && update_button==false && add_button==false && add_btw==false &&  getcontent_button==false)// for showing hovered item
       {
           node_number= grid[i][j];
           String tagdata=xmlparser.tag_obj[node_number].tag_data;
           if(tagdata==" ")
               tagdata="NULL";
           String tagatt=xmlparser.tag_obj[node_number].tag_attribute;
           if(tagatt==" ")
               tagatt="NULL";
           String str= "Tag Name : "+xmlparser.tag_obj[node_number].tag_name+"\n Tag Attribute: "+tagatt+"\n Tag Data: "+tagdata+"\n";
           JOptionPane.showMessageDialog(this, str);
       }
       if(grid[i][j]!=-1 && delete_button== true && edit_button==false)
       {
           node1= grid[i][j];
           try {
               deletechild_func();
           } catch (IOException ex) {
               Logger.getLogger(treePanel.class.getName()).log(Level.SEVERE, null, ex);
           }
           delete_button= false;
       }
       if(grid[i][j]>=0 && delete_single==true && !edit_button)
       {
           delete_single=false;
           node1= grid[i][j];
           try {
               single_delete();
           } catch (IOException ex) {
               Logger.getLogger(treePanel.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       if(grid[i][j]>=0 && update_button==true && edit_button==false && !delete_single)
       {
           node1= grid[i][j];
           try {
               update_node();
           } catch (IOException ex) {
               Logger.getLogger(treePanel.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       if(grid[i][j]>=0 && add_button==true)
       {
           
           node1= grid[i][j];
           try {
              add_new_node();
           } catch (IOException ex) {
               Logger.getLogger(treePanel.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       
       if(grid[i][j]!=-1 && first==0 && edit_button==true)
       {
           node1= grid[i][j];
           first=1;
          // JOptionPane.showMessageDialog(this, node1);
           deletechild();
       }
       else if(grid[i][j]!=-1 && first==1 && edit_button==true)
       {
           node2= grid[i][j];
          // JOptionPane.showMessageDialog(this, node2);
           first=0;
           edit_button=false;
           try {
               addchild();
           } catch (IOException ex) {
               Logger.getLogger(treePanel.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       if(grid[i][j]!=-1 && first_node==0 && add_btw==true)
       {
           node1= grid[i][j];
           first_node=1;
           
       }
       else if(grid[i][j]!=-1 && first_node==1 && add_btw==true)
       {
           node2= grid[i][j];
           first_node=0;
           AddBetweenNodes();
       }
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //edit node functions
    public void deletechild()
    {
        int parent= xmlparser.tag_obj[node1].parent_name;
        for(int idx=0;idx<xmlparser.tag_obj[parent].child.size();idx++)
        {
            if(xmlparser.tag_obj[parent].child.get(idx)==node1)
            {
                System.out.println("Index= hjeafsdnnnnnnnnnnnnnnnnnnnnnn"+xmlparser.tag_obj[parent].child.get(idx));
                xmlparser.tag_obj[parent].child.remove(idx);
                
            }
        }
       
    }
    
     public void addchild() throws IOException
    {
        int node_number=0;
        xmlparser.tag_obj[node2].child.add(node1);
        xmlparser.tag_obj[node1].parent_name=node2;
        print_parent_child_relation_edit();
        edit_button=false;
    }
     
     //delete subtree funtion
       public void  deletechild_func() throws IOException
      {
           int parent= xmlparser.tag_obj[node1].parent_name;
            for(int idx=0;idx<xmlparser.tag_obj[parent].child.size();idx++)
            {
                if(xmlparser.tag_obj[parent].child.get(idx)==node1)
                {
                    System.out.println("Index= hjeafsdnnnnnnnnnnnnnnnnnnnnnn"+xmlparser.tag_obj[parent].child.get(idx));
                    xmlparser.tag_obj[parent].child.remove(idx);
                      dfs(node1);
                    break;
                }
            }
        
            print_parent_child_relation_edit();
      }
       
       // delete node function
        public void single_delete() throws IOException 
        {
             int parent= xmlparser.tag_obj[node1].parent_name;
              for(int idx=0;idx<xmlparser.tag_obj[parent].child.size();idx++)
              {
                    if(xmlparser.tag_obj[parent].child.get(idx)==node1)
                    {
                            System.out.println("Index= hjeafsdnnnnnnnnnnnnnnnnnnnnnn"+xmlparser.tag_obj[parent].child.get(idx));
                            xmlparser.tag_obj[parent].child.remove(idx);
                               xmlparser.tag_obj[node1].presence= false;
                            break;
                  }
              }
              for(int idx=0; idx<xmlparser.tag_obj[node1].child.size();idx++)
              {
                  int child=xmlparser.tag_obj[node1].child.get(idx);
                  xmlparser.tag_obj[parent].child.add(child);
                  xmlparser.tag_obj[child].parent_name= parent;
              }
              print_parent_child_relation_edit();
        }
       
       // paint new tree
      public static void print_parent_child_relation_edit() throws IOException 
    {
        xmlparser.max_depth=-1;
        xmlparser.maxm_diameter=0;
         for(int idx=0;idx<=xmlparser.node_number;idx++)
         {
                 //  System.out.print("Node_number Edited: "+xmlparser.tag_obj[idx].node_index+"\tTag_name : "+xmlparser.tag_obj[idx].tag_name+"\tTag attribute:"+xmlparser.tag_obj[idx].tag_attribute+"\t Tag Data:"+xmlparser.tag_obj[idx].tag_data+"\tParent Name:"+xmlparser.tag_obj[idx].parent_name+" Level Number:"+xmlparser.tag_obj[idx].level_number );
                   if(xmlparser.tag_obj[idx].child.size()>0 && xmlparser.tag_obj[idx].presence==true)
                   {
                      // System.out.print("\tChild: ");
                       for(int v=0; v<xmlparser.tag_obj[idx].child.size();v++)
                       {
                           int ch=xmlparser.tag_obj[idx].child.get(v);
                           int pa= xmlparser.tag_obj[ch].parent_name;
                           xmlparser.tag_obj[ch].level_number= xmlparser.tag_obj[pa].level_number+1; 
                         //  System.out.print(" "+ch+"  ");
                       }
                     //  System.out.println();
                   }
                  
                  // else System.out.println("\tNo child");
         }
         for(int idx=0;idx<=xmlparser.node_number;idx++)
         {
             xmlparser.max_depth= Math.max(xmlparser.max_depth,xmlparser.tag_obj[idx].level_number);
         }
         for(int idx=0;idx<=xmlparser.max_depth+1;idx++)
         {
               xmlparser.level_diameter[idx]=0;
         }
        // System.out.println("node number = "+xmlparser.node_number);
         for(int idx=0;idx<=xmlparser.node_number;idx++)
         {
               int child_level= xmlparser.tag_obj[idx].level_number;
             if(xmlparser.tag_obj[idx].presence==true)
                  xmlparser.level_diameter[child_level]++;     
         }
         xmlparser.maxm_diameter=-1;
         for(int idx=0;idx<=xmlparser.max_depth;idx++)
         {
               xmlparser.maxm_diameter= Math.max(xmlparser.level_diameter[idx],xmlparser.maxm_diameter);
         }
        
        System.out.println("Maxm depth after ="+xmlparser.max_depth+" and maxm diameter after= "+xmlparser.maxm_diameter);
      //  newtree newtree_instance= new newtree();  
        gui_test gt = new gui_test();
    }
      
      // use dfs to deleted the whole subtree of the deleted node
      public void dfs(int node1)
      {
          boolean visited[]= new boolean[xmlparser.node_number+5];
          for(int node=0;node<=xmlparser.node_number;node++) visited[node]=false;
          Stack <Integer> dfs_stack= new Stack<>();
          xmlparser.tag_obj[node1].presence= false;
          // xmlparser.tag_obj[node1].reinit();
          dfs_stack.push(node1);
         // System.out.print("node to be deleted : "+node1);
          visited[node1]=true;
          while(!dfs_stack.empty())
          {
              int k= dfs_stack.peek();
              dfs_stack.pop();
              for(int node=0;node<xmlparser.tag_obj[k].child.size();node++)
              {
                  int child= xmlparser.tag_obj[k].child.get(node);
                  if(visited[child]==false )
                  {
                     // System.out.println("  "+child);
                      dfs_stack.push(child);
                      visited[child]=true;
                      xmlparser.tag_obj[child].presence=false;
                    //  xmlparser.tag_obj[child].reinit();
                  }
              }
          }
      }

    public void update_node() throws IOException {
        
        System.out.println("Update button called");
       update up =new update();
       //print_parent_child_relation_edit();
       
    }
    
    public void add_new_node() throws IOException {
       
        System.out.println("new  button called");
       update up =new update();
       //print_parent_child_relation_edit();
    }

    public void AddBetweenNodes() {
        System.out.println("Add between nodes");
        
        xmlparser.new_node= xmlparser.node_number+1;
         int parent= node1;
              for(int idx=0;idx<xmlparser.tag_obj[parent].child.size();idx++)
              {
                    if(xmlparser.tag_obj[parent].child.get(idx)==node2)
                    {
                            System.out.println("Index= hjeafsdnnnnnnnnnnnnnnnnnnnnnn"+xmlparser.tag_obj[parent].child.get(idx));
                            xmlparser.tag_obj[parent].child.remove(idx);
                               
                            break;
                  }
              }
          //xmlparser.tag_obj[parent].child.add(xmlparser.new_node);
          //xmlparser.tag_obj[xmlparser.new_node].parent_name= parent;
          
          xmlparser.tag_obj[node2].parent_name= xmlparser.new_node;
          xmlparser.tag_obj[xmlparser.new_node].child.add(node2);
           update up =new update();
        
        
    }

   
}
    
