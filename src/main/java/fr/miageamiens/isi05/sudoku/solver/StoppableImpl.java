/**
 * 
 */
package fr.miageamiens.isi05.sudoku.solver;

/**
 * Implémentation de l'interface Stoppable.
 */
public class StoppableImpl implements Stoppable {

  private volatile boolean stopped = false;

  /**
   * Arrête le traitement en cours.
   */
  @Override
  public void stop() {
    this.stopped = true;
  }

  /**
   * Indique si le traitement a été arrêté.
   * 
   * @return true si le traitement a été arrêté, false sinon.
   */
  public boolean isStopped() {
    return this.stopped;
  }

}
