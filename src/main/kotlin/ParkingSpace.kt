import java.util.*

const val MINUTES_IN_MILLISECOND = 60000

data class ParkingSpace(var vehicle: Vehicle) {

    val parkedTime: Long
        get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILLISECOND
    val parking = Parking(mutableSetOf())

    fun checkIn(vehicle: Vehicle) {
        when {
            parking.addVehicle(vehicle) -> println("Welcome to AlkeParking!")
            else -> println("Sorry, the has check-in failed")

        }

    }
}

    fun main() {
        val parking = Parking(mutableSetOf())
        for (num in 1..20) {
            val car = Vehicle("ABC11$num", VehicleType.CAR, Calendar.getInstance())
            when (parking.addVehicle(car)) {
                 true -> println("Welcome to AlkeParking!")
                 else -> println("Sorry, the has check-in failed")

            }
        }

    }
