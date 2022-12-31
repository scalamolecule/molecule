package molecule.db.datomic.transaction

import molecule.base.util.BaseHelpers
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.util.fns
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait DatomicTxBase_JS extends DatomicDataType_JS with BaseHelpers {

  protected def initTxBase(
    elements: Seq[Element],
    isUpsert: Boolean = false
  ): Unit = {
    update = if (isUpsert) "upsert" else "update"
    nsFull = getInitialNs(elements)
    part = fns.partNs(nsFull).head
    following = false
    buf.setLength(0) // reset
  }

  val buf       = new StringBuffer()
  var following = false

  protected var nsFull       : String              = ""
  protected var part         : String              = ""
  protected var tempId       : Int                 = 0
  protected var lowest       : Int                 = 0
  protected var e            : AnyRef              = "" // Long or String (#db/id[db.part/user -1])
  protected var e0           : AnyRef              = ""
  protected var backRefs     : Map[String, AnyRef] = Map.empty[String, AnyRef]
  protected val prevRefs     : ListBuffer[AnyRef]  = new ListBuffer[AnyRef]
  protected var hasComposites: Boolean             = false


  protected lazy val add           = kw("db", "add")
  protected lazy val retract       = kw("db", "retract")
  protected lazy val retractEntity = kw("db", "retractEntity")
  protected lazy val dbId          = kw("db", "id")
  protected lazy val datomicTx     = "datomic.tx"

  protected def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }

  protected def kw(ns: String, attr: String) = s":$ns/$attr"

  protected def addStmt(e: AnyRef, a: String, v: Any): Unit = {
    buf.append(s"\n [:db/add $e $a $v]")
  }
  protected def retractStmt(e: AnyRef, a: String, v: Any): Unit = {
    buf.append(s"\n [:db/retract $e $a $v]")
  }
  protected def retractEntityStmt(e: AnyRef, a: String, v: Any): Unit = {
    buf.append(s"\n [:db/retractEnity $e]")
  }

  @tailrec
  final protected def getInitialNs(elements: Seq[Element]): String = elements.head match {
    case a: Attr       => a.ns
    case b: Ref        => b.ns
    case Composite(es) => getInitialNs(es)
    case other         => throw MoleculeException("Unexpected head element: " + other)
  }

  var update = "update" // or "upsert"
  protected def multipleModifierMissing(count: Int) = throw MoleculeException(
    s"Please provide explicit `$update.multiple` to $update multiple entities " +
      s"(found $count matching entities)."
  )

  def quote(s: String): Unit = {
    buf.append('"')
    appendEscapedString(s)
    buf.append('"')
  }

  //  def apply(stmts: Seq[Statement], conn: Conn): (String, Set[String]) = {
  //  var uriAttrs = Set.empty[String]
  //  val attrMap  = conn.proxy.attrMap + (":molecule_Meta/otherEdge" -> (1, "ref"))

  // Shamelessly copied from lift-json:
  // https://github.com/lift/framework/blob/db05d863c290c5fd1081a7632263433153fc9fe3/core/json/src/main/scala/net/liftweb/json/JsonAST.scala#L813-L883

  lazy val jsEscapeChars: Set[Char] =
    List(
      ('\u00ad', '\u00ad'),
      ('\u0600', '\u0604'),
      ('\u070f', '\u070f'),
      ('\u17b4', '\u17b5'),
      ('\u200c', '\u200f'),
      ('\u2028', '\u202f'),
      ('\u2060', '\u206f'),
      ('\ufeff', '\ufeff'),
      ('\ufff0', '\uffff')
    ).foldLeft(Set.empty[Char]) {
      case (set, (start, end)) =>
        set ++ (start to end).toSet
    }

  def appendEscapedString(s: String): Unit = {
    s.foreach { c =>
      val strReplacement = c match {
        case '"'  => "\\\""
        case '\\' => "\\\\"
        case '\b' => "\\b"
        case '\f' => "\\f"
        case '\n' => "\\n"
        case '\r' => "\\r"
        case '\t' => "\\t"
        // Set.contains will cause boxing of c to Character, try and avoid this
        case c if (c >= '\u0000' && c < '\u0020') || jsEscapeChars.contains(c) =>
          "\\u%04x".format(c: Int)

        case _ => ""
      }

      // Use Char version of append if we can, as it's cheaper.
      if (strReplacement.isEmpty) {
        buf.append(c)
      } else {
        buf.append(strReplacement)
      }
    }
  }


  //  def value(attr: String, v: Any, gv: GenericValue): Unit = {
  //    val s = v.toString
  //    gv match {
  ////      case Card(3) => quote(s) // map type is always a String
  //
  //      case _ if attrMap.isEmpty => v match {
  //        case Enum(prefix, enum) => buf.append(prefix + enum)
  //        case _: String          => quote(s)
  //        case TempId(part, i)    => buf.append(s"#db/id[$part $i]")
  //        case _: Int             => buf.append(s)
  //        case _: Long            => buf.append(s)
  //        case _: Boolean         => buf.append(s)
  //        case _: Double          => buf.append(s + (if (s.contains('.')) "" else ".0"))
  //        case d: Date            => buf.append("#inst \"" + date2datomicStr2(d) + "\"")
  //        case _: UUID            => buf.append("#uuid \"" + v + "\"")
  //        case _: URI             => buf.append(s)
  //        case _: BigInt          => buf.append(s + "N")
  //        case _: BigDecimal      => buf.append(s + (if (s.contains('.')) "M" else ".0M"))
  //        case _                  => throw new IllegalArgumentException(
  //          s"Unexpected value `$v` of type ${v.getClass}."
  //        )
  //      }
  //
  //      case _ => (attrMap(attr)._2, v) match {
  //        case ("enum", Enum(prefix, enum))            => buf.append(prefix + enum)
  //        case ("String", _)                           => quote(s)
  //        case ("Long" | "ref", TempId(part, i))       => buf.append(s"#db/id[$part $i]")
  //        case ("Int" | "Long" | "ref" | "Boolean", _) => buf.append(s)
  //        case ("Double", _)                           => buf.append(s + (if (s.contains('.')) "" else ".0"))
  //        case ("Date", d: Date)                       => buf.append("#inst \"" + date2datomicStr2(d) + "\"")
  //        case ("UUID", _)                             => buf.append("#uuid \"" + v + "\"")
  //        case ("URI", _)                              => uriAttrs = uriAttrs + attr; buf.append(s)
  //        case ("BigInt", _)                           => buf.append(s + "N")
  //        case ("BigDecimal", _)                       => buf.append(s + (if (s.contains('.')) "M" else ".0M"))
  //        case (tpe, _)                                => throw new IllegalArgumentException(
  //          s"Unexpected $tpe value of type ${
  //            v.getClass
  //          }: " + v
  //        )
  //      }
  //    }
  //  }
  //
  //  def eid(e: Any): String = e match {
  //    case TempId(part, i) => s"#db/id[$part $i]"
  //    case "datomic.tx"    => """"datomic.tx""""
  //    case e               => s"$e"
  //  }
  //
  //  def addStmtX(stmt: Statement): Unit = stmt match {
  //    case Add(e, a, v, gv) =>
  //      buf.append(s"[:db/add ${eid(e)} $a ")
  //      value(a, v, gv)
  //      buf.append("]")
  //
  //    case Retract(e, a, v, gv) =>
  //      buf.append(s"[:db/retract ${eid(e)} $a ")
  //      value(a, v, gv)
  //      buf.append("]")
  //
  //    case RetractEntity(e) =>
  //      buf.append(s"[:db/retractEntity ${eid(e)}]")
  //
  //    case Cas(e, a, oldV, v, gv) =>
  //      buf.append(s"[:db.fn/cas ${eid(e)} $a ")
  //      value(a, oldV, gv)
  //      buf.append(" ")
  //      value(a, v, gv)
  //      buf.append("]")
  //  }


  //    // Build edn
  //
  //    buf.setLength(0) // reset
  //    var following = false
  //    stmts.foreach { stmt =>
  //      if (following)
  //        buf.append("\n ") // Indentation for subsequent clauses to align all
  //      else
  //        following = true
  //      addStmt(stmt)
  //    }
  //    (s"[${buf.toString}]", uriAttrs)
  //  }
}
