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


trait Schema_2[A, B] extends Molecule_02[A, B] {
  final lazy val t            : Schema_3_OneM[t           , Long   , A, B, Long   ] = ???
  final lazy val tx           : Schema_3_OneM[tx          , Long   , A, B, Long   ] = ???
  final lazy val txInstant    : Schema_3_OneM[txInstant   , Date   , A, B, Date   ] = ???
  final lazy val attrId       : Schema_3_OneM[attrId      , Long   , A, B, Long   ] = ???
  final lazy val a            : Schema_3_OneM[a           , String , A, B, String ] = ???
  final lazy val part         : Schema_3_OneM[part        , String , A, B, String ] = ???
  final lazy val nsFull       : Schema_3_OneM[nsFull      , String , A, B, String ] = ???
  final lazy val ns           : Schema_3_OneM[ns          , String , A, B, String ] = ???
  final lazy val attr         : Schema_3_OneM[attr        , String , A, B, String ] = ???
  final lazy val ident        : Schema_3_OneM[ident       , String , A, B, String ] = ???
  final lazy val valueType    : Schema_3_OneM[valueType   , String , A, B, String ] = ???
  final lazy val cardinality  : Schema_3_OneM[cardinality , String , A, B, String ] = ???
  final lazy val doc          : Schema_3_OneM[doc         , String , A, B, String ] = ???
  final lazy val unique       : Schema_3_OneM[unique      , String , A, B, String ] = ???
  final lazy val isComponent  : Schema_3_OneM[isComponent , Boolean, A, B, Boolean] = ???
  final lazy val noHistory    : Schema_3_OneM[noHistory   , Boolean, A, B, Boolean] = ???
  final lazy val index        : Schema_3_OneM[index       , Boolean, A, B, Boolean] = ???
  final lazy val fulltext     : Schema_3_OneM[fulltext    , Boolean, A, B, Boolean] = ???
  
  final lazy val ident$       : Schema_3_OneO[ident$      , String , A, B, Option[String ]] = ???
  final lazy val valueType$   : Schema_3_OneO[valueType$  , String , A, B, Option[String ]] = ???
  final lazy val cardinality$ : Schema_3_OneO[cardinality$, String , A, B, Option[String ]] = ???
  final lazy val doc$         : Schema_3_OneO[doc$        , String , A, B, Option[String ]] = ???
  final lazy val unique$      : Schema_3_OneO[unique$     , String , A, B, Option[String ]] = ???
  final lazy val isComponent$ : Schema_3_OneO[isComponent$, Boolean, A, B, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_3_OneO[noHistory$  , Boolean, A, B, Option[Boolean]] = ???
  final lazy val index$       : Schema_3_OneO[index$      , Boolean, A, B, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_3_OneO[fulltext$   , Boolean, A, B, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_2_OneT[t_          , Long   , A, B] = ???
  final lazy val tx_          : Schema_2_OneT[tx_         , Long   , A, B] = ???
  final lazy val txInstant_   : Schema_2_OneT[txInstant_  , Date   , A, B] = ???
  final lazy val attrId_      : Schema_2_OneT[attrId_     , Long   , A, B] = ???
  final lazy val a_           : Schema_2_OneT[a_          , String , A, B] = ???
  final lazy val part_        : Schema_2_OneT[part_       , String , A, B] = ???
  final lazy val nsFull_      : Schema_2_OneT[nsFull_     , String , A, B] = ???
  final lazy val ns_          : Schema_2_OneT[ns_         , String , A, B] = ???
  final lazy val attr_        : Schema_2_OneT[attr_       , String , A, B] = ???
  final lazy val ident_       : Schema_2_OneT[ident_      , String , A, B] = ???
  final lazy val valueType_   : Schema_2_OneT[valueType_  , String , A, B] = ???
  final lazy val cardinality_ : Schema_2_OneT[cardinality_, String , A, B] = ???
  final lazy val doc_         : Schema_2_OneT[doc_        , String , A, B] = ???
  final lazy val unique_      : Schema_2_OneT[unique_     , String , A, B] = ???
  final lazy val isComponent_ : Schema_2_OneT[isComponent_, Boolean, A, B] = ???
  final lazy val noHistory_   : Schema_2_OneT[noHistory_  , Boolean, A, B] = ???
  final lazy val index_       : Schema_2_OneT[index_      , Boolean, A, B] = ???
  final lazy val fulltext_    : Schema_2_OneT[fulltext_   , Boolean, A, B] = ???
}

trait Schema_2_OneM[Attr, t, A, B] extends Schema_2[A, B] with ExprOneM_2[Attr, t, A, B, Schema_2_OneM]
trait Schema_2_OneO[Attr, t, A, B] extends Schema_2[A, B] with ExprOneO_2[Attr, t, A, B, Schema_2_OneO]
trait Schema_2_OneT[Attr, t, A, B] extends Schema_2[A, B] with ExprOneT_2[Attr, t, A, B, Schema_2_OneT]

