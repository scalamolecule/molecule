package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastRow2Tpl extends DatomicGenBase("CastRow2Tpl", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => cast$i(casters)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.datomic.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_[Tpl] { self: Model2Query with DatomicQueryBase =>
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    attrIndex: AttrIndex,
       |    acc: List[Row => Any],
       |    nested: Option[NestedTpls]
       |  ): List[Row => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        val cast = (row: Row) => casts.head(row.get(attrIndex))
       |        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast, nested)
       |
       |      // Nested
       |      case List(-1) :: Nil =>
       |        val cast = (_: Row) => nested.get
       |        resolveArities(Nil, casts, 0, acc :+ cast, None)
       |
       |      // Composite
       |      case ii :: as =>
       |        val n                     = ii.length
       |        val (tplCasts, moreCasts) = casts.splitAt(n)
       |        val cast                  = castRow2AnyTpl(ii.map(List(_)), tplCasts, attrIndex, nested)
       |        resolveArities(as, moreCasts, attrIndex + n, acc :+ cast, nested)
       |
       |      case Nil => acc
       |    }
       |  }
       |
       |  final protected def castRow2AnyTpl(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    attrIndex: AttrIndex,
       |    nested: Option[NestedTpls]
       |  ): Row => Any = {
       |    val casters = resolveArities(arities, casts, attrIndex, Nil, nested)
       |    arities.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(casters: List[Row => Any]): Row => Any = {
         |    val List($casters) = casters
         |    (row: Row) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
