package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastNestedLeaf extends DatomicGenBase("CastNestedLeaf", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => cast$i(casts, rowIndexes)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |
       |
       |trait ${fileName}_[Tpl] {
       |  self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final protected def castLeaf(
       |    casts: List[AnyRef => AnyRef],
       |    firstRowIndex: Int
       |  ): Row => Any = {
       |    val n          = casts.length
       |    val rowIndexes = (firstRowIndex until (firstRowIndex + n)).toList
       |    n match {
       |      $resolveX
       |    }
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
         |  final private def cast$i(casts: List[AnyRef => AnyRef], rowIndexes: List[Int]): Row => Any = {
         |    $casters
         |    val List($indexes) = rowIndexes
         |    (row: Row) =>
         |      (
         |        $tuple
         |        )
         |  }""".stripMargin
  }
}
