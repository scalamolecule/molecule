package molecule.sql.postgres.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_Adjacent extends Adjacent with TestAsync_postgres
object Test_CrossNs extends CrossNs with TestAsync_postgres
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_postgres
object Test_Types extends Types with TestAsync_postgres
