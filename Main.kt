/**
 * We declare a package-level function main which returns Unit and takes
 * an Array of strings as a parameter. Note that semicolons are optional.
 */

fun main(args: Array<String>) {
    val mylist = listOf(
        Price(1,3.0,31111),
        Price(1,20.0,44444),
        Price(1,5.0,22222),
        Price(2,2.0,11111),
        Price(2,1.0,33333),
        Price(3,10.0,99989)
        )
    
    val latestPrice = getLatestPrice(mylist)
    println(latestPrice)
}

data class Price( val id: Int, val price: Double, val timestamp:Long)

fun getLatestPrice(priceList:List<Price>):List<Price?>{
    return priceList.groupBy{it.id}.map{it.value}.map{it.maxBy{x->x.timestamp}}
}
