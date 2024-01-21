package molecule.sql.h2.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.refNum._
import molecule.sql.h2.setup.TestAsync_h2

object AggrOneRefNum_sum extends AggrOneRefNum_sum with TestAsync_h2
object AggrOneRefNum_median extends AggrOneRefNum_median with TestAsync_h2
object AggrOneRefNum_avg extends AggrOneRefNum_avg with TestAsync_h2
object AggrOneRefNum_variance extends AggrOneRefNum_variance with TestAsync_h2
object AggrOneRefNum_stddev extends AggrOneRefNum_stddev with TestAsync_h2
