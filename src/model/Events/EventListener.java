/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources de l'interface EventListener*/

package model.Events;

import model.Events.Events.Event;

/**
 * Cette interface décrit la méthode requise pour un EventListener
 */
public interface EventListener {
    /**
     *  Les implémentations de l'interface EventListener vont @Override cette méthode pour gérer le fait de recevoir un évènement de leur propre
     *  façon. Les évènements qui correspondent au type qu'elles ciblent devrait être traités avec une logique spécifique, les autres, ignorés.
     * @param e l'évènement reçu
     */
    public void HandleEvent(Event e);
}
