package molecule.datalog.datomic.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.datalog.datomic.setup.{TestAsync_datomic, TestSuiteArray_datomic}
import molecule.datalog.datomic.spi.SpiAsync_datomic

object Test_InsertCardOne extends InsertCardOne with TestAsync_datomic
object Test_InsertCardSeq extends InsertCardSeq with TestSuiteArray_datomic with SpiAsync_datomic
object Test_InsertCardSet extends InsertCardSet with TestAsync_datomic
object Test_InsertCardMap extends InsertCardMap with TestAsync_datomic
object Test_InsertRefs extends InsertRefs with TestAsync_datomic
object Test_InsertSemantics extends InsertSemantics with TestAsync_datomic
