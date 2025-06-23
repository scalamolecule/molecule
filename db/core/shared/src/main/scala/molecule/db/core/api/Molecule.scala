package molecule.db.core.api

import molecule.base.error.ModelError
import molecule.core.dataModel.DataModel
import molecule.db.core.action.*

trait Molecule {
  val dataModel: DataModel

  protected def noBinding(action: String): Unit = {
    if (dataModel.binds != 0)
      throw ModelError(s"$action action does not support bind parameters.")
  }
}

trait Molecule_0 extends Molecule {
  def delete = Delete(dataModel)
}

trait MoleculeBase extends Molecule {
  def save: Save = {
    noBinding("Save")
    Save(dataModel)
  }

  def update: Update = {
    noBinding("Update")
    Update(dataModel)
  }

  def upsert: Update = {
    noBinding("Upsert")
    Update(dataModel, true)
  }

  def delete: Delete = {
    noBinding("Delete")
    Delete(dataModel)
  }
}


//trait Molecule_1[T](using T <:< Tuple =:= false) extends MoleculeBase {
//trait Molecule_1[T](using NotTuple[T]) extends MoleculeBase {
trait Molecule_1[T] extends MoleculeBase {
  def insert: Insert_1[T] = {
    noBinding("Insert")
    Insert_1[T](dataModel)
  }
  def query: Query[T] = Query[T](dataModel)
}

trait Molecule_n[Tpl <: Tuple] extends MoleculeBase {
  def insert: Insert_n[Reverse[Tpl]] = {
    noBinding("Insert")
    Insert_n[Reverse[Tpl]](dataModel)
  }
  def query: Query[Reverse[Tpl]] = Query[Reverse[Tpl]](dataModel)
}