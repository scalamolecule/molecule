package boilerplate.db.datalog.core.query.casting

import boilerplate.db.datalog.DbDatalogBase

object _CastNestedBranch extends DbDatalogBase("CastNestedBranch", "/query/casting") {

  val content = {
    val resolveX       = (1 to 21).map(i => s"case ${caseN(i)} => cast$i[T](casts, firstAttrIndex)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datalog.core.query.casting
       |
       |import molecule.db.datalog.core.query.DatomicQueryBase
       |
       |
       |trait $fileName_ { self: DatomicQueryBase =>
       |
       |  final protected def castBranch[T](
       |    casts: List[AnyRef => AnyRef],
       |    firstAttrIndex: AttrIndex,
       |  ): (Row, NestedTpls) => T = {
       |    casts.length match {
       |      case 0  => cast0[T]
       |      $resolveX
       |      case n  =>
       |        val last = n - 1
       |        (row: Row, nested: NestedTpls) =>
       |          var rowIndex   = firstAttrIndex + last
       |          var castIndex  = last
       |          var tpl: Tuple = Tuple1(nested) // adding nested tuples last
       |          while (castIndex >= 0) {
       |            tpl = casts(castIndex)(row.get(rowIndex)) *: tpl
       |            rowIndex -= 1
       |            castIndex -= 1
       |          }
       |          tpl.asInstanceOf[T]
       |    }
       |  }
       |
       |  final private def cast0[T]: (Row, NestedTpls) => T = {
       |    (_: Row, nested: NestedTpls) => nested.asInstanceOf[T]
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
         |  final private def cast$i[T](
         |    casts: List[AnyRef => AnyRef],
         |    firstIndex: AttrIndex
         |  ): (Row, NestedTpls) => T = {
         |    val List($casters) = casts
         |    val List($indexes) = (firstIndex until firstIndex + $i).toList
         |    (row: Row, nested: NestedTpls) =>
         |      (
         |        $castings,
         |        nested
         |      ).asInstanceOf[T]
         |  }""".stripMargin
  }
}
