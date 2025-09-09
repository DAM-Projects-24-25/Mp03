package samso.duarte.pp_samso_duarte.metodes;

/**
 *
 *      Ens obligarà a reescriure el mètode equals si utilitzem alguns dels mètodes d'aquesta LlibreriaFitxers, per exemple:
 *
 *          <p><i>
 *              {@code
 *          - cercaObjecteFitxer()
 *          }
 *          </i></p>
 *
 *          <p><i>
 *                 {@code
 *          - eliminaRegistreFitxerObjecte()
 *          }
 *
 *         </i></p>
 *      (Mira en el See Also, baix...)
 *<p>
 *.
 *</p>
 *
 *
 *
 *      Per cert...
 *      NO PUC SUBSTITUIR ABSTRACTA PER INTERFÍCIE JA QUE NO 'OBLIGA' A IMPLEMENTAR EL MÈTODE.
 *      AMB ABSTRACTA I MÈTODE ABSTRACTE SI!! (SHADOWING)
 *
 *
 *
 * <pre>
 *
 *
 * {@code
 *      1) cridar al mètode:
 *      f.eliminaRegistreFitxerObjecte(fitxer, objectePersona);
 *
 *
 *      2) Fer que la classe de l'objecte objectePersona implemente de la classe ComparadorObjectes()
 *
 *
 *      EXEMPLE D'ÚS:
 *
 *
 *
 *
 *       1)   f.eliminaRegistreFitxerObjecte(fitxer, p);
 *
 *       2)   public class Persona extends ComparadorObjectes { ... }
 *
 *

 *   }
 *</pre>
 *
 *@see Fitxers#cercaObjecteFitxer(String, ComparadorObjectes)
 *
 *@see Fitxers#eliminaRegistreFitxerObjecte(String, ComparadorObjectes)
 */
public abstract class ComparadorObjectes {


    // MÈTODE QUE ESTARÀ OBLIGAT A CODIFICAR LA CLASSE QUE IMPLEMENTI: ComparadorObjectes
    // mira la descripció de la classe (javadoc, o comentari superior d'aquesta classe)
    // ahi explique la manera d'utilitzar-la
    public abstract boolean equals(Object obj);





}

