package molecule.datalog.datomic.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_Adjacent extends Adjacent with Test_datomic_async
object Test_CrossNs extends CrossNs with Test_datomic_async
object Test_CrossNsOwned extends CrossNsOwned with Test_datomic_async
object Test_Types extends Types with Test_datomic_async
