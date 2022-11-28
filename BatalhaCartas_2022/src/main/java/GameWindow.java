import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import javafx.event.*;

public class GameWindow extends Application implements Observer {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Game.getInstance().addObserver(this);

		primaryStage.setTitle("Batalha de Cartas");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		DeckView deckJ1 = new DeckView(1);
		ScrollPane sd1 = new ScrollPane();
		sd1.setPrefSize(1000, 340);
		sd1.setContent(deckJ1);
		grid.add(sd1, 0, 0);

		PlacarView placar = new PlacarView();
		grid.add(placar, 0, 1);

		Button actionButton1 = new Button("Magic player 1");
		grid.add(actionButton1, 1, 2);
		actionButton1.setOnAction(e -> Game.getInstance().CastMagic1());

		Button actionButton2 = new Button("Magic player 2");
		grid.add(actionButton2, 1, 0);
		actionButton2.setOnAction(e -> Game.getInstance().CastMagic2());

		Button butClean = new Button("Clean");
		grid.add(butClean, 1, 1);
		butClean.setOnAction(e -> Game.getInstance().removeSelected());
		/*
		 * butClean.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override
		 * public void handle(ActionEvent e) {
		 * Game.getInstance().removeSelected();
		 * }
		 * });
		 */
		DeckView deckJ2 = new DeckView(2);
		ScrollPane sd2 = new ScrollPane();
		sd2.setPrefSize(1000, 340);
		sd2.setContent(deckJ2);
		grid.add(sd2, 0, 2);

		Scene scene = new Scene(grid);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		Alert alert;

		if (arg == null) {
			return;
		}
		GameEvent eg = (GameEvent) arg;
		if (eg.getTarget() == GameEvent.Target.GWIN) {
			switch (eg.getAction()) {
				case INVPLAY:
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("Atenção !!");
					alert.setHeaderText("Jogada inválida!!");
					alert.setContentText("Era a vez do jogador " + eg.getArg());
					alert.showAndWait();
					break;
				case MUSTCLEAN:
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("Atenção !!");
					alert.setHeaderText(null);
					alert.setContentText("Utilize o botao \"Clean\"");
					alert.showAndWait();
					break;
				case ENDGAME:
					String text = "Fim de Jogo !!\n";
					if (Game.getInstance().getPtsJ1() > Game.getInstance().getPtsJ2()) {
						text += "O jogador 1 ganhou !! :-)";
					} else if (Game.getInstance().getPtsJ2() > Game.getInstance().getPtsJ1()) {
						text += "O jogador 2 ganhou !! :-)";
					} else {
						text += "Deu empate !! :-)";
					}
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("Parabens !!");
					alert.setHeaderText(null);
					alert.setContentText(text);
					alert.showAndWait();
					break;
				case TURN:
					alert = new Alert(AlertType.INFORMATION);
					String turnText = "agora é o turno do " + eg.getArg();
					alert.setTitle("De quem é o turno?");
					alert.setHeaderText(null);
					alert.setContentText(turnText);
					alert.showAndWait();
					break;

				case UNABLEMAGIC:
					alert = new Alert(AlertType.INFORMATION);
					String magicText = "agora é o turno do " + eg.getArg();
					alert.setTitle("De quem é o turno?");
					alert.setHeaderText(null);
					alert.setContentText(magicText);
					alert.showAndWait();
				case REMOVESEL:
					// Esse evento não vem para cá
			}
		}
	}
}