//package molecule.sql.postgres.compliance.filter.seq
//
//import molecule.coreTests.spi.filter.seq.ref._
//import molecule.coreTests.spi.filter.seq.types._
//import molecule.sql.postgres.setup.{TestAsync_postgres, TestSuiteArray_postgres}
//import molecule.sql.postgres.spi.SpiAsync_postgres
//
//object FilterSeq_String_ extends FilterSeq_String_ with TestAsync_postgres
//object FilterSeq_Int extends FilterSeq_Int with TestAsync_postgres
//object FilterSeq_Long_ extends FilterSeq_Long_ with TestAsync_postgres
//object FilterSeq_Float_ extends FilterSeq_Float_ with TestAsync_postgres
//object FilterSeq_Double_ extends FilterSeq_Double_ with TestAsync_postgres
//object FilterSeq_Boolean extends FilterSeq_Boolean with TestAsync_postgres
//object FilterSeq_BigInt_ extends FilterSeq_BigInt_ with TestAsync_postgres
//object FilterSeq_BigDecimal_ extends FilterSeq_BigDecimal_ with TestAsync_postgres
//object FilterSeq_Date_ extends FilterSeq_Date_ with TestAsync_postgres
//object FilterSeq_Duration_ extends FilterSeq_Duration_ with TestAsync_postgres
//object FilterSeq_Instant_ extends FilterSeq_Instant_ with TestAsync_postgres
//object FilterSeq_LocalDate_ extends FilterSeq_LocalDate_ with TestAsync_postgres
//object FilterSeq_LocalTime_ extends FilterSeq_LocalTime_ with TestAsync_postgres
//object FilterSeq_LocalDateTime_ extends FilterSeq_LocalDateTime_ with TestAsync_postgres
//object FilterSeq_OffsetTime_ extends FilterSeq_OffsetTime_ with TestAsync_postgres
//object FilterSeq_OffsetDateTime_ extends FilterSeq_OffsetDateTime_ with TestAsync_postgres
//object FilterSeq_ZonedDateTime_ extends FilterSeq_ZonedDateTime_ with TestAsync_postgres
//object FilterSeq_UUID_ extends FilterSeq_UUID_ with TestAsync_postgres
//object FilterSeq_URI_ extends FilterSeq_URI_ with TestAsync_postgres
//object FilterSeq_Byte_ extends FilterSeq_ByteArray with TestSuiteArray_postgres with SpiAsync_postgres
//object FilterSeq_Short_ extends FilterSeq_Short_ with TestAsync_postgres
//object FilterSeq_Char_ extends FilterSeq_Char_ with TestAsync_postgres
//
//
//object FilterRefSeq_base extends FilterRefSeq_base with TestAsync_postgres
//object FilterRefSeq_Card1Ref extends FilterRefSeq_Card1Ref with TestAsync_postgres
//object FilterRefSeq_Card1RefOwned extends FilterRefSeq_Card1RefOwned with TestAsync_postgres
//object FilterRefSeq_Card2Ref extends FilterRefSeq_Card2Ref with TestAsync_postgres
//object FilterRefSeq_Card2RefOwned extends FilterRefSeq_Card2RefOwned with TestAsync_postgres
