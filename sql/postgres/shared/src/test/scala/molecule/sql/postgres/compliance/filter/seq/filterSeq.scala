package molecule.sql.postgres.compliance.filter.seq

import molecule.coreTests.spi.filter.seq.SeqSemantics
import molecule.coreTests.spi.filter.seq.ref._
import molecule.coreTests.spi.filter.seq.types._
import molecule.sql.postgres.setup.{TestSuite_postgres_array, Test_postgres_async}
import molecule.sql.postgres.spi.Spi_postgres_async

object Test_FilterSeq_String_ extends FilterSeq_String_ with Test_postgres_async
object Test_FilterSeq_Int extends FilterSeq_Int with Test_postgres_async
object Test_FilterSeq_Long_ extends FilterSeq_Long_ with Test_postgres_async
object Test_FilterSeq_Float_ extends FilterSeq_Float_ with Test_postgres_async
object Test_FilterSeq_Double_ extends FilterSeq_Double_ with Test_postgres_async
object Test_FilterSeq_Boolean extends FilterSeq_Boolean with Test_postgres_async
object Test_FilterSeq_BigInt_ extends FilterSeq_BigInt_ with Test_postgres_async
object Test_FilterSeq_BigDecimal_ extends FilterSeq_BigDecimal_ with Test_postgres_async
object Test_FilterSeq_Date_ extends FilterSeq_Date_ with Test_postgres_async
object Test_FilterSeq_Duration_ extends FilterSeq_Duration_ with Test_postgres_async
object Test_FilterSeq_Instant_ extends FilterSeq_Instant_ with Test_postgres_async
object Test_FilterSeq_LocalDate_ extends FilterSeq_LocalDate_ with Test_postgres_async
object Test_FilterSeq_LocalTime_ extends FilterSeq_LocalTime_ with Test_postgres_async
object Test_FilterSeq_LocalDateTime_ extends FilterSeq_LocalDateTime_ with Test_postgres_async
object Test_FilterSeq_OffsetTime_ extends FilterSeq_OffsetTime_ with Test_postgres_async
object Test_FilterSeq_OffsetDateTime_ extends FilterSeq_OffsetDateTime_ with Test_postgres_async
object Test_FilterSeq_ZonedDateTime_ extends FilterSeq_ZonedDateTime_ with Test_postgres_async
object Test_FilterSeq_UUID_ extends FilterSeq_UUID_ with Test_postgres_async
object Test_FilterSeq_URI_ extends FilterSeq_URI_ with Test_postgres_async
object Test_FilterSeq_Byte_ extends FilterSeq_ByteArray with TestSuite_postgres_array with Spi_postgres_async
object Test_FilterSeq_Short_ extends FilterSeq_Short_ with Test_postgres_async
object Test_FilterSeq_Char_ extends FilterSeq_Char_ with Test_postgres_async

object Test_SeqSemantics extends SeqSemantics with Test_postgres_async


object Test_FilterRefSeq_Card1Ref extends FilterRefSeq_Card1Ref with Test_postgres_async
object Test_FilterRefSeq_Card1RefOwned extends FilterRefSeq_Card1RefOwned with Test_postgres_async
object Test_FilterRefSeq_Card2Ref extends FilterRefSeq_Card2Ref with Test_postgres_async
object Test_FilterRefSeq_Card2RefOwned extends FilterRefSeq_Card2RefOwned with Test_postgres_async
