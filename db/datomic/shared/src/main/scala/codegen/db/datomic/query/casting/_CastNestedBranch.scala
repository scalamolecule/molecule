package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastNestedBranch extends DatomicGenBase("CastNestedBranch", "/query/casting") {

  val content = {
    val resolveX       = (1 to 21).map(i =>
      s"case $i => cast$i[T](casters)"
    ).mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |import scala.annotation.tailrec
       |
       |
       |trait ${fileName}_[Tpl] extends CastTpl_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  @tailrec
       |  final private def rec(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    rowIndex: Int,
       |    rowIndexTx: Int,
       |    castIndex: Int,
       |    acc: List[(Row, List[Any]) => Any]
       |  ): List[(Row, List[Any]) => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        rec(as, casts, rowIndex + 1, rowIndexTx, castIndex + 1,
       |          acc :+ ((row: Row, _: List[Any]) => casts(castIndex)(row.get(rowIndex))))
       |
       |      // Nested
       |      case List(-1) :: as =>
       |        rec(as, casts, rowIndexTx, rowIndexTx, castIndex,
       |          acc :+ ((_: Row, nested: List[Any]) => nested))
       |
       |      // Composite branch
       |      case ii :: aa if ii.last == -1 =>
       |        val tplCaster =
       |          (_: Row, nested: List[Any]) =>
       |            castTpl(ii.map(List(_)), casts, rowIndex, castIndex, Some(nested))
       |
       |        rec(aa, casts, rowIndexTx, 0, castIndex + ii.length - 1,
       |          acc :+ ((row: Row, nested: List[Any]) => tplCaster(row, nested)(row)))
       |
       |      // Composite
       |      case ii :: as =>
       |        val n         = ii.length
       |        val tplCaster = castTpl(ii.map(List(_)), casts, rowIndex, castIndex)
       |        rec(as, casts, rowIndex + n, rowIndexTx, castIndex + n,
       |          acc :+ ((row: Row, _: List[Any]) => tplCaster(row)))
       |
       |      case Nil => acc
       |    }
       |  }
       |
       |  final protected def castBranch[T](
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    rowIndex: Int = 0,
       |    rowIndexTx: Int = 0,
       |    castIndex: Int = 0,
       |  ): (Row, List[Any]) => T = {
       |    val casters = rec(arities, casts, rowIndex, rowIndexTx, castIndex, Nil)
       |    arities.length match {
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
    val body    =
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
