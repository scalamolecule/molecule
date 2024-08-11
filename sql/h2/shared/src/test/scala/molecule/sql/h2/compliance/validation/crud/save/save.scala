package molecule.sql.h2.compliance.validation.crud.save

import molecule.coreTests.spi.validation.save._
import molecule.sql.h2.setup.TestAsync_h2

object Test_FormatConstants  extends FormatConstants with TestAsync_h2
object Test_FormatVariables extends FormatVariables with TestAsync_h2
object Test_Semantics extends Semantics with TestAsync_h2
object Test_TypesOne extends TypesOne with TestAsync_h2
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_h2
object Test_TypesSeq extends TypesSeq with TestAsync_h2
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_h2
object Test_TypesSet extends TypesSet with TestAsync_h2
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_h2
