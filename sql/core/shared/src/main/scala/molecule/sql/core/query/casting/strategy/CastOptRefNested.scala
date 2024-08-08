package molecule.sql.core.query.casting.strategy

import molecule.sql.core.query.casting.OptRefNested

case class CastOptRefNested(private val tupleCasters0: List[CastTuple])
  extends TupleCasters(tupleCasters0) with CastStrategy {

  def getCast: (RS, ParamIndex) => Any = {
    (row: RS, _: ParamIndex) => {
      OptRefNested.row2nestedOptions(tupleCasters)(row)
    }
  }

  override def render(indent: Int): String = {
    val p = "  " * indent
    s"""${p}CastOptRefNested(List(
       |${tupleCasters.map(_.render(indent + 1)).mkString(s",\n")}
       |${p}))""".stripMargin
  }

  override def toString: String = render(0)
}