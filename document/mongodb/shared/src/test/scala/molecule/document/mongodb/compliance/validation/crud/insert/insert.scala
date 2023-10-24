package molecule.document.mongodb.compliance.validation.crud.insert

import molecule.coreTests.spi.validation.insert._
import molecule.document.mongodb.setup.TestAsync_mongodb

object FormatConstants  extends FormatConstants with TestAsync_mongodb
object FormatVariables extends FormatVariables with TestAsync_mongodb
object Nested extends Nested with TestAsync_mongodb
object Semantics extends Semantics with TestAsync_mongodb
object TypesOne extends TypesOne with TestAsync_mongodb
object TypesOneOpt extends TypesOneOpt with TestAsync_mongodb
object TypesSet extends TypesSet with TestAsync_mongodb
object TypesSetOpt extends TypesSetOpt with TestAsync_mongodb
