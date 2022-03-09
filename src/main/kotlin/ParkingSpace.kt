import java.util.*

const val MINUTES_IN_MILLISECOND = 60000

data class ParkingSpace(var vehicle: Vehicle) {

    val parkedTime: Long
        get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILLISECOND
    val parking = Parking(mutableSetOf())

    fun checkIn(vehicle: Vehicle) {

        if (parking.addVehicle(vehicle))
            println("Welcome to AlkeParking!")
        else
            println("Sorry, the has check-in failed")
    }

}


fun main() {
    val parkingSpace =  ParkingSpace ()
    for (num in 1..20) {
        val car = Vehicle("ABC11$num", VehicleType.CAR, Calendar.getInstance())
        parkingSpace.checkIn(car)
    }

}
