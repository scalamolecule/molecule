package molecule.sql.postgres.compliance.aggr

import molecule.coreTests.spi.aggr.ref.{AggrRef_count, AggrRef_distinct, AggrRef_min_max, AggrRef_min_max_n, AggrRef_sample, AggrRef_sample_n}
import molecule.sql.postgres.setup.TestAsync_postgres

object AggrOneRef_count extends AggrRef_count with TestAsync_postgres
object AggrOneRef_distinct extends AggrRef_distinct with TestAsync_postgres
object AggrOneRef_min_max extends AggrRef_min_max with TestAsync_postgres
object AggrOneRef_min_max_n extends AggrRef_min_max_n with TestAsync_postgres
object AggrOneRef_sample extends AggrRef_sample with TestAsync_postgres
object AggrOneRef_sample_n extends AggrRef_sample_n with TestAsync_postgres
