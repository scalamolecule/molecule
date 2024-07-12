package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastBranch extends SqlGenBase("CastBranch", "/query/casting") {

  override val content = {
    val caseX          = (1 to 21).map(i => s"case ${caseN(i)} => cast$i(casts, firstIndex)").mkString("\n      ")
    val resolveMethods = (2 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query.casting
       |
       |import molecule.sql.core.query.SqlQueryBase
       |
       |
       |class $fileName_[Nested] extends SqlQueryBase {
       |
       |  final def cast(
       |    casts: List[Cast],
       |    firstIndex: ParamIndex
       |  ): (RS, Nested) => Any = {
       |    casts.length match {
       |      case 0  => cast0
       |      $caseX
       |    }
       |  }
       |
       |  final private def cast0: (RS, Nested) => Any = {
       |    (_: RS, nested: Nested) =>
       |      nested
       |  }
       |
       |  final private def cast1(
       |    casts: List[(RS, ParamIndex) => Any],
       |    firstIndex: ParamIndex
       |  ): (RS, Nested) => Any = {
       |    val c1 = casts.head
       |    (row: RS, nested: Nested) => {
       |      (
       |        c1(row, firstIndex),
       |        nested
       |      )
       |    }
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
         |  ): (RS, Nested) => Any = {
         |    val List($casts) = casts
         |    val List($indexes) = (firstIndex until firstIndex + $i).toList
         |    (row: RS, nested: Nested) =>
         |      (
         |        $castings,
         |        nested
         |      )
         |  }""".stripMargin
  }
}
