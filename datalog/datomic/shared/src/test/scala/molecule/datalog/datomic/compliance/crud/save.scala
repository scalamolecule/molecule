package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.spi.crud.save._
import molecule.datalog.datomic.setup.{TestAsync_datomic, TestSuiteArray_datomic}
import molecule.datalog.datomic.spi.SpiAsync_datomic

object SaveCardOne extends SaveCardOne with TestAsync_datomic
object SaveCardSet extends SaveCardSet with TestAsync_datomic
object SaveCardSeq extends SaveCardSeq with TestSuiteArray_datomic with SpiAsync_datomic
object SaveRefs extends SaveRefs with TestAsync_datomic
object SaveRefsOwned extends SaveRefsOwned with TestAsync_datomic
object SaveSemantics extends SaveSemantics with TestAsync_datomic
