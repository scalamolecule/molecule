package molecule.datalog.datomic.test.txMetaData

import molecule.coreTests.test.txMetaData.crud._
import molecule.datalog.datomic.setup.CoreTestAsync

object TxDelete extends TxDelete with CoreTestAsync
object TxInsert extends TxInsert with CoreTestAsync
object TxSave extends TxSave with CoreTestAsync
object TxUpdate extends TxUpdate with CoreTestAsync
