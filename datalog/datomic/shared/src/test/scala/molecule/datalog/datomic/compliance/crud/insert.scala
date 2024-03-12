package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.spi.crud.insert._
import molecule.datalog.datomic.setup.TestAsync_datomic

object InsertCardOne extends InsertCardOne with TestAsync_datomic
object InsertCardSet extends InsertCardSet with TestAsync_datomic
object InsertCardSeq extends InsertCardSet with TestAsync_datomic
object InsertRefs extends InsertRefs with TestAsync_datomic
object InsertRefsOwned extends InsertRefsOwned with TestAsync_datomic
object InsertSemantics extends InsertSemantics with TestAsync_datomic
