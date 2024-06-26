package molecule.sql.mysql.compliance.aggr

import molecule.coreTests.spi.aggr.refNum._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrRefNum_sum extends AggrRefNum_sum with TestAsync_mysql
object AggrRefNum_median extends AggrRefNum_median with TestAsync_mysql
object AggrRefNum_avg extends AggrRefNum_avg with TestAsync_mysql
object AggrRefNum_variance extends AggrRefNum_variance with TestAsync_mysql
object AggrRefNum_stddev extends AggrRefNum_stddev with TestAsync_mysql
