package boilerplate.db.sql.core.query.casting

import boilerplate.db.sql.DbSqlBase

object _CastOptRefLeaf extends DbSqlBase("CastOptRefLeaf", "/query/casting") {

  override val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casts, firstIndex)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.sql.core.query.casting
       |
       |import molecule.db.sql.core.query.SqlQueryBase
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
       |      case n =>
       |        (row: RS) =>
       |          var i          = firstIndex + n
       |          var j          = n - 1
       |          var hasEmpty   = false
       |          var tpl: Tuple = EmptyTuple
       |
       |          while (j >= 0 && !hasEmpty) {
       |            val cast = casts(j)
       |            i -= 1
       |            val v = cast(row, i)
       |            hasEmpty = hasEmptyValue(row, i, v)
       |            if (!hasEmpty)
       |              tpl = v *: tpl
       |            j -= 1
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
