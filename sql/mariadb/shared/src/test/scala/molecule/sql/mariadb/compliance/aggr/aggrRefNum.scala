package molecule.sql.mariadb.compliance.aggr

import molecule.coreTests.spi.aggr.refNum._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object AggrRefNum_sum extends AggrRefNum_sum with TestAsync_mariadb
object AggrRefNum_median extends AggrRefNum_median with TestAsync_mariadb
object AggrRefNum_avg extends AggrRefNum_avg with TestAsync_mariadb
object AggrRefNum_variance extends AggrRefNum_variance with TestAsync_mariadb
object AggrRefNum_stddev extends AggrRefNum_stddev with TestAsync_mariadb
