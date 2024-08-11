package molecule.sql.sqlite.compliance.crud.update.relation.many

import molecule.coreTests.spi.crud.update.relation.many._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_Many_Map extends Many_Map with TestAsync_sqlite
object Test_Many_Map_add extends Many_Map_add with TestAsync_sqlite
object Test_Many_Map_remove extends Many_Map_remove with TestAsync_sqlite
object Test_Many_One extends Many_One with TestAsync_sqlite
object Test_Many_Seq extends Many_Seq with TestAsync_sqlite
object Test_Many_Seq_add extends Many_Seq_add with TestAsync_sqlite
object Test_Many_Seq_remove extends Many_Seq_remove with TestAsync_sqlite
object Test_Many_Set extends Many_Set with TestAsync_sqlite
object Test_Many_Set_add extends Many_Set_add with TestAsync_sqlite
object Test_Many_Set_remove extends Many_Set_remove with TestAsync_sqlite
