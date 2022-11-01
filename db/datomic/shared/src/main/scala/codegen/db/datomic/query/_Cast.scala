package codegen.db.datomic.query

import codegen.DatomicGenBase

object _Cast extends DatomicGenBase("Cast", "/query") {

  val content = {
    val resolveX = (1 to 22).map(i => s"case $i => resolve$i").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query
       |
       |import molecule.core.query.Model2Query
       |
       |
       |trait ${fileName}_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final override lazy protected val row2tpl: Row => Tpl = {
       |    castScala.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val resolvers = (0 until i).map { j => s"val cast$j = castScala($j)" }.mkString("\n    ")
    val tuple     = (0 until i).map { j => s"cast$j(row.get($j))" }.mkString(",\n        ")
    val body      =
      s"""
         |  final private def resolve$i: Row => Tpl = {
         |    $resolvers
         |    (row: Row) =>
         |      (
         |        $tuple
         |        ).asInstanceOf[Tpl]
         |  }""".stripMargin
  }
}
