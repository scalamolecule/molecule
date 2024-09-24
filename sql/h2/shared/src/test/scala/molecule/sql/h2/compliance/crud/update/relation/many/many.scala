package molecule.sql.h2.compliance.crud.update.relation.many

import molecule.coreTests.spi.crud.update.relation.many._
import molecule.sql.h2.setup.Test_h2_async

object Test_Many_Map extends Many_Map with Test_h2_async
object Test_Many_Map_add extends Many_Map_add with Test_h2_async
object Test_Many_Map_remove extends Many_Map_remove with Test_h2_async
object Test_Many_One extends Many_One with Test_h2_async
object Test_Many_Seq extends Many_Seq with Test_h2_async
object Test_Many_Seq_add extends Many_Seq_add with Test_h2_async
object Test_Many_Seq_remove extends Many_Seq_remove with Test_h2_async
object Test_Many_Set extends Many_Set with Test_h2_async
object Test_Many_Set_add extends Many_Set_add with Test_h2_async
object Test_Many_Set_remove extends Many_Set_remove with Test_h2_async
