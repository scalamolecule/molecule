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
       |import molecule.datomic.query.Base
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_[Tpl]
       |  extends CastRow2Tpl_[Tpl] { self: Model2Query with Base[Tpl] =>
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    rowIndex: Int,
       |    rowIndexTx: Int,
       |    acc: List[(Row, List[Any]) => Any]
       |  ): List[(Row, List[Any]) => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        val cast = (row: Row, _: List[Any]) => casts.head(row.get(rowIndex))
       |        resolveArities(as, casts.tail, rowIndex + 1, rowIndexTx, acc :+ cast)
       |
       |      // Nested
       |      case List(-1) :: as =>
       |        val cast = (_: Row, nested: List[Any]) => nested
       |        resolveArities(as, casts, rowIndexTx, rowIndexTx, acc :+ cast)
       |
       |      // Composite with only tacit attributes
       |      case ii :: as if ii.isEmpty =>
       |        resolveArities(as, casts, rowIndex, rowIndexTx, acc)
       |
       |      // Composite with nested
       |      case ii :: as if ii.last == -1 =>
       |        val n                     = ii.length - 1
       |        val (tplCasts, moreCasts) = casts.splitAt(n)
       |        val cast                  = (row: Row, nested: List[Any]) =>
       |          castRow2AnyTpl(ii.map(List(_)), tplCasts, rowIndex, Some(nested))(row)
       |        resolveArities(as, moreCasts, rowIndexTx, rowIndexTx, acc :+ cast)
       |
       |      // Composite
       |      case ii :: as =>
       |        val n                     = ii.length
       |        val (tplCasts, moreCasts) = casts.splitAt(n)
       |        val cast                  = (row: Row, _: List[Any]) =>
       |          castRow2AnyTpl(ii.map(List(_)), tplCasts, rowIndex, None)(row)
       |        resolveArities(as, moreCasts, rowIndex + n, rowIndexTx, acc :+ cast)
       |
       |      case Nil => acc
       |    }
       |  }
       |
       |  final protected def castBranch[T](
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    rowIndex: Int,
       |    rowIndexTx: Int
       |  ): (Row, List[Any]) => T = {
       |    val casters = resolveArities(arities, casts, rowIndex, rowIndexTx, Nil)
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
