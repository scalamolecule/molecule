package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.sql.jdbc.api.JdbcApiAsync

package object jdbc {

  object async extends MoleculeImplicits_ with JdbcApiAsync
//  object sync extends MoleculeImplicits_ with JdbcApiSync
//  object zio extends MoleculeImplicits_ with JdbcApiZio

  //  object typelevel extends DatomicMoleculeImplicits with DatomicApiAsync
  //  object laminar extends DatomicMoleculeImplicits with DatomicApiAsync
}