package xml;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class getContent {
    
   Scanner in = new Scanner(System.in);
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   public static String TagName,att;
   node node= new node();
    public getContent() throws IOException
    {
       
       
           
            int choice;
            while(true)
            {
                 System.out.println("\n\n\n\nEnter Your Choice");
                System.out.println("Enter 1 for getting Content of a Tag");
                System.out.println("Enter 2 for getting all child of a Tag");
                System.out.println("Enter 3 for getting all attributes of a Tag");
                System.out.println("Enter 4 for getting the value of attribute of a tag");
                System.out.println("Enter 5 for exit");
                 choice= in.nextInt();
                 if(choice==5)
                    {
                        System.out.println("Exit "); break;
                    }
                    switch (choice)
                    {
                        case 1:
                        {
                            System.out.println("Enter Tag Name to get its Content");
                            TagName= br.readLine();
                            node.getData(TagName);
                            break;
                        }
                        case 2:
                        {
                            System.out.println("Enter Tag Name to get its children");
                            TagName= br.readLine();
                            node.getChild(TagName);
                            break;
                        }
                        case 3:
                        {
                            System.out.println("Enter Tag Name to get its Attribute");
                            TagName= br.readLine();
                            node.getAttribute(TagName);
                            break;
                        }
                        case 4:
                        {
                            System.out.println("Enter Tag Name to get its value");
                           TagName= br.readLine();
                            System.out.println("Enter its attribute name to get its value");
                            att= br.readLine();;
                            node.getAttributeValue(TagName,att);
                            break;
                        }

                        default:
                        {
                            System.out.println("Enter a valid choice");
                            break;
                        }
                    }
            }       
    }
    
    
   
    
}
