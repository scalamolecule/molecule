package molecule.sql.h2.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.ref._
import molecule.sql.h2.setup.TestAsync_h2

object AggrOneRef_count extends AggrOneRef_count with TestAsync_h2
object AggrOneRef_distinct extends AggrOneRef_distinct with TestAsync_h2
object AggrOneRef_min_max extends AggrOneRef_min_max with TestAsync_h2
object AggrOneRef_min_max_n extends AggrOneRef_min_max_n with TestAsync_h2
object AggrOneRef_sample extends AggrOneRef_sample with TestAsync_h2
object AggrOneRef_sample_n extends AggrOneRef_sample_n with TestAsync_h2
