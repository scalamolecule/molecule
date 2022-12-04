package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastTpl extends DatomicGenBase("CastTpl", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => cast$i(casters)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |import scala.annotation.tailrec
       |
       |
       |trait ${fileName}_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  @tailrec
       |  final private def rec(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    rowIndex: Int,
       |    castIndex: Int,
       |    acc: List[Row => Any],
       |    nested: Option[List[Any]]
       |  ): List[Row => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        rec(as, casts, rowIndex + 1, castIndex + 1,
       |          acc :+ ((row: Row) => casts(castIndex)(row.get(rowIndex))), nested)
       |
       |      // Nested
       |      case List(-1) :: Nil =>
       |        rec(Nil, casts, 0, 0,
       |          acc :+ ((_: Row) => nested.get), None)
       |
       |      // Composite
       |      case ii :: as =>
       |        val n = ii.length
       |        rec(as, casts, rowIndex + n, castIndex + n,
       |          acc :+ castTpl(List.fill(n)(List(1)), casts, rowIndex, castIndex), nested)
       |
       |      case Nil => acc
       |    }
       |  }
       |
       |  final protected def castTpl(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    rowIndex: Int = 0,
       |    castIndex: Int = 0,
       |    nested: Option[List[Any]] = None
       |  ): Row => Any = {
       |    val casters = rec(arities, casts, rowIndex, castIndex, Nil, nested)
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
