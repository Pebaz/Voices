package colonist;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("Voices.fxml"));
		primaryStage.setTitle("Voices");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent windowEvent)
			{
				try
				{
					String peopleList = "";
					for (String people : Controller.peoples)
					{
						peopleList += people + "-";
					}

					String path = Controller.savePath.toString();
					if (!path.endsWith("/") || !path.endsWith("\\"))
					{
						if (path.indexOf("/") == -1)
						{
							path += '\\';
						}
						else
						{
							path += "/";
						}
					}

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy_HH-mm-ss");
					FileWriter fw = new FileWriter(path +
							peopleList + dtf.format(LocalDateTime.now()) + ".txt");
					fw.write(Controller.conversationHistory);
					fw.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});

		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);

		dialog.setScene(new Scene(
				FXMLLoader.load(getClass().getResource("path.fxml"))));
		dialog.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
