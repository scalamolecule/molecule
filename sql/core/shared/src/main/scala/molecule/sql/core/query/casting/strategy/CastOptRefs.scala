package molecule.sql.core.query.casting.strategy

import molecule.sql.core.query.casting.CastTpl_

case class CastOptRefs(
  initialTuple: CastTuple,
  optRefs: List[CastOptRefNested]
) extends CastStrategy {

  override def row2tpl: RS => Any = {
    CastTpl_.cast(
      initialTuple.casts0 ++ optRefs.map(_.getCast),
      1
    )
  }

  override def add(cast: Cast): Unit = optRefs.last.add(cast)
  override def replace(cast: Cast): Unit = optRefs.last.replace(cast)

  override def optRef(nested: Boolean): CastOptRefs = {
    if (nested) {
      // Add CastTuple to last CastOptRefNested
      val curOptRef           = optRefs.last
      val nestedOptTuple      = CastTuple(Nil, curOptRef.tupleCasters.last.lastIndex)
      val updatedNestedOptRef = curOptRef.copy(
        tupleCasters0 = curOptRef.tupleCasters :+ nestedOptTuple
      )
      CastOptRefs(initialTuple, optRefs.init :+ updatedNestedOptRef)

    } else {
      // Add CastOptRefNested with new CastTuple
      val lastIndex      = optRefs.last.tupleCasters.last.lastIndex
      val adjacentOptRef = CastOptRefNested(
        List(
          CastTuple(Nil, lastIndex) // optional casts to be added here
        )
      )
      CastOptRefs(initialTuple, optRefs :+ adjacentOptRef)
    }
  }


  override def render(indent: Int): String = {
    s"""CastOptRefs(
       |${initialTuple.render(indent + 1)},
       |${optRefs.map(_.render(indent + 1)).mkString(s",\n")}
       |)""".stripMargin
  }

  override def toString: String = render(0)
}