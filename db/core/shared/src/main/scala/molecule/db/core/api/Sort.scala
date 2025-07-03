package molecule.db.core.api

import molecule.base.error.ExecutionError
import molecule.core.dataModel.*
import molecule.db.core.ops.ModelTransformations_.*


trait Sort[Entity <: Molecule] { self: Molecule =>

  def sortEntity: DataModel => Entity

  def a1: Entity = sortEntity(addSort(dataModel, "a1"))
  def a2: Entity = sortEntity(addSort(dataModel, "a2"))
  def a3: Entity = sortEntity(addSort(dataModel, "a3"))
  def a4: Entity = sortEntity(addSort(dataModel, "a4"))
  def a5: Entity = sortEntity(addSort(dataModel, "a5"))
  def d1: Entity = sortEntity(addSort(dataModel, "d1"))
  def d2: Entity = sortEntity(addSort(dataModel, "d2"))
  def d3: Entity = sortEntity(addSort(dataModel, "d3"))
  def d4: Entity = sortEntity(addSort(dataModel, "d4"))
  def d5: Entity = sortEntity(addSort(dataModel, "d5"))

  // Dynamic sorting
  def sort(i: Int): Entity = {
    i match {
      case 0     => sortEntity(addSort(dataModel, ""))
      case 1     => sortEntity(addSort(dataModel, "a1"))
      case -1    => sortEntity(addSort(dataModel, "d1"))
      case 2     => sortEntity(addSort(dataModel, "a2"))
      case -2    => sortEntity(addSort(dataModel, "d2"))
      case 3     => sortEntity(addSort(dataModel, "a3"))
      case -3    => sortEntity(addSort(dataModel, "d3"))
      case 4     => sortEntity(addSort(dataModel, "a4"))
      case -4    => sortEntity(addSort(dataModel, "d4"))
      case 5     => sortEntity(addSort(dataModel, "a5"))
      case -5    => sortEntity(addSort(dataModel, "d5"))
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
