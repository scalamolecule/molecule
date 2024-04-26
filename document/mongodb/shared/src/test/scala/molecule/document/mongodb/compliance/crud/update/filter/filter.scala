package molecule.document.mongodb.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update2.filter._
import molecule.document.mongodb.setup.TestAsync_mongodb

object FilterOne extends FilterOne with TestAsync_mongodb
object FilterSet extends FilterSet with TestAsync_mongodb
object FilterSeq extends FilterSeq with TestAsync_mongodb
object FilterMap extends FilterMap with TestAsync_mongodb
