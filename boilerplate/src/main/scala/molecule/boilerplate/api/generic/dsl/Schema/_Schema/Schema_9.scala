/*
* AUTO-GENERATED Molecule DSL for namespace `Schema`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.Schema
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.Schema

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.Schema.Schema_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait Schema_9[A, B, C, D, E, F, G, H, I] extends Molecule_09[A, B, C, D, E, F, G, H, I] {
  final lazy val t            : Schema_10_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, Long   ] = ???
  final lazy val tx           : Schema_10_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, Long   ] = ???
  final lazy val txInstant    : Schema_10_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, Date   ] = ???
  final lazy val attrId       : Schema_10_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, Long   ] = ???
  final lazy val a            : Schema_10_OneM[a           , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val part         : Schema_10_OneM[part        , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val nsFull       : Schema_10_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val ns           : Schema_10_OneM[ns          , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val attr         : Schema_10_OneM[attr        , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val ident        : Schema_10_OneM[ident       , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val valueType    : Schema_10_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val cardinality  : Schema_10_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val doc          : Schema_10_OneM[doc         , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val unique       : Schema_10_OneM[unique      , String , A, B, C, D, E, F, G, H, I, String ] = ???
  final lazy val isComponent  : Schema_10_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, Boolean] = ???
  final lazy val noHistory    : Schema_10_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, Boolean] = ???
  final lazy val index        : Schema_10_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, Boolean] = ???
  final lazy val fulltext     : Schema_10_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, Boolean] = ???
  
  final lazy val ident$       : Schema_10_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, Option[String ]] = ???
  final lazy val valueType$   : Schema_10_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, Option[String ]] = ???
  final lazy val cardinality$ : Schema_10_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, Option[String ]] = ???
  final lazy val doc$         : Schema_10_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, Option[String ]] = ???
  final lazy val unique$      : Schema_10_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, Option[String ]] = ???
  final lazy val isComponent$ : Schema_10_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_10_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, Option[Boolean]] = ???
  final lazy val index$       : Schema_10_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_10_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_9_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I] = ???
  final lazy val tx_          : Schema_9_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I] = ???
  final lazy val txInstant_   : Schema_9_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I] = ???
  final lazy val attrId_      : Schema_9_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I] = ???
  final lazy val a_           : Schema_9_OneT[a_          , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val part_        : Schema_9_OneT[part_       , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val nsFull_      : Schema_9_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val ns_          : Schema_9_OneT[ns_         , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val attr_        : Schema_9_OneT[attr_       , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val ident_       : Schema_9_OneT[ident_      , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val valueType_   : Schema_9_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val cardinality_ : Schema_9_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val doc_         : Schema_9_OneT[doc_        , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val unique_      : Schema_9_OneT[unique_     , String , A, B, C, D, E, F, G, H, I] = ???
  final lazy val isComponent_ : Schema_9_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I] = ???
  final lazy val noHistory_   : Schema_9_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I] = ???
  final lazy val index_       : Schema_9_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I] = ???
  final lazy val fulltext_    : Schema_9_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I] = ???
}

trait Schema_9_OneM[Attr, t, A, B, C, D, E, F, G, H, I] extends Schema_9[A, B, C, D, E, F, G, H, I] with ExprOneM_9[Attr, t, A, B, C, D, E, F, G, H, I, Schema_9_OneM]
trait Schema_9_OneO[Attr, t, A, B, C, D, E, F, G, H, I] extends Schema_9[A, B, C, D, E, F, G, H, I] with ExprOneO_9[Attr, t, A, B, C, D, E, F, G, H, I, Schema_9_OneO]
trait Schema_9_OneT[Attr, t, A, B, C, D, E, F, G, H, I] extends Schema_9[A, B, C, D, E, F, G, H, I] with ExprOneT_9[Attr, t, A, B, C, D, E, F, G, H, I, Schema_9_OneT]

