package molecule.document.mongodb.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.refNum._
import molecule.document.mongodb.setup.TestAsync_mongodb

object AggrSetRefNum_sum extends AggrSetRefNum_sum with TestAsync_mongodb
object AggrSetRefNum_median extends AggrSetRefNum_median with TestAsync_mongodb
object AggrSetRefNum_avg extends AggrSetRefNum_avg with TestAsync_mongodb
object AggrSetRefNum_variance extends AggrSetRefNum_variance with TestAsync_mongodb
object AggrSetRefNum_stddev extends AggrSetRefNum_stddev with TestAsync_mongodb