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


trait Schema_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M] {
  final lazy val t            : Schema_14_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, Long   ] = ???
  final lazy val tx           : Schema_14_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, Long   ] = ???
  final lazy val txInstant    : Schema_14_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, Date   ] = ???
  final lazy val attrId       : Schema_14_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, Long   ] = ???
  final lazy val a            : Schema_14_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val part         : Schema_14_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val nsFull       : Schema_14_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val ns           : Schema_14_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val attr         : Schema_14_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val ident        : Schema_14_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val valueType    : Schema_14_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val cardinality  : Schema_14_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val doc          : Schema_14_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val unique       : Schema_14_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, String ] = ???
  final lazy val isComponent  : Schema_14_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Boolean] = ???
  final lazy val noHistory    : Schema_14_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Boolean] = ???
  final lazy val index        : Schema_14_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Boolean] = ???
  final lazy val fulltext     : Schema_14_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Boolean] = ???
  
  final lazy val ident$       : Schema_14_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, Option[String ]] = ???
  final lazy val valueType$   : Schema_14_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, Option[String ]] = ???
  final lazy val cardinality$ : Schema_14_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, Option[String ]] = ???
  final lazy val doc$         : Schema_14_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, Option[String ]] = ???
  final lazy val unique$      : Schema_14_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, Option[String ]] = ???
  final lazy val isComponent$ : Schema_14_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_14_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Option[Boolean]] = ???
  final lazy val index$       : Schema_14_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_14_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_13_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val tx_          : Schema_13_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val txInstant_   : Schema_13_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val attrId_      : Schema_13_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val a_           : Schema_13_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val part_        : Schema_13_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val nsFull_      : Schema_13_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val ns_          : Schema_13_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val attr_        : Schema_13_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val ident_       : Schema_13_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val valueType_   : Schema_13_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val cardinality_ : Schema_13_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val doc_         : Schema_13_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val unique_      : Schema_13_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val isComponent_ : Schema_13_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val noHistory_   : Schema_13_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val index_       : Schema_13_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
  final lazy val fulltext_    : Schema_13_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M] = ???
}

trait Schema_13_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] extends Schema_13[A, B, C, D, E, F, G, H, I, J, K, L, M] with ExprOneM_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Schema_13_OneM]
trait Schema_13_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] extends Schema_13[A, B, C, D, E, F, G, H, I, J, K, L, M] with ExprOneO_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Schema_13_OneO]
trait Schema_13_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] extends Schema_13[A, B, C, D, E, F, G, H, I, J, K, L, M] with ExprOneT_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Schema_13_OneT]

