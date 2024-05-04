package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.document.mongodb.query.mongoModel._
import org.bson.{BsonArray, BsonDocument, BsonInt32}
import scala.collection.mutable.ListBuffer


trait ResolveRef { self: MongoQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, card, owner, _) = ref
    val cardMany                                = card == CardSet
    if (isNestedOpt && cardMany) {
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
        cardMany,
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
      if (cardMany) {
        b.base.unwinds += b.dot + refAttr
      }
      embeddedBranch.base = b.base
      embeddedBranch

    } else if (b.isInstanceOf[NestedEmbed]) {
      val refBranch = new FlatRefNested(
        nestedLevel,
        Some(b),
        cardMany,
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
        cardMany,
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
    b.subBranches += subBranch
    b = subBranch

    // Cast from ref namespace
    val nss     = allCasts.last
    val curPath = nss.last
    nss += ((
      None,
      ListBuffer.empty[String] ++= (curPath._2.toList :+ refAttr),
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
      ListBuffer.empty[String] ++= (curPath._2.toList.init),
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
      b.subBranches += embeddedBranch
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
      b.subBranches += refBranch
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