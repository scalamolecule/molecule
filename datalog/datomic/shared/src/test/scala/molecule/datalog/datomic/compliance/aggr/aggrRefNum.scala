package molecule.datalog.datomic.compliance.aggr

import molecule.coreTests.spi.aggr.refNum._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_AggrRefNum_sum extends AggrRefNum_sum with Test_datomic_async
object Test_AggrRefNum_median extends AggrRefNum_median with Test_datomic_async
object Test_AggrRefNum_avg extends AggrRefNum_avg with Test_datomic_async
object Test_AggrRefNum_variance extends AggrRefNum_variance with Test_datomic_async
object Test_AggrRefNum_stddev extends AggrRefNum_stddev with Test_datomic_async
