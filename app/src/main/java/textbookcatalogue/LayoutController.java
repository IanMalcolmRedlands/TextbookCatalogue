package textbookcatalogue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class LayoutController {
	@FXML public VBox rootBox;

	@FXML public Button addTbButton;
	@FXML public TextField skuAddField;
	@FXML public TextField titleAddField;
	@FXML public TextField priceAddField;
	@FXML public TextField quantityAddField;
	@FXML public Label addErrorLabel;

	@FXML public Button showTbButton;
	@FXML public TextField skuShowField;
	@FXML public Label showTbLabel;

	@FXML public Button delTbButton;
	@FXML public TextField skuDelField;
	@FXML public Label delTbLabel;

	@FXML public TableView<Textbook> table;


	@SuppressWarnings("unchecked")
	public void initTable(TextbookList tbList) {
		table.setItems(tbList);
		table.setEditable(true);

		TableColumn<Textbook, Integer> fkidColumn = new TableColumn<>("SKU");
		fkidColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
		fkidColumn.setSortable(false);
		TableColumn<Textbook, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		titleColumn.setSortable(false);
		TableColumn<Textbook, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		priceColumn.setSortable(false);
		TableColumn<Textbook, Integer> stockColumn = new TableColumn<>("Quantity");
		stockColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		stockColumn.setSortable(false);

		table.getColumns().addAll(fkidColumn, titleColumn, priceColumn, stockColumn);
	}
}
