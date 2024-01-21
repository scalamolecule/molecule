package molecule.sql.mysql.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.refNum._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrSetRefNum_sum extends AggrSetRefNum_sum with TestAsync_mysql
object AggrSetRefNum_median extends AggrSetRefNum_median with TestAsync_mysql
object AggrSetRefNum_avg extends AggrSetRefNum_avg with TestAsync_mysql
object AggrSetRefNum_variance extends AggrSetRefNum_variance with TestAsync_mysql
object AggrSetRefNum_stddev extends AggrSetRefNum_stddev with TestAsync_mysql
