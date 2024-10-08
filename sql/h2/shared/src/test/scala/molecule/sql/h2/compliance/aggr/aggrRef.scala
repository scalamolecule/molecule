package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.ref._
import molecule.sql.h2.setup.Test_h2_async

object Test_AggrRef_count extends AggrRef_count with Test_h2_async
object Test_AggrRef_distinct extends AggrRef_distinct with Test_h2_async
object Test_AggrRef_min_max extends AggrRef_min_max with Test_h2_async
object Test_AggrRef_min_max_n extends AggrRef_min_max_n with Test_h2_async
object Test_AggrRef_sample extends AggrRef_sample with Test_h2_async
object Test_AggrRef_sample_n extends AggrRef_sample_n with Test_h2_async
