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

    doc.matches.add(Filters.ne(doc.pathDot + refAttr, new BsonNull))

    if (owner) {
      doc.path = doc.path ++ List(refAttr, refNs)
      doc.pathDot = doc.pathDot + refAttr + "."
      doc.pathUnderscore = doc.pathUnderscore + refAttr + "_"
      doc.addFields(doc.path) = Nil

    } else {
      // Referenced entities aggregate in a clean new branch
      val prevPathDot = doc.pathDot
      val newPath     = topPath ++ List(refAttr, refNs)
      val newDoc      = new DocData
      docs += newPath -> newDoc
      doc = newDoc
      doc.prevPathDot = prevPathDot
    }

    // Know if we get arrays (ref) or documents (embedded)
    doc.refOwnerships(doc.path) = owner

    val refDoc = new BsonDocument()
    projections(topPath) = projections(topPath).append(refAttr, refDoc)

    topPath = topPath ++ List(refAttr, refNs)
    projections(topPath) = refDoc

    // Continue from ref namespace
    val nss     = casts.last
    val curPath = nss.last
    nss += ((
      ListBuffer.empty[String].addAll(curPath._1.toList :+ refAttr),
      ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
    ))
    //    printCasts("REF " + refAttr)
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
    doc.path = doc.path.dropRight(2)
    doc.pathDot = {
      val nss = doc.pathDot.init.split('.').init
      if (nss.isEmpty) "" else nss.mkString(".") + "."
    }
    doc.pathUnderscore = {
      val nss = doc.pathUnderscore.init.split('_').init
      if (nss.isEmpty) "" else nss.mkString("_") + "_"
    }

    topPath = topPath.dropRight(2)

    // go back to previous doc if it exists
    docs.toMap.get(topPath).foreach { doc1 =>
      doc = doc1
    }

    val nss     = casts.last
    val curPath = nss.last
    nss += ((
      ListBuffer.empty[String].addAll(curPath._1.toList.init),
      ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
    ))
    //    printCasts("BACKREF")
  }

  protected def resolveNestedRef(ref: Ref, nestedElements: List[Element]): Unit = {
    level += 1
    isNested = true
    if (isNestedOpt) {
      noMixedNestedModes
    }
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, _, _, _, _) = ref

    // No empty nested arrays when asking for mandatory nested data
    doc.matches.add(Filters.ne(refAttr, new BsonArray()))

    doc.path = doc.path :+ refAttr
    doc.pathDot = doc.pathDot + refAttr + "."
    doc.pathUnderscore = doc.pathUnderscore + refAttr + "_"

    doc.addFields(doc.path) = Nil

    // Initiate nested namespace
    casts += ListBuffer((
      ListBuffer.empty[String].addAll(casts.last.last._1.toList :+ refAttr),
      ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
    ))
    //    printCasts("NEST " + refAttr)
  }

  protected def resolveNestedOptRef(ref: Ref, nestedElements: List[Element]): Unit = {
    level += 1
    isNestedOpt = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, _, _, _, _) = ref

    doc.path = doc.path :+ refAttr
    doc.pathDot = doc.pathDot + refAttr + "."
    doc.pathUnderscore = doc.pathUnderscore + refAttr + "_"

    doc.addFields(doc.path) = Nil

    // Initiate (optional) nested namespace
    casts += ListBuffer(
      (
        ListBuffer.empty[String].addAll(casts.last.last._1.toList :+ refAttr),
        ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
      )
    )
    //    printCasts("OPT NEST " + refAttr)
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
