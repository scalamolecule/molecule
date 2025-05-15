package boilerplate.db.core.api

import boilerplate.db.core.CoreGenBase

object _Molecules extends CoreGenBase( "Molecules", "/api") {

  val content = {
    val molecules = (1 to 22).map(arity => MoleculeFactories(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api
       |
       |import molecule.db.core.action.*
       |import molecule.db.core.ast.DataModel
       |
       |trait Molecule {
       |  val dataModel: DataModel
       |}
       |
       |trait Molecule_00 extends Molecule {
       |  def delete = Delete(dataModel)
       |}
       |
       |trait MoleculeBase extends Molecule {
       |  def save = Save(dataModel)
       |  def update = Update(dataModel)
       |  def upsert = Update(dataModel, true)
       |  def delete = Delete(dataModel)
       |}
       |
       |$molecules
       |""".stripMargin
  }

  case class MoleculeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  trait Molecule_$n0[${`A..V`}] extends MoleculeBase {
         |    def insert = Insert_$arity[${`A..V`}](dataModel)
         |    def query  = Query[${`(A..V)`}](dataModel)
         |  }
         |  """.stripMargin
  }
}
