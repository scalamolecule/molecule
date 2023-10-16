package molecule.sql.h2.compliance.validation.crud.insert

import molecule.coreTests.spi.validation.insert._
import molecule.sql.h2.setup.TestAsync_h2

object FormatConstants  extends FormatConstants with TestAsync_h2
object FormatVariables extends FormatVariables with TestAsync_h2
object Nested extends Nested with TestAsync_h2
object Semantics extends Semantics with TestAsync_h2
object TypesOne extends TypesOne with TestAsync_h2
object TypesOneOpt extends TypesOneOpt with TestAsync_h2
object TypesSet extends TypesSet with TestAsync_h2
object TypesSetOpt extends TypesSetOpt with TestAsync_h2
