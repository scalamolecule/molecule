package codegen.core.api

import codegen.CoreGenBase

object _Molecules extends CoreGenBase( "Molecules", "/api") {

  val content = {
    val molecules = (1 to 22).map(arity => MoleculeFactories(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |import molecule.core.action._
       |import molecule.core.ast.DataModel.Element
       |
       |trait Molecule {
       |  val elements: List[Element]
       |}
       |
       |trait Molecule_00 extends Molecule {
       |  def delete = Delete(elements)
       |}
       |
       |trait MoleculeBase extends Molecule {
       |  def save = Save(elements)
       |  def update = Update(elements)
       |  def upsert = Update(elements, true)
       |  def delete = Delete(elements)
       |}
       |
       |$molecules
       |""".stripMargin
  }

  case class MoleculeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  trait Molecule_$n0[${`A..V`}] extends MoleculeBase {
         |    def insert = Insert_$arity[${`A..V`}](elements)
         |    def query  = Query[${`(A..V)`}](elements)
         |  }
         |  """.stripMargin
  }
}
