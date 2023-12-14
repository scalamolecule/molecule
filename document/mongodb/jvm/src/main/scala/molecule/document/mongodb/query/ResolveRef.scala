package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import org.bson.{BsonArray, BsonDocument, BsonNull}
import scala.collection.mutable.ListBuffer


trait ResolveRef { self: MongoQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val Ref(_, refAttr, refNs, card, owner, _) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }

    b.matches.add(Filters.ne(b.pathDot + refAttr, new BsonNull))

    if (owner) {
      b.path = b.path ++ List(refAttr, refNs)
      b.pathDot = b.pathDot + refAttr + "."
      b.pathUnderscore = b.pathUnderscore + refAttr + "_"
      b.addFields(b.path) = Nil

    } else {
      // Referenced entities aggregate in a clean new branch
      val prevPathDot = b.pathDot
      val newPath     = topPath ++ List(refAttr, refNs)
      val newBranch   = new Branch
      bb += newPath -> newBranch
      b = newBranch
      b.prevPathDot = prevPathDot
    }

    // Know if we get arrays (ref) or documents (embedded)
    b.refOwnerships(b.path) = owner

    if (card == CardSet && b.pathDot.nonEmpty) {
      unwinds += "$" + b.pathDot.init
    }

    val refDoc = new BsonDocument()
    projections(topPath) = projections(topPath).append(refAttr, refDoc)

    topPath = topPath ++ List(refAttr, refNs)
    projections(topPath) = refDoc

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
    if (isNested || isNestedOpt) {
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

    // Back to previous namespace
    b.path = b.path.dropRight(2)
    b.pathDot = {
      val nss = b.pathDot.init.split('.').init
      if (nss.isEmpty) "" else nss.mkString(".") + "."
    }
    b.pathUnderscore = {
      val nss = b.pathUnderscore.init.split('_').init
      if (nss.isEmpty) "" else nss.mkString("_") + "_"
    }

    topPath = topPath.dropRight(2)

    // go back to previous doc if it exists
    bb.toMap.get(topPath).foreach { branch =>
      b = branch
    }

    val nss     = allCasts.last
    val curPath = nss.last
    nss += ((
      None,
      ListBuffer.empty[String].addAll(curPath._2.toList.init),
      ListBuffer.empty[(String, BsonDocument => Any)]
    ))
  }

  protected def resolveNestedRef(ref: Ref, nestedElements: List[Element]): Unit = {
    isNested = true
    if (isNestedOpt) {
      noMixedNestedModes
    }
    // No empty nested arrays when asking for mandatory nested data
    b.matches.add(Filters.ne(ref.refAttr, new BsonArray()))

    resolveNested(ref, nestedElements, true)
  }

  protected def resolveNestedOptRef(ref: Ref, nestedElements: List[Element]): Unit = {
    isNestedOpt = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    resolveNested(ref, nestedElements, false)
  }

  private def resolveNested(ref: Ref, nestedElements: List[Element], mandatory: Boolean): Unit = {
    b.nested = true
    level += 1
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, refNs, _, owner, _) = ref

    if (owner) {
      b.path = b.path :+ refAttr
      b.pathDot = b.pathDot + refAttr + "."
      b.pathUnderscore = b.pathUnderscore + refAttr + "_"
      b.addFields(b.path) = Nil
    } else {
      if (mandatory) {
        // Make sure that lookup ref document arrays are not empty.
        // Ref attr is overwritten by Mongo so we need to check after lookup.
        b.mandatoryLookup = Some(Filters.ne(ref.refAttr, new BsonArray))
      }

      // Referenced entities aggregate in a clean new branch
      val prevPathDot = b.pathDot
      val newPath     = topPath ++ List(refAttr, refNs)
      val newBranch   = new Branch
      bb += newPath -> newBranch
      b = newBranch
      b.prevPathDot = prevPathDot
    }

    // Know if we get arrays (ref) or documents (embedded)
    b.refOwnerships(b.path) = ref.owner

    val refDoc = new BsonDocument()
    projections(topPath) = projections(topPath).append(refAttr, refDoc)

    topPath = topPath ++ List(refAttr, refNs)
    projections(topPath) = refDoc

    // Initiate nested namespace
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
