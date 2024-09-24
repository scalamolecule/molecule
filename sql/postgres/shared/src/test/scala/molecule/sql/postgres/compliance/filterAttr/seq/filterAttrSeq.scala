package molecule.sql.postgres.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_Adjacent extends Adjacent with Test_postgres_async
object Test_CrossNs extends CrossNs with Test_postgres_async
object Test_CrossNsOwned extends CrossNsOwned with Test_postgres_async
object Test_Types extends Types with Test_postgres_async
