package molecule.sql.postgres.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_Adjacent extends Adjacent with TestAsync_postgres
object Test_CrossNs extends CrossNs with TestAsync_postgres
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_postgres
object Test_Types extends Types with TestAsync_postgres
