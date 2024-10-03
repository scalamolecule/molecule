package molecule.sql.mysql.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_Adjacent extends Adjacent with Test_mysql_async
object Test_CrossNs extends CrossNs with Test_mysql_async
object Test_FilterAttr_id extends FilterAttr_id with Test_mysql_async
object Test_FilterAttrNested extends FilterAttrNested with Test_mysql_async
object Test_FilterAttrRef extends FilterAttrRef with Test_mysql_async
object Test_Semantics extends Semantics with Test_mysql_async
object Test_Sorting extends Sorting with Test_mysql_async
object Test_Types extends Types with Test_mysql_async
