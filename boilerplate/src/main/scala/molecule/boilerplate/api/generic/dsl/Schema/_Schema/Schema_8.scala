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


trait Schema_8[A, B, C, D, E, F, G, H] extends Molecule_08[A, B, C, D, E, F, G, H] {
  final lazy val t            : Schema_9_OneM[t           , Long   , A, B, C, D, E, F, G, H, Long   ] = ???
  final lazy val tx           : Schema_9_OneM[tx          , Long   , A, B, C, D, E, F, G, H, Long   ] = ???
  final lazy val txInstant    : Schema_9_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, Date   ] = ???
  final lazy val attrId       : Schema_9_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, Long   ] = ???
  final lazy val a            : Schema_9_OneM[a           , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val part         : Schema_9_OneM[part        , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val nsFull       : Schema_9_OneM[nsFull      , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val ns           : Schema_9_OneM[ns          , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val attr         : Schema_9_OneM[attr        , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val ident        : Schema_9_OneM[ident       , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val valueType    : Schema_9_OneM[valueType   , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val cardinality  : Schema_9_OneM[cardinality , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val doc          : Schema_9_OneM[doc         , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val unique       : Schema_9_OneM[unique      , String , A, B, C, D, E, F, G, H, String ] = ???
  final lazy val isComponent  : Schema_9_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, Boolean] = ???
  final lazy val noHistory    : Schema_9_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, Boolean] = ???
  final lazy val index        : Schema_9_OneM[index       , Boolean, A, B, C, D, E, F, G, H, Boolean] = ???
  final lazy val fulltext     : Schema_9_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, Boolean] = ???
  
  final lazy val ident$       : Schema_9_OneO[ident$      , String , A, B, C, D, E, F, G, H, Option[String ]] = ???
  final lazy val valueType$   : Schema_9_OneO[valueType$  , String , A, B, C, D, E, F, G, H, Option[String ]] = ???
  final lazy val cardinality$ : Schema_9_OneO[cardinality$, String , A, B, C, D, E, F, G, H, Option[String ]] = ???
  final lazy val doc$         : Schema_9_OneO[doc$        , String , A, B, C, D, E, F, G, H, Option[String ]] = ???
  final lazy val unique$      : Schema_9_OneO[unique$     , String , A, B, C, D, E, F, G, H, Option[String ]] = ???
  final lazy val isComponent$ : Schema_9_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_9_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, Option[Boolean]] = ???
  final lazy val index$       : Schema_9_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_9_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_8_OneT[t_          , Long   , A, B, C, D, E, F, G, H] = ???
  final lazy val tx_          : Schema_8_OneT[tx_         , Long   , A, B, C, D, E, F, G, H] = ???
  final lazy val txInstant_   : Schema_8_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H] = ???
  final lazy val attrId_      : Schema_8_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H] = ???
  final lazy val a_           : Schema_8_OneT[a_          , String , A, B, C, D, E, F, G, H] = ???
  final lazy val part_        : Schema_8_OneT[part_       , String , A, B, C, D, E, F, G, H] = ???
  final lazy val nsFull_      : Schema_8_OneT[nsFull_     , String , A, B, C, D, E, F, G, H] = ???
  final lazy val ns_          : Schema_8_OneT[ns_         , String , A, B, C, D, E, F, G, H] = ???
  final lazy val attr_        : Schema_8_OneT[attr_       , String , A, B, C, D, E, F, G, H] = ???
  final lazy val ident_       : Schema_8_OneT[ident_      , String , A, B, C, D, E, F, G, H] = ???
  final lazy val valueType_   : Schema_8_OneT[valueType_  , String , A, B, C, D, E, F, G, H] = ???
  final lazy val cardinality_ : Schema_8_OneT[cardinality_, String , A, B, C, D, E, F, G, H] = ???
  final lazy val doc_         : Schema_8_OneT[doc_        , String , A, B, C, D, E, F, G, H] = ???
  final lazy val unique_      : Schema_8_OneT[unique_     , String , A, B, C, D, E, F, G, H] = ???
  final lazy val isComponent_ : Schema_8_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H] = ???
  final lazy val noHistory_   : Schema_8_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H] = ???
  final lazy val index_       : Schema_8_OneT[index_      , Boolean, A, B, C, D, E, F, G, H] = ???
  final lazy val fulltext_    : Schema_8_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H] = ???
}

trait Schema_8_OneM[Attr, t, A, B, C, D, E, F, G, H] extends Schema_8[A, B, C, D, E, F, G, H] with ExprOneM_8[Attr, t, A, B, C, D, E, F, G, H, Schema_8_OneM]
trait Schema_8_OneO[Attr, t, A, B, C, D, E, F, G, H] extends Schema_8[A, B, C, D, E, F, G, H] with ExprOneO_8[Attr, t, A, B, C, D, E, F, G, H, Schema_8_OneO]
trait Schema_8_OneT[Attr, t, A, B, C, D, E, F, G, H] extends Schema_8[A, B, C, D, E, F, G, H] with ExprOneT_8[Attr, t, A, B, C, D, E, F, G, H, Schema_8_OneT]

