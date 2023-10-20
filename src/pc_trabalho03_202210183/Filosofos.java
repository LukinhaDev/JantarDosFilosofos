/** **************************************************************
 * Autor............: Pedro Lucca Silva Martins
 * Matricula........: 202210183
 * Inicio...........: 16/10/2023
 * Ultima alteracao.: 29/10/2023
 * Nome.............: filosofos
 * Funcao...........: Classe dos filosofos
 *************************************************************** */
package pc_trabalho03_202210183;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

public class Filosofos extends Thread {

  private int filosofoIndex;
  private Garfos[] garfos;
  private Semaphore[] semaforos;
  private int soprasabe = 1;

  public Filosofos(int filosofoIndex, Garfos[] garfos, Semaphore[] semaforos) {
    this.filosofoIndex = filosofoIndex;
    this.garfos = garfos;
    this.semaforos = semaforos;
  }

  public void run() {
    while (true) {
      pensar(); // O filosofo esta pensando
      tentarComer(); // O filosofo tenta pegar garfos e comer
    }
  }

  private void pensar() {
    System.out.println("filosofo " + filosofoIndex + " pensando" + "\n--------------------------" + soprasabe);
    soprasabe++;
    garfos[filosofoIndex].desmover();
    garfos[(filosofoIndex + 1) % 5].desmover();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Logger.getLogger(Filosofos.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void tentarComer() {
    try {
      if (semaforos[filosofoIndex].tryAcquire(1, 1000, TimeUnit.MILLISECONDS)) { // ifs para caso ele nao consiga pegar os 2 garfos
        // ele coloca de novo na mesa
        if (semaforos[(filosofoIndex + 1) % 5].tryAcquire(1, 1000, TimeUnit.MILLISECONDS)) {
          comer();
          semaforos[filosofoIndex].release();
          semaforos[(filosofoIndex + 1) % 5].release();
        } else {
          semaforos[filosofoIndex].release();
          pensar(); // Se não conseguir pegar o garfo da direita, pense e tente novamente
        }
      } else {
        pensar(); // Se não conseguir pegar o garfo da esquerda, pense e tente novamente
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void comer() {
    System.out.println("filosofo " + filosofoIndex + " comendo" + "\n--------------------------" + soprasabe);
    soprasabe++;
    garfos[filosofoIndex].mover();
    garfos[(filosofoIndex + 1) % 5].mover();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Logger.getLogger(Filosofos.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
