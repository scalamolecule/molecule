package molecule.sql.postgres.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_Adjacent extends Adjacent with Test_postgres_async
object Test_CrossNs extends CrossNs with Test_postgres_async
object Test_Types extends Types with Test_postgres_async
