package dad;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {
	
	private Label pesoLabel, alturaLabel, imcLabel, kgLabel, cmLabel, estadoLabel;
	private TextField pesoTF, alturaTF;
	
	private DoubleProperty pesoProp = new SimpleDoubleProperty();
	private DoubleProperty alturaProp = new SimpleDoubleProperty();
	private DoubleProperty imcProp = new SimpleDoubleProperty();
	private StringProperty estadoProp = new SimpleStringProperty();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Labels
		pesoLabel = new Label ("Peso:");
		kgLabel = new Label ("kg");
		
		
		alturaLabel = new Label ("Altura:");
		cmLabel = new Label ("cm");
		
		imcLabel = new Label ("");
		estadoLabel = new Label ("");
		
		//TextFields
		pesoTF = new TextField();
		pesoTF.setPrefWidth(60);
		
		alturaTF = new TextField();
		alturaTF.setPrefWidth(60);
		
		//Panels
		HBox pesoPanel = new HBox();
		pesoPanel.setSpacing(5);
		pesoPanel.setFillHeight(false);
		pesoPanel.getChildren().addAll(pesoLabel, pesoTF, kgLabel);
		
		HBox alturaPanel = new HBox();
		alturaPanel.setSpacing(5);
		pesoPanel.setFillHeight(false);
		alturaPanel.getChildren().addAll(alturaLabel, alturaTF, cmLabel);
		
		VBox datosPanel = new VBox();
		datosPanel.setAlignment(Pos.CENTER);
		datosPanel.setFillWidth(false);
		datosPanel.setSpacing(5);
		datosPanel.getChildren().addAll(pesoPanel, alturaPanel, imcLabel, estadoLabel);
		
		Scene scene = new Scene(datosPanel, 320, 200);
		 
		primaryStage.setTitle("Calculadora IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Bindings
		pesoTF.textProperty().bindBidirectional(pesoProp, new NumberStringConverter());
		alturaTF.textProperty().bindBidirectional(alturaProp, new NumberStringConverter());
		
		imcProp.bind(pesoProp.divide(alturaProp.divide(100).multiply(alturaProp.divide(100))));
		imcLabel.textProperty().bindBidirectional(imcProp, new NumberStringConverter());
		estadoLabel.textProperty().bindBidirectional(estadoProp);
		
		
		imcProp.addListener((o, ov, nv) -> {
			String estadoIMC = "";
			double newValue = nv.doubleValue();
			if (newValue < 18.5) {
				estadoIMC = "Infrapeso";
			} else if (newValue >= 18.5 && newValue <25) {
				estadoIMC = "Peso normal";
			} else if (newValue >=25 && newValue <30) {
				estadoIMC = "Sobrepeso";
			} else {
				estadoIMC = "Obesidad";
			}
			estadoProp.set(estadoIMC);
		});
		
	}
	
	public static void main (String[] args) {
		 launch(args);

	}

}
