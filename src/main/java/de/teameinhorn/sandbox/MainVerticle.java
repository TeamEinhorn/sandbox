package de.teameinhorn.sandbox;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		HealthCheckHandler hch = HealthCheckHandler.create(vertx).register("main", future -> {
			future.complete(Status.OK());
		});

		Router router = Router.router(vertx);

		router.get("/hello/:name")//
				.handler(context -> {
					String name = context.request().getParam("name");
					context.response().end("Hello " + name + "!");
				});
		
		router.get("/status").handler(hch);

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

}