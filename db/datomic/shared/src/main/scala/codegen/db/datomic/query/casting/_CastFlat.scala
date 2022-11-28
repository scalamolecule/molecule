package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastFlat extends DatomicGenBase("CastFlat", "/query/casting") {

  val content = {
    val resolveX = (1 to 22).map(i => s"case $i => cast$i").mkString("\n      ")
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
       |  final override lazy protected val row2tpl: Row => Tpl = {
       |    casts.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val resolvers = (0 until i).map { j => s"val c$j = casts($j)" }.mkString("\n    ")
    val tuple     = (0 until i).map { j => s"c$j(row.get($j))" }.mkString(",\n        ")
    val body      =
      s"""
         |  final private def cast$i: Row => Tpl = {
         |    $resolvers
         |    (row: Row) =>
         |      (
         |        $tuple
         |        ).asInstanceOf[Tpl]
         |  }""".stripMargin
  }
}
