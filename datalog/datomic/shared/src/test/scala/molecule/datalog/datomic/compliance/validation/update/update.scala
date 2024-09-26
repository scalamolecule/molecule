package molecule.datalog.datomic.compliance.validation.update

import molecule.coreTests.spi.validation.update._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_TypesOne extends TypesOne with Test_datomic_async
object Test_TypesOneOpt extends TypesOneOpt with Test_datomic_async
object Test_TypesSeq extends TypesSeq with Test_datomic_async
object Test_TypesSeqOpt extends TypesSeqOpt with Test_datomic_async
object Test_TypesSet extends TypesSet with Test_datomic_async
object Test_TypesSetOpt extends TypesSetOpt with Test_datomic_async
