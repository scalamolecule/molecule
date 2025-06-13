package molecule.core.dataModel

import scala.annotation.targetName

/** Keywords to mark aggregate expressions on and unifications of attributes.
 * {{{
 * // Aggregates on any attribute type
 * Person.age(count).get.map(_.head ==> 3)         // count of asserted `age` attribute values
 * Person.age(countDistinct).get.map(_.head ==> 3) // count of asserted distinct `age` attribute values
 * Person.age(max).get.map(_.head ==> 38)          // maximum `age` value (using `compare`)
 * Person.age(min).get.map(_.head ==> 5)           // maximum `age` value (using `compare`)
 * Person.age(sample).get.map(_.head ==> 27)       // single sample `age` value
 *
 * // Aggregates on any attribute type, returning multiple values
 * Person.age(distinct).get.map(_.head ==> Vector(5, 7, 38)) // distinct `age` values
 * Person.age(max(2)).get.map(_.head ==> Vector(38, 7))      // 2 maximum `age` values
 * Person.age(min(2)).get.map(_.head ==> Vector(5, 7))       // 2 minimum `age` values
 * Person.age(sample(2)).get.map(_.head ==> Vector(7, 38))   // 2 sample `age` values
 *
 * // Aggregates on number attributes
 * Person.age(sum).get.map(_.head ==> 50)                  // sum of all `age` numbers
 * Person.age(avg).get.map(_.head ==> 16.66666667)         // average of all `age` numbers
 * Person.age(median).get.map(_.head ==> 7)                // median of all `age` numbers
 * Person.age(stddev).get.map(_.head ==> 15.107025591499)  // standard deviation of all `age` numbers
 * Person.age(variance).get.map(_.head ==> 228.2222222222) // variance of all `age` numbers
 * }}}
 *
 * @groupname aggregates Aggregate keywords
 * @groupdesc aggregates
 *            Keywords applied to attributes that return aggregated value(s).
 * @groupprio aggregates 30
 * @groupname aggrNumber Number aggregation keywords
 * @groupdesc aggrNumber
 *            Keywords applied to number attributes that return aggregated value(s).
 * @groupprio aggrNumber 31
 */
trait KeywordsStable {

  trait Kw

  trait AggrKw extends Kw
  trait AggrCoalesce extends AggrKw
  trait AggrInt extends AggrKw

  /** Distinct attribute values. <br><br> Apply `distinct` keyword to attribute
   * to return Vector of distinct attribute values of entities matching the
   * molecule.
   * {{{
   * for {
   *   _ <- Person.firstName.lastName.age insert List(
   *     ("Ben", "Hayday", 42),
   *     ("Liz", "Taylor", 34),
   *     ("Liz", "Swifty", 34),
   *     ("Liz", "Mooray", 25)
   *   )
   *   _ <- Person.firstName.age(distinct) insert List(
   *     ("Ben", 42),
   *     ("Liz", Vector(34, 25)) // only single 34 returned
   *   )
   * } yield ()
   * }}}
   *
   * @return
   * List[attribute-type]
   * @group aggregates
   */
  trait distinct extends AggrKw

  /** Minimum attribute value(s). <br><br> Apply `min` keyword to attribute to
   * return the minimum attribute value of entities matching the molecule.
   * {{{
   * for {
   *   _ <- Person.age.insert(25, 34, 37, 42, 70)
   *   _ <- Person.age(min).get.map(_.head ==> 25)
   * } yield ()
   * }}}
   * Apply `min(n)` to return Vector of the n smallest values.
   * {{{
   * Person.age(min(3)).get.map(_.head ==> Vector(25, 34, 37))
   * }}}
   *
   * @note
   * `min`/`min(n)` supports all value types (via comparators). <br>`min(n)`
   * Can at most return the number of values that match.
   * @group aggregates
   */
  trait min extends AggrKw {

    /** Minimum n values of attribute. <br><br> Apply `min(n)` to attribute to
     * return Vector of the n smallest values of entities matching the
     * molecule.
     * {{{
     * for {
     *   _ <- Person.age.insert(25, 34, 37, 42, 70)
     *   _ <- Person.age(min(2)).get.map(_.head ==> List(25, 34))
     * } yield ()
     * }}}
     *
     * @note
     * `min`/`min(n)` supports all value types (via comparators).<br> Can at
     * most return the number of values that match.
     * @return
     * List[attribute-type]
     */
    def apply(i: Int): mins = mins(i)
  }

  case class mins(n: Int) extends Kw {
    override def toString = "mins"
  }

  /** Maximum attribute value(s). <br><br> Apply `max` keyword to attribute to
   * return the maximum attribute value of entities matching the molecule.
   * {{{
   * for {
   *   _ <- Person.age.insert(25, 34, 37, 42, 70)
   *   _ <- Person.age(max).get.map(_.head ==> 70)
   * } yield ()
   * }}}
   * Apply `max(n)` to return Vector of the n biggest values.
   * {{{
   * Person.age(max(3)).get.map(_.head ==> Vector(37, 42, 70))
   * }}}
   *
   * @note
   * `max`/`max(n)` supports all value types (via comparators). <br>`max(n)`
   * Can at most return the number of values that match.
   * @group aggregates
   */
  trait max extends AggrKw {

    /** Maximum n values of attribute. <br><br> Apply `max(n)` to attribute to
     * return Vector of the n biggest values of entities matching the molecule.
     * {{{
     * for {
     *   _ <- Person.age.insert(25, 34, 37, 42, 70)
     *   _ <- Person.age(max(3)).get.map(_.head ==> List(37, 42, 70))
     * } yield ()
     * }}}
     *
     * @note
     * `max`/`max(n)` supports all value types (via comparators).<br> Can at
     * most return the number of values that match.
     * @return
     * List[attribute-type]
     */
    def apply(i: Int): maxs = maxs(i)
  }

  case class maxs(n: Int) extends Kw {
    override def toString = "maxs"
  }

  /** Sample attribute value(s). <br><br> Apply `sample` keyword to attribute to
   * return a single sample (random) attribute value of entities matching the
   * molecule.
   * {{{
   * for {
   *   _ <- Person.age.insert(25, 34, 37, 42, 70)
   *   _ <- Person.age(sample).get.map(_.head ==> 42) // or other..
   * } yield ()
   * }}}
   * Apply `sample(n)` to return Vector of up to n distinct sample values.
   * {{{
   * Person.age(sample(3)).get.map(_.head ==> Vector(70, 25, 37)) // or other..
   * }}}
   *
   * @note
   * Can at most return the number of values that match.
   * @group aggregates
   */
  trait sample extends AggrKw {

    /** Distinct sample values of attribute. <br><br> Apply `sample(n)` to an
     * attribute to return a Set of up to n sample values.
     * {{{
     * for {
     *   _ <- Person.age.insert(25, 34, 37, 42, 70)
     *   _ <- Person.age(sample(3)).get.map(_.head ==> Vector(42, 25, 42)) // or other..
     * } yield ()
     * }}}
     *
     * @note
     * Can at most return the number of values that match.
     * @return
     * List[attribute-type]
     * @group aggregates
     */
    def apply(n: Int): samples = samples(n)
  }

  case class samples(n: Int) extends Kw {
    override def toString = "samples"
  }

  /** Count of attribute values. <br><br> Apply `count` keyword to attribute to
   * return count of attribute values of entities matching the molecule.
   * {{{
   * for {
   *   _ <- Person.firstName.lastName.age insert List(
   *     ("Ben", "Hayday", 42),
   *     ("Liz", "Taylor", 34),
   *     ("Liz", "Swifty", 34),
   *     ("Liz", "Mooray", 25)
   *   )
   *   _ <- Person.firstName.age(count).get.map(_ ==> List(
   *     ("Ben", 1),
   *     ("Liz", 3) // 34, 34, 25
   *   ))
   * } yield ()
   * }}}
   *
   * @return
   * Int
   * @group aggregates
   */
  trait count extends AggrCoalesce with AggrInt

  /** Count of distinct attribute values. <br><br> Apply `countDistinct` keyword
   * to attribute to return count of distinct attribute values of entities
   * matching the molecule.
   * {{{
   * for {
   *   _ <- Person.firstName.lastName.age insert List(
   *     ("Ben", "Hayday", 42),
   *     ("Liz", "Taylor", 34),
   *     ("Liz", "Swifty", 34),
   *     ("Liz", "Mooray", 25)
   *   )
   *   _ <- Person.firstName.age(countDistinct).get.map(_ ==> List(
   *     ("Ben", 1),
   *     ("Liz", 2) // 34, 25
   *   ))
   * } yield ()
   * }}}
   *
   * @return
   * Int
   * @group aggregates
   */
  trait countDistinct extends AggrCoalesce

  /** Sum of attribute values. <br><br> Apply `sum` keyword to attribute to
   * return sum of attribute values of entities matching the molecule.
   * {{{
   * for {
   *   _ <- Match.sKeywords.insert(1, 2, 4)
   *   _ <- Match.score(sum).get.map(_.head ==> 7)
   * } yield ()
   * }}}
   *
   * @return
   * Value of Attribute type
   * @group aggrNumber
   */
  trait sum extends AggrCoalesce

  /** Median of attribute values. <br><br> Apply `median` keyword to attribute
   * to return median of attribute values of entities matching the molecule.
   * {{{
   * for {
   *   _ <- Match.sKeywords.insert(1, 2, 4)
   *   _ <- Match.score(median).get.map(_.head ==> 2)
   * } yield ()
   * }}}
   * OBS: When it comes to an even number of values, Datomic has a special
   * implementation of median that is different from the one described on the
   * [[https://en.wikipedia.org/wiki/Median Wiki entry on the median function]].
   * <br><br> Datomic calculates the median of even number of values as the
   * average of the two middle numbers rounded down to nearest whole number
   * {{{
   * for {
   *   _ <- Match.sKeywords.insert(1, 2, 3, 4)
   *   _ <- Match.score(median).get.map(_.head ==> 2) // (2 + 3) / 2 = 2.5 rounded down to 2
   * } yield ()
   * }}}
   * With decimal numbers this can go wrong:
   * {{{
   * for {
   *   _ <- Match.sKeywords.insert(1.0, 2.5, 2.5, 3.0)
   *   _ <- Match.score(median).get.map(_.head ==> 2) // (2.5 + 2.5) / 2 = 2.5 rounded down to 2 (This is wrong and bug report has been filed)
   * } yield ()
   * }}}
   *
   * @return
   * Value of Attribute type
   * @group aggrNumber
   */
  trait median extends AggrCoalesce

  /** Average of attribute values. <br><br> Apply `avg` keyword to attribute to
   * return average of attribute values of entities matching the molecule.
   * {{{
   * for {
   *   _ <- Match.sKeywords.insert(1, 2, 4)
   *   _ <- Match.score(avg).get.map(_.head ==> 2.3333333333333335) // (1 + 2 + 4) / 3
   * } yield ()
   * }}}
   *
   * @return
   * Double
   * @group aggrNumber
   */
  trait avg extends AggrCoalesce

  /** Variance of attribute values. <br><br> Apply `variance` keyword to
   * attribute to return variance of attribute values of entities matching the
   * molecule.
   * {{{
   * for {
   *   _ <- Match.sKeywords.insert(1, 2, 4)
   *   _ <- Match.score(variance).get.map(_.head ==> 1.5555555555555556)
   * } yield ()
   * }}}
   *
   * @return
   * Double
   * @group aggrNumber
   */
  trait variance extends AggrCoalesce

  /** Variance of attribute values. <br><br> Apply `stddev` keyword to attribute
   * to return variance of attribute values of entities matching the molecule.
   * {{{
   * for {
   *   _ <- Match.sKeywords.insert(1, 2, 4)
   *   _ <- Match.score(stddev).get.map(_.head ==> 1.247219128924647)
   * } yield ()
   * }}}
   *
   * @return
   * Double
   * @group aggrNumber
   */
  trait stddev extends AggrCoalesce

  /**
   * Bind parameter to mark expected data input for cached query structures.
   */
  trait qm extends Kw
}

object Keywords extends KeywordsStable


trait Keywords {
  // Common for all types
  object distinct extends Keywords.distinct {
    override def toString = "distinct"
  }
  object min extends Keywords.min {
    override def toString = "min"
  }
  object max extends Keywords.max {
    override def toString = "max"
  }
  object sample extends Keywords.sample {
    override def toString = "sample"
  }
  object count extends Keywords.count {
    override def toString = "count"
  }
  object countDistinct extends Keywords.countDistinct {
    override def toString = "countDistinct"
  }

  // Number aggregators
  object sum extends Keywords.sum {
    override def toString = "sum"
  }
  object median extends Keywords.median {
    override def toString = "median"
  }
  object avg extends Keywords.avg {
    override def toString = "avg"
  }
  object variance extends Keywords.variance {
    override def toString = "variance"
  }
  object stddev extends Keywords.stddev {
    override def toString = "stddev"
  }

  @targetName("Keywords.qm")
  object ? extends Keywords.qm
}
