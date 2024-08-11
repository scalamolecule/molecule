package molecule.datalog.datomic.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.datalog.datomic.setup.{TestAsync_datomic, TestSuiteArray_datomic}
import molecule.datalog.datomic.spi.SpiAsync_datomic

object Test_SaveCardOne extends SaveCardOne with TestAsync_datomic
object Test_SaveCardSeq extends SaveCardSeq with TestSuiteArray_datomic with SpiAsync_datomic
object Test_SaveCardSet extends SaveCardSet with TestAsync_datomic
object Test_SaveCardMap extends SaveCardMap with TestAsync_datomic
object Test_SaveRefs extends SaveRefs with TestAsync_datomic
object Test_SaveSemantics extends SaveSemantics with TestAsync_datomic
