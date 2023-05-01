package codegen.core.action

import codegen.CoreGenBase

object _Actions extends CoreGenBase( "Actions", "/action") {

  val content = {
    val classes = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.action
       |
       |import molecule.boilerplate.api._
       |
       |class Actions_00(molecule: Molecule_00) {
       |  final def delete = Delete(molecule.elements)
       |}
       |$classes
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val p    = if (arity == 1) "   " else if (arity >= 10) "  " else " "
    val body =
      s"""
         |class $fileName_$n0[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]) {
         |  final def save   = Save(molecule.elements)
         |  final def insert = Insert_$arity[${`A..V`}](molecule.elements)
         |  final def query  = Query[${`(A..V)`}](molecule.elements)
         |  final def update = Update(molecule.elements)
         |  final def upsert = Update(molecule.elements, true)
         |  final def delete = Delete(molecule.elements)
         |}""".stripMargin
  }
}
