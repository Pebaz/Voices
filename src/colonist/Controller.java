package colonist;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.nio.file.Path;

public class Controller
{
	public static Path savePath;
	public VBox AppWindow;
	public TextArea voices;
	public ChoiceBox people;
	public TextField voiceEnter;
	public static ObservableList<String> peoples;
	public static String conversationHistory = "";

	@FXML
	public void initialize()
	{
		people.setItems(FXCollections.observableArrayList("Allovelle"));
		people.setValue(people.getItems().get(0));
		Platform.runLater(() -> voiceEnter.requestFocus());
	}

	public void addtext(ActionEvent actionEvent)
	{
		String output = "[" + people.getValue() + "] ";
		String entry = voiceEnter.getText();
		String[] parts = entry.split(":");
		if (parts.length == 1)
		{
			output += parts[0] + '\n';
		}
		else
		{
			if (!people.getItems().contains(parts[0]))
			{
				people.getItems().add(parts[0]);
			}
			output = '[' + parts[0] + "] " + parts[1].trim() + '\n';
		}

		voices.setText(voices.getText() + output);
		voiceEnter.clear();

		peoples = people.getItems();

		conversationHistory = voices.getText();
	}

	public void choosePerson(KeyEvent keyEvent)
	{
		if (keyEvent.getCode() == KeyCode.UP)
		{
			int index = people.getItems().indexOf(people.getValue()) - 1;
			if (index >= 0)
			{
				people.setValue(people.getItems().get(index));
			}
		}
		else if (keyEvent.getCode() == KeyCode.DOWN)
		{
			int index = people.getItems().indexOf(people.getValue()) + 1;
			if (index < people.getItems().size())
			{
				people.setValue(people.getItems().get(index));
			}
		}
	}
}
