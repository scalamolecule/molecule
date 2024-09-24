package molecule.datalog.datomic.compliance.filter.set

import molecule.coreTests.spi.filter.set.ref._
import molecule.coreTests.spi.filter.set.types._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_FilterSet_String_ extends FilterSet_String_ with Test_datomic_async
object Test_FilterSet_Int extends FilterSet_Int with Test_datomic_async
object Test_FilterSet_Long_ extends FilterSet_Long_ with Test_datomic_async
object Test_FilterSet_Float_ extends FilterSet_Float_ with Test_datomic_async
object Test_FilterSet_Double_ extends FilterSet_Double_ with Test_datomic_async
object Test_FilterSet_Boolean extends FilterSet_Boolean with Test_datomic_async
object Test_FilterSet_BigInt_ extends FilterSet_BigInt_ with Test_datomic_async
object Test_FilterSet_BigDecimal_ extends FilterSet_BigDecimal_ with Test_datomic_async
object Test_FilterSet_Date_ extends FilterSet_Date_ with Test_datomic_async
object Test_FilterSet_Duration_ extends FilterSet_Duration_ with Test_datomic_async
object Test_FilterSet_Instant_ extends FilterSet_Instant_ with Test_datomic_async
object Test_FilterSet_LocalDate_ extends FilterSet_LocalDate_ with Test_datomic_async
object Test_FilterSet_LocalTime_ extends FilterSet_LocalTime_ with Test_datomic_async
object Test_FilterSet_LocalDateTime_ extends FilterSet_LocalDateTime_ with Test_datomic_async
object Test_FilterSet_OffsetTime_ extends FilterSet_OffsetTime_ with Test_datomic_async
object Test_FilterSet_OffsetDateTime_ extends FilterSet_OffsetDateTime_ with Test_datomic_async
object Test_FilterSet_ZonedDateTime_ extends FilterSet_ZonedDateTime_ with Test_datomic_async
object Test_FilterSet_UUID_ extends FilterSet_UUID_ with Test_datomic_async
object Test_FilterSet_URI_ extends FilterSet_URI_ with Test_datomic_async
object Test_FilterSet_Byte_ extends FilterSet_Byte_ with Test_datomic_async
object Test_FilterSet_Short_ extends FilterSet_Short_ with Test_datomic_async
object Test_FilterSet_Char_ extends FilterSet_Char_ with Test_datomic_async

object Test_FilterSet_ref_ extends FilterSet_ref with Test_datomic_async

object Test_FilterRefSet_Card1Ref extends FilterRefSet_Card1Ref with Test_datomic_async
object Test_FilterRefSet_Card1RefOwned extends FilterRefSet_Card1RefOwned with Test_datomic_async
object Test_FilterRefSet_Card2Ref extends FilterRefSet_Card2Ref with Test_datomic_async
object Test_FilterRefSet_Card2RefOwned extends FilterRefSet_Card2RefOwned with Test_datomic_async
