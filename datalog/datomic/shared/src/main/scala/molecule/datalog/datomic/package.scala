package molecule.datalog

import molecule.core.MoleculeImplicits_
import molecule.datalog.datomic.api._

package object datomic {

  object async extends MoleculeImplicits_ with DatomicApiAsync
  object sync extends MoleculeImplicits_ with DatomicApiSync
  object zio extends MoleculeImplicits_ with DatomicApiZio

  //  object typelevel extends DatomicMoleculeImplicits with DatomicApiAsync
  //  object laminar extends DatomicMoleculeImplicits with DatomicApiAsync
}