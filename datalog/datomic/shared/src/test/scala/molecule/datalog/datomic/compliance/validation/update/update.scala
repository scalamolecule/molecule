package molecule.datalog.datomic.compliance.validation.update

import molecule.coreTests.spi.validation.update._
import molecule.datalog.datomic.setup.Test_datomic_async

object TypesOne extends TypesOne with Test_datomic_async
object TypesOneOpt extends TypesOneOpt with Test_datomic_async
object TypesSeq extends TypesSeq with Test_datomic_async
object TypesSeqOpt extends TypesSeqOpt with Test_datomic_async
object TypesSet extends TypesSet with Test_datomic_async
object TypesSetOpt extends TypesSetOpt with Test_datomic_async
