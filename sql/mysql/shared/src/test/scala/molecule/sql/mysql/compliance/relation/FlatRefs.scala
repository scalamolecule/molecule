package molecule.sql.mysql.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_FlatRefs extends FlatRef with Test_mysql_async
object Test_FlatRefOpt extends FlatRefOpt with Test_mysql_async
object Test_FlatRefOptNested extends FlatRefOptNested with Test_mysql_async
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with Test_mysql_async
