package molecule.core.api

import molecule.base.api.SchemaTransaction

abstract class Connection(val schema: SchemaTransaction) {

  type Data

  def transact(data: Data): TxReport

  def db: Any = ???
}
