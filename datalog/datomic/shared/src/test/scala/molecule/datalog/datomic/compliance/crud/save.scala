package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.compliance.crud.save._
import molecule.datalog.datomic.setup.TestAsync_datomic

object SaveCardOne extends SaveCardOne with TestAsync_datomic
object SaveCardSet extends SaveCardSet with TestAsync_datomic
object SaveRefs extends SaveRefs with TestAsync_datomic
object SaveSemantics extends SaveSemantics with TestAsync_datomic
