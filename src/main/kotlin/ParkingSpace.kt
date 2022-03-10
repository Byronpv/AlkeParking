import java.util.*
import kotlin.math.roundToInt


data class ParkingSpace(var vehicle: Vehicle) {
    private val parking = Parking(mutableSetOf())
    private var vehicleList = mutableSetOf<Vehicle>()


    fun getListVehicles(vehicles: MutableSet<Vehicle>) {
        vehicleList = vehicles
    }


    fun checkOutVehicle(plate: String?, indexVehicle: Int, vehicleType: VehicleType?) {
        if (plate?.let { vehicleList.elementAt(indexVehicle).plate?.contains(it) } == true) {
            val fee = calculateFee(vehicleList.elementAt(indexVehicle), vehicleType)
            val totalEarnings = onSuccess(fee, vehicleList.elementAt(indexVehicle))
        } else {
            onError()
        }


    }

    private fun calculateFee(vehicle: Vehicle, vehicleType: VehicleType?): Int {

        val parkedTime = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis)
        val discountCard = vehicle.discountCard?.isNotEmpty() == true
        var totalFee = 0
        if (parkedTime <= 720000) {
            totalFee = vehicleType?.rate ?: 0
        } else {
            println("Additional Charges....")
        }

        when (discountCard) {
            true -> {
                val discountCard = (totalFee * 0.15).roundToInt()
                totalFee -= discountCard
            }
            false -> println("There is no discount")
        }
        return totalFee
    }

    private fun onSuccess(fee: Int, vehicle: Vehicle) {
        println("Your fee is $fee. Come back soon.")
        vehicleList.remove(vehicle)
    }

    fun onError() {
        println("Sorry, the check-out failed")
    }


}




