package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastOptRefBranch extends SqlGenBase("CastOptRefBranch", "/query/casting") {

  override val content = {
    val caseX          = (2 to 21).map(i => s"case ${caseN(i)} => cast$i(casters, firstAttrIndex)").mkString("\n      ")
    val resolveMethods = (3 to 21).map(arity => Chunk(arity).body).mkString("\n")
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
       |    acc: List[(RS, ParamIndex, Option[Any]) => Any]
       |  ): List[(RS, ParamIndex, Option[Any]) => Any] = {
       |    arities match {
       |      case 1 :: as =>
       |        val cast = (row: RS, attrIndex1: ParamIndex, _: Option[Any]) => casts.head(row, attrIndex1)
       |        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast)
       |
       |      // Nested
       |      case -1 :: as =>
       |        val cast = (_: RS, _: ParamIndex, nested: Option[Any]) => nested
       |        resolveArities(as, casts, 0, acc :+ cast)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final def cast(
       |    arities: List[Int],
       |    casts: List[(RS, ParamIndex) => Any],
       |    firstAttrIndex: ParamIndex,
       |  ): (RS, Option[Any]) => Option[Any] = {
       |    val casters = resolveArities(arities, casts, firstAttrIndex, Nil)
       |    casters.length match {
       |      $caseX
       |    }
       |  }
       |
       |  final private def cast2(
       |    casters: List[(RS, ParamIndex, Option[Any]) => Any],
       |    firstAttrIndex: ParamIndex
       |  ): (RS, Option[Any]) => Option[Any] = {
       |    val List(c1, c2) = casters
       |    val List(i1, i2) = (firstAttrIndex until firstAttrIndex + 2).toList
       |    (row: RS, nestedOption: Option[Any]) => {
       |      val r1       = row.getObject(i1)
       |      val (v1, v2) = (
       |        c1(row, i1, nestedOption),
       |        c2(row, i2, nestedOption)
       |      )
       |      if (r1 == null && v1 != None)
       |        Option.empty[Any]
       |      else
       |        Some((v1, v2))
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters    = (1 to i).map("c" + _).mkString(", ")
    val indexes    = (1 to i).map("i" + _).mkString(", ")
    val indexes2   = (1 until i).map("i" + _).mkString(", ")
    val results    = (1 until i).map("r" + _).mkString(", ")
    val values     = (1 to i).map("v" + _).mkString(", ")
    val castings   = (1 to i).map { j => s"c$j(row, i$j, nestedOption)" }.mkString(",\n        ")
    val nullChecks = (1 until i).map { j => s"r$j == null && v$j != None" }.mkString("\n||        || ")
    val body       =
      s"""
         |  final private def cast$i(
         |    casters: List[(RS, ParamIndex, Option[Any]) => Any],
         |    firstAttrIndex: ParamIndex
         |  ): (RS, Option[Any]) => Option[Any] = {
         |    val List($casters) = casters
         |    val List($indexes) = (firstAttrIndex until firstAttrIndex + $i).toList
         |    (row: RS, nestedOption: Option[Any]) => {
         |      val List($results) = List($indexes2).map(row.getObject)
         |      val ($values)     = (
         |        $castings
         |      )
         |      if ($nullChecks
         |      ) Option.empty[Any] else
         |        Some(($values))
         |    }
         |  }""".stripMargin
  }
}
