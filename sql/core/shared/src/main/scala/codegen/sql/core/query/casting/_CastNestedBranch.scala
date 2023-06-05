package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastNestedBranch extends SqlGenBase("CastNestedBranch", "/query/casting") {

  override val content = {
    val castX          = (1 to 21).map(i => s"case ${caseN(i)} => cast$i[T](casters, firstAttrIndex)").mkString("\n      ")
    val resolveMethods = (2 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.sql.core.query.Base
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_ extends CastRow2Tpl_ { self: Model2Query with Base =>
       |
       |@tailrec
       |  final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[(Row, AttrIndex) => AnyRef],
       |    attrIndex: AttrIndex,
       |    attrIndexTx: AttrIndex,
       |    acc: List[(Row, AttrIndex, NestedTpls) => Any]
       |  ): List[(Row, AttrIndex, NestedTpls) => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        val cast = (row: Row, attrIndex1: AttrIndex, _: NestedTpls) => casts.head(row, attrIndex1)
       |        resolveArities(as, casts.tail, attrIndex + 1, attrIndexTx, acc :+ cast)
       |
       |      // Nested
       |      case List(-1) :: as =>
       |        val cast = (_: Row, _: AttrIndex, nested: NestedTpls) => nested
       |        resolveArities(as, casts, attrIndexTx, attrIndexTx, acc :+ cast)
       |
       |      // Composite with only tacit attributes
       |      case ii :: as if ii.isEmpty =>
       |        resolveArities(as, casts, attrIndex, attrIndexTx, acc)
       |
       |      // Composite with nested
       |      case ii :: as if ii.last == -1 =>
       |        val n                     = ii.length - 1
       |        val (tplCasts, moreCasts) = casts.splitAt(n)
       |        val cast                  = (row: Row, attrIndex1: AttrIndex, nested: NestedTpls) =>
       |          castRow2AnyTpl(ii.map(List(_)), tplCasts, attrIndex1, Some(nested))(row)
       |        resolveArities(as, moreCasts, attrIndexTx, attrIndexTx, acc :+ cast)
       |
       |      // Composite
       |      case ii :: as =>
       |        val n                     = ii.length
       |        val (tplCasts, moreCasts) = casts.splitAt(n)
       |        val cast                  = (row: Row, _: AttrIndex, _: NestedTpls) =>
       |          castRow2AnyTpl(ii.map(List(_)), tplCasts, attrIndex, None)(row)
       |        resolveArities(as, moreCasts, attrIndex + n, attrIndexTx, acc :+ cast)
       |
       |      case Nil =>
       |        acc
       |    }
       |  }
       |
       |  final protected def castBranch[T](
       |    arities: List[List[Int]],
       |    casts: List[(Row, AttrIndex) => AnyRef],
       |    firstAttrIndex: AttrIndex,
       |    firstAttrIndexTx: AttrIndex
       |  ): (Row, NestedTpls) => T = {
       |    val casters = resolveArities(arities, casts, firstAttrIndex, firstAttrIndexTx, Nil)
       |    casters.length match {
       |      case 0  => cast0[T]
       |      $castX
       |    }
       |  }
       |
       |  final private def cast0[T]: (Row, NestedTpls) => T = {
       |    (_: Row, nested: NestedTpls) =>
       |      nested.asInstanceOf[T]
       |  }
       |
       |  final private def cast1[T](
       |    casters: List[(Row, AttrIndex, NestedTpls) => Any],
       |    firstAttrIndex: AttrIndex
       |  ): (Row, NestedTpls) => T = {
       |    val List(c1) = casters
       |    (row: Row, nested: NestedTpls) =>
       |      (
       |        c1(row, firstAttrIndex, nested)
       |        ).asInstanceOf[T]
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters     = (1 to i).map("c" + _).mkString(", ")
    val attrIndexes = (1 to i).map("a" + _).mkString(", ")
    val castings    = (1 to i).map { j => s"c$j(row, a$j, nested)" }.mkString(",\n        ")
    val body        =
      s"""
         |  final private def cast$i[T](
         |    casters: List[(Row, AttrIndex, NestedTpls) => Any],
         |    firstAttrIndex: AttrIndex
         |  ): (Row, List[Any]) => T = {
         |    val List($casters) = casters
         |    val List($attrIndexes) = (firstAttrIndex until firstAttrIndex + $i).toList
         |    (row: Row, nested: List[Any]) =>
         |      (
         |        $castings
         |      ).asInstanceOf[T]
         |  }""".stripMargin
  }
}
