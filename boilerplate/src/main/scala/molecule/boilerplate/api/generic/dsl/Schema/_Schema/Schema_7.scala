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


trait Schema_7[A, B, C, D, E, F, G] extends Molecule_07[A, B, C, D, E, F, G] {
  final lazy val t            : Schema_8_OneM[t           , Long   , A, B, C, D, E, F, G, Long   ] = ???
  final lazy val tx           : Schema_8_OneM[tx          , Long   , A, B, C, D, E, F, G, Long   ] = ???
  final lazy val txInstant    : Schema_8_OneM[txInstant   , Date   , A, B, C, D, E, F, G, Date   ] = ???
  final lazy val attrId       : Schema_8_OneM[attrId      , Long   , A, B, C, D, E, F, G, Long   ] = ???
  final lazy val a            : Schema_8_OneM[a           , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val part         : Schema_8_OneM[part        , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val nsFull       : Schema_8_OneM[nsFull      , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val ns           : Schema_8_OneM[ns          , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val attr         : Schema_8_OneM[attr        , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val ident        : Schema_8_OneM[ident       , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val valueType    : Schema_8_OneM[valueType   , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val cardinality  : Schema_8_OneM[cardinality , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val doc          : Schema_8_OneM[doc         , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val unique       : Schema_8_OneM[unique      , String , A, B, C, D, E, F, G, String ] = ???
  final lazy val isComponent  : Schema_8_OneM[isComponent , Boolean, A, B, C, D, E, F, G, Boolean] = ???
  final lazy val noHistory    : Schema_8_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, Boolean] = ???
  final lazy val index        : Schema_8_OneM[index       , Boolean, A, B, C, D, E, F, G, Boolean] = ???
  final lazy val fulltext     : Schema_8_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, Boolean] = ???
  
  final lazy val ident$       : Schema_8_OneO[ident$      , String , A, B, C, D, E, F, G, Option[String ]] = ???
  final lazy val valueType$   : Schema_8_OneO[valueType$  , String , A, B, C, D, E, F, G, Option[String ]] = ???
  final lazy val cardinality$ : Schema_8_OneO[cardinality$, String , A, B, C, D, E, F, G, Option[String ]] = ???
  final lazy val doc$         : Schema_8_OneO[doc$        , String , A, B, C, D, E, F, G, Option[String ]] = ???
  final lazy val unique$      : Schema_8_OneO[unique$     , String , A, B, C, D, E, F, G, Option[String ]] = ???
  final lazy val isComponent$ : Schema_8_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_8_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, Option[Boolean]] = ???
  final lazy val index$       : Schema_8_OneO[index$      , Boolean, A, B, C, D, E, F, G, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_8_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_7_OneT[t_          , Long   , A, B, C, D, E, F, G] = ???
  final lazy val tx_          : Schema_7_OneT[tx_         , Long   , A, B, C, D, E, F, G] = ???
  final lazy val txInstant_   : Schema_7_OneT[txInstant_  , Date   , A, B, C, D, E, F, G] = ???
  final lazy val attrId_      : Schema_7_OneT[attrId_     , Long   , A, B, C, D, E, F, G] = ???
  final lazy val a_           : Schema_7_OneT[a_          , String , A, B, C, D, E, F, G] = ???
  final lazy val part_        : Schema_7_OneT[part_       , String , A, B, C, D, E, F, G] = ???
  final lazy val nsFull_      : Schema_7_OneT[nsFull_     , String , A, B, C, D, E, F, G] = ???
  final lazy val ns_          : Schema_7_OneT[ns_         , String , A, B, C, D, E, F, G] = ???
  final lazy val attr_        : Schema_7_OneT[attr_       , String , A, B, C, D, E, F, G] = ???
  final lazy val ident_       : Schema_7_OneT[ident_      , String , A, B, C, D, E, F, G] = ???
  final lazy val valueType_   : Schema_7_OneT[valueType_  , String , A, B, C, D, E, F, G] = ???
  final lazy val cardinality_ : Schema_7_OneT[cardinality_, String , A, B, C, D, E, F, G] = ???
  final lazy val doc_         : Schema_7_OneT[doc_        , String , A, B, C, D, E, F, G] = ???
  final lazy val unique_      : Schema_7_OneT[unique_     , String , A, B, C, D, E, F, G] = ???
  final lazy val isComponent_ : Schema_7_OneT[isComponent_, Boolean, A, B, C, D, E, F, G] = ???
  final lazy val noHistory_   : Schema_7_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G] = ???
  final lazy val index_       : Schema_7_OneT[index_      , Boolean, A, B, C, D, E, F, G] = ???
  final lazy val fulltext_    : Schema_7_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G] = ???
}

trait Schema_7_OneM[Attr, t, A, B, C, D, E, F, G] extends Schema_7[A, B, C, D, E, F, G] with ExprOneM_7[Attr, t, A, B, C, D, E, F, G, Schema_7_OneM]
trait Schema_7_OneO[Attr, t, A, B, C, D, E, F, G] extends Schema_7[A, B, C, D, E, F, G] with ExprOneO_7[Attr, t, A, B, C, D, E, F, G, Schema_7_OneO]
trait Schema_7_OneT[Attr, t, A, B, C, D, E, F, G] extends Schema_7[A, B, C, D, E, F, G] with ExprOneT_7[Attr, t, A, B, C, D, E, F, G, Schema_7_OneT]

