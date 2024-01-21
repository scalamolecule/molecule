package molecule.sql.h2.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.refNum._
import molecule.sql.h2.setup.TestAsync_h2

object AggrSetRefNum_sum extends AggrSetRefNum_sum with TestAsync_h2
object AggrSetRefNum_median extends AggrSetRefNum_median with TestAsync_h2
object AggrSetRefNum_avg extends AggrSetRefNum_avg with TestAsync_h2
object AggrSetRefNum_variance extends AggrSetRefNum_variance with TestAsync_h2
object AggrSetRefNum_stddev extends AggrSetRefNum_stddev with TestAsync_h2
