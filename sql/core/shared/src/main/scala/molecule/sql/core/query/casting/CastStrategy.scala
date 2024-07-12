package molecule.sql.core.query.casting


import molecule.sql.core.javaSql.ResultSetInterface


sealed trait CastStrategy {
  type RS = ResultSetInterface
  type ParamIndex = Int
  type Cast = (RS, ParamIndex) => Any

  def add(cast: Cast): Unit
  def replace(cast: Cast): Unit

  def nest: CastNested
  def optNest: CastNested = ???
  def optRef: CastNested = ???
}


case class CastTuple(
  casts0: List[(ResultSetInterface, Int) => Any] = Nil,
  firstIndex: Int = 1
) extends CastStrategy {
  private var casts = casts0

  def tupleCaster: RS => Any = CastTpl_.tupleCaster(casts, firstIndex)
  def branchCaster: (RS, List[Any]) => Any = CastBranch_.branchCaster(casts, firstIndex)

  override def add(cast: Cast): Unit = {
    casts = casts :+ cast
  }
  override def replace(cast: Cast): Unit = {
    casts = casts.init :+ cast
  }

  override def nest: CastNested = {
    val levelId = 1
    CastNested(
      List(
        CastTuple(casts, levelId + firstIndex),
        CastTuple(Nil, levelId + lastIndex)
      ),
    )
  }

  def lastIndex: Int = firstIndex + casts.length
  def getCasts: List[Cast] = casts
}


case class CastNested(casters0: List[CastTuple]) extends CastStrategy {
  private var casters = casters0

  override def add(cast: Cast): Unit = {
    val last = casters.last
    casters = casters.init :+ last.copy(casts0 = last.getCasts :+ cast)
  }
  override def replace(cast: Cast): Unit = {
    val last = casters.last
    casters = casters.init :+ last.copy(casts0 = last.getCasts.init :+ cast)
  }

  override def nest: CastNested = {
    // Shift all first indexes since one more initial
    // nested level entity id is added for housekeeping
    val casters1 = casters.map(c => c.copy(firstIndex = c.firstIndex + 1))
    CastNested(casters1 :+ CastTuple(Nil, casters1.last.lastIndex))
  }

  def getCasters: List[CastTuple] = casters
}