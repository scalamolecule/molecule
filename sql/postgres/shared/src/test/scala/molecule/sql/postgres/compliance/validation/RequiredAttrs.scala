package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.postgres.setup.Test_postgres_async

object Test_RequiredAttrs extends RequiredAttrs with Test_postgres_async
