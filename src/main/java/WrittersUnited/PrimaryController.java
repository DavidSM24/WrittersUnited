package WrittersUnited;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PrimaryController {
    @FXML
    private Button login;
    @FXML
    private ImageView imagen;

    @FXML
    private void initialize() {

    }

    @FXML
    private void cambiar() throws IOException {
        App.loadScene(new Stage(), "secondary", "Administrar", true, true);
    }
}
