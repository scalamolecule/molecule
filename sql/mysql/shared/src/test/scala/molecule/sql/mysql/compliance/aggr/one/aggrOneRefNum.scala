package molecule.sql.mysql.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.refNum._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrOneRefNum_sum extends AggrOneRefNum_sum with TestAsync_mysql
object AggrOneRefNum_median extends AggrOneRefNum_median with TestAsync_mysql
object AggrOneRefNum_avg extends AggrOneRefNum_avg with TestAsync_mysql
object AggrOneRefNum_variance extends AggrOneRefNum_variance with TestAsync_mysql
object AggrOneRefNum_stddev extends AggrOneRefNum_stddev with TestAsync_mysql
