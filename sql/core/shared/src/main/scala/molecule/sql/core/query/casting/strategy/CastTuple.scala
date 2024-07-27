package molecule.sql.core.query.casting.strategy

import molecule.sql.core.javaSql.ResultSetInterface
import molecule.sql.core.query.casting.{CastBranch_, CastOptRefBranch_, CastOptTpl_, CastTpl_}

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
