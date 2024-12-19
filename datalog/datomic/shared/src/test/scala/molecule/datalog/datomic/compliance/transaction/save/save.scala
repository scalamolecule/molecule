package molecule.datalog.datomic.compliance.transaction.save

import molecule.coreTests.spi.transaction.save._
import molecule.datalog.datomic.setup.{Test_datomic_async, TestSuite_datomic_array}
import molecule.datalog.datomic.spi.Spi_datomic_async

object Test_SaveCardOne extends SaveCardOne with Test_datomic_async
object Test_SaveCardSeq extends SaveCardSeq with TestSuite_datomic_array with Spi_datomic_async
object Test_SaveCardSet extends SaveCardSet with Test_datomic_async
object Test_SaveCardMap extends SaveCardMap with Test_datomic_async
object Test_SaveRefs extends SaveRefs with Test_datomic_async
object Test_SaveSemantics extends SaveSemantics with Test_datomic_async
