// GENERATED CODE ********************************
package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.sql.core.api._
import scala.language.implicitConversions

package object core {

  object async extends MoleculeImplicits_ with SqlApiAsync
//  object sync extends MoleculeImplicits_ with SqlApiSync
//  object zio extends MoleculeImplicits_ with SqlApiZio

  //  object typelevel extends DatomicMoleculeImplicits with DatomicApiAsync
  //  object laminar extends DatomicMoleculeImplicits with DatomicApiAsync
}