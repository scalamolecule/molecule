package molecule.sql.sqlite.compliance.aggr

import molecule.coreTests.spi.aggr.refNum._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object AggrRefNum_sum extends AggrRefNum_sum with TestAsync_sqlite
object AggrRefNum_median extends AggrRefNum_median with TestAsync_sqlite
object AggrRefNum_avg extends AggrRefNum_avg with TestAsync_sqlite
object AggrRefNum_variance extends AggrRefNum_variance with TestAsync_sqlite
object AggrRefNum_stddev extends AggrRefNum_stddev with TestAsync_sqlite
