import java.util.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt


data class ParkingSpace(var vehicle: Vehicle) {
    private var vehicleList = mutableSetOf<Vehicle>()

    companion object {
        var countRemoveVehicles = 0
        var addFee = 0
    }


    fun getListVehicles(vehicles: MutableSet<Vehicle>) {
        vehicleList = vehicles
    }


    fun checkOutVehicle(plate: String?, indexVehicle: Int, vehicleType: VehicleType?) {
            when (plate?.let { vehicleList.elementAt(indexVehicle).plate.contains(it)}) {
            true-> {
                val fee = calculateFee(vehicleList.elementAt(indexVehicle), vehicleType)
                onSuccess(fee, vehicleList.elementAt(indexVehicle))
            }
            else -> onError()
        }


    }

    private fun calculateFee(vehicle: Vehicle, vehicleType: VehicleType?): Int {

        val parkedTime = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis)
        val hasDiscountCard = vehicle.discountCard?.isNotBlank() == true
        var totalFee = 0
        when (parkedTime <= 720000) {
            true -> totalFee = vehicleType?.rate ?: 0
            else -> {}
        }
        when (hasDiscountCard) {
            true -> {
                val discountCard = floor(totalFee * 0.15).toInt()
                totalFee -= discountCard
            }
            false -> println("There is no discount")
        }
        return totalFee
    }


    private fun onSuccess(fee: Int, vehicle: Vehicle) {

        println("Your fee is $fee. Come back soon.")
        vehicleList.remove(vehicle)
        countRemoveVehicles += 1
        Parking.totalEarnings = Pair(countRemoveVehicles, fee + addFee)
        addFee += fee
    }

   private fun onError() = println("Sorry, the check-out failed")


}




