package molecule.sql.mariadb.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.ref._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object AggrSetRef_count extends AggrSetRef_count with TestAsync_mariadb
object AggrSetRef_distinct extends AggrSetRef_distinct with TestAsync_mariadb
object AggrSetRef_max extends AggrSetRef_max with TestAsync_mariadb
object AggrSetRef_min extends AggrSetRef_min with TestAsync_mariadb
object AggrSetRef_sample extends AggrSetRef_sample with TestAsync_mariadb
