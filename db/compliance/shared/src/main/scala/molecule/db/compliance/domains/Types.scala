package molecule.db.compliance.domains

import molecule.DomainStructure

object Types extends DomainStructure(5) {

  trait Entity {
    val i    = oneInt
    val iSet = setInt
    val iSeq = seqInt
    val iMap = mapInt
    val s    = oneString
    val u    = oneInt.unique

    // Single values
    val string         = oneString
    val int            = oneInt
    val long           = oneLong
    val float          = oneFloat
    val double         = oneDouble
    val boolean        = oneBoolean
    val bigInt         = oneBigInt
    val bigDecimal     = oneBigDecimal
    val date           = oneDate
    val duration       = oneDuration
    val instant        = oneInstant
    val localDate      = oneLocalDate
    val localTime      = oneLocalTime
    val localDateTime  = oneLocalDateTime
    val offsetTime     = oneOffsetTime
    val offsetDateTime = oneOffsetDateTime
    val zonedDateTime  = oneZonedDateTime
    val uuid           = oneUUID
    val uri            = oneURI
    val byte           = oneByte
    val short          = oneShort
    val char           = oneChar

    val ref   = one[Ref]
    val other = one[Other]

    // Set
    val stringSet         = setString
    val intSet            = setInt
    val longSet           = setLong
    val floatSet          = setFloat
    val doubleSet         = setDouble
    val booleanSet        = setBoolean
    val bigIntSet         = setBigInt
    val bigDecimalSet     = setBigDecimal
    val dateSet           = setDate
    val durationSet       = setDuration
    val instantSet        = setInstant
    val localDateSet      = setLocalDate
    val localTimeSet      = setLocalTime
    val localDateTimeSet  = setLocalDateTime
    val offsetTimeSet     = setOffsetTime
    val offsetDateTimeSet = setOffsetDateTime
    val zonedDateTimeSet  = setZonedDateTime
    val uuidSet           = setUUID
    val uriSet            = setURI
    val byteSet           = setByte
    val shortSet          = setShort
    val charSet           = setChar

    val refs = many[Ref]

    // Seq
    val stringSeq         = seqString
    val intSeq            = seqInt
    val longSeq           = seqLong
    val floatSeq          = seqFloat
    val doubleSeq         = seqDouble
    val booleanSeq        = seqBoolean
    val bigIntSeq         = seqBigInt
    val bigDecimalSeq     = seqBigDecimal
    val dateSeq           = seqDate
    val durationSeq       = seqDuration
    val instantSeq        = seqInstant
    val localDateSeq      = seqLocalDate
    val localTimeSeq      = seqLocalTime
    val localDateTimeSeq  = seqLocalDateTime
    val offsetTimeSeq     = seqOffsetTime
    val offsetDateTimeSeq = seqOffsetDateTime
    val zonedDateTimeSeq  = seqZonedDateTime
    val uuidSeq           = seqUUID
    val uriSeq            = seqURI
    val byteArray         = arrayByte
    val shortSeq          = seqShort
    val charSeq           = seqChar

    // Map
    val stringMap         = mapString
    val intMap            = mapInt
    val longMap           = mapLong
    val floatMap          = mapFloat
    val doubleMap         = mapDouble
    val booleanMap        = mapBoolean
    val bigIntMap         = mapBigInt
    val bigDecimalMap     = mapBigDecimal
    val dateMap           = mapDate
    val durationMap       = mapDuration
    val instantMap        = mapInstant
    val localDateMap      = mapLocalDate
    val localTimeMap      = mapLocalTime
    val localDateTimeMap  = mapLocalDateTime
    val offsetTimeMap     = mapOffsetTime
    val offsetDateTimeMap = mapOffsetDateTime
    val zonedDateTimeMap  = mapZonedDateTime
    val uuidMap           = mapUUID
    val uriMap            = mapURI
    val byteMap           = mapByte
    val shortMap          = mapShort
    val charMap           = mapChar
  }

  trait Ref {
    val i = oneInt
    val s = oneString

    val string         = oneString
    val int            = oneInt
    val long           = oneLong
    val float          = oneFloat
    val double         = oneDouble
    val boolean        = oneBoolean
    val bigInt         = oneBigInt
    val bigDecimal     = oneBigDecimal
    val date           = oneDate
    val duration       = oneDuration
    val instant        = oneInstant
    val localDate      = oneLocalDate
    val localTime      = oneLocalTime
    val localDateTime  = oneLocalDateTime
    val offsetTime     = oneOffsetTime
    val offsetDateTime = oneOffsetDateTime
    val zonedDateTime  = oneZonedDateTime
    val uuid           = oneUUID
    val uri            = oneURI
    val byte           = oneByte
    val short          = oneShort
    val char           = oneChar

    val iSet = setInt
    val iSeq = seqInt
    val sSet = setString

    val stringSet         = setString
    val intSet            = setInt
    val longSet           = setLong
    val floatSet          = setFloat
    val doubleSet         = setDouble
    val booleanSet        = setBoolean
    val bigIntSet         = setBigInt
    val bigDecimalSet     = setBigDecimal
    val dateSet           = setDate
    val durationSet       = setDuration
    val instantSet        = setInstant
    val localDateSet      = setLocalDate
    val localTimeSet      = setLocalTime
    val localDateTimeSet  = setLocalDateTime
    val offsetTimeSet     = setOffsetTime
    val offsetDateTimeSet = setOffsetDateTime
    val zonedDateTimeSet  = setZonedDateTime
    val uuidSet           = setUUID
    val uriSet            = setURI
    val byteSet           = setByte
    val shortSet          = setShort
    val charSet           = setChar

    val stringSeq         = seqString
    val intSeq            = seqInt
    val longSeq           = seqLong
    val floatSeq          = seqFloat
    val doubleSeq         = seqDouble
    val booleanSeq        = seqBoolean
    val bigIntSeq         = seqBigInt
    val bigDecimalSeq     = seqBigDecimal
    val dateSeq           = seqDate
    val durationSeq       = seqDuration
    val instantSeq        = seqInstant
    val localDateSeq      = seqLocalDate
    val localTimeSeq      = seqLocalTime
    val localDateTimeSeq  = seqLocalDateTime
    val offsetTimeSeq     = seqOffsetTime
    val offsetDateTimeSeq = seqOffsetDateTime
    val zonedDateTimeSeq  = seqZonedDateTime
    val uuidSeq           = seqUUID
    val uriSeq            = seqURI
    val byteArray         = arrayByte
    val shortSeq          = seqShort
    val charSeq           = seqChar

    val entities = many[Entity]
  }

  trait Other {
    val i = oneInt
    val s = oneString

    val iSet = setInt
    val sSet = setString

    // 'select' is a reserved keyword in all sql databases.
    // Test if it correctly resolves to 'select_'
    val select = oneInt
  }
}