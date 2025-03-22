///*
//* AUTO-GENERATED schema boilerplate code
//*
//* To change:
//* 1. edit domain definition file in `molecule.coreTests.domains/`
//* 2. `sbt compile -Dmolecule=true`
//*/
//package molecule.sql.postgres.setup
//
//import molecule.base.api.Schema
//import molecule.base.ast._
//
//
//trait ValidationSchema2 { _: Schema =>
//
//  override val metaDomain: MetaDomain =
//    MetaDomain("molecule.coreTests.domains", "Validation", 5, Seq(
//      MetaSegment("", Seq(
//        MetaEntity("Strings", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("email", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """(s: String) => emailRegex.findFirstMatchIn(s).isDefined""",
//              """`$v` is not a valid email"""
//            ))),
//          MetaAttribute("emailWithMsg", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """(s: String) => emailRegex.findFirstMatchIn(s).isDefined""",
//              """Please provide a real email"""
//            ))),
//          MetaAttribute("regex", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """(s: String) => "^[a-zA-Z0-9]+$".r.findFirstMatchIn(s).isDefined""",
//              """"$v" doesn't match regex pattern: ^[a-zA-Z0-9]+$$"""
//            ))),
//          MetaAttribute("regexWithMsg", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """(s: String) => "^[a-zA-Z0-9]+$".r.findFirstMatchIn(s).isDefined""",
//              """Username cannot contain special characters."""
//            ))),
//          MetaAttribute("enums", CardSet, "ID", Some("Enum"), Nil, None, None, Nil, Nil, Nil)
//        ), Seq("Type"), Seq(), Seq()),
//
//        MetaEntity("Enum", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("luckyNumber", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """v => Seq(7, 9, 13).contains(v)""",
//              """Value `$v` is not one of the allowed values in Seq(7, 9, 13)"""
//            ))),
//          MetaAttribute("luckyNumber2", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """v => Seq(7, 9, 13).contains(v)""",
//              """Lucky number can only be 7, 9 or 13"""
//            )))
//        ), Seq("Require", "Strings"), Seq(), Seq()),
//
//        MetaEntity("Type", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("string", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > "b"""",
//              ""
//            ))),
//          MetaAttribute("int", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2""",
//              ""
//            ))),
//          MetaAttribute("long", CardOne, "Long", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2L""",
//              ""
//            ))),
//          MetaAttribute("float", CardOne, "Float", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2.2f""",
//              ""
//            ))),
//          MetaAttribute("double", CardOne, "Double", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2.2""",
//              ""
//            ))),
//          MetaAttribute("boolean", CardOne, "Boolean", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ == false""",
//              ""
//            ))),
//          MetaAttribute("bigInt", CardOne, "BigInt", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > BigInt(2)""",
//              ""
//            ))),
//          MetaAttribute("bigDecimal", CardOne, "BigDecimal", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > BigDecimal(2.2)""",
//              ""
//            ))),
//          MetaAttribute("date", CardOne, "Date", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.after(new Date(993942000000L))""",
//              ""
//            ))),
//          MetaAttribute("duration", CardOne, "Duration", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(Duration.ofMinutes(2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("instant", CardOne, "Instant", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(Instant.ofEpochSecond(2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localDate", CardOne, "LocalDate", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalDate.of(2002, 1, 1)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localTime", CardOne, "LocalTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalTime.of(2, 2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localDateTime", CardOne, "LocalDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("offsetTime", CardOne, "OffsetTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("offsetDateTime", CardOne, "OffsetDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("zonedDateTime", CardOne, "ZonedDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("uuid", CardOne, "UUID", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"""",
//              ""
//            ))),
//          MetaAttribute("uri", CardOne, "URI", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.toString.length > 2""",
//              ""
//            ))),
//          MetaAttribute("byte", CardOne, "Byte", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2""",
//              ""
//            ))),
//          MetaAttribute("short", CardOne, "Short", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2""",
//              ""
//            ))),
//          MetaAttribute("char", CardOne, "Char", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 'b'""",
//              ""
//            ))),
//          MetaAttribute("ref", CardOne, "ID", Some("Strings"), Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("stringSet", CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > "c"""",
//              ""
//            ))),
//          MetaAttribute("intSet", CardSet, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3""",
//              ""
//            ))),
//          MetaAttribute("longSet", CardSet, "Long", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3L""",
//              ""
//            ))),
//          MetaAttribute("floatSet", CardSet, "Float", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3.3f""",
//              ""
//            ))),
//          MetaAttribute("doubleSet", CardSet, "Double", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3.3""",
//              ""
//            ))),
//          MetaAttribute("booleanSet", CardSet, "Boolean", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ == false""",
//              ""
//            ))),
//          MetaAttribute("bigIntSet", CardSet, "BigInt", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > BigInt(3)""",
//              ""
//            ))),
//          MetaAttribute("bigDecimalSet", CardSet, "BigDecimal", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > BigDecimal(3.3)""",
//              ""
//            ))),
//          MetaAttribute("dateSet", CardSet, "Date", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.after(new Date(1057010400000L))""",
//              ""
//            ))),
//          MetaAttribute("durationSet", CardSet, "Duration", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(Duration.ofMinutes(2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("instantSet", CardSet, "Instant", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(Instant.ofEpochSecond(2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localDateSet", CardSet, "LocalDate", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalDate.of(2002, 1, 1)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localTimeSet", CardSet, "LocalTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalTime.of(2, 2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localDateTimeSet", CardSet, "LocalDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("offsetTimeSet", CardSet, "OffsetTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("offsetDateTimeSet", CardSet, "OffsetDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("zonedDateTimeSet", CardSet, "ZonedDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("uuidSet", CardSet, "UUID", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"""",
//              ""
//            ))),
//          MetaAttribute("uriSet", CardSet, "URI", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.toString.length > 3""",
//              ""
//            ))),
//          MetaAttribute("byteSet", CardSet, "Byte", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3""",
//              ""
//            ))),
//          MetaAttribute("shortSet", CardSet, "Short", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3""",
//              ""
//            ))),
//          MetaAttribute("charSet", CardSet, "Char", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 'c'""",
//              ""
//            ))),
//          MetaAttribute("refs", CardSet, "ID", Some("Strings"), Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("stringSeq", CardSeq, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > "c"""",
//              ""
//            ))),
//          MetaAttribute("intSeq", CardSeq, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3""",
//              ""
//            ))),
//          MetaAttribute("longSeq", CardSeq, "Long", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3L""",
//              ""
//            ))),
//          MetaAttribute("floatSeq", CardSeq, "Float", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3.3f""",
//              ""
//            ))),
//          MetaAttribute("doubleSeq", CardSeq, "Double", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3.3""",
//              ""
//            ))),
//          MetaAttribute("booleanSeq", CardSeq, "Boolean", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ == false""",
//              ""
//            ))),
//          MetaAttribute("bigIntSeq", CardSeq, "BigInt", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > BigInt(3)""",
//              ""
//            ))),
//          MetaAttribute("bigDecimalSeq", CardSeq, "BigDecimal", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > BigDecimal(3.3)""",
//              ""
//            ))),
//          MetaAttribute("dateSeq", CardSeq, "Date", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.after(new Date(1057010400000L))""",
//              ""
//            ))),
//          MetaAttribute("durationSeq", CardSeq, "Duration", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(Duration.ofMinutes(2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("instantSeq", CardSeq, "Instant", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(Instant.ofEpochSecond(2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localDateSeq", CardSeq, "LocalDate", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalDate.of(2002, 1, 1)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localTimeSeq", CardSeq, "LocalTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalTime.of(2, 2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("localDateTimeSeq", CardSeq, "LocalDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0""",
//              ""
//            ))),
//          MetaAttribute("offsetTimeSeq", CardSeq, "OffsetTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("offsetDateTimeSeq", CardSeq, "OffsetDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("zonedDateTimeSeq", CardSeq, "ZonedDateTime", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//              ""
//            ))),
//          MetaAttribute("uuidSeq", CardSeq, "UUID", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"""",
//              ""
//            ))),
//          MetaAttribute("uriSeq", CardSeq, "URI", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.toString.length > 3""",
//              ""
//            ))),
//          MetaAttribute("shortSeq", CardSeq, "Short", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 3""",
//              ""
//            ))),
//          MetaAttribute("charSeq", CardSeq, "Char", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 'c'""",
//              ""
//            )))
//        ), Seq(), Seq(), Seq()),
//
//        MetaEntity("Constants", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("noErrorMsg", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2""",
//              ""
//            ))),
//          MetaAttribute("errorMsg", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2""",
//              """One-line error msg"""
//            ))),
//          MetaAttribute("errorMsgWithValue", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_ > 2""",
//              """One-line error msg. Found $v"""
//            ))),
//          MetaAttribute("errorMsgWithValueQuoted", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.startsWith("hello")""",
//              """Expected hello. Found "$v"."""
//            ))),
//          MetaAttribute("errorMsgWithValueQuoted2", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """_.startsWith("hello")""",
//              """Expected hello. Found "$v"."""
//            ))),
//          MetaAttribute("multilineErrorMsg", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """(v: Int) => v.>(2)""",
//              """Long error explanation
//with multiple lines"""
//            ))),
//          MetaAttribute("multilineMsgWithValue", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """(v: Int) => v.>(2)""",
//              """Validation failed:
//Input value $v is not bigger than 2."""
//            ))),
//          MetaAttribute("multilineMsgWithValue2", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """(v: Int) => v.>(2)""",
//              """Validation failed:
//Input value "$v" is not bigger than 2."""
//            ))),
//          MetaAttribute("multiLine", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """v =>
//val data   = 22
//val result = data % 10
//v > result""",
//              ""
//            ))),
//          MetaAttribute("multiLine2", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """{ v =>
//  val data   = 22
//  val result = {
//    data % 10
//  }
//  v > result
//}""",
//              """One-line error msg"""
//            ))),
//          MetaAttribute("multiLine3", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """{ v =>
//  val data   = 22
//  val result = data % 10
//  v > result
//}""",
//              """Long error explanation
//with multiple lines"""
//            ))),
//          MetaAttribute("logic", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """v => v >= 3 && v <= 9 && v != 7 && v % 2 == 1""",
//              """Value must be an odd number between 3 and 9 but not 7"""
//            ))),
//          MetaAttribute("multipleErrors", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//            (
//              """v => v > 2""",
//              """Test 1: Number must be bigger than 2. Found: $v"""
//            ),
//            (
//              """v => v < 10""",
//              """Test 2: Number must be smaller than 10. Found: $v"""
//            ),
//            (
//              """v => v != 7""",
//              """Test 3: Number must not be 7"""
//            ),
//            (
//              """v => {
//  // Comments in code blocks are transferred to boilerplate code
//  val divider = 2
//  v % divider == 1
//}""",
//              """Test 4: Number must
//be odd. Found: $v"""
//            )))
//        ), Seq(), Seq(), Seq()),
//
//        MetaEntity("Variables", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("int", CardOne, "Int", None, Nil, None, None, Seq("noErrorMsg"), Nil, Nil),
//          MetaAttribute("noErrorMsg", CardOne, "Int", None, Nil, None, None, Seq("int"), Seq("int"), Seq(
//            (
//              """_ > int""",
//              ""
//            ))),
//          MetaAttribute("int1", CardOne, "Int", None, Nil, None, None, Seq("errorMsg"), Nil, Nil),
//          MetaAttribute("errorMsg", CardOne, "Int", None, Nil, None, None, Seq("int1"), Seq("int1"), Seq(
//            (
//              """_ > int1""",
//              """One-line error msg"""
//            ))),
//          MetaAttribute("int2", CardOne, "Int", None, Nil, None, None, Seq("errorMsgWithValue"), Nil, Nil),
//          MetaAttribute("errorMsgWithValue", CardOne, "Int", None, Nil, None, None, Seq("int2"), Seq("int2"), Seq(
//            (
//              """_ > int2""",
//              """One-line error msg. Found $v"""
//            ))),
//          MetaAttribute("int3", CardOne, "Int", None, Nil, None, None, Seq("multilineMsg"), Nil, Nil),
//          MetaAttribute("multilineMsg", CardOne, "Int", None, Nil, None, None, Seq("int3"), Seq("int3"), Seq(
//            (
//              """(v: Int) => v.>(int3)""",
//              """Validation failed:
//Input value `$v` is not bigger than `int3` value `$int3`."""
//            ))),
//          MetaAttribute("int4", CardOne, "Int", None, Nil, None, None, Seq("multiLine"), Nil, Nil),
//          MetaAttribute("multiLine", CardOne, "Int", None, Nil, None, None, Seq("int4"), Seq("int4"), Seq(
//            (
//              """v =>
//val data   = 22
//val result = data % int4
//v > result""",
//              ""
//            ))),
//          MetaAttribute("int5", CardOne, "Int", None, Nil, None, None, Seq("multiLine2"), Nil, Nil),
//          MetaAttribute("multiLine2", CardOne, "Int", None, Nil, None, None, Seq("int5"), Seq("int5"), Seq(
//            (
//              """{ v =>
//  val data   = 22
//  val result = {
//    data % int5
//  }
//  v > result
//}""",
//              """One-line error msg"""
//            ))),
//          MetaAttribute("int6", CardOne, "Int", None, Nil, None, None, Seq("multiLine3"), Nil, Nil),
//          MetaAttribute("multiLine3", CardOne, "Int", None, Nil, None, None, Seq("int6"), Seq("int6"), Seq(
//            (
//              """{ v =>
//  val data   = 22
//  val result = data % int6
//  v > result
//}""",
//              """Long error explanation
//with multiple lines"""
//            ))),
//          MetaAttribute("int7", CardOne, "Int", None, Nil, None, None, Seq("logic"), Nil, Nil),
//          MetaAttribute("logic", CardOne, "Int", None, Nil, None, None, Seq("int7"), Seq("int7"), Seq(
//            (
//              """v => v >= 3 && v <= 9 && v != int7 && v % 2 == 1""",
//              """Value must be an odd number between 3 and 9 but not `int7` value `$int7`"""
//            ))),
//          MetaAttribute("int8", CardOne, "Int", None, Nil, None, None, Seq("intSet", "multipleErrors", "str", "strs"), Nil, Nil),
//          MetaAttribute("str", CardOne, "String", None, Nil, None, None, Seq("int8", "intSet", "multipleErrors", "strs"), Nil, Nil),
//          MetaAttribute("intSet", CardSet, "Int", None, Nil, None, None, Seq("int8", "multipleErrors", "str", "strs"), Nil, Nil),
//          MetaAttribute("strs", CardSet, "String", None, Nil, None, None, Seq("int8", "intSet", "multipleErrors", "str"), Nil, Nil),
//          MetaAttribute("multipleErrors", CardOne, "Int", None, Nil, None, None, Seq("int8", "intSet", "str", "strs"), Seq("int8", "intSet", "str", "strs"), Seq(
//            (
//              """v => v > 4""",
//              """Test 1: Number must be bigger than 4. Found: $v"""
//            ),
//            (
//              """v => v > int8""",
//              """Test 2: Number must be bigger than `int8` value `$int8`. Found: $v"""
//            ),
//            (
//              """v => v < str.length * 2""",
//              """Test 3: Number must be smaller than `str` value `$str` length `${str.length}` * 2. Found: $v"""
//            ),
//            (
//              """v => {
//  v != intSet.head - 3
//}""",
//              """Test 4: Number must not be `intSet` head value `${intSet.head}` minus 3. Found: $v"""
//            ),
//            (
//              """v => {
//  val divider = strs.size
//  v % divider == 1
//}""",
//              """Test 5: Number must
//be odd. Found: $v"""
//            )))
//        ), Seq(), Seq(), Seq()),
//
//        MetaEntity("MandatoryAttr", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("name", CardOne, "String", None, Seq("mandatory"), None, None, Nil, Nil, Nil),
//          MetaAttribute("hobbies", CardSet, "String", None, Seq("mandatory"), None, None, Nil, Nil, Nil),
//          MetaAttribute("age", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil)
//        ), Seq(), Seq("name", "hobbies"), Seq()),
//
//        MetaEntity("MandatoryRefAB", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("refA", CardOne, "ID", Some("RefA"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//        ), Seq(), Seq(), Seq("refA" -> "RefA")),
//
//        MetaEntity("MandatoryRefB", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("refB", CardOne, "ID", Some("RefB"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//        ), Seq(), Seq(), Seq("refB" -> "RefB")),
//
//        MetaEntity("MandatoryRefsAB", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("refsA", CardSet, "ID", Some("RefA"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//        ), Seq(), Seq(), Seq("refsA" -> "RefA")),
//
//        MetaEntity("MandatoryRefsB", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("refsB", CardSet, "ID", Some("RefB"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//        ), Seq(), Seq(), Seq("refsB" -> "RefB")),
//
//        MetaEntity("RefA", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("refB", CardOne, "ID", Some("RefB"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//        ), Seq("MandatoryRefAB", "MandatoryRefsAB"), Seq(), Seq("refB" -> "RefB")),
//
//        MetaEntity("RefB", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil)
//        ), Seq("MandatoryRefB", "MandatoryRefsB", "RefA", "Require"), Seq(), Seq()),
//
//        MetaEntity("Require", Seq(
//          MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//          MetaAttribute("username", CardOne, "String", None, Nil, None, None, Seq("password"), Nil, Nil),
//          MetaAttribute("password", CardOne, "String", None, Nil, None, None, Seq("username"), Nil, Nil),
//          MetaAttribute("x", CardOne, "Int", None, Nil, None, None, Seq("y", "z"), Nil, Nil),
//          MetaAttribute("y", CardOne, "Int", None, Nil, None, None, Seq("x", "z"), Nil, Nil),
//          MetaAttribute("z", CardOne, "Int", None, Nil, None, None, Seq("x", "y"), Nil, Nil),
//          MetaAttribute("int", CardOne, "Int", None, Nil, None, None, Seq("refB"), Nil, Nil),
//          MetaAttribute("refB", CardOne, "ID", Some("RefB"), Nil, None, None, Seq("int"), Nil, Nil),
//          MetaAttribute("ref1", CardOne, "ID", Some("RefB"), Nil, None, None, Seq("ref2"), Nil, Nil),
//          MetaAttribute("ref2", CardOne, "ID", Some("Enum"), Nil, None, None, Seq("ref1"), Nil, Nil)
//        ), Seq(), Seq(), Seq())
//      ))
//    ))
//
//
//  override val entityMap: Map[String, MetaEntity] = Map(
//    "Strings" ->
//      MetaEntity("Strings", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("email", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """(s: String) => emailRegex.findFirstMatchIn(s).isDefined""",
//            """`$v` is not a valid email"""
//          ))),
//        MetaAttribute("emailWithMsg", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """(s: String) => emailRegex.findFirstMatchIn(s).isDefined""",
//            """Please provide a real email"""
//          ))),
//        MetaAttribute("regex", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """(s: String) => "^[a-zA-Z0-9]+$".r.findFirstMatchIn(s).isDefined""",
//            """"$v" doesn't match regex pattern: ^[a-zA-Z0-9]+$$"""
//          ))),
//        MetaAttribute("regexWithMsg", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """(s: String) => "^[a-zA-Z0-9]+$".r.findFirstMatchIn(s).isDefined""",
//            """Username cannot contain special characters."""
//          ))),
//        MetaAttribute("enums", CardSet, "ID", Some("Enum"), Nil, None, None, Nil, Nil, Nil)
//      ), Seq("Type"), Seq(), Seq()),
//
//    "Enum" ->
//      MetaEntity("Enum", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("luckyNumber", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """v => Seq(7, 9, 13).contains(v)""",
//            """Value `$v` is not one of the allowed values in Seq(7, 9, 13)"""
//          ))),
//        MetaAttribute("luckyNumber2", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """v => Seq(7, 9, 13).contains(v)""",
//            """Lucky number can only be 7, 9 or 13"""
//          )))
//      ), Seq("Require", "Strings"), Seq(), Seq()),
//
//    "Type" ->
//      MetaEntity("Type", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("string", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > "b"""",
//            ""
//          ))),
//        MetaAttribute("int", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2""",
//            ""
//          ))),
//        MetaAttribute("long", CardOne, "Long", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2L""",
//            ""
//          ))),
//        MetaAttribute("float", CardOne, "Float", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2.2f""",
//            ""
//          ))),
//        MetaAttribute("double", CardOne, "Double", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2.2""",
//            ""
//          ))),
//        MetaAttribute("boolean", CardOne, "Boolean", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ == false""",
//            ""
//          ))),
//        MetaAttribute("bigInt", CardOne, "BigInt", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > BigInt(2)""",
//            ""
//          ))),
//        MetaAttribute("bigDecimal", CardOne, "BigDecimal", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > BigDecimal(2.2)""",
//            ""
//          ))),
//        MetaAttribute("date", CardOne, "Date", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.after(new Date(993942000000L))""",
//            ""
//          ))),
//        MetaAttribute("duration", CardOne, "Duration", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(Duration.ofMinutes(2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("instant", CardOne, "Instant", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(Instant.ofEpochSecond(2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localDate", CardOne, "LocalDate", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalDate.of(2002, 1, 1)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localTime", CardOne, "LocalTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalTime.of(2, 2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localDateTime", CardOne, "LocalDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("offsetTime", CardOne, "OffsetTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("offsetDateTime", CardOne, "OffsetDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("zonedDateTime", CardOne, "ZonedDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("uuid", CardOne, "UUID", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"""",
//            ""
//          ))),
//        MetaAttribute("uri", CardOne, "URI", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.toString.length > 2""",
//            ""
//          ))),
//        MetaAttribute("byte", CardOne, "Byte", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2""",
//            ""
//          ))),
//        MetaAttribute("short", CardOne, "Short", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2""",
//            ""
//          ))),
//        MetaAttribute("char", CardOne, "Char", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 'b'""",
//            ""
//          ))),
//        MetaAttribute("ref", CardOne, "ID", Some("Strings"), Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("stringSet", CardSet, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > "c"""",
//            ""
//          ))),
//        MetaAttribute("intSet", CardSet, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3""",
//            ""
//          ))),
//        MetaAttribute("longSet", CardSet, "Long", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3L""",
//            ""
//          ))),
//        MetaAttribute("floatSet", CardSet, "Float", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3.3f""",
//            ""
//          ))),
//        MetaAttribute("doubleSet", CardSet, "Double", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3.3""",
//            ""
//          ))),
//        MetaAttribute("booleanSet", CardSet, "Boolean", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ == false""",
//            ""
//          ))),
//        MetaAttribute("bigIntSet", CardSet, "BigInt", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > BigInt(3)""",
//            ""
//          ))),
//        MetaAttribute("bigDecimalSet", CardSet, "BigDecimal", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > BigDecimal(3.3)""",
//            ""
//          ))),
//        MetaAttribute("dateSet", CardSet, "Date", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.after(new Date(1057010400000L))""",
//            ""
//          ))),
//        MetaAttribute("durationSet", CardSet, "Duration", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(Duration.ofMinutes(2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("instantSet", CardSet, "Instant", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(Instant.ofEpochSecond(2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localDateSet", CardSet, "LocalDate", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalDate.of(2002, 1, 1)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localTimeSet", CardSet, "LocalTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalTime.of(2, 2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localDateTimeSet", CardSet, "LocalDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("offsetTimeSet", CardSet, "OffsetTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("offsetDateTimeSet", CardSet, "OffsetDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("zonedDateTimeSet", CardSet, "ZonedDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("uuidSet", CardSet, "UUID", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"""",
//            ""
//          ))),
//        MetaAttribute("uriSet", CardSet, "URI", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.toString.length > 3""",
//            ""
//          ))),
//        MetaAttribute("byteSet", CardSet, "Byte", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3""",
//            ""
//          ))),
//        MetaAttribute("shortSet", CardSet, "Short", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3""",
//            ""
//          ))),
//        MetaAttribute("charSet", CardSet, "Char", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 'c'""",
//            ""
//          ))),
//        MetaAttribute("refs", CardSet, "ID", Some("Strings"), Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("stringSeq", CardSeq, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > "c"""",
//            ""
//          ))),
//        MetaAttribute("intSeq", CardSeq, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3""",
//            ""
//          ))),
//        MetaAttribute("longSeq", CardSeq, "Long", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3L""",
//            ""
//          ))),
//        MetaAttribute("floatSeq", CardSeq, "Float", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3.3f""",
//            ""
//          ))),
//        MetaAttribute("doubleSeq", CardSeq, "Double", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3.3""",
//            ""
//          ))),
//        MetaAttribute("booleanSeq", CardSeq, "Boolean", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ == false""",
//            ""
//          ))),
//        MetaAttribute("bigIntSeq", CardSeq, "BigInt", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > BigInt(3)""",
//            ""
//          ))),
//        MetaAttribute("bigDecimalSeq", CardSeq, "BigDecimal", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > BigDecimal(3.3)""",
//            ""
//          ))),
//        MetaAttribute("dateSeq", CardSeq, "Date", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.after(new Date(1057010400000L))""",
//            ""
//          ))),
//        MetaAttribute("durationSeq", CardSeq, "Duration", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(Duration.ofMinutes(2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("instantSeq", CardSeq, "Instant", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(Instant.ofEpochSecond(2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localDateSeq", CardSeq, "LocalDate", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalDate.of(2002, 1, 1)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localTimeSeq", CardSeq, "LocalTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalTime.of(2, 2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("localDateTimeSeq", CardSeq, "LocalDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0""",
//            ""
//          ))),
//        MetaAttribute("offsetTimeSeq", CardSeq, "OffsetTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("offsetDateTimeSeq", CardSeq, "OffsetDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("zonedDateTimeSeq", CardSeq, "ZonedDateTime", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0""",
//            ""
//          ))),
//        MetaAttribute("uuidSeq", CardSeq, "UUID", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"""",
//            ""
//          ))),
//        MetaAttribute("uriSeq", CardSeq, "URI", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.toString.length > 3""",
//            ""
//          ))),
//        MetaAttribute("shortSeq", CardSeq, "Short", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 3""",
//            ""
//          ))),
//        MetaAttribute("charSeq", CardSeq, "Char", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 'c'""",
//            ""
//          )))
//      ), Seq(), Seq(), Seq()),
//
//    "Constants" ->
//      MetaEntity("Constants", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("noErrorMsg", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2""",
//            ""
//          ))),
//        MetaAttribute("errorMsg", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2""",
//            """One-line error msg"""
//          ))),
//        MetaAttribute("errorMsgWithValue", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_ > 2""",
//            """One-line error msg. Found $v"""
//          ))),
//        MetaAttribute("errorMsgWithValueQuoted", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.startsWith("hello")""",
//            """Expected hello. Found "$v"."""
//          ))),
//        MetaAttribute("errorMsgWithValueQuoted2", CardOne, "String", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """_.startsWith("hello")""",
//            """Expected hello. Found "$v"."""
//          ))),
//        MetaAttribute("multilineErrorMsg", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """(v: Int) => v.>(2)""",
//            """Long error explanation
//with multiple lines"""
//          ))),
//        MetaAttribute("multilineMsgWithValue", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """(v: Int) => v.>(2)""",
//            """Validation failed:
//Input value $v is not bigger than 2."""
//          ))),
//        MetaAttribute("multilineMsgWithValue2", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """(v: Int) => v.>(2)""",
//            """Validation failed:
//Input value "$v" is not bigger than 2."""
//          ))),
//        MetaAttribute("multiLine", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """v =>
//val data   = 22
//val result = data % 10
//v > result""",
//            ""
//          ))),
//        MetaAttribute("multiLine2", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """{ v =>
//  val data   = 22
//  val result = {
//    data % 10
//  }
//  v > result
//}""",
//            """One-line error msg"""
//          ))),
//        MetaAttribute("multiLine3", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """{ v =>
//  val data   = 22
//  val result = data % 10
//  v > result
//}""",
//            """Long error explanation
//with multiple lines"""
//          ))),
//        MetaAttribute("logic", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """v => v >= 3 && v <= 9 && v != 7 && v % 2 == 1""",
//            """Value must be an odd number between 3 and 9 but not 7"""
//          ))),
//        MetaAttribute("multipleErrors", CardOne, "Int", None, Nil, None, None, Nil, Nil, Seq(
//          (
//            """v => v > 2""",
//            """Test 1: Number must be bigger than 2. Found: $v"""
//          ),
//          (
//            """v => v < 10""",
//            """Test 2: Number must be smaller than 10. Found: $v"""
//          ),
//          (
//            """v => v != 7""",
//            """Test 3: Number must not be 7"""
//          ),
//          (
//            """v => {
//  // Comments in code blocks are transferred to boilerplate code
//  val divider = 2
//  v % divider == 1
//}""",
//            """Test 4: Number must
//be odd. Found: $v"""
//          )))
//      ), Seq(), Seq(), Seq()),
//
//    "Variables" ->
//      MetaEntity("Variables", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("int", CardOne, "Int", None, Nil, None, None, Seq("noErrorMsg"), Nil, Nil),
//        MetaAttribute("noErrorMsg", CardOne, "Int", None, Nil, None, None, Seq("int"), Seq("int"), Seq(
//          (
//            """_ > int""",
//            ""
//          ))),
//        MetaAttribute("int1", CardOne, "Int", None, Nil, None, None, Seq("errorMsg"), Nil, Nil),
//        MetaAttribute("errorMsg", CardOne, "Int", None, Nil, None, None, Seq("int1"), Seq("int1"), Seq(
//          (
//            """_ > int1""",
//            """One-line error msg"""
//          ))),
//        MetaAttribute("int2", CardOne, "Int", None, Nil, None, None, Seq("errorMsgWithValue"), Nil, Nil),
//        MetaAttribute("errorMsgWithValue", CardOne, "Int", None, Nil, None, None, Seq("int2"), Seq("int2"), Seq(
//          (
//            """_ > int2""",
//            """One-line error msg. Found $v"""
//          ))),
//        MetaAttribute("int3", CardOne, "Int", None, Nil, None, None, Seq("multilineMsg"), Nil, Nil),
//        MetaAttribute("multilineMsg", CardOne, "Int", None, Nil, None, None, Seq("int3"), Seq("int3"), Seq(
//          (
//            """(v: Int) => v.>(int3)""",
//            """Validation failed:
//Input value `$v` is not bigger than `int3` value `$int3`."""
//          ))),
//        MetaAttribute("int4", CardOne, "Int", None, Nil, None, None, Seq("multiLine"), Nil, Nil),
//        MetaAttribute("multiLine", CardOne, "Int", None, Nil, None, None, Seq("int4"), Seq("int4"), Seq(
//          (
//            """v =>
//val data   = 22
//val result = data % int4
//v > result""",
//            ""
//          ))),
//        MetaAttribute("int5", CardOne, "Int", None, Nil, None, None, Seq("multiLine2"), Nil, Nil),
//        MetaAttribute("multiLine2", CardOne, "Int", None, Nil, None, None, Seq("int5"), Seq("int5"), Seq(
//          (
//            """{ v =>
//  val data   = 22
//  val result = {
//    data % int5
//  }
//  v > result
//}""",
//            """One-line error msg"""
//          ))),
//        MetaAttribute("int6", CardOne, "Int", None, Nil, None, None, Seq("multiLine3"), Nil, Nil),
//        MetaAttribute("multiLine3", CardOne, "Int", None, Nil, None, None, Seq("int6"), Seq("int6"), Seq(
//          (
//            """{ v =>
//  val data   = 22
//  val result = data % int6
//  v > result
//}""",
//            """Long error explanation
//with multiple lines"""
//          ))),
//        MetaAttribute("int7", CardOne, "Int", None, Nil, None, None, Seq("logic"), Nil, Nil),
//        MetaAttribute("logic", CardOne, "Int", None, Nil, None, None, Seq("int7"), Seq("int7"), Seq(
//          (
//            """v => v >= 3 && v <= 9 && v != int7 && v % 2 == 1""",
//            """Value must be an odd number between 3 and 9 but not `int7` value `$int7`"""
//          ))),
//        MetaAttribute("int8", CardOne, "Int", None, Nil, None, None, Seq("intSet", "multipleErrors", "str", "strs"), Nil, Nil),
//        MetaAttribute("str", CardOne, "String", None, Nil, None, None, Seq("int8", "intSet", "multipleErrors", "strs"), Nil, Nil),
//        MetaAttribute("intSet", CardSet, "Int", None, Nil, None, None, Seq("int8", "multipleErrors", "str", "strs"), Nil, Nil),
//        MetaAttribute("strs", CardSet, "String", None, Nil, None, None, Seq("int8", "intSet", "multipleErrors", "str"), Nil, Nil),
//        MetaAttribute("multipleErrors", CardOne, "Int", None, Nil, None, None, Seq("int8", "intSet", "str", "strs"), Seq("int8", "intSet", "str", "strs"), Seq(
//          (
//            """v => v > 4""",
//            """Test 1: Number must be bigger than 4. Found: $v"""
//          ),
//          (
//            """v => v > int8""",
//            """Test 2: Number must be bigger than `int8` value `$int8`. Found: $v"""
//          ),
//          (
//            """v => v < str.length * 2""",
//            """Test 3: Number must be smaller than `str` value `$str` length `${str.length}` * 2. Found: $v"""
//          ),
//          (
//            """v => {
//  v != intSet.head - 3
//}""",
//            """Test 4: Number must not be `intSet` head value `${intSet.head}` minus 3. Found: $v"""
//          ),
//          (
//            """v => {
//  val divider = strs.size
//  v % divider == 1
//}""",
//            """Test 5: Number must
//be odd. Found: $v"""
//          )))
//      ), Seq(), Seq(), Seq()),
//
//    "MandatoryAttr" ->
//      MetaEntity("MandatoryAttr", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("name", CardOne, "String", None, Seq("mandatory"), None, None, Nil, Nil, Nil),
//        MetaAttribute("hobbies", CardSet, "String", None, Seq("mandatory"), None, None, Nil, Nil, Nil),
//        MetaAttribute("age", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil)
//      ), Seq(), Seq("name", "hobbies"), Seq()),
//
//    "MandatoryRefAB" ->
//      MetaEntity("MandatoryRefAB", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("refA", CardOne, "ID", Some("RefA"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//      ), Seq(), Seq(), Seq("refA" -> "RefA")),
//
//    "MandatoryRefB" ->
//      MetaEntity("MandatoryRefB", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("refB", CardOne, "ID", Some("RefB"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//      ), Seq(), Seq(), Seq("refB" -> "RefB")),
//
//    "MandatoryRefsAB" ->
//      MetaEntity("MandatoryRefsAB", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("refsA", CardSet, "ID", Some("RefA"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//      ), Seq(), Seq(), Seq("refsA" -> "RefA")),
//
//    "MandatoryRefsB" ->
//      MetaEntity("MandatoryRefsB", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("refsB", CardSet, "ID", Some("RefB"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//      ), Seq(), Seq(), Seq("refsB" -> "RefB")),
//
//    "RefA" ->
//      MetaEntity("RefA", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("refB", CardOne, "ID", Some("RefB"), Seq("mandatory"), None, None, Nil, Nil, Nil)
//      ), Seq("MandatoryRefAB", "MandatoryRefsAB"), Seq(), Seq("refB" -> "RefB")),
//
//    "RefB" ->
//      MetaEntity("RefB", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil)
//      ), Seq("MandatoryRefB", "MandatoryRefsB", "RefA", "Require"), Seq(), Seq()),
//
//    "Require" ->
//      MetaEntity("Require", Seq(
//        MetaAttribute("id", CardOne, "ID", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("i", CardOne, "Int", None, Nil, None, None, Nil, Nil, Nil),
//        MetaAttribute("username", CardOne, "String", None, Nil, None, None, Seq("password"), Nil, Nil),
//        MetaAttribute("password", CardOne, "String", None, Nil, None, None, Seq("username"), Nil, Nil),
//        MetaAttribute("x", CardOne, "Int", None, Nil, None, None, Seq("y", "z"), Nil, Nil),
//        MetaAttribute("y", CardOne, "Int", None, Nil, None, None, Seq("x", "z"), Nil, Nil),
//        MetaAttribute("z", CardOne, "Int", None, Nil, None, None, Seq("x", "y"), Nil, Nil),
//        MetaAttribute("int", CardOne, "Int", None, Nil, None, None, Seq("refB"), Nil, Nil),
//        MetaAttribute("refB", CardOne, "ID", Some("RefB"), Nil, None, None, Seq("int"), Nil, Nil),
//        MetaAttribute("ref1", CardOne, "ID", Some("RefB"), Nil, None, None, Seq("ref2"), Nil, Nil),
//        MetaAttribute("ref2", CardOne, "ID", Some("Enum"), Nil, None, None, Seq("ref1"), Nil, Nil)
//      ), Seq(), Seq(), Seq())
//  )
//
//
//  override val attrMap: Map[String, (Card, String, Seq[String])] = Map(
//    "Strings.id" -> (CardOne, "ID", Seq()),
//    "Strings.email" -> (CardOne, "String", Seq()),
//    "Strings.emailWithMsg" -> (CardOne, "String", Seq()),
//    "Strings.regex" -> (CardOne, "String", Seq()),
//    "Strings.regexWithMsg" -> (CardOne, "String", Seq()),
//    "Strings.enums" -> (CardSet, "ID", Seq()),
//    "Enum.id" -> (CardOne, "ID", Seq()),
//    "Enum.luckyNumber" -> (CardOne, "Int", Seq()),
//    "Enum.luckyNumber2" -> (CardOne, "Int", Seq()),
//    "Type.id" -> (CardOne, "ID", Seq()),
//    "Type.string" -> (CardOne, "String", Seq()),
//    "Type.int" -> (CardOne, "Int", Seq()),
//    "Type.long" -> (CardOne, "Long", Seq()),
//    "Type.float" -> (CardOne, "Float", Seq()),
//    "Type.double" -> (CardOne, "Double", Seq()),
//    "Type.boolean" -> (CardOne, "Boolean", Seq()),
//    "Type.bigInt" -> (CardOne, "BigInt", Seq()),
//    "Type.bigDecimal" -> (CardOne, "BigDecimal", Seq()),
//    "Type.date" -> (CardOne, "Date", Seq()),
//    "Type.duration" -> (CardOne, "Duration", Seq()),
//    "Type.instant" -> (CardOne, "Instant", Seq()),
//    "Type.localDate" -> (CardOne, "LocalDate", Seq()),
//    "Type.localTime" -> (CardOne, "LocalTime", Seq()),
//    "Type.localDateTime" -> (CardOne, "LocalDateTime", Seq()),
//    "Type.offsetTime" -> (CardOne, "OffsetTime", Seq()),
//    "Type.offsetDateTime" -> (CardOne, "OffsetDateTime", Seq()),
//    "Type.zonedDateTime" -> (CardOne, "ZonedDateTime", Seq()),
//    "Type.uuid" -> (CardOne, "UUID", Seq()),
//    "Type.uri" -> (CardOne, "URI", Seq()),
//    "Type.byte" -> (CardOne, "Byte", Seq()),
//    "Type.short" -> (CardOne, "Short", Seq()),
//    "Type.char" -> (CardOne, "Char", Seq()),
//    "Type.ref" -> (CardOne, "ID", Seq()),
//    "Type.stringSet" -> (CardSet, "String", Seq()),
//    "Type.intSet" -> (CardSet, "Int", Seq()),
//    "Type.longSet" -> (CardSet, "Long", Seq()),
//    "Type.floatSet" -> (CardSet, "Float", Seq()),
//    "Type.doubleSet" -> (CardSet, "Double", Seq()),
//    "Type.booleanSet" -> (CardSet, "Boolean", Seq()),
//    "Type.bigIntSet" -> (CardSet, "BigInt", Seq()),
//    "Type.bigDecimalSet" -> (CardSet, "BigDecimal", Seq()),
//    "Type.dateSet" -> (CardSet, "Date", Seq()),
//    "Type.durationSet" -> (CardSet, "Duration", Seq()),
//    "Type.instantSet" -> (CardSet, "Instant", Seq()),
//    "Type.localDateSet" -> (CardSet, "LocalDate", Seq()),
//    "Type.localTimeSet" -> (CardSet, "LocalTime", Seq()),
//    "Type.localDateTimeSet" -> (CardSet, "LocalDateTime", Seq()),
//    "Type.offsetTimeSet" -> (CardSet, "OffsetTime", Seq()),
//    "Type.offsetDateTimeSet" -> (CardSet, "OffsetDateTime", Seq()),
//    "Type.zonedDateTimeSet" -> (CardSet, "ZonedDateTime", Seq()),
//    "Type.uuidSet" -> (CardSet, "UUID", Seq()),
//    "Type.uriSet" -> (CardSet, "URI", Seq()),
//    "Type.byteSet" -> (CardSet, "Byte", Seq()),
//    "Type.shortSet" -> (CardSet, "Short", Seq()),
//    "Type.charSet" -> (CardSet, "Char", Seq()),
//    "Type.refs" -> (CardSet, "ID", Seq()),
//    "Type.stringSeq" -> (CardSeq, "String", Seq()),
//    "Type.intSeq" -> (CardSeq, "Int", Seq()),
//    "Type.longSeq" -> (CardSeq, "Long", Seq()),
//    "Type.floatSeq" -> (CardSeq, "Float", Seq()),
//    "Type.doubleSeq" -> (CardSeq, "Double", Seq()),
//    "Type.booleanSeq" -> (CardSeq, "Boolean", Seq()),
//    "Type.bigIntSeq" -> (CardSeq, "BigInt", Seq()),
//    "Type.bigDecimalSeq" -> (CardSeq, "BigDecimal", Seq()),
//    "Type.dateSeq" -> (CardSeq, "Date", Seq()),
//    "Type.durationSeq" -> (CardSeq, "Duration", Seq()),
//    "Type.instantSeq" -> (CardSeq, "Instant", Seq()),
//    "Type.localDateSeq" -> (CardSeq, "LocalDate", Seq()),
//    "Type.localTimeSeq" -> (CardSeq, "LocalTime", Seq()),
//    "Type.localDateTimeSeq" -> (CardSeq, "LocalDateTime", Seq()),
//    "Type.offsetTimeSeq" -> (CardSeq, "OffsetTime", Seq()),
//    "Type.offsetDateTimeSeq" -> (CardSeq, "OffsetDateTime", Seq()),
//    "Type.zonedDateTimeSeq" -> (CardSeq, "ZonedDateTime", Seq()),
//    "Type.uuidSeq" -> (CardSeq, "UUID", Seq()),
//    "Type.uriSeq" -> (CardSeq, "URI", Seq()),
//    "Type.shortSeq" -> (CardSeq, "Short", Seq()),
//    "Type.charSeq" -> (CardSeq, "Char", Seq()),
//    "Constants.id" -> (CardOne, "ID", Seq()),
//    "Constants.noErrorMsg" -> (CardOne, "Int", Seq()),
//    "Constants.errorMsg" -> (CardOne, "Int", Seq()),
//    "Constants.errorMsgWithValue" -> (CardOne, "Int", Seq()),
//    "Constants.errorMsgWithValueQuoted" -> (CardOne, "String", Seq()),
//    "Constants.errorMsgWithValueQuoted2" -> (CardOne, "String", Seq()),
//    "Constants.multilineErrorMsg" -> (CardOne, "Int", Seq()),
//    "Constants.multilineMsgWithValue" -> (CardOne, "Int", Seq()),
//    "Constants.multilineMsgWithValue2" -> (CardOne, "Int", Seq()),
//    "Constants.multiLine" -> (CardOne, "Int", Seq()),
//    "Constants.multiLine2" -> (CardOne, "Int", Seq()),
//    "Constants.multiLine3" -> (CardOne, "Int", Seq()),
//    "Constants.logic" -> (CardOne, "Int", Seq()),
//    "Constants.multipleErrors" -> (CardOne, "Int", Seq()),
//    "Variables.id" -> (CardOne, "ID", Seq()),
//    "Variables.int" -> (CardOne, "Int", Seq("noErrorMsg")),
//    "Variables.noErrorMsg" -> (CardOne, "Int", Seq("int")),
//    "Variables.int1" -> (CardOne, "Int", Seq("errorMsg")),
//    "Variables.errorMsg" -> (CardOne, "Int", Seq("int1")),
//    "Variables.int2" -> (CardOne, "Int", Seq("errorMsgWithValue")),
//    "Variables.errorMsgWithValue" -> (CardOne, "Int", Seq("int2")),
//    "Variables.int3" -> (CardOne, "Int", Seq("multilineMsg")),
//    "Variables.multilineMsg" -> (CardOne, "Int", Seq("int3")),
//    "Variables.int4" -> (CardOne, "Int", Seq("multiLine")),
//    "Variables.multiLine" -> (CardOne, "Int", Seq("int4")),
//    "Variables.int5" -> (CardOne, "Int", Seq("multiLine2")),
//    "Variables.multiLine2" -> (CardOne, "Int", Seq("int5")),
//    "Variables.int6" -> (CardOne, "Int", Seq("multiLine3")),
//    "Variables.multiLine3" -> (CardOne, "Int", Seq("int6")),
//    "Variables.int7" -> (CardOne, "Int", Seq("logic")),
//    "Variables.logic" -> (CardOne, "Int", Seq("int7")),
//    "Variables.int8" -> (CardOne, "Int", Seq("intSet", "multipleErrors", "str", "strs")),
//    "Variables.str" -> (CardOne, "String", Seq("int8", "intSet", "multipleErrors", "strs")),
//    "Variables.intSet" -> (CardSet, "Int", Seq("int8", "multipleErrors", "str", "strs")),
//    "Variables.strs" -> (CardSet, "String", Seq("int8", "intSet", "multipleErrors", "str")),
//    "Variables.multipleErrors" -> (CardOne, "Int", Seq("int8", "intSet", "str", "strs")),
//    "MandatoryAttr.id" -> (CardOne, "ID", Seq()),
//    "MandatoryAttr.name" -> (CardOne, "String", Seq()),
//    "MandatoryAttr.hobbies" -> (CardSet, "String", Seq()),
//    "MandatoryAttr.age" -> (CardOne, "Int", Seq()),
//    "MandatoryRefAB.id" -> (CardOne, "ID", Seq()),
//    "MandatoryRefAB.i" -> (CardOne, "Int", Seq()),
//    "MandatoryRefAB.refA" -> (CardOne, "ID", Seq()),
//    "MandatoryRefB.id" -> (CardOne, "ID", Seq()),
//    "MandatoryRefB.i" -> (CardOne, "Int", Seq()),
//    "MandatoryRefB.refB" -> (CardOne, "ID", Seq()),
//    "MandatoryRefsAB.id" -> (CardOne, "ID", Seq()),
//    "MandatoryRefsAB.i" -> (CardOne, "Int", Seq()),
//    "MandatoryRefsAB.refsA" -> (CardSet, "ID", Seq()),
//    "MandatoryRefsB.id" -> (CardOne, "ID", Seq()),
//    "MandatoryRefsB.i" -> (CardOne, "Int", Seq()),
//    "MandatoryRefsB.refsB" -> (CardSet, "ID", Seq()),
//    "RefA.id" -> (CardOne, "ID", Seq()),
//    "RefA.i" -> (CardOne, "Int", Seq()),
//    "RefA.refB" -> (CardOne, "ID", Seq()),
//    "RefB.id" -> (CardOne, "ID", Seq()),
//    "RefB.i" -> (CardOne, "Int", Seq()),
//    "Require.id" -> (CardOne, "ID", Seq()),
//    "Require.i" -> (CardOne, "Int", Seq()),
//    "Require.username" -> (CardOne, "String", Seq("password")),
//    "Require.password" -> (CardOne, "String", Seq("username")),
//    "Require.x" -> (CardOne, "Int", Seq("y", "z")),
//    "Require.y" -> (CardOne, "Int", Seq("x", "z")),
//    "Require.z" -> (CardOne, "Int", Seq("x", "y")),
//    "Require.int" -> (CardOne, "Int", Seq("refB")),
//    "Require.refB" -> (CardOne, "ID", Seq("int")),
//    "Require.ref1" -> (CardOne, "ID", Seq("ref2")),
//    "Require.ref2" -> (CardOne, "ID", Seq("ref1"))
//  )
//
//
//  override val uniqueAttrs: List[String] = List()
//}