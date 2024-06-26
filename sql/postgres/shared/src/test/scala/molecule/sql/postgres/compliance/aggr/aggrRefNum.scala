package molecule.sql.postgres.compliance.aggr

import molecule.coreTests.spi.aggr.refNum.{AggrRefNum_avg, AggrRefNum_median, AggrRefNum_stddev, AggrRefNum_sum, AggrRefNum_variance}
import molecule.sql.postgres.setup.TestAsync_postgres

object AggrOneRefNum_sum extends AggrRefNum_sum with TestAsync_postgres
object AggrOneRefNum_median extends AggrRefNum_median with TestAsync_postgres
object AggrOneRefNum_avg extends AggrRefNum_avg with TestAsync_postgres
object AggrOneRefNum_variance extends AggrRefNum_variance with TestAsync_postgres
object AggrOneRefNum_stddev extends AggrRefNum_stddev with TestAsync_postgres
