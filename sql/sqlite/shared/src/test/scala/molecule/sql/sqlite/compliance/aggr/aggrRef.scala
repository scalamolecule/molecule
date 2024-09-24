package molecule.sql.sqlite.compliance.aggr

import molecule.coreTests.spi.aggr.ref._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_AggrRef_count extends AggrRef_count with Test_sqlite_async
object Test_AggrRef_distinct extends AggrRef_distinct with Test_sqlite_async
object Test_AggrRef_min_max extends AggrRef_min_max with Test_sqlite_async
object Test_AggrRef_min_max_n extends AggrRef_min_max_n with Test_sqlite_async
object Test_AggrRef_sample extends AggrRef_sample with Test_sqlite_async
object Test_AggrRef_sample_n extends AggrRef_sample_n with Test_sqlite_async
