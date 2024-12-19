package molecule.sql.sqlite.compliance.aggregation

import molecule.coreTests.spi.aggregation.refNum._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_AggrRefNum_sum extends AggrRefNum_sum with Test_sqlite_async
object Test_AggrRefNum_median extends AggrRefNum_median with Test_sqlite_async
object Test_AggrRefNum_avg extends AggrRefNum_avg with Test_sqlite_async
object Test_AggrRefNum_variance extends AggrRefNum_variance with Test_sqlite_async
object Test_AggrRefNum_stddev extends AggrRefNum_stddev with Test_sqlite_async