package main;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import myLogging.MyLogger;

public class MyApplication extends Application {

	public static void main(final String[] args) {
		Application.launch(args);
	}

	private final MyLogger myLogger = new MyLogger(Application.class);

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setScene(getScene());
		primaryStage.show();
	}

	private Scene getScene() {

		final Pane rootNode = new VBox();

		rootNode.getChildren().add(getWebView());

		final Scene scene = new Scene(rootNode);

		return scene;

	}

	private Node getWebView() {

		final Pane rootNode = new VBox();

		{

			final WebView webView = new WebView();

			rootNode.getChildren().add(webView);

			final WebEngine webEngine = webView.getEngine();

			webEngine.setJavaScriptEnabled(true);

			try {

				Path path = Path.of(".");

				path = Path.of("K:\\Syncurl\\Bookmark.htm");

//				path = Path.of("K:\\JavaAPPs\\MyWebViewFail\\MySetting.java.18068581270509216215.htm");

//				path = Path.of("K:\\JavaAPPs\\MyWebViewFail\\MySetting.java.18068581270509216215.htm");

				myLogger.log(Level.INFO, String.format("%s exists %s ", path, Files.exists(path)));

//				final String readString = Files.readString(path);

				final URL url = path.toUri().toURL();

//				webEngine.loadContent(readString);
				webEngine.load(url.toString());
				myLogger.log(Level.INFO, String.format("Location %s ", webEngine.getLocation()));

				Platform.runLater(() -> {

				});

			} catch (final Exception exception) {
				exception.printStackTrace();
			}

		}

		return rootNode;

	}

}
