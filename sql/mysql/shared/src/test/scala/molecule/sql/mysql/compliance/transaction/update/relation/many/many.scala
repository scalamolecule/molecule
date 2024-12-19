package molecule.sql.mysql.compliance.transaction.update.relation.many

import molecule.coreTests.spi.transaction.update.relation.many._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_Many_Map extends Many_Map with Test_mysql_async
object Test_Many_Map_add extends Many_Map_add with Test_mysql_async
object Test_Many_Map_remove extends Many_Map_remove with Test_mysql_async
object Test_Many_One extends Many_One with Test_mysql_async
object Test_Many_Seq extends Many_Seq with Test_mysql_async
object Test_Many_Seq_add extends Many_Seq_add with Test_mysql_async
object Test_Many_Seq_remove extends Many_Seq_remove with Test_mysql_async
object Test_Many_Set extends Many_Set with Test_mysql_async
object Test_Many_Set_add extends Many_Set_add with Test_mysql_async
object Test_Many_Set_remove extends Many_Set_remove with Test_mysql_async
