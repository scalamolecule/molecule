package molecule.sql.sqlite.util


trait BigEncoding {


  /*
    // Given these values we can see how BigInt's are encoded to be able
    // to lexicographically sort correctly even with negative numbers

    val l = List(
      BigInt("-20"),
      BigInt("-10"),
      BigInt("-2"),
      BigInt("-1"),
      BigInt("0"),
      BigInt("1"),
      BigInt("2"),
      BigInt("10"),
      BigInt("20"),
    )
    val upper = '0' + '9'
    val encoded = l.map {
      case pos if pos.signum >= 0 => "2" + f"$pos%40s"
      case neg                    => "1" + (-neg).toString.map(c => (upper - c).toChar).reverse.padTo(40, '9').reverse
    }
    encoded.sorted.foreach(println)
    /*
      19999999999999999999999999999999999999979
      19999999999999999999999999999999999999989
      19999999999999999999999999999999999999997
      19999999999999999999999999999999999999998
      2                                       0
      2                                       1
      2                                       2
      2                                      10
      2                                      20
     */

    val decoded = encoded.map {
      case pos if pos.head == '2' => BigInt(pos.tail.trim)
      case neg                    => BigInt("-" + neg.tail.map(c => (upper - c).toChar))
    }

    println("\n####################################################################################\n")
    decoded.foreach(println)
    /*
      -20
      -10
      -2
      -1
      0
      1
      2
      10
      20
     */
    */


  // todo: make padding length configurable
  val maxBigLength = 40

  // Encode BigInt for consistent lexicographical sorting
  // todo: make padding length configurable
  def encodeBigInt(bigInt: BigInt): String = {
    if (bigInt.signum >= 0) {
      // How can we use $max inside the f interpolation?
      // "2" + f"$bigInt%40s"
      val s = bigInt.toString
      "2" + " " * (maxBigLength - s.length) + s

    } else {
      val upper = '0' + '9'
      "1" + (-bigInt).toString.map(c => (upper - c).toChar).reverse.padTo(maxBigLength, '9').reverse
    }
  }

  // Decode stored string back to BigInt
  def decodeBigInt(encoded0: String): BigInt = {
    val encoded = encoded0.replace(".0", "")
    if (encoded.head == '2') {
      BigInt(encoded.tail.trim)
    } else {
      // Swap digits back to original value
      val upper = '0' + '9'
      BigInt("-" + encoded.tail.map(c => (upper - c).toChar))
    }
  }
}
