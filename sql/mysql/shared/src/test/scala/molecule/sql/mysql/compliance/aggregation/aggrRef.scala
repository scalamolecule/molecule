package molecule.sql.mysql.compliance.aggregation

import molecule.coreTests.spi.aggregation.ref._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_AggrRef_count extends AggrRef_count with Test_mysql_async
object Test_AggrRef_distinct extends AggrRef_distinct with Test_mysql_async
object Test_AggrRef_min_max extends AggrRef_min_max with Test_mysql_async
object Test_AggrRef_min_max_n extends AggrRef_min_max_n with Test_mysql_async
object Test_AggrRef_sample extends AggrRef_sample with Test_mysql_async
object Test_AggrRef_sample_n extends AggrRef_sample_n with Test_mysql_async