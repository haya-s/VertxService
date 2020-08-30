 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertx.service.htmlvalidation.entities;

/**
 *
 * @author user
 */
public class Response {
    int validTags;
    public Response(int v)
    {
        validTags = v;
    }
    public int getValidTags() {
        return validTags;
    }

    public void setValidTags(int validTags) {
        this.validTags = validTags;
    }
}
