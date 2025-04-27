package molecule.db.sql.core.query.casting.strategy

class TupleCasters(tupleCasters0: List[CastTuple])
  extends CastBase { self: CastStrategy =>

  private var _tupleCasters = tupleCasters0

  // Add cast to last CastTuple
  override def add(cast: Cast): Unit = {
    val last = _tupleCasters.last
    _tupleCasters = _tupleCasters.init :+ last.copy(casts0 = last.getCasts :+ cast)
  }

  // Replace last cast of last CastTuple
  override def replace(cast: Cast): Unit = {
    val last = _tupleCasters.last
    _tupleCasters = _tupleCasters.init :+ last.copy(casts0 = last.getCasts.init :+ cast)
  }

  def tupleCasters: List[CastTuple] = _tupleCasters
}
