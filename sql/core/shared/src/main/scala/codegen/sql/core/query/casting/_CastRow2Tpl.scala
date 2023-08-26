package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastRow2Tpl extends SqlGenBase("CastRow2Tpl", "/query/casting") {

  override val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casters, attrIndex)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.sql.core.query.SqlQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_ { self: Model2Query with SqlQueryBase =>
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[(Row, AttrIndex) => Any],
       |    attrIndex: AttrIndex,
       |    acc: List[(Row, AttrIndex) => Any],
       |    nested: Option[NestedTpls]
       |  ): List[(Row, AttrIndex) => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        val cast = (row: Row, _: AttrIndex) => casts.head(row, attrIndex)
       |        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast, nested)
       |
       |      // Nested
       |      case List(-1) :: Nil =>
       |        val cast = (_: Row, _: AttrIndex) => nested.getOrElse(List.empty[Any])
       |        resolveArities(Nil, casts, 0, acc :+ cast, None)
       |
       |      // Composite
       |      case ii :: as =>
       |        val n                     = ii.length
       |        val (tplCasts, moreCasts) = casts.splitAt(n)
       |        val cast                  = (row: Row, _: AttrIndex) =>
       |          castRow2AnyTpl(ii.map(List(_)), tplCasts, attrIndex, nested)(row)
       |        resolveArities(as, moreCasts, attrIndex + n, acc :+ cast, nested)
       |
       |      case Nil => acc
       |    }
       |  }
       |
       |  final protected def castRow2AnyTpl(
       |    arities: List[List[Int]],
       |    casts: List[(Row, AttrIndex) => Any],
       |    attrIndex: AttrIndex,
       |    nested: Option[NestedTpls]
       |  ): Row => Any = {
       |    val casters: List[(Row, AttrIndex) => Any] = resolveArities(arities, casts, attrIndex, Nil, nested)
       |    arities.length match {
       |      $resolveX
       |    }
       |  }
       |
       |  final private def cast1(casters: List[(Row, Int) => Any], attrIndex: Int): Row => Any = {
       |    val List(c1) = casters
       |    (row: Row) =>
       |      c1(row, attrIndex)
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row, a$j)" }.mkString(",\n        ")
    val colNumbers  = (1 to i).map("a" + _).mkString(", ")
    val body     =
      s"""
         |  final private def cast$i(casters: List[(Row, AttrIndex) => Any], attrIndex: AttrIndex): Row => Any = {
         |    val List($casters) = casters
         |    val List($colNumbers) = (attrIndex until attrIndex + $i).toList
         |    (row: Row) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
