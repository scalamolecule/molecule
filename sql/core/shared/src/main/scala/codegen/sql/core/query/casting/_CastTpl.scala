package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastTpl extends SqlGenBase("CastTpl", "/query/casting") {

  override val content = {
    val caseX          = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casters, attrIndex)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query.casting
       |
       |import molecule.sql.core.query.SqlQueryBase
       |import scala.annotation.tailrec
       |
       |
       |object $fileName_ extends SqlQueryBase {
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[Int],
       |    casts: List[(RS, ParamIndex) => Any],
       |    attrIndex: ParamIndex,
       |    acc: List[(RS, ParamIndex) => Any],
       |  ): List[(RS, ParamIndex) => Any] = {
       |    arities match {
       |      case 1 :: as =>
       |        val cast = (row: RS, _: ParamIndex) => casts.head(row, attrIndex)
       |        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast)
       |
       |      // Nested
       |      case -1 :: Nil =>
       |        val cast = (_: RS, _: ParamIndex) => List.empty[Any]
       |        resolveArities(Nil, casts, 0, acc :+ cast)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final def cast(
       |    arities: List[Int],
       |    casts: List[(RS, ParamIndex) => Any],
       |    attrIndex: ParamIndex,
       |  ): RS => Any = {
       |    val casters: List[(RS, ParamIndex) => Any] = resolveArities(arities, casts, attrIndex, Nil)
       |    arities.length match {
       |      $caseX
       |    }
       |  }
       |
       |  final private def cast1(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
       |    val List(c1) = casters
       |    (row: RS) =>
       |      c1(row, attrIndex)
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val indexes  = (1 to i).map("i" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row, i$j)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(casters: List[(RS, ParamIndex) => Any], attrIndex: ParamIndex): RS => Any = {
         |    val List($casters) = casters
         |    val List($indexes) = (attrIndex until attrIndex + $i).toList
         |    (row: RS) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
