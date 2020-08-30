/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertx.service.htmlvalidation.bl;

import java.util.Stack;
import vertx.service.htmlvalidation.entities.Response;

/**
 *
 * @author Haya
 */
public class ClosingValidationBL {
     public static Response ClosingCheck(String request)
        {
            Response r = new Response(CloseCheckByStack(request));
            return r;
        }
        
     private static int CloseCheckByStack(String request)
        {
            Stack<String> htmlOpenTags = new Stack<>();
            //split all tags by open tag
            String[] allElements = request.split("<");
            int counter = 0;
            for(String a :  allElements)
            {
                //find end tag
                int len = a.indexOf(">");
                 //if isnt contains closeTag - isnut tag
                if(len == -1)
                    continue;
                 // ignor text after tag
                String  tag = a.substring(0,len);
                
                if(tag.startsWith("/"))
                 {
                     //Searches for each tag that closes the corresponding one, 
                     //tags that have been opened between them and not closed between them - are not considered closed.
                     //If there is a closing tag without an opening tag, all tags left open before the closing tag will not count as closed tags.
                     boolean flag = false;
                     while(!htmlOpenTags.empty() && !flag)
                     {
                         if( htmlOpenTags.pop().equals(tag.substring(1)))
                         {
                             flag  = true;
                             counter++;
                         }
                     }
                 } 
                 else 
                     //if open tag - put stack
                    htmlOpenTags.push(tag);
        }        
        return counter;
    }
}
