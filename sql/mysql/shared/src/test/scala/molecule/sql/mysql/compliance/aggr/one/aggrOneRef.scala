package molecule.sql.mysql.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.ref._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrOneRef_count extends AggrOneRef_count with TestAsync_mysql
object AggrOneRef_distinct extends AggrOneRef_distinct with TestAsync_mysql
object AggrOneRef_min_max extends AggrOneRef_min_max with TestAsync_mysql
object AggrOneRef_min_max_n extends AggrOneRef_min_max_n with TestAsync_mysql
object AggrOneRef_sample extends AggrOneRef_sample with TestAsync_mysql
object AggrOneRef_sample_n extends AggrOneRef_sample_n with TestAsync_mysql
