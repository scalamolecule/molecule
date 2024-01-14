package molecule.document.mongodb.query

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.document.mongodb.query.mongoModel.{FlatEmbed, FlatRef, NestedEmbed, NestedRef}
import org.bson.{BsonDocument, BsonInt32}
import scala.collection.mutable.ListBuffer


trait ResolveRef { self: MongoQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, card, owner, _) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }

    if (path2.isEmpty) {
      path2 = List(ref.ns, ref.refAttr, ref.refNs)
    } else {
      path2 ++= List(ref.refAttr, ref.refNs)
    }

    // Project refNs
    val refProjections = new BsonDocument()
    b.projection.append(refAttr, refProjections)

    val subBranch = if (owner) {
      val embeddedBranch = new FlatEmbed(
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
    } else {
      //      println(s"#################  $refAttr  " + b.isEmbedded)
      val refBranch = new FlatRef(
        Some(b),
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

    // Matches build up on new ref branch as base
    b.subBranches.addOne(subBranch)
    b = subBranch

    // Continue from ref namespace
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

    path2 = path2.dropRight(2)

    //    println(s"----- B1 -----  ${b.dot}  ${b.refAttr}  ${b.parent.map(_.isEmbedded)}")
    //    b.matches.forEach(m => println(m))

    // Go one level up/back
    b = b.parent.get

    //    println(s"----- B2 -----  ${b.dot}  ${b.refAttr}  ${b.parent.map(_.isEmbedded)}")
    //    b.matches.forEach(m => println(m))

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
    path2 ++= List(ref.refAttr, ref.refNs)

    // No empty nested arrays when asking for mandatory nested data
    //    b.base.matches.add(Filters.ne(b.dot + ref.refAttr, new BsonArray()))

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
    path2 ++= List(ref.refAttr, ref.refNs)
    resolveNested(ref, nestedElements, false)
  }

  private def resolveNested(ref: Ref, nestedElements: List[Element], mandatory: Boolean): Unit = {
    isNested = true
    validateRefNs(ref, nestedElements)
    val Ref(ns, refAttr, refNs, _, owner, _) = ref

    // Project refNs
    val refProjections = new BsonDocument()

    if (owner) {
      b.projection.append(refAttr, refProjections)
      b = new NestedEmbed(
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
    } else {

      // Project ref
      b.projection.append(refAttr, new BsonInt32(1))

      val refBranch = new NestedRef(
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
