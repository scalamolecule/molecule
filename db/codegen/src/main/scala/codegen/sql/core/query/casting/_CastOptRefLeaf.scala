package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastOptRefLeaf extends SqlGenBase("CastOptRefLeaf", "/query/casting") {

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
       |    }
       |  }
       |
       |  final private def cast1(
       |    casts: List[(RS, ParamIndex) => Any],
       |    firstIndex: ParamIndex
       |  ): RS => Option[Any] = {
       |    val List(c1) = casts
       |    (row: RS) =>
       |      val v1 = c1(row, firstIndex)
       |      if (hasEmpty(row, firstIndex, List(v1)))
       |        Option.empty[Any]
       |      else
       |        Some(v1)
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
         |  ): RS => Option[Any] = {
         |    val List($casters) = casts
         |    val List($indexes) = (firstIndex until firstIndex + $i).toList
         |    (row: RS) => {
         |      val ($values) = (
         |        $castings
         |      )
         |      if (hasEmpty(row, firstIndex, List($values)))
         |        Option.empty[Any]
         |      else
         |        Some(($values))
         |    }
         |  }""".stripMargin
  }
}
