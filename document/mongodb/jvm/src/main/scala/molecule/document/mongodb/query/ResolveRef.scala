package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.document.mongodb.query.mongoModel.{FlatEmbed, FlatRef, FlatRefNested, NestedEmbed, NestedRef}
import molecule.document.mongodb.sync.pretty
import org.bson.{BsonArray, BsonDocument, BsonInt32, BsonNull}
import scala.collection.mutable.ListBuffer


trait ResolveRef { self: MongoQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, card, owner, _) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }

    if (path.isEmpty) {
      path = List(ref.ns, ref.refAttr, ref.refNs)
    } else {
      path ++= List(ref.refAttr, ref.refNs)
    }
    pathLevels(path) = nestedLevel

    // Project refNs
    val refProjections = new BsonDocument()
    b.projection.append(refAttr, refProjections)

    val subBranch = if (owner) {
      val embeddedBranch = new FlatEmbed(
        nestedLevel,
        Some(b),
        card.isInstanceOf[CardSet],
        ns,
        refAttr,
        refNs,
        b.pathFields, // Continue checking unique field names from base branch to here
        b.dot + refAttr + ".",
        b.und + refAttr + "_",
        b.path + refAttr + ".",
        b.alias + refAttr + "_",
        refProjections
      )
      embeddedBranch.base = b.base
      embeddedBranch

    } else if (b.isInstanceOf[NestedEmbed]) {
      val refBranch = new FlatRefNested(
        nestedLevel,
        Some(b),
        card.isInstanceOf[CardSet],
        ns,
        refAttr,
        refNs,
        ListBuffer.empty[String], // Start over with unique field names
        if (b.isEmbedded) b.dot else "",
        if (b.isEmbedded) b.und else "",
        b.path + refAttr + ".",
        b.alias + refAttr + "_",
        refProjections,
      )
      refBranch

    } else {
      val refBranch = new FlatRef(
        nestedLevel,
        Some(b),
        card.isInstanceOf[CardSet],
        ns,
        refAttr,
        refNs,
        ListBuffer.empty[String], // Start over with unique field names
        if (b.isEmbedded) b.dot else "",
        if (b.isEmbedded) b.und else "",
        b.path + refAttr + ".",
        b.alias + refAttr + "_",
        refProjections,
      )
      refBranch
    }

    // Move to ref branch
    b.subBranches.addOne(subBranch)
    b = subBranch

    // Cast from ref namespace
    val nss     = allCasts.last
    val curPath = nss.last
    nss += ((
      None,
      ListBuffer.empty[String].addAll(curPath._2.toList :+ refAttr),
      ListBuffer.empty[(String, BsonDocument => Any)]
    ))
  }


  final protected def resolveBackRef(bRef: BackRef, prev: Element): Unit = {
    if (isNestedMan || isNestedOpt) {
      val BackRef(backRef, _, _) = bRef
      prev match {
        case a: Attr => throw ModelError(
          s"Expected ref after backref _$backRef. " +
            s"Please add attribute ${a.ns}.${a.attr} to initial namespace ${a.ns} " +
            s"instead of after backref _$backRef."
        )
        case _       => ()
      }
    }

    // Go one level up/back
    path = path.dropRight(2)
    b = b.parent.get

    // Cast from previous namespace
    val nss     = allCasts.last
    val curPath = nss.last
    nss += ((
      None,
      ListBuffer.empty[String].addAll(curPath._2.toList.init),
      ListBuffer.empty[(String, BsonDocument => Any)]
    ))
  }

  protected def resolveNestedRef(ref: Ref, nestedElements: List[Element]): Unit = {
    isNestedMan = true
    if (isNestedOpt) {
      noMixedNestedModes
    }
    resolveNested(ref, nestedElements, true)
  }

  protected def resolveNestedOptRef(ref: Ref, nestedElements: List[Element]): Unit = {
    isNestedOpt = true
    if (isNestedMan) {
      noMixedNestedModes
    }
    if (hasFilterAttr) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    if (topBranch.projection.size == 1) {
      // Omit empty nested when there's no attributes before nested
      topBranch.matches.add(Filters.ne(b.dot + ref.refAttr, new BsonArray()))
    }
    resolveNested(ref, nestedElements, false)
  }

  private def resolveNested(ref: Ref, nestedElements: List[Element], mandatory: Boolean): Unit = {
    validateRefNs(ref, nestedElements)
    isNested = true
    val Ref(ns, refAttr, refNs, _, owner, _) = ref
    nestedLevel += 1
    path ++= List(refAttr, refNs)
    pathLevels(path) = nestedLevel

    // Project refNs
    val refProjections = new BsonDocument()

    if (owner) {
      b.projection.append(refAttr, refProjections)
      val embeddedBranch = new NestedEmbed(
        nestedLevel,
        Some(b),
        ns,
        refAttr,
        refNs,
        b.pathFields, // Continue checking unique field names from base branch to here
        b.dot + refAttr + ".",
        b.und + refAttr + "_",
        b.path + refAttr + ".",
        b.alias + refAttr + "_",
        refProjections
      )
      b.subBranches.addOne(embeddedBranch)
      b = embeddedBranch
      nestedBaseBranches(nestedLevel) = (refAttr, b)

    } else {

      // Project ref
      b.projection.append(refAttr, new BsonInt32(1))

      val refBranch = new NestedRef(
        nestedLevel,
        Some(b),
        ns,
        refAttr,
        refNs,
        ListBuffer.empty[String], // Start over with unique field names
        "",
        "",
        b.path + refAttr + ".",
        b.alias + refAttr + "_",
        mandatory,
        refProjections.append("_id", new BsonInt32(0))
      )
      // Matches build up on new ref branch as base
      b.subBranches.addOne(refBranch)
      b = refBranch
      nestedBaseBranches(nestedLevel) = (refAttr, b)
    }

    // Initiate nested namespace casts
    allCasts += ListBuffer((
      Some(refAttr),
      ListBuffer.empty[String],
      ListBuffer.empty[(String, BsonDocument => Any)]
    ))
  }

  final private def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
    val refName  = ref.refAttr.capitalize
    val nestedNs = nestedElements.head match {
      case a: Attr => a.ns
      case r: Ref  => r.ns
      case other   => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs) {
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
    }
  }
}

/*

  val nsMap: Map[String, MetaNs] = Map(
    "A" ->
      MetaNs("A", Seq(
        MetaAttr("id"   , CardOne, "Long"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i"    , CardOne, "Int"    , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("ii"   , CardSet, "Int"    , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s"    , CardOne, "String" , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("bool" , CardOne, "Boolean", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("a"    , CardOne, "Long"   , Some("A"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("b"    , CardOne, "Long"   , Some("B"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("b1"   , CardOne, "Long"   , Some("B"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("b2"   , CardOne, "Long"   , Some("B"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("c"    , CardOne, "Long"   , Some("C"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("d"    , CardOne, "Long"   , Some("D"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("aa"   , CardSet, "Long"   , Some("A"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("bb"   , CardSet, "Long"   , Some("B"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("cc"   , CardSet, "Long"   , Some("C"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("dd"   , CardSet, "Long"   , Some("D"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("ownB" , CardOne, "Long"   , Some("B"), Seq("owner"), None, None, Nil, Nil, Nil),
        MetaAttr("ownBb", CardSet, "Long"   , Some("B"), Seq("owner"), None, None, Nil, Nil, Nil)
      ), Seq("A", "B", "C"), Seq(), Seq()),

    "B" ->
      MetaNs("B", Seq(
        MetaAttr("id"   , CardOne, "Long"  , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i"    , CardOne, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("ii"   , CardSet, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s"    , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("a"    , CardOne, "Long"  , Some("A"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("b"    , CardOne, "Long"  , Some("B"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("c"    , CardOne, "Long"  , Some("C"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("c1"   , CardOne, "Long"  , Some("C"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("d"    , CardOne, "Long"  , Some("D"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("aa"   , CardSet, "Long"  , Some("A"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("bb"   , CardSet, "Long"  , Some("B"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("cc"   , CardSet, "Long"  , Some("C"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("dd"   , CardSet, "Long"  , Some("D"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("ownC" , CardOne, "Long"  , Some("C"), Seq("owner"), None, None, Nil, Nil, Nil),
        MetaAttr("ownCc", CardSet, "Long"  , Some("C"), Seq("owner"), None, None, Nil, Nil, Nil)
      ), Seq("A", "B"), Seq(), Seq()),

    "C" ->
      MetaNs("C", Seq(
        MetaAttr("id", CardOne, "Long"  , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i" , CardOne, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s" , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("ii", CardSet, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("a" , CardOne, "Long"  , Some("A"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("d" , CardOne, "Long"  , Some("D"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("dd", CardSet, "Long"  , Some("D"), Nil, None, None, Nil, Nil, Nil)
      ), Seq("A", "B"), Seq(), Seq()),

    "D" ->
      MetaNs("D", Seq(
        MetaAttr("id", CardOne, "Long"  , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i" , CardOne, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s" , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("e" , CardOne, "Long"  , Some("E"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("e1", CardOne, "Long"  , Some("E"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("ee", CardSet, "Long"  , Some("E"), Nil, None, None, Nil, Nil, Nil)
      ), Seq("A", "B", "C"), Seq(), Seq()),

    "E" ->
      MetaNs("E", Seq(
        MetaAttr("id", CardOne, "Long"  , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i" , CardOne, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s" , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("f" , CardOne, "Long"  , Some("F"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("ff", CardSet, "Long"  , Some("F"), Nil, None, None, Nil, Nil, Nil)
      ), Seq("D"), Seq(), Seq()),

    "F" ->
      MetaNs("F", Seq(
        MetaAttr("id", CardOne, "Long"  , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i" , CardOne, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s" , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("g" , CardOne, "Long"  , Some("G"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("gg", CardSet, "Long"  , Some("G"), Nil, None, None, Nil, Nil, Nil)
      ), Seq("E"), Seq(), Seq()),

    "G" ->
      MetaNs("G", Seq(
        MetaAttr("id", CardOne, "Long"  , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i" , CardOne, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s" , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("h" , CardOne, "Long"  , Some("H"), Nil, None, None, Nil, Nil, Nil),
        MetaAttr("hh", CardSet, "Long"  , Some("H"), Nil, None, None, Nil, Nil, Nil)
      ), Seq("F"), Seq(), Seq()),

    "H" ->
      MetaNs("H", Seq(
        MetaAttr("id", CardOne, "Long"  , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("i" , CardOne, "Int"   , None, Nil, None, None, Nil, Nil, Nil),
        MetaAttr("s" , CardOne, "String", None, Nil, None, None, Nil, Nil, Nil)
      ), Seq("G"), Seq(), Seq())
  )










trait RefsSchema_Sql extends Schema {

  private val dbs = List(
    "h2",
    "mysql",
    "mssql",
    "oracle",
    "derby",
    "db2",
    // etc..
  )

  private val types = List(
    //                    h2                    mysql   + more dialects...
    "String"     -> List("LONGVARCHAR"        , "LONGVARCHAR"),
    "Int"        -> List("INT"                , "INT"),
    "Long"       -> List("BIGINT"             , "BIGINT"),
    "Float"      -> List("REAL"               , "REAL"),
    "Double"     -> List("DOUBLE PRECISION"   , "DOUBLE"),
    "Boolean"    -> List("BOOLEAN"            , "BOOLEAN"),
    "BigInt"     -> List("DECIMAL(100, 0)"    , "DECIMAL"),
    "BigDecimal" -> List("DECIMAL(65535, 25)" , "DECIMAL"),
    "Date"       -> List("DATE"               , "DATE"),
    "UUID"       -> List("UUID"               , "UUID"),
    "URI"        -> List("VARCHAR"            , "VARCHAR"),
    "Byte"       -> List("TINYINT"            , "TINYINT"),
    "Short"      -> List("SMALLINT"           , "SMALLINT"),
    "Char"       -> List("CHAR"               , "CHAR"),
  )

  def sqlSchema(db: String) = {
    val dbIndex = dbs.indexOf(db, 0) match {
      case -1 => throw new Exception(
        s"Database `$db` not found among databases with implemented jdbc drivers:\n  " + dbs.mkString("\n  ")
      )
      case i  => i
    }

    val tpe = types.map { case (scalaType, sqlTpes) => scalaType -> sqlTpes(dbIndex) }.toMap
    val id  = "BIGINT AUTO_INCREMENT PRIMARY KEY"

    lazy val string     = tpe("String")
    lazy val int        = tpe("Int")
    lazy val long       = tpe("Long")
    lazy val float      = tpe("Float")
    lazy val double     = tpe("Double")
    lazy val boolean    = tpe("Boolean")
    lazy val bigInt     = tpe("BigInt")
    lazy val bigDecimal = tpe("BigDecimal")
    lazy val date       = tpe("Date")
    lazy val uuid       = tpe("UUID")
    lazy val uri        = tpe("URI")
    lazy val byte       = tpe("Byte")
    lazy val short      = tpe("Short")
    lazy val char       = tpe("Char")

    lazy val ref = long

    s"""
       |CREATE TABLE A (
       |  id   $id,
       |  i    $int,
       |  ii   $int ARRAY,
       |  s    $string,
       |  bool $boolean,
       |  a    $ref,
       |  b    $ref,
       |  b1   $ref,
       |  b2   $ref,
       |  c    $ref,
       |  d    $ref,
       |  ownB $ref
       |);
       |
       |CREATE TABLE A_aa_A (
       |  A_1_id BIGINT,
       |  A_2_id BIGINT
       |);
       |
       |CREATE TABLE A_bb_B (
       |  A_id BIGINT,
       |  B_id BIGINT
       |);
       |
       |CREATE TABLE A_cc_C (
       |  A_id BIGINT,
       |  C_id BIGINT
       |);
       |
       |CREATE TABLE A_dd_D (
       |  A_id BIGINT,
       |  D_id BIGINT
       |);
       |
       |CREATE TABLE A_ownBb_B (
       |  A_id BIGINT,
       |  B_id BIGINT
       |);
       |
       |CREATE TABLE B (
       |  id   $id,
       |  i    $int,
       |  ii   $int ARRAY,
       |  s    $string,
       |  a    $ref,
       |  b    $ref,
       |  c    $ref,
       |  c1   $ref,
       |  d    $ref,
       |  ownC $ref
       |);
       |
       |CREATE TABLE B_aa_A (
       |  B_id BIGINT,
       |  A_id BIGINT
       |);
       |
       |CREATE TABLE B_bb_B (
       |  B_1_id BIGINT,
       |  B_2_id BIGINT
       |);
       |
       |CREATE TABLE B_cc_C (
       |  B_id BIGINT,
       |  C_id BIGINT
       |);
       |
       |CREATE TABLE B_dd_D (
       |  B_id BIGINT,
       |  D_id BIGINT
       |);
       |
       |CREATE TABLE B_ownCc_C (
       |  B_id BIGINT,
       |  C_id BIGINT
       |);
       |
       |CREATE TABLE C (
       |  id $id,
       |  i  $int,
       |  s  $string,
       |  ii $int ARRAY,
       |  a  $ref,
       |  d  $ref
       |);
       |
       |CREATE TABLE C_dd_D (
       |  C_id BIGINT,
       |  D_id BIGINT
       |);
       |
       |CREATE TABLE D (
       |  id $id,
       |  i  $int,
       |  s  $string,
       |  e  $ref,
       |  e1 $ref
       |);
       |
       |CREATE TABLE D_ee_E (
       |  D_id BIGINT,
       |  E_id BIGINT
       |);
       |
       |CREATE TABLE E (
       |  id $id,
       |  i  $int,
       |  s  $string,
       |  f  $ref
       |);
       |
       |CREATE TABLE E_ff_F (
       |  E_id BIGINT,
       |  F_id BIGINT
       |);
       |
       |CREATE TABLE F (
       |  id $id,
       |  i  $int,
       |  s  $string,
       |  g  $ref
       |);
       |
       |CREATE TABLE F_gg_G (
       |  F_id BIGINT,
       |  G_id BIGINT
       |);
       |
       |CREATE TABLE G (
       |  id $id,
       |  i  $int,
       |  s  $string,
       |  h  $ref
       |);
       |
       |CREATE TABLE G_hh_H (
       |  G_id BIGINT,
       |  H_id BIGINT
       |);
       |
       |CREATE TABLE H (
       |  id $id,
       |  i  $int,
       |  s  $string
       |);
       |""".stripMargin
  }
}
 */