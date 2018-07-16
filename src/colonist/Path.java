package colonist;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Path
{
	@FXML TextField pathText;

	public void initialize()
	{
		Platform.runLater(() ->
				pathText.setText(System.getProperty("user.home")));
	}

	public void okClicked(ActionEvent actionEvent)
	{
		Controller.savePath = Paths.get(pathText.getText());
		Stage s = (Stage)(pathText.getScene().getWindow());
		s.close();
	}
}
