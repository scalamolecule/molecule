package boilerplate.db.sql.core.query.casting

import boilerplate.db.sql.DbSqlBase

object _CastOptRefBranch extends DbSqlBase("CastOptRefBranch", "/query/casting") {

  override val content = {
    val caseX          = (1 to 21).map(i => s"case ${caseN(i)} => cast$i(casts, firstIndex)").mkString("\n      ")
    val resolveMethods = (2 to 21).map(arity => Chunk(arity).body).mkString("\n")
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
       |  ): (RS, Option[Any]) => Option[Any] = {
       |    casts.length match {
       |      case 0  => cast0
       |      $caseX
       |    }
       |  }
       |
       |  final private def cast0: (RS, Option[Any]) => Option[Any] = {
       |    (_: RS, nestedOption: Option[Any]) =>
       |      nestedOption
       |  }
       |
       |  final private def cast1(
       |    casts: List[(RS, ParamIndex) => Any],
       |    firstIndex: ParamIndex
       |  ): (RS, Option[Any]) => Option[Any] = {
       |    val List(c1) = casts
       |    (row: RS, nestedOption: Option[Any]) => {
       |      val v1 = c1(row, firstIndex)
       |      if (hasEmpty(row, firstIndex, List(v1)))
       |        Option.empty[Any]
       |      else
       |        Some((v1, nestedOption))
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val indexes  = (1 to i).map("i" + _).mkString(", ")
    val values   = (1 to i).map("v" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row, i$j)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(
         |    casts: List[(RS, ParamIndex) => Any],
         |    firstIndex: ParamIndex
         |  ): (RS, Option[Any]) => Option[Any] = {
         |    val List($casters) = casts
         |    val List($indexes) = (firstIndex until firstIndex + $i).toList
         |    (row: RS, nestedOption: Option[Any]) => {
         |      val ($values) = (
         |        $castings
         |      )
         |      if (hasEmpty(row, firstIndex, List($values)))
         |        Option.empty[Any]
         |      else
         |        Some(($values, nestedOption))
         |    }
         |  }""".stripMargin
  }
}
