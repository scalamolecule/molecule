package molecule.datalog.datomic.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp.number._
import molecule.datalog.datomic.setup.Test_datomic_async


object Test_AttrOpSpecial_Int extends AttrOpInteger_Int with Test_datomic_async
object Test_AttrOpSpecial_Long_ extends AttrOpInteger_Long_ with Test_datomic_async
object Test_AttrOpSpecial_BigInt_ extends AttrOpInteger_BigInt with Test_datomic_async
object Test_AttrOpSpecial_Byte_ extends AttrOpInteger_Byte_ with Test_datomic_async
object Test_AttrOpSpecial_Short_ extends AttrOpInteger_Short_ with Test_datomic_async

