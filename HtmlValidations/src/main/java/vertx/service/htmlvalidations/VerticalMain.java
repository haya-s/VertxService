/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertx.service.htmlvalidations;

import io.vertx.core.Vertx;

/**
 *
 * @author user
 */
public class VerticalMain {
  
       public static void main(String[] args) throws InterruptedException {
            Vertx vertx = Vertx.vertx();
            //run service
            vertx.deployVerticle(new HtmlClosingService());
        }
}
