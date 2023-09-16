package molecule.datalog.datomic.test.crud

import molecule.coreTests.test.crud.insert._
import molecule.datalog.datomic.setup.TestAsync_datomic

object InsertCardOne extends InsertCardOne with TestAsync_datomic
object InsertCardSet extends InsertCardSet with TestAsync_datomic
object InsertRefs extends InsertRefs with TestAsync_datomic
object InsertSemantics extends InsertSemantics with TestAsync_datomic
