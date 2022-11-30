package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastNestedLeafComp extends DatomicGenBase("CastNestedLeafComp", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => cast$i(firstRowIndex, tplCounts)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |
       |
       |trait ${fileName}_[Tpl] extends CastCompositeTpl_[Tpl] {
       |  self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final protected def castLeafComp(
       |    firstRowIndex: Int,
       |    tplCounts: List[Int] = Nil
       |  ): Row => Any = {
       |    tplCounts.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val counts = (0 until i).map("n" + _).mkString(", ")
    val indexes = (Seq("val i0 = firstRowIndex") ++ (1 until i).map(j => s"val i$j = n${j - 1} + i${j - 1}")).mkString("\n    ")
    val casters = (0 until i).map { j => s"val c$j = castSubTpl(n$j, i$j)" }.mkString("\n    ")
    val tuple   = (0 until i).map { j => s"c$j(row)" }.mkString(",\n        ")
    val body    =
      s"""
         |  final private def cast$i(
         |    firstRowIndex: Int,
         |    tplCounts: List[Int]
         |  ): Row => Any = {
         |    val List($counts) = tplCounts
         |    $indexes
         |    $casters
         |    (row: Row) =>
         |      (
         |        $tuple
         |        )
         |  }""".stripMargin
  }
}
