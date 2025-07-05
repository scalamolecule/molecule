package boilerplate.db.datalog.core.query.casting

import boilerplate.db.datalog.DbDatalogBase

object _CastRow2AnyTpl extends DbDatalogBase("CastRow2AnyTpl", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casts, attrIndex)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datalog.core.query.casting
       |
       |import molecule.db.datalog.core.query.DatomicQueryBase
       |
       |
       |trait $fileName_ { self: DatomicQueryBase =>
       |
       |  final def castRow2AnyTpl(
       |    casts: List[AnyRef => AnyRef],
       |    attrIndex: AttrIndex
       |  ): Row => Any = {
       |    casts.length match {
       |      $resolveX
       |      case n  =>
       |        (row: Row) =>
       |          var rowIndex   = n - 1
       |          var castIndex  = attrIndex + n - 1
       |          var tpl: Tuple = EmptyTuple
       |          while (rowIndex >= 0) {
       |            tpl = casts(rowIndex)(row.get(castIndex)) *: tpl
       |            rowIndex -= 1
       |            castIndex -= 1
       |          }
       |          tpl
       |    }
       |  }
       |
       |  final private def cast1(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
       |    val cast = casts.head
       |    (row: Row) => cast(row.get(attrIndex))
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val indexes  = (1 to i).map("i" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row.get(i$j))" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(casts: List[AnyRef => AnyRef], attrIndex: AttrIndex): Row => Any = {
         |    val List($casters) = casts
         |    val List($indexes) = (attrIndex until attrIndex + $i).toList
         |    (row: Row) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
