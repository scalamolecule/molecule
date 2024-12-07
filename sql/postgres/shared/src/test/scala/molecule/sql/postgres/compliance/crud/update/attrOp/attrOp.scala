package molecule.sql.postgres.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp._
import molecule.sql.postgres.setup.Test_postgres_async


object Test_AttrOp_Boolean extends AttrOp_Boolean with Test_postgres_async
object Test_AttrOp_String extends AttrOp_String with Test_postgres_async

