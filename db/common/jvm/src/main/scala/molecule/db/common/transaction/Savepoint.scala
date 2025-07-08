package molecule.db.common.transaction

import molecule.db.common.api.Savepoint


class SavepointImpl(savepoint: java.sql.Savepoint, rollback0: () => Unit) extends Savepoint {
  def savepointId: Int = savepoint.getSavepointId
  def savepointName: String = savepoint.getSavepointName
  def rollback(): Unit = rollback0()
}