package molecule.sql.core.query.casting.strategy

trait CastStrategy extends CastBase {
  def add(cast: Cast): Unit
  def replace(cast: Cast): Unit

  def rs2row: RS => Any = ???

  def optRef(nested: Boolean): CastOptRefs = ???

  def optEntity: CastOptEntity = ???

  def nest: CastNested = ???

  def render(indent: Int): String = ???
}


