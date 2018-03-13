/*
 * This file will dump new xml generated in the xml file
 */
package xml;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Stack;
public class dump_xml 
{
    
     Writer writer = null;
    public dump_xml() throws IOException
    {
          try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream("C:\\Users\\cyber\\Desktop\\xmldump.xml"), "utf-8"));
                    
                }
                catch (IOException ex) {
                            System.out.println("Unable to write in the file");
                }
          writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+System.getProperty( "line.separator" ));
        print_dfs();
        close_file();
    }
    
    public  void print_dfs() throws IOException 
    {
       int start_node=0;
       dfs(start_node);
    }

    public void close_file() {
                try {writer.close();} catch (Exception ex) {}
             }

    public void dfs(int node) throws IOException 
    {    
         writer.write("<"+xmlparser.tag_obj[node].tag_name);
         if(!xmlparser.tag_obj[node].tag_attribute.equals("NULL"))
             writer.write(" "+xmlparser.tag_obj[node].tag_attribute);
         writer.write(">");
         if(xmlparser.tag_obj[node].child.size()!=0)
            writer.write(System.getProperty( "line.separator" ));
         
            if(!xmlparser.tag_obj[node].tag_data.equals("NULL"))
            {
                int l= xmlparser.tag_obj[node].tag_data.length();
                int i=l-1;
                while(i>=0 && xmlparser.tag_obj[node].tag_data.charAt(i)==' ')
                    i--;
                String str="";
                if(i>=0)
                { str= xmlparser.tag_obj[node].tag_data.substring(0,i+1);
                writer.write(str);}
            }
            for(int v= 0; v<=xmlparser.tag_obj[node].child.size()-1;v++)
            {
                dfs(xmlparser.tag_obj[node].child.get(v));
            }
            writer.write("</"+xmlparser.tag_obj[node].tag_name+">"+System.getProperty( "line.separator" ));       
    }
    
}
