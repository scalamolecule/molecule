package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.refNum._
import molecule.sql.h2.setup.TestAsync_h2

object AggrRefNum_sum extends AggrRefNum_sum with TestAsync_h2
object AggrRefNum_median extends AggrRefNum_median with TestAsync_h2
object AggrRefNum_avg extends AggrRefNum_avg with TestAsync_h2
object AggrRefNum_variance extends AggrRefNum_variance with TestAsync_h2
object AggrRefNum_stddev extends AggrRefNum_stddev with TestAsync_h2
