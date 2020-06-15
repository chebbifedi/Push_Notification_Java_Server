package notification;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class FCMSingleton {
	
	private static FCMSingleton single_instance = null;
	
	private FirebaseMessaging Firebase_Messaging_instance = null;
	
	private FCMSingleton() throws IOException {
		InitializeFirebase();
		Firebase_Messaging_instance = FirebaseMessaging.getInstance();
	}
	
	public static FCMSingleton getInstance() throws IOException {
		if (single_instance == null)
			single_instance = new FCMSingleton();
		return single_instance;
	}

	private void InitializeFirebase() throws IOException {
		FileInputStream serviceAccount = new FileInputStream("./battlenotifications-firebase-adminsdk-6qu5z-595372e87d.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://battlenotifications.firebaseio.com").build();

		FirebaseApp.initializeApp(options);
		System.out.println("Firebase initialized");
	}
	
	public void SendMessage(String registrationToken) throws FirebaseMessagingException {

		// Notification content
		Notification notification = Notification.builder()
			.setBody("Notification body server")
			.setTitle("Notifification title")
			.build();

		Message message = Message.builder()
			.setNotification(notification)
		    .setToken(registrationToken)
		    .build();
		
		// Send a message to the device corresponding to the provided
		// registration token.
		String response = Firebase_Messaging_instance.send(message);
		// Response is a message ID string.
		System.out.println("Successfully sent message: " + response);
	}
}
