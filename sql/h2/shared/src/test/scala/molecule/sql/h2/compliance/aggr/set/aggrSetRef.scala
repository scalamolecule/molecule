package molecule.sql.h2.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.ref._
import molecule.sql.h2.setup.TestAsync_h2

object AggrSetRef_count extends AggrSetRef_count with TestAsync_h2
object AggrSetRef_distinct extends AggrSetRef_distinct with TestAsync_h2
object AggrSetRef_max extends AggrSetRef_max with TestAsync_h2
object AggrSetRef_min extends AggrSetRef_min with TestAsync_h2
object AggrSetRef_sample extends AggrSetRef_sample with TestAsync_h2
