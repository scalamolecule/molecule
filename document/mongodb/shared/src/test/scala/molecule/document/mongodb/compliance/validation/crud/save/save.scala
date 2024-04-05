package molecule.document.mongodb.compliance.validation.crud.save

import molecule.coreTests.spi.validation.save._
import molecule.document.mongodb.setup.TestAsync_mongodb

object FormatConstants  extends FormatConstants with TestAsync_mongodb
object FormatVariables extends FormatVariables with TestAsync_mongodb
object Semantics extends Semantics with TestAsync_mongodb
object TypesOne extends TypesOne with TestAsync_mongodb
object TypesOneOpt extends TypesOneOpt with TestAsync_mongodb
object TypesSeq extends TypesSeq with TestAsync_mongodb
object TypesSeqOpt extends TypesSeqOpt with TestAsync_mongodb
object TypesSet extends TypesSet with TestAsync_mongodb
object TypesSetOpt extends TypesSetOpt with TestAsync_mongodb
