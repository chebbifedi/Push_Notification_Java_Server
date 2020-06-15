package notification;

import java.io.IOException;

import com.google.firebase.messaging.FirebaseMessagingException;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Routers {
	public void start() {
		Vertx vertx = Vertx.vertx();

		HttpServer httpServer = vertx.createHttpServer();

		Router router = Router.router(vertx);

		router.post("/notif").handler(BodyHandler.create()).handler(event -> {
			try {
				addOne(event);
			} catch (FirebaseMessagingException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		// Utiliser le port 8097 !
		httpServer.requestHandler(router).listen(8097);
	}

	public void addOne(RoutingContext routingContext) throws IOException, FirebaseMessagingException {
		final Test test = Json.decodeValue(routingContext.getBody(),Test.class);
		
		//Firebase Messaging
		FCMSingleton FCM = FCMSingleton.getInstance();
		FCM.SendMessage(test.getToken());
		
		routingContext
			.response()
			.setChunked(true)
			//.setStatusCode(201)
			//.putHeader("content-type", "application/json")
			.end("Success");
	}
}
