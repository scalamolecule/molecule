package molecule.document.mongodb.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.document.mongodb.setup.TestAsync_mongodb

object TypesOne extends TypesOne with TestAsync_mongodb
object TypesOneOpt extends TypesOneOpt with TestAsync_mongodb
object TypesSeq extends TypesSeq with TestAsync_mongodb
object TypesSeqOpt extends TypesSeqOpt with TestAsync_mongodb
object TypesSet extends TypesSet with TestAsync_mongodb
object TypesSetOpt extends TypesSetOpt with TestAsync_mongodb
