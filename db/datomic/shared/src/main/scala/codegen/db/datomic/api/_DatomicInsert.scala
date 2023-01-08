package codegen.db.datomic.api

import codegen.DatomicGenBase

object _DatomicInsert extends DatomicGenBase("DatomicInsert", "/api") {

  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.api
       |
       |import molecule.boilerplate.ast.Model._
       |import molecule.core.api._
       |
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |class ${fileName}_$arity[${`A..V`}](elements: List[Element])
         |  extends ${fileName}Impl(elements) with Insert_$arity[${`A..V`}]""".stripMargin
  }
}
