package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.ref._
import molecule.sql.h2.setup.TestAsync_h2

object AggrRef_count extends AggrRef_count with TestAsync_h2
object AggrRef_distinct extends AggrRef_distinct with TestAsync_h2
object AggrRef_min_max extends AggrRef_min_max with TestAsync_h2
object AggrRef_min_max_n extends AggrRef_min_max_n with TestAsync_h2
object AggrRef_sample extends AggrRef_sample with TestAsync_h2
object AggrRef_sample_n extends AggrRef_sample_n with TestAsync_h2
