package molecule.sql.postgres.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.postgres.setup.Test_postgres_async

object Test_SharedSemantics extends SharedSemantics with Test_postgres_async
