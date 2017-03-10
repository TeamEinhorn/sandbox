package de.teameinhorn.sandbox;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class MainVerticleTest {

	private static final String OUTCOME = "\"outcome\":\"UP\"";
	
	private Vertx vertx;

	@Before
	public void setUp(TestContext context) throws IOException {
		vertx = Vertx.vertx();
		vertx.deployVerticle(MainVerticle.class.getName(), new DeploymentOptions(), context.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void testStatus(TestContext context) {
		final Async async = context.async();

		vertx.createHttpClient().getNow(8080, "localhost", "/status", response -> {
			context.assertEquals(response.statusCode(), 200);
			response.bodyHandler(body -> {
				context.assertTrue(body.toString().contains(OUTCOME));
				async.complete();
			});
		});
	}

	@Test
	public void testGreeting(TestContext context) {
		final Async async = context.async();

		vertx.createHttpClient().getNow(8080, "localhost", "/hello/joachim", response -> {
			context.assertEquals(response.statusCode(), 200);
			response.bodyHandler(body -> {
				context.assertTrue(body.toString().contains("Hello joachim!"));
				async.complete();
			});
		});
	}

}
