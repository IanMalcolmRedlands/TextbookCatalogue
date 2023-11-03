package textbookcatalogue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class App  extends Application {
	private String layout_path = "/layout.fxml";
	LayoutController controller;
	TextbookCatalogue catalogue;


	@Override
	public void start(Stage primaryStage) throws Exception {
		catalogue = new TextbookCatalogue();

		FXMLLoader loader = new FXMLLoader(getClass().getResource(layout_path));
		loader.load();

		controller = loader.getController();
		controller.initTable(catalogue.getList());

		setAddTextbookListener();
		setShowTextbookListener();
		setDelTextbookListener();

		Scene scene = new Scene(controller.rootBox);
        primaryStage.setTitle("Textbook Catalogue");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setAddTextbookListener() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e) {
				Textbook textbook;

    			String sku = controller.skuAddField.getText();
				String title = controller.titleAddField.getText();
				String price = controller.priceAddField.getText();
				String quantity = controller.quantityAddField.getText();

				try {
					textbook = new Textbook(sku, title, price, quantity);
					catalogue.add(textbook);
					controller.addErrorLabel.setText("");
				}
				catch (IllegalArgumentException error) {
					controller.addErrorLabel.setText("  Error: "+error.getMessage());
				}

				controller.table.refresh();
    		}
    	};
    	
    	controller.addTbButton.setOnAction(listener);
	}

	private void setShowTextbookListener() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e) {
				Textbook textbook;
				int sku;

				controller.showTbLabel.setText("");

				try {
					sku = Integer.parseInt(controller.skuShowField.getText());
				}
				catch (IllegalArgumentException error) {
					controller.showTbLabel.setText("Error: Non-valid integer");
					return;
				}

				try {
					textbook = catalogue.get(sku);
				}
				catch (IllegalArgumentException error) {
					controller.showTbLabel.setText("Error: "+error.getMessage());
					return;
				}

				controller.showTbLabel.setText(textbook.toString());

				controller.table.refresh();
    		}
    	};
    	
    	controller.showTbButton.setOnAction(listener);
	}

	private void setDelTextbookListener() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e) {
				int sku;

				controller.delTbLabel.setText("");

				try {
					sku = Integer.parseInt(controller.skuDelField.getText());
				}
				catch (IllegalArgumentException error) {
					controller.delTbLabel.setText("   Error: Non-valid integer");
					return;
				}

				try {
					catalogue.remove(sku);
				}
				catch (IllegalArgumentException error) {
					controller.delTbLabel.setText("   Error: "+error.getMessage());
				}

				controller.table.refresh();
    		}
    	};
    	
    	controller.delTbButton.setOnAction(listener);
	}
}
