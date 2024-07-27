package molecule.sql.core.query.casting.strategy

import molecule.sql.core.javaSql.ResultSetInterface

trait CastStrategy extends CastBase {
  def add(cast: Cast): Unit
  def replace(cast: Cast): Unit

  def row2tpl: RS => Any = ???

  def nest: CastNested
  def optRef: CastOptRefNested
}


