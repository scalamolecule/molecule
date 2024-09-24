package molecule.sql.h2.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.h2.setup.TestAsync_h2

object Test_TypesOne extends TypesOne with TestAsync_h2
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_h2
object Test_TypesSeq extends TypesSeq with TestAsync_h2
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_h2
object Test_TypesSet extends TypesSet with TestAsync_h2
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_h2
