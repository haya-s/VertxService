/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertx.service.htmlvalidation.app
        ;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.BodyHandler;
import vertx.service.htmlvalidation.bl.ClosingValidationBL;
import vertx.service.htmlvalidation.entities.ErrResponse;
import vertx.service.htmlvalidation.entities.Response;
/**
 *
 * @author Haya
 */
public class HtmlClosingService extends AbstractVerticle {
     //@Override
public void start(Future<Void> fut) throws Exception {

    Router router = Router.router(vertx);
    // add a handler which sets the request body on the RoutingContext.
    router.route().handler(BodyHandler.create());
// expose a POST method endpoint on the URI: /analyze
    router.post("/analyze").handler(this::analyze);

    // Create Http server and pass the 'accept' method to the request handler
    vertx.createHttpServer().requestHandler(router::accept).
            listen(config().getInteger("http.port", 9000),
                    result -> {
                        if (result.succeeded()) {
                            System.out.println("Http server completed..");
                            fut.complete();
                        } else {
                            fut.fail(result.cause());
                            System.out.println("Http server failed..");
                        }
                    }
            );
}


// handle anything POSTed to /analyze
public void analyze(RoutingContext context) {
    // the POSTed content is available in context.getBodyAsJson()
    try
    {

        JsonObject body = context.getBodyAsJson();
        if(!body.containsKey("html"))
        {
            context.response().setStatusCode(500)
                                .putHeader("content-type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(new ErrResponse("input doesn't contains 'html'")));
        }
        else
        {
            // a JsonObject wraps a map and it exposes type-aware getters
            String postedText = body.getString("html");

            Response res = ClosingValidationBL.ClosingCheck(postedText);
            context.response().setStatusCode(201)
                                .putHeader("content-type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(res));

        }
    }
    catch(Exception ex)
            {
             context.response().setStatusCode(500)
                                .putHeader("content-type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(new ErrResponse(ex.getMessage())));
            }
            
}
}
