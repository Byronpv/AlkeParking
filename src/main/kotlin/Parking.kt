import java.util.*

const val MAXIMUM_QUOTAS = 3

data class Parking(val vehicles: MutableSet<Vehicle>) {

    companion object {
        var totalEarnings: Pair<Int, Int> = Pair(0, 0)
    }

    fun addVehicle(vehicle: Vehicle): Boolean {
        val availableQuotas = when (vehicles.size < MAXIMUM_QUOTAS) {
            true -> vehicles.add(vehicle)
            else -> false
        }
        return availableQuotas
    }

    fun totalEarnings() =
        println("${totalEarnings.first} vehicles have checked out and have earnigs of ${totalEarnings.second}")


    fun listVehicles(vehicle: MutableSet<Vehicle>) = vehicle.forEach { println(it.plate) }
}








