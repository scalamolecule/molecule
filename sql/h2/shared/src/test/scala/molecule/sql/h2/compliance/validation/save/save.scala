package molecule.sql.h2.compliance.validation.save

import molecule.coreTests.spi.validation.save._
import molecule.sql.h2.setup.Test_h2_async

object Test_FormatConstants  extends FormatConstants with Test_h2_async
object Test_FormatVariables extends FormatVariables with Test_h2_async
object Test_Semantics extends Semantics with Test_h2_async
object Test_TypesOne extends TypesOne with Test_h2_async
object Test_TypesOneOpt extends TypesOneOpt with Test_h2_async
object Test_TypesSeq extends TypesSeq with Test_h2_async
object Test_TypesSeqOpt extends TypesSeqOpt with Test_h2_async
object Test_TypesSet extends TypesSet with Test_h2_async
object Test_TypesSetOpt extends TypesSetOpt with Test_h2_async
