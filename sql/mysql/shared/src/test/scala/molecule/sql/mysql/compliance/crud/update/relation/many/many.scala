package molecule.sql.mysql.compliance.crud.update.relation.many

import molecule.coreTests.spi.crud.update.relation.many._
import molecule.sql.mysql.setup.TestAsync_mysql

object Many_Map extends Many_Map with TestAsync_mysql
object Many_Map_add extends Many_Map_add with TestAsync_mysql
object Many_Map_remove extends Many_Map_remove with TestAsync_mysql
object Many_One extends Many_One with TestAsync_mysql
object Many_Seq extends Many_Seq with TestAsync_mysql
object Many_Seq_add extends Many_Seq_add with TestAsync_mysql
object Many_Seq_remove extends Many_Seq_remove with TestAsync_mysql
object Many_Set extends Many_Set with TestAsync_mysql
object Many_Set_add extends Many_Set_add with TestAsync_mysql
object Many_Set_remove extends Many_Set_remove with TestAsync_mysql
