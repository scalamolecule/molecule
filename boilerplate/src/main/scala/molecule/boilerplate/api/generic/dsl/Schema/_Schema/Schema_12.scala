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


trait Schema_12[A, B, C, D, E, F, G, H, I, J, K, L] extends Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L] {
  final lazy val t            : Schema_13_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, Long   ] = ???
  final lazy val tx           : Schema_13_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, Long   ] = ???
  final lazy val txInstant    : Schema_13_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, Date   ] = ???
  final lazy val attrId       : Schema_13_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, Long   ] = ???
  final lazy val a            : Schema_13_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val part         : Schema_13_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val nsFull       : Schema_13_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val ns           : Schema_13_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val attr         : Schema_13_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val ident        : Schema_13_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val valueType    : Schema_13_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val cardinality  : Schema_13_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val doc          : Schema_13_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val unique       : Schema_13_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, String ] = ???
  final lazy val isComponent  : Schema_13_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Boolean] = ???
  final lazy val noHistory    : Schema_13_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Boolean] = ???
  final lazy val index        : Schema_13_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Boolean] = ???
  final lazy val fulltext     : Schema_13_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Boolean] = ???
  
  final lazy val ident$       : Schema_13_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, Option[String ]] = ???
  final lazy val valueType$   : Schema_13_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, Option[String ]] = ???
  final lazy val cardinality$ : Schema_13_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, Option[String ]] = ???
  final lazy val doc$         : Schema_13_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, Option[String ]] = ???
  final lazy val unique$      : Schema_13_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, Option[String ]] = ???
  final lazy val isComponent$ : Schema_13_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_13_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Option[Boolean]] = ???
  final lazy val index$       : Schema_13_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_13_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_12_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val tx_          : Schema_12_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val txInstant_   : Schema_12_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val attrId_      : Schema_12_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val a_           : Schema_12_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val part_        : Schema_12_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val nsFull_      : Schema_12_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val ns_          : Schema_12_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val attr_        : Schema_12_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val ident_       : Schema_12_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val valueType_   : Schema_12_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val cardinality_ : Schema_12_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val doc_         : Schema_12_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val unique_      : Schema_12_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val isComponent_ : Schema_12_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val noHistory_   : Schema_12_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val index_       : Schema_12_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L] = ???
  final lazy val fulltext_    : Schema_12_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L] = ???
}

trait Schema_12_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] extends Schema_12[A, B, C, D, E, F, G, H, I, J, K, L] with ExprOneM_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Schema_12_OneM]
trait Schema_12_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] extends Schema_12[A, B, C, D, E, F, G, H, I, J, K, L] with ExprOneO_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Schema_12_OneO]
trait Schema_12_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] extends Schema_12[A, B, C, D, E, F, G, H, I, J, K, L] with ExprOneT_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Schema_12_OneT]

