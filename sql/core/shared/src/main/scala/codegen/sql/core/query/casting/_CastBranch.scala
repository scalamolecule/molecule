package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastBranch extends SqlGenBase("CastBranch", "/query/casting") {

  override val content = {
    val caseX          = (1 to 21).map(i => s"case ${caseN(i)} => cast$i[T](casters, firstAttrIndex)").mkString("\n      ")
    val resolveMethods = (2 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query.casting
       |
       |import molecule.sql.core.query.SqlQueryBase
       |import scala.annotation.tailrec
       |
       |
       |class $fileName_[NestedTpls] extends SqlQueryBase {
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[Int],
       |    casts: List[(RS, ParamIndex) => Any],
       |    attrIndex: ParamIndex,
       |    acc: List[(RS, ParamIndex, NestedTpls) => Any]
       |  ): List[(RS, ParamIndex, NestedTpls) => Any] = {
       |    arities match {
       |      case 1 :: as =>
       |        val cast = (row: RS, attrIndex1: ParamIndex, _: NestedTpls) => casts.head(row, attrIndex1)
       |        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast)
       |
       |      // Nested
       |      case -1 :: as =>
       |        val cast = (_: RS, _: ParamIndex, nested: NestedTpls) => nested
       |        resolveArities(as, casts, 0, acc :+ cast)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final def cast[T](
       |    arities: List[Int],
       |    casts: List[(RS, ParamIndex) => Any],
       |    firstAttrIndex: ParamIndex,
       |  ): (RS, NestedTpls) => T = {
       |    val casters = resolveArities(arities, casts, firstAttrIndex, Nil)
       |    casters.length match {
       |      case 0  => cast0[T]
       |      $caseX
       |    }
       |  }
       |
       |  final private def cast0[T]: (RS, NestedTpls) => T = {
       |    (_: RS, nested: NestedTpls) =>
       |      nested.asInstanceOf[T]
       |  }
       |
       |  final private def cast1[T](
       |    casters: List[(RS, ParamIndex, NestedTpls) => Any],
       |    firstAttrIndex: ParamIndex
       |  ): (RS, NestedTpls) => T = {
       |    val List(c1) = casters
       |    (row: RS, nestedTpls: NestedTpls) =>
       |      (
       |        c1(row, firstAttrIndex, nestedTpls)
       |        ).asInstanceOf[T]
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val indexes  = (1 to i).map("i" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row, i$j, nested)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i[T](
         |    casters: List[(RS, ParamIndex, NestedTpls) => Any],
         |    firstAttrIndex: ParamIndex
         |  ): (RS, NestedTpls) => T = {
         |    val List($casters) = casters
         |    val List($indexes) = (firstAttrIndex until firstAttrIndex + $i).toList
         |    (row: RS, nested: NestedTpls) =>
         |      (
         |        $castings
         |      ).asInstanceOf[T]
         |  }""".stripMargin
  }
}
