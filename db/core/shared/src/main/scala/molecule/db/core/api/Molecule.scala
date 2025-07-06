package molecule.db.core.api

import molecule.base.error.ModelError
import molecule.core.dataModel.DataModel
import molecule.db.core.action.*
import scala.annotation.compileTimeOnly

trait Molecule {
  val dataModel: DataModel

  protected def noBinding(action: String): Unit = {
    if (dataModel.binds != 0)
      throw ModelError(s"$action action does not support bind parameters.")
  }
}

trait Molecule_0 extends Molecule {
  def delete: Delete = {
    noBinding("Delete")
    Delete(dataModel)
  }

  // Avoid stack overflow from overload resolution
  @compileTimeOnly("Save by applying data to mandatory attributes only.")
  def save: Save = ???

  @compileTimeOnly("Insert by applying data to mandatory attributes only.")
  def insert: Insert = ???

  @compileTimeOnly("Update by applying data to mandatory attributes only.")
  def update: Update = ???

  @compileTimeOnly("Upsert by applying data to mandatory attributes only.")
  def upsert: Update = ???
}

trait NonEmptyMolecule extends Molecule {
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

  // Avoid stack overflow from overload resolution
  @compileTimeOnly("Mandatory attributes not allowed in delete molecules.")
  def delete: Delete = ???
}


trait Molecule_1[T] extends NonEmptyMolecule {
  def insert: Insert_1[T] = {
    noBinding("Insert")
    Insert_1[T](dataModel)
  }
  def query: Query[T] = Query[T](dataModel)
}

trait Molecule_n[Tpl <: Tuple] extends NonEmptyMolecule {
  def insert: Insert_n[Tpl] = {
    noBinding("Insert")
    Insert_n[Tpl](dataModel)
  }
  def query: Query[Tpl] = Query[Tpl](dataModel)
}