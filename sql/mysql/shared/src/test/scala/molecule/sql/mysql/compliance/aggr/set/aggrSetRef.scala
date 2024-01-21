package molecule.sql.mysql.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.ref._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrSetRef_count extends AggrSetRef_count with TestAsync_mysql
object AggrSetRef_distinct extends AggrSetRef_distinct with TestAsync_mysql
object AggrSetRef_max extends AggrSetRef_max with TestAsync_mysql
object AggrSetRef_min extends AggrSetRef_min with TestAsync_mysql
object AggrSetRef_sample extends AggrSetRef_sample with TestAsync_mysql
