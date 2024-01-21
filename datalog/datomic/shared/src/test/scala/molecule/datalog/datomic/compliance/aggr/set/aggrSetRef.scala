package molecule.datalog.datomic.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.ref._
import molecule.datalog.datomic.setup.TestAsync_datomic

object AggrSetRef_count extends AggrSetRef_count with TestAsync_datomic
object AggrSetRef_distinct extends AggrSetRef_distinct with TestAsync_datomic
object AggrSetRef_max extends AggrSetRef_max with TestAsync_datomic
object AggrSetRef_min extends AggrSetRef_min with TestAsync_datomic
object AggrSetRef_sample extends AggrSetRef_sample with TestAsync_datomic
