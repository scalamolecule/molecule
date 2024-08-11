package molecule.datalog.datomic.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_Adjacent extends Adjacent with TestAsync_datomic
object Test_CrossNs extends CrossNs with TestAsync_datomic
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_datomic
object Test_Types extends Types with TestAsync_datomic
