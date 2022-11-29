package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastComposite extends DatomicGenBase("CastComposite", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => cast$i(tplCounts)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |
       |
       |trait ${fileName}_[Tpl] extends ${fileName}Tpl_[Tpl] {
       |  self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final protected def compositeRow2tpl: Row => Tpl = {
       |    val tplCounts = compositeTplCounts.filterNot(_ == 0)
       |    tplCounts.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val counts = (0 until i).map("n" + _).mkString(", ")
    val indexes = (Seq("val i0 = 0") ++ (1 until i).map(j => s"val i$j = n${j - 1} + i${j - 1}")).mkString("\n    ")
    val casters = (0 until i).map { j => s"val c$j = castSubTpl(n$j, i$j)" }.mkString("\n    ")
    val tuple   = (0 until i).map { j => s"c$j(row)" }.mkString(",\n        ")
    val body    =
      s"""
         |  final private def cast$i(tplCounts: List[Int]): Row => Tpl = {
         |    val List($counts) = tplCounts
         |    $indexes
         |    $casters
         |    (row: Row) =>
         |      (
         |        $tuple
         |        ).asInstanceOf[Tpl]
         |  }""".stripMargin
  }
}
