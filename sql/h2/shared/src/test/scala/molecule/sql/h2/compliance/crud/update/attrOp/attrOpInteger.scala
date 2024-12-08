package molecule.sql.h2.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp.number._
import molecule.sql.h2.setup.Test_h2_async


object Test_AttrOpSpecial_Int extends AttrOpInteger_Int with Test_h2_async
object Test_AttrOpSpecial_Long_ extends AttrOpInteger_Long_ with Test_h2_async
object Test_AttrOpSpecial_BigInt_ extends AttrOpInteger_BigInt with Test_h2_async
object Test_AttrOpSpecial_Byte_ extends AttrOpInteger_Byte_ with Test_h2_async
object Test_AttrOpSpecial_Short_ extends AttrOpInteger_Short_ with Test_h2_async


