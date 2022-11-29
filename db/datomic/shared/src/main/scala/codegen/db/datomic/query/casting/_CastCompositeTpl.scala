package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastCompositeTpl extends DatomicGenBase("CastCompositeTpl", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => cast$i(firstIndex)").mkString("\n      ")
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
       |  final protected def castSubTpl(n: Int, firstIndex: Int): Row => Any = {
       |    n match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters = ("val c0 = casts(firstIndex)" +: (1 until i)
      .map { j => s"val c$j = casts(firstIndex + $j)" }).mkString("\n    ")
    val indexes = (0 until i).map("i" + _).mkString(", ")
    val tuple   = (0 until i).map { j => s"c$j(row.get(i$j))" }.mkString(",\n        ")
    val body    =
      s"""
         |  final private def cast$i(firstIndex: Int): Row => Any = {
         |    $casters
         |    val List($indexes) = (firstIndex until firstIndex + $i).toList
         |    (row: Row) =>
         |      (
         |        $tuple
         |        )
         |  }""".stripMargin
  }
}
