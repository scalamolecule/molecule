package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.document.mongodb.query.mongoModel.{FlatEmbed, FlatRef, NestedEmbed, NestedRef}
import org.bson.{BsonArray, BsonDocument, BsonInt32, BsonNull}
import scala.collection.mutable.ListBuffer


trait ResolveRef { self: MongoQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val Ref(_, refAttr, refNs, card, owner, _) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }

    // Project refNs
    val refProjections = new BsonDocument()
    b.projection.append(refAttr, refProjections)

    val subBranch = if (owner) {
      new FlatEmbed(
        Some(b),
        card.isInstanceOf[CardSet],
        refAttr,
        refNs,
        b.pathFields, // Continue checking unique field names from base branch to here
        b.dot + refAttr + ".",
        b.und + refAttr + "_",
        refProjections
      )
    } else {
      new FlatRef(
        Some(b),
        refAttr,
        refNs,
        ListBuffer.empty[String], // Start over with unique field names
        if (b.isEmbedded) b.dot else "",
        if (b.isEmbedded) b.und else "",
        refProjections,
      )
    }
    // Matches build up on new ref branch as base
    baseBranch = subBranch
    b.refs.addOne(subBranch)
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
    // No empty nested arrays when asking for mandatory nested data
    //    baseBranch.matches.add(Filters.ne(b.dot + ref.refAttr, new BsonArray()))

    resolveNested(ref, nestedElements, true)
  }

  protected def resolveNestedOptRef(ref: Ref, nestedElements: List[Element]): Unit = {
    isNestedOpt = true
    if (isNestedMan) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    resolveNested(ref, nestedElements, false)
  }

  private def resolveNested(ref: Ref, nestedElements: List[Element], mandatory: Boolean): Unit = {
    isNested = true
    level += 1
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, refNs, _, owner, _) = ref

    // Project refNs
    val refProjections = new BsonDocument()

    if (owner) {
      b.projection.append(refAttr, refProjections)
      b = new NestedEmbed(
        Some(b),
        refAttr,
        refNs,
        b.pathFields, // Continue checking unique field names from base branch to here
        b.dot + refAttr + ".",
        b.und + refAttr + "_",
        refProjections
      )
    } else {

      // Project ref
      b.projection.append(refAttr, new BsonInt32(1))

      val refBranch = new NestedRef(
        Some(b),
        refAttr,
        refNs,
        ListBuffer.empty[String], // Start over with unique field names
        "",
        "",
        mandatory,
        refProjections.append("_id", new BsonInt32(0))
      )
      // Matches build up on new ref branch as base
      baseBranch = refBranch
      b.refs.addOne(refBranch)
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
