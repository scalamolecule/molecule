package molecule.sql.core.query.casting.strategy

import molecule.sql.core.query.casting.NestOptRef

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