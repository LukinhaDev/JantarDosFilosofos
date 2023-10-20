/** **************************************************************
 * Autor............: Pedro Lucca Silva Martins
 * Matricula........: 202210183
 * Inicio...........: 16/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: garfos
 * Funcao...........: classe dos garfos
 *************************************************************** */
package pc_trabalho03_202210183;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Garfos extends Thread {

  private ImageView garfo;
  private Semaphore semaforo;

  public Garfos(ImageView garfo, Semaphore semaforo) {
    this.garfo = garfo;
    this.semaforo = semaforo;
  }

  public void run() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Logger.getLogger(Garfos.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void mover() {
    //Platform.runLater(() -> garfo.setLayoutY(garfo.getLayoutY() + 40));
    garfo.setVisible(true);
  }

  public void desmover() {
    //Platform.runLater(() -> garfo.setLayoutY(garfo.getLayoutY() - 40));
    garfo.setVisible(false);
  }
}
// setVisible