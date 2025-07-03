package boilerplate.db.sql.core.query.casting

import boilerplate.db.sql.DbSqlBase

object _CastTpl extends DbSqlBase("CastTpl", "/query/casting") {

  override val content = {
    val caseX          = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casts, firstIndex)").mkString("\n      ")
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
       |  ): RS => Any = {
       |    casts.length match {
       |      $caseX
       |      case n  =>
       |        val i0 = firstIndex + n
       |        val j0 = n - 1
       |        (row: RS) =>
       |          var i          = i0
       |          var j          = j0
       |          var tpl: Tuple = EmptyTuple
       |          while (j >= 0) {
       |            i -= 1
       |            tpl = casts(j)(row, i) *: tpl
       |            j -= 1
       |          }
       |          tpl
       |    }
       |  }
       |
       |  final private def cast1(
       |    casts: List[(RS, ParamIndex) => Any],
       |    firstIndex: ParamIndex
       |  ): RS => Any = {
       |    val c1 = casts.head
       |    (row: RS) =>
       |      c1(row, firstIndex)
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casts    = (1 to i).map("c" + _).mkString(", ")
    val indexes  = (1 to i).map("i" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row, i$j)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(
         |    casts: List[(RS, ParamIndex) => Any],
         |    firstIndex: ParamIndex
         |  ): RS => Any = {
         |    val List($casts) = casts
         |    val List($indexes) = (firstIndex until firstIndex + $i).toList
         |    (row: RS) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
