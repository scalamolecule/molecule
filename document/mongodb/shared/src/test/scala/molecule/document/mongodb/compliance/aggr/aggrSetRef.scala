package molecule.document.mongodb.compliance.aggr

import molecule.coreTests.spi.aggr.set.ref._
import molecule.document.mongodb.setup.TestAsync_mongodb

object AggrSetRef_count extends AggrSetRef_count with TestAsync_mongodb
object AggrSetRef_distinct extends AggrSetRef_distinct with TestAsync_mongodb
object AggrSetRef_max extends AggrSetRef_max with TestAsync_mongodb
object AggrSetRef_min extends AggrSetRef_min with TestAsync_mongodb
object AggrSetRef_sample extends AggrSetRef_sample with TestAsync_mongodb
