package molecule.document.mongodb.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update2.ops._
import molecule.document.mongodb.setup.TestAsync_mongodb

object OpsOne extends OpsOne with TestAsync_mongodb
object OpsSet extends OpsSet with TestAsync_mongodb
object OpsSeq extends OpsSeq with TestAsync_mongodb
object OpsMap extends OpsMap with TestAsync_mongodb
