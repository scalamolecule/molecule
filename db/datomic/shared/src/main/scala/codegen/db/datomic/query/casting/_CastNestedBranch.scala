package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastNestedBranch extends DatomicGenBase("CastNestedBranch", "/query/casting") {

  val content = {
    val resolveX       = (1 to 21).map(i => s"case $i => cast$i[T](casts, rowIndexes)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |
       |
       |trait ${fileName}_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final protected def castBranch[T](
       |    casts: List[AnyRef => AnyRef],
       |    firstRowIndex: Int
       |  ): (Row, List[Any]) => T = {
       |    val n          = casts.length
       |    val rowIndexes = (firstRowIndex until (firstRowIndex + n)).toList
       |    n match {
       |      case 0 => cast0[T]
       |      $resolveX
       |    }
       |  }
       |
       |  final private def cast0[T]: (Row, List[Any]) => T = {
       |    (row: Row, leaf: List[Any]) => leaf.asInstanceOf[T]
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters = (0 until i).map { j => s"val c$j = casts($j)" }.mkString("\n    ")
    val indexes = (0 until i).map("i" + _).mkString(", ")
    val tuple   = (0 until i).map { j => s"c$j(row.get(i$j))" }.mkString(",\n        ")
    val body    =
      s"""
         |  final private def cast$i[T](
         |    casts: List[AnyRef => AnyRef],
         |    rowIndexes: List[Int]
         |  ): (Row, List[Any]) => T = {
         |    $casters
         |    val List($indexes) = rowIndexes
         |    (row: Row, leaf: List[Any]) =>
         |      (
         |        $tuple,
         |        leaf
         |        ).asInstanceOf[T]
         |  }""".stripMargin
  }
}
