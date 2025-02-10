package molecule.sql.core.query.casting.strategy

import molecule.sql.core.javaSql.ResultSetInterface
import molecule.sql.core.query.casting._

case class CastTuple(
  casts0: List[(ResultSetInterface, Int) => Any] = Nil,
  firstIndex: Int = 1
) extends CastStrategy {

  private var casts = casts0
  //  override def getCasts: List[Cast] = casts
  def getCasts: List[Cast] = casts
  def lastIndex: Int = firstIndex + casts.length

  override def row2tpl: RS => Any =
    CastTpl_.cast(casts, firstIndex)

  def branchListCaster: (RS, List[Any]) => Any =
    new CastBranch_[List[Any]].cast(casts, firstIndex)

  def optRefBranchCaster: (RS, Option[Any]) => Option[Any] =
    CastOptRefBranch_.cast(casts, firstIndex)

  def optRefLeafCaster: RS => Option[Any] =
    CastOptRefLeaf_.cast(casts, firstIndex)

  override def add(cast: Cast): Unit = {
    casts = casts :+ cast
  }
  override def replace(cast: Cast): Unit = {
    casts = casts.init :+ cast
  }

  // First opt ref
  override def optRef(nested: Boolean): CastOptRefs = {
    CastOptRefs(
      CastTuple(casts, firstIndex), // initial casts before first opt ref
      List(
        CastOptRefNested(
          List(
            CastTuple(Nil, lastIndex) // optional casts to be added here
          )
        )
      )
    )
  }

  // First opt entity
  override def optEntity: CastTuple = {
    CastTuple(
      List(
        (row: RS, _: ParamIndex) => {
          // Collect optional entity casts in Option
          CastOptRefLeaf_.cast(casts, 1)(row)
        }
      )
    )
  }

  override def nest: CastNested = {
    val levelId = 1
    CastNested(
      List(
        CastTuple(casts, levelId + firstIndex),
        CastTuple(Nil, levelId + lastIndex) // nested casts to be added here
      ),
    )
  }

  override def render(indent: Int): String = {
    val p        = "  " * indent
    val castList = if (casts0.isEmpty) "" else
      casts0.mkString(s"\n$p    ", s",\n$p    ", s"\n$p  ")
    s"""${p}CastTuple(
       |${p}  List($castList),
       |${p}  $firstIndex
       |${p})""".stripMargin
  }

  override def toString: String = render(0)
}
