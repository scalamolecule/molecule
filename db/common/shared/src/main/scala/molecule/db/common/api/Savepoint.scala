package molecule.db.common.api

trait Savepoint {
  def savepointId: Int
  def savepointName: String
  def rollback(): Unit
}
