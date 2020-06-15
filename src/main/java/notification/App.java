package notification;

import java.io.IOException;

import com.google.firebase.messaging.FirebaseMessagingException;

public class App {

	public static void main(String[] args) throws IOException, FirebaseMessagingException {

		Routers routers = new Routers();
		routers.start();
	}
	
}
