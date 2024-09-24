package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.refNum._
import molecule.sql.h2.setup.Test_h2_async

object Test_AggrRefNum_sum extends AggrRefNum_sum with Test_h2_async
object Test_AggrRefNum_median extends AggrRefNum_median with Test_h2_async
object Test_AggrRefNum_avg extends AggrRefNum_avg with Test_h2_async
object Test_AggrRefNum_variance extends AggrRefNum_variance with Test_h2_async
object Test_AggrRefNum_stddev extends AggrRefNum_stddev with Test_h2_async
