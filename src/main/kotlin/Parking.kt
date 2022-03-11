import java.util.*

const val MAXIMUM_QUOTAS = 20

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

    fun totalEarnings() = println("${totalEarnings.first} vehicles have checked out and have earnigs of ${totalEarnings.second}")

    fun listVehicles(vehicle: MutableSet<Vehicle>) = vehicle.forEach { println(it.plate) }
}


/**
 *
 *
-- Why is Vehicle defined as a set?
   This allows to add elements without order criteria, and also helps to avoid repeating elements.

-- Can the type of vehicle change over time?
   Yes, as we can add or change the type of vehicles entering the parking lot over time.

-- Should it be defined as a variable or as a constant in Vehicle?
   As a variable, because if it is required to add or edit more Vehicles already exiting, the variable Type the Class Vehicle must be a variable


-- Where should the checkInTime and discountCard properties be added, in ParkingSpace, Vehicle or both?
   In the class ParkingSpace, because already exists to instance of Vehicle

-- How do we indicate that a data type can be null in Kotlin?
   adding the sign "?" to end declaration
 *
 *
 */




