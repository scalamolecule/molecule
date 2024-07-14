package molecule.sql.core.query.casting


import molecule.sql.core.javaSql.ResultSetInterface

trait CastBase {
  type RS = ResultSetInterface
  type ParamIndex = Int
  type Cast = (RS, ParamIndex) => Any
}

class TupleCasters(tupleCasters0: List[CastTuple]) extends CastBase { self: CastStrategy =>
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


sealed trait CastStrategy extends CastBase {
  def add(cast: Cast): Unit
  def replace(cast: Cast): Unit

  def row2tpl: RS => Any = ???

  def nest: CastNested
  def optRef: CastOptRefNested
}


case class CastTuple(
  casts0: List[(ResultSetInterface, Int) => Any] = Nil,
  firstIndex: Int = 1
) extends CastStrategy {
  private var casts = casts0
  def getCasts: List[Cast] = casts
  def lastIndex: Int = firstIndex + casts.length

  override def row2tpl: RS => Any =
    CastTpl_.cast(casts, firstIndex)

  def branchListCaster: (RS, List[Any]) => Any =
    new CastBranch_[List[Any]].cast(casts, firstIndex)

  def branchOptionCaster: (RS, Option[Any]) => Any =
    new CastBranch_[Option[Any]].cast(casts, firstIndex)

  def nestedOptRefCaster: (RS, Option[Any]) => Option[Any] =
    CastOptRefBranch_.cast(casts, firstIndex)

  def optTupleCaster: RS => Option[Any] =
    CastOptTpl_.cast(casts, firstIndex)

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

  override def optRef: CastOptRefNested = {
    CastOptRefNested(
      List(
        CastTuple(casts, firstIndex),
        CastTuple(Nil, lastIndex)
      ),
    )
  }
}


case class CastNested(private val tupleCasters0: List[CastTuple])
  extends TupleCasters(tupleCasters0) with CastStrategy {

  override def nest: CastNested = {
    // Shift all first indexes since one more initial
    // nested level entity id is added for housekeeping
    val casters1 = tupleCasters.map(c => c.copy(firstIndex = c.firstIndex + 1))
    CastNested(casters1 :+ CastTuple(Nil, casters1.last.lastIndex))
  }
  override def optRef: CastOptRefNested = ???
}

case class CastOptRefNested(private val tupleCasters0: List[CastTuple])
  extends TupleCasters(tupleCasters0) with CastStrategy {

  override def row2tpl: RS => Any =
    NestOptRef.row2nestedOptions(tupleCasters)

  override def nest: CastNested = ???

  override def optRef: CastOptRefNested = {
    CastOptRefNested(
      tupleCasters :+ CastTuple(Nil, tupleCasters.last.lastIndex)
    )
  }
}