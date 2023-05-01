// GENERATED CODE ********************************
package molecule

import molecule.core.MoleculeImplicits_
import molecule.datomic.api.{DatomicApiAsync, DatomicApiSync, DatomicApiZio}
import scala.language.implicitConversions

package object datomic {

  object async extends MoleculeImplicits_ with DatomicApiAsync
  object sync extends MoleculeImplicits_ with DatomicApiSync
  object zio extends MoleculeImplicits_ with DatomicApiZio

  //  object typelevel extends DatomicMoleculeImplicits with DatomicApiAsync
  //  object laminar extends DatomicMoleculeImplicits with DatomicApiAsync
}