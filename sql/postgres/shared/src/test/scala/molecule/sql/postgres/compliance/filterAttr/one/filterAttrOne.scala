package molecule.sql.postgres.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_Adjacent extends Adjacent with TestAsync_postgres
object Test_CrossNs extends CrossNs with TestAsync_postgres
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_postgres
object Test_FilterAttr_id extends FilterAttr_id with TestAsync_postgres
object Test_FilterAttrNested extends FilterAttrNested with TestAsync_postgres
object Test_FilterAttrRef extends FilterAttrRef with TestAsync_postgres
object Test_Semantics extends Semantics with TestAsync_postgres
object Test_Sorting extends Sorting with TestAsync_postgres
object Test_Types extends Types with TestAsync_postgres
