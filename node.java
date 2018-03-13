
package xml;


public class node {
 
    
    public node()
    {
        getChild(getContent.TagName);
        getAttribute(getContent.TagName);
        getData(getContent.TagName);
        getAttributeValue(getContent.TagName,getContent.att);
    }
     public void getChild(String tag)
    {
        int flag=0,n;
        for(int i=0;i<xmlparser.node_number;i++)
        {
            String str="";
            if(xmlparser.tag_obj[i].tag_name.equals(tag))
            {
                    System.out.println("The Children of "+tag +" Node Number :"+ i+" are: ");
                   for(int node=0;node< xmlparser.tag_obj[i].child.size();node++)
                   {
                       n=xmlparser.tag_obj[i].child.get(node);
                       System.out.print("\t"+xmlparser.tag_obj[n].tag_name+" ");
                       str= str+xmlparser.tag_obj[n].tag_name+", ";
                   }
                   System.out.println();
                    flag=1;
            }
        }
        if(flag==0)
            System.out.println("Not a Valid Tag Name");       
    }
    
    public void getAttribute(String tag)
    {
        int flag=0;
        
        for(int i=0;i<xmlparser.node_number;i++)
        {
            if(xmlparser.tag_obj[i].tag_name.equals(tag))
            {
                 System.out.println("The Attribute of "+tag +" Node Number :"+ i+" are: ");
                System.out.println("\t"+xmlparser.tag_obj[i].tag_attribute);
                flag=1;
            }
        }
        if(flag==0)
            System.out.println("Not a Valid Tag Name");
    }
    
    public void getAttributeValue(String tag,String att)
    {
        int flag=0;
        
        for(int i=0;i<xmlparser.node_number;i++)
        {
            if(xmlparser.tag_obj[i].tag_name.equals(tag))
            {
                
                String full= xmlparser.tag_obj[i].tag_attribute;
                int len= full.length();
                int k=0;
                String temp="";
                while(k<len)
                {
                    temp="";
                    while(k <len && full.charAt(k)==' ')
                    {
                        k++;
                    }
                    while(k <len && full.charAt(k)!='=')
                    {
                        temp+= full.charAt(k);k++;
                    }
                   
                    String tem="";
                    k+=2;
                        while(k< len && full.charAt(k)!='"')
                        {
                            tem+= full.charAt(k); k++;
                        }
                        k++;
                       
                    if(temp.equals(att))
                    {
                        flag=1;
                        System.out.println("The tag number is "+ i +" and The Tag Attribute value is :"+ tem);
                    }
                 }
            }
        }
        if(flag==0)
            System.out.println("Not a Valid Tag Name or Attribute");
    }
    
    public void getData(String tag)
    {
        int flag=0;
        
        for(int i=0;i<xmlparser.node_number;i++)
        {
            if(xmlparser.tag_obj[i].tag_name.equals(tag))
            {
                 System.out.print("\tThe Content of "+tag +" and Node Number :"+ i+" is : ");
                System.out.println(xmlparser.tag_obj[i].tag_data);
                flag=1;
            }
        }
        if(flag==0)
            System.out.println("Not a Valid Tag Name");
       
    }
}
