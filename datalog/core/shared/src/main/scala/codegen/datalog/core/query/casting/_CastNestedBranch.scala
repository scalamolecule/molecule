package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastNestedBranch extends DatomicGenBase("CastNestedBranch", "/query/casting") {

  val content = {
    val resolveX       = (1 to 21).map(i =>
      s"case $i => cast$i[T](casters)"
    ).mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.datomic.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_[Tpl]
       |  extends CastRow2Tpl_ { self: Model2Query with DatomicQueryBase =>
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    attrIndex: AttrIndex,
       |    attrIndexTx: AttrIndex,
       |    acc: List[(Row, NestedTpls) => Any]
       |  ): List[(Row, NestedTpls) => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        val cast = (row: Row, _: NestedTpls) => casts.head(row.get(attrIndex))
       |        resolveArities(as, casts.tail, attrIndex + 1, attrIndexTx, acc :+ cast)
       |
       |      // Nested
       |      case List(-1) :: as =>
       |        val cast = (_: Row, nested: NestedTpls) => nested
       |        resolveArities(as, casts, attrIndexTx, attrIndexTx, acc :+ cast)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final protected def castBranch[T](
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    firstAttrIndex: AttrIndex,
       |    firstAttrIndexTx: AttrIndex
       |  ): (Row, NestedTpls) => T = {
       |    val casters = resolveArities(arities, casts, firstAttrIndex, firstAttrIndexTx, Nil)
       |    casters.length match {
       |      case 0 => cast0[T]
       |      $resolveX
       |    }
       |  }
       |
       |  final private def cast0[T]: (Row, List[Any]) => T = {
       |    (_: Row, nested: List[Any]) => nested.asInstanceOf[T]
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row, nested)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i[T](casters: List[(Row, List[Any]) => Any]): (Row, List[Any]) => T = {
         |    val List($casters) = casters
         |    (row: Row, nested: List[Any]) =>
         |      (
         |        $castings
         |      ).asInstanceOf[T]
         |  }""".stripMargin
  }
}
