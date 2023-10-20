/** **************************************************************
 * Autor............: Pedro Lucca Silva Martins
 * Matricula........: 202210183
 * Inicio...........: 16/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: Jantar dos filósofos
 * Funcao...........: Controlador da GUI
 *************************************************************** */
package pc_trabalho03_202210183;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.util.concurrent.Semaphore;

public class FXMLController implements Initializable {

  @FXML
  private Label label;
  @FXML
  private ImageView aristoteles;
  @FXML
  private ImageView platao;
  @FXML
  private ImageView kant;
  @FXML
  private ImageView socrates;
  @FXML
  private ImageView nietzsche;
  @FXML
  private ImageView garfo1;
  @FXML
  private ImageView garfo2;
  @FXML
  private ImageView garfo3;
  @FXML
  private ImageView garfo4;
  @FXML
  private ImageView garfo5;

  private Garfos[] garfos; // declara um array de objetos Garfos

  private Semaphore[] semaforos = new Semaphore[5]; // declaro os objetos do tipo semaforo

  @FXML
  private void handleButtonAction(ActionEvent event) {
    
    garfos = new Garfos[]{new Garfos(garfo1, semaforos[0]), new Garfos(garfo2, semaforos[1]), new Garfos(garfo3, semaforos[2]), new Garfos(garfo4, semaforos[3]), new Garfos(garfo5, semaforos[4])};

    Filosofos filosofo1 = new Filosofos(0, garfos, semaforos);
    Filosofos filosofo2 = new Filosofos(1, garfos, semaforos);
    Filosofos filosofo3 = new Filosofos(2, garfos, semaforos);
    Filosofos filosofo4 = new Filosofos(3, garfos, semaforos);
    Filosofos filosofo5 = new Filosofos(4, garfos, semaforos);

    filosofo1.start();
    filosofo2.start();
    filosofo3.start();
    filosofo4.start();
    filosofo5.start();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    for (int i = 0; i < 5; i++) {
      semaforos[i] = new Semaphore(1); // inicializando os semaforos com 1, para dizer que estao disponiveis
    }
  }
}
