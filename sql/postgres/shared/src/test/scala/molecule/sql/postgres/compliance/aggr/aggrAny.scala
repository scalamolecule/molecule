package molecule.sql.postgres.compliance.aggr

import molecule.coreTests.spi.aggr.any.{Aggr_BigDecimal_, Aggr_BigInt_, Aggr_Boolean, Aggr_Byte_, Aggr_Char_, Aggr_Date_, Aggr_Double_, Aggr_Duration_, Aggr_Float_, Aggr_Instant_, Aggr_Int, Aggr_LocalDateTime_, Aggr_LocalDate_, Aggr_LocalTime_, Aggr_Long_, Aggr_OffsetDateTime_, Aggr_OffsetTime_, Aggr_Short_, Aggr_String_, Aggr_URI_, Aggr_UUID_, Aggr_ZonedDateTime_, Aggr_ref_}
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_AggrOne_String extends Aggr_String_ with TestAsync_postgres
object Test_AggrOne_Int extends Aggr_Int with TestAsync_postgres
object Test_AggrOne_Long extends Aggr_Long_ with TestAsync_postgres
object Test_AggrOne_Float extends Aggr_Float_ with TestAsync_postgres
object Test_AggrOne_Double extends Aggr_Double_ with TestAsync_postgres
object Test_AggrOne_Boolean extends Aggr_Boolean with TestAsync_postgres
object Test_AggrOne_BigInt extends Aggr_BigInt_ with TestAsync_postgres
object Test_AggrOne_BigDecimal extends Aggr_BigDecimal_ with TestAsync_postgres
object Test_AggrOne_Date extends Aggr_Date_ with TestAsync_postgres
object Test_AggrOne_Duration extends Aggr_Duration_ with TestAsync_postgres
object Test_AggrOne_Instant extends Aggr_Instant_ with TestAsync_postgres
object Test_AggrOne_LocalDate extends Aggr_LocalDate_ with TestAsync_postgres
object Test_AggrOne_LocalTime extends Aggr_LocalTime_ with TestAsync_postgres
object Test_AggrOne_LocalDateTime extends Aggr_LocalDateTime_ with TestAsync_postgres
object Test_AggrOne_OffsetTime extends Aggr_OffsetTime_ with TestAsync_postgres
object Test_AggrOne_OffsetDateTime extends Aggr_OffsetDateTime_ with TestAsync_postgres
object Test_AggrOne_ZonedDateTime extends Aggr_ZonedDateTime_ with TestAsync_postgres
object Test_AggrOne_UUID extends Aggr_UUID_ with TestAsync_postgres
object Test_AggrOne_URI extends Aggr_URI_ with TestAsync_postgres
object Test_AggrOne_Byte extends Aggr_Byte_ with TestAsync_postgres
object Test_AggrOne_Short extends Aggr_Short_ with TestAsync_postgres
object Test_AggrOne_Char extends Aggr_Char_ with TestAsync_postgres

object Test_AggrOne_ref extends Aggr_ref_ with TestAsync_postgres
