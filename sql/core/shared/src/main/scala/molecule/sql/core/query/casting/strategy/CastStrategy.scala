package molecule.sql.core.query.casting.strategy

trait CastStrategy extends CastBase {
  def add(cast: Cast): Unit
  def replace(cast: Cast): Unit

  def row2tpl: RS => Any = ???

  def optRef(nested: Boolean): CastOptRefs = ???

  def optEntity: CastTuple = ???

  def nest: CastNested = ???

  def render(indent: Int): String = ???
}


