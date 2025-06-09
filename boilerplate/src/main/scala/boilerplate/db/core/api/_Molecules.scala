package boilerplate.db.core.api

import boilerplate.db.core.DbCoreBase

object _Molecules extends DbCoreBase( "Molecules", "/api") {

  val content = {
    val molecules = (1 to 22).map(arity => MoleculeFactories(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api
       |
       |import molecule.base.error.ModelError
       |import molecule.db.core.action.*
       |import molecule.core.ast.DataModel
       |
       |trait Molecule {
       |  val dataModel: DataModel
       |
       |  protected def noBinding(action: String): Unit = {
       |    if (dataModel.binds != 0)
       |      throw ModelError(s"$$action action does not support bind parameters.")
       |  }
       |}
       |
       |trait Molecule_00 extends Molecule {
       |  def delete = Delete(dataModel)
       |}
       |
       |trait MoleculeBase extends Molecule {
       |  def save: Save = {
       |    noBinding("Save")
       |    Save(dataModel)
       |  }
       |
       |  def update: Update = {
       |    noBinding("Update")
       |    Update(dataModel)
       |  }
       |
       |  def upsert: Update = {
       |    noBinding("Upsert")
       |    Update(dataModel, true)
       |  }
       |
       |  def delete: Delete = {
       |    noBinding("Delete")
       |    Delete(dataModel)
       |  }
       |}
       |
       |$molecules
       |""".stripMargin
  }

  case class MoleculeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""trait Molecule_$n0[${`A..V`}] extends MoleculeBase {
         |  def insert: Insert_$arity[${`A..V`}] = {
         |    noBinding("Insert")
         |    Insert_$arity[${`A..V`}](dataModel)
         |  }
         |  def query: Query[${`(A..V)`}] =
         |    Query[${`(A..V)`}](dataModel)
         |}
         |""".stripMargin
  }
}
