package molecule.datalog.datomic.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.ref._
import molecule.datalog.datomic.setup.TestAsync_datomic

object AggrOneRef_count extends AggrOneRef_count with TestAsync_datomic
object AggrOneRef_distinct extends AggrOneRef_distinct with TestAsync_datomic
object AggrOneRef_min_max extends AggrOneRef_min_max with TestAsync_datomic
object AggrOneRef_min_max_n extends AggrOneRef_min_max_n with TestAsync_datomic
object AggrOneRef_sample extends AggrOneRef_sample with TestAsync_datomic
object AggrOneRef_sample_n extends AggrOneRef_sample_n with TestAsync_datomic