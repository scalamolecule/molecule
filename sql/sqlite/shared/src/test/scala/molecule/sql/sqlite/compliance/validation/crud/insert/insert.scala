package molecule.sql.sqlite.compliance.validation.crud.insert

import molecule.coreTests.spi.validation.insert._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_FormatConstants  extends FormatConstants with TestAsync_sqlite
object Test_FormatVariables extends FormatVariables with TestAsync_sqlite
object Test_Nested extends Nested with TestAsync_sqlite
object Test_Semantics extends Semantics with TestAsync_sqlite
object Test_TypesOne extends TypesOne with TestAsync_sqlite
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_sqlite
object Test_TypesSeq extends TypesSeq with TestAsync_sqlite
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_sqlite
object Test_TypesSet extends TypesSet with TestAsync_sqlite
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_sqlite
