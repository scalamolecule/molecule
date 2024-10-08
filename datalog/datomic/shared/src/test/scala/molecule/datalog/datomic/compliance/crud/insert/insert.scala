package molecule.datalog.datomic.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.datalog.datomic.setup.{TestSuite_datomic_array, Test_datomic_async}
import molecule.datalog.datomic.spi.Spi_datomic_async

object Test_InsertCardOne extends InsertCardOne with Test_datomic_async
object Test_InsertCardSeq extends InsertCardSeq with TestSuite_datomic_array with Spi_datomic_async
object Test_InsertCardSet extends InsertCardSet with Test_datomic_async
object Test_InsertCardMap extends InsertCardMap with Test_datomic_async
object Test_InsertRefs extends InsertRefs with Test_datomic_async
object Test_InsertSemantics extends InsertSemantics with Test_datomic_async
