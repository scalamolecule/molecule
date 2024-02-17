package molecule.datalog.datomic.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.refNum._
import molecule.datalog.datomic.setup.TestAsync_datomic

object AggrOneRefNum_sum extends AggrOneRefNum_sum with TestAsync_datomic
object AggrOneRefNum_median extends AggrOneRefNum_median with TestAsync_datomic
object AggrOneRefNum_avg extends AggrOneRefNum_avg with TestAsync_datomic
object AggrOneRefNum_variance extends AggrOneRefNum_variance with TestAsync_datomic
object AggrOneRefNum_stddev extends AggrOneRefNum_stddev with TestAsync_datomic