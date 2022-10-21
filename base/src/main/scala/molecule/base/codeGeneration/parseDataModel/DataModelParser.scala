package molecule.base.codeGeneration.parseDataModel

import molecule.base.codeGeneration.parseDataModel.parserAST.{DefAttr, Edge, Enum, Namespace, Optional, ParseModel, Ref, Val}
import parserAST._


case class DataModelParser(dataModelFileName: String, lines0: List[String], allIndexed: Boolean) {

  val lines: List[String] = lines0.map(_.trim)

  def parse: ParseModel = {

    // Checks .......................................................................

    // Check package statement
    lines.collectFirst {
      case r"package (.*)$p\..*" => p
    }.getOrElse {
      throw DataModelException("Found no package statement in data model file")
    }

    // Check domain name
    lines.collectFirst {
      case r"class (.*)${dmn} extends DataModel(.+)"    => throw DataModelException(s"Can't use class as data model container in $dataModelFileName. Please use an object:\nobject $dmn extends DataModel(<max arity>, <max object levels>) { ...")
      case r"trait (.*)${dmn} extends DataModel"        => throw DataModelException(s"Can't use trait as data model container in $dataModelFileName. Please use an object:\nobject $dmn extends DataModel(<max arity>, <max object levels>) { ...")
      case r"trait (.*)${dmn} extends DataModel \{"     => throw DataModelException(s"Can't use trait as data model container in $dataModelFileName. Please use an object:\nobject $dmn extends DataModel(<max arity>, <max object levels>) { ...")
      case r"trait (.*)${dmn} extends DataModel \{ *\}" => throw DataModelException(s"Can't use trait as data model container in $dataModelFileName. Please use an object:\nobject $dmn extends DataModel(<max arity>, <max object levels>) { ...")
    }

    lines.collect {
      case r"object (.*)${dmn} extends DataModel\(\s*(\d+)$arity\s*\) *\{"                         => dmn
      case r"object (.*)${dmn} extends DataModel\(\s*(\d+)$arity\s*\,\s*(\d+)$levels\s*\) *\{"     => dmn
      case r"object (.*)${dmn} extends DataModel\(\s*(\d+)$arity\s*\,\s*(\d+)$levels\s*\) *\{"     => dmn
      case r"object (.*)${dmn} extends DataModel\(\s*(\d+)$arity\s*\,\s*(\d+)$levels\s*\) *\{ *\}" => dmn
    } match {
      case Nil                      => throw DataModelException("Couldn't find data model `object <domain> extends DataModel(<max arity>, <max object levels>) { ...` in " + dataModelFileName)
      case l: List[_] if l.size > 1 => throw DataModelException(s"Only one data model object per data model file allowed. Found ${l.size}:" + l.mkString("\n - ", "DataModel\n - ", "DataModel"))
      case domainNameList           => firstLow(domainNameList.head)
    }


    // Parse ..........................................

    def parseOptions(str0: String, acc: Seq[Optional] = Nil, attr: String, curFullNs: String = ""): Seq[Optional] = {
      val index   = Optional(":db/index         true", "Index")
      val options = str0 match {
        case r"\.alias\((.*)$a\).*" if a.contains('`') => throw DataModelException(s"Attribute alias is not allowed to be a special name in back-ticks. Found $a in namespace $curFullNs. Please use a regular alias name.")
        case r"\.alias\((.*?)$alias\)(.*)$str"         => parseOptions(str, acc :+ Optional(s"alias", alias.init.tail.trim), attr, curFullNs)
        case r"\.doc\((.*)$msg\)(.*)$str"              => parseOptions(str, acc :+ Optional(s":db/doc           $msg", ""), attr, curFullNs)
        case r"\.fulltext(.*)$str"                     => parseOptions(str, acc :+ Optional(":db/fulltext      true", "Fulltext"), attr, curFullNs)
        case r"\.uniqueValue(.*)$str"                  => parseOptions(str, acc :+ Optional(":db/unique        :db.unique/value", "UniqueValue"), attr, curFullNs)
        case r"\.uniqueIdentity(.*)$str"               => parseOptions(str, acc :+ Optional(":db/unique        :db.unique/identity", "UniqueIdentity"), attr, curFullNs)
        case r"\.isComponent(.*)$str"                  => parseOptions(str, acc :+ Optional(":db/isComponent   true", "IsComponent"), attr, curFullNs)
        case r"\.noHistory(.*)$str"                    => parseOptions(str, acc :+ Optional(":db/noHistory     true", "NoHistory"), attr, curFullNs)
        case r"\.index(.*)$str"                        => parseOptions(str, acc :+ index, attr, curFullNs)


        case r"\.allowed\((.*?)$enums\)(.*)$str" =>
          //          val rawEnums: Seq[String] = enums.replaceAll("\"", "").split(",").toList.map(_.trim)
          //          if (rawEnums.forall(_.matches("[a-zA-Z_][a-zA-Z0-9_]*")))
          //            Enum(attr, 1, "OneEnum", "String", "String", rawEnums, parseOptions(str, Nil, attr, curFullNs))
          //          else
          //            throw DataModelException("Enum values can only match `[a-z][a-zA-Z0-9_]*`. Found:\n" + rawEnums.mkString("`", "`\n`", "`"))
          val rawValues: String = enums.replaceAll("\"", "")
          //          parseOptions(str, acc :+ Optional(s"allowed: $rawValues", ""), attr, curFullNs)
          // ignore allowed values for now and include in proper validation implementation
          parseOptions(str, acc, attr, curFullNs)


        case ""         => acc
        case unexpected => throw DataModelException(s"Unexpected options code for attribute `$attr` in namespace `$curFullNs` in $dataModelFileName:\n" + unexpected)
      }
      if (allIndexed) (options :+ index).distinct else options
    }
    val isComponent = Optional(":db/isComponent   true", "IsComponent")

    val reservedAttrNames = List(
      "a", "e", "v", "t", "tx", "txInstant", "op",
      "save", "insert", "update", "retract ",
      "self", "apply", "assert", "replace", "not", "contains", "k"
    )

    val generic = List("Datom", "Schema", "Log", "AEVT", "AVET", "EAVT", "VAET")

    def parseAttr(attr: String, str: String, curPart: String, curFullNs: String, attrGroup0: Option[String]): DefAttr = {
      if (!generic.contains(curFullNs) && reservedAttrNames.contains(attr))
        throw DataModelException(s"Attribute name `$attr` in $dataModelFileName not allowed " +
          s"since it collides with one of the following reserved attribute names:\n" + reservedAttrNames.mkString("\n")
        )
      val curNs        = if (curFullNs.contains('_')) curFullNs.split("_").last else curFullNs
      val curPartDotNs = if (curFullNs.contains('_')) curFullNs.replace("_", ".") else curFullNs

      str match {
        case r"oneString(.*)$str"     => Val(attr, 1, "OneString", "String", "String", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneInt(.*)$str"        => Val(attr, 1, "OneInt", "Int", "Int", "long", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneLong(.*)$str"       => Val(attr, 1, "OneLong", "Long", "Long", "long", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneDouble(.*)$str"     => Val(attr, 1, "OneDouble", "Double", "Double", "double", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneBigInt(.*)$str"     => Val(attr, 1, "OneBigInt", "BigInt", "BigInt", "bigint", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneBigDecimal(.*)$str" => Val(attr, 1, "OneBigDecimal", "BigDecimal", "BigDecimal", "bigdec", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneBoolean(.*)$str"    => Val(attr, 1, "OneBoolean", "Boolean", "Boolean", "boolean", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneDate(.*)$str"       => Val(attr, 1, "OneDate", "Date", "Date", "instant", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneUUID(.*)$str"       => Val(attr, 1, "OneUUID", "UUID", "UUID", "uuid", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneURI(.*)$str"        => Val(attr, 1, "OneURI", "URI", "URI", "uri", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"oneAny(.*)$str"        => Val(attr, 1, "OneAny", "Any", "Any", "object", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)

        case r"manyString(.*)$str"     => Val(attr, 2, "ManyString", "Set[String]", "String", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyInt(.*)$str"        => Val(attr, 2, "ManyInt", "Set[Int]", "Int", "long", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyLong(.*)$str"       => Val(attr, 2, "ManyLong", "Set[Long]", "Long", "long", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyDouble(.*)$str"     => Val(attr, 2, "ManyDouble", "Set[Double]", "Double", "double", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyBigInt(.*)$str"     => Val(attr, 2, "ManyBigInt", "Set[BigInt]", "BigInt", "bigint", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyBigDecimal(.*)$str" => Val(attr, 2, "ManyBigDecimal", "Set[BigDecimal]", "BigDecimal", "bigdec", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyBoolean(.*)$str"    => Val(attr, 2, "ManyBoolean", "Set[Boolean]", "Boolean", "boolean", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyDate(.*)$str"       => Val(attr, 2, "ManyDate", "Set[Date]", "Date", "instant", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyUUID(.*)$str"       => Val(attr, 2, "ManyUUID", "Set[UUID]", "UUID", "uuid", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"manyURI(.*)$str"        => Val(attr, 2, "ManyURI", "Set[URI]", "URI", "uri", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)

        case r"mapString(.*)$str"     => Val(attr, 3, "MapString", "Map[String, String]", "String", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapInt(.*)$str"        => Val(attr, 3, "MapInt", "Map[String, Int]", "Int", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapLong(.*)$str"       => Val(attr, 3, "MapLong", "Map[String, Long]", "Long", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapDouble(.*)$str"     => Val(attr, 3, "MapDouble", "Map[String, Double]", "Double", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapBigInt(.*)$str"     => Val(attr, 3, "MapBigInt", "Map[String, BigInt]", "BigInt", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapBigDecimal(.*)$str" => Val(attr, 3, "MapBigDecimal", "Map[String, BigDecimal]", "BigDecimal", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapBoolean(.*)$str"    => Val(attr, 3, "MapBoolean", "Map[String, Boolean]", "Boolean", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapDate(.*)$str"       => Val(attr, 3, "MapDate", "Map[String, Date]", "Date", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapUUID(.*)$str"       => Val(attr, 3, "MapUUID", "Map[String, UUID]", "UUID", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"mapURI(.*)$str"        => Val(attr, 3, "MapURI", "Map[String, URI]", "URI", "string", parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)

        case r"oneEnum\((.*?)$enums\)(.*)$str" =>
          val rawEnums: Seq[String] = enums.replaceAll("\"", "").split(",").toList.map(_.trim)
          if (rawEnums.forall(_.matches("[a-zA-Z_][a-zA-Z0-9_]*")))
            Enum(attr, 1, "OneEnum", "String", "String", rawEnums, parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
          else
            throw DataModelException("Enum values can only match `[a-z][a-zA-Z0-9_]*`. Found:\n" + rawEnums.mkString("`", "`\n`", "`"))

        case r"manyEnum\((.*?)$enums\)(.*)$str" =>
          val rawEnums: Seq[String] = enums.replaceAll("\"", "").split(",").toList.map(_.trim)
          if (rawEnums.forall(_.matches("[a-zA-Z_][a-zA-Z0-9_]*")))
            Enum(attr, 2, "ManyEnums", "Set[String]", "String", rawEnums, parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
          else
            throw DataModelException("Enum values can only match [a-zA-Z_][a-zA-Z0-9_]*`. Found:\n" + rawEnums.mkString("`", "`\n`", "`"))


        // Bidirectional edge ref

        case r"oneBiEdge\[(.*)$biRef\](.*)$str" =>
          val (refNs, revRef) = parseBiEdgeRefTypeArg("one", biRef, attr, curPart, curFullNs)
          Ref(attr, 1, "OneRefAttr", "OneRef", "Long", "Long", refNs, parseOptions(str, Nil, attr, curFullNs) :+ isComponent, Some("BiEdgeRef_"), revRef, attrGroup = attrGroup0)

        case r"manyBiEdge\[(.*)$biRef\](.*)$str" =>
          val (refNs, revRef) = parseBiEdgeRefTypeArg("many", biRef, attr, curPart, curFullNs)
          Ref(attr, 2, "ManyRefAttr", "ManyRef", "Set[Long]", "Long", refNs, parseOptions(str, Nil, attr, curFullNs) :+ isComponent, Some("BiEdgeRef_"), revRef, attrGroup = attrGroup0)


        // Bidirectional ref

        case r"oneBi\[(.*)$biRef\](.*)$str" =>
          val (refNs, bi, revRef) = parseBiRefTypeArg("one", biRef, attr, curPart, curFullNs)
          Ref(attr, 1, "OneRefAttr", "OneRef", "Long", "Long", refNs, parseOptions(str, Nil, attr, curFullNs), Some(bi), revRef, attrGroup = attrGroup0)

        case r"manyBi\[(.*)$biRef\](.*)$str" =>
          val (refNs, bi, revRef) = parseBiRefTypeArg("many", biRef, attr, curPart, curFullNs)
          Ref(attr, 2, "ManyRefAttr", "ManyRef", "Set[Long]", "Long", refNs, parseOptions(str, Nil, attr, curFullNs), Some(bi), revRef, attrGroup = attrGroup0)

        // Bidirectional edge target
        case r"target\[(.*)$biTargetRef\](.*)$str" =>
          val (targetNs, revRef) = parseTargetRefTypeArg(biTargetRef, attr, curPart, curFullNs)
          Ref(attr, 1, "OneRefAttr", "OneRef", "Long", "Long", targetNs, parseOptions(str, Nil, attr, curFullNs), Some("BiTargetRef_"), revRef, attrGroup = attrGroup0)


        // Reference

        case r"one\[(.*)$ref\](.*)$str"  => Ref(attr, 1, "OneRefAttr", "OneRef", "Long", "Long", parseRefTypeArg(ref, curPart), parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)
        case r"many\[(.*)$ref\](.*)$str" => Ref(attr, 2, "ManyRefAttr", "ManyRef", "Set[Long]", "Long", parseRefTypeArg(ref, curPart), parseOptions(str, Nil, attr, curFullNs), attrGroup = attrGroup0)


        // Missing ref type args

        case r"oneBi(.*)$str" => throw DataModelException(
          s"""Type arg missing for bidirectional ref definition `$attr` in `$curPartDotNs` of $dataModelFileName.
             |Please add something like:
             |  val $attr = oneBi[$curNs] // for bidirectional self-reference, or:
             |  val $attr = oneBi[<otherNamespace>.<revRefAttr>.type] // for "outgoing" bidirectional reference to other namespace""".stripMargin)

        case r"manyBi(.*)$str" => throw DataModelException(
          s"""Type arg missing for bidirectional ref definition `$attr` in `$curPartDotNs` of $dataModelFileName.
             |Please add something like:
             |  val $attr = manyBi[$curNs] // for bidirectional self-reference, or:
             |  val $attr = manyBi[<otherNamespace>.<revRefAttr>.type] // for "outgoing" bidirectional reference to other namespace""".stripMargin)

        case r"rev(.*)$str" => throw DataModelException(
          s"""Type arg missing for bidirectional reverse ref definition `$attr` in `$curPartDotNs` of $dataModelFileName.
             |Please add the namespace where the bidirectional ref pointing to this attribute was defined:
             |  val $attr = rev[<definingNamespace>]""".stripMargin)

        case r"one(.*)$str" => throw DataModelException(
          s"""Type arg missing for ref definition `$attr` in `$curPartDotNs` of $dataModelFileName.
             |Please add something like:
             |  val $attr = one[$curNs] // for self-reference, or
             |  val $attr = one[<otherNamespace>] // for ref towards other namespace""".stripMargin)

        case r"many(.*)$str" => throw DataModelException(
          s"""Type arg missing for ref definition `$attr` in `$curPartDotNs` of $dataModelFileName.
             |Please add something like:
             |  val $attr = many[$curNs] // for self-reference, or
             |  val $attr = many[<otherNamespace>] // for ref towards other namespace""".stripMargin)

        case unexpected => throw DataModelException(s"Unexpected attribute code in $dataModelFileName:\n" + unexpected)
      }
    }

    def parseRefTypeArg(refStr: String, curPartition: String = ""): String = refStr match {
      case r"\w*\.([a-z].*)$partref"            => partref.replace(".", "_")
      case r"([a-z]\w*)$part\.(.*)$ref"         => part + "_" + ref
      case r"(.*)$ref" if curPartition.nonEmpty => curPartition + "_" + ref
      case r"(.*)$ref"                          => ref
    }

    def parseBiEdgeRefTypeArg(card: String, refStr: String, baseAttr: String, basePart: String = "", baseFullNs: String = ""): (String, String) = {

      refStr match {

        // With MyDomain .......................................

        // val selfRef = oneBi[MyDomain.ThisPartition.ThisNamespace.selfRef.type]  // or manyBi
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"\w*\.([a-z]\w*)$part\.(.*)$edgeNs\.(.*)$targetAttr\.type" if s"${part}_$edgeNs" == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$edgeNs]")

        // val outRefAttr = oneBi[MyDomain.ThisPartition.OtherNamespace.revRefAttr.type]  // or manyBi
        // should be only
        // val outRefAttr = oneBi[OtherNamespace.revRefAttr.type]
        case r"\w*\.([a-z]\w*)$part\.(.*)$edgeNs\.(.*)$targetAttr\.type" if part == basePart =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName should have " +
            s"only the namespace prefix in the type argument:\n  val $baseAttr = ${card}Bi[$edgeNs.$targetAttr.type]")

        // val outRefAttr = oneBi[MyDomain.SomePartition.OtherNamespace.toRefAttr.type]
        case r"\w*\.([a-z]\w*)$part\.(.*)$edgeNs\.(.*)$targetAttr\.type" => (s"${part}_$edgeNs", targetAttr)


        // With partition .......................................

        // val selfRef = oneBi[ThisPartition.ThisNamespace.selfRef.type]
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"([a-z]\w*)$part\.(.*)$edgeNs\.(.*)$targetAttr\.type" if s"${part}_$edgeNs" == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and can't have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$edgeNs]")

        // val selfRef = oneBi[ThisNamespace.selfRef.type]
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"(.*)$edgeNs\.(.*)$targetAttr\.type" if basePart.nonEmpty && s"${basePart}_$edgeNs" == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$edgeNs]")

        // val outgoingRef = oneBi[SomePartition.OtherNamespace.toRefAttr.type]
        case r"([a-z]\w*)$part\.(.*)$edgeNs\.(.*)$targetAttr\.type" => (s"${part}_$edgeNs", targetAttr)


        // With edge ns .......................................

        // val selfRef = oneBi[ThisNamespace.selfRef.type]
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"(.*)$edgeNs\.(.*)$targetAttr\.type" if edgeNs == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$edgeNs]")

        // val outRefAttr = oneBi[OtherNamespace.toRefAttr.type]
        case r"(.*)$edgeNs\.(.*)$targetAttr\.type" if basePart.nonEmpty => (s"${basePart}_$edgeNs", targetAttr)

        // val outRefAttr = oneBi[OtherNamespace.revRefAttr.type]
        case r"(.*)$edgeNs\.(.*)$targetAttr\.type" => (edgeNs, targetAttr)

        // Incorrect edge definition
        // val selfRef = oneBi[selfRef.type] // presuming it hasn't been imported from another namespace
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"(.*)$a\.type" if a == baseAttr =>
          val ns = if (basePart.nonEmpty) baseFullNs.split("_").last else baseFullNs
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and only needs the current namespace as type argument:\n  val $baseAttr = ${card}Bi[$ns]")
      }
    }

    def parseTargetRefTypeArg(refStr: String, baseAttr: String, basePart: String = "", baseFullNs: String = ""): (String, String) = {
      refStr match {

        // val outRefAttr = oneBi[OtherNamespace.revRefAttr.type]
        case r"(.*)$targetNs\.(.*)$targetAttr\.type" if basePart.nonEmpty => (s"${basePart}_$targetNs", targetAttr)

        // val outRefAttr = oneBi[OtherNamespace.revRefAttr.type]
        case r"(.*)$targetNs\.(.*)$targetAttr\.type" => (targetNs, targetAttr)

        case other =>
          throw DataModelException(
            s"""Target reference `$baseAttr` in `$baseFullNs` of $dataModelFileName should have a type arg pointing to
               |the attribute that points to this. Something like:
               |  val $baseAttr: AnyRef = target[<baseNs>.<biAttr>.type]
               |(Since this is a recursive definition, we need to add a return type)""".stripMargin)
      }
    }

    def parseBiRefTypeArg(card: String, refStr: String, baseAttr: String, basePart: String = "", baseFullNs: String = ""): (String, String, String) = {

      refStr match {

        // val selfRef = oneBi[MyDomain.ThisPartition.ThisNamespace.selfRef.type]  // or manyBi
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"\w*\.([a-z]\w*)$part\.(.*)$otherNs\.(.*)$targetAttr\.type" if s"${part}_$otherNs" == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$otherNs]")

        // val selfRef = oneBi[ThisPartition.ThisNamespace.selfRef.type]
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"([a-z]\w*)$part\.(.*)$otherNs\.(.*)$targetAttr\.type" if s"${part}_$otherNs" == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$otherNs]")

        // val selfRef = oneBi[ThisNamespace.selfRef.type]
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"(.*)$otherNs\.(.*)$targetAttr\.type" if otherNs == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$otherNs]")


        // val otherRef = oneBi[MyDomain.SomePartition.OtherNamespace.toRefAttr.type]
        case r"\w*\.([a-z]\w*)$part\.(.*)$otherNs\.(.*)$targetAttr\.type" => (s"${part}_$otherNs", "BiOtherRef_", targetAttr)

        // val otherRef = oneBi[SomePartition.OtherNamespace.revRefAttr.type]
        case r"([a-z]\w*)$part\.(.*)$otherNs\.(.*)$targetAttr\.type" => (s"${part}_$otherNs", "BiOtherRef_", targetAttr)

        // val otherRef = oneBi[OtherNamespace.toRefAttr.type]
        case r"(.*)$otherNs\.(.*)$targetAttr\.type" if basePart.nonEmpty => (s"${basePart}_$otherNs", "BiOtherRef_", targetAttr)

        // val otherRef = oneBi[OtherNamespace.revRefAttr.type]
        case r"(.*)$otherNs\.(.*)$targetAttr\.type" => (otherNs, "BiOtherRef_", targetAttr)


        // val selfRef = oneBi[MyDomain.ThisPartition.ThisNamespace] // or manyBi
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"\w*\.([a-z]\w*)$part\.(.*)$selfRef" if s"${part}_$selfRef" == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have the attribute name specified. This is enough:\n  val $baseAttr = ${card}Bi[$selfRef]")

        // val selfRef = oneBi[ThisPartition.ThisNamespace]
        // should be only
        // val selfRef = oneBi[ThisNamespace]
        case r"([a-z]\w*)$part\.(.*)$selfRef" if s"${part}_$selfRef" == baseFullNs =>
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is a self-reference " +
            s"and doesn't need to have partition prefix specified. This is enough:\n  val $baseAttr = ${card}Bi[$selfRef]")

        // val selfRef = oneBi[ThisNamespace]
        case selfNs if basePart.nonEmpty && s"${basePart}_$selfNs" == baseFullNs => (s"${basePart}_$selfNs", "BiSelfRef_", "")

        // val selfRef = oneBi[ThisNamespace]
        case selfNs if selfNs == baseFullNs => (selfNs, "BiSelfRef_", "")

        // val selfRef = oneBi[OtherNamespace]
        case dodgyNs =>
          val part = if (basePart.nonEmpty) s"$basePart." else ""
          throw DataModelException(s"Bidirectional reference `$baseAttr` in `$baseFullNs` of $dataModelFileName is ambiguous. " +
            s"\nPlease choose from one of those 2 options:" +
            s"\n1. Self-reference : val $baseAttr = ${card}Bi[${baseFullNs.replace("_", ".")}]" +
            s"\n2. Other-reference: val $baseAttr = ${card}Bi[$part$dodgyNs.<reverseRefAttr>.type]" +
            s"\nwhere <reverseRefAttr> is a ref in the other namespace that points back to this ref attribute like:" +
            s"\nval reverseRefAttr = ${card}Bi[${baseFullNs.replace("_", ".")}.$baseAttr.type]"
          )
      }
    }

    def someDescr(descr: String): Option[String] = if (descr.nonEmpty) Some(descr) else None

    def attrCmt(emptyLine: Int, comment: String): Option[String] = (emptyLine, comment) match {
      case (0, "")  => None
      case (1, "")  => Some("")
      case (_, cmt) => Some(cmt)
    }
    val dataModel: ParseModel = lines.zipWithIndex.foldLeft(0, "", ParseModel("", -1, "", "", "", Seq())) {
      case ((emptyLine, cmt, d), (line, i)) => line.trim match {
        case r"\/\/\s*val .*"                      => (emptyLine, "", d)
        case r"\/\/\s*(.*?)$comment\s*-*"          => (emptyLine, comment, d)
        case r"package (.*)$pkg\.dataModel"        => (0, "", d.copy(pkg = pkg))
        case r"import molecule\.DataModel"         => (0, "", d)
        case r"import molecule\.DataModel\.(.*)$t" => throw DataModelException(s"Data model api in $dataModelFileName (line ${i + 1}) should be imported with `import molecule.DataModel.`")

        case r"object\s+([A-Z][a-zA-Z0-9]*)${dmn} extends DataModel\(\s*(\d+)$arity\s*\) *\{" =>
          val a = arity.toInt
          if (a < 1 || a > 22)
            throw DataModelException(s"Max arity defined in '$dataModelFileName' was $arity. It should be in the range 1-22.")
          (0, "", d.copy(domain = dmn, maxArity = a))

        // Partition definitions
        case r"object\s+(tx|db|molecule)$part\s*\{"    => throw DataModelException(s"Partition name '$part' in $dataModelFileName (line ${i + 1}) is not allowed. `tx`, `db` and `molecule` are reserved partition names.")
        case r"object\s+([a-z][a-zA-Z0-9]*)$part\s*\{" => (0, "", d.copy(curPart = part, curPartDescr = cmt, nss = d.nss :+ Namespace(part, someDescr(cmt), "", None)))
        case r"object\s+([A-Z][a-zA-Z0-9]*)$part\s*\{" => throw DataModelException(s"Partition name '$part' in $dataModelFileName (line ${i + 1}) should start with a lowercase letter and contain only [a-zA-Z0-9].")
        case r"object\s+(.*)$part\s*\{"                => throw DataModelException(s"Unexpected partition name '$part' in $dataModelFileName (line ${i + 1}).\nPartition names have to start with a lowercase letter and contain only [a-zA-Z0-9].")

        // Ns definitions
        case r"trait\s+([a-zA-Z0-9]*)$ns\s*\{" if d.curPart.nonEmpty => (0, "", d.copy(nss = (if (d.nss.last.ns.isEmpty) d.nss.init else d.nss) :+ Namespace(d.curPart, someDescr(d.curPartDescr), d.curPart + "_" + ns, someDescr(cmt))))
        case r"trait\s+([a-zA-Z0-9]*)$ns\s*\{"                       => (0, "", d.copy(nss = d.nss :+ Namespace("db.part/user", None, ns, someDescr(cmt))))
        case r"trait\s+(.*)$ns\s*\{"                                 => throw DataModelException(s"Unexpected namespace name '$ns' in $dataModelFileName (line ${i + 1}).\nNamespace names can only contain standard letters and numbers ([a-zA-Z0-9]).")

        // Attribute definitions
        case r"val\s+\`(.*)$a\`.*" => throw DataModelException(s"Special attribute names in back-ticks not allowed. Found `$a` in namespace ${d.nss.last.ns}. Please use a regular name without back-ticks.")

        case r"val\s+get(\w*)$a.*"                                      => throw DataModelException(s"Attribute name `get$a` not allowed to start with `get` in $dataModelFileName (line ${i + 1}).")
        case r"val\s+([a-z][a-zA-Z0-9]*)$a\s*:\s*[^=]+\s*\=\s*(.*)$str" => (0, "", d.addAttr(parseAttr(a, str, d.curPart, d.nss.last.ns, attrCmt(emptyLine, cmt))))
        case r"val\s+([a-z][a-zA-Z0-9]*)$a\s*\=\s*(.*)$str"             => (0, "", d.addAttr(parseAttr(a, str, d.curPart, d.nss.last.ns, attrCmt(emptyLine, cmt))))
        case r"val\s+([A-Z][a-zA-Z0-9]*)$a\s*:\s*[^=]+\s*\=\s*(.*)$str" => throw DataModelException(s"Attribute name `$a` in $dataModelFileName (line ${i + 1}) should start with a lowercase letter and contain only [a-zA-Z0-9].")
        case r"val\s+([A-Z][a-zA-Z0-9]*)$a\s*\=\s*(.*)$str"             => throw DataModelException(s"Attribute name `$a` in $dataModelFileName (line ${i + 1}) should start with a lowercase letter and contain only [a-zA-Z0-9].")
        case r"val\s+(.*)$a\s*:\s*[^=]+\s*\=\s*(.*)$str"                => throw DataModelException(s"Unexpected attribute name `$a` in $dataModelFileName (line ${i + 1}).\nAttribute names have to start with lower letter [a-z] and contain only [a-zA-Z0-9].")
        case r"val\s+(.*)$a\s*\=\s*(.*)$str"                            => throw DataModelException(s"Unexpected attribute name `$a` in $dataModelFileName (line ${i + 1}).\nAttribute names have to start with lower letter [a-z] and contain only [a-zA-Z0-9].")

        case "}"                     => (0, "", d)
        case ""                      => (1, cmt, d)
        case r"object .* extends .*" => (0, "", d)
        case unexpected              => throw DataModelException(s"Unexpected data model code in $dataModelFileName (line ${i + 1}):\n" + unexpected)
      }
    }._3

    resolveParseModel(dataModel)
  }


  def resolveParseModel(parseModel: ParseModel): ParseModel = {
    val updatedNss1: Seq[Namespace] = markBidrectionalEdgeProperties(parseModel.nss)
    val updatedNss3: Seq[Namespace] = parseModel.nss.foldLeft(updatedNss1) { case (updatedNss2, curNs) =>
      addBackRefs(updatedNss2, curNs)
    }
    val updatedNss4: Seq[Namespace] = resolveEdgeToOther(updatedNss3)
    parseModel.copy(nss = updatedNss4)
  }

  def resolveEdgeToOther(nss: Seq[Namespace]): Seq[Namespace] = nss.map { ns =>
    val isBaseEntity: Boolean = ns.attrs.collectFirst {
      case Ref(_, _, _, _, _, _, _, _, Some("BiEdgeRef_"), _, _) => true
    } getOrElse false

    if (isBaseEntity) {
      val newAttrs: Seq[DefAttr] = ns.attrs.map {
        case biEdgeRefAttr@Ref(attr1, _, _, _, _, _, edgeNs1, _, Some("BiEdgeRef_"), _, _) =>
          nss.collectFirst {
            case Namespace(part2, _, ns2, _, _, attrs2) if part2 == ns.part && ns2 == edgeNs1 =>
              attrs2.collectFirst {
                case Ref(attr3, _, _, _, _, _, refNs3, _, Some("BiTargetRef_"), _, _) if refNs3 == ns.ns =>
                  biEdgeRefAttr.copy(revRef = attr3)
              } getOrElse {
                val baseNs = ns.ns.replace("_", ".")
                throw DataModelException(s"Couldn't find target reference in edge namespace `${edgeNs1.replace("_", ".")}` that points back to `$baseNs.$attr1`. " +
                  s"Expecting something like:\nval ${firstLow(baseNs.split('.').last)} = target[${baseNs.split('.').last}.$attr1.type]")
              }
          } getOrElse {
            val baseNs = ns.ns.replace("_", ".")
            throw DataModelException(s"Couldn't find target reference in edge namespace `${edgeNs1.replace("_", ".")}` that points back to `$baseNs.$attr1`. " +
              s"Expecting something like:\nval ${firstLow(baseNs.split('.').last)} = target[${baseNs.split('.').last}.$attr1.type]")
          }
        case other                                                                         => other
      }
      ns.copy(attrs = newAttrs)
    } else {
      ns
    }
  }

  def markBidrectionalEdgeProperties(nss: Seq[Namespace]): Seq[Namespace] = nss.map { ns =>

    val isEdge: Boolean = ns.attrs.collectFirst {
      case Ref(_, _, _, _, _, _, _, _, Some("BiTargetRef_"), _, _) => true
    } getOrElse false

    if (isEdge) {
      val newAttrs: Seq[DefAttr] = ns.attrs.map {
        case biEdgeRefAttr@Ref(_, _, _, _, _, _, _, _, Some("BiEdgeRefAttr_"), _, _) => biEdgeRefAttr

        case biTargetRef@Ref(_, _, _, _, _, _, _, _, Some("BiTargetRef_"), _, _) => biTargetRef

        case Ref(attr, _, _, _, _, _, _, _, Some(bi), _, _) if bi.substring(6, 10) != "Prop" => throw DataModelException(
          s"""Namespace `${ns.ns}` is already defined as a "property edge" and can't also define a bidirectional reference `$attr`.""")

        case ref: Ref   => ref.copy(bi = Some("BiEdgePropRef_"))
        case enum: Enum => enum.copy(bi = Some("BiEdgePropAttr_"))
        case value: Val => value.copy(bi = Some("BiEdgePropAttr_"))
        case other      => other
      }
      ns.copy(opt = Some(Edge), attrs = newAttrs)
    } else {
      ns
    }
  }

  def addBackRefs(nss: Seq[Namespace], curNs: Namespace): Seq[Namespace] = {
    // Gather OneRefs (ManyRefs are treated as nested data structures)
    val refMap: Map[String, Ref] = curNs.attrs.collect {
      case outRef@Ref(_, _, _, _, _, _, refNs, _, _, _, _) => refNs -> outRef
    }.toMap

    nss.map {
      case ns2 if refMap.nonEmpty && refMap.keys.toList.contains(ns2.ns) => {
        val attrs2 = refMap.foldLeft(ns2.attrs) { case (attrs, (_, _: Ref)) =>
          val cleanNs = if (curNs.ns.contains('_')) curNs.ns.split("_").tail.head else curNs.ns
          // todo: check not to backreference same-named namespaces in different partitions
          curNs.ns match {
            case ns1 if ns1 == ns2.ns => attrs
            case _                    => attrs :+ BackRef(s"_$cleanNs", 0, "", "", "", "", curNs.ns)
          }
        }.distinct
        ns2.copy(attrs = attrs2)
      }
      case ns2                                                           => ns2
    }
  }
}
