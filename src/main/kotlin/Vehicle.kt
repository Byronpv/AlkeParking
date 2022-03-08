data class Vehicle(val plate: String) {

    override fun equals(other: Any?): Boolean {
        if (other is Vehicle) return this.plate == other.plate
        return super.equals(other)
    }

    override fun hashCode(): Int = this.plate.hashCode()





}

fun main() {
    val test = Vehicle("RGV408")
    val test1 = Vehicle("RGV409")
    println(test.equals(test1))
    println(test.hashCode())
}