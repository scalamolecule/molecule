package molecule.core.api

trait Savepoint {
  def savepointId: Int
  def savepointName: String
  def rollback(): Unit
}
