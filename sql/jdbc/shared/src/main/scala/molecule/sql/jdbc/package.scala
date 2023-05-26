package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.sql.jdbc.api._

package object jdbc {

  object async extends MoleculeImplicits_ with JdbcApiAsync
  object sync extends MoleculeImplicits_ with JdbcApiSync
  object zio extends MoleculeImplicits_ with JdbcApiZio
}