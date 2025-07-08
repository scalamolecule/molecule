package boilerplate.db.common.query.casting

import boilerplate.db.common.DbCommonBase

object _CastOptRefLeaf extends DbCommonBase("CastOptRefLeaf", "/query/casting") {

  override val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casts, firstIndex)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.common.query.casting
       |
       |import molecule.db.common.query.SqlQueryBase
       |
       |
       |object $fileName_ extends SqlQueryBase {
       |
       |  final def cast(
       |    casts: List[Cast],
       |    firstIndex: ParamIndex
       |  ): RS => Option[Any] = {
       |    casts.length match {
       |      case 0  => (_: RS) => Option.empty[Any]
       |      $resolveX
       |      case n  =>
       |        val last = n - 1
       |        (row: RS) =>
       |          var rowIndex   = firstIndex + last
       |          var castIndex  = last
       |          var hasEmpty   = false
       |          var tpl: Tuple = EmptyTuple
       |          while (castIndex >= 0 && !hasEmpty) {
       |            val cast = casts(castIndex)
       |            val v    = cast(row, rowIndex)
       |            hasEmpty = hasEmptyValue(row, rowIndex, v)
       |            if (!hasEmpty)
       |              tpl = v *: tpl
       |            rowIndex -= 1
       |            castIndex -= 1
       |          }
       |          if (hasEmpty) Option.empty[Any] else Some(tpl)
       |    }
       |  }
       |
       |  final private def cast1(
       |    casts: List[(RS, ParamIndex) => Any],
       |    firstIndex: ParamIndex
       |  ): RS => Option[Any] = {
       |    val List(c1) = casts
       |    (row: RS) =>
       |      val v1 = cast(row, firstIndex)
       |      if (hasEmptyValue(row, firstIndex, v1)) Option.empty[Any] else Some(v1)
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters     = (1 to i).map("c" + _).mkString(", ")
    val indexes     = (1 to i).map("i" + _).mkString(", ")
    val values      = (1 to i).map(j => s"val v$j = c$j(row, i$j)").mkString("\n      ")
    val checks      = (1 to i).map(j => s"hasEmptyValue(row, i$j, v$j)").mkString(" ||\n          ")
    val tupleValues = (1 to i).map("v" + _).mkString(", ")

    val body =
      s"""
         |  final private def cast$i(
         |    casts: List[(RS, ParamIndex) => Any],
         |    firstIndex: ParamIndex
         |  ): RS => Option[Any] = {
         |    val List($casters) = casts
         |    val List($indexes) = (firstIndex until firstIndex + $i).toList
         |    (row: RS) => {
         |      $values
         |      if (
         |        $checks
         |      ) Option.empty[Any] else
         |        Some(($tupleValues))
         |    }
         |  }""".stripMargin
  }
}
