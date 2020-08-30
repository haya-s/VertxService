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
public class ErrResponse{
      String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
     public ErrResponse(String er)
    {
        this.err = er;
    }
    
}
