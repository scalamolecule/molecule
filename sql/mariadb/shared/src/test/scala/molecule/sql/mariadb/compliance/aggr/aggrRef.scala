package molecule.sql.mariadb.compliance.aggr

import molecule.coreTests.spi.aggr.ref._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object AggrRef_count extends AggrRef_count with TestAsync_mariadb
object AggrRef_distinct extends AggrRef_distinct with TestAsync_mariadb
object AggrRef_min_max extends AggrRef_min_max with TestAsync_mariadb
object AggrRef_min_max_n extends AggrRef_min_max_n with TestAsync_mariadb
object AggrRef_sample extends AggrRef_sample with TestAsync_mariadb
object AggrRef_sample_n extends AggrRef_sample_n with TestAsync_mariadb
