package molecule.sql.postgres.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.ref._
import molecule.sql.postgres.setup.TestAsync_postgres

object AggrSetRef_count extends AggrSetRef_count with TestAsync_postgres
object AggrSetRef_distinct extends AggrSetRef_distinct with TestAsync_postgres
object AggrSetRef_max extends AggrSetRef_max with TestAsync_postgres
object AggrSetRef_min extends AggrSetRef_min with TestAsync_postgres
object AggrSetRef_sample extends AggrSetRef_sample with TestAsync_postgres
