package molecule.db.sql.core.query.casting.strategy

case class CastNested(private val tupleCasters0: List[CastTuple])
  extends TupleCasters(tupleCasters0) with CastStrategy {

  override def nest: CastNested = {
    // Shift all first indexes since one more initial
    // nested level entity id is added for housekeeping
    val casters1 = tupleCasters.map(c => c.copy(firstIndex = c.firstIndex + 1))
    CastNested(casters1 :+ CastTuple(Nil, casters1.last.lastIndex))
  }
}

